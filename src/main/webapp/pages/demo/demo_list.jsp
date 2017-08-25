<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="oscache" uri="http://www.opensymphony.com/oscache" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${ctx }/common/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/common/css/themes/icon.css">
<script type="text/javascript" src="${ctx }/common/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx }/common/js/jquery.easyui.min.js"></script>
<title>用户列表</title>
<script type="text/javascript">
$(function(){
	$("#userTable").datagrid({
		url:'${ctx}/demo/datajson',   
	    columns:[[   
	        {field:'id',title:'id',width:50},   
	        {field:'name',title:'用户名',width:50},   
	        {field:'age',title:'年龄',width:50}   
	    ]],
	    toolbar:"#tb",
	    fitColumns:true,
	    fit:true,
	    pagination:true
	})
})
	
</script>
</head>
<body>
	<div id="userTable"></div>
	<div id="tb">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:alert('Add')">Add</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="javascript:alert('Cut')">Cut</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:alert('Save')">Save</a>
	</div>
</body>
</html>