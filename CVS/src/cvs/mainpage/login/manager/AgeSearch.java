package cvs.mainpage.login.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import cvs.function.Function;
import cvs.function.UI;

/**
 * 
 * @author 정필립
 *
 */
public class AgeSearch {

	static Scanner scan = new Scanner(System.in);
	/**
	 *주문 데이터
	 */
	public static final String ORDER;
	/**
	 * 회원 정보 데이터
	 */
	public static final String AGE;
	/**
	 * 구매 목록 데이터
	 */
	public static final String TOTAL;
	private static UI ui;
	private static Function function;
	
	static {
		function = new Function();
		ui = new UI();
		ORDER = "dat\\order.dat";
		AGE = "dat\\data.txt";
		TOTAL = "dat\\totalData.dat";
	}
	/**
	 * 테스트용 main()
	 * @param args null
	 */
	public static void main(String[] args) {

		String year = "";
		String month = "";

		System.out.print("\t\t\t입력 : ");

		String num = scan.nextLine();

		switch (num) {
		case "1":
			System.out.print("\t\t\t날짜 입력(YYYYMMDD) : ");

			String date = scan.nextLine();

			if (date.length() < 9) {
				year = date.substring(0, 4);
				month = date.substring(4, 6);
				String day = date.substring(6, 8);
				ageGraph(year, month, day, date);
			}
			break;
		case "2":
			System.out.print("\t\t\t날짜 입력(YYYYMM) : ");

			date = scan.nextLine();

			if (date.length() < 7) {
				year = date.substring(0, 4);
				month = date.substring(4, 6);
				ageGraph(year, month, date);
			} else {
				System.out.println("\t\t\tYYYYMM 숫자 6글자로 입력해주세요.");
			}
			break;
		case "3":
			System.out.print("\t\t\t년도를 입력 하세요 (YYYY) : ");
			year = scan.nextLine();
			System.out.println();
			System.out.print("\t\t\t분기 입력 (번호) : ");
			String quarter = scan.nextLine();
			ageGraph(year, quarter);
			break;
		default:
			break;
		}

	}
	
	/**
	 * 일별 연령별 매출을 조회하는 메소드
	 * @param year 입력한 값에서 년도만 따로 뺀 값
	 * @param month 입력한 값에서 월만 따로 뺀 값
	 * @param day 입력한 값에서 일만 따로 뺀 값
	 * @param date 입력한 날짜 총 값
	 */
	public static void ageGraph(String year, String month, String day, String date) {
		ui.bar();
		System.out.printf("                                %s년%s월%s일 연령별 매출조회\n", year, month, day);
		ui.bar();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(ORDER));
			BufferedReader user = new BufferedReader(new FileReader(AGE));
			BufferedReader total = new BufferedReader(new FileReader(TOTAL));

			int count = 0;			  //총 이용자 수 측정
			int price_상품가격 = 0; //총 상품 판매 가격 측정
			int age = 0;			  //나이 산정을 위한 변수

			int[] score = new int[5]; // 연령대별 배열

			String line = "";
			String priceLine = "";
			String ageLine = "";

			while ((line = reader.readLine()) != null) {//구입 목록 데이터 스캔
				String[] temp = line.split("■");

				if (temp[4].equals(date)) {//입력한 년도와 구입한 년도와 비교

					count++;

					while ((priceLine = total.readLine()) != null) {//제품 목록 데이터 스캔

						String[] temp2 = priceLine.split("■");
						if (temp[0].equals(temp2[0])) {//상품의 일련번호와 구입목록의 상품 일련번호가 일치하는 지 비교
							price_상품가격 += Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]);

						}

						while ((ageLine = user.readLine()) != null) {//회원 정보 데이터 스캔
							String[] temp3 = ageLine.split("■");

							if (temp[3].equals(temp3[0])) {

								String a = temp3[4].substring(0, 1);

								if (!a.equals("0")) {
									age = 2018 - Integer.parseInt("19" + temp3[4].substring(0, 2)) + 1;
								} else {
									age = 2018 - Integer.parseInt("20" + temp3[4].substring(0, 2)) + 1;
								}

								int count2 = age / 10; //연령대 산정

								if (count2 == 1) {//10대 이하
									score[0] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));

								} else if (count2 == 2) {//20대 
									score[1] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
								} else if (count2 == 3) {//30대
									score[2] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
								} else if (count2 == 4) {//40대
									score[3] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
								} else if (count2 > 4) {//50대이상
									score[4] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
								}

							}

						}
					}

					user = new BufferedReader(new FileReader(AGE));
					total = new BufferedReader(new FileReader(TOTAL));
				}
			}

			System.out.println("■단위당 10000원");

			String[][] matrix = new String[5][10];//그래프 배열 생성

			for (int i = 0; i < matrix.length; i++) {//그래프 만들기
				for (int j = 0; j < (score[i] / 10000); j++) {
					matrix[i][j] = "■";

				}
			}

			dump(matrix);
			System.out.printf("10대   20대     30대     40대    50대이상\n");

			System.out.printf("총이용자 수 : %d\n", count);
			System.out.printf("총 매출 금액 : %,d원\n", price_상품가격);
			ui.bar();
			System.out.println("\t\t\t1. 연령별 매출 상세 보기");
			System.out.println("\t\t\t0. 뒤로가기");

			System.out.print("\t\t\t입력 : ");
			String select = scan.nextLine();

			switch (select) {
			case "1":
				// 연령별 매출 상세보기 클래스 이름
				break;
			case "0":
				Sales.daySalesLookupPrint(year, month, day, date);
				break;
			}

			reader.close();
			user.close();

		} catch (Exception e) {
			System.out.println("AgeSearch.ageGraph() : " + e.toString());
		}
	}
	
	/**
	 * 월별 연령별 매출을 조회하는 메소드
	 * @param year 입력한 값에서 년도만 빠로 뺀 값
	 * @param month 입력한 값에서 월만 빠로 뺀 값
	 * @param date 입력한 값
	 */
	   
	public static void ageGraph(String year, String month, String date) { 
		ui.bar();
		System.out.printf("                                %s년%s월 연령별 매출조회\n", year, month);
		ui.bar();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(ORDER));
			BufferedReader user = new BufferedReader(new FileReader(AGE));
			BufferedReader total = new BufferedReader(new FileReader(TOTAL));

			int count = 0;
			int price_상품가격 = 0;
			int age = 0;

			int[] score = new int[5]; // 연령대별 배열

			String line = "";
			String priceLine = "";
			String ageLine = "";

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");

				if (temp[4].contains(date.substring(0, 6))) {

					count++;

					while ((priceLine = total.readLine()) != null) {

						String[] temp2 = priceLine.split("■");

						if (temp[0].equals(temp2[0])) {

							price_상품가격 += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));

						}

						while ((ageLine = user.readLine()) != null) {
							String[] temp3 = ageLine.split("■");

							if (temp[3].equals(temp3[0])) {

								String a = temp3[4].substring(0, 1);

								if (!a.equals("0")) {
									age = 2018 - Integer.parseInt("19" + temp3[4].substring(0, 2)) + 1;
								} else {
									age = 2018 - Integer.parseInt("20" + temp3[4].substring(0, 2)) + 1;
								}

								int count2 = age / 10;

								if (count2 == 1) {
									score[0] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));

								} else if (count2 == 2) {
									score[1] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
								} else if (count2 == 3) {
									score[2] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
								} else if (count2 == 4) {
									score[3] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
								} else if (count2 > 4) {
									score[4] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
								}

							}

						}

					}
					user = new BufferedReader(new FileReader(AGE));
					total = new BufferedReader(new FileReader(TOTAL));
				}
			}

			System.out.println("■단위당 1000000원");

			String[][] matrix = new String[5][10];

			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < (score[i] / 1000000); j++) {
					matrix[i][j] = "";

				}
			}

			dump(matrix);
			System.out.println("       10대             20대          30대            40대           50대이상\n");

			System.out.printf("총이용자 수 : %d\n", count);
			System.out.printf("총 매출 금액 : %,d원\n", price_상품가격);
			ui.bar();
			System.out.println("\t\t\t1. 연령별 매출 상세 보기");
			System.out.println("\t\t\t0. 뒤로가기");

			System.out.print("\t\t\t입력 : ");
			String select = scan.nextLine();

			switch (select) {
			case "1":
				printPage();
				break;
			case "0":
				Sales.monthSalesLookupPrint(year, month, date);
				break;
			}

			reader.close();
			user.close();

		} catch (Exception e) {
			System.out.println("AgeSearch.ageGraph() : " + e.toString());
		}
	}

	private static void printPage() {
		Scanner s = new Scanner(System.in);
		System.out.println("           1.10대         2.20대         3.30대         4.40대         5.50대");
		System.out.printf("\t\t\t입력 : ");
		String answer = s.next();
		System.out.println("=========================================================================================");
		ui.bar();
		System.out.printf("                                2018년 08월 10대 매출조회\n");
		ui.bar();
		System.out.println("\n■(단위) : 5만원                 \n");
		System.out.println("                                                                                  ");
		System.out.println("                                                                                  ");
		System.out.println("                                                                             ■       ");
		System.out.println("                        ■                                                   ■       ");
		System.out.println("                        ■                                                   ■     ");
		System.out.println("           ■           ■          ■                                       ■       ");
		System.out.println("           ■           ■          ■           ■                          ■      ");
		System.out.println("           ■           ■          ■           ■                          ■      ");
		System.out.println("                                                                                  ");
		System.out.println("         식음료       과자류       식품       간편식사       생필품       기호식품");
		ui.bar();
		System.out.println("[총 이용자 수]  32명 [총 매출액] 925,500\n");
		
		System.out.println("[대분류]         [이용자 수]                [매출액]");
		System.out.println("식음료              6                        153,000           ");
		System.out.println("과자류              8                        250,300           ");
		System.out.println("식품                6                        180,200         ");
		System.out.println("간편식사            4                        100,000            ");
		System.out.println("생필품              0                              0         ");
		System.out.println("기호식품            8                        242,000             ");
		
		function.pause();
		
		ui.bar();
		System.out.println("\t\t\t1. 제품군 매출 상세보기");
		System.out.println("\t\t\t0. 처음으로");
		
		System.out.printf("\t\t\t입력 : ");
		answer = s.next();
		
		System.out.println("      1.식음료      2.과자류      3.식품      4.간편식사      5.생필품    6.기호식품\n");
		System.out.printf("입력 : ");
		answer = s.next();
		ui.bar();
		
        System.out.println("\t\t\t\t\t┌─────┐");
        System.out.println("\t\t\t\t\t│  식음료  │");
        System.out.println("\t\t\t\t\t└─────┘");
        
        System.out.println("[이용자수]  5명,  [총 매출액] 153,000원");
        System.out.println("\n■(단위) : 5만원\n");
        System.out.println("                                           ");
		System.out.println("                                           ");
		System.out.println("                        ■                  ");
		System.out.println("                        ■                 ");
		System.out.println("                        ■                ");
		System.out.println("           ■           ■          ■    ");
		System.out.println("           ■           ■          ■    ");
		System.out.println("           ■           ■          ■   ");
		System.out.println("                                         ");
		System.out.println("          음료         주류       유제품  ");
		ui.bar();
		
		System.out.println("[중분류]         [이용자 수]                [매출액]");
		System.out.println(" 음료                 1                      32,500         ");
		System.out.println(" 주료                 3                      91,500         ");
		System.out.println(" 유제품               1                      29,000           ");
		
		function.pause();
        
	}
	
	/**
	 * 분기별 연령별 매출을 조회하는 메소드
	 * @param year 입력한 값에서 년도만 뺀 값
	 * @param quarter 입력한 분기 값
	 */
	public static void ageGraph(String year, String quarter) {// 분기별 매출
		ui.bar();
		System.out.printf("                                %s년%s 분기별 연령별 매출조회\n", year, quarter);
		ui.bar();

		switch (quarter) {
		case "1":
			try {
				BufferedReader reader = new BufferedReader(new FileReader(ORDER));
				BufferedReader user = new BufferedReader(new FileReader(AGE));
				BufferedReader total = new BufferedReader(new FileReader(TOTAL));

				int count = 0;
				int price_상품가격 = 0;
				int age = 0;

				int[] score = new int[5]; // 연령대별 배열

				String line = "";
				String priceLine = "";
				String ageLine = "";

				while ((line = reader.readLine()) != null) {
					String[] temp = line.split("■");

					if ((temp[4].substring(0, 4)).equals(year) && (temp[4].substring(4, 6).equals("01")
							|| temp[4].substring(4, 6).equals("02") || temp[4].substring(4, 6).equals("03"))) {
						count++;

						while ((priceLine = total.readLine()) != null) {

							String[] temp2 = priceLine.split("■");
							if (temp[0].equals(temp2[0])) {
								price_상품가격 += Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]);

							}

							while ((ageLine = user.readLine()) != null) {
								String[] temp3 = ageLine.split("■");

								if (temp[3].equals(temp3[0])) {

									String a = temp3[4].substring(0, 1);

									if (!a.equals("0")) {
										age = 2018 - Integer.parseInt("19" + temp3[4].substring(0, 2)) + 1;
									} else {
										age = 2018 - Integer.parseInt("20" + temp3[4].substring(0, 2)) + 1;
									}

									int count2 = age / 10;

									if (count2 == 1) {
										score[0] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));

									} else if (count2 == 2) {
										score[1] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									} else if (count2 == 3) {
										score[2] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									} else if (count2 == 4) {
										score[3] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									} else if (count2 > 4) {
										score[4] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									}

								}

							}
						}

						user = new BufferedReader(new FileReader(AGE));
						total = new BufferedReader(new FileReader(TOTAL));
					}
				}

				System.out.println("■단위당 1000000원");

				String[][] matrix = new String[5][10];

				for (int i = 0; i < matrix.length; i++) {
					for (int j = 0; j < (score[i] / 1000000); j++) {
						matrix[i][j] = "■";

					}
				}

				dump(matrix);
				System.out.printf("10대   20대     30대     40대    50대이상\n");

				System.out.printf("총이용자 수 : %d\n", count);
				System.out.printf("총 매출 금액 : %,d원\n", price_상품가격);
				ui.bar();
				System.out.println("1. 연령별 매출 상세 보기");
				System.out.println("0. 뒤로가기");

				System.out.print("입력 : ");
				String select = scan.nextLine();

				switch (select) {
				case "1":
					// 연령별 매출 상세보기 클래스 이름
					break;
				case "0":
					Sales.quarterSalesLookup();
					break;
				}

				reader.close();
				user.close();

				reader.close();

			} catch (Exception e) {
				System.out.println("AgeSearch.ageGraph() : " + e.toString());
			}

			break;
		case "2":
			try {
				BufferedReader reader = new BufferedReader(new FileReader(ORDER));
				BufferedReader user = new BufferedReader(new FileReader(AGE));
				BufferedReader total = new BufferedReader(new FileReader(TOTAL));

				int count = 0;
				int price_상품가격 = 0;
				int age = 0;

				int[] score = new int[5]; // 연령대별 배열

				String line = "";
				String priceLine = "";
				String ageLine = "";

				while ((line = reader.readLine()) != null) {
					String[] temp = line.split("■");

					if ((temp[4].substring(0, 4)).equals(year) && (temp[4].substring(4, 6).equals("04")
							|| temp[4].substring(4, 6).equals("05") || temp[4].substring(4, 6).equals("06"))) {
						count++;

						while ((priceLine = total.readLine()) != null) {

							String[] temp2 = priceLine.split("■");
							if (temp[0].equals(temp2[0])) {
								price_상품가격 += Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]);

							}

							while ((ageLine = user.readLine()) != null) {
								String[] temp3 = ageLine.split("■");

								if (temp[3].equals(temp3[0])) {

									String a = temp3[4].substring(0, 1);

									if (!a.equals("0")) {
										age = 2018 - Integer.parseInt("19" + temp3[4].substring(0, 2)) + 1;
									} else {
										age = 2018 - Integer.parseInt("20" + temp3[4].substring(0, 2)) + 1;
									}

									int count2 = age / 10;

									if (count2 == 1) {
										score[0] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));

									} else if (count2 == 2) {
										score[1] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									} else if (count2 == 3) {
										score[2] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									} else if (count2 == 4) {
										score[3] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									} else if (count2 > 4) {
										score[4] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									}

								}

							}
						}

						user = new BufferedReader(new FileReader(AGE));
						total = new BufferedReader(new FileReader(TOTAL));
					}
				}

				System.out.println("■단위당 1000000원");

				String[][] matrix = new String[5][10];

				for (int i = 0; i < matrix.length; i++) {
					for (int j = 0; j < (score[i] / 1000000); j++) {
						matrix[i][j] = "■";

					}
				}

				dump(matrix);
				System.out.printf("10대   20대     30대     40대    50대이상\n");

				System.out.printf("총이용자 수 : %d\n", count);
				System.out.printf("총 매출 금액 : %,d원\n", price_상품가격);
                ui.bar();
				System.out.println("1. 연령별 매출 상세 보기");
				System.out.println("0. 뒤로가기");

				System.out.print("입력 : ");
				String select = scan.nextLine();

				switch (select) {
				case "1":
					// 연령별 매출 상세보기 클래스 이름
					break;
				case "0":
					Sales.quarterSalesLookup();
					break;
				}

				reader.close();
				user.close();

				reader.close();

			} catch (Exception e) {
				System.out.println("AgeSearch.ageGraph() : " + e.toString());
			}

			break;
		case "3":
			try {
				BufferedReader reader = new BufferedReader(new FileReader(ORDER));
				BufferedReader user = new BufferedReader(new FileReader(AGE));
				BufferedReader total = new BufferedReader(new FileReader(TOTAL));

				int count = 0;
				int price_상품가격 = 0;
				int age = 0;

				int[] score = new int[5]; // 연령대별 배열

				String line = "";
				String priceLine = "";
				String ageLine = "";

				while ((line = reader.readLine()) != null) {
					String[] temp = line.split("■");

					if ((temp[4].substring(0, 4)).equals(year) && (temp[4].substring(4, 6).equals("07")
							|| temp[4].substring(4, 6).equals("08") || temp[4].substring(4, 6).equals("09"))) {
						count++;

						while ((priceLine = total.readLine()) != null) {

							String[] temp2 = priceLine.split("■");
							if (temp[0].equals(temp2[0])) {
								price_상품가격 += Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]);

							}

							while ((ageLine = user.readLine()) != null) {
								String[] temp3 = ageLine.split("■");

								if (temp[3].equals(temp3[0])) {

									String a = temp3[4].substring(0, 1);

									if (!a.equals("0")) {
										age = 2018 - Integer.parseInt("19" + temp3[4].substring(0, 2)) + 1;
									} else {
										age = 2018 - Integer.parseInt("20" + temp3[4].substring(0, 2)) + 1;
									}

									int count2 = age / 10;

									if (count2 == 1) {
										score[0] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));

									} else if (count2 == 2) {
										score[1] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									} else if (count2 == 3) {
										score[2] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									} else if (count2 == 4) {
										score[3] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									} else if (count2 > 4) {
										score[4] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									}

								}

							}
						}

						user = new BufferedReader(new FileReader(AGE));
						total = new BufferedReader(new FileReader(TOTAL));
					}
				}

				System.out.println("■단위당 1000000원");

				String[][] matrix = new String[5][10];

				for (int i = 0; i < matrix.length; i++) {
					for (int j = 0; j < (score[i] / 1000000); j++) {
						matrix[i][j] = "■";

					}
				}

				dump(matrix);
				System.out.printf("10대   20대     30대     40대    50대이상\n");

				System.out.printf("총이용자 수 : %d\n", count);
				System.out.printf("총 매출 금액 : %,d원\n", price_상품가격);
				ui.bar();
				System.out.println("1. 연령별 매출 상세 보기");
				System.out.println("0. 뒤로가기");

				System.out.print("입력 : ");
				String select = scan.nextLine();

				switch (select) {
				case "1":
					// 연령별 매출 상세보기 클래스 이름
					break;
				case "0":
					Sales.quarterSalesLookup();
					break;
				}

				reader.close();
				user.close();

				reader.close();

			} catch (Exception e) {
				System.out.println("AgeSearch.ageGraph() : " + e.toString());
			}

			break;
		case "4":
			try {
				BufferedReader reader = new BufferedReader(new FileReader(ORDER));
				BufferedReader user = new BufferedReader(new FileReader(AGE));
				BufferedReader total = new BufferedReader(new FileReader(TOTAL));

				int count = 0;
				int price_상품가격 = 0;
				int age = 0;

				int[] score = new int[5]; // 연령대별 배열

				String line = "";
				String priceLine = "";
				String ageLine = "";

				while ((line = reader.readLine()) != null) {
					String[] temp = line.split("■");

					if ((temp[4].substring(0, 4)).equals(year) && (temp[4].substring(4, 6).equals("10")
							|| temp[4].substring(4, 6).equals("11") || temp[4].substring(4, 6).equals("12"))) {
						count++;

						while ((priceLine = total.readLine()) != null) {

							String[] temp2 = priceLine.split("■");
							if (temp[0].equals(temp2[0])) {
								price_상품가격 += Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]);

							}

							while ((ageLine = user.readLine()) != null) {
								String[] temp3 = ageLine.split("■");

								if (temp[3].equals(temp3[0])) {

									String a = temp3[4].substring(0, 1);

									if (!a.equals("0")) {
										age = 2018 - Integer.parseInt("19" + temp3[4].substring(0, 2)) + 1;
									} else {
										age = 2018 - Integer.parseInt("20" + temp3[4].substring(0, 2)) + 1;
									}

									int count2 = age / 10;

									if (count2 == 1) {
										score[0] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));

									} else if (count2 == 2) {
										score[1] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									} else if (count2 == 3) {
										score[2] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									} else if (count2 == 4) {
										score[3] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									} else if (count2 > 4) {
										score[4] += (Integer.parseInt(temp[2]) * Integer.parseInt(temp2[2]));
									}

								}

							}
						}

						user = new BufferedReader(new FileReader(AGE));
						total = new BufferedReader(new FileReader(TOTAL));
					}
				}

				System.out.println("■단위당 1000000원");

				String[][] matrix = new String[5][10];

				for (int i = 0; i < matrix.length; i++) {
					for (int j = 0; j < (score[i] / 1000000); j++) {
						matrix[i][j] = "■";

					}
				}

				dump(matrix);
				System.out.printf("10대   20대     30대     40대    50대이상\n");

				System.out.printf("총이용자 수 : %d\n", count);
				System.out.printf("총 매출 금액 : %,d원\n", price_상품가격);
				ui.bar();
				System.out.println("1. 연령별 매출 상세 보기");
				System.out.println("0. 뒤로가기");

				System.out.print("입력 : ");
				String select = scan.nextLine();

				switch (select) {
				case "1":
					// 연령별 매출 상세보기 클래스 이름
					break;
				case "0":
					Sales.quarterSalesLookup();
					break;
				}

				reader.close();
				user.close();

				reader.close();

			} catch (Exception e) {
				System.out.println("AgeSearch.ageGraph() : " + e.toString());
			}

			break;
		}
	}
	/**
	 * 그래프를 만들기 위한 dump
	 * @param temp - 배열 수
	 */
	public static void dump(String[][] temp) {//그래프 만들기 dump메소드
		for (int i = temp[0].length - 1; i >= 0; i--){
			for (int j = 0; j < temp.length; j++) {

				if (temp[j][i] == null) {
					System.out.printf("\t\t", temp[j][i]);
					continue;
				}

				System.out.printf("\t■\t", temp[j][i]);
			}
			System.out.println();
		}
		System.out.println();
	}

}
