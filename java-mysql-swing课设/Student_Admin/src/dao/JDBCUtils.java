package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description: TODO
 * @Author: dreamfish
 * @Date: 2022/12/12 23:27
 * @Version: 1.0
 **/
public class JDBCUtils {
    private static Connection conn=null;//存储数据库连接
    /**
     *@description:
     *@param: 获取数据库连接(有异常抛出)
     *@return: java.sql.Connection
     **/
    public static Connection getConnection() throws Exception {
        if(conn==null){
            //读取配置文件中的数据库基本信息
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

            Properties pros = new Properties();
            pros.load(is);

            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String url = pros.getProperty("url");
            String driverClass = pros.getProperty("driverClass");

            //加载驱动
            Class.forName(driverClass);
            // 获取并保存连接
            conn=DriverManager.getConnection(url, user, password);
        }
        return conn;
    }
    /**
     *@description: 关闭数据库连接
     *@param: java.sql.Connection
     *@return: null
     **/
    public static void closeConnection(Connection con){

        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn=null;
    }
}
