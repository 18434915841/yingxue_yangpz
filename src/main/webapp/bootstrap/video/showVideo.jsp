<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>

    $(function(){
        pageInit();
    });
    function pageInit(){
        $("#videoTable").jqGrid(
            {
                url : '${path}/video/showAll',
                editurl:"${path}/video/edits",
                datatype : "json",
                rowNum : 5,
                rowList : [5, 10, 20, 30 ],
                pager : '#videoPage',
                sortname : 'id',
                mtype : "post",
                viewrecords : true,
                styleUI:"Bootstrap",
                autowidth:true,
                height:"auto",
                sortorder : "desc",
                caption : "视频信息展示",
                colNames : [ 'Id', '标题', '简介', '视频链接', '发布时间', '所属类别id','分组id',"用户id" ],
                colModel : [
                    {name : 'id',width : 55},
                    {name : 'title',editable:true,width : 90},
                    {name : 'description',editable:true,width : 100},
                    {name : 'videoPath',editable:true,width : 80,align : "right","edittype":"file",
                        // formatter:function(cellvalue){
                        //     return "<video src='"+cellvalue+"' width='200px' height='100px' >";
                        // }//<input type="file" id="headImg" name="headImg" >
                        formatter:function(cellvalue,options,row){
                            return "<video src='"+cellvalue+"' width='200px' height='100px' " +
                                     "controls poster='"+row.coverPath+"'  >";
                        }
                    },
                    // {name : 'coverPath',editable:true,width : 80,align : "right","edittype":"file"},
                    {name : 'uploadTime',width : 80,align : "right"},
                    {name : 'cateId',editable:true,width : 150,},
                    {name : 'groupId',editable:true,width : 150,},
                    {name : 'userId',width : 150,}
                ]
            });
        $("#videoTable").jqGrid('navGrid', '#videoPage', {edit : true,add : true,del : true},
            {closeAfterEdit:true  },
            {closeAfterAdd:true,
                afterSubmit:function (data){
                    console.log(data);
                    <%--$.ajaxFileUpload({--%>
                    <%--    url:"${path}/video/fileupload",--%>
                    <%--    type:"post",--%>
                    <%--    dataType:"json",--%>
                    <%--    data:{"videoId":data.responseJSON.videoId},--%>
                    <%--    fileElementId:"videoPath",--%>
                    <%--    success:function (data){--%>

                    <%--        $("#videoTable").trigger("reloadGrid");--%>
                    <%--    }--%>
                    <%--});--%>
                    $.ajaxFileUpload({
                        url:"${path}/video/fileupload",
                        type:"post",
                        dataType:"json",
                        data:{"videoId":data.responseJSON.videoId},
                        fileElementId:"videoPath", //fileElementId　　　需要上传的文件域的ID，即<input type="file" >的ID。
                        success:function (data){
                            //文件上传成功返回
                            //alert(data.message);
                            //刷新表格
                            $("#videoTable").trigger("reloadGrid");
                        }
                    });
                return "hello";
                }
            },
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
    <table id="videoTable" />

    <%--创建分页工具栏--%>
    <div id="videoPage" />
</div>


