package io.namoosori.travelclub.step3.logic;

import io.namoosori.travelclub.step1.entity.AutoldEntity;
import io.namoosori.travelclub.step1.entity.club.ClubMembership;
import io.namoosori.travelclub.step1.entity.club.CommunityMember;
import io.namoosori.travelclub.step1.entity.club.RoleInClub;
import io.namoosori.travelclub.step1.entity.club.TravelClub;
import io.namoosori.travelclub.step3.logic.storage.MapStroage;
import io.namoosori.travelclub.step3.service.ClubService;
import io.namoosori.travelclub.step3.service.dto.ClubMembershipDto;
import io.namoosori.travelclub.step3.service.dto.TravelClubDto;
import io.namoosori.travelclub.step3.util.ClubDuplicationException;
import io.namoosori.travelclub.step3.util.MemberDuplicationException;
import io.namoosori.travelclub.step3.util.NoSuchClubException;
import io.namoosori.travelclub.step3.util.NoSuchMemberException;
import io.namoosori.travelclub.util.StringUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClubServiceLogic implements ClubService {

    private Map<String, CommunityMember> memberMap;
    private Map<String, TravelClub> clubMap;
    private Map<String,Integer> autoIdMap;

    public ClubServiceLogic() {
        this.memberMap = MapStroage.getInstance().getMemberMap();
        this.clubMap = MapStroage.getInstance().getClubMap();
        this.autoIdMap = MapStroage.getInstance().getAutoIdMap();
    }

    @Override
    public void registerClub(TravelClubDto clubDto) {
        Optional.ofNullable(retrieveByName(clubDto.getName()))
                .ifPresent(club -> {
                    throw new ClubDuplicationException("Club already exists with name:" + club.getName());
                });

        TravelClub travelClub = clubDto.toTravelClub();

        if (travelClub instanceof AutoldEntity){
            String className = TravelClub.class.getSimpleName();
            if(autoIdMap.get(className) == null){
                autoIdMap.put(className, 1);
            }

            int keySequence = autoIdMap.get(className);
            String autoId = String.format("%05d", keySequence);
            travelClub.setAutoId(autoId);
            autoIdMap.put(className, ++keySequence);
        }

        clubMap.put(travelClub.getBoardId(), travelClub);

        clubDto.setUsid(travelClub.getUsid());
    }

    @Override
    public TravelClubDto findClub(String clubId) {

        return Optional.ofNullable(clubMap.get(clubId))
                .map(foundClub -> new TravelClubDto(foundClub))
                .orElseThrow(() -> new NoSuchClubException("No such club with id: " + clubId));
    }

    @Override
    public TravelClubDto findClubByName(String name) {
        return Optional.ofNullable(retrieveByName(name))
                .map(foundClub -> new TravelClubDto(foundClub))
                .orElseThrow(() -> new NoSuchClubException("No such club with name: " + name));
    }


    @Override
    public void modify(TravelClubDto clubDto) {
        String clubId = clubDto.getUsid();

        TravelClub travelClub = Optional.ofNullable(clubMap.get(clubId))
                .orElseThrow(() -> new NoSuchClubException("No such club with id: " + clubDto.getUsid()));
        if(StringUtil.isEmpty(clubDto.getName())){
            clubDto.setName(travelClub.getName());
        }
        if(StringUtil.isEmpty(clubDto.getIntro())){
            clubDto.setIntro(travelClub.getIntro());
        }

        clubMap.put(clubId, clubDto.toTravelClub());
    }

    @Override
    public void remove(String clubId) {
        Optional.ofNullable(clubMap.get(clubId))
                .orElseThrow(() -> new NoSuchClubException("No such club with id: " + clubId));
        clubMap.remove(clubId);
    }

    @Override
    public void addMembership(ClubMembershipDto membershipDto) {
        String memberId = membershipDto.getMemberEmail();

        CommunityMember foundMember = Optional.ofNullable(memberMap.get(memberId))
                .orElseThrow(() -> new NoSuchClubException("No such member with email: " + memberId));

        String clubId = membershipDto.getClubId();
        TravelClub foundClub = clubMap.get(clubId);
        if(foundClub.getMembershipList().stream().anyMatch(membership -> memberId.equals(membership.getMemberEmail())))
            throw new MemberDuplicationException("Member already exists in the club -->" + memberId);


        ClubMembership clubMembership = membershipDto.toMembership();
        foundClub.getMembershipList().add(clubMembership);
        clubMap.put(clubId,foundClub);
        foundMember.getMembershipList().add(clubMembership);
        memberMap.put(memberId, foundMember);
    }

    @Override
    public ClubMembershipDto findMembershipIn(String clubId, String memberId) {
        TravelClub foundClub = clubMap.get(clubId);

        ClubMembership membership = getMembershipOfClub(foundClub, memberId);

        return new ClubMembershipDto(membership);
    }

    @Override
    public List<ClubMembershipDto> findAllMembershipsIn(String clubId) {
        return clubMap.get(clubId).getMembershipList().stream()
                .map(membership -> new ClubMembershipDto(membership))
                .collect(Collectors.toList());
    }

    @Override
    public void modifyMembership(String clubId, ClubMembershipDto membershipDto) {
        String targetEmail = membershipDto.getMemberEmail();
        RoleInClub newRole = membershipDto.getRole();

        TravelClub targetClub = clubMap.get(clubId);
        ClubMembership clubMembership = getMembershipOfClub(targetClub,targetEmail);
        clubMembership.setRole(newRole);

        memberMap.get(targetEmail).getMembershipList().stream()
                .filter(clubMembership1 -> clubMembership1.getClubId().equals(clubId))
                .forEach(clubMembership1 -> clubMembership1.setRole(newRole));
    }

    @Override
    public void removeMembership(String clubId, String memberId) {
        TravelClub foundClub = clubMap.get(clubId);
        CommunityMember foundMember = memberMap.get(memberId);

        ClubMembership clubMembership = getMembershipOfClub(foundClub, memberId);

        foundClub.getMembershipList().remove(clubMembership);
        foundMember.getMembershipList().remove(clubMembership);

        clubMap.put(clubId, foundClub);
        memberMap.put(foundMember.getId(), foundMember);

    }


    private TravelClub retrieveByName(String name) {
        Collection<TravelClub> clubs = clubMap.values();
        if(clubs.isEmpty())
            return null;

        TravelClub foundClub = null;
        for (TravelClub club : clubs) {
            if (club.getName().equals(name)) {
                foundClub = club;
                break;
            }
        }
        return foundClub;
    }

    private ClubMembership getMembershipOfClub(TravelClub club, String memberId){
        for (ClubMembership membership : club.getMembershipList()) {
            if (memberId.equals(membership.getMemberEmail())) {
                return membership;
            }
        }
        throw new NoSuchMemberException(String.format("No such member[email:%s] in club[name:%s]", memberId, club.getName()));
    }
}
