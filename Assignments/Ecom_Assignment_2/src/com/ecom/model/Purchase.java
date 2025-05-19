package com.ecom.model;

import java.time.LocalDate;

import com.ecom.enums.Coupon;

public class Purchase {
	
	private int id;
    private LocalDate dateOfPurchase;
    private Customer customer;       
    private Product product;         
    private int quantity;
    private double totalPrice;
    private Coupon coupon;
    
	public Purchase() { }

	public Purchase(int id, LocalDate dateOfPurchase, Customer customer, Product product, int quantity,
			double totalPrice, Coupon coupon) {
		super();
		this.id = id;
		this.dateOfPurchase = dateOfPurchase;
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.coupon = coupon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(LocalDate dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", dateOfPurchase=" + dateOfPurchase + ", customer=" + customer + ", product="
				+ product + ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", coupon=" + coupon + "]";
	} 
	
}
