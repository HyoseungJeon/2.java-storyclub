package io.namoosori.travelclub.step4.da.maria;

import io.namoosori.travelclub.step1.entity.club.Address;
import io.namoosori.travelclub.step1.entity.club.ClubMembership;
import io.namoosori.travelclub.step1.entity.club.CommunityMember;
import io.namoosori.travelclub.step1.entity.club.TravelClub;
import io.namoosori.travelclub.step4.resources.Sql;
import io.namoosori.travelclub.step4.store.MemberStore;
import io.namoosori.travelclub.util.ConnectionUtil;
import io.namoosori.travelclub.util.JsonUtil;

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
        String result = null;
        String sql = Sql.M1c;
        try(Connection connection = ConnectionUtil.createConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getNickName());
            pstmt.setString(4, member.getPhoneNumber());
            pstmt.setString(5, member.getBirthDay());
            pstmt.setString(6, JsonUtil.toJson(member.getAddressList()));
            pstmt.setString(7, JsonUtil.toJson(member.getMembershipList()));

            pstmt.executeUpdate();
            result = member.getId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public CommunityMember retrieve(String targetemail) {
        CommunityMember result = null;
        ResultSet rs = null;
        String sql = Sql.M1r;

        try (Connection connection = ConnectionUtil.createConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1,targetemail);

            rs = pstmt.executeQuery();

            while (rs.next()){
                String email = rs.getString("email");
                String name = rs.getString("name");
                String nickName = rs.getString("nickName");
                String phoneNumber = rs.getString("phoneNumber");
                String birthDay = rs.getString("birthDay");
                String addressList = rs.getString("addressList");
                String membershipList = rs.getString("membershipList");

                result = new CommunityMember(email,name,nickName,phoneNumber,birthDay
                ,JsonUtil.fromJsonList(addressList, Address.class)
                        ,JsonUtil.fromJsonList(membershipList, ClubMembership.class));
            }

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public List<CommunityMember> retrieveByName(String targetname) {
        List<CommunityMember> results = new ArrayList<>();
        ResultSet rs = null;
        String sql = Sql.M1rn;

        try (Connection connection = ConnectionUtil.createConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1,targetname);

            rs = pstmt.executeQuery();

            while (rs.next()){
                String email = rs.getString("email");
                String name = rs.getString("name");
                String nickName = rs.getString("nickName");
                String phoneNumber = rs.getString("phoneNumber");
                String birthDay = rs.getString("birthDay");
                String addressList = rs.getString("addressList");
                String membershipList = rs.getString("membershipList");

                CommunityMember result = new CommunityMember(email,name,nickName,phoneNumber,birthDay
                        ,JsonUtil.fromJsonList(addressList, Address.class)
                        ,JsonUtil.fromJsonList(membershipList, ClubMembership.class));
                results.add(result);
            }

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return results;
    }

    @Override
    public void update(CommunityMember member) {
        String sql = Sql.M1u;
        try(Connection connection = ConnectionUtil.createConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getNickName());
            pstmt.setString(4, member.getPhoneNumber());
            pstmt.setString(5, member.getBirthDay());
            pstmt.setString(6, JsonUtil.toJson(member.getAddressList()));
            pstmt.setString(7, JsonUtil.toJson(member.getMembershipList()));

            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(String targetemail) {
        String sql = Sql.M1d;
        try (Connection connection = ConnectionUtil.createConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1,targetemail);

            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean exists(String email) {
        boolean result = false;
        ResultSet rs = null;
        String sql = "select * from communitymember where email=?";

        try(Connection connection = ConnectionUtil.createConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, email);

            rs = pstmt.executeQuery();
            if(rs.next()){
                String usid = rs.getString("email");
                result = Optional.ofNullable(usid).isPresent();
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
