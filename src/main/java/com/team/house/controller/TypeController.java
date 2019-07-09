package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @RequestMapping("getType")
    @ResponseBody
    public Map<String,Object> getType(Page page){
        PageInfo<Type> info = typeService.getTypeByPage(page);
        Map<String,Object> map=new HashMap<>();
        map.put("total",info.getTotal());
        map.put("rows",info.getList());
        return map;
    }
    @RequestMapping("addType")
    @ResponseBody
    public String addType(Type type){
        int i = typeService.addType(type);
        return "{\"result\":"+i+"}";
    }
    @RequestMapping("updateType")
    @ResponseBody
    public String updateType(Type type){
        int i = typeService.updateType(type);
        return "{\"result\":"+i+"}";
    }
    @RequestMapping("deleteType")
    @ResponseBody
    public String deleteType(Integer[] ids){
        int i = typeService.deleteType(ids);
        return "{\"result\":"+i+"}";
    }
}
