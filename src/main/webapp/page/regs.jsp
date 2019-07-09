﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0049)http://localhost:8080/HouseRent/page/register.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 用户注册</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新用户注册</DT>
  <DD class=past>填写个人信息</DD></DL>
<DIV class=box>
<FORM action="addUsers">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>用 户 名：</TD>
    <TD><INPUT class=text type=text name=name id="uname"> <span id="sp1"></span>
    </TD></TR>
  <TR>
    <TD class=field>密　　码：</TD>
    <TD><INPUT class=text type=password name=password id="password"></TD></TR>
  <TR>
    <TD class=field>确认密码：</TD>
    <TD><INPUT class=text type=password name=repassword id="repassword"> <span id="sp2"></span></TD></TR>
  <TR>
    <TD class=field>电　　话：</TD>
    <TD><INPUT class=text type=text name=telephone> </TD></TR>
  </TBODY></TABLE>
<DIV class=buttons>
<INPUT value=立即注册 type=submit>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>
<script src="../admin/js/jquery-1.8.3.js" type="application/javascript"></script>
<script>
    $(function () {
       $("#uname").blur(function () {
           var username=$("#uname").val();
           $.post("getUserByName",{name:username},function (data) {
               if (data.result==0){
                   $("#sp1").html("用户名可注册").css("color","green");
               }else {
                   $("#sp1").html("用户名已存在").css("color","red");
               }
           },"json")
       });
       $("#repassword").blur(function () {
         var repassword=  $("#repassword").val();
         var password=$("#password").val();
         if (repassword==password){
             $("#sp2").html("两次密码一致").css("color","green");
         }else {
             $("#sp2").html("两次密码输入不一致").css("color","red");
         }
       });
    });
</script>
</HTML>
