<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!doctype html>
<html>
<title>HTTP Monitor</title>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="Pragma" content="no-cache" />
  <meta http-equiv="Expires" content="-1" />
  <title>Monitor</title>
</head>
<body>
  <%= new java.util.Date().toString() %>
  Server host name is: <b><%=request.getServerName() %></b>
</body>
</html>
