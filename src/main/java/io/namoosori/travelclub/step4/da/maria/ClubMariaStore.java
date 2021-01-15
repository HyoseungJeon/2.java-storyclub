package io.namoosori.travelclub.step4.da.maria;

import io.namoosori.travelclub.step1.entity.club.ClubMembership;
import io.namoosori.travelclub.step1.entity.club.TravelClub;
import io.namoosori.travelclub.step4.resources.Sql;
import io.namoosori.travelclub.step4.store.ClubStore;
import io.namoosori.travelclub.util.ConnectionUtil;
import io.namoosori.travelclub.util.JsonUtil;

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
        //create(new TravelClub("test", "testestststestsetset"));
        //retrieve("1");
        //retrieveByName("name");
        //delete("1");
    }

    @Override
    public String create(TravelClub club) {
        String result = null;
        //String sql = "insert travelclub set usid=?, name=?,intro=?, foundationDay=?, boardId=?, membershipList=?";
        String sql = Sql.C1c;
        try(Connection connection = ConnectionUtil.createConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, club.getUsid());
            pstmt.setString(2, club.getName());
            pstmt.setString(3, club.getIntro());
            pstmt.setString(4, club.getFoundationDay());
            pstmt.setString(5, club.getBoardId());
            pstmt.setString(6, JsonUtil.toJson(club.getMembershipList()));

            pstmt.executeUpdate();
            result = club.getId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public TravelClub retrieve(String clubId) {
        TravelClub result = null;
        ResultSet rs = null;
        String sql = "select * from travelclub where usid=?";

        try (Connection connection = ConnectionUtil.createConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1,clubId);

            rs = pstmt.executeQuery();

            while (rs.next()){
                String usid = rs.getString("usid");
                String name = rs.getString("name");
                String intro = rs.getString("intro");
                String foundationDay = rs.getString("foundationDay");
                String boardId = rs.getString("boardId");
                String membershipListStr = rs.getString("membershipList");

                result = new TravelClub(usid, name, intro, foundationDay,boardId);
                result.setMembershipList(JsonUtil.fromJsonList(membershipListStr, ClubMembership.class));
            }

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public TravelClub retrieveByName(String name) {
        TravelClub result = null;
        ResultSet rs = null;
        String sql = "select * from travelclub where name=?";

        try (Connection connection = ConnectionUtil.createConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1,name);

            rs = pstmt.executeQuery();

            while (rs.next()){
                String usid = rs.getString("usid");
                String ename = rs.getString("name");
                String intro = rs.getString("intro");
                String foundationDay = rs.getString("foundationDay");
                String boardId = rs.getString("boardId");
                String membershipListStr = rs.getString("membershipList");

                result = new TravelClub(usid, ename, intro, foundationDay,boardId);
                result.setMembershipList(JsonUtil.fromJsonList(membershipListStr, ClubMembership.class));
            }

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public void update(TravelClub club) {
        String sql = "update travelclub set usid=?, name=?,intro=?, foundationDay=?, boardId=?, membershipList=? where usid=?";

        try(Connection connection = ConnectionUtil.createConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, club.getUsid());
            pstmt.setString(2, club.getName());
            pstmt.setString(3, club.getIntro());
            pstmt.setString(4, club.getFoundationDay());
            pstmt.setString(5, club.getBoardId());
            pstmt.setString(6, club.getMembershipList().toString());
            pstmt.setString(7, club.getUsid());

            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(String clubId) {
        String result = null;
        String sql = "delete from travelclub where usid=?";

        try(Connection connection = ConnectionUtil.createConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, clubId);

            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean exists(String clubId) {
        boolean result = false;
        ResultSet rs = null;
        String sql = "select * from travelclub where usid=?";

        try(Connection connection = ConnectionUtil.createConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, clubId);

            rs = pstmt.executeQuery();
            String usid = rs.getString("usid");
            result = Optional.ofNullable(usid).isPresent();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
