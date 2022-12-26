package dao;

import entity.Student;
import java.sql.Connection;
import java.util.List;

/**
 * @Description: 接口类，定义规范
 * @Author: dreamfish
 * @Date: 2022/12/12 22:45
 * @Version: 1.0
 **/
public interface StudentDao {
    /**
     *@description: 按照姓名查找
     *@param:
     *@return: entity.Student
     **/
    Student getStudentByNum(Connection conn,String name);
    /**
     *@description: 获取所有学生
     *@param:
     *@return: java.util.List<entity.Student>
     **/
    List<Student> getAll(Connection conn);
    /**
     *@description: 插入、新增
     *@param:
     *@return: int
     **/
    int insert(Connection conn,Student student);
    /**
     *@description: 删除学生
     *@param:
     *@return: int
     **/
    int delete(Connection conn,int uid);
    /**
     *@description: 修改学生信息
     *@param:
     *@return: int
     **/
    int updateInfo(Connection conn,Student student);
    /**
     *@description: 通过uid获取学生
     *@param:
     *@return:
     **/
    Student getById(Connection conn,int uid);
}
