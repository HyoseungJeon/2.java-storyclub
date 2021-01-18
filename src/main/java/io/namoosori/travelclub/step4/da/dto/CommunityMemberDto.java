package io.namoosori.travelclub.step4.da.dto;

import io.namoosori.travelclub.step1.entity.Entity;
import io.namoosori.travelclub.step1.entity.club.Address;
import io.namoosori.travelclub.step1.entity.club.ClubMembership;
import io.namoosori.travelclub.step1.entity.club.CommunityMember;
import io.namoosori.travelclub.step1.util.InvalidEmailException;
import io.namoosori.travelclub.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class CommunityMemberDto implements Entity {
    private String email;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String birthDay;

    private String addressList;
    private String membershipList;

    /*create table CommunityMember(
        email varchar(30),
        name varchar(20),
        nickName varchar(20),
        phoneNumber varchar(20),
        birthDay varchar(10),

        addressList text,
        membershipList text
    );*/

    public CommunityMemberDto(){
    }

    public CommunityMemberDto(String email, String name, String phoneNumber) throws InvalidEmailException {
        this();
        setEmail(email);
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public CommunityMemberDto(String email, String name, String nickName, String phoneNumber, String birthDay) {
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
    }

    public CommunityMember toCommunityMember(CommunityMemberDto communityMemberDto){
        CommunityMember communityMember = new CommunityMember(
          communityMemberDto.getEmail(),
          communityMemberDto.getName(),
          communityMemberDto.getNickName(),
          communityMemberDto.getPhoneNumber(),
          communityMemberDto.getBirthDay()
        );
        communityMember.setAddressList(JsonUtil.fromJsonList(communityMemberDto.getAddressList(),Address.class));
        communityMember.setMembershipList(JsonUtil.fromJsonList(communityMemberDto.getMembershipList(),ClubMembership.class));
        return communityMember;
    }

    public CommunityMemberDto toCommunityMemberDto(CommunityMember communityMember){
        CommunityMemberDto communityMemberDto = new CommunityMemberDto(
                communityMember.getEmail(),
                communityMember.getName(),
                communityMember.getNickName(),
                communityMember.getPhoneNumber(),
                communityMember.getBirthDay()
        );
        communityMemberDto.setAddressList(JsonUtil.toJson(communityMember.getAddressList()));
        communityMemberDto.setMembershipList(JsonUtil.toJson(communityMember.getMembershipList()));

        return communityMemberDto;
    }

    private boolean isValidEmailAddress(String email){
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    @Override
    public String getId() {
        return email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidEmailException{
        if(!this.isValidEmailAddress(email)){
            throw new InvalidEmailException("Email is not valid. --> " + email);
        }

        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddressList() {
        return addressList;
    }

    public void setAddressList(String addressList) {
        this.addressList = addressList;
    }

    public String getMembershipList() {
        return membershipList;
    }

    public void setMembershipList(String membershipList) {
        this.membershipList = membershipList;
    }
}
