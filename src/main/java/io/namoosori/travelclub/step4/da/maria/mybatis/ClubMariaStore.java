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
import org.apache.ibatis.annotations.Param;
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
//        create(new TravelClub("test10", "testestststestsetset"));
//        TravelClub travelClub = retrieve("1");
//        System.out.println(travelClub.toString());
//        TravelClub travelClub = retrieveByName("test");
//        System.out.println(travelClub.toString());
//        update(new TravelClub("1","newtest15","adaskasmkcasmkdasmkd"));
//        delete("5");
    }

    @Override
    public String create(TravelClub club) {
        String usid= null;
        try (SqlSession session = MybatisUtil.createSession()){
            ClubMapper clubMapper = session.getMapper(ClubMapper.class);
            clubMapper.create(club);
            usid = clubMapper.retrieveByName(club.getName()).getUsid();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usid;
    }

    @Override
    public TravelClub retrieve(String clubId) {
        TravelClub travelClub = null;
        try(SqlSession session = MybatisUtil.createSession()){
            ClubMapper clubMapper = session.getMapper(ClubMapper.class);
            travelClub = clubMapper.retrieve(clubId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return travelClub;
    }

    @Override
    public TravelClub retrieveByName(String name) {
        TravelClub travelClub = null;
        try (SqlSession session = MybatisUtil.createSession()){
            ClubMapper clubMapper = session.getMapper(ClubMapper.class);

            travelClub = clubMapper.retrieveByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return travelClub;
    }

    @Override
    public void update(TravelClub club) {
        try (SqlSession session = MybatisUtil.createSession()) {
            ClubMapper clubMapper = session.getMapper(ClubMapper.class);
            clubMapper.update(club);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String clubId) {
        try (SqlSession session = MybatisUtil.createSession()){
            ClubMapper clubMapper = session.getMapper(ClubMapper.class);
            clubMapper.delete(clubId);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exists(String clubId) {
        return Optional.ofNullable(retrieve(clubId)).isPresent();
    }
}
