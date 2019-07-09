package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import com.team.house.entity.StreetExample;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.DistrictService;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public PageInfo<District> getDistrictByPage(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        List<District> list = districtMapper.selectByExample(null);
        PageInfo<District> info=new PageInfo<>(list);
        return info;
    }

    @Override
    public boolean addDistrict(District district) {
        return districtMapper.insertSelective(district)>0?true:false;
    }

    @Override
    public boolean updateBistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district)>0?true:false;
    }

    @Override
    @Transactional
    public boolean deleteDistrict(Integer[] id) {
        DistrictExample example=new DistrictExample();
        DistrictExample.Criteria criteria = example.createCriteria();
        List<Integer> list = Arrays.asList(id);
        criteria.andIdIn(list);
        StreetExample example1=new StreetExample();
        StreetExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andDistrictIdIn(list);
        int i = streetMapper.deleteByExample(example1);
        return districtMapper.deleteByExample(example)>0?true:false;
    }

    @Override
    public List<District> getDistrictList() {
        return districtMapper.selectByExample(null);
    }

    @Override
    public int delDistrict(Integer id) {
        return districtMapper.deleteByPrimaryKey(id);
    }

}
