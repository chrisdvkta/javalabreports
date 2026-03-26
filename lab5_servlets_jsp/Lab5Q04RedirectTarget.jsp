<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>SendRedirect Target</title></head>
<body>
  <h2>SendRedirect Demo</h2>
  <p>Message: <%= request.getParameter("msg") %></p>
  <p>done by Krish Devkota</p>
  <%
    System.out.println("done by Krish Devkota");
  %>
</body>
</html>

