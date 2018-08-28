package cvs.mainpage.login.manager;



import java.util.Scanner;

import cvs.function.Function;
import cvs.function.UI;



public class ManagerMode {
   
   private static Scanner scan;
   public static Function function;
   private static UI ui;
   
   static {
	   	ui = new UI();
         scan = new Scanner(System.in);
         function = new Function();
      }
   
   public static void main(String[] args) {
       ui.logo("관리자 모드");
       
       System.out.println("\t\t1. 제품 관리 및 재고 관리");
       System.out.println("\t\t2. 매출 관리");
       System.out.println("\t\t*. 종료");
       System.out.print("\t\t입력(번호) : ");
       String num = scan.nextLine();
       
       switch(num) {
         case "1":
            Inquire.main(null);
            break;
            
         case "2":
           Sales.main(null);
           break;
           
         case "*":
            //종료
            function.exit();
       }
       

   }
}