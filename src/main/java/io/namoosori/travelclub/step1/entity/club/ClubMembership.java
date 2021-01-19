package io.namoosori.travelclub.step1.entity.club;

import io.namoosori.travelclub.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class ClubMembership {
    private String clubId; //cludId - usid
    private String memberEmail; //member - email
    private RoleInClub role;
    private String joinDate;

    /*create table clubmembership(
        clubId Int,
        memberEmail varchar(30),
        role varchar(10),
        joinDate varchar(20),
        foreign key(clubId) references travelclub(usid),
        foreign key(memberEmail) references CommunityMember(email)
    );*/

    public ClubMembership(TravelClub club, CommunityMember member){
        this.clubId = club.getUsid();
        this.memberEmail = member.getEmail();

        this.role = RoleInClub.Member;
        this.joinDate = DateUtil.today();
    }

    public ClubMembership(String clubId, String memberEmail){

        this.clubId = clubId;
        this.memberEmail = memberEmail;

        this.role = RoleInClub.Member;
        this.joinDate = DateUtil.today();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("ClubMembership{");
        builder.append("clubId='").append(clubId).append('\'');
        builder.append(", memberEmail='").append(memberEmail).append('\'');
        builder.append(", role=").append(role);
        builder.append(", joinDate='").append(joinDate).append('\'');
        builder.append('}');
        return builder.toString();
    }

    public static ClubMembership getSample(TravelClub club, CommunityMember member){

        ClubMembership clubMembership = new ClubMembership(club, member);
        clubMembership.setRole(RoleInClub.Member);

        return clubMembership;
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
