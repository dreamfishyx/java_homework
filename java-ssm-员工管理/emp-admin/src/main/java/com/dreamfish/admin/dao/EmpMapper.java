package com.dreamfish.admin.dao;

import com.dreamfish.admin.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: dreamfish
 * @Description: TODO
 * @date: Created in 2022/8/5-20:18
 * @version: v1.8
 **/
public interface EmpMapper {

    Employee getById(@Param("empId")Integer id);

    List<Employee> getAllEmp();

    //通过名称模糊搜索
    List<Employee> getByName(@Param("name")String name);

    //通过部门id获取员工数目
    int getCount(@Param("id")int id);

    void delEmp(@Param("id")Integer id);

    void addEmp(Employee e);

    void updateEmp(Employee e);
}
