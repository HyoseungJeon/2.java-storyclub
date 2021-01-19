package io.namoosori.travelclub.step1.entity.club;

import io.namoosori.travelclub.step1.entity.Entity;
import io.namoosori.travelclub.step1.util.InvalidEmailException;
import io.namoosori.travelclub.step4.da.dto.CommunityMemberDto;
import io.namoosori.travelclub.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CommunityMember implements Entity {
    private String email;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String birthDay;

    private List<Address> addressList;
    private List<ClubMembership> membershipList;

    /*create table CommunityMember(
        email varchar(30) not null primary key,
        name varchar(20),
        nickName varchar(20),
        phoneNumber varchar(20),
        birthDay varchar(10),

        addressList text
    );*/

    public String addressListToJson(){
        return JsonUtil.toJson(addressList);
    }

    public CommunityMember(){
        addressList = new ArrayList<>();
        membershipList = new ArrayList<>();
    }

    public CommunityMember(String email, String name, String phoneNumber) throws InvalidEmailException{
        this();
        setEmail(email);
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public CommunityMemberDto toMemberDto(){
        return new CommunityMemberDto(
                email,name,nickName,phoneNumber,birthDay,
                JsonUtil.toJson(addressList),
                JsonUtil.toJson(membershipList)
        );
    }

    public static CommunityMember getSample(){

        CommunityMember member = null;
        try{
            member = new CommunityMember("mymy@nextree.co.kr", "Minsoo Lee", "010-3321-1001");
            member.setBirthDay("2001.09.23");
            member.getAddressList().add(Address.getHomeAddressSample());
        }catch (InvalidEmailException e){
            System.out.println(e.getMessage());
        }
        return member;
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

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<ClubMembership> getMembershipList() {
        return membershipList;
    }

    public void setMembershipList(List<ClubMembership> membershipList) {
        this.membershipList = membershipList;
    }
}
