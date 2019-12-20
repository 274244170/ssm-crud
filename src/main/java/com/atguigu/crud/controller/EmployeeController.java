package com.atguigu.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.crud.dto.EmployeeDto;
import com.atguigu.crud.dto.Msg;
import com.atguigu.crud.dto.PageVo;
import com.atguigu.crud.dto.RespPageResult;
import com.atguigu.crud.dto.RespResult;
import com.atguigu.crud.enums.GenderEnum;
import com.atguigu.crud.enums.ResultCode;
import com.atguigu.crud.model.Department;
import com.atguigu.crud.model.Employee;
import com.atguigu.crud.service.DepartmentService;
import com.atguigu.crud.service.EmployeeService;
import com.atguigu.crud.utils.ResultUtil;
import com.atguigu.crud.vo.EmployeeVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 处理员工CRUD请求
 * 
 * @author Peng.H
 * 
 */
@Controller
public class EmployeeController
{

	@Autowired
	EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * layui首页
	 * @author Peng.Hu
	 * @date 2019年5月31日
	 * @return
	 *
	 */
	@RequestMapping("/index")
	public ModelAndView index() 
	{
		Map<String, Object> map = new HashMap<>();
		List<Department> list = departmentService.getDepts();
		map.put("depts", list);
		return new ModelAndView("emps_index", map);
	}
	
	@RequestMapping("/index1")
	public ModelAndView index1() 
	{
		return new ModelAndView("list");
	}
	
	/**
	 * 检查用户名是否可用
	 * @param empName
	 * @return
	 */
	@RequestMapping("/checkuser")
	@ResponseBody
	public Msg checkuser(@RequestParam("empName") String empName)
	{
		Msg msg = new Msg();
		//先判断用户名是否是合法的表达式;
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if (!empName.matches(regx))
		{
			msg.setResCode(ResultCode.C800401);
			msg.add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
		}

		//数据库用户名重复校验
		boolean b = employeeService.checkUser(empName);
		if (b)
		{
			msg.setResCode(ResultCode.C800200);
		}
		else
		{
			msg.setResCode(ResultCode.C800401);
			msg.add("va_msg", "用户名不可用");
		}
		return msg;
	}

	/**
	 * 导入jackson包。
	 * @param pn
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/emps", method = RequestMethod.GET)
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn)
	{
		Msg msg = new Msg();
		// 这不是一个分页查询
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pn, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> emps = employeeService.getAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page = new PageInfo(emps, 5);
		msg.setResCode(ResultCode.C800200);
		msg.add("pageInfo", page);
		return msg;
	}

	/**
	 * 分页查询
	 * @author Peng.Hu
	 * @date 2019年5月23日
	 * @param vo
	 * @return
	 *
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ResponseBody
	public RespPageResult list(EmployeeVo vo) 
	{
		try {
			List<EmployeeVo> employeePageVoList = new ArrayList<>();
			
			Page<EmployeeDto> pageDto = employeeService.listEmployeesPage(vo);
			// 需要把查询结果包装成Page对象才能序列化。
		    PageVo pageVO = new PageVo(pageDto);
		    
		    if (pageDto != null)
			{
				for (EmployeeDto employeeDto : pageDto)
				{
					EmployeeVo employeeVo = new EmployeeVo(employeeDto);
					if (employeeDto.getGender() == GenderEnum.MAN.getKey())
					{
						employeeVo.setGenderName(GenderEnum.MAN.getValue());
					}
					else
					{
						employeeVo.setGenderName(GenderEnum.WOMAN.getValue());
					}
					employeePageVoList.add(employeeVo);
				}
			}
		    pageVO.setList(employeePageVoList);
			return ResultUtil.getSuccessPageResult(pageVO);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.getFailedPageResult();
		}
	}
	
	/**
	 * 新增员工
	 * 
	 */
	@RequestMapping("/addEmp")
	public ModelAndView addEmp()
	{
		Map<String, Object> map = new HashMap<>();
		List<Department> list = departmentService.getDepts();
		map.put("depts", list);
		return new ModelAndView("emp_add", map);
	}
	
	/**
	 * 员工保存
	 * 1、支持JSR303校验
	 * 2、导入Hibernate-Validator
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee, BindingResult result)
	{
		Msg msg = new Msg();
		if (result.hasErrors())
		{
			//校验失败，应该返回失败，并显示校验失败的错误信息
			Map<String, Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors)
			{
				System.out.println("错误的字段名：" + fieldError.getField());
				System.out.println("错误信息：" + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			msg.setResCode(ResultCode.C800401);
			msg.add("errorFields", map);
		}
		else
		{
			employeeService.saveEmp(employee);
			msg.setResCode(ResultCode.C800200);
		}
		return msg;
	}
	
	/**
	 * 更新员工信息
	 * 
	 */
	@RequestMapping(value = "/editEmp/{id}", method = RequestMethod.GET)
	public ModelAndView editEmp(@PathVariable("id") Integer id)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Employee employee = employeeService.getEmp(id);
		List<Department> list = departmentService.getDepts();
		map.put("depts", list);
		map.put("emp", employee);
		return new ModelAndView("emp_edit", map);
	}
	
	/**
	 * 根据id查询员工
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id)
	{
		Msg msg = new Msg();
		Employee employee = employeeService.getEmp(id);
		msg.setResCode(ResultCode.C800200);
		msg.add("emp", employee);
		return msg;
	}
	
	/**
	 * 如果直接发送ajax=PUT形式的请求
	 * 封装的数据
	 * Employee [empId=1014, empName=null, gender=null, email=null, dId=null]
	 * 
	 * 问题：
	 * 请求体中有数据；但是Employee对象封装不上；
	 * 
	 * 原因：
	 * Tomcat：
	 * 1、将请求体中的数据，封装一个map。
	 * 2、request.getParameter("empName")就会从这个map中取值。
	 * 3、SpringMVC封装POJO对象的时候。会把POJO中每个属性的值，request.getParamter("email");
	 * 
	 * AJAX发送PUT请求引发的血案：
	 * 		PUT请求，请求体中的数据，request.getParameter("empName")拿不到
	 * 		Tomcat一看是PUT不会封装请求体中的数据为map，只有POST形式的请求才封装请求体为map
	 * org.apache.catalina.connector.Request--parseParameters() (3111);
	 * 
	 * protected String parseBodyMethods = "POST";
	 * if( !getConnector().isParseBodyMethod(getMethod()) ) {
            success = true;
            return;
       }
	 * 
	 * 解决方案；
	 * 我们要能支持直接发送PUT之类的请求还要封装请求体中的数据
	 * 1、配置上HttpPutFormContentFilter；
	 * 2、他的作用:将请求体中的数据解析包装成一个map。
	 * 3、request被重新包装，request.getParameter()被重写，就会从自己封装的map中取数据
	 * 
	 */
	
	/**
	 * 员工更新方法
	 * @param employee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
	public Msg saveEmp(Employee employee, HttpServletRequest request)
	{
		Msg msg = new Msg();
		System.out.println("请求体中的值：" + request.getParameter("gender"));
		System.out.println("将要更新的员工数据：" + employee);
		employeeService.updateEmp(employee);
		msg.setResCode(ResultCode.C800200);
		return msg;
	}
	
	/**
	 * 删除员工信息
	 * 批量删除：1-2-3
	 * 单个删除：1
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
	public Msg deleteEmp(@PathVariable("ids") String ids)
	{
		Msg msg = new Msg();
		//批量删除
		if (ids.contains("-"))
		{
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			//组装id的集合
			for (String string : str_ids)
			{
				del_ids.add(Integer.parseInt(string));
			}
			employeeService.deleteBatch(del_ids);
		}
		else
		{
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		msg.setResCode(ResultCode.C800200);
		return msg;
	}
	
	@RequestMapping(value = "/deleteEmp", method = RequestMethod.POST)
	@ResponseBody
	public RespResult deleteEmp(String[] empIds) {
		List<Integer> del_ids = new ArrayList<>();
		for (String empId : empIds)
		{
			del_ids.add(Integer.parseInt(empId));
		}
		employeeService.deleteBatch(del_ids);
		return ResultUtil.getSuccessResult();
	}
	
	/*@ResponseBody
	public RespResult addUser(Employee employee) {
		employeeService.saveEmp(employee);
		return ResultUtil.getSuccessResult(employee);
	}*/
}
