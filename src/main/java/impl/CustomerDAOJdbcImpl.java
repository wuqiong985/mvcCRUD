package impl;

import dao.CustomerDao;
import dao.DAO;
import domain.CriteriaCustomer;
import domain.Customer;

import java.util.List;

/**
 * Created by wuqiong6 on 2018/1/9.
 */
public class CustomerDAOJdbcImpl  extends DAO<Customer> implements CustomerDao {

    //模糊查询
    @Override
    public List<Customer> getForListWithCritieriaCustomer(CriteriaCustomer cc) {
        String sql = "select id,name,address,phone from customers where name like ? and address like ? and phone like ?";
        //此时的get方法已经被处理过，详情见CriteriaCustomer的get方法
        return getForList(sql,cc.getName(), cc.getAddress(), cc.getPhone());
    }



    public List<Customer> getAll() {
        String sql  = "select id,name,address,phone from customers";
        return getForList(sql);
    }

    public void save(Customer customer) {
        String sql = "insert into customers(name,address,phone) values (?,?,?)";
        update(sql,customer.getName(),customer.getAddress(),customer.getPhone());
    }

    public Customer get(Integer id) {
        String sql = "select id,name,address,phone from customers where id = ?";
        return get(sql,id);
    }


    public void delete(Integer id) {
        String sql = "delete from customers where id = ?";
        update(sql,id);
    }

    public long getCountWithName(String name) {
        String sql  = "select count(id) from customers where name = ?";
        return getForValue(sql,name);
    }

    public void hello(){
        System.out.println("hello");
    }
}
