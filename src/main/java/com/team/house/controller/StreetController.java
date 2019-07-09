package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.service.StreetService;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class StreetController {
    @Autowired
    private StreetService streetService;
    @RequestMapping("getStreet")
    @ResponseBody
    public Map<String,Object> getStreet(Page page){
        PageInfo<Street> info = streetService.getStreetByPage(page);
        Map<String,Object> map=new HashMap<>();
        map.put("total",info.getTotal());
        map.put("rows",info.getList());
        return map;
    }
    @RequestMapping("addStreet")
    @ResponseBody
    public String addStreet(Street street){
        int i = streetService.addStreet(street);
        return "{\"result\":"+i+"}";

    }
    @RequestMapping("upStreet")
    @ResponseBody
    public String upStreet(Street street){
        int i = streetService.upStreet(street);
        return "{\"result\":"+i+"}";

    }
    @RequestMapping("deleteStreet")
    @ResponseBody
    public String deleteStreet(Integer[] ids){
        int i = streetService.deleteStreet(ids);
        return "{\"result\":"+i+"}";

    }
    @RequestMapping("getStreets")
    @ResponseBody
    public  Map<String,Object> getStreets(Integer id,Page page){
        PageInfo<Street> info = streetService.getStreets(page, id);
        Map<String,Object> map=new HashMap<>();
        map.put("total",info.getTotal());
        map.put("rows",info.getList());
        return map;

    }
}
