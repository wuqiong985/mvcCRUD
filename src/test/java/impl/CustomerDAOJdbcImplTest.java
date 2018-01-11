package impl;

import dao.CustomerDao;
import domain.Customer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wuqiong6 on 2018/1/9.
 */
public class CustomerDAOJdbcImplTest {

    private CustomerDao customerDao = new CustomerDAOJdbcImpl();

    @Test
    public void getAll() throws Exception {
        List<Customer> customers = customerDao.getAll();
        System.out.println(customers);
    }

    @Test
    public void save() throws Exception {
       Customer customer = new Customer();
       customer.setName("Tom");
       customer.setAddress("HeNan");
       customer.setPhone("13182467741");
       customerDao.save(customer);
    }

    @Test
    public void get() throws Exception {
        Customer customer = customerDao.get(2);
        System.out.println(customer);
    }

    @Test
    public void delete() throws Exception {
        customerDao.delete(1);

    }

    @Test
    public void getCountWithName() throws Exception {
        long count = customerDao.getCountWithName("ABC");
        System.out.println(count);
        count = customerDao.getCountWithName("Jerry");
        System.out.println(count);
    }

}