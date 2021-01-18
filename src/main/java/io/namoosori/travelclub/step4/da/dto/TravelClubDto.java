package io.namoosori.travelclub.step4.da.dto;

import io.namoosori.travelclub.step1.entity.AutoldEntity;
import io.namoosori.travelclub.step1.entity.club.ClubMembership;
import io.namoosori.travelclub.step1.entity.club.TravelClub;
import io.namoosori.travelclub.util.DateUtil;
import io.namoosori.travelclub.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class TravelClubDto implements AutoldEntity {
    private static final int MINMUM_NAME_LENGTH = 3;
    private static final int MINMUM_INTRO_LENGTH = 10;
    public static final String ID_FORMAT = "%05d";

    private String usid;
    private String name;
    private String intro;
    private String foundationDay;

    private String boardId;
    private String membershipList;

    public TravelClubDto(String usid, String name, String intro, String foundationDay, String boardId) {
        this.usid = usid;
        this.name = name;
        this.intro = intro;
        this.foundationDay = foundationDay;
        this.boardId = boardId;
    }

    public TravelClub toTravelClub(TravelClubDto travelClubDto){
        TravelClub travelClub = new TravelClub(
          travelClubDto.getUsid(),
          travelClubDto.getName(),
          travelClubDto.getIntro(),
          travelClubDto.getFoundationDay(),
          travelClubDto.getBoardId()
        );
        travelClub.setMembershipList(JsonUtil.fromJsonList(travelClubDto.getMembershipList(),ClubMembership.class));
        return travelClub;
    }

    public TravelClubDto toTravelClubDto(TravelClub travelClub){
        TravelClubDto travelClubDto1 = new TravelClubDto(
                travelClub.getUsid(),
                travelClub.getName(),
                travelClub.getIntro(),
                travelClub.getFoundationDay(),
                travelClub.getBoardId()
        );
        travelClubDto1.setMembershipList(JsonUtil.toJson(travelClub.getMembershipList()));
        return travelClubDto1;
    }


    /*create table TravelClub(
        usid varchar(20),
        name varchar(20),
        intro varchar(50),
        foundationDay varchar(20),

        boardId varchar(20),
        membershipList text
    );*/
    public TravelClubDto(){};


    public TravelClubDto(String name, String intro){
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
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
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

    public String getMembershipList() {
        return membershipList;
    }

    public void setMembershipList(String membershipList) {
        this.membershipList = membershipList;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getIdFormat() {
        return null;
    }

    @Override
    public void setAutoId(String autoId) {

    }
}
