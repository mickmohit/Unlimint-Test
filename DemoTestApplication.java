package com.example.demo;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONArray;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoTestApplication {
	
	private static int len = 0;
	@Component
	public class StartupPrintRunner implements CommandLineRunner {
	    @Override
	    public void run(String... args) throws Exception {
	    	len = Arrays.deepToString(args).length();
	        System.out.println("args = " + Arrays.deepToString(args));
	    }
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoTestApplication.class, args);
		
		 ExecutorService executorService = Executors.newCachedThreadPool();
		 
		 int noofFiles = 2; //here you can change it to len variable
		 
		 
		 for(int i=0;i<noofFiles;i++)
		 {
			 //reading & parsing json
			 if(i==0)
			 {
				 Callable<String> callable = () -> {
			            // Perform some computation
			            System.out.println("Entered JSON Callable");
			            //Thread.sleep(2000);
			            String contentsJson = ReadFiles.readJSON();
			            return contentsJson;
			        };
			     System.out.println("Submitting read JSON callable task to new thread");
			     Future<String> future = executorService.submit(callable);
			     String result = future.get();
			     if(future.isDone()) {
			         System.out.println(result);
			     }
			     
			   
			     final String tempResult = result;
			     if(tempResult!=null) {
			    	 executorService.submit(()-> {
			    		 try {
							ParseFiles.parse(tempResult,"order2.json");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	 });
			     }
			     
			 }
			 //reading & parsing csv
			 if(i==1)
			 {
				 Callable<JSONArray> callable = () -> {
			            // Perform some computation
			            System.out.println("Entered CSV Callable");
			            JSONArray contentsCSV = ReadFiles.readCSV();
			            return contentsCSV;
			        };
			     System.out.println("Submitting read CSV callable task to new thread");
			     Future<JSONArray> future = executorService.submit(callable);
			     JSONArray result = future.get();
			     if(future.isDone()) {
			     //    System.out.println(result);
			     }
			     
			   
			     final JSONArray tempResult = result;
			     if(tempResult!=null) {
			    	 executorService.submit(()-> {
			    		 try {
			    			 ParseFiles.parse(tempResult,"order1.csv");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	 });
			     }
			     
			 }
		 }
		 
		 executorService.shutdown();
	}

}