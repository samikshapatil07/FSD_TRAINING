package com.ecom.service;

import java.time.LocalDate;
import java.util.Scanner;

import com.ecom.dao.CustomerDao;
import com.ecom.dao.ProductDao;
import com.ecom.dao.PurchaseDao;
import com.ecom.daoImpl.CustomerDaoImpl;
import com.ecom.daoImpl.ProductDaoImpl;
import com.ecom.daoImpl.PurchaseDaoImpl;
import com.ecom.enums.Coupon;
import com.ecom.exception.CustomerNotFoundException;
import com.ecom.exception.ProductNotFoundException;
import com.ecom.model.Customer;
import com.ecom.model.Product;
import com.ecom.model.Purchase;

public class PurchaseService {
	
	private CustomerDao customerDao = new CustomerDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();
    private PurchaseDao purchaseDao = new PurchaseDaoImpl();

    public void purchaseProduct(int customerId, int productId, Scanner sc){
        Purchase purchase = new Purchase();

        // Step 1: Validate customer ID
        Customer customer;
		try {
			customer = customerDao.getById(customerId);
			purchase.setCustomer(customer);
		} catch (CustomerNotFoundException e) {
			System.out.println("Customer not found");
		}
        
        // Step 2: Validate product ID
        Product product = null;
		try {
			product = productDao.getById(productId);
			purchase.setProduct(product);
		} catch (ProductNotFoundException e) {
			System.out.println("Product not found");
		}
        

        // Step 3: Enter quantity
        System.out.println("Enter quantity to purchase:");
        int quantity = sc.nextInt();
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        purchase.setQuantity(quantity);

        // Step 4: Check coupon
        double totalPrice = product.getPrice() * quantity;
        System.out.println("Do you have a coupon? (Y/N): ");
        String ans = sc.next();
        if (ans.equalsIgnoreCase("Y")) {
            System.out.println("Enter coupon code: ");
            String couponCode = sc.next().toUpperCase();
            try {
                Coupon coupon = Coupon.valueOf(couponCode);
                double discount = coupon.getDiscount();
                //System.out.println("Discount = " + discount + "%");
                totalPrice = totalPrice - (totalPrice * discount / 100);
                //System.out.println("Discounted Total Price = " + totalPrice);
                purchase.setCoupon(coupon);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid coupon code! Proceeding without discount...");
            }
        } else {
            System.out.println("No coupon applied...");
        }

        purchase.setTotalPrice(totalPrice);
        purchase.setDateOfPurchase(LocalDate.now());

        // Step 5: Save Purchase in DB
        purchaseDao.addPurchase(purchase);
    }

}
