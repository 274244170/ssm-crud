<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/static/js/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工信息编辑</title>
<script type="text/javascript" src="${pageScope.webRoot}static/js/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" href="${pageScope.webRoot}static/styles/layui-v2.2.6/layui/css/layui.css">
<script src="${pageScope.webRoot}static/styles/layui-v2.2.6/layui/layui.js"></script>
</head>
<body>
<div class="x-body layui-fluid">
	<form class="layui-form mForm">
		<input type="hidden" class="layui-input" name="empId" value="${emp.empId}"/>
		<div class="opera-form-box">
            <div class="layui-form-item">
                <label for="username" class="layui-form-label query-label" title="用户名">
                    <span class="required-red">*</span>用户名：
                </label>
                <div class="layui-input-inline query-text">
                    <p class="layui-input">${emp.empName}</p>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="realName" class="layui-form-label query-label" title="邮箱">
                        <span class="required-red">*</span>邮箱：
                    </label>
                    <div class="layui-input-inline query-text">
                        <input type="text" name="email" class="layui-input" lay-verify="email" placeholder="email@qq.com" value="${emp.email }">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="gender" class="layui-form-label query-label" title="性别">
                        <span class="required-red">*</span>性别：
                    </label>
                    <div class="layui-input-inline query-select">
                        <label class="layui-radio-inline">
						  <input type="radio" name="gender" value="0" title="男" <c:if test="${emp.gender == 0}">checked</c:if>>
						</label>
						<label class="layui-radio-inline">
						  <input type="radio" name="gender" value="1" title="女" <c:if test="${emp.gender == 1}">checked</c:if>>
						</label>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="deptName" class="layui-form-label query-label" title="部门">
                    <span class="required-red">*</span>部门：
                </label>
                <div class="layui-input-inline query-select">
                    <input id="deptId" name="deptId" type="hidden">
                    <select name="deptId">
                    	<option value=""></option>
					    <c:forEach  items="${depts}" var="dept" >
					    	<option value="${dept.deptId }" <c:if test="${emp.deptId == dept.deptId}">selected=""</c:if>>${dept.deptName }</option>
					    </c:forEach>
      				</select>
                </div>
            </div>
        </div>
        
        <div class="opera-bottom-bar">
            <div class="layui-form-item">
                <button class="layui-btn" id="close">关闭</button>
      			<button class="layui-btn layui-btn-normal" lay-filter="update" lay-submit="">更新</button>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
layui.use(['form', 'layer'], function(){
	var form = layui.form
	    layer = layui.layer;
	
	//自定义验证规则
    form.verify({
    	email: function(value){
        	if (value.trim() == "") {
                return "邮箱不能为空";
            }
        	if(!/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/.test(value)){
        		return "邮箱格式不正确";
        	}
        }
    });

    $('#close').click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
    
    //监听提交
    form.on('submit(update)', function (data) {
        var loadIndex = layer.load();
        //校验
        if(!validate(data)){
            layer.close(loadIndex);
            return false;
        }

        $.ajax({
    		url:"${pageScope.webRoot}emp/"+data.field.empId,
    		type:"PUT",
    		data: data.field,
            async: false, 
            traditional: true,
            success: function (d) {
                if (d.code == 800200) {
                    layer.alert(
                        "修改用户成功",
                        {icon: 1, closeBtn: false},
                        function () {
                            parent.location.reload();
                        }
                    );
                }
            }, error: function () {
                layer.msg('请求失败', {icon: 5, offset: 'rb', area: ['120px', '80px'], anim: 2});
            }, complete: function(){
                layer.close(loadIndex);
            }
    	});
        return false;

        function validate(data)
        {
            if(!data.field.deptId || data.field.deptId == ""){
                layer.msg("请选择部门", {shift:6,icon:2});
                return false;
            }
            return true;
        }
    });
});

</script>
</body>
</html>