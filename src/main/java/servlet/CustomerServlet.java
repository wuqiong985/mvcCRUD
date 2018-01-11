package servlet;

import dao.CustomerDao;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();

        //返回请求的servlet名，也就是方法.do
        System.out.println(servletPath);

        //截取方法名
        String methodName = servletPath.substring(1).substring(0,servletPath.length()-4);
        System.out.println(methodName);

        //执行方法
        try {
            Method method = getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (Exception e) {
            resp.sendRedirect("Error.jsp");
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("delete");
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取所有customers
        List<Customer> customers = customerDao.getAll();

        //2.添加customers到attribute
        req.setAttribute("customers",customers);

        req.getRequestDispatcher("/index.jsp").forward(req,resp);

        System.out.println("query");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("add");
    }

    private void edit(HttpServletRequest req,HttpServletResponse resp){
        System.out.println("Edit function");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
