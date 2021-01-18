package io.namoosori.travelclub.step4.resources;

public class Sql {
    //_1_ form
    //First index -> C : club, CS : ClubStore, M : Member, B : Board, P : Posting
    //second index -> c : create, r : retrieve, r_ : retrieveBy%, u : update, d : delete
    //====================================================================================

    public static final String C1c = "insert travelclub set name=?,intro=?, foundationDay=?, boardId=?, membershipList=?";
    public static final String C1r = "select * from travelclub where usid=?";
    public static final String C1rn = "select * from travelclub where name=?";
    public static final String C1u = "update travelclub set usid=?, name=?,intro=?, foundationDay=?, boardId=?, membershipList=? where usid=?";
    public static final String C1d = "delete from travelclub where usid=?";
    public static final String M1c = "insert communitymember set email=?, name=?,nickName=?, phoneNumber=?, birthDay=?," +
            "addressList=?,membershipList=?";
    public static final String M1r = "select * from communitymember where email=?";
    public static final String M1rn = "select * from communitymember where email=?";
    public static final String M1u = "update communitymember set email=?, name=?,nickName=?, phoneNumber=?, birthDay=?," +
            "addressList=?,membershipList=?";
    public static final String M1d = "delete from communitymember where email=?";
}
