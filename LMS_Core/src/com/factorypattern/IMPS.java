package com.factorypattern;

public class IMPS implements Payment{
	
	@Override
	public double dailyLimit() {
		return 500000;
	}
}