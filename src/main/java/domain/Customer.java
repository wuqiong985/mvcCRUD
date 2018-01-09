package domain;

/**
 * 操作对象
 * Created by wuqiong6 on 2018/1/9.
 */
public class Customer {

    private Integer id;

    private String name;

    private String address;

    private String phone;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Customer() {
    }

    public Customer(Integer id, String name, String address, String phone) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
