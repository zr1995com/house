package com.team.house.page.controller;

import com.team.house.entity.District;
import com.team.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("districtController2")
@RequestMapping("/page/")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    @RequestMapping("getDistrict")
    @ResponseBody
    public List<District> getDistrict(){
        List<District> list = districtService.getDistrictList();
        return list;
    }

}
