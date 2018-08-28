package cvs.mainpage.login.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import cvs.function.Function;
import cvs.function.UI;

/**
 * 
 * @author 한동군
 * 관리자 매출 관리 클래스
 */

public class Sales {
   
   private static Scanner scan; 
   private static final String DATA; //파일 주소
   private static final String NUMBER; //파일 주소
   public static Function function; 
   private static UI ui;
   
   
   static {
	   ui = new UI();
      scan = new Scanner(System.in); //스캐너 이용 입력 받기
      DATA = "dat\\order.dat"; //파일 주소
      NUMBER = "dat\\totalData.dat"; //파일 주소
      function = new Function(); //CommonFunction클래스 객체
   }
   /**
    * 관리자 매출 관리 입력 창.
    * @param main
    * 
    */
   
   public static void main(String[] args) {
      ui.logo("관리자 매출 관리");
      
      System.out.println("\t\t1. 매출 조회");
      System.out.println("\t\t2. 이번 달 진열 추천");
      System.out.println("\t\t3. 상위 메뉴 돌아가기");

      System.out.println("\t\t*. 종료");
      System.out.println();
      System.out.print("\t\t입력(번호) : ");
      String num = scan.nextLine();
      
      switch(num) {
         case "1":
            salesLookUp(); //해당 메소드 이동
            break;
         case "2":
            Recommend.main(null);//해당 메소드 이동
         case "3":
            ManagerMode.main(null);//해당 메소드 이동
            break;
    case "*":
       //종료
       function.exit(); //시스템 종료
            
         
      }
      
      
      
   }
   /**
    * 매출 조회창
    * 원하는 메뉴로 입력받아 이동시키기
    */
   public static void salesLookUp() {
      ui.logo("매출 조회");
      
      System.out.println("\t\t1. 일별 매출 조회");
      System.out.println("\t\t2. 월별 매출 조회");
      System.out.println("\t\t3. 분기별 매출 조회");
      System.out.println("\t\t0. 뒤로가기");
      System.out.println("\t\t*. 종료");
      System.out.println();
      System.out.print("\t\t입력(번호) : ");
      String num = scan.nextLine();
      
      switch(num) {
      case "1":
         daySalesLookup();
         break;
      case "2":
         monthSalesLookup();
         break;
      case "3":
         quarterSalesLookup();
         break;
      case "0":
         main(null);
         break;
      case "*":
         function.exit();
    break;
      default : 
         System.out.println("\t\t목록에 있는 번호를 입력해 주세요.");
         
         salesLookUp();
         break;
         
      
   }
      
   }
   /**
    * 일별 매출 조회
    * 원하는 날짜 입력받아 해당 날짜 일별 매출 조회 가능
    */
   public static void daySalesLookup() { 
      
      ui.logo("일별매출조회");
      System.out.print("\t\t날짜 입력 (YYYYMMDD) : ");
      
      
      String date = scan.nextLine();
      
      String year = date.substring(0, 4); 
      String month = date.substring(4, 6);
      String day = date.substring(6, 8); //입력받은 날짜들을 년도, 월별, 일별 나누기.
      
      daySalesLookupPrint(year,month,day,date);//입력받아 해당 메소드로 이동
      
      
   }
   /**
    * 
    * 일별 매출 계산 메소드
    * @param year - 년도
    * @param month - 월
    * @param day - 일
    * @param date - 입력한 날짜
    */
   public static void daySalesLookupPrint(String year,String month, String day, String date) { //총이용자수, 총 매출액 출력, pause 후 메뉴
      try {
            BufferedReader datereader = new BufferedReader(new FileReader(DATA));
            BufferedReader numberreader = new BufferedReader(new FileReader(NUMBER));
            
            int count = 0; //총 이용자 수
            int money = 0;//총 매출액
            
            String line = ""; 
            String moneyline = "";
            
            
            while ((line = datereader.readLine()) != null) {
               String[] temp = line.split("■");
               
               if(temp[4].equals(date)) {
                  count++;
                  
                  while((moneyline = numberreader.readLine())!=null) {
                     String[] moneytemp = moneyline.split("■");
                     
                     if(temp[0].equals(moneytemp[0])) {
                        money += (Integer.parseInt(moneytemp[2])*Integer.parseInt(temp[2]));
                  }
                 
                 }
                  numberreader = new BufferedReader(new FileReader(NUMBER));
               }
            }
            
            System.out.printf("\t[%s년 %s월 %s일]\n",year,month,day); 
            System.out.printf("\t총 이용자 수 : %,d명 , ", count); //해당 날짜 하루 총 이용자 수
            System.out.printf("\t총 매출액 : %,d원\n", money); //해당 날짜 하루 총 매출
            

            datereader.close();

         } catch (Exception e) {
         }
      
      function.pause();   //잠시 멈춤.

      
      ui.bar();
      System.out.println("\t\t1. 연령별 매출액 조회");
      System.out.println("\t\t2. 제품별 매출액 조회");
      System.out.println("\t\t0. 뒤로 가기");
      System.out.print("\t\t입력(번호) : ");
      scan.nextLine();
      String num = scan.nextLine();
      
      switch(num) {
      case "1":
         AgeSearch.ageGraph(year, month, day, date);//년도, 월, 일, 입력한 날짜를 가지고 연령별 매출 메소드로 이동
         break;
      case "2":
         ItemSearch item = new ItemSearch(date);
          ItemSearch.daySales(); // 제품별 매출액 조회 메소드로 이동
         break;
      case "0":
         salesLookUp(); //이전 화면으로
         break;
      default : //"1","2","0" 제외하고 입력 받았을 경우
         System.out.println("목록에 있는 번호를 입력해 주세요.");
         System.out.println();
         daySalesLookupPrint(year, month, day, date);
         break;
      }
   }
   /**
    * 월별 매출 조회
    * 원하는 날짜 입력받아 해당 월별 매출 조회 가능
    */
   public static void monthSalesLookup(){ 
      
      ui.logo("월별매출조회");
      System.out.print("\t\t날짜 입력 (YYYYMM) : ");
      
      
      String date = scan.nextLine();
      
      String year = date.substring(0, 4);
      String month = date.substring(4, 6);
      
      monthSalesLookupPrint(year,month,date);
      
      
      
      
      
      
   }
   /**
    * 월별 매출 계산 메소드
    * @param year - 년도
    * @param month - 달
    * @param date - 일
    */
   public static void monthSalesLookupPrint(String year, String month, String date) { 
      try {
            BufferedReader datereader = new BufferedReader(new FileReader(DATA));
            BufferedReader numberreader = new BufferedReader(new FileReader(NUMBER));
            
            int count = 0; //총 이용자 수
            int money = 0; //총 매출액
            
            String line = ""; 
            String moneyline = "";
            
            
            while ((line = datereader.readLine()) != null) {
               String[] temp = line.split("■");
               
               if(temp[4].contains(date.substring(0, 6))) {
                  count++;
                  
                  while((moneyline = numberreader.readLine())!=null) {
                     String[] moneytemp = moneyline.split("■");
                     
                     if(temp[0].equals(moneytemp[0])) {
                        money += (Integer.parseInt(moneytemp[2])*Integer.parseInt(temp[2]));
                  }
                 
                 }
                  numberreader = new BufferedReader(new FileReader(NUMBER));
               }
            }
            
            System.out.printf("\t[%s년 %s월 ]\n",year,month);
            System.out.printf("\t총 이용자 수 : %,d명 , ", count);
            System.out.printf("\t총 매출액 : %,d원\n", money);
            

            datereader.close();

         } catch (Exception e) {
         }
      
      function.pause();
      
      ui.bar();
      System.out.println("\t\t1. 연령별 매출액 조회");
      System.out.println("\t\t2. 제품별 매출액 조회");
      System.out.println("\t\t0. 뒤로 가기");
      System.out.print("\t\t입력(번호) : ");
      scan.nextLine();
      String num = scan.nextLine();
      
      switch(num) {
      case "1":
         AgeSearch.ageGraph(year, month, date);
         break;
      case "2":
         ItemSearch item = new ItemSearch(date);
         ItemSearch.monthSales();
         break;
      case "0":
         salesLookUp();
         break;
      default : 
         System.out.println("\t\t목록에 있는 번호를 입력해 주세요.");
         System.out.println();
         monthSalesLookupPrint(year, month, date);
         break;
      }
      
   }
   /**
    * 분기별 매출 조회
    * 원하는 년도를 입력 받고
    * 원하는 분기를 입력 받음
    */
   public static void quarterSalesLookup() { 
      
      ui.logo("분기별매출조회");
      System.out.println("\t\t1. 1분기(1~3월)");
      System.out.println("\t\t2. 2분기(4~6월)");
      System.out.println("\t\t3. 3분기(7~9월)");
      System.out.println("\t\t4. 4분기(10~12월)");
      System.out.println("\t\t#. 처음으로 되돌아가기");
      System.out.println();
      System.out.print("\t\t년도를 입력 하세요 (YYYY) : ");
      String year = scan.nextLine();
      System.out.println();
      System.out.print("\t\t분기 입력 (번호) : ");
      String quarter = scan.nextLine();
      
      if(quarter.equals("#")) {
         main(null); //관리자 관리 초기화면으로 이동
      }else if(!quarter.equals("#") && !quarter.equals("1") && !quarter.equals("2") && !quarter.equals("3") && !quarter.equals("4")) {
         System.out.println("\t\t목록에 있는 번호를 입력해 주세요.");
         System.out.println();
         quarterSalesLookup();
      }
      quarterSalesLookupPrint(year,quarter);
      
      
   }
   /**
    * 분기별 총 이용자수
    * 분기별 총 매출액
    * @param year - 년도
    * @param quarter - 월
    */
   public static void quarterSalesLookupPrint(String year,String quarter) { 
      
      switch (quarter) {
         case "1": //분기
            try {
                  BufferedReader datereader = new BufferedReader(new FileReader(DATA));
                  BufferedReader numberreader = new BufferedReader(new FileReader(NUMBER));
                  
                  int count = 0; //총 이용자 수
                  int money = 0; //총 매출액
                  
                  String line = ""; 
                  String moneyline = "";
                  
                  
                  while ((line = datereader.readLine()) != null) {
                     String[] temp = line.split("■");
                     
                     if((temp[4].substring(0, 4)).equals(year) && (temp[4].substring(4, 6).equals("01") || temp[4].substring(4, 6).equals("02") || temp[4].substring(4, 6).equals("03") )) {
                        count++;
 
                        
                        while((moneyline = numberreader.readLine())!=null) {
                           String[] moneytemp = moneyline.split("■");
                           
                           if(temp[0].equals(moneytemp[0])) {
                              money += (Integer.parseInt(moneytemp[2])*Integer.parseInt(temp[2]));
                        }
                       
                       }
                        numberreader = new BufferedReader(new FileReader(NUMBER));
                     }
                  }
                  
                  
                  System.out.printf("\t\t총 이용자 수 : %,d명 , ", count);
                  System.out.printf("\t\t총 매출액 : %,d원\n", money);
                  

                  datereader.close();

               } catch (Exception e) {
               }
            
       function.pause();
            
            ui.bar();
            System.out.println("\t\t1. 연령별 매출액 조회");
            System.out.println("\t\t2. 제품별 매출액 조회");
            System.out.println("\t\t0. 뒤로 가기");
            System.out.print("\t\t입력(번호) : ");
       scan.nextLine();
            String num = scan.nextLine();
            
            switch(num) {
            case "1":
               AgeSearch.ageGraph(year, quarter); 
               break;
            case "2":
               ItemSearch item = new ItemSearch(year, quarter);
                ItemSearch.quartersSales();
               break;
            case "0":
               salesLookUp();
               break;
            default : 
               System.out.println("\t\t목록에 있는 번호를 입력해 주세요.");
               System.out.println();
               quarterSalesLookupPrint(year, quarter);
               break;
            }
            
            
            break;
            
         case "2":
            
            try {
                  BufferedReader datereader = new BufferedReader(new FileReader(DATA));
                  BufferedReader numberreader = new BufferedReader(new FileReader(NUMBER));
                  
                  int count = 0; //총 이용자 수
                  int money = 0; //총 매출액
                  
                  String line = ""; 
                  String moneyline = "";
                  
                  
                  while ((line = datereader.readLine()) != null) {
                     String[] temp = line.split("■");
                     
                     if((temp[4].substring(0, 4)).equals(year) && (temp[4].substring(4, 6).equals("04") 
                           || temp[4].substring(4, 6).equals("05") 
                           || temp[4].substring(4, 6).equals("06") )) {
                        count++;

                        
                        while((moneyline = numberreader.readLine())!=null) {
                           String[] moneytemp = moneyline.split("■");
                           
                           if(temp[0].equals(moneytemp[0])) {
                              money += (Integer.parseInt(moneytemp[2]) * Integer.parseInt(temp[2]));
                        }
                       
                       }
                        numberreader = new BufferedReader(new FileReader(NUMBER));
                     }
                  }
                  
                  
                  System.out.printf("\t\t총 이용자 수 : %,d명 , ", count);
                  System.out.printf("\t\t총 매출액 : %,d원\n", money);
                  

                  datereader.close();

               } catch (Exception e) {
               }
            
        function.pause();
            
            ui.bar();
            System.out.println("\t\t1. 연령별 매출액 조회");
            System.out.println("\t\t2. 제품별 매출액 조회");
            System.out.println("\t\t0. 뒤로 가기");
            System.out.print("\t\t입력(번호) : ");
            scan.nextLine();
            String num1 = scan.nextLine();
            
            switch(num1) {
            case "1":
               AgeSearch.ageGraph(year, quarter);
               break;
            case "2":
               ItemSearch item = new ItemSearch(year, quarter);
                ItemSearch.quartersSales();
               break;
            case "0":
               salesLookUp();
               break;
            default : 
                 System.out.println("\t\t목록에 있는 번호를 입력해 주세요.");
                 System.out.println();
                 quarterSalesLookupPrint(year, quarter);
                 break;
            }
            
            break;
         case "3":
            
            
            try {
                  BufferedReader datereader = new BufferedReader(new FileReader(DATA));
                  BufferedReader numberreader = new BufferedReader(new FileReader(NUMBER));
                  
                  int count = 0; //총 이용자 수
                  int money = 0; //총 매출액
                  
                  String line = ""; 
                  String moneyline = "";
                  
                  
                  while ((line = datereader.readLine()) != null) {
                     String[] temp = line.split("■");
                     
                     if((temp[4].substring(0, 4)).equals(year) && (temp[4].substring(4, 6).equals("07") || temp[4].substring(4, 6).equals("08") || temp[4].substring(4, 6).equals("09") )) {
                        count++;

                        
                        while((moneyline = numberreader.readLine())!=null) {
                           String[] moneytemp = moneyline.split("■");
                           
                           if(temp[0].equals(moneytemp[0])) {
                              money += (Integer.parseInt(moneytemp[2])*Integer.parseInt(temp[2]));
                        }
                       
                       }
                        numberreader = new BufferedReader(new FileReader(NUMBER));
                     }
                  }
                  
                  
                  System.out.printf("\t\t총 이용자 수 : %,d명 , ", count);
                  System.out.printf("\t\t총 매출액 : %,d원\n", money);
                  

                  datereader.close();

               } catch (Exception e) {
               }
            
       function.pause();
            
            ui.bar();
            System.out.println("\t\t1. 연령별 매출액 조회");
            System.out.println("\t\t2. 제품별 매출액 조회");
            System.out.println("\t\t0. 뒤로 가기");
            System.out.print("\t\t입력(번호) : ");
       scan.nextLine();
            String num11 = scan.nextLine();
            
            switch(num11) {
            case "1":
               AgeSearch.ageGraph(year, quarter);
               break;
            case "2":
               ItemSearch item = new ItemSearch(year, quarter);
                ItemSearch.quartersSales();
               break;
            case "0":
               salesLookUp();
               break;
            default : 
                 System.out.println("\t\t목록에 있는 번호를 입력해 주세요.");
                 System.out.println();
                 quarterSalesLookupPrint(year, quarter);
                 break;
            }
            
            
            
            break;
         case "4":
            
            try {
                  BufferedReader datereader = new BufferedReader(new FileReader(DATA));
                  BufferedReader numberreader = new BufferedReader(new FileReader(NUMBER));
                  
                  int count = 0; //총 이용자 수
                  int money = 0; //총 매출액
                  
                  String line = ""; 
                  String moneyline = "";
                  
                  
                  while ((line = datereader.readLine()) != null) {
                     String[] temp = line.split("■");
                     
                     if((temp[4].substring(0, 4)).equals(year) && (temp[4].substring(4, 6).equals("10") || temp[4].substring(4, 6).equals("11") || temp[4].substring(4, 6).equals("12") )) {
                        count++;

                        
                        while((moneyline = numberreader.readLine())!=null) {
                           String[] moneytemp = moneyline.split("■");
                           
                           if(temp[0].equals(moneytemp[0])) {
                              money += (Integer.parseInt(moneytemp[2])*Integer.parseInt(temp[2]));
                        }
                       
                       }
                        numberreader = new BufferedReader(new FileReader(NUMBER));
                     }
                  }
                  
                  
                  System.out.printf("\t\t총 이용자 수 : %,d명 , ", count);
                  System.out.printf("\t\t총 매출액 : %,d원\n", money);
                  

                  datereader.close();

               } catch (Exception e) {
               }
            
       function.pause();
            
            ui.bar();
            System.out.println("\t\t1. 연령별 매출액 조회");
            System.out.println("\t\t2. 제품별 매출액 조회");
            System.out.println("\t\t0. 뒤로 가기");
            System.out.print("\t\t입력(번호) : ");
       scan.nextLine();
            String num2 = scan.nextLine();
            
            switch(num2) {
            case "1":
               AgeSearch.ageGraph(year, quarter);
               break;
            case "2":
               ItemSearch item = new ItemSearch(year, quarter);
                ItemSearch.quartersSales();
               break;
            case "0":
               salesLookUp();
               break;
            default : 
                 System.out.println("\t\t목록에 있는 번호를 입력해 주세요.");
                 System.out.println();
                 quarterSalesLookupPrint(year, quarter);
                 break;
            }
            break;
      }
   } 
}