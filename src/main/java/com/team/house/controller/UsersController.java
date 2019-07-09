package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.service.UsersService;
import com.team.house.utils.UserSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("getUser")
    @ResponseBody
    public Map<String,Object> getUser(UserSearch userSearch){
        PageInfo<Users> page = usersService.getUsersByPage(userSearch);
        Map<String,Object> map=new HashMap<>();
        map.put("total",page.getTotal());
        map.put("rows",page.getList());
        return map;
    }
}
