package dao;

import entity.Student;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 实现具体增删改查
 * @Author: dreamfish
 * @Date: 2022/12/12 22:53
 * @Version: 1.0
 **/
public class StudentDaoImpl extends BaseDao<Student> implements StudentDao {
    public StudentDaoImpl() {
        //设置Student类与数据库表映射关系
        HashMap<String, String> map = new HashMap<>();//(表字段,类字段)
        map.put("uid","uid");
        map.put("name","name");
        map.put("phone_num","phoneNumber");
        map.put("native_place","nativePlace");
        map.put("age","age");
        map.put("stu_num","stuNumber");
        setProcessor(map);
    }

    @Override
    public Student getStudentByNum(Connection conn, String num) {
        String sql="select uid ,name,phone_num ,native_place ,age,stu_num" +
                    " from  student_info where is_delete=0 and stu_num= ?";
        return getEntity(conn,sql,num);
    }

    @Override
    public List<Student> getAll(Connection conn) {
        String sql="select uid ,name,phone_num,native_place,age,stu_num " +
                " from  student_info where is_delete=0";
        return getEntities(conn,sql);
    }

    @Override
    public int insert(Connection conn, Student student) {
        String sql="insert into student_info(uid ,name,phone_num,native_place,age,stu_num )" +
                "values(?,?,?,?,?,?)";
        return update(conn,sql,student.getUid(),student.getName(),student.getPhoneNumber(),student.getNativePlace(),student.getAge(),student.getStuNumber());
    }

    @Override
    public int delete(Connection conn, int uid) {
        String sql="update student_info set is_delete=1 where uid=?";
        return update(conn,sql,uid);
    }

    @Override
    public int updateInfo(Connection conn, Student student) {
        String sql="update student_info set name=?,phone_num=?,native_place=?,age=?,stu_num=?  where uid=?";
        return update(conn,sql,student.getName(),student.getPhoneNumber(),student.getNativePlace(),student.getAge(),student.getStuNumber(),student.getUid());
    }

    @Override
    public Student getById(Connection conn, int uid) {
        String sql="select uid ,name,phone_num ,native_place ,age,stu_num" +
                " from  student_info where is_delete=0 and uid= ?";
        return getEntity(conn,sql,uid);
    }
}
