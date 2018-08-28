package cvs.mainpage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainPage {
   private static String inputNum;
   
   //입력을 받기위한 버퍼드리더 변수 선언
   private static BufferedReader input;
   private static Login login;
   private static Search search;
   private static Join join;
   
   static {
      input = new BufferedReader(new InputStreamReader(System.in));//버퍼드 리더 입력
      login = new Login();
      search  = new Search();
      join = new Join();
   }
   
   public static void main(String[] args) {
      mainPageUi();
   }

   private static void mainPageUi() {
	  System.out.println("#######################################################################");
	  System.out.println("■■■■■■■    ■            ■■       ■     ■■■■■■■     ■     ");
	  System.out.println("    ■  ■        ■          ■    ■     ■               ■       ■");
	  System.out.println("    ■  ■  ■■■■         ■      ■    ■             ■         ■ ");
	  System.out.println("    ■  ■        ■        ■        ■   ■           ■       ■■■ ");
	  System.out.println("    ■  ■  ■■■■         ■      ■    ■         ■  ■         ■ ");
	  System.out.println("    ■  ■        ■          ■    ■     ■       ■      ■       ■");
	  System.out.println("■■■■■■■    ■           ■■■      ■     ■          ■     ■");
	  System.out.println("                                           ■                        ■  ");
	  System.out.println("  ■                                       ■     ■■■■■■■■■■    ");
	  System.out.println("  ■                                       ■     ■                ■  ");
	  System.out.println("  ■                     ■■■■■■■■  ■     ■                ■  ");
	  System.out.println("  ■■■■■■■■■                       ■     ■■■■■■■■■■ ");
      System.out.println("#######################################################################");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("\t\t  1. 로그인 하기");
      System.out.println("\t\t  2. 회원 번호/비밀 번호 찾기");
      System.out.println("\t\t  3. 회원 가입");
      try {
         System.out.print("\t\t 입력: ");
         inputNum = input.readLine();
         
         
         if(inputNum.equals("1")) {
            login.main(null);
         }else if(inputNum.equals("2")) {
            search.main(null);
         }else if(inputNum.equals("3")) {
            join.main(null);
         }
         
         
         
         
      } catch (Exception e) {
         System.out.println("Login.loginMainUi():" + e.toString());
      }
      
      
   }
}