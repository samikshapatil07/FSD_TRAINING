package com.ecom.controller;

import java.util.List;
import java.util.Scanner;

import com.ecom.exception.CategoryNotFoundException;
import com.ecom.exception.CustomerNotFoundException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Category;
import com.ecom.model.Customer;
import com.ecom.model.Product;
import com.ecom.model.Purchase;
import com.ecom.service.CategoryService;
import com.ecom.service.CustomerService;
import com.ecom.service.ProductService;
import com.ecom.service.PurchaseService;

public class App {
	
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        CategoryService categoryService = new CategoryService();
        CustomerService customerService = new CustomerService();
        ProductService productService = new ProductService();
        PurchaseService purchaseService = new PurchaseService();

        Category category = new Category();
        Customer customer = new Customer();
        Product product = new Product();
        Purchase purchase = new Purchase();

        while (true) {
            System.out.println("\n********** E-COMMERCE MENU **********");
            System.out.println("1. Add Category");
            System.out.println("2. View All Categories");
            System.out.println("3. Get Category By ID");
            System.out.println("4. Add Customer");
            System.out.println("5. View All Customers");
            System.out.println("6. Delete Customer");
            System.out.println("7. Add Product");
            System.out.println("8. View All Products");
            System.out.println("9. View Products By Category");
            System.out.println("10. Add Purchase");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("Exiting... Thank you!");
                break;
            }
            
            switch (choice) {
            case 1: // Add Category
                sc.nextLine(); 
                System.out.print("Enter category name: ");
                category.setName(sc.nextLine());
                categoryService.addCategory(category);
                System.out.println("Category added successfully."); 
                break;

            case 2: // View All Categories
                List<Category> categories = categoryService.viewAllCategories();
                categories.forEach(System.out::println);
                break;

            case 3: // Get Category By ID
                System.out.print("Enter category ID: ");
                int catId = sc.nextInt();
                try {
                    Category cat = categoryService.getById(catId);
                    System.out.println(cat);
                } catch (CategoryNotFoundException e) {
                    System.out.println("Category not found with id "+ catId);
                }
                break;

            case 4: // Add Customer
                sc.nextLine();
                System.out.print("Enter customer name: ");
                customer.setName(sc.nextLine());
                System.out.print("Enter customer city: ");
                customer.setCity(sc.nextLine());
                try {
					customerService.addCustomer(customer);
					System.out.println("Customer added successfully.");
				} catch (InvalidInputException e) {
					System.out.println("Invalid Customer name or city");
				}
                
                break;

            case 5: // View All Customers
                List<Customer> customers = customerService.getAllCustomers();
                customers.forEach(System.out::println);
                break;

            case 6: // Delete Customer
                System.out.print("Enter customer ID to delete: ");
                int custId = sc.nextInt();
                try {
                    customerService.deleteCustomer(custId);
                    System.out.println("Customer deleted successfully.");
                } catch (CustomerNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 7: // Add Product
                sc.nextLine();
                System.out.print("Enter product title: ");
                product.setTitle(sc.nextLine());
                System.out.print("Enter product price: ");
                product.setPrice(sc.nextDouble());
                sc.nextLine();
                System.out.println("Enter Product Description:");
                product.setDescription(sc.nextLine());
                System.out.print("Enter category ID for product: ");
                int productCatId = sc.nextInt();
                productService.addProduct(product,productCatId);
                System.out.println("Product added successfully.");
                break;

            case 8: // View All Products
                List<Product> products = productService.getAllProducts();
                products.forEach(System.out::println);
                break;

            case 9: // View Products by Category
                System.out.print("Enter category ID: ");
                int cId = sc.nextInt();
                List<Product> prodByCat = productService.getProductsByCategory(cId);
                prodByCat.forEach(System.out::println);
                break;

            case 10: // Add Purchase
                System.out.print("Enter customer ID: ");
                int pCustId = sc.nextInt();
                System.out.print("Enter product ID: ");
                int pProdId = sc.nextInt();
 
                try {
					purchaseService.purchaseProduct(pCustId,pProdId,sc);
					System.out.println("Purchase recorded successfully.");
				}catch(IllegalArgumentException e) {
					System.out.println("Coupon code is Invalid!!");
				}
                
                break;

            default:
                System.out.println("Invalid input! Please try again.");
        }
    }

    sc.close();
}
}



        
