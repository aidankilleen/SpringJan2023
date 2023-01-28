<%@ page import="ie.pt.TestClass" %>
<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<style>
    h1 {
        color: red;
    }
</style>
<body>
<h1>Hello World!</h1>

<%
    for (int i=1; i<=6; i++) {

        out.println("<h" + i + ">Is this working?</h" + i + ">");
    }

    TestClass tc = new TestClass();
    out.println(tc.getMessage());

    %>


    <hr>

    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Email</th>
                <th>Active</th>
            </tr>
        </thead>
        <tbody>
        <%
            // can we connect to a db??

            // need to load the JDBC driver into memory
            Class.forName("org.sqlite.JDBC");


            String connectionString = "jdbc:sqlite:C:/data/8westjava/users.db";
            Connection conn = DriverManager.getConnection(connectionString);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users");

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {

                out.println("<tr><td><a href='userDetail.jsp?id=" + rs.getInt("id") + "'>" + rs.getInt("id") + "</a></td>" +
                            "<td>" + rs.getString("name") + "</td>" +
                            "<td>" + rs.getString("email")+ "</td>" +
                            "<td>" + (rs.getBoolean("active") ? "Active" : "Inactive") + "</td></tr>");
            }

            rs.close();
            stmt.close();
            conn.close();
        %>
        </tbody>
    </table>




<script>

    document.write("can I use javascript?");
</script>
</body>
</html>
