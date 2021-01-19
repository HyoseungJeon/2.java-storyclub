package io.namoosori.travelclub.step4.mapper;

import io.namoosori.travelclub.step1.entity.club.ClubMembership;
import io.namoosori.travelclub.step1.entity.club.CommunityMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MembershipMapper {
    void create(ClubMembership membership);
    public ClubMembership retrieve(String email);
    public List<ClubMembership> retrieveById(String clubId);
    public List<ClubMembership> retrieveByEmail(String memberEmail);
    public void update(ClubMembership membership);
    public void delete(String clubId);

    public boolean exists(String clubId);
}
