package io.namoosori.travelclub.step4.service;

import io.namoosori.travelclub.step4.service.dto.PostingDto;

import java.util.List;

public interface PostingService {
    public String register(String boardId, PostingDto postingDto);
    public PostingDto find(String postingId);
    public List<PostingDto> findByBoardId(String boardId);
    public void modify(PostingDto postingDto);
    public void remove(String postingId);
    public void removeAllIn(String boardId);
}
