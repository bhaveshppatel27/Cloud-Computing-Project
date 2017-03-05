package com.neu.edu.wtp.utility;

public class RandomNumberUtility {

	public int generateCvv() {
		int range = (999 - 100) + 1;
		return (int) (Math.random() * range) + 100;
	}

	public int generatePincode(){
		int range = (9999 - 1000) + 1;
		return (int) (Math.random() * range) + 1000;
		
	}
	public long generateAccountNumber() {
		long range = (9999999999L - 10000000000L) + 1;
		return (long) (Math.random() * range) + 10000000000L;

	}

	public long generateCardNumber() {
		long range = (9999999999999999L - 1000000000000000L) + 1;
		return (long) (Math.random() * range) + 1000000000000000L;

	}

}
