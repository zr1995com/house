package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.utils.HouseConnection;
import com.team.house.utils.Page;

public interface HouseService {
    public int addHouse(House house);

    public PageInfo<House> getHouseById(Integer id, Page page);

    public House getHouseById(String id);

    public int updateHouse(House house);

    public int updateisdel(String id,Integer isdel);

    public PageInfo<House> getHouseByispass(Page page,Integer ispass);

    public int updateispass(String id,Integer ispass);

    public PageInfo<House> getHouse(HouseConnection houseConnection);
}
