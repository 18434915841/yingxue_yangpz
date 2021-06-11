<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

        <!--引入Bootstrap核心css-->
        <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.min.css">

        <!--引入jqGrid与Bootstrap集成核心css-->
        <link rel="stylesheet" href="${path}/bootstrap/jqgrid/js/i18n/ui.jqgrid-bootstrap.css">

        <!--引入jquery核心js-->
        <script src="${path}/bootstrap/js/jquery-3.4.1.min.js"></script>

        <!--引入Bootstrap核心js-->
        <script src="${path}/bootstrap/js/bootstrap.js"></script>

        <!--引入jqGrid国际化的js-->
        <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>

        <!--引入jqGrid核心js-->
        <script src="${path}/bootstrap/jqgrid/js/i18n/jquery.jqGrid.js"></script>

        <script>
            $(function(){
                    $("#feedbackTable").jqGrid(
                        {
                            url : "${path}/feedback/queryByPage",
                            editurl:"${path}/feedback/edits",//指定增删改操作的url   添加oper=add  修改oper=edit  删除oper=del
                            datatype : "json",
                            rowNum : 3,
                            rowList : [ 5,10, 20, 30 ],
                            pager : '#feedbackPage',
                            mtype : "post",
                            viewrecords : true,
                            sortorder : "desc",
                            autowidth:true,
                            styleUI:"Bootstrap",
                            height:"auto",
                            sortname : 'id',  //排序的字段
                            sortorder : "desc",  //排序的方式
                            caption : "反馈数据",
                            colNames : [ 'ID', '标题', '内容', '用户ID', '反馈时间'],
                            colModel : [
                                {name : 'id',width : 55},
                                {name : 'title',editable:true,width : 90},
                                {name : 'content',editable:true, width : 100},
                                {name : 'userId',width : 80,align : "right"},
                                {name : 'feedTime',width : 150}
                            ],
                        }).navGrid("#feedbackPage",//开启增删改查操作  指定分页工具栏的位置
                        {edit:true,del:true,add:true,search:true},  //是否展示增删改查按钮  {},都展示
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


            });

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
    <table id="feedbackTable" />

    <%--创建分页工具栏--%>
    <div id="feedbackPage" />



</div></div>

