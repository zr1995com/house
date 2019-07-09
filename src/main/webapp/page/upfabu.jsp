<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
  <script src="../admin/js/jquery-1.8.3.js"></script>
  <script src="../admin/js/jquery.form.js"></script>
  <script>
    $(function () {
        $("#districtid").change(function () {
            aaa($(this).val());

        });
        //图片上传的异步提交
        $("#pfile").change(function () {
            $("#add_action").ajaxSubmit({
                url:"getPath",
                type:"post",
                success:function (data) {
                    $("#img1").attr("src","http://localhost:80/"+data)
                    $("#path").val(data)
                }
            })
        })
        //类型
        $.post("getType",null,function (data) {
            for (var i = 0; i <data.length ; i++) {
                var a=data[i];
                var b=$("<option value='"+a.id+"'>"+a.name+"</option>")
                $("#typeId").append(b)
            }
            $("#typeId").val(${house.typeId});
        },"json");
        //区域
        $.post("getDistrict",null,function (data) {
            for (var i = 0; i <data.length ; i++) {
                var a=data[i];
                var b=$("<option value='"+a.id+"'>"+a.name+"</option>")
                $("#districtid").append(b)
            }
            $("#districtid").val(${house.did});
            aaa(${house.did})
        },"json");
    });
    function aaa(a) {
        $.post("getStreets",{id:a},function (data) {
            $("#streetid>option:gt(0)").remove();
            for (var i = 0; i <data.length ; i++) {
                var b=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
                $("#streetid").append(b);
            }
            $("#streetid").val(${house.streetId});
        },"json")
    }

  </script>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新房屋信息发布</DT>
  <DD class=past>填写房屋信息</DD></DL>
<DIV class=box>
<FORM id=add_action method=post name=add.action 
action="updateHouse" enctype="multipart/form-data">
<DIV class=infos>
<TABLE class=field >
  <TBODY>
  <TR>
    <TD class=field>标　　题：</TD>
    <TD><input type="hidden" id="id" name="id" value="${house.id}">
      <INPUT id=add_action_title class=text type=text name=title value="${house.title}"> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD><SELECT class=text name=typeId id="typeId">
      <OPTION value=-1>请选择</OPTION>
    </SELECT></TD></TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage class=text type=text value="${house.floorage}"
name=floorage></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=add_action_price class=text type=text name=price value="${house.price}"> </TD></TR>
  <TR>
    <TD class=field>房产证日期：</TD>
    <TD><INPUT class=text type=date name=pubdate value="<fmt:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"></fmt:formatDate>"></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT class=text name=district_id id="districtid"><OPTION
        value=-1>请选择</OPTION>
    </SELECT> 街：<SELECT class=text
        name=streetId id="streetid"><OPTION  value=-1>请选择</OPTION>

    </SELECT> </TD></TR><!--
						<tr>
							<td class="field">坐  标：</td>
							<td><input type="text" class="text" name="point" />
							</td>
						</tr>
						--><!--  <tr>
							<td class="field">Y 坐  标：</td>
							<td><input type="text" class="text" name="point.y" /></td>
						</tr>-->
  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT id=add_action_contact class=text type=text name=contact value="${house.contact}"> </TD></TR>
  <tr>
    <td class="field">图片:</td>
    <td>
      <img src="http://localhost:80/${house.path}" height="80" width="100" id="img1">
      <input type="file" id="pfile" name="pfile">
      <input type="hidden" id="path" name="path" value="${house.path}">
    </td>
  </tr>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description>${house.description}</TEXTAREA></TD></TR></TBODY></TABLE>
<DIV class=buttons><INPUT  value=立即发布 type=submit>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
