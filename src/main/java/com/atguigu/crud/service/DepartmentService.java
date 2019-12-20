package com.atguigu.crud.service;

import java.util.List;

import com.atguigu.crud.model.Department;

public interface DepartmentService
{
	/**
	 * 查询部门信息
	 * @return
	 *
	 */
	public List<Department> getDepts();
}
