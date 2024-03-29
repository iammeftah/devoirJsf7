package com.example.crud2jsp.dao;

import com.example.crud2jsp.model.Product;
import com.example.crud2jsp.service.Database;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private String ADD_PRODUCT_SQL = "INSERT INTO products (productName , productDescription , productPrice , productAvailable ) VALUES (?,?,?,?)";
    private String READ_PRODUCTS_SQL = "SELECT * FROM products";

    private String UPDATE_PRODUCT_SQL = "UPDATE products SET productName = ?, productDescription = ?, productPrice = ?, productAvailable = ? WHERE productID = ?;" ;
    private String DELETE_PRODUCT_SQL = "DELETE FROM products WHERE productID = ?";

    // Add product
    public void addProduct(Product product){
        try(Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT_SQL)){
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductDescription());
            preparedStatement.setDouble(3, product.getProductPrice());
            preparedStatement.setBoolean(4, product.isProductAvailable());
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // Read Product
    public List<Product> readProducts (){
        List<Product> listedProducts = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_PRODUCTS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Product product = new Product(rs.getInt("productID"),rs.getString("productName"), rs.getString("productDescription") , rs.getDouble("productPrice") , rs.getBoolean("productAvailable"));
                listedProducts.add(product);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listedProducts;
    }


    // Update Product

    public void updateProduct(Product product){
        try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
        preparedStatement.setString(1 , product.getProductName());
        preparedStatement.setString(2 , product.getProductDescription());
        preparedStatement.setDouble(3,product.getProductPrice());
        preparedStatement.setBoolean(4,product.isProductAvailable());
        preparedStatement.setInt(5,product.getProductID());

        preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Delete Product

    public void deleteProduct(Product product) {
        String DELETE_PRODUCT_SQL = "DELETE FROM products WHERE productID = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            preparedStatement.setInt(1, product.getProductID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
