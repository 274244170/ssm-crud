layui.use(['form', 'layer'], function () {
    var form = layui.form;
    var layer = layui.layer;

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
    
   
    //监听新增
    form.on('submit(add)', function (data) {
        var loadIndex = layer.load();
        //校验
        if(!validate(data)){
            layer.close(loadIndex);
            return false;
        }

        $.ajax({
        	url:"emp",
            type: 'post',
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

    //监听编辑
    form.on('submit(update)', function (data) {
        var loadIndex = layer.load();
        //校验
        if(!validate(data)){
            layer.close(loadIndex);
            return false;
        }

        console.log(data.field.empId);
        $.ajax({
    		url:"emp/"+data.field.empId,
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