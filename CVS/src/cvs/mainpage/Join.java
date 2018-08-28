package cvs.mainpage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cvs.function.Effectiveness;
import cvs.function.Function;
import cvs.function.Member;
import cvs.function.UI;

/**
 * 사용자가 회원정보을 입력해 회원가입
 * 회원정보(이름, 비밀번호 , 성별, 생년월일, 전화번호, 카드번호)
 * @author 헌상민
 *
 */
public class Join {
   
   private static BufferedReader input;
   private static String PATH; //파일 주소
   private static String memberName = "";      //이름
   private static String memberPassword ="";   //비밀 번호
   private static String memberGender = "";    //성별
   private static String memberBirth = "";     //생년월일
   private static String memberPhoneNum = "";   //전화번호
   private static String memberCardNum = "";   //카드번호
   private static String memberNum ;         //회원번호
   private static ArrayList<Member> memberList = new ArrayList<Member>();//멤버리스트
   public static int checkCount =0;         //로그인 횟수
   public static String errorKind ="";         //에러 내용
   private static Member member;            
   private static int memberCount;            //회원번호 수 누적 변수
   private static Function function;         
   private static Effectiveness effectiveness;
   private static UI ui;
   
   static {
	  ui = new UI();
	  effectiveness = new Effectiveness();
      member = new Member();
      PATH = "dat\\data.txt";
      input = new BufferedReader(new InputStreamReader(System.in));
   }//주소,입력 선언
   
   /**
    * 메인 화면 출력
    * @param main
    */
   public static void main(String[] args) {
      joinMemberUi();
   }//main

   /**
    * 회원가입 화면, 입력창
    */
   public static void joinMemberUi() {
         
     ui.logo("회원가입");
      
      
      
      try {
         
         System.out.println();
         System.out.print("               이름 :");
         memberName = input.readLine();
         System.out.println();
         
         System.out.print("               비밀번호 :");
         memberPassword = input.readLine();
         System.out.println();
         
         System.out.print("               성별(0:남자,1:여자) :");
         memberGender = input.readLine();
         System.out.println();
         
         System.out.print("               생년월일 :");
         memberBirth = input.readLine();
         System.out.println();
         
         System.out.print("               전화번호 :");
         memberPhoneNum = input.readLine();
         System.out.println();
         
         System.out.print("               카드번호 :");
         memberCardNum = input.readLine();
         
         addJoinMember();
         
         System.out.println();
         
         MainPage mainpage = new MainPage();
         mainpage.main(null);
         

         
         
      } catch (Exception e) {
         System.out.println("Login.loginMainUi():" + e.toString());
      }
      
      
      
      
   }//joinMemberUi  
   
   /**
    * 회원가입 조건 통과시 화면 창
    */
   public static void addJoinMember() {
      
	  effectiveness.checkName(memberName);
	  effectiveness.checkPassWord(memberPassword);
	  effectiveness.checkGender(memberGender);
	  effectiveness.checkBirth(memberBirth);
	  effectiveness.checkPhoneNum(memberPhoneNum);
	  effectiveness.checkCardNum(memberCardNum);
      calculationMemberNum();
      
      
      if(checkCount == 0) {
         
         createSep();
         addFileMember();
         System.out.println();
         ui.bar();
         System.out.println("                              회원가입에 성공하셨습니다.");
         ui.bar();
         System.out.println();
         System.out.println();
         
      }else  {
        System.out.println();
        System.out.println("             "+errorKind);
         ui.bar();
         System.out.println("         위와 같이 입력하신 정보가 조건에 맞지않아 회원가입에 실패하셨습니다.");
         ui.bar();
         System.out.println();          
         
      }
      
   }
  
   /**
    * 파일에 회원을 추가할때 회원번호를 계산할 수 있는 메서드
    */
   private static void calculationMemberNum() {
      String finalMemberNum ="";
      try {
         BufferedReader reader = new BufferedReader(new FileReader(PATH));
         
         String line = "";
         
         while((line = reader.readLine()) != null) {
            String[] temp = line.split("■");
            finalMemberNum = temp[0];
            
            
         }
         //System.out.println(finalMemberNum);
         memberCount = Integer.parseInt(finalMemberNum) +1;
            
         
      } catch (Exception e) {
         System.out.println("Login.checkId():" + e.toString());
      }
      
   }
   
   /**
    * 회원을 파일에 쓰는 메서드
    */
   private static void addFileMember() {
      
      memberList.add(new Member(memberNum, memberPassword, memberName, memberGender, memberBirth, memberCardNum, memberPhoneNum));
      
      
      
      try {
         BufferedWriter writer = null;
         writer = new BufferedWriter(new FileWriter(PATH,true)); 
         
         for(Member member : memberList) {
             String line = String.format("%04d■%s■%s■%s■%s■%s■%s"
//            System.out.printf("%04d■%s■%s■%s■%s■%s■%s"
               //(String, int, String, String, String, String, String, void)
                   ,memberCount 
                         ,member.getPassword()
                         ,member.getName()
                         ,member.getGender().equals("0")? "남자":"여자"
                         ,member.getBirth()
                         ,member.getCardNum()
                         ,member.getPhonNum()
                         );
            writer.write(line);
            writer.newLine();
         }
         writer.close();
         
      } catch (Exception e) {
         System.out.println("Join.addFileMember():" + e.toString());
      }
   }
   
   /**
    * 입력받은 카드번호,전화번호에 구분자를 추가하는 메서드
    */
   public static void createSep() {
      String temp1 = "";
      String temp2 = "";
      String temp3 = "";
      String temp4 = "";
      
      temp1 = memberPhoneNum.substring(0,3);
      temp2 = memberPhoneNum.substring(3,7);
      temp3 = memberPhoneNum.substring(7);
      memberPhoneNum = temp1+"-"+temp2+"-"+temp3;
      
      member.setPhonNum(memberPhoneNum);
      
      temp1 = memberCardNum.substring(0,4);
      temp2 = memberCardNum.substring(4,8);
      temp3 = memberCardNum.substring(8,12);
      temp4 = memberCardNum.substring(12);
      memberCardNum = temp1+"-"+temp2+"-"+temp3 +"-" + temp4 ;
      
      member.setCardNum(memberCardNum);
      
      
   }
}