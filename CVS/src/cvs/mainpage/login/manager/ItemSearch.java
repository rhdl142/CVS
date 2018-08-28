package cvs.mainpage.login.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import cvs.function.Order;
import cvs.function.UI;

/**
 * 
 * @author 장시영
 *
 */

/**
 * 
 * 제품별 매출 조회
 *
 */
public class ItemSearch {

   
   /**
    * ItemSearch 생성자 오버라이딩 (기본)
    */
   public ItemSearch() {
   }
   
   /**
    * ItemSearch 생성자 오버라이딩 (일별, 월별)
    * @param date - Sales에서 입력받은 기간
    */
   public ItemSearch(String date) {
      this.date = date;
   }
   
   /**
    * ItemSearch 생성자 오버라이딩 (분기별)
    * @param date - Sales에서 입력받은 년도
    * @param quarter - Sales에서 입력받은 분기
    */
   public ItemSearch(String date, String quarter) {
      this.date = date;
      this.quarters = quarter;
   }

   MoreItem more = new MoreItem(date);

   private static Scanner scan ;
   private static MoreItem moreitem;
   
   private static ArrayList<Order> olist;
   private static HashMap<String, String> map_가격;
   private static HashMap<String, Integer> price;
   private static HashMap<String, Integer> user;
   
   private static UI ui;
   private static String date;
   private static String quarters;
   
   static {
	  ui = new UI();
      scan = new Scanner(System.in);
      moreitem = new MoreItem();
      olist = new ArrayList<Order>();
      map_가격 = new HashMap<String, String>();
   }
   

   /**
    * @MethodName daySales
    * 일별 매출액 조회
    */
   public static void daySales() { //일별 매출액
      
      String year = date.substring(0, 4);
      String month = date.substring(4, 6);
      String day = date.substring(6);
      
      ui.bar();
      System.out.println("\t\t\t\t" + year + "년 " + month + "월 " + day + "일 제품별 매출 조회" );
      ui.bar();
      
      int price_식음료 = 0;
      int price_과자류 = 0;
      int price_식품 = 0;
      int price_간편식사 = 0;
      int price_생필품 = 0;
      int price_기호식품 = 0;
      
      int user_식음료 = 0;
      int user_과자류 = 0;
      int user_식품 = 0;
      int user_간편식사 = 0;
      int user_생필품 = 0;
      int user_기호식품 = 0;
      
      int totalUser = 0; //총 이용자 수
      int priceCount = 0;
      int itemPrice = 0; //수량 * 가격
      
      map_가격();
      common();
      
      
      for(Order order : olist) {
         
         
         if(order.getBuyDate().equals(date)) { //날짜가 같으면
            
            
            switch(order.getItemNum().substring(0, 3)) { //대분류
            
            case "001" : 
               price_식음료 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_식음료++;
               break;
            case "010" : 
               price_과자류 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_과자류++;
               break;
            case "011" : 
               price_식품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_식품++;
               break;
            case "100" : 
               price_간편식사 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_간편식사++;
               break;
            case "101" : 
               price_생필품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_생필품++;
               break;
            case "110" : 
               price_기호식품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_기호식품++;
               break;
            }
         }
      }
      totalUser = user_식음료 + user_과자류 + user_식품 + user_간편식사 + user_생필품 + user_기호식품;
      
      String[][] priceList = new String[10][6];
      
      
      for(int i = 0; i < 6; i++) {
         for(int j = 0; j < 10; j++) {
            priceList[j][i] = "";
            
         }
      }
      System.out.println();
      System.out.println("\t\t\t■(단위) : 5천 원                * 기호식품의 단위는 5만 원");
      
      
      for(int i = 0; i < 6; i++) { //각 대분류 ■ count
         
         //■ 당 5만 원
         switch(i) {
         case 0 : 
            if(price_식음료 == 0) {
               priceCount = 0;
               break;
            } 
            priceCount = price_식음료 / 50000;
            break;
         case 1 : 
            if(price_과자류 == 0) {
               priceCount = 0;
               break;
            }
            priceCount = price_과자류 / 50000;
            break;
         case 2 : 
            if(price_식품 == 0) {
               priceCount = 0;
               break;
            }
            priceCount = price_식품 / 50000;
            break;
         case 3 : 
            if(price_간편식사 == 0) {
               priceCount = 0;
               break;
            }
            priceCount = price_간편식사 / 50000;
            break;
         case 4 : 
            if(price_생필품 == 0) {
               priceCount = 0;
               break;
            }
            priceCount = price_생필품 / 50000;
            break;
         case 5 : 
            if(price_기호식품 == 0) {
               priceCount = 0;
               break;
            }
            priceCount = price_기호식품 / 50000;
            break;
            
         }
         
         for(int j = 9; j >= 10 - priceCount; j--) {
            priceList[j][i] = "■";
         }
      }
      
      
      dump(priceList);
      System.out.println("\t\t\t식음료\t과자류\t식품  간편식사  생필품  기호식품");
      ui.bar();
      System.out.printf("\t\t\t[총 이용자 수] %,d명 [총 매출액] %,d원\n", totalUser, price_식음료 + price_과자류 + price_식품 + price_간편식사 + price_생필품 + price_기호식품);
      System.out.println();
      System.out.println("\t\t\t[ 대분류 ]\t[이용자 수]\t[매출액]");
      System.out.printf("\t\t\t식음료\t\t%,d명\t\t%,15d원\n", user_식음료, price_식음료);
      System.out.printf("\t\t\t과자류\t\t%,d명\t\t%,15d원\n", user_과자류, price_과자류);
      System.out.printf("\t\t\t식품\t\t%,d명\t\t%,15d원\n", user_식품, price_식품);
      System.out.printf("\t\t\t식음료\t\t%,d명\t\t%,15d원\n", user_간편식사, price_간편식사);
      System.out.printf("\t\t\t생필품\t\t%,d명\t\t%,15d원\n", user_생필품, price_생필품);
      System.out.printf("\t\t\t기호식품\t%,d명\t\t%,15d원\n", user_기호식품, price_기호식품);
      
      pause();
      
      sel(scan);
         
   }
   
   /**
    * @MethodName monthSales
    * 월별 매출액 조회
    */
   public static void monthSales() {
      
      String year = date.substring(0, 4);
      String month = date.substring(4, 6);
      
      ui.bar();
      System.out.println("\t\t        " + year + "년 " + month + "월 제품별 매출 조회");
      ui.bar();
      
      int price_식음료 = 0;
      int price_과자류 = 0;
      int price_식품 = 0;
      int price_간편식사 = 0;
      int price_생필품 = 0;
      int price_기호식품 = 0;
      
      int user_식음료 = 0;
      int user_과자류 = 0;
      int user_식품 = 0;
      int user_간편식사 = 0;
      int user_생필품 = 0;
      int user_기호식품 = 0;
      
      int userCount = 0; //이용자 수
      int priceCount = 0; //총 매출액
      
      
      common();
      map_가격();
      
      for(Order order : olist) {
         
         if(order.getBuyDate().substring(0, 6).equals(date)) { //날짜가 같으면
            
            switch(order.getItemNum().substring(0, 3)) { //대분류
            
            case "001" : 
               price_식음료 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_식음료++;
               break;
            case "010" : 
               price_과자류 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_과자류++;
               break;
            case "011" : 
               price_식품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_식품++;
               break;
            case "100" : 
               price_간편식사 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_간편식사++;
               break;
            case "101" : 
               price_생필품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_생필품++;
               break;
            case "110" : 
               price_기호식품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_기호식품++;
               break;
            }
         }
      }
      
      //userCount : 총 이용자 수
      userCount = user_식음료 + user_과자류 + user_식품 + user_간편식사 + user_생필품 + user_기호식품;
      
      String[][] priceList = new String[10][6];
      
      
      for(int i = 0; i < 6; i++) {
         for(int j = 0; j < 10; j++) {
            priceList[j][i] = "";
            
         }
      }
      
      System.out.println();
      System.out.println("\t\t■(단위) : 5만 원               * 기호식품 단위 :  50만 원");
      
      for(int i = 0; i < 6; i++) { //각 대분류 ■ count
         
         //■ 당 50만원
         switch(i) {
         case 0 : 
            if(price_식음료 == 0) {
               priceCount = 0;
               break;
            }
            priceCount = price_식음료 / 50000;
            break;
         case 1 : 
            if(price_과자류 == 0) {
               priceCount = 0;
               break;
            }
            priceCount = price_과자류 / 50000;
            break;
         case 2 : 
            if(price_식품 == 0) {
               priceCount = 0;
               break;
            }
            priceCount = price_식품 / 50000;
            break;
         case 3 : 
            if(price_간편식사 == 0) {
               priceCount = 0;
               break;
            }
            priceCount = price_간편식사 / 50000;
            break;
         case 4 : 
            if(price_생필품 == 0) {
               priceCount = 0;
               break;
            }
            priceCount = price_생필품 / 50000;
            break;
         case 5 : 
            if(price_기호식품 == 0) {
               priceCount = 0;
               break;
            }
            priceCount = price_기호식품 / 500000;
            break;
            
         }
         for(int j = 9; j >= 10 - priceCount; j--) {
            priceList[j][i] = "■";
         }
      }
      
      dump(priceList);
      System.out.println("\t식음료\t과자류\t식품  간편식사  생필품  기호식품");
      ui.bar();
      System.out.printf("\t[총 이용자 수] %,d명 [총 매출액] %,d원\n", userCount, price_식음료 + price_과자류 + price_식품 + price_간편식사 + price_생필품 + price_기호식품);
      System.out.println();
      System.out.println("\t[ 대분류 ]\t[이용자 수]\t\t[매출액]");
      System.out.printf("\t식음료\t\t    %,d명\t%,15d원\n", user_식음료, price_식음료);
      System.out.printf("\t과자류\t\t    %,d명\t%,15d원\n", user_과자류, price_과자류);
      System.out.printf("\t식품\t\t    %,d명\t%,15d원\n", user_식품, price_식품);
      System.out.printf("\t식음료\t\t    %,d명\t%,15d원\n", user_간편식사, price_간편식사);
      System.out.printf("\t생필품\t\t    %,d명\t%,15d원\n", user_생필품, price_생필품);
      System.out.printf("\t기호식품\t    %,d명\t%,15d원\n", user_기호식품, price_기호식품);
      
      pause();
      
      sel(scan);
      
   }//month
   
   /**
    * @MethodName quartersSales
    * 분기별 매출액 조회
    */
   public static void quartersSales() {
      
      String year = date.substring(0, 4);
      String quart = quarters;
      
      ui.bar();
      System.out.println("\t\t\t\t" + year + "년 " + quart + "분기 제품별 매출 조회" );
      ui.bar();
      
      
      int price_식음료 = 0;
      int price_과자류 = 0;
      int price_식품 = 0;
      int price_간편식사 = 0;
      int price_생필품 = 0;
      int price_기호식품 = 0;
      
      int user_식음료 = 0;
      int user_과자류 = 0;
      int user_식품 = 0;
      int user_간편식사 = 0;
      int user_생필품 = 0;
      int user_기호식품 = 0;
      
      map_가격();
      common();
      
      for(Order order : olist) {
         
         if(order.getBuyDate().startsWith(year)) { //년도가 같으면
            
            if(quart.equals("1")) { //1분기 (1~3월)
               
               switch(order.getBuyDate().substring(4, 6)) { //month
               
               case "01" : 
               case "02" : 
               case "03" :
                  
                  switch(order.getItemNum().substring(0, 3)) { //대분류
                  
                  case "001" : 
                     price_식음료 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_식음료++;
                     break;
                  case "010" : 
                     price_과자류 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_과자류++;
                     break;
                  case "011" : 
                     price_식품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_식품++;
                     break;
                  case "100" : 
                     price_간편식사 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_간편식사++;
                     break;
                  case "101" : 
                     price_생필품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_생필품++;
                     break;
                  case "110" : 
                     price_기호식품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_기호식품++;
                     break;
                  }
                  break;
               
               }
               
               
            } else if (quart.equals("2")) {
               
               switch(order.getBuyDate().substring(4, 6)) { //month
               
               case "04" : 
               case "05" : 
               case "06" :
                  
                  switch(order.getItemNum().substring(0, 3)) { //대분류
                  
                  case "001" : 
                     price_식음료 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_식음료++;
                     break;
                  case "010" : 
                     price_과자류 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_과자류++;
                     break;
                  case "011" : 
                     price_식품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_식품++;
                     break;
                  case "100" : 
                     price_간편식사 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_간편식사++;
                     break;
                  case "101" : 
                     price_생필품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_생필품++;
                     break;
                  case "110" : 
                     price_기호식품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_기호식품++;
                     break;
                  }
                  break;
               
               }
               
            } else if (quart.equals("3")) {
               
               switch(order.getBuyDate().substring(4, 6)) { //month
               
               case "07" : 
               case "08" : 
               case "09" :
                  
                  switch(order.getItemNum().substring(0, 3)) { //대분류
                  
                  case "001" : 
                     price_식음료 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_식음료++;
                     break;
                  case "010" : 
                     price_과자류 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_과자류++;
                     break;
                  case "011" : 
                     price_식품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_식품++;
                     break;
                  case "100" : 
                     price_간편식사 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_간편식사++;
                     break;
                  case "101" : 
                     price_생필품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_생필품++;
                     break;
                  case "110" : 
                     price_기호식품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_기호식품++;
                     break;
                  }
                  break;
               
               }
               
            } else if (quart.equals("4")) {
               
               switch(order.getBuyDate().substring(4, 6)) { //month
               
               case "10" : 
               case "11" : 
               case "12" :
                  
                  switch(order.getItemNum().substring(0, 3)) { //대분류
                  
                  case "001" : 
                     price_식음료 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_식음료++;
                     break;
                  case "010" : 
                     price_과자류 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_과자류++;
                     break;
                  case "011" : 
                     price_식품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_식품++;
                     break;
                  case "100" : 
                     price_간편식사 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_간편식사++;
                     break;
                  case "101" : 
                     price_생필품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_생필품++;
                     break;
                  case "110" : 
                     price_기호식품 += Integer.parseInt(map_가격.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
                     user_기호식품++;
                     break;
                  }
                  break;
               
               }
               
            }
         }
         
      }
            
            int userCount = user_식음료 + user_과자류 + user_식품 + user_간편식사 + user_생필품 + user_기호식품; //총 이용자 수
            
            
            String[][] priceList = new String[10][6];
            
            
            for(int i = 0; i < 6; i++) {
               for(int j = 0; j < 10; j++) {
                  priceList[j][i] = "";
                  
               }
            }

            
            System.out.println("■(단위) : 50만 원");
            
            int priceCount = 0;//■
            
            for(int i = 0; i < 6; i++) { //각 대분류 ■ count
               
               //■ 당 50만원
               switch(i) {
               case 0 : 
                  if(price_식음료 == 0) {
                     priceCount = 0;
                     break;
                  }
                  priceCount = price_식음료 / 500000;
                  break;
               case 1 : 
                  if(price_과자류 == 0) {
                     priceCount = 0;
                     break;
                  }
                  priceCount = price_과자류 / 500000;
                  break;
               case 2 : 
                  if(price_식품 == 0) {
                     priceCount = 0;
                     break;
                  }
                  priceCount = price_식품 / 500000;
                  break;
               case 3 : 
                  if(price_간편식사 == 0) {
                     priceCount = 0;
                     break;
                  }
                  priceCount = price_간편식사 / 500000;
                  break;
               case 4 : 
                  if(price_생필품 == 0) {
                     priceCount = 0;
                     break;
                  }
                  priceCount = price_생필품 / 500000;
                  break;
               case 5 : 
                  if(price_기호식품 == 0) {
                     priceCount = 0;
                     break;
                  }
                  priceCount = price_기호식품 / 5000000;
                  break;
               
               }
               
               for(int j = 9; j >= 10 - priceCount; j--) {
                  priceList[j][i] = "■";
               }
            }
            
            dump(priceList);
            System.out.println("식음료\t과자류\t식품  간편식사  생필품  기호식품");
            ui.bar();
            System.out.printf("[총 이용자 수] %,d명 [총 매출액] %,d원\n", userCount, price_식음료 + price_과자류 + price_식품 + price_간편식사 + price_생필품 + price_기호식품);
            System.out.println();
            System.out.println("[ 대분류 ]\t[이용자 수]\t[매출액]");
            System.out.printf("식음료\t\t%,d명\t\t%,d원\n", user_식음료, price_식음료);
            System.out.printf("과자류\t\t%,d명\t\t%,d원\n", user_과자류, price_과자류);
            System.out.printf("식품\t\t%,d명\t\t%,d원\n", user_식품, price_식품);
            System.out.printf("식음료\t\t%,d명\t\t%,d원\n", user_간편식사, price_간편식사);
            System.out.printf("생필품\t\t%,d명\t\t%,d원\n", user_생필품, price_생필품);
            System.out.printf("기호식품\t%,d명\t\t%,d원\n", user_기호식품, price_기호식품);
            
            pause();
            
            sel(scan);
   }

   /**
    * 다음 메뉴 select
    * @param scan - 하고자 하는 메뉴 입력
    */
   private static void sel(Scanner scan) {
      boolean loop = true;
      
      while(true) {
         
         ui.bar();
            System.out.println("\t\t\t1. 제품군 매출 상세 보기");
            System.out.println("\t\t\t0. 처음으로");
            System.out.println();
            
            System.out.print("\t\t\t입력 : ");
         
         int sel = scan.nextInt();
         System.out.println();
         switch (sel) {
         case 1 :
            
            System.out.println("\t\t\t1.식음료  2.과자류  3.식품  4.간편식사  5.생필품  6.기호식품");
            System.out.println();
            System.out.print("\t\t\t입력 : ");
            
            scan.nextLine(); //남은 엔터 버리기
            
            sel = scan.nextInt();
            
            ui.bar();
            
            switch (sel) {
            case 1 : 
               moreitem.Item001(olist, map_가격, date); break; //식음료
            case 2 :
               moreitem.Item010(olist, map_가격, date); break; //과자류
            case 3 :
               moreitem.Item011(olist, map_가격, date); break; //식품
            case 4 : 
               moreitem.Item100(olist, map_가격, date); break; //간편식사
            case 5 : 
               moreitem.Item101(olist, map_가격, date); break; //생필품
            case 6 : 
               moreitem.Item110(olist, map_가격, date); break; //기호식품
            case 0 :
               //뒤로가기
            default :
               loop = false; 
               System.out.println("다시 입력해주세요.");
               break;
            }
            break;
         case  0 : 
            //뒤로가기
            Sales.main(null);
            break;
         default : 
            loop = false;
            System.out.println("\t\t\t다시 입력해주세요.");
            break;
         }
         
         
      }
   }
   
   /**
    * 공통 order.dat을 저장
    * @return ArrayList<Order> - 공통 order.dat을 저장한 ArrayList 반환
    */
   private static ArrayList<Order> common() {
      
      try {
         
         BufferedReader reader = new BufferedReader(new FileReader("dat\\order.dat"));
         
         String line = "";
         
         String[] tempO = null;
         
         while((line = reader.readLine()) != null) {
            
            tempO = line.split("■");
            olist.add(new Order(tempO[0], tempO[1], tempO[2], tempO[3], tempO[4]) ); //order data 넣기
         }
         reader.close();
         
      } catch (Exception e) {
         System.out.println("ItemGraph.daySales() : " + e.toString());
      }
      
      return olist;
   }
   
   /**
    * 공통 totalData.dat을 저장
    * @return HashMap<String, String> - key : 제품번호, values : 가격
    */
   private static HashMap<String, String> map_가격() {
      try {
         BufferedReader reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
         
         String line = "";
         
         String[] tempT = null;
         
         while((line = reader.readLine()) != null) {
            
            tempT = line.split("■");
            map_가격.put(tempT[0],tempT[2]); 
         }
         reader.close();
      } catch (Exception e) {
         System.out.println("ItemSearch.map_가격() : " + e.toString());
      }
      
      return map_가격;
   }
   
   /**
    * 매출액 그래프 출력
    * @param temp - 출력하고자 하는 배열 입력
    */
   private static void dump(String[][] temp) { //메소드 오버로딩
      
      for(int i = 0; i < temp.length; i++){
         for(int j = 0; j <temp[0].length; j++){
            if (j == 0) {
               System.out.printf("\t\t\t%s", temp[i][0]);
            }
            System.out.printf("\t%s", temp[i][j]);
         }
         System.out.println();
      }
      System.out.println();
   }//dump
   
   
   /**
    * 정지 시간
    */
   private static void pause() {
         
         Scanner scan = new Scanner(System.in);
         System.out.println();
         System.out.println("\t\t\t계속 진행하시려면 아무키나 눌러주세요.");
         scan.nextLine();
      }

}