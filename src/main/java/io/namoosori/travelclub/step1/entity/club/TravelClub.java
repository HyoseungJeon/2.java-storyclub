package io.namoosori.travelclub.step1.entity.club;

import io.namoosori.travelclub.step1.entity.AutoldEntity;
import io.namoosori.travelclub.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class TravelClub implements AutoldEntity {
    private static final int MINMUM_NAME_LENGTH = 3;
    private static final int MINMUM_INTRO_LENGTH = 10;
    public static final String ID_FORMAT = "%05d";

    private String usid;
    private String name;
    private String intro;
    private String foundationDay;

    private String boardId;
    private List<ClubMembership> membershipList;

    public TravelClub(String usid, String name, String intro, String foundationDay, String boardId) {
        this.usid = usid;
        this.name = name;
        this.intro = intro;
        this.foundationDay = foundationDay;
        this.boardId = boardId;
    }


    /*create table TravelClub(
        usid varchar(20),
        name varchar(20),
        intro varchar(50),
        foundationDay varchar(20),

        boardId varchar(20),
        membershipList text
    );*/

    private TravelClub(){ this.membershipList = new ArrayList<ClubMembership>();
    }

    public TravelClub(String name, String intro){
        this();
        this.setName(name);
        this.setIntro(intro);
        this.foundationDay = DateUtil.today();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("TravelClub{");
        builder.append("usid='").append(usid).append('\'');
        builder.append(", name='").append(name).append('\'');
        builder.append(", intro='").append(intro).append('\'');
        builder.append(", foundationDay='").append(foundationDay).append('\'');
        builder.append('}');
        return builder.toString();
    }

    public static TravelClub getSample(boolean keyIncluded){
        String name = "JavaTravelClub";
        String intro = "Travel club to the Java island.";
        TravelClub club = new TravelClub(name, intro);

        if (keyIncluded){
            int sequence = 21;
            club.setAutoId(String.format(ID_FORMAT, sequence));
        }

        return club;
    }

    public ClubMembership getMembershipBy(String email){
        if(email == null || email.isEmpty()){
            return null;
        }

        for (ClubMembership clubMembership : this.membershipList){
            if(email.equals(clubMembership.getMemberEmail())){
                return clubMembership;
            }
        }
        return null;
    }

    @Override
    public String getId() {
        return usid;
    }

    @Override
    public String getIdFormat() {
        return ID_FORMAT;
    }

    @Override
    public void setAutoId(String autoId) {
        this.usid = autoId;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length() < MINMUM_NAME_LENGTH){
            throw new IllegalArgumentException("Name should be longer than " + MINMUM_NAME_LENGTH);
        }

        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        if(intro.length() < MINMUM_INTRO_LENGTH){
            throw new IllegalArgumentException("Intro should be longer than " + MINMUM_INTRO_LENGTH);
        }
        this.intro = intro;
    }

    public String getFoundationDay() {
        return foundationDay;
    }

    public void setFoundationDay(String foundationDay) {
        this.foundationDay = foundationDay;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public List<ClubMembership> getMembershipList() {
        return membershipList;
    }

    public void setMembershipList(List<ClubMembership> membershipList) {
        this.membershipList = membershipList;
    }
}
