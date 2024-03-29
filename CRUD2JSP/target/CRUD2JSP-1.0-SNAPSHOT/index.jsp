<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>CRUD - Introducing the crud project using JSP</title>
  <style></style>
</head>
<body>
<header>
  <div class="logo">iProduct</div>
  <div class="header-content">
    <a href="index.jsp">Home</a>
    <a href="<%= request.getContextPath()%>/product">Add Product</a>
    <a href="<%= request.getContextPath()%>/Store.jsp">Store</a>
  </div>
</header>
<hero>

</hero>

<footer>
  <h4>2024</h4>
</footer>
</body>
</html>