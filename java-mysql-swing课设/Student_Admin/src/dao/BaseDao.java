package dao;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Description: BaseDAO为抽象类,借助阿里的dbutil工具包实现广泛增删改查
 * @Author: dreamfish
 * @Date: 2022/12/1 22:30
 * @Version: 1.0
 **/
public abstract class BaseDao<T> {
    private QueryRunner  queryRunner=new QueryRunner(); //使用dbutil工具包
    private Class<T> type;  //对象封装类型

    private RowProcessor processor=null;
    /**
     *@description: 通过反射初始化对象封装类型
     *@param:
     *@return: null
     **/
    public BaseDao(){
        Class clazz= this.getClass();   //获取子类类型(当前类不会实例化)
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();//获取父类泛型类型

        Type[] types = parameterizedType.getActualTypeArguments();//获取泛型类型
        this.type=(Class<T>) types[0];
    }
    /**
     *@description: 通用增删改
     *@param:
     *@return: int-影响数据条数
     **/
    public int update(Connection conn,String sql,Object... params){
        int count=0;//记录影响条数
        try {
            count = queryRunner.update(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    /**
     *@description: 查询，返回单个对象
     *@param:
     *@return: T
     **/
    public T getEntity(Connection conn,String sql,Object...params){
        T t=null;
        try {
            if (processor!=null)
                t=queryRunner.query(conn,sql,new BeanHandler<>(type,processor),params);
            else
                t=queryRunner.query(conn,sql,new BeanHandler<>(type),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }
    /**
     *@description: 查询，返回list集合
     *@param:
     *@return: List<T>
     **/
    public List<T> getEntities(Connection conn, String sql, Object...params){
        List<T> list=null;
        try {
            if(processor!=null)
                list = queryRunner.query(conn, sql, new BeanListHandler<>(type,processor), params);
            else
                list = queryRunner.query(conn, sql, new BeanListHandler<>(type), params);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *@description: 设置表字段与实体类映射关系(不好用)
     *@param: java.util.Map
     *@return:
     **/
    public void setProcessor(Map<String,String> map) {
        BeanProcessor beanProcessor = new BeanProcessor(map);
        this.processor = new BasicRowProcessor(beanProcessor);
    }
}
