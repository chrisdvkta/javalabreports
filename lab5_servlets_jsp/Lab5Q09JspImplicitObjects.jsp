<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>JSP Implicit Objects</title></head>
<body>
  <h2>JSP Implicit Objects Demo</h2>

  <h3>request</h3>
  <p>Method: <%= request.getMethod() %></p>
  <p>URI: <%= request.getRequestURI() %></p>

  <h3>session</h3>
  <p>Session ID: <%= session.getId() %></p>

  <h3>application</h3>
  <p>Context path: <%= application.getContextPath() %></p>

  <h3>out</h3>
  <p>This line is printed using the implicit object <code>out</code>.</p>

  <%
    // Console output (server logs)
    System.out.println("done by Krish Devkota");
  %>

  <p>done by Krish Devkota</p>
</body>
</html>

