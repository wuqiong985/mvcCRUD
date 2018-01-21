package servlet;

import dao.factory.CustomerDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * load-on-startup : 1
 * web应用一启动，InitServlet就被创建，并由容器调用servlet方法
 * servlet init() 时，从属性文件获取type，并将type赋给处理的servlet
 */
public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        //默认jdbc处理方式
        CustomerDaoFactory.getInstance().setType("jdbc");

        //读取属性文件
        InputStream in = getServletContext().getResourceAsStream("/WEB-INF/type.properties");
        Properties properties = new Properties();

        try {
            properties.load(in);
            //得到type属性
            String type = properties.getProperty("type");
            CustomerDaoFactory.getInstance().setType(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
