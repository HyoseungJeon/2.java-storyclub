package io.namoosori.travelclub.step4.mapper;

import io.namoosori.travelclub.step1.entity.club.CommunityMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberMapper {
    public String create(@Param("member") CommunityMember member);
    public CommunityMember retrieve(String email);
    public List<CommunityMember> retrieveByName(String name);
    public void update(@Param("member") CommunityMember member);
    public void delete(String email);

    public boolean exists(String email);
}
