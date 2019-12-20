layui.use(['table', 'form', 'element'], function () {
    var table = layui.table;
    var form = layui.form;
    var element = layui.element;
    var index = layer.load(0);//添加laoding,0-2两种方式
    //方法级渲染
    var tableOpt = {
        id: 'userList',
        elem: '#userList'
        , height: 'full-200'
        , width: '100%'
        , loading: true
        , cellMinWidth: '80'
        , url: 'list'
        , toolbar: '#toolbar'
        , cols: [[
            {checkbox: true, fixed: true}
            , {field: 'empId', title: '序号', width: '80', sort: true }
            , {field: 'empName', title: '用户姓名', width: '300', sort: true }
            , {field: 'genderName', title: '性别', width: '100', sort: true}
            , {field: 'email', title: '邮箱', width: '400'}
            , {field: 'deptName', title: '所属部门', width: '400'}
            , {field: 'right', title: '操作', width: '200', fixed: 'right', toolbar: "#barDemo"}
        ]]
        , page: true
        , request: {
            pageName: 'pageNum' //页码的参数名称，默认：page
            , limitName: 'pageSize' //每页数据量的参数名，默认：limit
        }
        , response: {
            statusCode: 800200 //成功的状态码，默认：0
            , countName: 'total' //数据总数的字段名称，默认：count
            , dataName: 'list' //数据列表的字段名称，默认：data
        },
        done: function (res) {   //返回数据执行回调函数
            layer.close(index);    //返回数据关闭loading

        }

    };
    table.render(tableOpt);

    //监听提交
    form.on('submit(search)', function (data) {
        tableOpt.where = data.field;
        table.render(tableOpt);
        return false;
    });

    //监听排序
    table.on('sort(user)', function (data) { //注：tool是工具条事件名，userList是table原始容器的属性 lay-filter="对应的值"
        // 尽管我们的 table 自带排序功能，但并没有请求服务端。
        // 有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，从而实现服务端排序，如：
        var sort = '';
        if (data.type != null) {
        	if (data.field == 'empId') {
                sort = 'emp_id ' + data.type;
            }
        	else if (data.field == 'empName') {
                sort = 'emp_name ' + data.type;
            } 
        	else if (data.field == 'genderName') {
                sort = 'gender ' + data.type;
            }
        }
        table.reload('userList', {
            // initSort: data //记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
            where: { 
            	//请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
                sort: sort
            }
        });
    });

    var active = {
        add: function () {
            add();
        },
        del: function () {
            var checkStatus = table.checkStatus('userList'), data = checkStatus.data;
            if (data.length == 0) {
                layer.msg('请至少选择一个用户', {icon: 5});
                return false;
            }
            var idArr = [];
            for (var i = 0; i < data.length; i++) {
                idArr.push(data[i].empId);
            }
            del(idArr);
        },
        reload: function () {
            location.reload();
        }
    };

    $(document).on('click','.layui-btn', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //监听表格复选框选择
    table.on('checkbox(user)', function (obj) {
    	
    });
    
    //监听工具条
    table.on('tool(user)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            update(data.empId);
        } else if (obj.event === 'del') {
            del([data.empId]);
        }
    });
    
    //加载部门信息
    /*$.ajax({
		type:"GET",
		url:"depts",
		success:function(result){
			//显示部门信息在下拉列表中
			$.each(result.extend.depts,function(){
				var optionEle = "";
				optionEle += "<option value='" + this.deptId + "'>" + this.deptName + "</option>";
				$("#deptId").append(optionEle);
			});
			form.render("select");
		}
	});*/
    
});


function del(idArr) {
    layer.confirm("确定删除选中用户吗", function () {
        var params = $.map(idArr, function (id) {
            return {name: "empIds", value: id};
        });

        $.ajax({
        	type:"POST",
        	url:"deleteEmp",
        	data: params, async: false,
            success: function (d) {
                if (d.code == 800200) {
                    layer.alert("删除用户成功", function () {
                        location.reload();
                    });
                } else {
                    layer.msg(d.msg, {shift: 6, icon: 2});
                }
            }, error: function () {
                layer.msg('请求失败', {icon: 2});
            }
        });
    });


}

/**
 * 更新用户
 */
function update(empId) {
	layer.open({
        type: 2,
        id: 'user-update',
        title: '编辑用户',
        area: ['800px', '500px'],
        scrollbar: true,
        shade: 0,
        maxmin: true,
        content: 'editEmp/' + empId
    });
}

/**
 * 新增用户
 */
function add() {
	layer.open({
        type: 2,
        id: 'user-add',
        title: '新增用户',
        area: ['800px', '500px'],
        scrollbar: true,
        shade: 0,
        maxmin: true,
        content: 'addEmp'
    });
}