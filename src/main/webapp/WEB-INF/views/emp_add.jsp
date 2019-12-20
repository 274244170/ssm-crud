<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/static/js/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工新增</title>
<script type="text/javascript" src="${pageScope.webRoot}static/js/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" href="${pageScope.webRoot}static/styles/layui-v2.2.6/layui/css/layui.css">
<script src="${pageScope.webRoot}static/styles/layui-v2.2.6/layui/layui.js"></script>
</head>
<body>
<div class="x-body layui-fluid">
	<form class="layui-form mForm">
		<div class="opera-form-box">
            <div class="layui-form-item">
                <label for="username" class="layui-form-label query-label" title="用户名">
                    <span class="required-red">*</span>用户名：
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="empName" id="empName_add_input" lay-verify="empName" placeholder="empName" autocomplete="off" class="layui-input">
      				<span class="layui-form-mid layui-word-aux tip"></span>
                </div>
                <div id="ms" class="layui-form-mid layui-word-aux">
                    <span id="ums" style="color: #FFB800;">将会成为您唯一的登入名</span>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="realName" class="layui-form-label query-label" title="邮箱">
                        <span class="required-red">*</span>邮箱：
                    </label>
                    <div class="layui-input-inline query-text">
                        <input type="text" name="email" id="email_add_input" lay-verify="email" placeholder="email@qq.com" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="gender" class="layui-form-label query-label" title="性别">
                        <span class="required-red">*</span>性别：
                    </label>
                    <div class="layui-input-inline query-select">
                        <input type="radio" name="gender" value="0" checked="checked" title="男"/>
	  					<input type="radio" name="gender" value="1" title="女"/>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="deptName" class="layui-form-label query-label" title="部门">
                    <span class="required-red">*</span>部门：
                </label>
                <div class="layui-input-inline query-select">
                    <input id="deptId" name="deptId" type="hidden">
                    <select name="deptId" id="deptId_add_select">
      					<option value=""></option>
					    <c:forEach  items="${depts}" var="dept" >
					    	<option value="${dept.deptId }">${dept.deptName }</option>
					    </c:forEach>
      				</select>
                </div>
            </div>
        </div>
        
        <div class="opera-bottom-bar">
            <div class="layui-form-item">
                <button class="layui-btn" id="close">关闭</button>
      			<button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit="">保存</button>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
layui.use(['form', 'layer'], function(){
	var form = layui.form,
	    layer = layui.layer;
	
	//自定义验证规则
    form.verify({
    	empName: function (value) {
            if (value.trim() == "") {
                return "用户名不能为空";
            }
        	if(!/(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/.test(value)){
        		return "用户名可以是2-5位中文或者6-16位英文和数字的组合";
        	}
        }
        ,email: function(value){
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
    form.on('submit(add)', function (data) {
        var loadIndex = layer.load();
        //校验
        if(!validate(data)){
            layer.close(loadIndex);
            return false;
        }

        $.ajax({
        	url:"${pageScope.webRoot}emp",
            type: 'POST',
            data: data.field,
            async: false, 
            traditional: true,
            success: function (d) {
                if (d.code == 800200) {
                    layer.alert(
                        "新增用户成功",
                        {icon: 1, closeBtn: false},
                        function () {
                            parent.location.reload();
                        }
                    );
                } else {
                	//显示失败信息：
					if(undefined != result.extend.errorFields.empName){
						//显示员工名字的错误信息
						layer.msg(result.extend.errorFields.empName, {shift:6,icon:2});
					}
					if(undefined != result.extend.errorFields.email){
						//显示邮箱错误信息
						layer.msg(result.extend.errorFields.email, {shift:6,icon:2});
					}
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
            
            if($(".tip").text() == "用户名不可用"){
        		layer.msg("请修改用户名", {shift:6,icon:2});
        		return false;
        	}
            
            return true;
        }
    });
    
});

//校验用户名是否可用
$("#empName_add_input").change(function(){
	var empName = this.value;
	$.ajax({
		url:"${pageScope.webRoot}checkuser",
		data:"empName="+empName,
		type:"POST",
		success:function(result){
			if(result.code==800200){
				show_validate_msg("#empName_add_input","success","用户名可用");
			}else{
				show_validate_msg("#empName_add_input","error",result.extend.va_msg);
			}
		}
	});
});

//显示校验结果的提示信息
function show_validate_msg(ele,status,msg){
	//清除当前元素的校验状态
	$(ele).next("span").text("");
	if("success"==status){
		$(ele).next("span").text(msg);
	}else if("error" == status){
		$(ele).next("span").text(msg);
	}
}

</script>
</body>
</html>