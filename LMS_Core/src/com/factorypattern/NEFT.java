package com.factorypattern;

public class NEFT implements Payment{
	
	@Override
	public double dailyLimit() {
		return 200000;
	}
}