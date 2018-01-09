package db;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by wuqiong6 on 2018/1/9.
 */
public class JdbcUtilsTest {
    @Test
    public void releaseConnection() throws Exception {
    }

    @Test
    public void getConnection() throws Exception {
        Connection connection = JdbcUtils.getConnection();
        System.out.println(connection);
    }

}