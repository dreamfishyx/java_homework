package com.dreamfish.admin.service;

import com.dreamfish.admin.dao.DeptMapper;
import com.dreamfish.admin.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: dreamfish
 * @Description: TODO
 * @date: Created in 2022/8/6-23:42
 * @version: v1.8
 **/
@Service
public class DeptService {
    @Autowired
    private DeptMapper mapper;

    public List<Department> getAllWithPart(){
        return mapper.getAllDeptWithPart();
    }


    public void delDept(Integer id){
        try {
            mapper.delEmp(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Department> getByName(String name){
        return mapper.getByName(name);
    }

    public void addDept(Department d) {
        //防止2020-09-09,去掉多余0
        String s = d.getEstablishTime().replace("-0", "-");
        d.setEstablishTime(s);

        try {
            mapper.addDept(d);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Department getById(Integer id) {
        return mapper.getById(id);
    }

    public void updateDept(Department d){
        //防止2020-09-09,去掉多余0
        if(!"".equals(d.getEstablishTime())){
            String s = d.getEstablishTime().replace("-0", "-");
            d.setEstablishTime(s);
        }
        try {
            mapper.updateDept(d);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
