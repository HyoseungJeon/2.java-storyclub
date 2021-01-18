package io.namoosori.travelclub.step4.da.maria.mybatis;

import io.namoosori.travelclub.step1.entity.club.Address;
import io.namoosori.travelclub.step1.entity.club.ClubMembership;
import io.namoosori.travelclub.step1.entity.club.CommunityMember;
import io.namoosori.travelclub.step4.da.dto.CommunityMemberDto;
import io.namoosori.travelclub.step4.da.dto.TravelClubDto;
import io.namoosori.travelclub.step4.mapper.ClubMapper;
import io.namoosori.travelclub.step4.mapper.MemberMapper;
import io.namoosori.travelclub.step4.resources.Sql;
import io.namoosori.travelclub.step4.store.MemberStore;
import io.namoosori.travelclub.util.ConnectionUtil;
import io.namoosori.travelclub.util.JsonUtil;
import io.namoosori.travelclub.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberMariaStore implements MemberStore {
    @Override
    public String create(CommunityMember member) {
        SqlSession session = MybatisUtil.createSession();
        MemberMapper memberMapper = session.getMapper(MemberMapper.class);

        return memberMapper.create(new CommunityMemberDto().toCommunityMemberDto(member));
    }

    @Override
    public CommunityMember retrieve(String targetemail) {
        SqlSession session = MybatisUtil.createSession();
        MemberMapper memberMapper = session.getMapper(MemberMapper.class);

        return new CommunityMemberDto().toCommunityMember(memberMapper.retrieve(targetemail));
    }

    @Override
    public List<CommunityMember> retrieveByName(String targetname) {
        SqlSession session = MybatisUtil.createSession();
        MemberMapper memberMapper = session.getMapper(MemberMapper.class);

        return JsonUtil.fromJsonList(memberMapper.retrieveByName(targetname), CommunityMember.class);
    }

    @Override
    public void update(CommunityMember member) {
        SqlSession session = MybatisUtil.createSession();
        MemberMapper memberMapper = session.getMapper(MemberMapper.class);

        memberMapper.update(new CommunityMemberDto().toCommunityMemberDto(member));
    }

    @Override
    public void delete(String targetemail) {
        SqlSession session = MybatisUtil.createSession();
        MemberMapper memberMapper = session.getMapper(MemberMapper.class);

        memberMapper.delete(targetemail);
    }

    @Override
    public boolean exists(String email) {
        return Optional.ofNullable(retrieve(email)).isPresent();
    }
}
