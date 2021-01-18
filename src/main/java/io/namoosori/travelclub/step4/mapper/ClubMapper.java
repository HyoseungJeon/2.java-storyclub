package io.namoosori.travelclub.step4.mapper;

import io.namoosori.travelclub.step1.entity.club.TravelClub;
import io.namoosori.travelclub.step4.da.dto.TravelClubDto;

public interface ClubMapper {
    public String create(TravelClubDto club);
    public TravelClubDto retrieve(String clubId);
    public TravelClubDto retrieveByName(String name);
    public void update(TravelClubDto club);
    public void delete(String clubId);

    public boolean exists(String clubId);
}
