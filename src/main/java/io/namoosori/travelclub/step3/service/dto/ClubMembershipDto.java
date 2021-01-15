package io.namoosori.travelclub.step3.service.dto;

import io.namoosori.travelclub.step1.entity.club.ClubMembership;
import io.namoosori.travelclub.step1.entity.club.RoleInClub;
import io.namoosori.travelclub.util.DateUtil;

public class ClubMembershipDto {
    private String clubId;
    private String memberEmail;
    private RoleInClub role;
    private String joinDate;

    public ClubMembershipDto() {
        this.role = RoleInClub.Member;
        this.joinDate = DateUtil.today();
    }

    public ClubMembershipDto(String clubId, String memberEmail) {
        this.clubId = clubId;
        this.memberEmail = memberEmail;
    }
    public ClubMembershipDto(TravelClubDto clubDto, MemberDto memberDto){
        this();
        this.clubId = clubDto.getUsid();
        this.memberEmail = memberDto.getEmail();
    }

    public ClubMembershipDto(ClubMembership membership) {
        //
        this.clubId = membership.getClubId();
        this.memberEmail = membership.getMemberEmail();
        this.role = membership.getRole();
        this.joinDate = membership.getJoinDate();
    }

    public ClubMembership toMembership(){
        ClubMembership membership = new ClubMembership(clubId, memberEmail);
        membership.setRole(role);
        membership.setJoinDate(joinDate);
        return membership;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClubMembershipDto{");
        sb.append("clubId='").append(clubId).append('\'');
        sb.append(", memberEmail='").append(memberEmail).append('\'');
        sb.append(", role=").append(role);
        sb.append(", joinDate='").append(joinDate).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public RoleInClub getRole() {
        return role;
    }

    public void setRole(RoleInClub role) {
        this.role = role;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
