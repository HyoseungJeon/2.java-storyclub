package io.namoosori.travelclub.step4.da.maria;

import io.namoosori.travelclub.step1.entity.board.SocialBoard;
import io.namoosori.travelclub.step4.store.BoardStore;

import java.util.List;

public class BoardMariaStore implements BoardStore {
    @Override
    public String create(SocialBoard board) {
        return null;
    }

    @Override
    public SocialBoard retrieve(String boardId) {
        return null;
    }

    @Override
    public List<SocialBoard> retrieveByName(String boardId) {
        return null;
    }

    @Override
    public void update(SocialBoard board) {

    }

    @Override
    public void delete(String boardId) {

    }

    @Override
    public boolean exists(String boardId) {
        return false;
    }
}
