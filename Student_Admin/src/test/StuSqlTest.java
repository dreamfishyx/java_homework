package test;

import dao.JDBCUtils;
import dao.StudentDaoImpl;
import entity.Student;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

/**
 * @Description: TODO
 * @Author: dreamfish
 * @Date: 2022/12/12 23:49
 * @Version: 1.0
 **/
public class StuSqlTest {
    private StudentDaoImpl impl=new StudentDaoImpl();
    private Connection conn=getCon();
    public Connection getCon(){
        Connection connection=null;
        try {
            connection = JDBCUtils.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Test
    public void test1(){
        //单个查
        Student student = impl.getStudentByNum(conn, "2123342");
        System.out.println(student);
    }
    @Test
    public void test2(){
        //添加
        impl.insert(conn,new Student("2123342","阿拉斯加",18,"1872088733","北京"));
        impl.insert(conn,new Student("2123349","南极洲",18,"18720887666","海南"));
        int insert = impl.insert(conn, new Student("2123347", "北冰洋", 18, "18720887777", "北京"));
        System.out.println(insert);
    }
    @Test
    public void test3(){
        //全部查
        for (Student student : impl.getAll(conn)) {
            System.out.println(student);
        }

    }
    @Test
    public void test4(){
        //单个改
        Student student = impl.getStudentByNum(conn, "2123342");
        student.setName("阿拉斯加帝企鹅");
        int i = impl.updateInfo(conn, student);
        System.out.println(i);

    }
    @Test
    public void test5(){
        //删除
        Student student = impl.getStudentByNum(conn, "2123347");
        int delete = impl.delete(conn, student.getUid());
        System.out.println(delete);
    }


}
