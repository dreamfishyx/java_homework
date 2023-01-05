package test;

import dao.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

/**
 * @Description: TODO
 * @Author: dreamfish
 * @Date: 2022/12/12 23:33
 * @Version: 1.0
 **/
public class ConTest {
    @Test
    public void test1(){
        //获取连接
        try {
            Connection connection = JDBCUtils.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        //关闭连接
        Connection connection=null;
        try {
            connection = JDBCUtils.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JDBCUtils.closeConnection(connection);
    }

}
