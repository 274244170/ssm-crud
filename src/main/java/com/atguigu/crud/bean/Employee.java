package com.atguigu.crud.bean;

import javax.validation.constraints.Pattern;

public class Employee
{
	private Integer empId;

	@Pattern(regexp = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})", message = "用户名必须是2-5位中文或者6-16位英文和数字的组合")
	private String empName;

	private Integer gender;

	@Pattern(regexp = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$", message = "邮箱格式不正确")
	private String email;

	private Integer deptId;

	//希望查询员工的同时部门信息也是查询好的
	private Department department;

	public Employee()
	{
		super();
	}

	public Employee(Integer empId, String empName, Integer gender, String email, Integer deptId)
	{
		super();
		this.empId = empId;
		this.empName = empName;
		this.gender = gender;
		this.email = email;
		this.deptId = deptId;
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
		this.empName = empName == null ? null : empName.trim();
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
		this.email = email == null ? null : email.trim();
	}

	public Integer getDeptId()
	{
		return deptId;
	}

	public void setDeptId(Integer deptId)
	{
		this.deptId = deptId;
	}

	public Department getDepartment()
	{
		return department;
	}

	public void setDepartment(Department department)
	{
		this.department = department;
	}

	@Override
	public String toString()
	{
		return "Employee [empId=" + empId + ", empName=" + empName + ", gender=" + gender + ", email=" + email
		        + ", deptId=" + deptId + "]";
	}

}