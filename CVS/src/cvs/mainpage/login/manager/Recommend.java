package cvs.mainpage.login.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

import cvs.function.Order;
import cvs.function.UI;
import cvs.mainpage.login.customer.Buy;

/**
 * 많이 팔린 수량 순으로 제품을 나열하여 관리자가 매장 진열을 설정
 * @author 장시영
 *
 */
public class Recommend {
   
   private static final ArrayList<Order> olist;
   private static final Map<String, String> priceMap;
   private static final Map<String, String> code; //제품번호, 제품명
   private static final Map<String, String> code2; //제품번호, 제품명
   private static final Map<String, Integer> SellNum; //판매수량
   private static final Map<String, Integer> PriceNum; //판매액
   private static final Calendar now;
   private static final int nowYear;
   private static final int nowMonth;
   private static final int lastYear;
   private static final int lastMonth;
   private static final Scanner scan;
   private Buy m;
   private static UI ui;
   
   /**
    * 입력한 이벤트 상품을 저장하는 ArrayList<String> 
    */
   public static ArrayList<String> event;
   
   static {
	  ui = new UI();
      olist = new ArrayList<Order>();
      priceMap = new HashMap<String, String>();
      code = new HashMap<String, String>();
      code2 = new HashMap<String, String>();
      SellNum = new HashMap<String, Integer>();
      PriceNum = new HashMap<String, Integer>();
      now = Calendar.getInstance();
      nowYear = now.get(Calendar.YEAR);
      nowMonth = now.get(Calendar.MONTH) + 1;
      lastYear = nowYear - 1 ;
      lastMonth = nowMonth;
      scan = new Scanner(System.in);
      event = new ArrayList<String>();
   }
   
   
   /**
    * 출력을 위한 main
    * @param args
    */
   public static void main(String[] args) {
      
      ui.bar();
      System.out.println("\t\t\t  " + nowYear + "년 " + nowMonth + "월 진열 추천");
      ui.bar();
      
      System.out.println("" );

      display();
      pause();
      sel(scan);
      
   }

   
   /**
    * 판매수량이 많은 순으로 작년 매출을 조회
    * @param lastYear - 작년 년도
    * @param lastMonth - 작년 월
    */
   private static void run(int lastYear, int lastMonth) {
      
      String S_lastYear = lastYear+"";
      String S_lastMonth = "";
      
   
      if(lastMonth < 10) {
         S_lastMonth = "0" + lastMonth;
      } else {
         S_lastMonth = lastMonth + "";
      }
      
      String date = S_lastYear + S_lastMonth;
      
      ui.bar();
      System.out.println();
      System.out.println("\t\t\t[ " + lastYear + "년 " + lastMonth + "월 ] 매출 상황");
      System.out.println();
      ui.bar();
      
      System.out.println("\t\t\t   [ 소분류 ]    [ 판매수량 ]             [ 판매액 ]");
      
      try {
         
         BufferedReader reader = new BufferedReader(new FileReader("dat\\order.dat"));
         
         String line = "";
         
         while((line = reader.readLine()) != null) {
            
            String[] temp = line.split("■");
            
            olist.add(new Order(temp[0], temp[1], temp[2], temp[3], temp[4]));
         }
         
         reader.close();
         
         reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
         
         line = "";
         
         while((line = reader.readLine()) != null) {
            String[] temp = line.split("■");
            
            priceMap.put(temp[0], temp[2]); //제품번호, 가격
         }
         
         reader.close();
         
         reader = new BufferedReader(new FileReader("dat\\소분류.dat"));
         
         line = "";
         
         while((line = reader.readLine()) != null) {
            String[] temp = line.split("■");
            
            code.put(temp[0], temp[0]); //제품번호, 소분류 명
            code2.put(temp[0], temp[1]); //제품번호, 소분류 명
            
            PriceNum.put(temp[0], 0); //판매액
            SellNum.put(temp[0], 0); //판매수량
         }
         
         reader.close();

         for(Order order : olist) {
            
            if(order.getBuyDate().startsWith(date)) {
               
               if(order.getItemNum().substring(0, 9).equals(code.get(order.getItemNum().substring(0, 9)))) { //order리스트의 제품코드가 code의 key와같으면
                  
                  PriceNum.put(order.getItemNum().substring(0, 9), PriceNum.get(order.getItemNum().substring(0, 9)) + (Integer.parseInt(priceMap.get(order.getItemNum())) * Integer.parseInt(order.getBuyNum())));
                  SellNum.put(order.getItemNum().substring(0, 9), SellNum.get(order.getItemNum().substring(0, 9)) + Integer.parseInt(order.getBuyNum()));
               }
            }
         }
         
         Map<String, Integer> sortedSellNum = sort(SellNum);
         
         for (Entry<String, Integer> entry : sortedSellNum.entrySet()){
              
            System.out.printf("\t\t\t  %6s \t%10d개\t\t%,10d원\n", code2.get(entry.getKey()), entry.getValue(), PriceNum.get(entry.getKey()));
           }
      } catch (Exception e) {
         System.out.println("recommend.main() : " + e.toString());
      }
   }
   
   
   /**
    * 매장 진열을 나타낸 UI
    */
   private static void display() {
      
      System.out.println("\t  _________________________________________    _________________________________________");
         System.out.println("\t /_______________________________________/┃  /_______________________________________/┃");
         System.out.println("\tㅣ                  ①                  ┃┃ ㅣ                  ①                  ┃┃");
         System.out.println("\t└───────────────────┛┛ └───────────────────┛┛");
         System.out.println("\t                                                 ______   ______   ______   ______");
         System.out.println("\t              ┌─────┐                    /____/┃ /____/┃ /____/┃ /____/┃");
         System.out.println("\t              │  계산대  │                   ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
         System.out.println("\t              └─────┘                   ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
         System.out.println("\t                                               ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
         System.out.println("\t                                               ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
         System.out.println("\t      ___________          ___________         ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
         System.out.println("\t     /_________/ㅣ        /_________/ㅣ        ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
         System.out.println("\t    ㅣ   ③   ㅣㅣ       ㅣ   ④   ㅣㅣ        ┃② ┃┃┃② ┃┃┃② ┃┃┃② ┃┃");
         System.out.println("\t    └────┘┘       └────┘┘        ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
         System.out.println("\t  _______________________________________      ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
         System.out.println("\t /_____________________________________/ㅣ     ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
         System.out.println("\tㅣ                 ⑤                 ㅣㅣ     ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
         System.out.println("\t└──────────────────┘┘     ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
         System.out.println("\t    ___________          ___________           ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
         System.out.println("\t   /_________/ㅣ        /_________/ㅣ          ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
         System.out.println("\t  ㅣ   ⑦   ㅣㅣ       ㅣ   ⑥   ㅣㅣ          └─ ┛┛└─ ┛┛└─ ┛┛└─ ┛┛");
         System.out.println("\t  └────┘┘       └────┘┘");
         System.out.println("           ___________          ___________        ___________    __________ ");
         System.out.println("          /_________/ㅣ        /_________/ㅣ      /_________/ㅣ  /_________/ㅣ");
         System.out.println("         ㅣ   ⑦   ㅣㅣ       ㅣ   ⑥   ㅣㅣ     ㅣ   ⑥   ㅣㅣ ㅣ   ⑥   ㅣㅣ");
         System.out.println("         └────┘┘       └────┘┘     └────┘┘ └────┘┘");
         System.out.println();
         System.out.println();
         System.out.println("\t                                  ┌─────┐");
         System.out.println("\t                                  │   입구   │");
         System.out.println("\t                                  └─────┘");
         System.out.println();
         System.out.println();
         System.out.println();
         
   }
   
   
   /**
    * 정지 시간
    */
   private static void pause() {
         
         Scanner scan = new Scanner(System.in);
         System.out.println();
         System.out.println("\t\t계속 진행하시려면 엔터키를 눌러주세요.");
         scan.nextLine();
      }
   
   
   /**
    * 판매수량 순으로 나열
    * @param map - 나열하고자 하는 map
    * @return sortedMap - 나열된 map
    */
   private static Map<String, Integer> sort(Map<String, Integer> map){
      
      List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2){
                 return o2.getValue().compareTo(o1.getValue());
            }
        });
        
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
   }
   
   
   /**
    * 다음 메뉴 select
    * @param scan - 하고자 하는 메뉴 입력
    */
   private static void sel(Scanner scan) {
      
      boolean loop = true;
      
      while(true) {
         ui.bar();
         System.out.println();
         System.out.println("\t\t1. 이벤트 진열대 상세보기       0. 처음으로     *. 종료");
         System.out.println();
         
         System.out.print("\t\t\t입력 : ");
         String sel = scan.nextLine();
         System.out.println();
         
         
         switch (sel) {
         case "1" :
            
            run(lastYear, lastMonth);
            
            ui.bar();
            System.out.println("\t\t1. 이벤트 상품 진열하기       0. 뒤로가기");
            System.out.println();
            
            System.out.print("\t\t\t입력 : ");
            sel = scan.nextLine();
            System.out.println();
            
            switch (sel) {
            case "1" :          
               System.out.print("\t\t\t상품 1 입력 : ");
               String event1 = scan.nextLine();
               event.add(event1);
               
               System.out.print("\t\t\t상품 2 입력 : ");
               String event2 = scan.nextLine();
               event.add(event2);
               
               System.out.print("\t\t\t상품 3 입력 : ");
               String event3 = scan.nextLine();
               event.add(event3);
               
               System.out.println();
               System.out.println("\t\t진열이 완료되었습니다.");
               
               
               try {
               BufferedWriter writer = new BufferedWriter(new FileWriter("dat\\event.dat"));
               
               writer.flush();
               
               BufferedReader reader = new BufferedReader(new FileReader("dat\\event.dat"));
               ArrayList<String> array    = new ArrayList<>();
               Random rnd = new Random();
               
               for(int i=0; i<event.size();i++) {
                  BufferedReader eventReader = new BufferedReader(new FileReader("dat\\totalData.dat"));
                  
                  String totalTxt = null;
                  
                  while((totalTxt = eventReader.readLine())!=null) {
                     String temp[] = totalTxt.split("■");
                     
                     if(temp[4].trim().equals(event.get(i).trim())) {
                        array.add(temp[0]+"■"+temp[2]+"■"+temp[3]+"■"+temp[4]+"■"+temp[1]);
                     }
                  }
                  
                  for(int j=0;j<3;j++) {
                     writer.write(array.get(rnd.nextInt(array.size())));
                     writer.newLine();
                  }
               
                  eventReader = new BufferedReader(new FileReader("dat\\totalData.dat"));
                  array.clear();
               }
               reader.close();
               writer.close();
               } catch (Exception e) {
                  // TODO: handle exception
               }
               
               pause();
               
               break;
               
            case "0" : 
               //뒤로가기
               break;
            }
            
            break;
         case  "0" : 
            Sales.main(null);
            break;
         
         case "*" : 
            System.exit(0);
         
         default : 
            loop = false;
            System.out.println("\t\t다시 입력해주세요.");
            break;
         }
      }
   }
}