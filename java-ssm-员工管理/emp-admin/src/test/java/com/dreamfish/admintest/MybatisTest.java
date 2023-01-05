package com.dreamfish.admintest;

import com.dreamfish.admin.dao.DeptMapper;
import com.dreamfish.admin.dao.EmpMapper;
import com.dreamfish.admin.entity.Department;
import com.dreamfish.admin.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author: dreamfish
 * @Description: TODO
 * @date: Created in 2022/8/5-22:22
 * @version: v1.8
 **/
@SpringJUnitConfig(locations = {"classpath:spring.xml"})
public class MybatisTest {
    @Autowired
    private EmpMapper mapper;
    @Autowired
    private DeptMapper mapper2;

    @Test
    public void test1(){
        Employee byId = mapper.getById(1);
        System.out.println(byId);
    }
    @Test
    public void test2(){
        for (Employee f : mapper.getByName("f")) {
            System.out.println(f);
        }


    }
    @Test
    public void test3(){
        for (Department department : mapper2.getAllDeptWithPart()) {
            System.out.println(department);
        }

    }
    @Test
    public void test4(){
        for (Department f : mapper2.getByName("f")) {
            System.out.println(f);
        }


    }
}
