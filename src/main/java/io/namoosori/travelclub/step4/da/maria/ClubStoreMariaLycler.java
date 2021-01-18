package io.namoosori.travelclub.step4.da.maria;

import io.namoosori.travelclub.step4.store.*;

public class ClubStoreMariaLycler implements ClubStoreLycler {
    private static ClubStoreMariaLycler lycler;

    public ClubStoreMariaLycler(){}

    public static ClubStoreMariaLycler getInstance(){
        if(lycler == null)
            lycler = new ClubStoreMariaLycler();

        return lycler;
    }

    @Override
    public MemberStore requestMemberStore() {
        return new MemberMariaStore();
    }

    @Override
    public ClubStore requestClubStore() {
        return new ClubMariaStore();
    }

    @Override
    public BoardStore requestBoardStore() {
        return new BoardMariaStore();
    }

    @Override
    public PostingStore requestPostingStore() {
        return new PostingMariaStore();
    }
}
