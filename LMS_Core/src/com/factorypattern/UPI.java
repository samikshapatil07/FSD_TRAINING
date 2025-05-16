
package com.factorypattern;

public class UPI implements Payment{

	@Override
	public double dailyLimit() {
		 
		return 100000;
	}
}
