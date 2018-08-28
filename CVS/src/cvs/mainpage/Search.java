package cvs.mainpage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cvs.function.Function;
import cvs.function.Member;
import cvs.function.UI;

/**
 * 
 * @author 한상민
 *
 */
public class Search {
      
      private static BufferedReader input;         //입력을 받기위한 버퍼드리더 변수 선언
      private static String PATH;               //파일 주소를 담은 변수 선언
      private static boolean isPassId  = false;    //회원번호 + 비밀번호 일치
      private static String memberNumber = "";     //입력받을 회원 번호
      private static String memberPwd = "";          //입력받을 비밀 번호
      private static String memberPhoneNum = "";      //입력받을 전화 번호
      private static String memberName = "";         //입력받을 이름
      private static String memberBirth = "";      //입력받을 생년 월일
      private static String findIdNumber = "";      //찾을때 입력한 회원번호,로그인한 회원 번호
      private static String finePwdNumber = "";      //찾을때 입력한 비밀번호
      private static String inputNum;               //입력받은 선택 번호(문자열)
      private static ArrayList<Member> memberList = new ArrayList<Member>();//회원 정보들을 넣을 배열
      private static int failCount =1;               //로그인 실패 횟수
      private static MainPage mainpage;
      private static Function function;
      private static UI ui;
      
      static {
    	 ui = new UI();
    	 function = new Function();
         PATH = "dat\\data.txt"; // 주소값 저장
         input = new BufferedReader(new InputStreamReader(System.in));//버퍼드 리더 입력
         mainpage = new MainPage(); 
      }
   
      
      
   /**
    * 메인 화면창
    * @param args
    */
   public static void main(String[] args) {
      
      searchUi();

   }//main
   
   /**
    * 회원번호 비밀번호 찾기 화면
    */
   private static void searchUi() {

     System.out.println();
     System.out.println();
      ui.logo("회원번호/비밀번호 찾기");
      System.out.println();
      System.out.println();
      System.out.println("\t\t\t  1. 회원 번호 찾기");
      System.out.println("\t\t\t  2. 비밀 번호 찾기");
      System.out.println("\t\t\t  0. 이전 페이지");
      
      try {
        System.out.println();
         System.out.print("\t\t 입력: ");
         inputNum = input.readLine();
         
         
         if(inputNum.equals("1")) {
            searchNumUi();
         }else if(inputNum.equals("2")) {
            searchPwdUi();
         }else if(inputNum.equals("0")) {
            MainPage.main(null);
         }
         
         
         
         
      } catch (Exception e) {
         System.out.println("Login.loginMainUi():" + e.toString());
      }
   }
   
   /**
    * 비밀번호 찾기,  비밀번호 찾기 화면과 입력받는 메서드
    */
   private static void searchPwdUi() {
      System.out.println();
      System.out.println();
      ui.logo("비밀번호 찾기");
        try {
           System.out.print("\t\t\t회원 이름:");
           memberName = input.readLine();
           System.out.print("\t\t\t생년 월일:");
           memberBirth = input.readLine();
           System.out.print("\t\t\t전화 번호:");
           memberPhoneNum = input.readLine();
           
           
           searchPwd();//입력받은 값들을 이용해 회원을 찾을 수 있는 메소드
           
        } catch (Exception e) {
           System.out.println("Login.loginMainUi():" + e.toString());
        }
   }//findPwdUi

   /**
    * 비밀번호 찾기, 파일에서 회원이름과 전화번호를 이용해 비밀 번호를 찾는 메서드
    */
   public static void searchPwd() {
      try {
         BufferedReader reader = new BufferedReader(new FileReader(PATH));
            
         String line = "";
            
         ArrayList<String> array = new ArrayList<>();
            
         //전체파일 읽기
         while((line = reader.readLine()) != null) {
               
            String[] temp = line.split("■");//구분자(■)를 이용해 한줄의 정보를 구분
               
            if(temp[2].equals(memberName)) {//회원 이름을 먼저 비교
                 if(temp[6].replaceAll("-","").equals(memberPhoneNum)) { //회원 전화 번호를 비교
                    if(temp[4].equals(memberBirth)) {
                       isPassId = true;   // 비밀 번호를 찾았다고 저장
                       finePwdNumber = temp[1];//비교한 줄에 회원 번호를 변수에 넣음
                    }
                 }
              }
           }
            
            if(isPassId) {//비밀번호 찾았는지 물음
               System.out.println();
               System.out.println("\t핸드폰 인증되셨습니다.");
               System.out.println("\t당신 비밀번호는 "+finePwdNumber+" 입니다.");
               System.out.println();
               mainpage.main(null);
            }else {
               System.out.println("잘못 입력하셨습니다.");
               function.pause();
               mainpage.main(null);
            }
            
         } catch (Exception e) {
            System.out.println("Login.checkId():" + e.toString());
         }
      }//findPwd
      
      //회원 번호를 찾을 수 있는 메소드
   
   
   /**
    * 회원 번호를 찾을 수 있는 메서드
    */
   private static void searchNum() {
   
     try {
        BufferedReader reader = new BufferedReader(new FileReader(PATH));
        
        String line = "";
    
        ArrayList<String> array = new ArrayList<>();
    
    
        while((line = reader.readLine()) != null) {
       
           String[] temp = line.split("■");
       
       
           
        if(temp[2].equals(memberName)) {
          if(temp[6].replaceAll("-","").equals(memberPhoneNum)) {
             isPassId = true;
             findIdNumber = temp[0];
          }
       }
    }
    
    if(isPassId) {
       System.out.println();
       System.out.println("\t\t당신의 회원번호는 "+findIdNumber + " 입니다.");
       System.out.println();
       mainpage.main(null);
       
    }else {
       System.out.println();
       System.out.println("\t\t잘못 입력하셨습니다.");
       main(null);
    }
    
 } catch (Exception e) {
    System.out.println("Login.checkId():" + e.toString());
     }
  }
      
      
   /**
    * 아이디 찾기 화면 구현
    */
   private static void searchNumUi() {
     System.out.println();
     System.out.println();
     ui.logo("회원번호 찾기");
      try {
         System.out.print("\t\t\t회원 이름:");
         memberName = input.readLine();
         System.out.print("\t\t\t전화 번호:");
         memberPhoneNum = input.readLine();
        
        
         searchNum();
        
      } catch (Exception e) {
         System.out.println("Login.loginMainUi():" + e.toString());
      }
   }//findIdUi
}