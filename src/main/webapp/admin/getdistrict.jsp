<%--
  Created by IntelliJ IDEA.
  User: zr
  Date: 2019/6/25
  Time: 14:15
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
<body style="height: 100%; width: 100%">
<table id="dg" style="width: 500px"></table>
<div id="ToolBar">
    <div style="height: 25px;">
        <a href="javascript:Add()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加区域</a> <a
            href="javascript:ModifyBySelect()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改区域</a> <a
            href="javascript:DeleteByFruitName()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">删除区域</a>
    </div>
</div>
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true" cache="true">
    <form id="ModiyDialogForm" method="post">
        <table>
            <tr>
                <td>区域名</td>
                <td><input type="text"  required
                           name="name" id="name" />
                    <input type="hidden" name="id" id="id">
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<div id="upDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<div id="showStreetDialog" class="easyui-dialog" buttons="#upDialogButtons"
     style="width: 500px; height: 350px; padding: 10px 20px;" closed="true">
    <table id="steetdg"></table>
    <hr/>
    <form id="addDiasslogForm"  method="post">
        <table>
            <tr>
                <td>街道名称:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="name" id="bname" /></td>
                <a href="javascript:CloseDialog('upDialog')"
                   class="easyui-linkbutton" iconCls="icon-cancel">添加</a>
            </tr>

        </table>
    </form>
</div>
</body>
<script type="application/javascript" src="js/District.js"></script>

</html>
