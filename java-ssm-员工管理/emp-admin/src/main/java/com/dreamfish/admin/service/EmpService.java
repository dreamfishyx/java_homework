package com.dreamfish.admin.service;

import com.dreamfish.admin.dao.EmpMapper;
import com.dreamfish.admin.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: dreamfish
 * @Description: TODO
 * @date: Created in 2022/8/6-16:15
 * @version: v1.8
 **/
@Service
public class EmpService {

    @Autowired
    private EmpMapper mapper;


    public void delEmp(Integer id){
        try {
            mapper.delEmp(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Employee> getByName(String name){
        return mapper.getByName(name);
    }

    public  void  addEmp(Employee e){
        //防止2020-09-09,去掉多余0
        String s = e.getHireDate().replace("-0", "-");
        e.setHireDate(s);

        try {
            mapper.addEmp(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public Employee getById(Integer id){
        return mapper.getById(id);
    }

    public void updateEmp(Employee e){
        //防止2020-09-09,去掉多余0
        if(!"".equals(e.getHireDate())){
            String s = e.getHireDate().replace("-0", "-");
            e.setHireDate(s);
        }

        try {
            mapper.updateEmp(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
