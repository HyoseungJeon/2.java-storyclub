package io.namoosori.travelclub.step4.da;

import io.namoosori.travelclub.step4.da.maria.*;
import io.namoosori.travelclub.step4.store.*;

public class ClubStoreMasterLycler implements ClubStoreLycler {
    private static ClubStoreLycler lycler;

    private ClubStoreMasterLycler(){}

    public static ClubStoreLycler getInstance(){

        //ClubStoreMariaLycler are 2, so u can convert each Lycler;
        //Check directory plz.
        if(lycler == null)
            lycler = new ClubStoreMariaLycler();

        return lycler;
    }

    @Override
    public MemberStore requestMemberStore() {
        return lycler.requestMemberStore();
    }

    @Override
    public ClubStore requestClubStore() {
        return lycler.requestClubStore();
    }

    @Override
    public BoardStore requestBoardStore() {
        return lycler.requestBoardStore();
    }

    @Override
    public PostingStore requestPostingStore() {
        return lycler.requestPostingStore();
    }
}
