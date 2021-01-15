package io.namoosori.travelclub.step4;

import io.namoosori.travelclub.step1.entity.club.CommunityMember;
import io.namoosori.travelclub.step1.entity.club.TravelClub;
import io.namoosori.travelclub.step4.da.map.ClubStoreMapLycler;
import io.namoosori.travelclub.step4.da.map.io.MemoryMap;
import io.namoosori.travelclub.step4.logic.ServiceLogicLycler;
import io.namoosori.travelclub.step4.service.dto.ClubMembershipDto;
import io.namoosori.travelclub.step4.ui.menu.MainMenu;

public class StoryAssistant {

    private void start(){
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }

    public static void main(String[] args) {
        MemoryMap.getInstance().getMemberMap().put("test@test.co.kr", new CommunityMember("test@test.co.kr", "test", "123"));
        ClubStoreMapLycler.getInstance().requestClubStore().create(new TravelClub("test", "testestststestsetset"));
        ServiceLogicLycler.shareInstance().createClubService().addMembership(new ClubMembershipDto("00001", "test@test.co.kr"));

        StoryAssistant assistant = new StoryAssistant();
        assistant.start();
    }
}
