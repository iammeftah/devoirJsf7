package com.example.crud2jsp.controller;

import com.example.crud2jsp.dao.ProductDAO;
import com.example.crud2jsp.model.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/product")
public class ProductControllerServlet extends HttpServlet {
    ProductDAO productDao = new ProductDAO();

    private boolean showForm = false ;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assuming this is for displaying the form to add a new product
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Views/AddProduct.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the action is to add a new product or delete an existing one
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            // Handle adding a new product
            String productName = request.getParameter("productName");
            String productDescription = request.getParameter("productDescription");
            double productPrice = Double.parseDouble(request.getParameter("productPrice"));
            boolean productAvailable = Boolean.parseBoolean(request.getParameter("productAvailable"));

            Product product = new Product(productName, productDescription, productPrice, productAvailable);
            productDao.addProduct(product);

            // Redirect to the Store page or another page as needed
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Store.jsp");
            requestDispatcher.forward(request,response);

        } else if ("delete".equals(action)) {
            String productIDStr = request.getParameter("productID");
            if (productIDStr != null && !productIDStr.isEmpty()) {
                int productID = Integer.parseInt(productIDStr);
                System.out.println("Deleting product with ID: " + productID);

                Product product = new Product();
                product.setProductID(productID);
                productDao.deleteProduct(product);

                // Redirect to the Store page or another page as needed
                response.sendRedirect("Store.jsp");
            } else {
                System.out.println("Product ID is missing or empty.");
            }
        }else if("edit".equals(action)){
            showForm = true;
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Store.jsp");
            requestDispatcher.forward(request, response);

        }else if("update".equals(action)) {
                showForm = false;
                // Handle updating the product
                int productID = Integer.parseInt(request.getParameter("productID"));
                String productName = request.getParameter("productName");
                String productDescription = request.getParameter("productDescription");
                double productPrice = Double.parseDouble(request.getParameter("productPrice"));
                boolean productAvailable = "on".equals(request.getParameter("productAvailable"));

                Product product = new Product();
                product.setProductID(productID);
                product.setProductName(productName);
                product.setProductDescription(productDescription);
                product.setProductPrice(productPrice);
                product.setProductAvailable(productAvailable);

                productDao.updateProduct(product);



        }
    }
}

