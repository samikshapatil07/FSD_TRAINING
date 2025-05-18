
package com.lms.enums;

public enum Coupon {
	DIWALI(15),
	NEW_YEAR(12),
	BLACK_FRIDAY(20),
	SUMMER_CODE(10);
	
	Coupon(int discount){
		this.discount = discount;
	}
	
	private int discount;

	public int getDiscount() {
		return discount;
	} 
	
	
}
