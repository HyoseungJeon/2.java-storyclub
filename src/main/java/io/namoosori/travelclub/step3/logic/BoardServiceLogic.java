package io.namoosori.travelclub.step3.logic;

import io.namoosori.travelclub.step1.entity.board.SocialBoard;
import io.namoosori.travelclub.step1.entity.club.TravelClub;
import io.namoosori.travelclub.step3.logic.storage.MapStroage;
import io.namoosori.travelclub.step3.service.BoardService;
import io.namoosori.travelclub.step3.service.dto.BoardDto;
import io.namoosori.travelclub.step3.util.BoardDuplicationException;
import io.namoosori.travelclub.step3.util.NoSuchBoardException;
import io.namoosori.travelclub.step3.util.NoSuchClubException;
import io.namoosori.travelclub.step3.util.NoSuchMemberException;
import io.namoosori.travelclub.util.StringUtil;

import java.net.CookieHandler;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BoardServiceLogic implements BoardService {
    private Map<String, SocialBoard> boardMap;
    private Map<String, TravelClub> clubMap;

    public BoardServiceLogic() {
        this.boardMap = MapStroage.getInstance().getSocialBoardMap();
        this.clubMap = MapStroage.getInstance().getClubMap();
    }

    @Override
    public String register(BoardDto boardDto) {
        String boardId = boardDto.getClubId();

        Optional.ofNullable(boardMap.get(boardId))
                .ifPresent((targetBoard) -> {
                    throw new BoardDuplicationException("Board already exists in the club -->" + targetBoard.getName());
                });
        TravelClub clubFound = Optional.ofNullable(clubMap.get(boardId))
                .orElseThrow(() -> new NoSuchClubException("No such club with id: " + boardId));

        Optional.ofNullable(clubFound.getMembershipBy(boardDto.getAdminEmail()))
                .orElseThrow(() -> new NoSuchMemberException("In the club, No such member with admin's email -->" + boardDto.getAdminEmail()));

        SocialBoard board = boardDto.toBoard();
        boardMap.put(boardId, board);

        return boardId;
    }

    @Override
    public BoardDto find(String boardId) {
        return Optional.ofNullable(boardMap.get(boardId))
                .map(board -> new BoardDto(board))
                .orElseThrow( ()-> new NoSuchBoardException("No such board with id --> " + boardId));
    }

    @Override
    public List<BoardDto> findByName(String boardName) {
        Collection<SocialBoard> boards = boardMap.values();
        if(boards.isEmpty()){
            throw new NoSuchBoardException("No boards in the stroage.");
        }

        List<BoardDto> boardDtos = boards.stream()
                .filter(board -> board.getName().equals(boardName))
                .map(board -> new BoardDto(board))
                .collect(Collectors.toList());

        if(boardDtos.isEmpty()){
            throw new NoSuchBoardException("No such board with name --> " + boardName);
        }

        return boardDtos;
    }

    @Override
    public BoardDto findByClubName(String clubName) {
        TravelClub foundClub = null;
        for(TravelClub club : clubMap.values()){
            if(club.getName().equals(clubName)){
                foundClub = club;
                break;
            }
        }

        return Optional.ofNullable(foundClub)
                .map(club -> boardMap.get(club.getBoardId()))
                .map(board -> new BoardDto(board))
                .orElseThrow(() -> new NoSuchClubException("No such club with name: " + clubName));
    }

    @Override
    public void modify(BoardDto boardDto) {
        String boardId = boardDto.getClubId();

        SocialBoard targetBoard = Optional.ofNullable(boardMap.get(boardId))
                .orElseThrow(()-> new NoSuchBoardException("No such board with id --> " + boardDto.getId()));

        if(StringUtil.isEmpty(boardDto.getName())){
            boardDto.setName(targetBoard.getName());
        }
        if(StringUtil.isEmpty(boardDto.getAdminEmail())){
            boardDto.setAdminEmail(targetBoard.getAdminEmail());
        } else{
            Optional.ofNullable(clubMap.get(boardDto.getClubId()))
                    .map(club -> club.getMembershipBy(boardDto.getAdminEmail()))
                    .orElseThrow(() -> new NoSuchMemberException("In the club, No such member with admin's email -->" + boardDto.getAdminEmail()));
        }
        boardMap.put(boardId, boardDto.toBoard());
    }

    @Override
    public void remove(String boardId) {
        Optional.ofNullable(boardMap.get(boardId))
                .orElseThrow(() -> new NoSuchBoardException("No such board with id --> " + boardId));
        boardMap.remove(boardId);
    }
}
