package io.namoosori.travelclub.step4.da.maria;

import io.namoosori.travelclub.step4.da.map.*;
import io.namoosori.travelclub.step4.store.*;

public class ClubStoremariaLycler implements ClubStoreLycler {
    private static ClubStoremariaLycler lycler;

    private ClubStoremariaLycler(){}

    public static ClubStoremariaLycler getInstance(){
        if(lycler == null)
            lycler = new ClubStoremariaLycler();

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
