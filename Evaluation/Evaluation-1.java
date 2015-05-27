package com.test.stl.linkedList;

import java.io.*;
import java.util.*;
import java.math.*;


public class Evaluation {

	public static final Integer I=81482;
	public static Map<String, Set<Integer> > rMap =new HashMap<String, Set<Integer> >();
	public static Map<String, Set<Integer> > tMap =new HashMap<String, Set<Integer> >();
	public static Integer molecular=0; //R(u)&T(u)
	public static Integer All_t=0;
	public static Integer All_r=0;	
	public static Integer Union_r=0;
	public static Integer getIntersection(Set<Integer> R,Set<Integer> T){
		Set <Integer> Result =new HashSet<Integer>();
		Result.addAll(T);
		Result.retainAll(R);
		return Result.size();
	}
	
	public static Integer getUnion(Set<Integer>R,Set<Integer> T){
		Set<Integer> Result = new HashSet<Integer>();
		Result.addAll(T);
		Result.addAll(R);
		return Result.size();
	}
	
	public static void Caculate(){
		
		Set<String> keys1=rMap.keySet();
		Set<Integer> r_unionSet=new HashSet<Integer>();
		
		for(String skey:keys1){
			Set<Integer> rSet=  rMap.get(skey);
			Set<Integer> tSet=tMap.get(skey);
			if(rSet==null||tSet==null ) continue;
			molecular+=getIntersection(rSet, tSet); //???????
			All_t+=tSet.size();
			All_r+=rSet.size();
			r_unionSet.addAll(rSet);
		}		
		Union_r=r_unionSet.size();
	}
	public static Double getRecall(){
		return (Double)((molecular+0.0)/(All_t+0.0));
	}
	
	public static Double getPrecision(){
		
		
		return (Double)((molecular+0.0)/(All_r+0.0));
	}
	
	public static Double getCoverage(){
		return (Double)((Union_r+0.0)/(I+0.0));
	}
	
	public static void printMap(Map<String, Set<Integer> > mp){
       Set<String> keys=mp.keySet();
       for(String skey:keys){
    	   System.out.println("key= "+skey);
    	   System.out.println("values= ");
    	   Set<Integer> tmpSet=mp.get(skey);
    	   for(Integer ans:tmpSet){
    		   System.out.println(ans);
    	   }
       }		
	}
	
	public static void inputMap(String path,Map<String,Set<Integer> > mp){
	       File file = new File(path);
	       BufferedReader reader = null;
	     //  FileWriter writer = new FileWriter("D://Data//1year_item_rec", true);
	       
	       try{
	    	   reader = new BufferedReader(new FileReader(file));
	    	   String tmpString=null;
	    	   while((tmpString = reader.readLine()) != null){
	    		   String sarray[]=tmpString.split(":");
	    		   String key = sarray[0];
	    		   String items=sarray[1];
	    		   Set<Integer> itemSet=new HashSet<Integer>();
	    		   itemSet.clear();
	    		   String itemarray[] = null;
	    		   if(items!=null){
		    		   if(items.contains(",")){
		    			   itemarray = items.split(",");
			    		   for(String tmp:itemarray){
			    			   itemSet.add(Integer.parseInt(tmp));
			    		   }
		    		   }
		    		   else{
		    			   itemSet.add(Integer.parseInt(items));
		    		   }
	    		   }
	    		   mp.put(key, itemSet);
	    	   }
	       }catch(IOException e){
	    	   e.printStackTrace();
	       }finally{
	    	   if(reader!=null){
	    		   try{
	    			   reader.close();
	    		   }catch(IOException e1){
	    			   e1.printStackTrace();
	    		   }
	    	   }
	       }		
	}
	public static void inputMap2(String path,Map<String,Set<Integer> > mp){
	       File file = new File(path);
	       BufferedReader reader = null;
	     //  FileWriter writer = new FileWriter("D://Data//1year_item_rec", true);
	       
	       try{
	    	   reader = new BufferedReader(new FileReader(file));
	    	   String tmpString=null;
	    	   int step=1;
	    	   while((tmpString = reader.readLine()) != null){
	    		   System.out.println("step="+step);
	    		   step++;
	    		   String sarray[]=tmpString.split(":");
	    		   String key = sarray[0];
	    		   String items=sarray[1];
	    		   Set<Integer> itemSet=new HashSet<Integer>();
	    		   itemSet.clear();
	    		   String itemarray[] = null;
	    		   if(items!=null){
		    		   if(items.contains(" ")){
		    			   itemarray = items.split(" ");
			    		   for(String tmp:itemarray){
			    			   itemSet.add(Integer.parseInt(tmp));
			    		   }
		    		   }
		    		   else{
		    			   itemSet.add(Integer.parseInt(items));
		    		   }
	    		   }
	    		   mp.put(key, itemSet);
	    	   }
	       }catch(IOException e){
	    	   e.printStackTrace();
	       }finally{
	    	   if(reader!=null){
	    		   try{
	    			   reader.close();
	    		   }catch(IOException e1){
	    			   e1.printStackTrace();
	    		   }
	    	   }
	       }		
	}	
	public static void main(String []args) throws Exception{
		inputMap("D://REData//Test1.txt", tMap);
		inputMap2("D:/REData//user-SimilarUser-Unionitem1000", rMap);
/*		inputMap2("D://in1.txt", rMap);
		inputMap("D://in2.txt", tMap);*/
	//	System.out.println("rMap:");
	//	printMap(rMap);
	//	System.out.println("tMap:");
	//	printMap(tMap);
		Caculate();
		System.out.println( "molecular="+molecular);
		System.out.println("All_t="+All_t);
		System.out.println("All_r="+All_r);
		System.out.println("Union_r="+Union_r);
		System.out.println("Precision= "+ getPrecision());
		System.out.println("Recall= "+getRecall());
		System.out.println("getCoverage= "+getCoverage());
		
	}
}

