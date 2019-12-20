package com.atguigu.crud.vo;

import java.io.Serializable;

import com.atguigu.crud.dto.EmployeeDto;

public class EmployeeVo implements Serializable
{
	private static final long serialVersionUID = -8660744974121772593L;

	private Integer pageNum;
	
	private Integer pageSize;
	
	private Integer empId;

	private String empName;

	private Integer gender;
	
	private String genderName;

	private String email;

	private Integer deptId;
	
	private String deptName;
	
	private String sort;
	
	public EmployeeVo()
	{
		super();
	}

	public EmployeeVo(EmployeeDto employeeDto)
	{
		super();
		this.empId = employeeDto.getEmpId();
		this.empName = employeeDto.getEmpName();
		this.email = employeeDto.getEmail();
		this.deptName = employeeDto.getDeptName();
	}

	public Integer getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(Integer pageNum)
	{
		this.pageNum = pageNum;
	}

	public Integer getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	public Integer getEmpId()
	{
		return empId;
	}

	public void setEmpId(Integer empId)
	{
		this.empId = empId;
	}

	public String getEmpName()
	{
		return empName;
	}

	public void setEmpName(String empName)
	{
		this.empName = empName;
	}

	public Integer getGender()
	{
		return gender;
	}

	public void setGender(Integer gender)
	{
		this.gender = gender;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Integer getDeptId()
	{
		return deptId;
	}

	public void setDeptId(Integer deptId)
	{
		this.deptId = deptId;
	}

	public String getGenderName()
	{
		return genderName;
	}

	public void setGenderName(String genderName)
	{
		this.genderName = genderName;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	public String getSort()
	{
		return sort;
	}

	public void setSort(String sort)
	{
		this.sort = sort;
	}

}
