package com.com.controller;

public class pswGenerator {
java.util.Random r=new java.util.Random();
	public pswGenerator(){
		
		
	}
	
	public String generate(){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<6;i++){
			int rnum=r.nextInt();
			int anw=0;
	       //大寫
			if(rnum/3==0){
	    	   anw=(int) (Math.random()*26+65);
	    	   sb.append((char)anw);
	       }
	       else if(rnum%3/2==0){
	    	   anw=(int) (Math.random()*26+97);
	    	   sb.append((char)anw);
	       }
	       else if(rnum%3/2!=0){
	    	   anw=(int)(Math.random()*10+1);
	    	   sb.append(String.valueOf(anw));
	    	  
	       }
			
		
		}
		return sb.toString();
	}
//	public static void main(String args[]){
//		pswGenerator a= new pswGenerator();
//		a.generate();
//		System.out.println(a.generate());
//	}
}
