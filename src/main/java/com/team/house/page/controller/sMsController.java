package com.team.house.page.controller;

import com.team.house.sms.SmsUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/page/")
public class sMsController {
    @RequestMapping("getCode")
    public String getCode(String sendPhone, HttpSession session){
        String s=(int) (Math.random()*10000)+"";
        String s1="验证码是:"+s;
        int i = SmsUtil.sendMsg(sendPhone, s1);
        session.setAttribute("saveCode",s);
        session.setMaxInactiveInterval(120);
        return "{\"result\":+i+}";
    }

}
