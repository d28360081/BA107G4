package com.com.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHandler {
//	public static void main(String args[]){
//		TimeHandler th=new TimeHandler();
//		System.out.println(th.toTimeStamp("1993-02-06", "18:02:33"));
//		
//	}
public static void main(String args[]){
	
}

public TimeHandler(){
	
}
public Timestamp toTimeStamp(String yyyymmdd,String hhmmss){
	Timestamp ts=null;
	try {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	StringBuffer sb=new StringBuffer();
	Date d=new Date();
	sb.append(yyyymmdd);
	sb.append(" ");
	sb.append(hhmmss);

	d=sdf.parse(sb.toString());

	ts=new Timestamp(d.getTime());
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ts;
	
}

}