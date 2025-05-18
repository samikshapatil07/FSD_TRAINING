package com.factorypattern;

public enum PaymentType { //1 for active , 0 for temporarily inactive 
	UPI(1) ,NEFT(1) ,IMPS(0) ,RTGS(0) ;
	
	 private PaymentType(int value) {
		this.value = value; 
	}
	 
	 private int value;

	public int getValue() {
		return value;
	}
	
}