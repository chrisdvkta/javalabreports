<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>RequestDispatcher Result</title></head>
<body>
  <h2>RequestDispatcher Demo</h2>
  <p>Message: <%= request.getAttribute("msg") %></p>
  <p>done by <%= request.getAttribute("author") %></p>
  <%
    System.out.println("done by Krish Devkota");
  %>
</body>
</html>

