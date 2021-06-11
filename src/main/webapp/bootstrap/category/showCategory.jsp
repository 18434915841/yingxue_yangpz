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
        pageInit();
    });
    function pageInit(){
        $("#cateTable").jqGrid(
            {
                url : "${path}/cate/queryAll",
                editurl:"${path}/cate/edits",
                datatype : "json",
                rowNum : 8,
                rowList : [ 8, 10, 20, 30 ],
                pager : '#catePage',
                sortname : 'id',
                viewrecords : true,
                sortorder : "desc",
                multiselect : false,
                subGrid : true,
                caption : "展示类别数据",
                autowidth:true,
                styleUI:"Bootstrap",
                height:"auto",
                colNames : [ 'Id', '类别名称', '级别' ],
                colModel : [
                    {name : 'id',index : 'id',  width : 55},
                    {name : 'cateName',editable:true,index : 'invdate',width : 90},
                    {name : 'levels',index : 'name',width : 100}
                ],
                subGridRowExpanded : function(subgrid_id, row_id) {//当点击一级类别左侧的黑色三角号,展示一级类别下的二级类别
                    subGrid(subgrid_id, row_id);
                },

            });
        $("#cateTable").jqGrid('navGrid', '#catePage', {add : true, edit : true, del : true
        },
            {closeAfterEdit:true  },
            {closeAfterAdd:true},
            {closeAfterDel:true,
                afterSubmit:function (data) {
                    alert(data.responseJSON.message);

                    return "ok";
                }
            },
            {}
        );
    }
        function subGrid(subgrid_id, rowId) {
            console.log(subgrid_id, rowId);
            // we pass two parameters
            // subgrid_id is a id of the div tag created whitin a table data
            // the id of this elemenet is a combination of the "sg_" + id of the row
            // the row_id is the id of the row
            // If we wan to pass additinal parameters to the url we can use
            // a method getRowData(row_id) - which returns associative array in type name-value
            // here we can easy construct the flowing
            // var subgrid_table_id, pager_id;
            // subgrid_table_id = subgrid_id + "_t";
            // pager_id = "p_" + subgrid_table_id;
            var TableId = subgrid_id+"Table";
            var pageId =  subgrid_id+"Page";
            $("#" + subgrid_id).html("<table id='" + TableId + "' class='scroll'></table>" +
                "<div id='" + pageId + "' class='scroll'></div>"
            );


            $("#" + TableId).jqGrid(
                {
                    url : "${path}/cate/queryTwoAll?parentId="+rowId,
                    editurl:"${path}/cate/edits2?parentId="+rowId,
                    datatype : "json",
                    rowNum : 10,
                    pager : "#"+pageId,
                    sortname : 'num',
                    sortorder : "asc",
                    autowidth:true,
                    styleUI:"Bootstrap",
                    height:"auto",
                    colNames : [ 'Id', '类别名', '级别', '父类别' ],
                    colModel : [
                        {name : "id",  index : "num",width : 80,key : true},
                        {name : "cateName",editable:true,index : "item",  width : 130},
                        {name : "levels",index : "qty",width : 70,align : "center"},
                        {name : "parentId",index : "unit",width : 70,align : "center"}
                    ],

                });
            $("#" + TableId).jqGrid('navGrid',
                "#" + pageId, {edit : true, add : true, del : true},
                {closeAfterEdit:true  },
                {closeAfterAdd:true},
                {closeAfterDel:true,},
                {}
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
    <table id="cateTable" />

    <%--创建分页工具栏--%>
    <div id="catePage" />



</div></div>

