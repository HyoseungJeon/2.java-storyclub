package io.namoosori.travelclub.step3.service;

import io.namoosori.travelclub.step3.service.dto.BoardDto;

import java.util.List;

public interface BoardService {

    public String register(BoardDto boardDto);
    public BoardDto find(String boardId);
    public List<BoardDto> findByName(String clubName);
    public BoardDto findByClubName(String clubName);
    public void modify(BoardDto board);
    public void remove(String boardId);
}
