package com.team.house.page.controller;

import com.team.house.entity.Users;
import com.team.house.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("usersController2")
@RequestMapping("/page/")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @RequestMapping("getUserByName")
    @ResponseBody
    public String getUserByName(String name){
        int i = usersService.getUsersByName(name);
        return "{\"result\":"+i+"}";
    }
    @RequestMapping("addUsers")
    public String addUsers(Users users, Model model){
        int i = usersService.addUser(users);
        if (i==0){
            return "regs";
        }else {
            model.addAttribute("name",users.getName());
            model.addAttribute("password",users.getPassword());
            return "login";
        }
    }
    @RequestMapping("loginUser")
    public String loginUser(String name, String password, Model model, HttpSession session){
        Users users = usersService.loginUser(name, password);
        if (users==null){
            model.addAttribute("look","用户名或密码错误");
            return "login";
        }else {
            session.setAttribute("loginInfo",users);
            session.setMaxInactiveInterval(300);
            return "guanli";
        }
    }

}
