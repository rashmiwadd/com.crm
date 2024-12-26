package practice.test;

import java.util.Date;

public class camptureTimeStamp {
public static void main(String[] args) {
	
	String time = new Date().toString().replace(" ", "_").replace(":", "_");
	//we removed space with _ and replaced : blank
	System.out.println(time);
	
}
	
}
