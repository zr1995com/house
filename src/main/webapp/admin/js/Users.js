$(function () {
    $('#dg').datagrid({
        url:'getUser',
        pagination:true,
        title:'用户列表',
        toolbar:"#ToolBar",
        pageSize:3,
        rownumbers:true,//编号
        pageList:[2,3,4],
        columns:[[
            {field:'xb',checkbox:"true"},
            {field:'id',title:'编号',width:100},
            {field:'name',title:'类型名',width:100,align:'right'},
            {field:'telephone',title:'电话号码',width:100,align:'right'},
            {field:'isadmin',title:'用户类型',width:100,align:'right'}
        ]]
    });
});
function AddType() {
    $("#AddDialog").dialog("open").dialog("setTitle","添加类型");
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