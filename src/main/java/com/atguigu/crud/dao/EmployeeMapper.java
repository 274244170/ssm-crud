package com.atguigu.crud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crud.dto.EmployeeDto;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.EmployeeExample;
import com.atguigu.crud.vo.EmployeeVo;
import com.github.pagehelper.Page;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);
    
    List<Employee> selectByExampleWithDept(EmployeeExample example);
    
    Employee selectByPrimaryKeyWithDept(Integer empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
    
    /**
     * 用户信息分页查询方法
     * @return
     */
    Page<EmployeeDto> listEmployeesPage(EmployeeVo vo);
}