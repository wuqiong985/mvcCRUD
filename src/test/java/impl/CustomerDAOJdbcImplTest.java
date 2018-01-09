package impl;

import dao.CustomerDao;
import domain.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wuqiong6 on 2018/1/9.
 */
public class CustomerDAOJdbcImplTest {

    private CustomerDao customerDao = new CustomerDAOJdbcImpl();
    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void getInteger() throws Exception {
        Customer cust = customerDao.get(1);
        System.out.println(cust);
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void getCountWithName() throws Exception {
    }

}