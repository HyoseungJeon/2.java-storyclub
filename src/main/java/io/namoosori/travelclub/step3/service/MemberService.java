package io.namoosori.travelclub.step3.service;

import io.namoosori.travelclub.step1.util.InvalidEmailException;
import io.namoosori.travelclub.step3.service.dto.MemberDto;

import java.util.List;

public interface MemberService {
    public void register(MemberDto memberDto) throws InvalidEmailException;
    public MemberDto find(String memberId);
    public List<MemberDto> findByName(String memberName);
    public void modify(MemberDto memberDto) throws InvalidEmailException;
    public void remove(String memberId);
}
