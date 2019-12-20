package com.atguigu.crud.dto;

import java.io.Serializable;

public class EmployeeDto implements Serializable
{
	private static final long serialVersionUID = -184009306207076712L;

	private Integer empId;

	private String empName;

	private Integer gender;

	private String email;

	private Integer deptId;
	
	private String deptName;

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

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deptId == null) ? 0 : deptId.hashCode());
		result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result + ((empName == null) ? 0 : empName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDto other = (EmployeeDto) obj;
		if (deptId == null)
		{
			if (other.deptId != null)
				return false;
		}
		else if (!deptId.equals(other.deptId))
			return false;
		if (deptName == null)
		{
			if (other.deptName != null)
				return false;
		}
		else if (!deptName.equals(other.deptName))
			return false;
		if (email == null)
		{
			if (other.email != null)
				return false;
		}
		else if (!email.equals(other.email))
			return false;
		if (empId == null)
		{
			if (other.empId != null)
				return false;
		}
		else if (!empId.equals(other.empId))
			return false;
		if (empName == null)
		{
			if (other.empName != null)
				return false;
		}
		else if (!empName.equals(other.empName))
			return false;
		if (gender == null)
		{
			if (other.gender != null)
				return false;
		}
		else if (!gender.equals(other.gender))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "EmployeeDto [empId=" + empId + "]";
	}
	
	
}
