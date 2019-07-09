package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.Street;
import com.team.house.entity.StreetExample;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.StreetService;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public PageInfo<Street> getStreetByPage(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        List<Street> list = streetMapper.selectByExample(null);
        for (int i = 0; i <list.size() ; i++) {
            Street street = list.get(i);
            Integer districtId = street.getDistrictId();
            District district = districtMapper.selectByPrimaryKey(districtId);
            street.setDistrict(district);
        }
        PageInfo<Street> info=new PageInfo<>(list);
        return info;
    }

    @Override
    public int addStreet(Street street) {
        return streetMapper.insertSelective(street);
    }

    @Override
    public int upStreet(Street street) {
        return streetMapper.updateByPrimaryKeySelective(street);
    }

    @Override
    public int deleteStreet(Integer[] id) {
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        List<Integer> list = Arrays.asList(id);
        criteria.andIdIn(list);
        return streetMapper.deleteByExample(example);
    }

    @Override
    public PageInfo<Street> getStreets(Page page, Integer id) {
        PageHelper.startPage(page.getPage(),page.getPage());
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(id);
        List<Street> list = streetMapper.selectByExample(streetExample);
        PageInfo<Street> info=new PageInfo<>(list);
        return info;
    }

    @Override
    public List<Street> getStreetByDistrictId(Integer id) {
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(id);
        return streetMapper.selectByExample(example);
    }
}
