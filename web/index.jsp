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

  <script type="text/javascript" src="scripts/jquery-1.6.4.js"></script>

  <script>
    <%-- 弹出对话框 --%>
    $(function () {
        $(".delete").click(function () {
//            获取要删除的人的姓名
            var content = $(this).parent().parent().find("td:eq(1)").text();
            var flag = confirm("确定要删除"+content+"的信息吗");

            return flag;
        })
    })
  </script>

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
          <td><input type="submit" value="Query" ></td>
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
            <td><a href=""> Update </a></td>
            <td><a href="delete.do?id=<%=customer.getId()%>" class="delete"> Delete</a></td>
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
