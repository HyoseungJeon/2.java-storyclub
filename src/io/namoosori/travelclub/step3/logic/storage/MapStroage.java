package io.namoosori.travelclub.step3.logic.storage;

import io.namoosori.travelclub.step1.entity.board.Posting;
import io.namoosori.travelclub.step1.entity.board.SocialBoard;
import io.namoosori.travelclub.step1.entity.club.CommunityMember;
import io.namoosori.travelclub.step1.entity.club.TravelClub;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStroage {

    private static MapStroage singletonMap;

    private Map<String, CommunityMember> memberMap;
    private Map<String, TravelClub> clubMap;
    private Map<String, SocialBoard> socialBoardMap;
    private Map<String, Posting> postingMap;
    private Map<String, Integer> autoIdMap;

    private MapStroage(){
        this.memberMap = new LinkedHashMap<>();
        this.clubMap = new LinkedHashMap<>();
        this.socialBoardMap = new LinkedHashMap<>();
        this.postingMap = new LinkedHashMap<>();
        this.autoIdMap = new LinkedHashMap<>();
    }

    public static MapStroage getInstance(){
        if(singletonMap == null){
            singletonMap = new MapStroage();
        }

        return singletonMap;
    }

    public Map<String, CommunityMember> getMemberMap() {
        return memberMap;
    }

    public Map<String, TravelClub> getClubMap() {
        return clubMap;
    }

    public Map<String, SocialBoard> getSocialBoardMap() {
        return socialBoardMap;
    }

    public Map<String, Posting> getPostingMap() {
        return postingMap;
    }

    public Map<String, Integer> getAutoIdMap() {
        return autoIdMap;
    }
}
