<%--
  Created by IntelliJ IDEA.
  User: zr
  Date: 2019/6/28
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js"></script>

</head>
<body style="width: 100%; height: 100%">
<%--分页的页面--%>
<table id="dg"></table>

<%--工具栏--%>
<div id="ToolBar">
    <div style="height: 25px;">
        <a href="javascript:AddType()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加类型</a> <a
            href="javascript:UpdateType()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改类型</a> <a
            href="javascript:DeleteType()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">删除类型</a>
    </div>
    <div>
       用户名: <input type="text" id="sname" name="name">
        电话号码:<input type="text" id="telephone" name="telephone">
        <a href="javascript:Searcha()" class="easyui-linkbutton"
                iconCls="icon-search" plain="true">查询</a>
    </div>
</div>

<%--添加的窗口--%>
<div id="AddDialog" class="easyui-dialog" buttons="#Addbutten"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true" cache="true">
    <form id="ModiyDialogForm" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td>
                    <input type="text"  required
                           name="name" id="name" />
                </td>
            </tr>
            <tr>
                <td></td>
            </tr>
        </table>
    </form>
</div>

<%--添加的确认按钮--%>
<div id="Addbutten">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">添加</a> <a href="javascript:CloseDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

<%--修改的窗口--%>
<div id="Updatebutten" class="easyui-dialog" buttons="#updatebutten"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true" cache="true">
    <form id="updateform" method="post">
        <table>
            <tr>
                <td>编号</td>
                <td>
                    <input type="text" name="id" id="id" readonly style="border: none" class="easyui-validatebox" required>
                </td>
            </tr>
            <tr>
                <td>类型</td>
                <td>
                    <input type="text" name="name" id="bname" class="easyui-validatebox" required/>
                </td>
            </tr>
        </table>
    </form>
</div>

<%--修改的确认按钮--%>
<div id="updatebutten">
    <a href="javascript:UpdateDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">更新</a> <a href="javascript:CloseaDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
<script type="application/javascript" src="js/Users.js"></script>
<script>
    function Searcha() {
        var name1=  $("#sname").val();
        var telephone1=$("#telephone").val();
        $("#dg").datagrid("load",{name:name1,telephone:telephone1})
    }
</script>
</html>
