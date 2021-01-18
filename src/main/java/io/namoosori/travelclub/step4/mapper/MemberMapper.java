package io.namoosori.travelclub.step4.mapper;

import io.namoosori.travelclub.step4.da.dto.CommunityMemberDto;

import java.util.List;

public interface MemberMapper {
    public String create(CommunityMemberDto member);
    public CommunityMemberDto retrieve(String email);
    public String retrieveByName(String name);
    public void update(CommunityMemberDto member);
    public void delete(String email);

    public boolean exists(String email);
}
