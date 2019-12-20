<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/js/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>SSM-CRUD</title>
	<script type="text/javascript" src="${pageScope.webRoot}static/js/jquery-1.12.4.min.js"></script>
	<link rel="stylesheet" href="${pageScope.webRoot}static/styles/layui-v2.2.6/layui/css/layui.css">
	<script src="${pageScope.webRoot}static/styles/layui-v2.2.6/layui/layui.js"></script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body" style="padding: 15px;">
            <form class="layui-form searchForm" style="padding: 10px 0 0 0;">
                <div class="layui-form-item">
                	<div class="layui-inline">
                        <label class="layui-form-label query-label">姓名：</label>
                        <div class="layui-input-inline query-text">
                            <input class="layui-input" name="empName" autocomplete="off" placeholder="请输入真实姓名">
                        </div>
                    </div>
                    
                    <div class="layui-inline">
                        <label class="layui-form-label query-label">性别：</label>
                        <div class="layui-input-inline query-select">
                            <select name="gender">
                                <option value="">选择性别</option>
                                <option value="0">男</option>
                                <option value="1">女</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="layui-inline">
                        <label class="layui-form-label query-label">部门：</label>
                        <div class="layui-input-inline query-select">
                        	<select name="deptId" id="deptId">
                                <option value="">选择部门</option>
                                <c:forEach  items="${depts}" var="dept" >
							    	<option value="${dept.deptId }">${dept.deptName }</option>
							    </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="layui-inline">
                        <button class="layui-btn layui-btn-md" lay-submit lay-filter="search">
                            <i class="layui-icon">&#xe615;</i>查询
                        </button>
                        <button class="layui-btn layui-btn-md layui-btn-warm" id="refresh" data-type="reload">
                            <i class="layui-icon">&#xe639;</i>重置
                        </button>
                    </div>
                </div>
            </form>
            <hr>
            <table id="userList" class="layui-hide" lay-filter="user"></table>
            
            <div style="display: none" id="barDemo">
                <a class="layui-btn layui-btn-sm layui-btn-normal edit_btn" lay-event="edit"><i class='layui-icon layui-icon-bianji'></i>编辑</a>
                <a class="layui-btn layui-btn-sm layui-btn-danger dle_btn" lay-event="del"><i class='layui-icon layui-icon-delete'></i>删除</a>
            </div>
            <script type="text/html" id="toolbar">
                <div class="layui-btn-container">
                    <div class="layui-btn-group">
                        <button class="layui-btn layui-btn-md layui-btn-normal" data-type="add"><i class="layui-icon">&#xe654;</i>新增</button>
                        <button class="layui-btn layui-btn-md layui-btn-danger" data-type="del">批量删除</button>
                    </div>
                </div>
            </script>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageScope.webRoot}static/public/index.js"></script>
</body>
</html>