package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.service.DistrictService;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    @RequestMapping("/getdistrict")
    @ResponseBody
    public Map<String,Object> getdistrict(Page page){
        PageInfo<District> info = districtService.getDistrictByPage(page);
        Map<String,Object> map=new HashMap<>();
        map.put("total",info.getTotal());
        map.put("rows",info.getList());
        return map;
    }
    @RequestMapping("/adddistrict")
    @ResponseBody
    public String adddistrict(District district){
        if (districtService.addDistrict(district)){
            return "SCUUESS";
        }else {
            return "FALL";
        }

    }
    @RequestMapping("/updatedistrict")
    @ResponseBody
    public String updatedistrict(District district){
        if (districtService.updateBistrict(district)){
            return "SCUUESS";
        }else {
            return "FALL";
        }

    }
    @RequestMapping("/deletedistrict")
    @ResponseBody
    public boolean deletedistrict(Integer[] ids){
        if (districtService.deleteDistrict(ids)){
            return true;
        }else {
            return false;
        }

    }
    @RequestMapping("/aaa")
    @ResponseBody
    public List<District> aaa(){
        return districtService.getDistrictList();
    }
    @RequestMapping("delDistrict")
    @ResponseBody
    public String delDistrict(Integer id)  {//接收编号，名称
        //调用业务
        try {
            districtService.delDistrict(id);
            return "{\"result\":1}";
        }catch (Exception e){
            //记录错误信息
            System.out.println("出错啦");
        }
        return "{\"result\":0}";
    }
}
