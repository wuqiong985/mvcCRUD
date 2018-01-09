package impl;

import dao.CustomerDao;
import dao.DAO;
import domain.Customer;

import java.util.List;

/**
 * Created by wuqiong6 on 2018/1/9.
 */
public class CustomerDAOJdbcImpl  extends DAO<Customer> implements CustomerDao {
    public List<Customer> getAll() {
        return null;
    }

    public void save(Customer customer) {

    }

    public Customer get(Integer id) {
        return super.get(null,null);
    }


    public void delete(Integer id) {

    }

    public long getCountWithName(String name) {
        return 0;
    }
}
