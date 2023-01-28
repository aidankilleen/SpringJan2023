<%@ page import="ie.pt.User" %>
<%@ page import="ie.pt.SqliteUserDao" %>
<%@ page import="ie.pt.UserDao" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    String confirmed = request.getParameter("confirmed");

    if (confirmed != null) {
        // do the delete
        UserDao dao = new SqliteUserDao();
        dao.deleteUser(id);
        dao.close();
        response.sendRedirect("users.jsp");
    }
    else
    {

%>
    <html>

    <%@include file="header.jsp"%>

    <body>

    <%@include file="navigation.jsp"%>

    <h2>Delete User</h2>

    <%=id%>

    Are you sure?

    <a class="btn btn-danger" href="delete_user.jsp?id=<%=id%>&confirmed=yes">Ok</a>
    <a class="btn btn-secondary" href="users.jsp">Cancel</a>

    <%@include file="scripts.jsp"%>

    </body>
    </html>
<%
    }
%>