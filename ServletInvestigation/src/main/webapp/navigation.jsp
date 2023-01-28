<%--
  Created by IntelliJ IDEA.
  User: HP201802
  Date: 12/01/2023
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String filename = this.getClass().getSimpleName();
%>
<nav class="navbar navbar-dark bg-dark navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">User Manager</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link <%=filename.equals("index_jsp") ? "active" : ""%>" aria-current="page" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%=filename.equals("about_jsp") ? "active" : ""%>" href="about.jsp">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%=filename.equals("contact_jsp") ? "active" : ""%>" href="contact.jsp">Contact</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle <%=filename.startsWith("users") ? "active" : ""%>" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Users
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="users.jsp">List</a></li>
                        <li><a class="dropdown-item" href="add_user.jsp">Add User</a></li>
                        <li><hr class="dropdown-divider"></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
