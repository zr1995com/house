package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.utils.Page;

import java.util.List;

public interface StreetService {
    public PageInfo<Street> getStreetByPage(Page page);

    public int addStreet(Street street);

    public int upStreet(Street street);

    public int deleteStreet(Integer[] id);

    public PageInfo<Street> getStreets(Page page,Integer id);

    public List<Street> getStreetByDistrictId(Integer id);
}
