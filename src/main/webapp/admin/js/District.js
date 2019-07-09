function getStreetByDir(id){
    //打开窗口
    $("#showStreetDialog").dialog("open").dialog("setTitle","街道明细");

    //显示当前区域下的街道
    $('#steetdg').datagrid({
        title:"街道信息",
        url:'getStreets?id='+id,  //服务器地址
        pagination:true,  //启用分页
        //toolbar:"#ToolBar",  //工具栏
        rownumbers:true,  //显示行号
        //singleSelect:true,  //实现单行选择
        pageList:[3,6,9,15,20], //设置每页大小
        pageSize:3, //每页三条
        columns:[[
            {field:'ck',checkbox:true,width:100,align:'left'},
            {field:'id',title:'编号',width:100,align:'left'},
            {field:'name',title:'街道名称',width:100,align:'left'},
            {field:'opt',title:'操作',width:100,align:'left',
                formatter:function(value,row,index){
                    //发送同步请求
                    // return "<a href=\"delDistrict?id="+row.id+"\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";
                    //发送异步请求Ajax
                    return "<a href=\"javascript:DelDirstrict("+row.id+")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";

                }}
        ]]
    });
}
$(function () {
    $('#dg').datagrid({
        title:'区域列表',
        pagination:true,
        url:'getdistrict',
        toolbar:"#ToolBar",
        pageList:[2,3,4],
        fit:"true",
        pageSize:3,
        rownumbers:true,
        columns:[[
            {field:'xb',checkbox:"true"},
            {field:'id',title:'编号',width:100},
            {field:'name',title:'区域名',width:100},
            {field:'opt',title:'操作',width:100,align:'left',
                formatter:function(value,row,index){
                    //发送同步请求
                    // return "<a href=\"delDistrict?id="+row.id+"\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";
                    //发送异步请求Ajax
                    return "<a href=\"javascript:DelDirstrict("+row.id+")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a> " +
                        "<a href=\"javascript:getStreetByDir("+row.id+")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">街道查询</a> ";

                }}
        ]]
    });

});
var url;
function ResetValues(){
    $("#name").val('');

}
function SaveDialog(){
    //表单异步提交添加
    $("#ModiyDialogForm").form('submit',{
        url:url,
        onSubmit:function(){
            return $(this).form('validate');
        },
        success:function(result){
            if( result=='FALL'){
                $.messager.alert("系统提示",result);
                return;
            }
            else if (result=='SCUUESS') {
                $.messager.alert("系统提示",result);
                ResetValues();
                $("#AddDialog").dialog('close'); //关闭
                $("#dg").datagrid('reload'); //刷新
            }
        }
    });

    ResetValues();
}

function CloseDialog(){
    ResetValues();
    $("#AddDialog").dialog('close');
}


//设置添加url
function Add(){
    $("#AddDialog").dialog('open').dialog('setTitle',"添加数据");
    url = "adddistrict";
}
function ModifyBySelect(){
    //获取选中的行
    var SelectRows = $("#dg").datagrid('getSelections');
    if( 1 != SelectRows.length ){
        $.messager.alert("系统提示", "请选择一行要编辑的数据");
        return;
    }
    var SelectRow = SelectRows[0];
    //打开编辑对话框
    $("#AddDialog").dialog('open').dialog('setTitle',"编辑数据");
    //获得行对象的数据加载到表单中显示
    $("#ModiyDialogForm").form('load',SelectRow);
    url = "updatedistrict";  //设置修改的地址
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
            $.post("deletedistrict",{ids:SelectIndexToString},function(result){
                if( result ){
                    $.messager.alert("系统提示", "你已成功删除 ");
                    $("#dg").datagrid('reload');
                }
                else{
                    $.messager.alert("系统提示", "<font color=red>删除失败</font>");
                }
            },"json");
        }
    });
}
function  DelDirstrict(obj) {  //传的是编号
    //发送异步请求实现删除

    $.messager.confirm('提示框', '你真的想把我删除掉吗？我不能离开你', function(r){
        if (r){
            $.post("delDistrict",{"id":obj},function(data){
                if(data.result==1){
                    //实现datagrid的刷新
                    $('#dg').datagrid('reload');
                }else{
                    $.messager.alert('提示框','删除失败！^_^','info');
                }

            },"json");
        }else{
            $.messager.alert('提示框','想好再点，可以吗！^_^','info');
        }

    });
}