package io.namoosori.travelclub.step4.store;

import io.namoosori.travelclub.step1.entity.board.Posting;

import java.util.List;

public interface PostingStore {
    public String create(Posting posting);
    public Posting retrieve(String postingId);
    public List<Posting> retrieveByBoardId(String boardId);
    public List<Posting> retrieveByTitle(String title);
    public void update(Posting posting);
    public void delete(String posingId);

    public boolean exists(String postingId);
}
