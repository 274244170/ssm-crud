package com.atguigu.crud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crud.dao.EmployeeMapper;
import com.atguigu.crud.dto.EmployeeDto;
import com.atguigu.crud.model.Employee;
import com.atguigu.crud.model.EmployeeExample;
import com.atguigu.crud.model.EmployeeExample.Criteria;
import com.atguigu.crud.service.EmployeeService;
import com.atguigu.crud.vo.EmployeeVo;
import com.github.pagehelper.Page;

@Service
public class EmployeeServiceImpl implements EmployeeService
{

	@Autowired
	EmployeeMapper employeeMapper;
	
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> getAll()
	{
		return employeeMapper.selectByExampleWithDept(null);
	}

	/**
	 * 查询满足条件的员工信息
	 * @return
	 */
	public List<Employee> getEmployeeByVo(EmployeeVo vo)
	{
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		if (vo.getEmpName() != "")
		{
			criteria.andEmpNameLike("%" + vo.getEmpName() + "%");
		}
		if (vo.getGender() != null)
		{
			criteria.andGenderEqualTo(vo.getGender());
		}
		if (vo.getDeptId() != null)
		{
			criteria.andDeptIdEqualTo(vo.getDeptId());
		}
		return employeeMapper.selectByExampleWithDept(example);
	}
	
	public Page<EmployeeDto> listEmployeesPage(EmployeeVo vo)
	{
		return employeeMapper.listEmployeesPage(vo);
	}
	
	/**
	 * 员工保存
	 * @param employee
	 */
	public void saveEmp(Employee employee)
	{
		employeeMapper.insertSelective(employee);
	}

	/**
	 * 检验用户名是否可用
	 * 
	 * @param empName
	 * @return  true：代表当前姓名可用   fasle：不可用
	 */
	public boolean checkUser(String empName)
	{
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}

	/**
	 * 按照员工id查询员工
	 * @param id
	 * @return
	 */
	public Employee getEmp(Integer id)
	{
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	/**
	 * 员工更新
	 * @param employee
	 */
	public void updateEmp(Employee employee)
	{
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	/**
	 * 员工删除
	 * @param id
	 */
	public void deleteEmp(Integer id)
	{
		employeeMapper.deleteByPrimaryKey(id);
	}

	public void deleteBatch(List<Integer> ids)
	{
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		//delete from xxx where emp_id in(1,2,3)
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
	}

}
