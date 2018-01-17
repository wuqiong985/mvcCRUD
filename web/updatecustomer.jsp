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
    Customer customer = (Customer) request.getAttribute("customer");
    if (msg != null){
%>
    <br>
    <font color="red" ><%= msg%></font>
<%
    }
%>
    <form action="update.do" method="post">

        <input type="hidden" name="id" value="<%= customer.getId()%>">
        <input type="hidden" name="oldName" value="<%=customer.getName()%>">

        <table>
        <tr>
             <td>CustomerName:</td>
             <td><input type="text" name="name" value="<%=customer.getName()%>">
             </td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><input type="text" name="address" value="<%=customer.getAddress()%>">
            </td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone" value="<%=customer.getPhone()%>">
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Sumbit"></td>
        </tr>
        </table>
    </form>
</body>
</html>
