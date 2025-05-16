
package com.factorypattern;

public class PaymentFactory {

	public static Payment getInstance(PaymentType paymentType){
		
		switch(paymentType) {
		
			case UPI:
				return new UPI();
				 
			case NEFT:
				return new NEFT();
				
			case IMPS:
				return new IMPS();
			 
			default:
				return null;
			
			
		}
	}
}
