package io.namoosori.travelclub.step4.service;

import io.namoosori.travelclub.step4.service.dto.ClubMembershipDto;

import java.util.List;

public interface MembershipService {
    void addMembership(ClubMembershipDto membershipDto);
    public ClubMembershipDto findMembershipIn(String clubId, String memberId);
    public List<ClubMembershipDto> findAllMembershipsIn(String clubId);
    public void modifyMembership(String clubId, ClubMembershipDto member);
    public void removeMembership(String clubId, String memberId);
}
