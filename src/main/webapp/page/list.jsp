<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<script src="../admin/js/jquery-1.8.3.js"></script>
<script>
function page(id) {
    $("#page").val(id);
    $("#sform").submit();
}
$(function () {
    $.post("getType",null,function (data) {
        for (var i = 0; i <data.length ; i++) {
            var a=data[i];
            var b=$("<option value='"+a.id+"'>"+a.name+"</option>")
            $("#typeId").append(b)
        }
        $("#typeId").val(${connection.typeId});
    },"json");
    //区域
    $("#districtId").change(function () {
        var b=$("#districtId").val();
            aaa($(this).val());
    });
    $.post("getDistrict",null,function (data) {
        for (var i = 0; i <data.length ; i++) {
            var a=data[i];
            var b=$("<option value='"+a.id+"'>"+a.name+"</option>")
            $("#districtId").append(b)
        }
        $("#districtId").val(${connection.districtId});
        aaa(${connection.districtId})
    },"json");
});
function aaa(a) {
    $.post("getStreets",{id:a},function (data) {
        $("#streetId>option:gt(0)").remove();
        for (var i = 0; i <data.length ; i++) {
            var b=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
            $("#streetId").append(b);
        }
        $("#streetId").val(${connection.streetId});
    },"json")
}
</script>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id=sform method=post action="getHouses">
    <input type="hidden" id="page" name="page" value="1">
    标题: <input type="text" id="title" name="title" value="${connection.title}">
    类型: <select name="typeId" id="typeId">
    <option   value="-1">不限</option>
  </select>
    区域: <select name="districtId" id="districtId">
    <option  value="-1">不限</option>
  </select>
    街道: <select name="streetId" id="streetId">
    <option  value="-1">不限</option>
  </select>
    价格: <input type="text" id="minPrice" name="minPrice" value="${connection.minPrice}">
    - <input type="text" id="maxPrice" name="maxPrice" value="${connection.maxPrice}">
    <input type="submit" value="查询">
  </FORM></DL></DIV>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
    <c:forEach items="${info.list}" var="h">
      <tr>
      <TD class=house-thumb><span><A href="house?id=${h.id}" target="_blank"><img src="http://localhost:80/${h.path}" width="100" height="75" alt=""></a></span></TD>
      <TD>
        <DL>
          <DT><A href="house?id=${h.id}" target="_blank">${h.title}</A></DT>
          <DD>${h.dname}==${h.sname},${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
      <TD class=house-type>${h.tname}</TD>
      <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD>
      </tr>
    </c:forEach>
  <TR>无租房信息</TR></TBODY></TABLE>
<DIV class=pager>
<UL>
  <LI class=current><A href="javascript:page(1)">首页</A></LI>
  <LI><A href="javascript:page(${info.pageNum-1})">上一页</A></LI>
  <LI><A href="javascript:page(${info.pageNum+1})">下一页</A></LI>
  <LI><A href="javascript:page(${info.pages})">末页</A></LI></UL><SPAN
class=total>${info.pageNum}/${info.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
