package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.service.HouseService;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("houseController2")
@RequestMapping("/admin/")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @RequestMapping("passYes")
    public Map<String,Object> passYes(Page page){
        PageInfo<House> info = houseService.getHouseByispass(page, 1);
        Map<String,Object> map=new HashMap<>();
        map.put("total",info.getTotal());
        map.put("rows",info.getList());
        return map;
    }
    @RequestMapping("passNo")
    public Map<String,Object> passNo(Page page){
        PageInfo<House> info = houseService.getHouseByispass(page, 0);
        Map<String,Object> map=new HashMap<>();
        map.put("total",info.getTotal());
        map.put("rows",info.getList());
        return map;
    }
    @RequestMapping("uppassYes")
    public String uppassYes(String id){
        int temp = houseService.updateispass(id, 1);
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("uppassNo")
    public String uppassNo(String id){
        int temp = houseService.updateispass(id, 0);
        return "{\"result\":"+temp+"}";
    }
}
