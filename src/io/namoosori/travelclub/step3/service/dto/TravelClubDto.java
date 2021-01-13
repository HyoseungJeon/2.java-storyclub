package io.namoosori.travelclub.step3.service.dto;

import io.namoosori.travelclub.step1.entity.club.ClubMembership;
import io.namoosori.travelclub.step1.entity.club.TravelClub;
import io.namoosori.travelclub.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class TravelClubDto {
    private String usid;
    private String name;
    private String intro;
    private String foundationDay;

    private List<ClubMembershipDto> membershipList;

    public TravelClubDto() {
        this.membershipList = new ArrayList<ClubMembershipDto>();
    }

    public TravelClubDto(String name, String intro) {
        this();
        this.name = name;
        this.intro = intro;
        this.foundationDay = DateUtil.today();
    }

    public TravelClubDto(TravelClub travelClub){
        this();
        this.usid = travelClub.getUsid();
        this.name = travelClub.getName();
        this.intro = travelClub.getIntro();
        this.foundationDay = travelClub.getFoundationDay();

        for(ClubMembership membership : travelClub.getMembershipList()){
            membershipList.add(new ClubMembershipDto(membership));
        }
    }

    public TravelClub toTravelClub(){
        TravelClub travelClub = new TravelClub(name, intro);
        travelClub.setUsid(usid);
        travelClub.setFoundationDay(foundationDay);

        for(ClubMembershipDto clubMembershipDto : membershipList){
            travelClub.getMembershipList().add(clubMembershipDto.toMembership());
        }
        return travelClub;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TravelClubDto{");
        sb.append("usid='").append(usid).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", intro='").append(intro).append('\'');
        sb.append(", foundationDay='").append(foundationDay).append('\'');

        int i = 0;
        for(ClubMembershipDto membershipDto : membershipList){
            sb.append(" [" + i +"] Club member ").append(membershipDto.toString()).append("\n");
            i++;
        }
        sb.append('}');
        return sb.toString();
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

    public List<ClubMembershipDto> getMembershipList() {
        return membershipList;
    }

    public void setMembershipList(List<ClubMembershipDto> membershipList) {
        this.membershipList = membershipList;
    }
}
