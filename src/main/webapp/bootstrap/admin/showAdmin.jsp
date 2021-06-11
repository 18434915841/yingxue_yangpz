<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>



<script>
    $(function(){
        pageInit();
    });
    function pageInit(){
        $("#adminTable").jqGrid(
            {
                url : "${path}/admin/queryAllAdmin",
                editurl:"${path}/admin/edits",
                datatype : "json",
                rowNum : 5,
                rowList : [5, 10, 20, 30 ],
                pager : '#adminPage',
                sortname : 'id',
                sortorder : "desc",
                mtype : "post",
                autowidth:true,
                viewrecords : true,
                styleUI:"Bootstrap",
                height:"auto",
                caption : "管理员信息展示",
                colNames : [ 'Id', '用户名', '密码', '状态', '盐','级别'],
                colModel : [
                    {name : 'id',width : 55},
                    {name : 'username',editable:true,width : 90},
                    {name : 'password',editable:true,width : 100},
                    {name : 'status',width : 80,align : "right"},
                    {name : 'salt',editable:true,width : 80,align : "right"},
                    {name : 'level',width : 80,align : "right"}
                ]
            });
        $("#adminTable").jqGrid('navGrid', '#adminPage',{edit:true,del:true,add:true,search:true},  //是否展示增删改查按钮  {},都展示
            {
                closeAfterEdit:true
            },  //修改之后的额外操作
            {   //在执行添加成功之后进入该括号执行
                closeAfterAdd:true //执行成功之后关闭对话框
            },  //添加之后的额外操作
            {
                closeAfterDel: true
            },   //删除之后的额外操作
            {
                closeAfterSearch: true
            }
        );
    }
</script>






<%--创建一个面板--%>
<div class="panel panel-info">

    <%--设置面板的内容--%>
    <div class="panel panel-heading">用户信息</div>

    <!-- 创建标签页 -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">用户信息</a></li>
    </ul><br>

    <%--按钮--%>
    &emsp;&emsp;<button class="btn btn-info">导出用户信息</button>&emsp;&emsp;
    <button class="btn btn-success">导出用户信息</button>&emsp;&emsp;
    <button class="btn btn-warning">导出用户信息</button>&emsp;&emsp;
    <button class="btn btn-danger">导出用户信息</button><br>

    <%--初始化表格--%>
    <table id="adminTable" />

    <%--创建分页工具栏--%>
    <div id="adminPage" />



</div></div>