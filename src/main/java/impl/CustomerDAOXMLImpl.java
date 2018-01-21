package impl;

import dao.CustomerDao;
import domain.CriteriaCustomer;
import domain.Customer;

import java.util.List;

public class CustomerDAOXMLImpl implements CustomerDao {
    @Override
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc) {
        System.out.println("getForListWithCriteriaCustomer");
        return null;
    }

    @Override
    public List<Customer> getAll() {
        System.out.println("getAll");
        return null;
    }

    @Override
    public void save(Customer customer) {
        System.out.println("save");
    }

    @Override
    public Customer get(Integer id) {
        System.out.println("get");
        return null;
    }

    @Override
    public void delete(Integer id) {
        System.out.println("delete");
    }

    @Override
    public long getCountWithName(String name) {
        System.out.println("getCountWithName");
        return 11;
    }


    @Override
    public void update(Customer customer) {
        System.out.println("update");
    }
}
