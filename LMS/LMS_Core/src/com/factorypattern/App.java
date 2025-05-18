package com.factorypattern;

import java.util.Scanner;

public class App {
	public static void main(String[]arge){
		Scanner sc =  new Scanner(System.in);
		System.out.println("----Available Payment types--------");
		for(PaymentType type : PaymentType.values()) {
			System.out.println(type);
		}
		System.out.println("Enter your payment type:");
		String paymentTypeValue = sc.next();
		try {
			/*convertig user given value into enum
			 * if i can -->> go frwd else go to catch block*/
			PaymentType paymentType =  PaymentType.valueOf(paymentTypeValue); 
            /*make sure the given payment type is curreently active*/
			if(paymentType.getValue() == 0) {
				System.out.println("This is legitimate but currently IN-ACTIVE!!");
				return; 
			}
			else {
				/*
				 * If i am here, then it means, payment type is correct 
				 * and it is active 
				 * now, i need to call dailyLimit() method for this payment
				 * to tell my end user what id the limit for this payment type
				 * NOTE: NOTE: NOTE: do not make me create objects of your payment classes!!!!!!
				 */
				//System.out.println("go forward.. this is legitimate and active");
			 
				Payment payment =  PaymentFactory.getInstance(paymentType);
				/* Excellent, I got the object of Payment */
				System.out.println("Daily limit of " + paymentTypeValue + " is " + payment.dailyLimit());
			}
		}
		catch(IllegalArgumentException  e) {
			System.out.println("this payment type is not active and not available right now");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			sc.close();
		}
	}
}

