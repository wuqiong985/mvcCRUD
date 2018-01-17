<%@ page import="domain.Customer" %><%--
  Created by IntelliJ IDEA.
  User: wuqiong6
  Date: 2018/1/17
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>

<%
    Object msg =request.getAttribute("message");
    String id = null;
    String name = null;
    String oldName = null;
    String address = null;
    String phone = null;

    Customer customer = (Customer) request.getAttribute("customer");

//    根据customer的属性来源决定怎么设置属性值
    //实现数值回显
    if (customer != null){
        id = customer.getId()+"";
        name = customer.getName();
        oldName = customer.getName();
        address = customer.getAddress();
        phone = customer.getPhone();
    } else {
        id = request.getParameter("id");
        oldName = request.getParameter("oldName");
        name = request.getParameter("oldName");
        address = request.getParameter("address");
        phone = request.getParameter("phone");
    }
    if (msg != null){
%>
    <br>
    <font color="red" ><%= msg%></font>
<%
    }
%>
    <form action="update.do" method="post">

        <input type="hidden" name="id" value="<%= id%>">
        <input type="hidden" name="oldName" value="<%=oldName%>">

        <table>
        <tr>
             <td>CustomerName:</td>
             <td><input type="text" name="name" value="<%=name%>">
             </td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><input type="text" name="address" value="<%=address%>">
            </td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone" value="<%=phone%>">
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Sumbit"></td>
        </tr>
        </table>
    </form>
</body>
</html>
