package cvs.mainpage;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import cvs.function.*;
import cvs.mainpage.*;
import cvs.mainpage.login.customer.Buy;
import cvs.mainpage.login.manager.ManagerMode;

/**
 * 로그인과 마이페이지 기능 
 * @author 한상민
 *
 */
public class Login {
   

   private static BufferedReader input;
   private static String PATH;
   private static boolean isPassId  = false;   //회원번호 + 비밀번호 일치
   private static Scanner s;
   private String answer;
   private static String memberNumber = "";          //입력받을 회원 번호
   private static String memberPwd = "";            //입력받을 비밀 번호
   private static String memberPhoneNum = "";      //입력받을 전화 번호
   private static String memberName = "";            //입력받을 이름
   private static String memberBirth = "";         //입력받을 생년 월일
   private static String findIdNumber = "";         //찾을때 입력한 회원번호,로그인한 회원 번호
   private static String finePwdNumber = "";         //찾을때 입력한 비밀번호
   private static String inputNum;                  //입력받은 선택 번호(문자열)
   private static ArrayList<Member> memberList = new ArrayList<Member>();//회원 정보들을 넣을 배열
   private static int failCount =1;               //로그인 실패 횟수
   private static Member member;
   private static MainPage mainpage;
   private static Function function;
   private static UI ui;
   
   static {
	   function = new Function();
	   mainpage = new MainPage();
	   ui = new UI();
      s = new Scanner(System.in);
      member = new Member();
      PATH = "dat\\data.txt"; // 주소
      input = new BufferedReader(new InputStreamReader(System.in));//버퍼드 리더 입력
   }
   
   /**
    * 메인 화면 출력
    * @param args
    */
   public static void main(String[] args) {
      loginMainUi();
      
   }

   /**
    * 로그인 메인 화면 구현
    */
   private static void loginMainUi() {
      System.out.println();
      System.out.println();
      ui.logo("로그인");
      
      try {
        System.out.println();
         System.out.printf("\t\t\t회원 번호:");
         memberNumber = s.next();
         System.out.println();
         
         System.out.printf("\t\t\t비밀 번호:");
         memberPwd = s.next();
         checkId();
         
      } catch (Exception e) {
         System.out.println("Login.loginMainUi():" + e.toString());
      }
   }
   
   /**
    * 탈퇴 확인 입력 메서드
    */
   private static void secessionMemberUi() {
      
      System.out.println();
      System.out.println();
      
      ui.logo("회원탈퇴");
      System.out.println("\t\t정말로 회원을 탈퇴하시겠습니까?(Y/N)");
      
      try {
         System.out.print("\t\t\t입력 :");
         inputNum = s.next();
         
         if(inputNum.equals("Y") || inputNum.equals("y")) {
            DeleteMember(findIdNumber);//파일에서 회원을 삭제할 수 있는 메소드 호출
            System.out.println("\t삭제 완료 되었습니다.");
            mainpage.main(null);
            
         }else if(inputNum.equals("N") || inputNum.equals("n")){
            myPageUi();
         }
      } catch (Exception e) {
         System.out.println("Login.secessionMemberUi():" + e.toString());
      }
      
      
   }//secessionMemberUi
   
   /**
    * 입력된 회원 번호,비밀번호 확인 메서드
    */
   private static void checkId() {
      //관리자 아이디랑 비밀번호
      String adminId = "admin";
      String adminPwd = "999999";
      try {
         BufferedReader reader = new BufferedReader(new FileReader(PATH));
         
         String line = "";
         
         ArrayList<String> array = new ArrayList<>();
         
         
         while((line = reader.readLine()) != null) {
            
            String[] temp = line.split("■");
            
            //회원 번호와 비밀 번호를 비교
            if(temp[0].equals("num")) {
               isPassId = false;
            }else if(temp[0].equals(memberNumber)) {
                     if(temp[1].equals(memberPwd)) {
                        member.setMemberNum(temp[0]);
                        member.setName(temp[2]);
                        member.setGender(temp[3]);
                        member.setBirth(temp[4]);
                        member.setPhonNum(temp[6]);
                        member.setCardNum(temp[5]);
                     
                        findIdNumber = temp[0];   //로그인된 회원 번호를 저장
                        memberName = temp[2];   //찾은 회원 이름을 저장 //님 환영합니다.문구를 추가학위해
                        finePwdNumber = temp[1];//로그인된 비밀 번호를 저장
                        isPassId = true;      //로그인 되었을 시 true를 저장
                     
                     }
            }
            
         }
         
         if(isPassId) {//로그인 성공시 
            if(adminId.equals(findIdNumber)) {
               if(adminPwd.equals(finePwdNumber)) {
                 System.out.println();
                  System.out.println("\t\t관리자 모드입니다.");
                  ManagerMode.main(null);
                  //관리자모드();
               }
            }else {//관리로그인 실패시
               loginSuccessUi();//로그인 실패 메서드 실행
            }
         }else {
            if(failCount == 6) {//6번 틀렸을 시 종료
               MainPage.main(null); // 처음화면으로 되돌아가게 만듬
            }
            System.out.println();
            System.out.println("\t\t  입력된 정보가 틀립니다."+failCount+"/5");
            System.out.println("=========================================================================================");
            System.out.println();
            loginFailUi();
         }
         
      } catch (Exception e) {
         System.out.println("Login.checkId():" + e.toString());
      }
      
   
   }
   
   /**
    * 로그인 실패횟수 확인 메서드
    */
   private static void loginFailUi() {
      System.out.println();
      ui.logo("로그인");
      try {
         failCount ++; // 실패시 카운트 증가
         System.out.print("\t\t\t회원 번호:");
         memberNumber = s.next();
         System.out.print("\t\t\t비밀 번호:");
         memberPwd = s.next();
         
         checkId();
         
      } catch (Exception e) {
         System.out.println("Login.loginMainUi():" + e.toString());
      }
   }
   
   /**
    * 로그인 성공시 메뉴 구현
    * (1.구매 , 2.마이페이지)
    */
   public static void loginSuccessUi() {
      System.out.println();
      System.out.println();
      ui.logo("로그인");
      System.out.println("\t\t\t로그인 되었습니다.");
      System.out.println("\t\t\t"+memberName+"님, 환영합니다.");
      System.out.println("\n\n\t\t\t     1.구매하기\n\t\t\t     2.마이페이지\n\t\t\t     3.로그아웃");
      System.out.println();
      
      try {
         System.out.print("\t\t\t선택:");
         inputNum = s.next();
         
         if(inputNum.equals("1")) {
            Buy.main(null);
         }else if(inputNum.equals("2")) {
            myPageUi();
         }else if(inputNum.equals("3")) {
           MainPage.main(null);
         }
      } catch (Exception e) {
         System.out.println("Login.loginMainUi():" + e.toString());
      }
   }
   
   /**
    * 마이페이지 화면
    * (1.회원정보 수정,2.회원 탈퇴,3.구매이력 보기,4.이전페이지)
    */
   private static void myPageUi() {
      System.out.println();
      System.out.println();
      ui.logo("마이페이지");
      
      memberInfoUi();
      System.out.println("=========================================================================================");
      System.out.println("\t1.회원정보 수정\t\t\t2.회원 탈퇴");
      System.out.println("\t3.구매이력 보기\t\t\t0.이전 페이지");
      System.out.println();
      
      try {
         System.out.print("\t\t\t입력 :");
         //inputNum = input.readLine();
         inputNum = s.next();
         
         
         if(inputNum.equals("1")) {
            myPageModifiedUi(); 
         }else if(inputNum.equals("2")) {
            secessionMemberUi();
         }else if(inputNum.equals("3")) {
            purchaseHistory(memberNumber);
         }else if(inputNum.equals("0")) {
            loginSuccessUi();;
         }
      } catch (Exception e) {
         System.out.println("Login.loginMainUi():" + e.toString());
      }
      
   }
   
   /**
    * 사용자의 구매이력 찾기 메서드
    * @param memberNumber -회원번호
    */
   private static void purchaseHistory(String memberNumber) {
      
      try {
            BufferedReader reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
            BufferedReader orderReader = new BufferedReader(new FileReader("dat\\order.dat"));
            
            String txt = null;
            
            int totalMoney = 0;
            
            while((txt = orderReader.readLine())!= null) {
               String temp[] = txt.split("■");

               if(memberNumber.equals(temp[3])) {
                  
                  while((txt = reader.readLine())!= null) {
                     String totalTemp[] = txt.split("■");
                     
                     if(temp[1].equals(totalTemp[1])) {
                        totalMoney = totalMoney + (Integer.parseInt(totalTemp[2]) * Integer.parseInt(temp[2]));
                     }
                  }   
                  reader = new BufferedReader(new FileReader("dat\\totalData.dat"));
               }
            }
            
            if(totalMoney == 0) {
               System.out.println();
               System.out.println("\t\t구매이력이 없습니다.");
               function.pause();
               
               myPageUi();
            }else {
               System.out.println();
               System.out.printf("\t%s님의 구매 금액은 %,d 원입니다.",memberName,totalMoney);
               function.pause();
               myPageUi();
            }

         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

      
      
   }
   /**
    * 회원 정보 화면과 메뉴 구현 메서드
    * (1.이름,2.카드번호,3.비밀번호,4.전화번호)
    */
   private static void myPageModifiedUi() {
      System.out.println();
      System.out.println();
      ui.logo("회원 수정");
      memberInfoUi();//로그인한 사용자의 회원 정보를 보여줌
      System.out.println("=========================================================================================");
      System.out.println("\t1.이름\n\t2.카드번호");
      System.out.println("\t3.비밀번호\n\t4.전화번호");
      System.out.println("\t0.이전페이지");
      
      try {
         System.out.print("\t\t\t입력 :");
         //inputNum = input.readLine();
         inputNum = s.next();
         
         if(inputNum.equals("0")) {
            myPageUi();
         }else {
            memberChangeInfo(inputNum,member.getMemberNum());
         }
         
            
      } catch (Exception e) {
         System.out.println("Login.loginMainUi():" + e.toString());
      }
      
   }//myPageModifiedUi
   
   /**
    * 로그인된 사용자 정보 화면
    */
   private static void memberInfoUi() {
      System.out.println("\t⊙ 회원 번호 : " +member.getMemberNum());
      System.out.println();
      System.out.println("\t⊙ 이  름 : " +member.getName());
      System.out.println();
      System.out.println("\t⊙ 성  별 : " +member.getGender());
      System.out.println();
      System.out.println("\t⊙ 나  이 : " +birthChangeAge());
      System.out.println();
      System.out.println("\t⊙ 전화 번호 : " +member.getPhonNum());
      System.out.println();
      System.out.println("\t⊙ 카드 번호 : " +member.getCardNum());
      System.out.println();
      
   }//memberInfoUi
   

   /**
    * 입력받은 생년월일을 나이로 계산 메서드
    * @return  -나이
    */
   private static int birthChangeAge() {
      
      String a = member.getBirth().substring(0, 1);
      
      if(!a.equals("0")) { // ~ 1999년생
         member.setAge(2018 - Integer.parseInt("19" +  member.getBirth().substring(0, 2)) + 1);
      } else { // 2000년생 ~
         member.setAge(2018 - Integer.parseInt("20" +  member.getBirth().substring(0, 2)) + 1);
      }
      
      
      return member.getAge();
   }
   
   
      //회원 정보 수정
   /**
    * 수정할 정보를 입력받아 수정 메서드
    * @param inputNum     -메뉴 입력 번호
    * @param findIdNumber -회원번호
    */
   public static void memberChangeInfo(String inputNum, String findIdNumber) {
      String temp1 = "";
        String temp2 = "";
        String temp3 = "";
        String temp4 = "";
        try {
        
        
        String changeValue;//바꾸려는 값 변수선언
        //String checkValue; //확인하려는 값 변수 선언
        System.out.println();
          System.out.printf("\t바꾸려는 값 입력 :  ");
          changeValue = s.next();
          
          BufferedReader reader = new BufferedReader(new FileReader(PATH));
          
          ArrayList<String> array = new ArrayList<>();
          
          String txt = null;
          
          //이름 1 - 3 카드번호 2 - 6 비밀번호 3 - 2 전화번호 4 - 7
          
          if(inputNum.equals("1")) {//이름
             inputNum = (Integer.parseInt(inputNum)+1)+"";
             member.setName(changeValue);
          }else if(inputNum.equals("2")) {//카드번호
             inputNum = (Integer.parseInt(inputNum)+3)+"";
             member.setCardNum(changeValue);  
              temp1 = changeValue.substring(0,4);
             temp2 = changeValue.substring(4,8);
             temp3 = changeValue.substring(8,12);
             temp4 = changeValue.substring(12);
             changeValue = temp1+"-"+temp2+"-"+temp3 +"-" + temp4 ;
            
            member.setPhonNum(memberPhoneNum);
          }else if(inputNum.equals("3")){//비밀번호
             member.setCardNum(changeValue);
             inputNum = (Integer.parseInt(inputNum)-2)+"";
          }else if(inputNum.equals("4")) {//전화번호
             inputNum = (Integer.parseInt(inputNum)+2)+"";
             member.setPhonNum(changeValue);
             temp1 = changeValue.substring(0,3);
              temp2 = changeValue.substring(3,7);
              temp3 = changeValue.substring(7);
              changeValue = temp1+"-"+temp2+"-"+temp3;
          }
          
          //수정하려는 회원 찾음
          while((txt = reader.readLine()) != null) {
             String[] temp = txt.split("■");
             
             if((findIdNumber.replace("-", "")).equals(temp[0].replace("-", ""))) {
                temp[Integer.parseInt(inputNum)] = changeValue;
                System.out.println("\t"+temp[Integer.parseInt(inputNum)]);
             }
             array.add(temp[0]+"■"+temp[1]+"■"+temp[2]+"■"+temp[3]+"■"+temp[4]+"■"+temp[5]+"■"+temp[6]);
          }
      
          reader.close();
          
          BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
          
          for(int i=0; i<array.size(); i++) {
             writer.write(array.get(i));
             writer.newLine();
          }
          
          writer.close();
          
          System.out.println("\t\t수정이 완료 되었습니다.");
          myPageUi();
          
       } catch (Exception e) {
          System.out.println("Inquire.finalChangeProduct() : " + e.toString());
           }
     }
      
   /**
    * 회원 탈퇴할 수 있는 메서드
    * @param findIdNumber - 회원 번호
    */
   public static void DeleteMember(String findIdNumber) {
      try {
         ArrayList<String> list = new ArrayList<>();
            
          
         BufferedReader reader = new BufferedReader(new FileReader(PATH));
       
         String txt = null;
       
         while((txt = reader.readLine())!= null) {
            String temp[] = txt.split("■");
          
            if(temp[0].equals(findIdNumber)) {
             
            } else {
               list.add(txt);
            }
         }
       
         reader.close();
       
         BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
           
         for(int i=0; i<list.size(); i++) {
            writer.write(list.get(i));
            writer.newLine();
         }
           
         writer.close();
      } catch (Exception e) {
         System.out.println("DeleteMember.enclosing_method() : " + e.toString());
      }
      
      }
   
}