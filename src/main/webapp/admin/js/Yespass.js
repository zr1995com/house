$(function () {
    $('#dg').datagrid({
        url:'passYes',
        pagination:true,
        title:'未审核',
        toolbar:"#ToolBar",
        pageSize:3,
        rownumbers:true,//编号
        pageList:[2,3,4],
        columns:[[
            {field:'xb',checkbox:"true"},
            {field:'id',title:'编号',width:100},
            {field:'title',title:'标题',width:100,align:'right'},
            {field:'price',title:'价格',width:100,align:'right'},
            {field:'pubdate',title:'日期',width:100,align:'right',formatter: function(value,row,index){
                    var date=new Date(value);
                    var a=date.getFullYear();
                    var b=date.getMonth()+1;
                    var c=date.getDate();
                    return a+"/"+b+"/"+c;
                }},
            {field:'floorage',title:'面积',width:100,align:'right'},
            {field:'contact',title:'电话号码',width:100,align:'right'},
            {field:'tname',title:'类型',width:100,align:'right'},
            {field:'sname',title:'街道名',width:100,align:'right'},
            {field:'dname',title:'区域名',width:100,align:'right'},
            {field:'opt',title:'操作',width:100,align:'left',
                formatter:function(value,row,index){
                    //发送同步请求
                    // return "<a href=\"delDistrict?id="+row.id+"\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";
                    //发送异步请求Ajax
                    return "<a href=\"javascript:updateispass("+row.id+")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">未审核</a>";

                }}
        ]]
    });
});
function AddType() {
    $("#AddDialog").dialog("open").dialog("setTitle","添加类型");
}
function getHouse(id) {
    
}
function updateispass(id) {
    $.post("uppassNo",{"id":id},function (data) {
        if (data.result>0){
            $("#dg").datagrid("reload");
        }else {
            $.messager.alert("提示框","审查失败","info")
        }
    },"json")
}
function SaveDialog() {
    $("#ModiyDialogForm").form('submit',{
        url:'addType',
        success:function (data) {
            data=$.parseJSON(data);
            if (data.result>0){
                $.messager.alert("提示框","添加成功","info");
                $("#ModiyDialogForm").form("clear");
                $("#AddDialog").dialog("close");
                $("#dg").datagrid("reload");
            }else {
                $.messager.alert("系统提示","添加失败","info");
                $("#ModiyDialogForm").form("clear");
                return;
            }
        }
    })
}
function CloseDialog() {
    $("#ModiyDialogForm").form("clear");
    $("#AddDialog").dialog("close")
}

function UpdateType() {
    var Selectrows= $("#dg").datagrid("getSelections");
    if (Selectrows.length!=1){
        $.messager.alert("提示框","请选择一行要编辑的数据","info");
        return;
    }else {
        var row=Selectrows[0];
        $("#Updatebutten").dialog("open").dialog("setTitle","修改页面");
        $("#updateform").form("load",row);
    }
}

function CloseaDialog() {
    $("#updateform").form("clear");
    $("#Updatebutten").dialog("close");
}
function UpdateDialog() {
    $("#updateform").form('submit',{
        url:'updateType',
        success:function (data) {
            data=$.parseJSON(data);
            if (data.result>0){
                $.messager.alert("提示框","修改成功","info");
                $("#updateform").form("clear");
                $("#Updatebutten").dialog("close");
                $("#dg").datagrid("reload");
            }else {
                $.messager.alert("系统提示","修改失败","info");
            }
        }
    })
}
function DeleteType() {
    var SelectRows= $("#dg").datagrid("getSelections");
    if (SelectRows.length==0){
        $.messager.alert("提示框","请选择要编辑的数据","info");
        return
    }else {
        var a=[];
        for (var i = 0; i <SelectRows.length ; i++) {
            var row= SelectRows[i];
            a.push(row.id)
        }
        var b=a.join(",");
        $.messager.confirm("提示框","确认删除吗?",function (r) {
            if (r){
                $.post("deleteType",{ids:b},function (data) {
                    if (data.result>0){
                        $.messager.alert("提示框","删除成功","info");
                        $("#dg").datagrid("reload");
                    }else {
                        $.messager.alert("系统提示","删除失败","info");
                    }                   },"json")
            }
        })
    }
}