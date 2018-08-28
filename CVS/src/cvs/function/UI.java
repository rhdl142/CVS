package cvs.function;

/**
 * 
 * @author 임광민
 * 전체 UI 통합 클래스
 */
public class UI {
	/**
	 * 
	 * @param logo - logo에 들어갈 문자
	 * logo
	 */
	public void logo(String logo) {
		System.out.println("=========================================================================================");
		System.out.printf("                                        %s\n",logo);
		System.out.println("=========================================================================================");
	}
	
	/**
	 * 경계선 출력
	 */
	public void bar() {
		System.out.println("=========================================================================================");
	}
	
	/**
	 * 
	 * @param menu - menu에 들어갈 문자열 배열
	 * @param number - menu 총 번호
	 * menu 출력
	 */
	public void menu(String[] menu, int number) {
		System.out.println();
		for(int i=0; i<number; i=i+2) {
			if(number%2==0) {
				if(number-2==i) {
					System.out.printf("             %d. %s                               %s. %s\n\n",i+1,menu[i],"0",menu[i+1]);
				} else {
					System.out.printf("             %d. %s                               %d. %s\n\n",i+1,menu[i],i+2,menu[i+1]);
				}
			} else {
				if(number-1==i) {
					System.out.printf("             *. %s  \n\n",menu[i]);
				} else {
					System.out.printf("             %d. %s                               %d. %s\n\n",i+1,menu[i],i+2,menu[i+1]);
				}
			}
		}
	}
}
