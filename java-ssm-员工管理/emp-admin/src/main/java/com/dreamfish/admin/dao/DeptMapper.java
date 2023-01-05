package com.dreamfish.admin.dao;

import com.dreamfish.admin.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: dreamfish
 * @Description: TODO
 * @date: Created in 2022/8/5-22:12
 * @version: v1.8
 **/
public interface DeptMapper {

    List<Department> getAllDeptWithPart();
//    List<Department> getAllDeptWithAll();
    void delEmp(@Param("id")Integer id);

    //通过名称模糊搜索
    List<Department> getByName(@Param("name")String name);

    void addDept(Department d);

    Department getById(@Param("deptId") Integer id);

    void updateDept(Department e);
}
