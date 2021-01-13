package io.namoosori.travelclub.step3.service.dto;

import io.namoosori.travelclub.step1.entity.club.Address;
import io.namoosori.travelclub.step1.entity.club.ClubMembership;
import io.namoosori.travelclub.step1.entity.club.CommunityMember;
import io.namoosori.travelclub.step1.util.InvalidEmailException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MemberDto {
    private String email;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String birthDay;

    private List<Address> addressList;
    private List<ClubMembershipDto> membershipList;

    private MemberDto(){
        this.addressList = new ArrayList<Address>();
        this.membershipList = new ArrayList<ClubMembershipDto>();
    }

    public MemberDto(String email, String name, String phoneNumber) {
        this();
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public MemberDto(CommunityMember member){
        this(member.getEmail(), member.getName(), member.getPhoneNumber());
        this.nickName = member.getNickName();
        this.birthDay = member.getBirthDay();
        this.addressList = member.getAddressList();

        for(ClubMembership membership : member.getMembershipList()){
            membershipList.add(new ClubMembershipDto(membership));
        }
        //membershipList = member.getMembershipList().stream().map(member -> member = new ClubMembershipDto(member));
    }

    public CommunityMember toMember() throws InvalidEmailException{

        CommunityMember member = new CommunityMember(email, name, phoneNumber);
        member.setNickName(nickName);
        member.setBirthDay(birthDay);

        for(Address address : addressList){
            member.getAddressList().add(address);
        }

        for(ClubMembershipDto clubMembershipDto : membershipList){
            member.getMembershipList().add(clubMembershipDto.toMembership());
        }
        return member;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MemberDto{");
        sb.append("name='").append(email).append('\'');
        sb.append(", email='").append(name).append('\'');
        sb.append(", nickName='").append(nickName).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", birthDay='").append(birthDay).append('\'');
        if(addressList != null){
            int i = 1;
            for(Address address : addressList){
                sb.append(", Address[" + i + "]").append(address.toString());
            }
        }

        int i = 0;
        for(ClubMembershipDto membershipDto : membershipList){
            sb.append(" [" + i + "] Club member ").append(membershipDto.toString()).append("\n");
            i++;
        }
        sb.append('}');
        return sb.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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

    public List<ClubMembershipDto> getMembershipList() {
        return membershipList;
    }

    public void setMembershipList(List<ClubMembershipDto> membershipList) {
        this.membershipList = membershipList;
    }
}
