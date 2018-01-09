package db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Jdbc 操作的工具类
 * Created by wuqiong6 on 2018/1/9.
 */
public class JdbcUtils {

    private static DataSource dataSource = null;

    static {
        //数据源只能被创建一次
        dataSource = new ComboPooledDataSource("mvcCRUD");
    }


    /**
     * 释放连接
     * @param connection
     */
    public static void releaseConnection(Connection connection){
        try{
            if (connection != null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 返回数据源的一个 Connection 对象
     * @return
     */
    public static Connection getConnection() throws SQLException {
       return dataSource.getConnection();
    }
}
