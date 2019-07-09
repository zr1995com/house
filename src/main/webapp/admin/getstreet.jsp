<%--
  Created by IntelliJ IDEA.
  User: zr
  Date: 2019/6/25
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body style="width: 100%; height: 100%">
<table id="dg" style="width: 500px"></table>
<div id="ToolBar">
    <div style="height: 25px;">
        <a href="javascript:Add('AddDialog','添加街道')" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加街道</a> <a
            href="javascript:ModifyBySelect()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改街道</a> <a
            href="javascript:DeleteByFruitName()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">删除街道</a>
    </div>
</div>
<div id="upStreet" class="easyui-dialog" buttons="#upDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true" cache="true">
    <form id="upDialogForm" method="post">
        <table>
            <tr>
                <td>区域编号</td>
                <td>
                        <input id="bdistrictId" class="easyui-combobox" name="districtId"
                               data-options="valueField:'id',textField:'name',url:'aaa'"/>
                </td>
            </tr>
            <tr>
                <td>街道名</td>
                <td><input type="text"  required
                           name="name" id="bname" />
                    <input type="hidden" name="id" id="bid">
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true" cache="true">
    <form id="ModiyDialogForm" method="post">
        <table>
            <tr>
                <td>区域编号</td>
                <td>
                    <input id="districtId" class="easyui-combobox" name="districtId"
                           data-options="valueField:'id',textField:'name',url:'aaa'"/>
                </td>
            </tr>
            <tr>
                <td>街道名</td>
                <td><input type="text"  required
                           name="name" id="name" />
                    <input type="hidden" name="id" id="id">
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="upDialogButtons">
    <a href="javascript:upDaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">更新</a>
    <a href="javascript:CloseDialog()"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
<script>
    $(function () {
        $('#dg').datagrid({
            title:'街道列表',
            pagination:true,
            url:'getStreet',
            toolbar:"#ToolBar",
            pageList:[2,3,4],
            pageSize:3,
            rownumbers:true,
            columns:[[
                {field:'xb',checkbox:"true"},
                {field:'id',title:'编号',width:100},
                {field:'name',title:'街道名',width:100},
                {field:'districtId',title:'区域编号',width:100}
            ]]
        });
    });
    function Add(id,name){
        $("#"+id).dialog('open').dialog('setTitle',name);
        url = "addStreet";
    }
    function ResetValues(){
        $("#name").val('');

    }

    function SaveDialog(){
        //表单异步提交添加
        $("#ModiyDialogForm").form('submit',{
            url:url,
            success:function(data){
                data=$.parseJSON(data);
                if( data.result!=1){
                    $.messager.alert("系统提示","添加失败");
                    $('#districtId').form('clear');
                    return;
                }
                else if (data.result==1) {
                    $.messager.alert("系统提示","添加成功");
                    ResetValues();
                    $('#districtId').combobox('clear');
                    $("#AddDialog").dialog('close'); //关闭
                    $("#dg").datagrid('reload'); //刷新
                }
            }
        });

        ResetValues();
    }
    function CloseDialog(){
        ResetValues();
        $('#districtId').combobox('clear');
        $("#AddDialog").dialog('close');
    }
    function ModifyBySelect() {
       var Selections= $("#dg").datagrid('getSelections');
       if (Selections.length!=1){
           $.messager.alert("系统提示","请选择一行要编辑的数据")
           return;
       }else {
           $("#upStreet").dialog("open").dialog("setTitle",">>>修改街道")
           var row=Selections[0];
           $("#upDialogForm").form('load',row);
       }
    }
    function upDaveDialog() {
        $("#upDialogForm").form('submit',{
            url:"upStreet",
            success:function (data) {
              data=  $.parseJSON(data);
              if (data.result==1){
                  $("#upStreet").dialog("close");
                  $("#dg").datagrid("reload")
              }else {
                  $.messager.alert("提示框",'修改失败！^_^','info')
              }
            }
        });
    }
    function DeleteByFruitName(){
        //获取选择行
        var SelectRows = $("#dg").datagrid('getSelections');
        if( 0 == SelectRows.length ){
            $.messager.alert("系统提示", "请选择要删除的数据");
            return;
        }
        var SelectIndexArr = [];
        for( var i = 0 ; i < SelectRows.length; i++ ){
            SelectIndexArr.push(SelectRows[i].id);
        }
        var SelectIndexToString = SelectIndexArr.join(",");
        $.messager.confirm("系统提示", "你确定要删除<font color=red> " + SelectRows.length + " </font>条数据吗?", function(xo){
            if( xo ){
                $("#dg").datagrid('reload');
                //支持多项删除
                $.post("deleteStreet",{ids:SelectIndexToString},function(data){
                    if(data.result<1){
                        $.messager.alert("系统提示", "<font color=red>删除失败,"+data.result+"条记录</font>");
                    }
                    else{
                        $.messager.alert("系统提示", "你已成功删除 ");
                        $("#dg").datagrid('reload');
                    }
                },"json");
            }
        });
    }
</script>
</html>
