package io.namoosori.travelclub.step4.mapper;

import io.namoosori.travelclub.step1.entity.board.SocialBoard;

import java.util.List;

public interface BoardMapper{
    public String create(SocialBoard board);
    public SocialBoard retrieve(String boardId);
    public List<SocialBoard> retrieveByName(String boardId);
    public void update(SocialBoard board);
    public void delete(String boardId);

    public boolean exists(String boardId);
}
