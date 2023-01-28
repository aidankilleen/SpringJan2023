<%@ page import="ie.pt.User" %>
<%@ page import="ie.pt.SqliteUserDao" %>
<%@ page import="ie.pt.UserDao" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%


    int id = Integer.parseInt(request.getParameter("id"));

    UserDao dao = new SqliteUserDao();


    User user = dao.getUser(id);

    String submit = request.getParameter("submit");

    if (submit != null) {

        if (submit.equals("Cancel")) {
            response.sendRedirect("users.jsp");
        } else {

            // do the update

            String name = request.getParameter("name");
            String email = request.getParameter("email");
            boolean active = request.getParameter("chkactive") != null;
            User newUser = new User(id, name, email, active);

            dao.updateUser(newUser);
            dao.close();

            response.sendRedirect("users.jsp");

        }
    }
%>

<html>
<%@include file="header.jsp"%>
<body>
<%@include file="navigation.jsp"%>

<h2>Update User</h2>
<form method="post" action="update_user.jsp">

    <input type="hidden" name="id" value="<%=id%>"/>

    <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="your name" value="<%=user.getName()%>">
    </div>
    <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" name="email" placeholder="name@gmail.com" value="<%=user.getEmail()%>">
    </div>
    <div class="form-check">
        <input class="form-check-input" type="checkbox" id="chkactive" name="chkactive" <%=user.isActive() ? "checked" : ""%>>
        <label class="form-check-label" for="chkactive">
            Active
        </label>
    </div>

    <input type="submit" name="submit" value="Save" class="btn btn-success"/>
    <input type="submit" name="submit" value="Cancel" class="btn btn-secondary"/>
</form>



<%@include file="scripts.jsp"%>

</body>
</html>
