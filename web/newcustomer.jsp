<%--
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
    if (msg != null){
%>
    <br>
    <font color="red" ><%= msg%></font>
<%
    }
%>
    <form action="addCustomer.do" method="post">
        <table>
        <tr>
             <td>CustomerName:</td>
             <td><input type="text" name="name" value="<%=request.getParameter("name") == null? "" : request.getParameter("name")%>">
             </td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><input type="text" name="address" value="<%=request.getParameter("address") == null? "" : request.getParameter("address")%>">
            </td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone" value="<%=request.getParameter("phone") == null? "" : request.getParameter("phone")%>">
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Sumbit"></td>
        </tr>
        </table>
    </form>
</body>
</html>
