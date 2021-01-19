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
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberMariaStore implements MemberStore {

    public static void main(String[] args) {
        //MemberMariaStore mariaStore = new MemberMariaStore();
        //mariaStore.create(new CommunityMember());
        //mariaStore.retrieve("targetemail");
        //mariaStore.retrieveByName("test1");
        //mariaStore.update(new CommunityMember());
        //mariaStore.delete("targetemail");
    }
    @Override
    public String create(CommunityMember member) {
        SqlSession session = MybatisUtil.createSession();
        MemberMapper memberMapper = session.getMapper(MemberMapper.class);
        memberMapper.create(member);
        return member.getEmail();
    }

    @Override
    public CommunityMember retrieve(String targetemail) {
        SqlSession session = MybatisUtil.createSession();
        MemberMapper memberMapper = session.getMapper(MemberMapper.class);

        return memberMapper.retrieve(targetemail);
    }

    @Override
    public List<CommunityMember> retrieveByName(String targetname) {
        SqlSession session = MybatisUtil.createSession();
        MemberMapper memberMapper = session.getMapper(MemberMapper.class);
        return memberMapper.retrieveByName(targetname);
    }

    @Override
    public void update(CommunityMember member) {
        SqlSession session = MybatisUtil.createSession();
        MemberMapper memberMapper = session.getMapper(MemberMapper.class);

        memberMapper.update(member);
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
