<%@ page import="ie.pt.User" %>
<%@ page import="ie.pt.SqliteUserDao" %>
<%@ page import="ie.pt.UserDao" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDao dao = new SqliteUserDao();
    List<User> users = dao.getUsers();
    dao.close();
%>

<html>

<%@include file="header.jsp"%>

<body>


<%@include file="navigation.jsp"%>


<h2>Index</h2>

<table class="table">
    <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Active</th>
            <th>&nbsp;</th>
        </tr>
    </thead>
    <tbody>
        <%
            for (User u : users) {
        %>
                <tr>
                    <td>
                        <a href="users_detail.jsp?id=<%=u.getId()%>">
                            <%=u.getId()%>
                        </a>
                    </td>
                    <td><%=u.getName()%></td>
                    <td><%=u.getEmail()%></td>
                    <td><%=u.isActive() ? "Active" : "Inactive" %></td>
                    <td>
                        <a class="btn btn-danger" href="delete_user.jsp?id=<%=u.getId()%>">Delete</a>
                        <a class="btn btn-primary" href="update_user.jsp?id=<%=u.getId()%>">Edit</a>
                    </td>
                </tr>
        <%
            }
        %>
    </tbody>
</table>

<%@include file="scripts.jsp"%>

</body>
</html>
