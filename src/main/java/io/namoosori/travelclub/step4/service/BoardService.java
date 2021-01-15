package io.namoosori.travelclub.step4.service;

import io.namoosori.travelclub.step4.service.dto.BoardDto;
import io.namoosori.travelclub.step4.service.dto.ClubMembershipDto;
import io.namoosori.travelclub.step4.service.dto.TravelClubDto;

import java.util.List;

public interface BoardService {
    public String register(BoardDto boardDto);
    public BoardDto find(String boardId);
    public List<BoardDto> findByName(String boardName);
    public BoardDto findByClubName(String clubName);
    public void modify(BoardDto boardDto);
    public void remove(String boardId);
}
