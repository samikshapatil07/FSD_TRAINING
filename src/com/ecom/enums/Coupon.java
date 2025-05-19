package com.ecom.enums;

public enum Coupon {
	
	WELCOME(10),
    FESTIVE(20),
    SUMMER(15),
    NEW_YEAR(25);
    
	
	Coupon(int discount){
		this.discount = discount;
	}
	
	private int discount;

	public int getDiscount() {
		return discount;
	} 

}
