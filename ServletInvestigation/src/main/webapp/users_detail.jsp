<%@ page import="ie.pt.User" %>
<%@ page import="ie.pt.SqliteUserDao" %>
<%@ page import="ie.pt.UserDao" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String sId = request.getParameter("id");
    int id= Integer.parseInt(sId);
    UserDao dao = new SqliteUserDao();
    User user = dao.getUser(id);
    dao.close();
%>

<html>

<%@include file="header.jsp"%>

<body>


<%@include file="navigation.jsp"%>


<h2>User Detail</h2>

<table class="table">
    <tbody>
        <tr>
            <td>Id</td><td><%=user.getId()%></td>
        </tr>
        <tr>
            <td>Name</td><td><%=user.getName()%></td>
        </tr>
        <tr>
            <td>Email</td><td><%=user.getEmail()%></td>
        </tr>
        <tr>
            <td>Active</td><td><%=user.isActive() ? "Active" : "Inactive"%></td>
        </tr>
    </tbody>
</table>

<%@include file="scripts.jsp"%>

</body>
</html>
