package com.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandonNumber() {
		
		Random random = new Random();
		int randomNumber = random.nextInt(1000);
		return randomNumber;
	}
		
	public String getSystemDateYYYYDDMM() {
		
		Date dateObj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		String date =sdf.format(dateObj);
		return date;
		
	}
	
	public String getRequiredDateYYYYDDMM(int days) { //you can provide -30 or 30 days
		Date dateObj = new Date();
	SimpleDateFormat sim = new SimpleDateFormat("YYYY-MM-DD");
	String date = sim.format(dateObj);
	Calendar cal =sim.getCalendar();
	cal.add(Calendar.DAY_OF_MONTH,days);
	String reqDate=sim.format(cal.getTime());
	return reqDate;
	
		
		
		
	}
		
		
		
		
		
		
		
		
		
	}


