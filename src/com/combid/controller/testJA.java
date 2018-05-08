package com.combid.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class testJA {
 public static void main(String args[]) throws JSONException{
   JSONObject job=new JSONObject();
   try {	
		   JSONObject job2=new JSONObject();
		     job2.put("type", "t");
		     job2.put("job2", "job2");
		   JSONObject job3=new JSONObject();
		    job3.put("type", "d");
			job3.put("job3", "job3");
			
		   
		   job.put("2", job2);
		   job.put("3",job3);
		   
		   System.out.println(job);
		   System.out.println(job.get("2"));
		   System.out.println(job.length());
		   while(job.keys().hasNext()){
			   if(job.get((String)job.keys().next()).equals("t")){
				   System.out.println("yes");
			   }
		   }
   } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
}
