package cvs.mainpage.login.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import cvs.function.Effectiveness;
import cvs.function.Function;
import cvs.function.UI;

/**
 * 
 * @author 임광민
 * 재고 관련 클래스
 */

public class Inquire {
   private static Scanner s;
   private static String answer;
   private static ArrayList<String> array;
   private static UI ui;
   private static Function function;
   private static Effectiveness effect;
   private static Calendar c;
   private static ManagerMode managerMode;
   
   static {
	  c = Calendar.getInstance(); 
	  effect = new Effectiveness(); 
	  function = new Function();
	  ui = new UI();        
	  array = new ArrayList<>();  
      s = new Scanner(System.in);
      managerMode = new ManagerMode();
   }
   /**
    * 
    * @param main
    * 재고 관련 메인 UI와 메뉴에 해당 메서드 연결
    */
   public static void main(String[] args) {
	  String[] menuArray = {"제품 검색","제품 구매","제품 검수","제품 삭제","제품 수정","상위 메뉴 돌아가기","종료"};
	  
	  while(true) {
		  ui.logo("관리자 재고 관리");
		  ui.menu(menuArray, 7);
      
		  answer = function.input("입력");
		  
		  if(answer.equals("1")) {searchUI();}
		  else if(answer.equals("2")) {orderProductUI();}
		  else if(answer.equals("3")) {productCheckUI(); }
		  else if(answer.equals("4")) {deleteProductUI();}
		  else if(answer.equals("5")) {informationProductUI();}
		  else if(answer.equals("6")) {managerMode.main(null);}
		  else if(answer.equals("*")) System.exit(0);
	  }
   }
   
   /**
    * 제품 삭제 UI
    */
   private static void deleteProductUI() {
	   // TODO Auto-generated method stub
	   ui.logo("제품삭제");
	   
	   try {
		   function.reader("dat\\ClassificationData.dat");

    	   answer = function.input("카테고리 번호 입력");
    	   
    	   if(effect.categoryInspection(answer)) {
    		   deleteProduct(answer);   
    	   } else {
    		   System.out.println("카테고리 번호를 잘 못 입력하였습니다.");
    	   }
	   } catch (Exception e) {
		   System.out.println("Inquire.deleteProductUI() : " + e.toString());
	   }
   }
   
   //delte삭제 input = 카테고리 번호 
   /**
    * 
    * @param input - deleteProductUI에서 사용자가 적은 카테고리 번호.
    * 카테고리 번호에 상품을 출력.
    * 삭제하고 싶은 일련번호를 입력 후, 유통기한과 재고수가 0인 상품만 삭제
    */
   private static void deleteProduct(String input) {
	   // TODO Auto-generated method stub
	   try {
		   BufferedReader reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
		   
	       String txt = null;
	       ArrayList<String> array = new ArrayList<>();
	       
	       ArrayList<String> newArray = new ArrayList<>();
   
	       System.out.println("        [일련번호]        [수량]     [입고날짜]     [유통기한]     [제품명]");
	          
	       while((txt = reader.readLine()) != null) {
	    	   if(input.length() == 7) {                                    //소분류
	    		   if((txt.replace("-", "").substring(0, 7)).equals(input)) {
	    			   String temp[] = txt.split("■");
	                
	    			   array.add("      "+temp[0]+"        "+temp[3]+"        "+temp[5]+"        "+temp[6]+"   "+temp[1]);
	         		}
	         	} else if(input.length() == 5) {                           //중분류
	         		if((txt.replace("-", "").substring(0, 5)).equals(input)) {
	         			String temp[] = txt.split("■");
	                
	         			array.add("      "+temp[0]+"        "+temp[3]+"        "+temp[5]+"        "+temp[6]+"   "+temp[1]);
	         		}
	         	} 
	       }
	       reader.close();
	          
	       int k=1;
	       
	       reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
	       
	       txt = null;
	       
	       while(true) {
	    	   if(array.size() == 0) {
	    		   System.out.println("일련번호를 잘못 입력하였습니다.");
	    		   break;
	    	   }

	    	   if(!array.get(k-1).equals(null)) {
	    		   System.out.println(array.get(k-1));
	    	   }

	    	   if((k%5==0 && k!=0) || array.size() == k) {
	    		   String[] menuArray = {"이전페이지 ","다음페이지","삭제 할 일련번호","뒤로가기"};
	    		   ui.menu(menuArray, 4);

	    		   answer = function.input("입력");
	
	    		   if(answer.equals("1") && k!=5 && array.size() != k) {
	    			   k = k-10;
	    		   } else if(answer.equals("1") && k==5) {
	    			   k = k-5;
	    		   } else if(answer.equals("1") && array.size() == k) {
	    			   k = k-5-(array.size()%5);
	    		   } else if(answer.equals("2") && array.size() == k) {
	    			   k = k-(array.size()%5);;
	    		   } else if(answer.equals("3")) {
	    			   
	    			   answer = function.input("삭제할 일련번호");
	    			   boolean check = false;
	    			   while((txt = reader.readLine()) != null) {
	    				   String temp[] = txt.split("■");
	    				   
	    				   if(answer.equals(temp[0].replace("-", ""))) {
	    					   if(temp[3].equals("0") || temp[3].equals("00") || Integer.parseInt(temp[6]) > Integer.parseInt(c.get(Calendar.YEAR)+""+c.get(Calendar.MONTH)+""+c.get(Calendar.DATE))) {
	    						   check = true;
	    						   System.out.println("삭제가 완료 되었습니다.");
	    						   continue;
	    					   } else {
	    	    				   System.out.println("재고가 남아 거나 유통기한이 지나지 않았습니다.");
	    					   }
	    				   } else {
		    				   newArray.add(temp[0]+"■"+temp[1]+"■"+temp[2]+"■"+temp[3]+"■"+temp[4]+"■"+temp[5]+"■"+temp[6]);
	    				   }
	    			   }
	    			   
	    			   reader.close();
	    			   
	    			   BufferedWriter writer = new BufferedWriter(new FileWriter("dat\\totalData.dat"));
	    			   if(check) {
	    				   for(int i=0; i<newArray.size(); i++) {
	    					   writer.write(newArray.get(i));
	    					   writer.newLine();
	    				   }
	    			   } else {
	    				   
	    			   }
	    			   
	    			   writer.close();
	    			   
	    			   break;
	    		   } else if(answer.equals("0")) {
	    			   break;
	    		   }
	    	   }
	    	   k++;
	       }         
	   } catch (Exception e) {
		   // TODO: handle exception
		   System.out.println("Inquire.searchNumberProduct() : " + e.toString());
	   }
   }

   /**
    * 제품 수정 UI 및 수정하고 싶은 일련번호 입력.
    */
   private static void informationProductUI() {
	   // TODO Auto-generated method stub
	   ui.logo("제품 수정");
	      
	   try {
		   function.reader("dat\\ClassificationData.dat");
	       
		   System.out.println("\n\n                        1. 제품 수정");
		   System.out.println("                         0. 뒤로가기\n");
		   
		   answer = function.input("입력");
		   
		   if(answer.equals("1")) {
			   answer = function.input("일련번호 입력");

			   if(effect.numberInspection(answer)) {
				   changeInformationProduct(answer);   
			   } else {
				   System.out.println("일련번호를 잘못 입력하였습니다.");   
			   }
		   } else if(answer.equals("0")) {
			   System.out.println("일련번호를 잘못 입력하였습니다.");
		   } 
	   } catch (Exception e) {
		   System.out.println("Inquire.enterProduct() : " + e.toString());
	   }
   }
   
   /**
    * 
    * @param answer - informationProductUI에서 사용자가 적은 일련번호
    * 사용자가 적은 일련번호에 정보 출력 및 수정하고 싶은 메뉴 선택
    */
   private static void changeInformationProduct(String answer) {
	   // TODO Auto-generated method stub
	   try {
		   BufferedReader reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
		   
		   String txt = null;
		   String number = null;
		   String result = null;
		   
		   while((txt=reader.readLine()) != null) {
			   String temp[] = txt.split("■");
			   
			   if(answer.equals(temp[0].replace("-", ""))) {
				   System.out.println("                        제품 번호 : "+temp[0]);
				   System.out.println("                        제품 이름 : "+temp[1]);
				   System.out.println("                        제품 수량 : "+temp[3]);
				   System.out.println("                        입고 날짜 : "+temp[5]);
				   System.out.println("                        유통 기한 : "+temp[6]);
				   
				   result = txt;
				   number = temp[0];
				   break;
			   }
		   }
		   
		   ui.bar();
		   System.out.println("수정할 사항을 선택해 주세요");
		   
		   String[] menuArray = {"제품명 ","수량","입고날짜","뒤로가기"};
		   ui.menu(menuArray, 4);

		   answer = function.input("입력");
		   
		   if(answer.equals("0")) {
			   
		   } else if(answer.equals("1") || answer.equals("2") || answer.equals("3")) {
			   finalChangeProduct(answer,number);
		   } else {
			   System.out.println("잘못 입력하셨습니다.");
		   }
	   } catch (Exception e) {
		   System.out.println("Inquire.changeInformationProduct() : " + e.toString());
	   }
   }
   
   /**
    * 
    * @param input - informationProductUI에서 사용자가 적은 일련번호
    * @param number - changeInformationProduct에서 사용자가 수정하고 싶은 메뉴 번호
    */
   private static void finalChangeProduct(String input, String number) {
	   // TODO Auto-generated method stub
	   try {
		   answer = function.input("바꾸려는 값 입력");
		   
		   BufferedReader reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
		   
		   ArrayList<String> array = new ArrayList<>();
		   
		   String txt = null;
		   
		   //제품명 1 - 1, 수량 2 - 3, 입고날짜 3 - 5
		   if(input.equals("2")) {
			   input = (Integer.parseInt(input)+1)+""; 
		   } else if(input.equals("3")) {
			   input = (Integer.parseInt(input)+2)+"";
		   }
		   
		   while((txt = reader.readLine()) != null) {
			   String[] temp = txt.split("■");
			   
			   if((number.replace("-", "")).equals(temp[0].replace("-", ""))) {
				   temp[Integer.parseInt(input)] = answer;
				   System.out.println(temp[Integer.parseInt(input)]);
			   }
			   array.add(temp[0]+"■"+temp[1]+"■"+temp[2]+"■"+temp[3]+"■"+temp[4]+"■"+temp[5]+"■"+temp[6]);
		   }
		   
		   reader.close();
		   
		   BufferedWriter writer = new BufferedWriter(new FileWriter("dat\\totalData.dat"));
		   
		   for(int i=0; i<array.size(); i++) {
			   writer.write(array.get(i));
			   writer.newLine();
		   }
		   
		   writer.close();
		   
		   System.out.println("수정이 완료 되었습니다.");

	   } catch (Exception e) {
		   System.out.println("Inquire.finalChangeProduct() : " + e.toString());
	   }
   }

   /**
    * 제품 검수 UI 
    * orderProduct에서 구매한 제품 출력
    */
   private static void productCheckUI() {
	   int total = 0;
	   
	   // TODO Auto-generated method stub
	   ui.logo("제품 검수");	   
	   
	   System.out.println("        [일련번호]        [수량]     [입고날짜]     [제품가격]     [제품명]");
	   
	   for(int i=0; i<array.size(); i++) {
		   //일련번호,수량,입고날짜,제품가격,제품명
		   String temp[] = array.get(i).split(",");
		   
		   System.out.println("      "+temp[0]+"          "+temp[1]+"        "+temp[2]+"         "+temp[5]+"         "+temp[4]);
		   total = total + (Integer.parseInt(temp[1]) * Integer.parseInt(temp[5]));
	   }
	   
	   System.out.println("      총 결제 금액 : "+total);
	   
	   System.out.println("=========================================================================================");
	   System.out.println("1.제고 추가");
	   System.out.println("*.뒤로가기");
	   
	   answer = function.input("입력");

	   if(answer.equals("1")) {
		   enterProduct();
	   } else if(answer.equals("*")) {
		  System.out.println("종료되었습니다.");
	   }
   }
   
   //totalData에 재고 추가하기, 제품 검수 기능
   /**
    * productCheckUI에서 재고 추가시, totalData.dat파일에 재고 추가.
    */
   private static void enterProduct() {
	   //TODO Auto-generated method stub
	   ArrayList<String> list = new ArrayList<>();
	   
	   try {
		   BufferedReader reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
		   
		   String txt = null;

		   for(int i=0; i<array.size(); i++) {
			   //arrayTemp - 일련번호,제품명,가격,수량,입고날짜
			   String arrayTemp[] = array.get(i).split(",");

			   while((txt = reader.readLine()) != null) {
				   String temp[] = txt.split("■");
				   
				   if((temp[0].replace("-", "")).equals(arrayTemp[0].replace("-", ""))) {
					   temp[5] = arrayTemp[2];
					   temp[6] = arrayTemp[3];
					   temp[3] = (Integer.parseInt(temp[3]) + Integer.parseInt(arrayTemp[1]))+"";
				   }

				   list.add(temp[0]+"■"+temp[1]+"■"+temp[2]+"■"+temp[3]+"■"+temp[4]+"■"+temp[5]+"■"+temp[6]);
			   }
		   }
		   
		   reader.close();
		   
		  BufferedWriter writer = new BufferedWriter(new FileWriter("dat\\totalData.dat"));
		   
		  int count = list.size();
		  
		  for(int i=0; i<count; i++) {
			   writer.write(list.get(i));
			   writer.newLine();
		   }
		   array.clear();
		   
		   writer.close();
		   
		   System.out.println("재고가 추가되었습니다.");
	   } catch (Exception e) {
		   System.out.println("Inquire.enterProduct() : " + e.toString());
	   }
   }

   /**
    * 제품 구매 UI
    * 전체 상품 출력 및 재고가 0인 제품 출력
    */
   private static void orderProductUI() {
	// TODO Auto-generated method stub
	   ui.logo("제품 주문");
	   System.out.println("        [일련번호]        [수량]     [입고날짜]     [유통기한]     [제품명]");
	   
	   try {
		   BufferedReader reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
		   
		   ArrayList<String> array = new ArrayList<>();
		   ArrayList<String> emptyArray = new ArrayList<>();
		   String txt = null;
		   
	       while((txt = reader.readLine()) != null) {
	    	   String temp[] = txt.split("■");
	    	   String emptyTemp[] = txt.split("■");
    	   
	    	   array.add("        "+temp[0]+"         "+temp[3]+"     "+temp[5]+"        "+temp[6]+"    "+temp[1]);
	    	   
	    	   if(Integer.parseInt(emptyTemp[3]) == 0) {
	    		   emptyArray.add("        "+temp[0]+"         "+temp[3]+"     "+temp[5]+"        "+temp[6]+"    "+temp[1]);
	    	   }
	       }
	       
	       int k=1;
	       int p=1;  
	       while(true) {
	    	   if(array.size() == 0) {
	    		   System.out.println("일련번호를 잘못 입력했습니다.");
	    		   break;
	    	   }

	    	   if(!array.get(k-1).equals(null)) {
	    		   System.out.println(array.get(k-1));
	    	   }

	           if(k%10==0 || array.size() == k) {
	    		   String[] menuArray = {"이전페이지 ","다음페이지","부족수량제품보기","뒤로가기"};
	    		   ui.menu(menuArray, 4);
	    		   
	               answer = function.input("번호 입력");

	               if(answer.equals("1") && k!=10 && array.size() != k) {
	                  k = k-20;
	               } else if(answer.equals("1") && k==10) {
	            	   k = k-10;
	               } else if(answer.equals("1") && array.size() == k) {
	            	   k = k-10-(array.size()%10);
	               } else if(answer.equals("2") && array.size() == k) {
	            	   k = k-(array.size()%10);;
	               } else if(answer.equals("3")) {
	            	   while(true) {
	            		   if(emptyArray.size()==0) {
	            			   System.out.println("부족한 상품이 없습니다.");
	            			   break;
	            		   }
	            		   
	            		   if(!emptyArray.get(p-1).equals(null)) {
	            			   System.out.println(emptyArray.get(p-1));
	            		   }
	            		   
	            		   if(p%10==0 || emptyArray.size()==p) {
	            			   System.out.println("\n        1.이전페이지        2.다음페이지        3.재고 주문 하기      0.뒤로가기\n");
	            			   
	            			   answer = function.input("번호 입력");

	        	               if(answer.equals("1") && p!=10 && emptyArray.size() != p) {
	        		                  p = p-20;
	        		               } else if(answer.equals("1") && p==10) {
	        		            	   p = p-10;
	        		               } else if(answer.equals("1") && emptyArray.size() == p) {
	        		            	   p = p-10-(emptyArray.size()%10);
	        		               } else if(answer.equals("2") && emptyArray.size() == p) {
	        		            	   p = p-(emptyArray.size()%10);;
	        		               } else if (answer.equals("3")) {
	        		            	   orderProduct();
	        		               } else if(answer.equals("0")) {
	        		            	   break;
	        		               }
	            		   }
	            		   p++;
	            	   }
	               } else if(answer.equals("0")) {
	            	   break;
	               }
	           }
	           k++;
	       }
		   
	   } catch (Exception e) {
		   System.out.println("Inquire.orderProduct() : " + e.toString());
	   }
   }
   
   /**
    * 주문하기
    * 배열에 주문할 일련번호의 정보 넣기 
    */
   private static void orderProduct() {
	  // TODO Auto-generated method stub
	  ui.logo("재고 주문");
	  
	  try {  
		  while(true) {
			  String numberAnswer = null;
			  String[] menuArray = {"주문하기","주문한 제품 보기","뒤로 가기"};
			  ui.menu(menuArray, 3);
			  
			  answer = function.input("번호 입력");

			  if(answer.equals("1")) {
				  System.out.println("\n");
				  answer = function.input("주문할 일련번호");
				  numberAnswer = function.input("수량");
				  
				  BufferedReader reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
				  
				  String txt = null;
				  String productName = "";
				  
				  if(!effect.numberInspection(answer)) {
					  System.out.println("잘못된 일련번호 입니다.");
					  continue;
				  }
				  
				  while((txt = reader.readLine()) != null) {
					  String[] temp = txt.split("■");
			  
					  if(answer.equals(temp[0].replace("-", ""))) {
						  if(Integer.parseInt(temp[3])==0) { 
							  productName = temp[0]+","+numberAnswer+","+c.get(Calendar.YEAR)+c.get(Calendar.MONTH)+c.get(Calendar.DATE)+","+expire(temp[0])+","+temp[1]+","+temp[2];
							  array.add(productName);
							  
							  System.out.println("\n\n         재고 주문을 성공하였습니다.\n");
							  break;
						  } else {
							  System.out.println("재고가 있는 제품 입니다.");
							  continue;
						  }
					  } 
				  }
			  } else if(answer.equals("2")) {
				  System.out.println("        [일련번호]        [수량]     [입고날짜]     [유통기한]     [제품명]");

				  for(int i=0; i<array.size(); i++) {
					  String[] temp = array.get(i).split(",");
					  
					  System.out.println("      "+temp[0]+"         "+temp[1]+"          "+temp[2]+"        "+temp[3]+"         "+temp[4]);
				  }
			  } else if(answer.equals("*")) {
				  break;
			  }
		  }
	  } catch (Exception e) {
		  System.out.println("Inquire.orderProduct() : " + e.toString());
  	  }
   }
   
   /**
    * 
    * @param number - orderProduct에 사용자가 적은 일련번호
    * @return 일련번호에 맞는 유통기한 값 넘기기
    */
   private static String expire(String number) {
	   // TODO Auto-generated method stub
	   String expireDay = null;
	   
	   if(number.substring(0,3).equals("001")) { //식음료 1년 유제품 1주일
		   if(number.substring(0, 5).equals("00110")) { //유제품일경우
			   c.add(Calendar.DATE, 7);
		   } else {
			   c.add(Calendar.YEAR, 1);
		   }
	   } else if(number.substring(0,3).equals("010")) { //과자류 2년
		   c.add(Calendar.YEAR, 2);
	   } else if(number.substring(0,3).equals("011")) { //식품 1년
		   c.add(Calendar.YEAR, 1);
	   } else if(number.substring(0,3).equals("100")) { //간편식사 7일
		   c.add(Calendar.DATE, 7);
	   } else if(number.substring(0,3).equals("101")) { //생활용품 3년
		   c.add(Calendar.YEAR, 3);
	   } else {//기호식품 2년
		   c.add(Calendar.YEAR, 2);
	   }
	   expireDay = c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH))+""+c.get(Calendar.DATE);
	   return expireDay;
   }

   /**
    * 제품 검색 UI
    * 해당 메뉴에 메서드 연결
    */
   private static void searchUI() {
      // TODO Auto-generated method stub
	  String[] menuArray = {"제품 번호 검색","카테고리 검색","뒤로 가기     ","종료"};
	  while(true) {
		  ui.logo("제품 검색");
		  ui.menu(menuArray, 4);
		  answer = function.input("입력");
		  
		  if(answer.equals("1")) searchNumberProduct();
		  else if(answer.equals("2")) searchCategoryProduct();
		  else if(answer.equals("3")) {break;}
		  else if(answer.equals("*")) System.exit(0);
		  else System.out.println("\n잘못 입력하였습니다. 다시 입력해주세요");
	  }
   }
   
   /**
    * 일련번호 검색 시, 제품 정보 출력
    */
   private static void searchNumberProduct() {
	// TODO Auto-generated method stub
	   ui.logo("제품 번호 검색");
	      
	   try {
		   function.reader("dat\\ClassificationData.dat");
		   
	       ArrayList<String> array = new ArrayList<>(); 
	         
	       while(true) {
	    	   answer = function.input("일련번호 입력");
    	   
	    	   if(effect.numberInspection(answer)) {
	    		   break;
	    	   } else {
	    		   System.out.println("일련번호를 잘 못 입력하였습니다.");
	    		   function.pause();
	    		   break;
	    	   }
	       }
	       
	       BufferedReader reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
	       
	       String txt = null;
	       
	       while((txt = reader.readLine()) != null) {
	    	   if(answer.length() == 10) {                                    //소분류
	    		   if((txt.replace("-", "").substring(0, 10)).equals(answer)) {
	    			   String temp[] = txt.split("■");

    		    	   ui.logo("제품 정보");
    				   
    		    	   System.out.println("\n                        제품 번호 : "+temp[0]);
    		    	   System.out.println("\n                        제품 이름 : "+temp[1]);
    		    	   System.out.println("\n                        제품 수량 : "+temp[3]);
    		    	   System.out.println("\n                        입고 날짜 : "+temp[5]);
    		    	   System.out.println("\n                        유통 기한 : "+temp[6]);
    		    	   function.pause();
    			   }
    		   } 
    	   }

    	   reader.close();
	   } catch (Exception e) {
		   System.out.println("Inquire.searchNumberProduct() : " + e.toString());
	   }
   }

   /**
    * 카테고리 중분류, 소분류 검색시
    * 해당 제품 출력
    */
   private static void searchCategoryProduct() {
      // TODO Auto-generated method stub
	  ui.logo("카테고리 검색");
      
      try {
         
         while(true) {
        	 function.reader("dat\\ClassificationData.dat");
        	 ArrayList<String> array = new ArrayList<>();
        	 
        	 answer = function.input("카테고리 번호 입력");
    	   
        	 if(effect.categoryInspection(answer)) {
                 BufferedReader reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
                 
                 String txt = null;
                 System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                 System.out.println("ㅣ        [일련번호]        [수량]     [입고날짜]     [유통기한]     [제품명]        ㅣ");
                 while((txt = reader.readLine()) != null) {
                	if(answer.length() == 7) {                                    //소분류
                		if((txt.replace("-", "").substring(0, 7)).equals(answer)) {
                			String temp[] = txt.split("■");
                			
                			array.add("      "+temp[0]+"        "+temp[3]+"         "+temp[5]+"        "+temp[6]+"     "+temp[1]+"  ");
                		}
                	} else if(answer.length() == 5) {                           //중분류
                		if((txt.replace("-", "").substring(0, 5)).equals(answer)) {
                			String temp[] = txt.split("■");
                       
                			array.add("      "+temp[0]+"        "+temp[3]+"         "+temp[5]+"        "+temp[6]+"     "+temp[1]);
                		}
                	} 
                 }
                 reader.close();
                 
                 int k=1;
                 int i=1;
                 while(true) {
                	if(array.size() == 0) {
                		break;
                	}

                	if(!array.get(k-1).equals(null)) {
                		System.out.println("ㅣ"+array.get(k-1)+"ㅣ");
                	}

                    if((k%5==0 && k!=0) || array.size() == k) {
                    	System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                       System.out.printf("\n                                     페이지 (%d/%d)\n",i,array.size()/5+1);
                       System.out.println("\n               1.이전페이지               2.다음페이지               0.뒤로가기\n");
                       
                       answer = function.input("번호 입력");
                       System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                       if(answer.equals("1") && k!=5 && array.size() != k) {
                          k = k-10;
                       } else if(answer.equals("1") && k==5) {
                    	   k = k-5;
                       } else if(answer.equals("1") && array.size() == k) {
                    	   k = k-5-(array.size()%5);
                       } else if(answer.equals("2") && array.size() == k) {
                    	   k = k-(array.size()%5);
                       } else if(answer.equals("0")) {
                          break;
                       }
                       i++;
                    }
                    k++;
                 }   
                 break;
        	 } else {
        		 System.out.println("카테고리 번호를 잘 못 입력하였습니다.");
        		 function.pause();
        		 break;
        	 }
         }
      } catch (Exception e) {
         // TODO: handle exception
    	  System.out.println("Inquire.searchNumberProduct() : " + e.toString());
      }
   }
}