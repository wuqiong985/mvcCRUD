package dao;

import db.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

/**
 * Created by wuqiong6 on 2018/1/9.
 * 封装了基本的 CRUD 的方法，以供子类继承使用
 * 当前 DAO 直接在方法中获取数据库连接
 * 整个DAO采取 DBUtils 解决方案
 * @param <T>:当前的 DAO 处理的实体类的类型是什么
 */
public class DAO<T> {

    private QueryRunner queryRunner = new QueryRunner();

    private Class<T> clazz;

    public DAO(){
//        //在构造器中获取clazz
//
//        Class thisClass = this.getClass();
//
//        //thisClass:CustomerDAOImpl
//        System.out.println("thisClass:"+thisClass);
//
//        //thisClass:CustomerDAOImpl
//        System.out.println("getClass:"+getClass());

        //获取带泛型参数的父类类型
        Type superClass =  getClass().getGenericSuperclass();

//        //DAO<T>
//        System.out.println(superClass);

        //判断是否实现参数类型接口
        if (superClass instanceof ParameterizedType){

            ParameterizedType parameterizedType = (ParameterizedType) superClass;

            //获取参数类型数组
            Type[] typeArgs = parameterizedType.getActualTypeArguments();

            //获取第一个泛型参数
            if (null != typeArgs && typeArgs.length > 0){
                if (typeArgs[0] instanceof  Class){
                    clazz = (Class<T>) typeArgs[0];
                }
            }
        }
    }
    /**
     * 返回某一字段的值：例如某一条记录的CustomerName
     * @param sql
     * @param args
     * @param <E>
     * @return
     */
    public <E> E getForValue(String sql,Object ... args){

        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            return (E) queryRunner.query(connection,sql,new ScalarHandler(),args);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.releaseConnection(connection);
        }
        return null;
    }

    /**
     * 增删改操作
     * @param sql：SQL 语句
     * @param args ： 填充 SQL 语句的占位符
     */
    public void update(String sql,Object ... args){
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            queryRunner.update(connection,sql,args);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.releaseConnection(connection);
        }
    }

    /**
     * 返回对应的一个实体类的对象
     * @param sql
     * @param args
     * @return
     */
    public T get(String sql,Object ... args){

        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            System.out.println(clazz);
            return queryRunner.query(connection,sql,new BeanHandler<>(clazz),args);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.releaseConnection(connection);
        }
        return null;
    }

    /**
     * 返回T对应的List
     * @param sql
     * @param args
     * @return
     */
    public List<T> getForList(String sql, Object ... args){

        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            return queryRunner.query(connection,sql, new BeanListHandler<>(clazz),args);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtils.releaseConnection(connection);
        }
        return null;
    }

}
