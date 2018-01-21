package dao.factory;

import dao.CustomerDao;
import impl.CustomerDAOJdbcImpl;
import impl.CustomerDAOXMLImpl;

import java.util.HashMap;
import java.util.Map;

public class CustomerDaoFactory {

    private Map<String,CustomerDao> daos = new HashMap<>();

    private static CustomerDaoFactory instance = new CustomerDaoFactory();

    public static CustomerDaoFactory getInstance() {
        return instance;
    }

    private String type = null;

    public  void setType(String type) {
        this.type = type;
    }

    private CustomerDaoFactory(){
        daos.put("jdbc",new CustomerDAOJdbcImpl());
        daos.put("xml",new CustomerDAOXMLImpl());
    }

    public CustomerDao getCustomerDao(){
        return daos.get(type);
    }
}
