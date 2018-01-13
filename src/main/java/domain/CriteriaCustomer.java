package domain;

import com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV;

/**
 * Created by wuqiong6 on 2018/1/11.
 *
 * 用于模糊查询对象的封装
 *
 * 因为查询条件很多时候和domain类并不相同，所以要做成一个单独的类
 *
 */
public class CriteriaCustomer {

    private String name;

    private String address;

    private String phone;

    @Override
    public String toString() {
        return "CriteriaCustomer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
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

    public String getName() {
        if (name == null){
            name = "%%";
        } else {
            name = "%"+name+"%";
        }
        return name;
    }

    public String getAddress() {
        if (address == null){
            address ="%%";
        } else {
            address = "%"+address+"%";
        }
        return address;
    }

    public String getPhone() {
        if (phone == null){
            phone ="%%";
        } else {
            phone = "%"+phone+"%";
        }
        return phone;
    }

    public CriteriaCustomer() {

    }

    public CriteriaCustomer(String name, String address, String phone) {

        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
