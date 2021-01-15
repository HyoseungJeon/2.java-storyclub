package io.namoosori.travelclub.step4.logic;

import io.namoosori.travelclub.step1.entity.board.Posting;
import io.namoosori.travelclub.step4.da.map.ClubStoreMapLycler;
import io.namoosori.travelclub.step4.service.PostingService;
import io.namoosori.travelclub.step4.service.dto.PostingDto;
import io.namoosori.travelclub.step4.store.BoardStore;
import io.namoosori.travelclub.step4.store.ClubStore;
import io.namoosori.travelclub.step4.store.PostingStore;
import io.namoosori.travelclub.step4.util.NoSuchBoardException;
import io.namoosori.travelclub.step4.util.NoSuchMemberException;
import io.namoosori.travelclub.step4.util.NoSuchPostingException;
import io.namoosori.travelclub.util.StringUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostingServiceLogic implements PostingService {
    private BoardStore boardStore;
    private PostingStore postingStore;
    private ClubStore clubStore;

    public PostingServiceLogic(){
        this.boardStore = ClubStoreMapLycler.getInstance().requestBoardStore();
        this.postingStore = ClubStoreMapLycler.getInstance().requestPostingStore();
        this.clubStore = ClubStoreMapLycler.getInstance().requestClubStore();
    }

    @Override
    public String register(String boardId, PostingDto postingDto) {
        Optional.ofNullable(clubStore.retrieve(boardId))
                .map(club -> club.getMembershipBy(postingDto.getWriterEmail()))
                .orElseThrow(() -> new NoSuchMemberException("In the club, No such member with email -->" + postingDto.getWriterEmail()));

        return Optional.ofNullable(boardStore.retrieve(boardId))
                .map(board -> postingStore.create(postingDto.toPostingIn(board)))
                .orElseThrow(() -> new NoSuchBoardException("No such board with id --> " + boardId));
    }

    @Override
    public PostingDto find(String postingId) {
        return Optional.ofNullable(postingStore.retrieve(postingId))
                .map(posting -> new PostingDto(posting))
                .orElseThrow(() -> new NoSuchPostingException("No such posting with id : " + postingId));
    }

    @Override
    public List<PostingDto> findByBoardId(String boardId) {
        Optional.ofNullable(boardStore.retrieve(boardId))
                .orElseThrow(() -> new NoSuchBoardException("No such board with id --> " + boardId));

        return postingStore.retrieveByBoardId(boardId).stream()
                .map(posting -> new PostingDto(posting)).collect(Collectors.toList());
    }

    @Override
    public void modify(PostingDto postingDto) {
        String postingId = postingDto.getUsid();

        Posting targetPosting = Optional.ofNullable(postingStore.retrieve(postingId))
                .orElseThrow(() -> new NoSuchPostingException("No such posting with id : " + postingId));

        if(StringUtil.isEmpty(postingDto.getTitle())){
            postingDto.setTitle(targetPosting.getTitle());
        }
        if(StringUtil.isEmpty(postingDto.getContents())){
            postingDto.setContents(targetPosting.getContents());
        }

        postingStore.update(postingDto.toPostingIn(postingId, targetPosting.getBoardId()));
    }

    @Override
    public void remove(String postingId) {

        if(!postingStore.exists(postingId)){
            throw new NoSuchPostingException("No such posting with id : " + postingId);
        }

        postingStore.delete(postingId);
    }

    @Override
    public void removeAllIn(String boardId) {
        postingStore.retrieveByBoardId(boardId).stream()
                .forEach(posting -> postingStore.delete(posting.getId()));
    }
}
