package io.namoosori.travelclub.step4.store;

import io.namoosori.travelclub.step1.entity.club.CommunityMember;

import java.util.List;

public interface MemberStore {
    public String create(CommunityMember member);
    public CommunityMember retrieve(String email);
    public List<CommunityMember> retrieveByName(String name);
    public void update(CommunityMember member);
    public void delete(String email);

    public boolean exists(String email);
}