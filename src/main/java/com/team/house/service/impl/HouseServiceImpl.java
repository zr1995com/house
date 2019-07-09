package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.mapper.HouseMapper;
import com.team.house.service.HouseService;
import com.team.house.utils.HouseConnection;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public int addHouse(House house) {
        return  houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getHouseById(Integer id, Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        List<House> list = houseMapper.getHouseById(id);
        return new PageInfo<House>(list);
    }

    @Override
    public House getHouseById(String id) {
        return houseMapper.getHouseByIda(id);
    }

    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int updateisdel(String id, Integer isdel) {
        House house=new House();
        house.setId(id);
        house.setIsdel(isdel);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getHouseByispass(Page page, Integer ispass) {
        PageHelper.startPage(page.getPage(),page.getRows());
        List<House> list = houseMapper.getHouseByispass(ispass);
        return new PageInfo<House>(list);
    }

    @Override
    public int updateispass(String id, Integer ispass) {
        House house=new House();
        house.setId(id);
        house.setIspass(ispass);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getHouse(HouseConnection houseConnection) {
        PageHelper.startPage(houseConnection.getPage(),houseConnection.getRows());
        List<House> list = houseMapper.getHouse(houseConnection);
        PageInfo<House> info=new PageInfo<>(list);
        return info;
    }
}
