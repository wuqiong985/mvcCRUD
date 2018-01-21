package servlet;

import dao.CustomerDao;
import dao.factory.CustomerDaoFactory;
import domain.CriteriaCustomer;
import domain.Customer;
import impl.CustomerDAOJdbcImpl;
import impl.CustomerDAOXMLImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by wuqiong6 on 2018/1/11.
 */
@WebServlet("*.do")
public class CustomerServlet extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String method = req.getParameter("method");
//
//        switch (method){
//            case "add":     add(req,resp); break;
//            case "query":   query(req,resp); break;
//            case "delete":  delete(req,resp); break;
//        }
//    }

//    private CustomerDao customerDao = new CustomerDAOJdbcImpl();

    //通过工厂类获取CustomerDAO实现类
    private CustomerDao customerDao = CustomerDaoFactory.getInstance().getCustomerDao();
//    private CustomerDao customerDao = new CustomerDAOXMLImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();

        //返回请求的servlet名，也就是方法.do
        System.out.println(servletPath);

        String tempPath = servletPath.substring(1);
        //截取方法名
        String methodName = tempPath.substring(0,servletPath.indexOf(".")-1);
        System.out.println(methodName);

        //执行方法
        try {
            Method method = getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (Exception e) {
            resp.sendRedirect("Error.jsp");
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("delete");
        int id = 0;

        //防止id不能转为int，如不能转，ID = 0
        try{
            id = Integer.parseInt(req.getParameter("id"));
            customerDao.delete(id);
        } catch (Exception e){

        }

        resp.sendRedirect("query.do");
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("query");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");

        CriteriaCustomer cc = new CriteriaCustomer(name,address,phone);

//        //1.获取所有customers
//        List<Customer> customers = customerDao.getAll();

        List<Customer> customers = customerDao.getForListWithCriteriaCustomer(cc);

        //2.添加customers到attribute
        req.setAttribute("customers",customers);

        //3.转发
        req.getRequestDispatcher("/index.jsp").forward(req,resp);

        System.out.println("query");
    }

    private void addCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //1.获取表单参数
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");

        //2:检验名字是否已经使用
        long count = customerDao.getCountWithName(name);

        //2.1若返回值大于0，则转发响应newcustomer.jsp页面，
        //   在页面上显示：用户名name已经存在，请换一个！
        if (count > 0) {
            //2.1.1:在request中放入一个属性message：用户名已经存在，请换一个！
            //      在页面上通过request.getAttribue("message")的方法来显示
            req.setAttribute("message", "用户名" + name + "已经存在，请换一个！");

            //2.1.2：表单数据可以回显，即原来输入的东西还是可以显示
            //      value="<%=request.getParameter("name") == null ? "":request.getParameter("name")%>"

            req.getRequestDispatcher("/newcustomer.jsp").forward(req,resp);
            //2.1.3:结束方法：return
            return;
        }

        //3.若验证通过：封装要添加的customer对象并保存
        Customer customer = new Customer(name,address,phone);
        customerDao.save(customer);

        //4.重定向到success.jsp页面,使用重定向可以避免出现表单重复提交的问题
        resp.sendRedirect("success.jsp");

        System.out.println("add");

    }

    private void edit(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String forwardPath = "Error.jsp";

        //1.获取请求参数
        String idStr = req.getParameter("id");

        try {
            Customer customer = customerDao.get(Integer.parseInt(idStr));
            if (customer != null){
                forwardPath = "/updatecustomer.jsp";
                //2.存入customer属性
                req.setAttribute("customer",customer);
            }
        } catch (NumberFormatException e){
        }
        //3.响应
        req.getRequestDispatcher(forwardPath).forward(req,resp);


        System.out.println("Edit function");
    }

    private void update(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        //1.获取表单参数:id,name,address,phone,oldName
        int id = Integer.parseInt(req.getParameter("id"));
        String oldName = req.getParameter("oldName");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String name = req.getParameter("name");

        System.out.println(oldName+address+phone+name);

        //2.检验name是否已经被占用
        //2.1比较name和oldName是否相同，若相同则说明name可以用
        if (!oldName.equalsIgnoreCase(name)){
            //2.2若不相同，则调用CustomerDao的getCountWithName(String name) 获取name在数据库中是否存在
            long count = customerDao.getCountWithName(name);

            //3.若返回值大于0，则响应updatecustomer.jsp，转发响应
            if (count > 0){

                //3.1:在updatecustomer.jsp中放入一个属性message：用户名已经存在，请换一个！
                //      在页面上通过request.getAttribue("message")的方法来显示
                req.setAttribute("message","用户名"+name+"已经存在，请重新选择！");

                //3.1.2：表单数据可以回显，即原来输入的东西还是可以显示
                //      address,phone 显示表单提交的新的值，name显示oldName ，而不是新提交的name

                req.getRequestDispatcher("/updatecustomer.jsp").forward(req,resp);

                //3.1.3:结束方法：return
                return;
            }
        }

        //4.若验证通过：封装要添加的customer对象并保存
        Customer customer = new Customer(name,address,phone);
        customer.setId(id);

        customerDao.update(customer);

        //5.重定向到success.jsp页面,使用重定向可以避免出现表单重复提交的问题
        resp.sendRedirect("query.do");

    }

}
