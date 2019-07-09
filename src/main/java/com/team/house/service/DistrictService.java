package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.utils.Page;

import java.util.List;

public interface DistrictService {
    public PageInfo<District> getDistrictByPage(Page page);

    public boolean addDistrict(District district);

    public boolean updateBistrict(District district);

    public boolean deleteDistrict(Integer[] id);

    public List<District> getDistrictList();

    public int delDistrict(Integer id);
}
