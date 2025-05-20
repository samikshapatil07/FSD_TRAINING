package com.ecom.test;

import com.ecom.model.Category;
import com.ecom.model.Customer;
import com.ecom.model.Product;
import com.ecom.model.Purchase;
import com.ecom.service.CategoryService;
import com.ecom.service.CustomerService;
import com.ecom.service.ProductService;
import com.ecom.service.PurchaseService;


import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    CustomerService customerService;
    CategoryService categoryService;
    ProductService productService;
    PurchaseService purchaseService;

    @BeforeEach
    public void init() {
        customerService = new CustomerService();
        categoryService = new CategoryService();
        productService = new ProductService();
        purchaseService = new PurchaseService();
    }

    @Test
    public void testCustomerInsert() {
        Customer c = new Customer();
        c.setName("John Doe");
        c.setCity("123 Main St");
        assertDoesNotThrow(() -> customerService.addCustomer(c));
    }

    @Test
    public void testCategoryInsert() {
        Category cat = new Category();
        cat.setName("Electronics");
        assertDoesNotThrow(() -> categoryService.addCategory(cat));
    }

    @Test
    public void testProductInsertAndFetchByCategory() {

        Category cat = new Category();
        cat.setName("Books");
        categoryService.addCategory(cat);

        Product p = new Product();
        p.setTitle("Java Programming");
        p.setPrice(499.99);
        p.setDescription("Comprehensive Java Guide");

        assertDoesNotThrow(() -> productService);

        List<Product> products = productService.getProductsByCategory(1);
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }
/*
    @Test
    public void testMakePurchase() {
        Purchase purchase = new Purchase();
        purchase.setCustomer(1);
        purchase.setProduct(1);
        purchase.setDateOfPurchase("2025-05-17");

        assertDoesNotThrow(() -> purchaseService.insert(purchase));
    }*/
}