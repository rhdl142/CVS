package cvs.function;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author 임광민
 * 전체 기능 클래스
*/

public class Function {
	private static Scanner s;
	private String answer;
	private BufferedReader reader;
	private static String txt;
	
	static {
		txt = null;
		s = new Scanner(System.in);
	}
	
	/**
	 * 
	 * @param 읽고자 하는 파일 경로
	 * 파일안에 내용 읽어서 출력
	 */
	public void reader(String path) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
		         
			String txt = null;
		         
			while((txt = reader.readLine()) != null) {
				System.out.println(txt);
			}		      
			reader.close();			
		} catch (Exception e) {
			System.out.println("Function.reader() : " + e.toString());
		}
	}
	
	/**
	 * 엔터키 입력후 시작
	 */
	public void pause() {
        try {
           System.out.println();
           System.out.println("계속하시려면 엔터키를 누르세요");
             System.in.read();
              
        } catch (IOException e) {
           e.printStackTrace();
        }
     }
	
	/**
	 * 
	 * @param temp - 사용자가 입력할때 도움말. 
	 * @return 사용자가 입력한 값
	 */
	public String input(String temp) {
		System.out.printf("                                 %s : ",temp);
		answer = s.next();
		
		return answer;
	}
	
	/**
	 * 콘솔창 전부 정리
	 */
	public void clear() {
		for(int i=0; i<20; i++) {
			System.out.println();
		}
	}
	/**
	 * 콘솔창 중간 정리
	 */
	public void clear1() {
		System.out.println("\n\n\n");
	}
	
	/**
	 * 프로그램 종료
	 */
	public void exit() {
		System.out.println();
		System.out.println("시스템이 종료 되었습니다.");
		System.exit(0);
	}
}
