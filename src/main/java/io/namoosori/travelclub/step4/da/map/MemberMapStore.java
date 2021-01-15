package io.namoosori.travelclub.step4.da.map;

import io.namoosori.travelclub.step1.entity.club.CommunityMember;
import io.namoosori.travelclub.step4.da.map.io.MemoryMap;
import io.namoosori.travelclub.step4.store.MemberStore;
import io.namoosori.travelclub.step4.util.MemberDuplicationException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemberMapStore  implements MemberStore {

    private Map<String, CommunityMember> memberMap;

    public MemberMapStore(){
        this.memberMap = MemoryMap.getInstance().getMemberMap();
    }
    @Override
    public String create(CommunityMember member) {
        Optional.ofNullable(memberMap.get(member.getId()))
                .ifPresent(targetMember -> {
                    throw new MemberDuplicationException("Member already exists with email: " + member.getId());
                });

        memberMap.put(member.getId(), member);
        return member.getId();
    }

    @Override
    public CommunityMember retrieve(String memberId) {
        return memberMap.get(memberId);
    }

    @Override
    public List<CommunityMember> retrieveByName(String name) {
        return memberMap.values().stream()
                .filter(member -> member.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void update(CommunityMember member) {
        memberMap.put(member.getId(), member);
    }

    @Override
    public void delete(String memberId) {
        memberMap.remove(memberId);
    }

    @Override
    public boolean exists(String memberId) {
        return Optional.ofNullable(memberMap.get(memberId)).isPresent();
    }
}
