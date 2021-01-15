package io.namoosori.travelclub.step1;

import io.namoosori.travelclub.step1.entity.board.Posting;
import io.namoosori.travelclub.step1.entity.board.SocialBoard;
import io.namoosori.travelclub.step1.entity.club.ClubMembership;
import io.namoosori.travelclub.step1.entity.club.CommunityMember;
import io.namoosori.travelclub.step1.entity.club.TravelClub;
import io.namoosori.travelclub.util.Narrator;
import io.namoosori.travelclub.util.TalkingAt;

import java.util.List;

public class StoryAssistant {

    private Narrator narrator;

    public StoryAssistant(){

        this.narrator = new Narrator(this, TalkingAt.Left);
    }

    private void showBoardDemo(){
        TravelClub club = TravelClub.getSample(true);
        SocialBoard board = SocialBoard.getSample(club);
        List<Posting> postings = Posting.getSamples(board);

        narrator.say("> board: " + board.toString());
        narrator.say("> posting: " + postings.toString());
    }

    private void showMemberDemo(){
        TravelClub club = TravelClub.getSample(true);
        CommunityMember member = CommunityMember.getSample();
        ClubMembership membership = new ClubMembership(club, member);

        narrator.sayln("> club: " + club.toString());
        narrator.sayln("> member: " + member.toString());
        narrator.sayln("> membership: " + membership.toString());
    }

    public static void main(String[] args){
        StoryAssistant assistant = new StoryAssistant();
        assistant.showMemberDemo();
        assistant.showBoardDemo();
    }
}
