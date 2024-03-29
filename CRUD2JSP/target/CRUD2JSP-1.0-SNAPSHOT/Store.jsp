<%@ page import="com.example.crud2jsp.model.Product" %>
<%@ page import="com.example.crud2jsp.dao.ProductDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Store</title>
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
    <div class="grid">
        <%
            ProductDAO productDAO = new ProductDAO();
            List<Product> products = productDAO.readProducts();
            for (Product product : products) {
        %>
        <div class="product">
            <h3><%= product.getProductName() %></h3>
            <p><%= product.getProductDescription() %></p>
            <p>Price: <%= product.getProductPrice() %></p>
            <p>Product ID: <%= product.getProductID() %> </p>
            <p>Available: <%= product.isProductAvailable() %></p>
            <div class="editDelete">
                <form method="post" action="<%= request.getContextPath()%>/product">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="productID" value="<%= product.getProductID() %>">
                    <input type="submit" value="Delete">
                </form>
                <form method="post" action="<%= request.getContextPath()%>/product">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="productID" value="<%= product.getProductID() %>">
                    <input type="submit" value="Edit">
                </form>

                <%
                    Boolean showFormObj = (Boolean) request.getAttribute("showForm");
                    boolean showForm = (showFormObj != null) ? showFormObj : false;
                %>

                <div class="showForm">
                    <% if (showForm) { %>
                    <form method="post" action="<%= request.getContextPath() %>/product">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="productID" value="<%= product.getProductID() %>">
                        <label for="productName">Product Name:</label>
                        <input type="text" id="productName" name="productName" value="<%= product.getProductName() %>" required>
                        <label for="productDescription">Product Description:</label>
                        <textarea id="productDescription" name="productDescription" required><%= product.getProductDescription() %></textarea>
                        <label for="productPrice">Product Price:</label>
                        <input type="number" id="productPrice" name="productPrice" step="0.01" value="<%= product.getProductPrice() %>" required>
                        <label for="productAvailable">Product Available:</label>
                        <input type="checkbox" id="productAvailable" name="productAvailable" <%= product.isProductAvailable() ? "checked" : "" %>>
                        <input type="submit" value="Update Product">
                    </form>
                    <% } %>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</hero>

<footer>
    <h4>2024</h4>
</footer>
</body>
</html>

