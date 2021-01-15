package io.namoosori.travelclub.step4.logic;

import io.namoosori.travelclub.step1.entity.board.SocialBoard;
import io.namoosori.travelclub.step1.entity.club.TravelClub;
import io.namoosori.travelclub.step4.da.map.ClubStoreMapLycler;
import io.namoosori.travelclub.step4.service.BoardService;
import io.namoosori.travelclub.step4.service.dto.BoardDto;
import io.namoosori.travelclub.step4.store.BoardStore;
import io.namoosori.travelclub.step4.store.ClubStore;
import io.namoosori.travelclub.step4.util.BoardDuplicationException;
import io.namoosori.travelclub.step4.util.NoSuchBoardException;
import io.namoosori.travelclub.step4.util.NoSuchClubException;
import io.namoosori.travelclub.step4.util.NoSuchMemberException;
import io.namoosori.travelclub.util.StringUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BoardServiceLogic implements BoardService {
    //
    private BoardStore boardStore;
    private ClubStore clubStore;

    public BoardServiceLogic() {
        //

        this.boardStore = ClubStoreMapLycler.getInstance().requestBoardStore();
        this.clubStore = ClubStoreMapLycler.getInstance().requestClubStore();
    }

    @Override
    public String register(BoardDto boardDto) {
        //
        String boardId = boardDto.getId();

        Optional.ofNullable(boardStore.retrieve(boardId))
                .ifPresent((boardFound) -> {
                    throw new BoardDuplicationException("Board already exists in the club -->" + boardFound.getName());
                });

        TravelClub clubFound = Optional.ofNullable(clubStore.retrieve(boardId))
                .orElseThrow(() -> new NoSuchClubException("No such club with id: " + boardId));

        return Optional.ofNullable(clubFound.getMembershipBy(boardDto.getAdminEmail()))
                .map(adminEmail -> boardStore.create(boardDto.toBoard()))
                .orElseThrow(() -> new NoSuchMemberException("In the club, No such member with admin's email -->" + boardDto.getAdminEmail()));
    }

    @Override
    public BoardDto find(String boardId) {
        //
        return Optional.ofNullable(boardStore.retrieve(boardId))
                .map(board -> new BoardDto(board))
                .orElseThrow(() -> new NoSuchBoardException("No such board with id --> " + boardId));
    }

    @Override
    public List<BoardDto> findByName(String boardName) {
        //
        List<SocialBoard> boards = boardStore.retrieveByName(boardName);

        if (boards == null || boards.isEmpty()) {
            throw new NoSuchBoardException("No such board with name --> " + boardName);
        }

        return boards.stream()
                .map(board -> new BoardDto(board))
                .collect(Collectors.toList());
    }

    @Override
    public BoardDto findByClubName(String clubName) {
        //
        return Optional.ofNullable(clubStore.retrieveByName(clubName))
                .map(club -> boardStore.retrieve(club.getId()))
                .map(board -> new BoardDto(board))
                .orElseThrow(() -> new NoSuchClubException("No such club with name: " + clubName));
    }

    @Override
    public void modify(BoardDto boardDto) {
        //
        SocialBoard targetBoard = Optional.ofNullable(boardStore.retrieve(boardDto.getId()))
                .orElseThrow(() -> new NoSuchBoardException("No such board with id --> " + boardDto.getId()));

        if (StringUtil.isEmpty(boardDto.getName())) {
            boardDto.setName(targetBoard.getName());
        }
        if (StringUtil.isEmpty(boardDto.getAdminEmail())) {

            boardDto.setAdminEmail(targetBoard.getAdminEmail());
        } else {
            Optional.ofNullable(clubStore.retrieve(boardDto.getClubId()))
                    .map(club -> club.getMembershipBy(boardDto.getAdminEmail()))
                    .orElseThrow(() -> new NoSuchMemberException("In the club, No such member with admin's email -->" + boardDto.getAdminEmail()));
        }

        boardStore.update(boardDto.toBoard());
    }

    @Override
    public void remove(String boardId) {
        //
        if (!boardStore.exists(boardId)) {
            throw new NoSuchBoardException("No such board with id --> " + boardId);
        }

        boardStore.delete(boardId);
    }
}
