package com.atguigu.crud.service;

import java.util.List;

import com.atguigu.crud.dto.EmployeeDto;
import com.atguigu.crud.model.Employee;
import com.atguigu.crud.vo.EmployeeVo;
import com.github.pagehelper.Page;

public interface EmployeeService
{
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> getAll();
	
	/**
	 * 查询满足条件的员工信息
	 * @return
	 */
	public List<Employee> getEmployeeByVo(EmployeeVo vo);
	
	/**
	 * 带有分页的员工列表
	 * @param vo
	 * @return
	 *
	 */
	public Page<EmployeeDto> listEmployeesPage(EmployeeVo vo);
	
	/**
	 * 检验用户名是否可用
	 * 
	 * @param empName
	 * @return  true：代表当前姓名可用   fasle：不可用
	 */
	public boolean checkUser(String empName);
	
	/**
	 * 员工保存
	 * @param employee
	 */
	public void saveEmp(Employee employee);
	
	/**
	 * 按照员工id查询员工
	 * @param id
	 * @return
	 */
	public Employee getEmp(Integer id);
	
	/**
	 * 员工更新
	 * @param employee
	 */
	public void updateEmp(Employee employee);
	
	/**
	 * 员工删除
	 * @param id
	 */
	public void deleteEmp(Integer id);
	
	/**
	 * 批量删除
	 * @param ids
	 *
	 */
	public void deleteBatch(List<Integer> ids);
}
