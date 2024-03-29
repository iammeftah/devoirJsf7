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
    <a href="../../index.jsp">Home</a>
    <a href="<%= request.getContextPath()%>/product">Add Product</a>
    <a href="../../<%= request.getContextPath()%>/Store.jsp">Store</a>
  </div>
</header>
<hero>
  <div class="container">
    <h1>Add Product</h1>
    <form method="post" action="<%= request.getContextPath()%>/product">
      <input type="hidden" name="action" value="add"> <!-- This line tells the servlet to add a new product -->

      <label for="productName">Product Name:</label>
      <input id="productName" name="productName" type="text" placeholder="Product Name" required><br><br>

      <label for="productDescription">Product Description:</label>
      <textarea id="productDescription" name="productDescription" placeholder="Product Description" required></textarea><br><br>

      <label for="productPrice">Product Price:</label>
      <input id="productPrice" name="productPrice" type="number" step="0.01" placeholder="Product Price" required><br><br>

      <label for="productAvailable">Product Available:</label>
      <input id="productAvailable" name="productAvailable" type="checkbox"><br><br>

      <input type="submit" value="Add Product">
    </form>
  </div>
</hero>

<footer>
  <h4>2024</h4>
</footer>
</body>
</html>
