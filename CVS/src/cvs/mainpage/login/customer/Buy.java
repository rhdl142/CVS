package cvs.mainpage.login.customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import cvs.function.UI;
import cvs.mainpage.Login;

/**
 * 
 * @author 민문경
 *
 */
public class Buy {
   /**
    * 구매자가 물품을 구매할 때 실행 시키는 클래스입니다.
    */
   
   private static final Scanner s;
   private static UI ui;
   
   private static final ArrayList<String> name;
   private static final ArrayList<Integer> count;
   
   static {
      
      s = new Scanner(System.in);

      ui = new UI();
      name = new ArrayList<String>();
      count = new ArrayList<Integer>();
   }

   public static void main(String[] args) {

      exhibition();

   }
   
   

   private static void exhibition() {
      // TODO Auto-generated method stub

      String inputNum = ""; // 입력번호 받을 변수(중분류 진입 시)

      ui.logo("진열대");
      System.out.println("  _________________________________________    _________________________________________");
      System.out.println(" /_______________________________________/┃  /_______________________________________/┃");
      System.out.println("ㅣ                  ①                  ┃┃ ㅣ                  ①                  ┃┃");
      System.out.println("└───────────────────┛┛ └───────────────────┛┛");
      System.out.println("                                                 ______   ______   ______   ______");
      System.out.println("              ┌─────┐                    /____/┃ /____/┃ /____/┃ /____/┃");
      System.out.println("              │  계산대  │                   ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
      System.out.println("              └─────┘                   ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
      System.out.println("                                               ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
      System.out.println("                                               ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
      System.out.println("      ___________          ___________         ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
      System.out.println("     /_________/ㅣ        /_________/ㅣ        ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
      System.out.println("    ㅣ   ③   ㅣㅣ       ㅣ   ④   ㅣㅣ        ┃② ┃┃┃② ┃┃┃② ┃┃┃② ┃┃");
      System.out.println("    └────┘┘       └────┘┘        ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
      System.out.println("  _______________________________________      ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
      System.out.println(" /_____________________________________/ㅣ     ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
      System.out.println("ㅣ                 ⑤                 ㅣㅣ     ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
      System.out.println("└──────────────────┘┘     ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
      System.out.println("    ___________          ___________           ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
      System.out.println("   /_________/ㅣ        /_________/ㅣ          ┃   ┃┃┃   ┃┃┃   ┃┃┃   ┃┃");
      System.out.println("  ㅣ   ⑦   ㅣㅣ       ㅣ   ⑥   ㅣㅣ          └─ ┛┛└─ ┛┛└─ ┛┛└─ ┛┛");
      System.out.println("  └────┘┘       └────┘┘");
      System.out.println("               ___________          ___________        ___________    __________ ");
      System.out.println("              /_________/ㅣ        /_________/ㅣ      /_________/ㅣ  /_________/ㅣ");
      System.out.println("             ㅣ   ⑦   ㅣㅣ       ㅣ   ⑥   ㅣㅣ     ㅣ   ⑥   ㅣㅣ ㅣ   ⑥   ㅣㅣ");
      System.out.println("             └────┘┘       └────┘┘     └────┘┘ └────┘┘");
      System.out.println();
      System.out.println();
      System.out.println("                                  ┌─────┐");
      System.out.println("                                  │   입구   │");
      System.out.println("                                  └─────┘");
      System.out.println();

      System.out.println("\t\t\t1.기호식품   2.식음료   3.생활용품");
      System.out.println("\t\t\t4.과자       5.식품      6.간편식사   7.이벤트");
      System.out.println("\t\t\t8.상품검색   9.장바구니   0.뒤로 가기   *.종료");
      System.out.println();
      ui.bar();

      System.out.print("\t\t입력(번호) : ");
      inputNum = s.next();

      switch (inputNum) {
      case "1":
         middleProductLook(inputNum); // 중분류 메뉴 선택(기호식품)
         break;
      case "2":
         middleProductLook(inputNum); // 중분류 메뉴 선택(식음료)
         break;
      case "3":
         middleProductLook(inputNum); // 중분류 메뉴 선택(생활용품)
         break;
      case "4":
         middleProductLook(inputNum); // 중분류 메뉴 선택(과자)
         break;
      case "5":
         middleProductLook(inputNum); // 중분류 메뉴 선택(식품)
         break;
      case "6":
         middleProductLook(inputNum); // 중분류 메뉴 선택(간편식사)
         break;
      case "7":
         middleProductLook(inputNum); // 중분류 메뉴 선택(이벤트)
         break;
      case "8":
         productSearch(); // 상품검색
         break;
      case "9":
         outputBasket(); // 장바구니 보기
         break;
      case "0":
         Login.loginSuccessUi();
          //로그인 화면으로 이동
         break;
      case "*":
         end(); // 화면 종료
         break;

      }

   }

   private static void outputBasket() {
      /**
       * 장바구니 보기
       */
      
      ui.logo("장바구니 목록");
      
      
      
      try {
         BufferedReader reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
         
         String txt = null;
         
         System.out.println("\t[일련번호]\t[가격]\t[개수]\t[카테고리]\t[유통기한]\t[제품명]");
         
         for(int i=0; i<name.size(); i++) { 
         
            while((txt = reader.readLine()) != null) {
               
               String[] temp = txt.split("■");
               
               String t = temp[0];
               String l = name.get(i);

               
               if(t.equals(l)) {
                  
                  System.out.println("\t"+temp[0]+"\t"+temp[2]+"\t"+count.get(i)+"\t"+temp[4]+"\t\t"+temp[6]+"\t"+temp[1]);
               }
                  
               
            }
            reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
         }
         
         reader.close();
         
      } catch (Exception e) {
         
         ui.bar();
         System.out.println("\t\t\t장바구니 목록에 제품이 없습니다.\n");
         ui.bar();
      }
      
      ui.bar();
      System.out.println("\n\n\t\t   1. 결제하기\t0.메인화면으로 돌아가기\n");
      System.out.print("\t\t입력(번호) : ");
      String answer = s.next();
      
      switch(answer) {
         case "1" : pay(); break;
         case "0" : exhibition(); break;
         default : 
            ui.bar();
            System.out.println("\t\t잘못된 입력입니다. 메인화면으로 돌아갑니다.\n");
            ui.bar();
            exhibition();
            break;
      }
      
      
      
      
   }



   private static void pay() {
      /**
       * 결제하기
       */
      
      int totalResult = 0;
      int totalCount = 0;
      
      ui.logo("장바구니 목록");
      
      
      
      
      try {
         BufferedReader reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
         
         String txt = null;
         
         System.out.println("\t[일련번호]\t[가격]\t[개수]\t[카테고리]\t[유통기한]\t[제품명]");
         
         for(int i=0; i<name.size(); i++) { 
         
            while((txt = reader.readLine()) != null) {
               
               String[] temp = txt.split("■");
               
               String t = temp[0];
               String l = name.get(i);
               
   
                        //총 금액
               
               if(t.equals(l)) {
                  
                  System.out.println("\t"+temp[0]+"\t"+temp[2]+"\t"+count.get(i)+"\t"+temp[4]+"\t\t"+temp[6]+"\t"+temp[1]);
                  totalCount = totalCount + count.get(i);
                  totalResult = totalResult + (Integer.parseInt(temp[2])*count.get(i));
               }
                  
               
            }
            reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
         }
         
         reader.close();
         
      } catch (Exception e) {
         
         e.printStackTrace();
      }
      
      
      
      ui.bar();
      
      System.out.printf("\t\t\t총 개수 : %d", totalCount);
      System.out.printf("\t\t총 결제 금액 : %d\n\n\n", totalResult);
      System.out.println("\t\t\t결제를 진행하시겠습니까?");
      System.out.print("\t\t\t1. 결제하기     0. 뒤로가기\n");
      ui.bar();
      
      System.out.print("\t\t입력(번호) : ");
      String answer = s.next();
      switch(answer){
         case "1" : 
            
            try {
               BufferedReader reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
               ArrayList<String> list = new ArrayList<>();
               
               String txt = null;
               
               for(int i=0; i<name.size(); i++) {

                  while((txt = reader.readLine()) != null) {
                     String temp[] = txt.split("■");
                     
                     if(name.get(i).equals(temp[0])) {
                        temp[3] = (Integer.parseInt(temp[3]) - count.get(i)) + "";
                     }
                     list.add(temp[0]+"■"+temp[1]+"■"+temp[2]+"■"+temp[3]+"■"+temp[4]+"■"+temp[5]+"■"+temp[6]);
                  }
               }
               
               reader.close();
               
               
               BufferedWriter writer = new BufferedWriter(new FileWriter("dat\\totalData.dat"));
               
               for(int i=0; i<list.size();i++) {
                  writer.write(list.get(i));
                  writer.newLine();
               }
               
               writer.close();
               
               name.clear();
               count.clear();
               
               ui.bar();
               System.out.println("\t\t\t결제가 완료 되었습니다. 이용해주셔서 감사합니다.\n\n");
               ui.bar();
               exhibition();

               
               
            } catch (Exception e) {
               System.out.println("");
            }
               
               
            
               
               
               break;
               
         case "0" : outputBasket(); break;
      }
      
      
      
   }



   private static void productSearch() {
      /**
       * 상품검색
       */
      
      ui.logo("상품검색");
      System.out.println("\t\t입력란에 원하는 상품명을 입력해주세요.\n");
      System.out.print("\t\t제품명 입력 : ");
      String productName = s.next();

         
      
      //해당제품 이름 찾기
      
      try {
         
         BufferedReader reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
         
         String txt = null;
         boolean check = true;

         
         
         ui.logo("상품 검색 목록");
         System.out.printf("\t\t%s를(을) 검색하셨습니다.\n\n", productName);
         
         System.out.println("\t[일련번호]\t\t[가격]\t[재고]\t[카테고리]\t[제품명]");
         
         while ((txt = reader.readLine()) != null) {
            
            String[] temp = txt.split("■");
            
            String n = temp[1];

            if(n.trim().contains(productName.trim())) {
               check = false;
               System.out.println("\t"+temp[0]+"\t\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[1]);
            }
         }
         
         if(check) {
            ui.bar();
            System.out.println("\t\t검색하신 목록에 없습니다.");
            ui.bar();
            productSearch();
            
         }
         
         reader.close();
         
      } catch (Exception e) {
         
         e.printStackTrace();
      }
      
      
      System.out.println("\n\t\t\t1.장바구니 담기    0.뒤로 가기   #.처음으로 가기\n");
      
      System.out.print("\t\t입력(번호) : ");
      String answer = s.next();
      
      switch(answer) {
         case "1" : inputBasket(); break;
         case "0" : productSearch(); break;
         case "#" : exhibition(); break;
         default : 
            ui.bar();
            System.out.println("\t\t잘못된 입력입니다. 메인화면으로 돌아갑니다.\n");
            ui.bar();
            exhibition();
            break;
         
      }
      
      
      
      
   }



   private static void end() {
      /**
       * 화면 종료
       */
      ui.bar();
      System.out.println("\t\t구매 화면을 종료합니다. 로그인 화면으로 돌아갑니다.");
      ui.bar();
      Login.loginSuccessUi();
      

   }

//   private static void main() {
//      /**
//       * 뒤로가기(처음 메인화면으로 이동)
//       */
//      System.out.println("처음으로 돌아갑니다.");
//      System.out.println("\r\n");
//      exhibition();
//
//   }

   private static void inputBasket() {
      /**
       * 장바구니 담기
       * 
       */
      
      System.out.print("\t\t제품 번호 입력 : ");
      String productNum = s.next();
      
      System.out.println();
      System.out.print("\t\t수량 입력 : ");
      int productCount = s.nextInt();
      
      try {
         BufferedReader reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
         
         boolean check = false;
         String txt = null;
         
         while((txt = reader.readLine())!= null) {
            String temp[] = txt.split("■");
            
            if(temp[0].replace("-", "").equals(productNum)) {
               if(Integer.parseInt(temp[3]) >= productCount) {
                  check = true;
                  
                  name.add(temp[0]);
                  count.add(productCount);
                  break;
               } else {
                  ui.bar();
                  System.out.println("\t\t수량이 부족합니다. 다시 입력하시기 바랍니다.\n\n");
                  System.out.print("\t\t수량 입력 : ");
                  productCount = s.nextInt();
               }
            } 
         }
         
         if(!check) {
            ui.bar();
            System.out.println("\t\t없는 일련번호 입니다. 다시 입력하시기 바랍니다.\n");
            inputBasket();
            //종료   
         }
         
         
      
         ui.bar();
         System.out.println("\t\t\t\t장바구니에 추가되었습니다.\n\n");
         ui.bar();
      
         System.out.println("\t\t\t1. 쇼핑 계속하기                  2. 장바구니 보기");
      
         System.out.print("\t\t입력(번호) : ");
         String answer = s.next();
      
         switch(answer) {
         case "1" : exhibition(); break;
         case "2" : outputBasket(); break;
         default : 
            ui.bar();
            System.out.println("\t\t잘못된 입력입니다. 메인화면으로 돌아갑니다.\n");
            System.out.println("\r\n");
            exhibition();
            break;
         }
      
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      

   }


   private static void middleProductLook(String inputNum) {
      /**
       * 상품보기(중분류)
       */

      String inputMiddleNum = ""; // 입력번호 받을 변수(소분류 진입 시)

      ui.logo("상품 보기(중분류)");

      switch (inputNum) {
      case "1":
         System.out.println("\t\t\t   1.담배\t\t0.뒤로 가기"); // 중분류 메뉴 선택(기호식품)
         System.out.print("\t\t입력(번호) : ");
         inputMiddleNum = s.next();
         if(inputMiddleNum.equals("0")) {
            exhibition();
         }
         smallProductLook(inputNum, inputMiddleNum);
         break;
      case "2":
         System.out.println("\t\t\t   1.음료\t2.주류\t3.유제품\t0.뒤로 가기"); // 중분류 메뉴 선택(식음료)
         System.out.print("\t\t입력(번호) : ");
         inputMiddleNum = s.next();
         if(inputMiddleNum.equals("0")) {
            exhibition();
         }
         smallProductLook(inputNum, inputMiddleNum);
         break;
      case "3":
         System.out.println("\t\t\t   1.세안도구\t2.생필품\t3.의류\t0.뒤로 가기"); // 중분류 메뉴 선택(생활용품)
         System.out.print("\t\t입력(번호) : ");
         inputMiddleNum = s.next();
         if(inputMiddleNum.equals("0")) {
            exhibition();
         }
         smallProductLook(inputNum, inputMiddleNum);
         break;
      case "4":
         System.out.println("\t\t\t   1.스낵/비스켓\t2.껌/초콜릿/캔디\t0.뒤로 가기"); // 중분류 메뉴 선택(과자)
         System.out.print("\t\t입력(번호) : ");
         inputMiddleNum = s.next();
         if(inputMiddleNum.equals("0")) {
            exhibition();
         }
         smallProductLook(inputNum, inputMiddleNum);
         break;
      case "5":
         System.out.println("\t\t\t   1.라면\t2.안주류\t0.뒤로 가기"); // 중분류 메뉴 선택(식품)
         System.out.print("\t\t입력(번호) : ");
         inputMiddleNum = s.next();
         if(inputMiddleNum.equals("0")) {
            exhibition();
         }
         smallProductLook(inputNum, inputMiddleNum);
         break;
      case "6":
         System.out.println("\t\t\t   1.도시락\t2.샌드위치/햄버거\t3.주먹밥/김밥\t0.뒤로 가기"); // 중분류 메뉴 선택(간편식사)
         System.out.print("\t\t입력(번호) : ");
         inputMiddleNum = s.next();
         if(inputMiddleNum.equals("0")) {
            exhibition();
         }
         smallProductLook(inputNum, inputMiddleNum);
         break;
      case "7":
         System.out.println("\t\t\t   1.베스트 상품\t0.뒤로 가기"); // 중분류 메뉴 선택(이벤트)
         System.out.print("\t\t입력(번호) : ");
         inputMiddleNum = s.next();
         if(inputMiddleNum.equals("0")) {
            exhibition();
         }else if(inputMiddleNum.equals("1")) {
            eventProduct();
            inputBasket();
         }

         break;
      default :
         ui.bar();
         System.out.println("\t잘못된 입력입니다. 메인화면으로 돌아갑니다.\n");
         ui.bar();
         exhibition();
         break;

      }

//      smallProductLook(inputNum, inputMiddleNum);

   }
   
   private static void eventProduct() {
         // TODO Auto-generated method stub
         try {
            BufferedReader reader = new BufferedReader(new FileReader("dat\\event.dat"));
            String txt = null;
            
            
            System.out.println("\t[일련번호]\t\t[가격]\t[재고]\t[카테고리]\t[제품명]");
            while((txt = reader.readLine())!= null) {
               //System.out.println(txt);
               String temp[] = txt.split("■");
               System.out.println("\t"+temp[0]+"\t\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t\t"+temp[4]);
            }
            reader.close();
            
         } catch (Exception e) {
            // TODO: handle exception
         }
   }

   private static void smallProductLook(String inputNum, String inputMiddleNum) {
      /**
       * 상품보기(소분류)
       */

      String inputSmallNum = ""; // 입력번호 받을 변수(소분류 진입 시)

      ui.logo("상품 보기(소분류)");

      switch (inputNum) {
      // 소분류 메뉴 선택(식음료)
      case "1" :
         switch(inputMiddleNum) {
         case "1" :
            inputSmallNum = "1";
            productSearch(inputNum, inputMiddleNum, inputSmallNum);
            break;
         }
         break;
      
      case "2":
         switch (inputMiddleNum) {
         case "1":
            System.out.println("\t\t\t   1.이온\t2.탄산\t3.에너지드링크\t0.뒤로가기"); // 소분류 메뉴 선택(식음료)
            System.out.print("\t\t입력(번호) : ");
            inputSmallNum = s.next();
            
            //조건문
            if(inputSmallNum.equals("0")) {
               middleProductLook("2");
            }
            
            break;
         case "2":
            System.out.println("\t\t\t   1.소주\t2.맥주\t3.막걸리\t0.뒤로가기"); // 소분류 메뉴 선택(식음료)
            System.out.print("\t\t입력(번호) : ");
            inputSmallNum = s.next();
            
            if(inputSmallNum.equals("0")) {
               middleProductLook("2");
            }
            
            break;
         case "3":
            System.out.println("\t\t\t   1.우유\t2.요거트\t0.뒤로가기"); // 소분류 메뉴 선택(식음료)
            System.out.print("\t\t입력(번호) : ");
            inputSmallNum = s.next();
            
            if(inputSmallNum.equals("0")) {
               middleProductLook("2");
            }
            
            break;
         default :
            ui.bar();
            System.out.println("\t\t잘못된 입력입니다. 메인화면으로 돌아갑니다.");
            ui.bar();
            exhibition();
            break;
         }
         
         productSearch(inputNum, inputMiddleNum, inputSmallNum);
         break;

      case "3":
         switch (inputMiddleNum) {
         case "1":
            System.out.println("\t\t\t   1.칫솔/치약\t2.면도기\t3.여성용품\t4. 바디용품\t0.뒤로가기"); // 소분류 메뉴 선택(식음료)
            System.out.print("\t\t입력(번호) : ");
            inputSmallNum = s.next();
            
            //조건문
            if(inputSmallNum.equals("0")) {
               middleProductLook("3");
            }
            
            break;
         case "2":
            System.out.println("\t\t\t   1.우산\t2.티슈\t0.뒤로가기"); // 소분류 메뉴 선택(식음료)
            System.out.print("\t\t입력(번호) : ");
            inputSmallNum = s.next();
            
            if(inputSmallNum.equals("0")) {
               middleProductLook("3");
            }
            
            break;
         case "3":
            System.out.println("\t\t\t   1.스타킹/양말\t2.속옷\t0.뒤로가기"); // 소분류 메뉴 선택(식음료)
            System.out.print("\t\t입력(번호) : ");
            inputSmallNum = s.next();
            
            if(inputSmallNum.equals("0")) {
               middleProductLook("3");
            }
            
            break;
         default :
            ui.bar();
            System.out.println("\t\t잘못된 입력입니다. 메인화면으로 돌아갑니다.");
            ui.bar();
            exhibition();
            break;
         }
         
         productSearch(inputNum, inputMiddleNum, inputSmallNum);
         break;

      case "4":
         switch (inputMiddleNum) {
         case "1":
            System.out.println("\t\t\t   1.스낵\t2.비스켓\t0.뒤로가기"); // 소분류 메뉴 선택(식음료)
            System.out.print("\t\t입력(번호) : ");
            inputSmallNum = s.next();
            
            //조건문
            if(inputSmallNum.equals("0")) {
               middleProductLook("4");
            }
            
            break;
         case "2":
            System.out.println("\t\t\t   1.껌\t2.초콜릿\t3.캔디/젤리\t0.뒤로가기"); // 소분류 메뉴 선택(식음료)
            System.out.print("\t\t입력(번호) : ");
            inputSmallNum = s.next();
            
            if(inputSmallNum.equals("0")) {
               middleProductLook("4");
            }
            
            break;
         default :
           ui.bar();
            System.out.println("\t\t잘못된 입력입니다. 메인화면으로 돌아갑니다.");
            ui.bar();
            exhibition();
            break;
         }
         productSearch(inputNum, inputMiddleNum, inputSmallNum);
         break;

      case "5":
         switch (inputMiddleNum) {
         case "1":
            System.out.println("\t\t\t   1.컵라면\t2.봉지라면\t0.뒤로가기"); // 소분류 메뉴 선택(식음료)
            System.out.print("\t\t입력(번호) : ");
            inputSmallNum = s.next();
            
            //조건문
            if(inputSmallNum.equals("0")) {
               middleProductLook("5");
            }
            
            break;
         case "2":
            System.out.println("\t\t\t   1.마른안주\t2.견과류\t0.뒤로가기"); // 소분류 메뉴 선택(식음료)
            System.out.print("\t\t입력(번호) : ");
            inputSmallNum = s.next();
            
            if(inputSmallNum.equals("0")) {
               middleProductLook("5");
            }
            
            break;
         default :
            ui.bar();
            System.out.println("\t\t잘못된 입력입니다. 메인화면으로 돌아갑니다.");
            ui.bar();
            exhibition();
            break;
         }
         productSearch(inputNum, inputMiddleNum, inputSmallNum);
         break;

      case "6":
         switch (inputMiddleNum) {
         case "1":
            System.out.println("\t\t\t   1.도시락\t0.뒤로가기"); // 소분류 메뉴 선택(식음료)
            System.out.print("\t\t입력(번호) : ");
            inputSmallNum = s.next();
            
            //조건문
            if(inputSmallNum.equals("0")) {
               middleProductLook("6");
            }
            
            break;
         case "2":
            System.out.println("\t\t\t   1.샌드위치\t2.햄버거\t0.뒤로가기"); // 소분류 메뉴 선택(식음료)
            System.out.print("\t\t입력(번호) : ");
            inputSmallNum = s.next();
            
            if(inputSmallNum.equals("0")) {
               middleProductLook("6");
            }
            
            break;
         case "3":
            System.out.println("\t\t\t   1.주먹밥/김밥\t0.뒤로가기"); // 소분류 메뉴 선택(식음료)
            System.out.print("\t\t입력(번호) : ");
            inputSmallNum = s.next();
            
            if(inputSmallNum.equals("0")) {
               middleProductLook("6");
            }
            
            break;
         default :
            ui.bar();
            System.out.println("\t\t잘못된 입력입니다. 메인화면으로 돌아갑니다.");
            ui.bar();
            exhibition();
            break;
         }
         productSearch(inputNum, inputMiddleNum, inputSmallNum);
         break;

      case "7":
         switch (inputMiddleNum) {
         case "1":
            System.out.print("\t\t입력(번호) : ");
            inputSmallNum = s.next();
            
            //조건문
            if(inputSmallNum.equals("0")) {
               middleProductLook("7");
            }
            
            break;
         case "2":
            System.out.print("\t\t입력(번호) : ");
            inputSmallNum = s.next();
            
            if(inputSmallNum.equals("0")) {
               middleProductLook("7");
            }
            
            break;
         default :
            ui.bar();
            System.out.println("\t\t잘못된 입력입니다. 메인화면으로 돌아갑니다.");
            ui.bar();
            exhibition();
            break;
         }
         productSearch(inputNum, inputMiddleNum, inputSmallNum);
         break;
      default :
         ui.bar();
         System.out.println("\t\t잘못된 입력입니다. 메인화면으로 돌아갑니다.");
         ui.bar();
         exhibition();
         break;
      }

   }

   private static void productSearch(String inputNum, String inputMiddleNum, String inputSmallNum) {
	      /**
	       * 상품검색, 소분류 리스트 출력
	       */
	      
	      

	      try {

	         BufferedReader readerT = new BufferedReader(new FileReader("dat\\totalData.dat"));
	         BufferedReader readerC = new BufferedReader(new FileReader("dat\\customerCode.dat"));

	         String txtT = null;
	         String txtC = null;
	         
	         ArrayList<String> array = new ArrayList<>();

	         System.out.println("[일련번호]\t\t[가격]\t[재고]\t[카테고리]\t[제품명]\n");
	         System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	         
	         
	         String smallCategoryName = "";
	         
	         while ((txtC = readerC.readLine()) != null) {
	            
	            String tempC[] = txtC.split("■");
	            
	            if (tempC[0].equals(inputNum) && tempC[1].equals(inputMiddleNum) && tempC[2].equals(inputSmallNum)) {
	               smallCategoryName = tempC[3];
	               
	               break;
	            }

	         }
	         

	         readerC.close();
	   
	         
	         while ((txtT = readerT.readLine()) != null) {

	            String tempT[] = txtT.split("■");
	            
	            if (tempT[4].equals(smallCategoryName)) {
	               array.add(tempT[0]+"\t\t"+tempT[2]+"\t"+tempT[3]+"\t"+tempT[4]+"\t\t"+tempT[1]); 
	            }
	         }
	         readerT.close();
	         
	         
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
	        	   System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	              System.out.printf("\n                                     페이지 (%d/%d)\n",i,array.size()/5+1);
	              System.out.println("\t\t1.이전페이지\t2.다음페이지\t3.장바구니 담기\t0.뒤로가기\n");
	              System.out.println();
	              System.out.print("\t\t입력(번호) : ");
	              String answer = s.next();
	              System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	              if(answer.equals("1") && k!=5 && array.size() != k) {
	                 k = k-10;
	                 i = i-2;
	              } else if(answer.equals("1") && k==5) {
	                 k = k-5;
	                 i = i-1;
	              } else if(answer.equals("1") && array.size() == k) {
	                 k = k-5-(array.size()%5);
	                 i = i-2;
	              } else if(answer.equals("2") && array.size() == k) {
	                 k = k-(array.size()%5);
	              } else if(answer.equals("2")) {
	                 
	              } else if(answer.equals("3")) {
	                 inputBasket(); 
	                 break;
	              } else if(answer.equals("0")) {
	                 smallProductLook(inputNum, inputMiddleNum); 
	                 break;
	              } else if(answer.equals("#")) {
	                 exhibition(); 
	                 break;
	              } else {
	                 ui.bar();
	                 System.out.println("\t\t잘못된 입력입니다. 메인화면으로 돌아갑니다.");
	                 ui.bar();
	                 exhibition();
	                 break;
	              }   
	              i++;
	           }
	           k++;
	         }
	         
	      } catch (Exception e) {

	         e.printStackTrace();
	      }
	   }
}