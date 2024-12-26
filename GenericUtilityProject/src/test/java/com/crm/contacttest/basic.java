package com.crm.contacttest;

import com.crm.generic.webdriverutility.JavaUtility;

public class basic {
	
	public static void main(String[] args) {
		JavaUtility j = new JavaUtility();
		System.out.println(j.getSystemDateYYYYDDMM());
		System.out.println(j.getRequiredDateYYYYDDMM(30));
		
	}

}
