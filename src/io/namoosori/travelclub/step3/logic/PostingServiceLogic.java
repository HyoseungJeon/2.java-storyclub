package io.namoosori.travelclub.step3.logic;

import io.namoosori.travelclub.step1.entity.board.Posting;
import io.namoosori.travelclub.step1.entity.board.SocialBoard;
import io.namoosori.travelclub.step1.entity.club.TravelClub;
import io.namoosori.travelclub.step3.logic.storage.MapStroage;
import io.namoosori.travelclub.step3.service.PostingService;
import io.namoosori.travelclub.step3.service.dto.PostingDto;
import io.namoosori.travelclub.step3.util.NoSuchBoardException;
import io.namoosori.travelclub.step3.util.NoSuchMemberException;
import io.namoosori.travelclub.step3.util.NoSuchPostingException;
import io.namoosori.travelclub.util.StringUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostingServiceLogic implements PostingService {

    private Map<String, SocialBoard> boardMap;
    private Map<String, Posting> postingMap;
    private Map<String, TravelClub> clubMap;

    public PostingServiceLogic() {
        this.boardMap = MapStroage.getInstance().getSocialBoardMap();
        this.postingMap = MapStroage.getInstance().getPostingMap();
        this.clubMap = MapStroage.getInstance().getClubMap();
    }

    @Override
    public String register(String boardId, PostingDto postingDto) {
        Optional.ofNullable(clubMap.get(boardId))
                .map(club -> club.getMembershipBy(postingDto.getWriterEmail()))
                .orElseThrow(() -> new NoSuchMemberException("In the club, No such member with admin's email -->" + postingDto.getWriterEmail()));
        Posting newPosting = Optional.ofNullable(boardMap.get(boardId))
                .map(foundBoard -> postingDto.toPostingIn(foundBoard))
                .orElseThrow(()-> new NoSuchBoardException("No such board with id --> " + boardId));
        return newPosting.getBoardId();
    }

    @Override
    public PostingDto find(String postingId) {
        return Optional.ofNullable(postingMap.get(postingId))
                .map(foundPosting -> new PostingDto(foundPosting))
                .orElseThrow( () -> new NoSuchPostingException("No such posting with id : " + postingId));
    }

    @Override
    public List<PostingDto> findByBoardId(String boardId) {
        SocialBoard foundBoard = boardMap.get(boardId);
        if(foundBoard == null){
            throw new NoSuchBoardException("No such board with id --> " + boardId);
        }

        Optional.ofNullable(boardMap.get(boardId))
                .orElseThrow(() -> new NoSuchBoardException("No such board with id --> " + boardId));

        return postingMap.values().stream()
                .filter(posting -> posting.getBoardId().equals(boardId))
                .map(targetPosting -> new PostingDto(targetPosting))
                .collect(Collectors.toList());
    }

    @Override
    public void modify(PostingDto postingDto) {
        String postingId = postingDto.getUsid();

        Posting targetPosting = Optional.ofNullable(postingMap.get(postingId))
                .orElseThrow(() -> new NoSuchMemberException("No such posting with id : " + postingId));



        if(StringUtil.isEmpty(postingDto.getTitle()))
            postingDto.setTitle(targetPosting.getTitle());
        if(StringUtil.isEmpty(postingDto.getContents()))
            postingDto.setContents(targetPosting.getContents());

        Posting newPosting = postingDto.toPostingIn(postingDto.getUsid(),targetPosting.getBoardId());

        postingMap.put(postingId, newPosting);
    }

    @Override
    public void remove(String postingId) {
        Optional.ofNullable(postingMap.get(postingId))
                .orElseThrow(() -> new NoSuchMemberException("No such posting with id : " + postingId));
        postingMap.remove(postingId);
    }

    @Override
    public void removeAllIn(String boardId) {
        postingMap.values().stream()
                .forEach(posting -> postingMap.remove(posting.getBoardId()));
    }
}
