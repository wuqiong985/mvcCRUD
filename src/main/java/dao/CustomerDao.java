package dao;

import domain.CriteriaCustomer;
import domain.Customer;

import java.util.List;

/**
 * Created by wuqiong6 on 2018/1/9.
 */
public interface CustomerDao {

    public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc);

    public List<Customer> getAll();

    public void save(Customer customer);

    public Customer get(Integer id);

    public void delete(Integer id);

    /**
     * 返回和 name 相等的记录数
     * @param name
     * @return
     */
    public long getCountWithName(String name);

    public void update(Customer customer);
}
