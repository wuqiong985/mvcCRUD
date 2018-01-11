<%@ page import="java.util.List" %>
<%@ page import="domain.Customer" %>
<%--
  Created by IntelliJ IDEA.
  User: wuqiong6
  Date: 2018/1/9
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <form action="query.do" method="post">
      <table >
        <tr>
          <td>CustomerName:</td>
          <td><input type="text" name="name"></td>
        </tr>
        <tr>
          <td>Address:</td>
          <td><input type="text" name="address"></td>
        </tr>
        <tr>
          <td>Phone:</td>
          <td><input type="text" name="phone"></td>
        </tr>
        <tr>
          <td><input type="submit" value="Query"></td>
          <td> <a href="" >Add new customer</a> </td>
        </tr>
      </table>
    </form>

    <br><br>
    <%
      List<Customer> customers = (List<Customer>) request.getAttribute("customers");
      if (null != customers && customers.size() > 0){
    %>
    <br><br>
    <hr>
        <table border="1" cellspacing="0" cellpadding="10">
          <tr>
            <td>Id</td>
            <td>CustomerName</td>
            <td>Address</td>
            <td>Phone</td>
            <td>Update</td>
            <td>Delete</td>
          </tr>

          <%
            for (Customer customer:customers){
          %>
          <tr>
            <td><%=customer.getId()%></td>
            <td><%=customer.getName()%></td>
            <td><%=customer.getAddress()%></td>
            <td><%=customer.getPhone()%></td>
            <td><a href="delete.do?id=<%=customer.getId()%>"> Update </a></td>
            <td><a href=""> Delete</a></td>
          </tr>
          <%
            }
          %>
        </table>
    <%
      }
    %>


  </body>
</html>
