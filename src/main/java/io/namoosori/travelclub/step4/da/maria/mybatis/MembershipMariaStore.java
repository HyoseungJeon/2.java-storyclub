package io.namoosori.travelclub.step4.da.maria.mybatis;

import io.namoosori.travelclub.step1.entity.club.ClubMembership;
import io.namoosori.travelclub.step4.mapper.ClubMapper;
import io.namoosori.travelclub.step4.mapper.MembershipMapper;
import io.namoosori.travelclub.step4.store.MembershipStore;
import io.namoosori.travelclub.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MembershipMariaStore implements MembershipStore {
    @Override
    public void create(ClubMembership membership) {
        try (SqlSession session = MybatisUtil.createSession();){
            MembershipMapper membershipMapper = session.getMapper(MembershipMapper.class);
            membershipMapper.create(membership);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClubMembership retrieve(String email) {
        ClubMembership clubMembership = null;
        try (SqlSession session = MybatisUtil.createSession();){
            MembershipMapper membershipMapper = session.getMapper(MembershipMapper.class);
            clubMembership = membershipMapper.retrieve(email);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return clubMembership;
    }

    @Override
    public List<ClubMembership> retrieveById(String clubId) {
        List<ClubMembership> clubMemberships = new ArrayList<>();
        try (SqlSession session = MybatisUtil.createSession();){
            MembershipMapper membershipMapper = session.getMapper(MembershipMapper.class);
            clubMemberships = membershipMapper.retrieveById(clubId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return clubMemberships;
    }

    @Override
    public List<ClubMembership> retrieveByEmail(String memberEmail) {
        List<ClubMembership> clubMemberships = new ArrayList<>();
        try (SqlSession session = MybatisUtil.createSession();){
            MembershipMapper membershipMapper = session.getMapper(MembershipMapper.class);
            clubMemberships = membershipMapper.retrieveByEmail(memberEmail);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return clubMemberships;
    }

    @Override
    public void update(ClubMembership membership) {
        try (SqlSession session = MybatisUtil.createSession();){
            MembershipMapper membershipMapper = session.getMapper(MembershipMapper.class);
            membershipMapper.update(membership);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(String clubId) {
        try (SqlSession session = MybatisUtil.createSession();){
            MembershipMapper membershipMapper = session.getMapper(MembershipMapper.class);
            membershipMapper.delete(clubId);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exists(String clubId) {
        return Optional.ofNullable(retrieveById(clubId)).isPresent();
    }
}
