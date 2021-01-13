package io.namoosori.travelclub.step4.da.map;

import io.namoosori.travelclub.step1.entity.board.Posting;
import io.namoosori.travelclub.step3.logic.storage.MapStroage;
import io.namoosori.travelclub.step4.store.PostingStore;
import io.namoosori.travelclub.step4.util.PostingDuplicationException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PostingMapStroe implements PostingStore {
    private Map<String, Posting> postingMap;

    public PostingMapStroe(){
        this.postingMap = MapStroage.getInstance().getPostingMap();
    }


    @Override
    public String create(Posting posting) {
        Optional.ofNullable(postingMap.get(posting.getId()))
                .ifPresent(postingId -> {
                    throw new PostingDuplicationException("Already exists: " + postingId);
                });
        postingMap.put(posting.getId(), posting);
        return posting.getId();
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
