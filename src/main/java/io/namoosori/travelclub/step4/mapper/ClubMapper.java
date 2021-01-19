package io.namoosori.travelclub.step4.mapper;

import io.namoosori.travelclub.step1.entity.club.TravelClub;
import io.namoosori.travelclub.step4.da.dto.TravelClubDto;
import org.apache.ibatis.annotations.Param;

public interface ClubMapper {
    public String create(@Param("travelClub")TravelClub club);
    public TravelClub retrieve(String clubId);
    public TravelClub retrieveByName(String name);
    public void update(@Param("travelClub")TravelClub club);
    public void delete(String clubId);

    public boolean exists(String clubId);
}
