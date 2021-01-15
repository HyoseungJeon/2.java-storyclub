package io.namoosori.travelclub.step4.da.maria;

import io.namoosori.travelclub.step1.entity.board.Posting;
import io.namoosori.travelclub.step4.store.PostingStore;

import java.util.List;

public class PostingMariaStore implements PostingStore {
    @Override
    public String create(Posting posting) {
        return null;
    }

    @Override
    public Posting retrieve(String postingId) {
        return null;
    }

    @Override
    public List<Posting> retrieveByBoardId(String boardId) {
        return null;
    }

    @Override
    public List<Posting> retrieveByTitle(String title) {
        return null;
    }

    @Override
    public void update(Posting posting) {

    }

    @Override
    public void delete(String posingId) {

    }

    @Override
    public boolean exists(String postingId) {
        return false;
    }
}
