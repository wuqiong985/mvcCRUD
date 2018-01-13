package servlet;

import dao.CustomerDao;
import domain.CriteriaCustomer;
import domain.Customer;
import impl.CustomerDAOJdbcImpl;

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

    private CustomerDao customerDao = new CustomerDAOJdbcImpl();


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

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("add");
    }

    private void edit(HttpServletRequest req,HttpServletResponse resp){
        System.out.println("Edit function");
    }


}
