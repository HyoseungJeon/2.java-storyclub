package io.namoosori.travelclub.step4.service.dto;

import io.namoosori.travelclub.step1.entity.club.Address;
import io.namoosori.travelclub.step1.entity.club.ClubMembership;
import io.namoosori.travelclub.step1.entity.club.CommunityMember;
import io.namoosori.travelclub.step1.util.InvalidEmailException;
import io.namoosori.travelclub.step3.service.dto.ClubMembershipDto;

import java.util.ArrayList;
import java.util.List;

public class MemberDto {
    private String email;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String birthDay;

    private List<Address> addresses;
    private List<io.namoosori.travelclub.step3.service.dto.ClubMembershipDto> membershipList;

    private MemberDto() {
        //
        this.addresses = new ArrayList<Address>();
        this.membershipList = new ArrayList<io.namoosori.travelclub.step3.service.dto.ClubMembershipDto>();
    }

    public MemberDto(String email, String name, String phoneNumber) {
        //
        this();
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public MemberDto(CommunityMember member) {
        //
        this(member.getEmail(), member.getName(), member.getPhoneNumber());
        this.nickName = member.getNickName();
        this.birthDay = member.getBirthDay();
        this.addresses = member.getAddressList();

        for (ClubMembership membership : member.getMembershipList()) {
            //
            membershipList.add(new io.namoosori.travelclub.step3.service.dto.ClubMembershipDto(membership));
        }
    }

    public CommunityMember toMember() throws InvalidEmailException {
        //
        CommunityMember member = new CommunityMember(email, name, phoneNumber);
        member.setNickName(nickName);
        member.setBirthDay(birthDay);

        for (Address address : addresses) {
            member.getAddressList().add(address);
        }

        for (io.namoosori.travelclub.step3.service.dto.ClubMembershipDto membershipDto : membershipList) {
            member.getMembershipList().add(membershipDto.toMembership());
        }
        return member;
    }

    @Override
    public String toString() {
        //
        StringBuilder builder = new StringBuilder();

        builder.append("Name:").append(name);
        builder.append(", email:").append(email);
        builder.append(", nickname:").append(nickName);
        builder.append(", phone number:").append(phoneNumber);
        builder.append(", birthDay:").append(birthDay);

        if (addresses != null) {
            int i = 1;
            for(Address address : addresses) {
                builder.append(", Address[" + i + "]").append(address.toString());
            }
        }

        int i=0;
        for(io.namoosori.travelclub.step3.service.dto.ClubMembershipDto membership : membershipList) {
            builder.append(" ["+ i +"] Club member ").append(membership.toString()).append("\n");
            i++;
        }

        return builder.toString();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<ClubMembershipDto> getMembershipList() {
        return membershipList;
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

}
