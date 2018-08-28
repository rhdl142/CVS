package cvs.mainpage.login.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import cvs.function.Order;
import cvs.function.UI;


/**
 * 제품별 매출액 상세 조회
 * @author 장시영
 *
 */
public class MoreItem {
   
   /**
    * MoreItem 생성자 오버라이딩 (기본)
    */
   public MoreItem() {
   }

   /**
    * MoreItem 생성자 오버라이딩 (일별, 월별)
    * @param date - Sales에서 입력받은 기간
    */
   public MoreItem(String date) {
      this.date = date;
   }
   
   /**
    * MoreItem 생성자 오버라이딩 (분기별)
    * @param date - Sales에서 입력받은 년도
    * @param quarters - Sales에서 입력받은 분기
    */
   public MoreItem(String date, String quarters) {
      this.date = date;
      this.quarters = quarters;
   }
   
   private static UI ui;
   private static ItemSearch itemgraph;
   private static String date;
   private static String quarters;
   
   static {
	   ui = new UI();
      itemgraph = new ItemSearch();
   }
   
   
   /**
    * 대분류 코드가 001인 제품 매출액
    * @param olist - ItemSearch에서 받은 order.dat를 저장한 ArrayList<Order>
    * @param map - ItemSearch에서 받은 totalData.dat의 제품번호와 제품가격을 저장한 HashMap<String, String>
    * @param date - Sales에서 입력받은 기간
    */
   public static void Item001(ArrayList<Order> olist, HashMap<String, String> map, String date) { //식음료
         
      //00 01 10 음료 주류 유제품
      
      int[] price = new int[3];
      
      int user_음료 = 0;
      int user_주류 = 0;
      int user_유제품 = 0;
      
      for(int i = 0; i < price.length; i++) {
         price[i]=0;
      }
      
      for(Order order : olist) {
         
         if(order.getBuyDate().contains(date)) {
         
            switch(order.getItemNum().substring(0, 6)) {
            case "001-00":
               price[0] += Integer.parseInt(map.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_음료++;
               break;
            case "001-01" : 
               price[1] += Integer.parseInt(map.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_주류++;
               break;
            case "001-10" : 
               price[2] += Integer.parseInt(map.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_유제품++;
               break;
            }
         }
      }
      int totalUser = user_음료 + user_주류 + user_유제품;
      
      int sum = 0;
      for(int i = 0; i < price.length; i++) {
         sum += price[i];
      }
      System.out.println();
         System.out.println("\t\t\t\t\t┌─────┐");
         System.out.println("\t\t\t\t\t│  식음료  │");
         System.out.println("\t\t\t\t\t└─────┘");
      System.out.printf("\t\t\t[이용자 수] %,d명 , [총 매출액] %,d원\n", totalUser, sum);
   
      Graph(price);
      
      System.out.println("\t\t\t음료\t주류\t유제품");
      ui.bar();
      System.out.println();
      System.out.println("\t\t\t[ 중분류 ]\t[이용자 수]\t[매출액]");
      System.out.printf("\t\t\t음료\t\t%,d명\t\t%,d원\n", user_음료, price[0]);
      System.out.printf("\t\t\t주료\t\t%,d명\t\t%,d원\n", user_주류, price[1]);
      System.out.printf("\t\t\t유제품\t\t%,d명\t\t%,d원\n", user_유제품, price[2]);
      
      pause();
   }
   
   /**
    * 대분류 코드가 010인 제품 매출액
    * @param olist - ItemSearch에서 받은 order.dat를 저장한 ArrayList<Order>
    * @param map - ItemSearch에서 받은 totalData.dat의 제품번호와 제품가격을 저장한 HashMap<String, String>
    * @param date - Sales에서 입력받은 기간
    */
   public void Item010(ArrayList<Order> olist, HashMap<String, String> map, String date) { //과자류
      
      int[] price = new int[2];
      
      int user_스낵 = 0;
      int user_껌 = 0;
      
      for(int i = 0; i < price.length; i++) {
         price[i]=0;
      }
      
      for(Order order : olist) {
         if(order.getBuyDate().contains(date)) {
            
            switch(order.getItemNum().substring(0, 6)) {
            case "010-00":
               price[0] += Integer.parseInt(map.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_스낵++;
               break;
            case "010-01" : 
               price[1] += Integer.parseInt(map.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_껌++;
               break;
            }
         }
      }
      
      int totalUser = user_스낵 + user_껌;
      
      int sum = 0;
      for(int i = 0; i < price.length; i++) {
         sum += price[i];
      }
      System.out.println();
      System.out.printf("[과자류]\n이용자 수 : %,d명 총 매출액 : %,d원\n", totalUser, sum);
      Graph(price);
      
      ui.bar();
      System.out.println("스낵\t껌\n/비스켓\t초콜릿/캔디\t");
      System.out.println();
      System.out.println("[ 중분류 ]\t[이용자 수]\t[매출액]");
      System.out.printf("스낵/비스켓\t\t%,d명\t\t%,d원\n", user_스낵, price[0]);
      System.out.printf("껌/초콜릿/캔디\t\t%,d명\t\t%,d원\n", user_껌, price[1]);
      
      pause();
   }
   
   /**
    * 대분류 코드가 011인 제품 매출액
    * @param olist - ItemSearch에서 받은 order.dat를 저장한 ArrayList<Order>
    * @param map - ItemSearch에서 받은 totalData.dat의 제품번호와 제품가격을 저장한 HashMap<String, String>
    * @param date - Sales에서 입력받은 기간
    */
   public void Item011(ArrayList<Order> olist, HashMap<String, String> map, String date) { //식품
      int[] price = new int[2];
      
      int user_라면 = 0;
      int user_안주류 = 0;
      
      for(int i = 0; i < price.length; i++) {
         price[i]=0;
      }
      
      for(Order order : olist) {
         
         if(order.getBuyDate().contains(date)) {
            
            switch(order.getItemNum().substring(0, 6)) {
            case "011-00":
               price[0] += Integer.parseInt(map.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_라면++;
               break;
            case "011-01" : 
               price[1] += Integer.parseInt(map.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_안주류++;
               break;
            }
         }
      }
      
      int totalUser = user_라면 + user_안주류;
      
      int sum = 0;
      for(int i = 0; i < price.length; i++) {
         sum += price[i];
      }
      System.out.println();
      System.out.printf("[식품류]\n이용자 수 : %,d명 총 매출액 : %,d원\n", totalUser, sum);
      Graph(price);
      
      ui.bar();
      System.out.println("라면\t안주류\t");
      System.out.println();
      System.out.println("[ 중분류 ]\t[이용자 수]\t[매출액]");
      System.out.printf("라면\t\t%,d명\t\t%,d원\n", user_라면, price[0]);
      System.out.printf("안주류\t\t%,d명\t\t%,d원\n", user_안주류, price[1]);
      
      pause();
   }
   
   /**
    * 대분류 코드가 10인 제품 매출액
    * @param olist - ItemSearch에서 받은 order.dat를 저장한 ArrayList<Order>
    * @param map - ItemSearch에서 받은 totalData.dat의 제품번호와 제품가격을 저장한 HashMap<String, String>
    * @param date - Sales에서 입력받은 기간
    */
   public void Item100(ArrayList<Order> olist, HashMap<String, String> map, String date) {
      int[] price = new int[3];
      
      int user_도시락 = 0;
      int user_샌드위치 = 0;
      int user_김밥 = 0;
      
      for(int i = 0; i < price.length; i++) {
         price[i]=0;
      }
      
      for(Order order : olist) {
         
         if(order.getBuyDate().contains(date)) {
            
            switch(order.getItemNum().substring(0, 6)) {
            case "100-00":
               price[0] += Integer.parseInt(map.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_도시락++;
               break;
            case "100-01" : 
               price[1] += Integer.parseInt(map.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_샌드위치++;
               break;
            case "100-10" : 
               price[1] += Integer.parseInt(map.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_김밥++;
               break;
            }
         }
         
      }
      
      int totalUser = user_도시락+ user_샌드위치 + user_김밥;
      
      int sum = 0;
      for(int i = 0; i < price.length; i++) {
         sum += price[i];
      }
      System.out.println();
      System.out.printf("[간편식사]\n이용자 수 : %,d명 총 매출액 : %,d원\n", totalUser, sum);
      Graph(price);
      
      ui.bar();
      System.out.println("도시락\t샌드위치\t김밥\t");
      System.out.println();
      System.out.println("[ 중분류 ]\t[이용자 수]\t[매출액]");
      System.out.printf("도시락\t\t%,d명\t\t%,d원\n", user_도시락, price[0]);
      System.out.printf("샌드위치/햄버거\t%,d명\t\t%,d원\n", user_샌드위치, price[1]);
      System.out.printf("주먹밥/김밥\t\t%,d명\t\t%,d원\n", user_김밥, price[2]);
      
      pause();
   }
   
   /**
    * 대분류 코드가 101인 제품 매출액
    * @param olist - ItemSearch에서 받은 order.dat를 저장한 ArrayList<Order>
    * @param map - ItemSearch에서 받은 totalData.dat의 제품번호와 제품가격을 저장한 HashMap<String, String>
    * @param date - Sales에서 입력받은 기간
    */
   public void Item101(ArrayList<Order> olist, HashMap<String, String> map, String date) {
      int[] price = new int[3];
      
      int user_세안도구 = 0;
      int user_생필품 = 0;
      int user_의류 = 0;
      
      for(int i = 0; i < price.length; i++) {
         price[i]=0;
      }
      
      for(Order order : olist) {
         
         if(order.getBuyDate().contains(date)) {
            
            switch(order.getItemNum().substring(0, 6)) {
            case "101-00":
               price[0] += Integer.parseInt(map.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_세안도구++;
               break;
            case "101-01" : 
               price[1] += Integer.parseInt(map.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_생필품++;
               break;
            case "101-10" : 
               price[1] += Integer.parseInt(map.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_의류++;
               break;
            }
         }
      }
      
      int totalUser = user_세안도구 + user_생필품 + user_의류;
      
      int sum = 0;
      for(int i = 0; i < price.length; i++) {
         sum += price[i];
      }
      System.out.println();
      System.out.printf("[생활용품]\n이용자 수 : %,d명 총 매출액 : %,d원\n", totalUser, sum);
      Graph(price);
      
      ui.bar();
      System.out.println("세안도구\t생필품\t의류\t");
      System.out.println();
      System.out.println("[ 중분류 ]\t[이용자 수]\t[매출액]");
      System.out.printf("세안도구\t\t%,d명\t\t%,d원\n", user_세안도구, price[0]);
      System.out.printf("생필품\t\t%,d명\t\t%,d원\n", user_생필품, price[1]);
      System.out.printf("의류\t\t\t%,d명\t\t%,d원\n", user_의류, price[2]);
      
      pause();
   }
   
   /**
    * 대분류 코드가 110인 제품 매출액
    * @param olist - ItemSearch에서 받은 order.dat를 저장한 ArrayList<Order>
    * @param map - ItemSearch에서 받은 totalData.dat의 제품번호와 제품가격을 저장한 HashMap<String, String>
    * @param date - Sales에서 입력받은 기간
    */
   public void Item110(ArrayList<Order> olist, HashMap<String, String> map, String date) {
      int[] price = new int[1];
      
      int user_담배 = 0;
      
      for(int i = 0; i < price.length; i++) {
         price[i]=0;
      }
      
      for(Order order : olist) {
         
         if(order.getBuyDate().contains(date)) {
            
            switch(order.getItemNum().substring(0, 6)) {
            case "110-00":
               price[0] += Integer.parseInt(map.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum());
               user_담배++;
               break;
            }
         }
      }
      
      int sum = 0;
      for(int i = 0; i < price.length; i++) {
         sum += price[i];
      }
      System.out.println();
      System.out.printf("[기호식품]\n이용자 수 : %,d명 총 매출액 : %,d원\n", user_담배, sum);
      Graph(price);
      
      ui.bar();
      System.out.println("담배\t");
      System.out.println();
      System.out.println("[ 중분류 ]\t[이용자 수]\t[매출액]");
      System.out.printf("담배\t\t%,d명\t\t%,d원\n", user_담배, price[0]);
      
      pause();
   }
   
   /**
    * 중분류 그래프를 출력
    * @param price - 중분류의 갯수
    */
   public static void Graph(int[] price) { //중분류 그래프
      
      int priceCount = 0;
      //int count = 0;
      
      String[][] priceList = new String[10][price.length];
      
      for(int i = 0; i < price.length; i++) {
         for(int j = 0; j < 10; j++) {
            priceList[j][i] = "";
         }
      }
      System.out.println();
      System.out.println("\t\t■(단위) : 만 원");
      
      for(int i = 0; i < price.length; i++) {
         
         switch(i) {
         case 0 : 
            if(price[0] == 0) {
               priceCount = 0;
               break;
            } 
            priceCount = price[0] / 10000;
            break;
         case 1 :
            if(price[1] == 0) {
               priceCount = 0;
               break;
            } 
            priceCount = price[1] / 10000;
            break;
         case 2 :
            if(price[2] == 0) {
               priceCount = 0;
               break;
            } 
            priceCount = price[2] / 10000;
            break;
         case 3 :
            if(price[3] == 0) {
               priceCount = 0;
               break;
            } 
            priceCount = price[3] / 10000;
            break;
         }
         for(int j = 9; j >= 10 - priceCount; j--) {
            priceList[j][i] = "■";
         }
      }
      dump(priceList);
   }
   
   
   /**
    * 매출액 그래프 출력
    * @param temp - 출력하고자 하는 배열 입력
    */
   public static void dump(String[][] temp) { //메소드 오버로딩
      
      for(int i = 0; i < temp.length; i++){
         for(int j = 0; j <temp[0].length; j++){
            
            if(temp[i][j] != null) {
            
               if (j == 0) {
                  System.out.printf("\t\t\t%s", temp[i][0]);
               }
            System.out.printf("\t%s", temp[i][j]);
            }   
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
         System.out.println("\t\t\t계속 진행하시려면 엔터키를 눌러주세요.");
         scan.nextLine();
      }
}