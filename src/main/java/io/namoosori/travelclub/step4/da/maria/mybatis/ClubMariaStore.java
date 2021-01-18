package io.namoosori.travelclub.step4.da.maria.mybatis;

import io.namoosori.travelclub.step1.entity.club.ClubMembership;
import io.namoosori.travelclub.step1.entity.club.TravelClub;
import io.namoosori.travelclub.step4.da.dto.TravelClubDto;
import io.namoosori.travelclub.step4.mapper.BoardMapper;
import io.namoosori.travelclub.step4.mapper.ClubMapper;
import io.namoosori.travelclub.step4.resources.Sql;
import io.namoosori.travelclub.step4.store.ClubStore;
import io.namoosori.travelclub.util.ConnectionUtil;
import io.namoosori.travelclub.util.JsonUtil;
import io.namoosori.travelclub.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ClubMariaStore implements ClubStore {

    public static void main(String[] args) {
        new ClubMariaStore().test();
    }

    public void test(){
        //create(new TravelClub("test15", "testestststestsetset"));
        //TravelClub travelClub = retrieve("21");
        //System.out.println(travelClub.toString());
        //TravelClub travelClub = retrieveByName("test");
        //System.out.println(travelClub.toString());
        //delete("21");
    }

    @Override
    public String create(TravelClub club) {
        SqlSession session = MybatisUtil.createSession();
        ClubMapper clubMapper = session.getMapper(ClubMapper.class);

        return clubMapper.create(new TravelClubDto().toTravelClubDto(club));
    }

    @Override
    public TravelClub retrieve(String clubId) {
        SqlSession session = MybatisUtil.createSession();
        ClubMapper clubMapper = session.getMapper(ClubMapper.class);

        return new TravelClubDto().toTravelClub(clubMapper.retrieve(clubId));
    }

    @Override
    public TravelClub retrieveByName(String name) {
        SqlSession session = MybatisUtil.createSession();
        ClubMapper clubMapper = session.getMapper(ClubMapper.class);

        return new TravelClubDto().toTravelClub(clubMapper.retrieveByName(name));
    }

    @Override
    public void update(TravelClub club) {
        SqlSession session = MybatisUtil.createSession();
        ClubMapper clubMapper = session.getMapper(ClubMapper.class);

        clubMapper.update(new TravelClubDto().toTravelClubDto(club));
    }

    @Override
    public void delete(String clubId) {
        SqlSession session = MybatisUtil.createSession();
        ClubMapper clubMapper = session.getMapper(ClubMapper.class);

        clubMapper.delete(clubId);
    }

    @Override
    public boolean exists(String clubId) {
        return Optional.ofNullable(retrieve(clubId)).isPresent();
    }
}
