package cvs.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.io.*;

public class OrderDummy {
	
	
	public static void main(String[] args) {
		String path = "dat\\totalData.dat";
		Random rnd = new Random();

		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = "";
			
			ArrayList<String> numList = new ArrayList<>(); // 제품번호 리스트
			HashMap<String, String> itemMap = new HashMap<>(); //  제품번호, 제품명
			HashMap<String, Integer> countMap = new HashMap<>(); //수량
			
			while((line = reader.readLine())!= null) {
				String[] temp = line.split("■");
				
				//데이터 넣기
				numList.add(temp[0]); //제품번호
				itemMap.put(temp[0], temp[1]); //제품번호, 제품이름
				countMap.put(temp[0], Integer.parseInt(temp[3])); //제품번호, 제품수량
				
			}
			reader.close();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("dat\\order.dat"));
			
			
			//1 ~ 3월
			for(int i = 0; i < 1000; i++) { //1000개의 주문 만들기
				String result = "";
				
				
				String num = numList.get(rnd.nextInt(numList.size() - 1));
				String item = itemMap.get(num);
				String surung = (rnd.nextInt(countMap.get(num)) + 1) + "";
				
				int member = rnd.nextInt(1000) + 1; //회원번호
				
				int year = rnd.nextInt(2) + 2017;
				int month= rnd.nextInt(3) + 1; //1 ~ 3월
				
				int day = 0;
				if (month == 2) {  //2월
					day = rnd.nextInt(30) + 1; //1 ~ 30일
				} else {
					day = rnd.nextInt(31) + 1; //1 ~ 31일
				}
				
				result = String.format("%s■%s■%s■%04d■%d%02d%02d", num, item, surung, member, year, month, day);
				writer.write(result);
				writer.newLine();
			}
			
			//4 ~ 6월
			for(int i = 0; i < 1000; i++) { //1000개의 주문 만들기
				String result = "";
				
				String num = numList.get(rnd.nextInt(numList.size() - 1));
				String item = itemMap.get(num);
				String surung = (rnd.nextInt(countMap.get(num)) + 1) + "";
				
				int member = rnd.nextInt(1000) + 1; //회원번호
				
				int year = rnd.nextInt(2) + 2017;
				int month= rnd.nextInt(3) + 4; //3 ~ 5월
				
				int day = 0;
				if (month == 5) {  //4월
					day = rnd.nextInt(31) + 1; //1 ~ 30일
				} else {
					day = rnd.nextInt(30) + 1; //1 ~ 31일
				}
				
				result = String.format("%s■%s■%s■%04d■%d%02d%02d", num, item, surung, member, year, month, day);
				writer.write(result);
				writer.newLine();
			}
			
			//7 ~ 9월
			for(int i = 0; i < 1000; i++) { //1000개의 주문 만들기
				String result = "";
				
				String num = numList.get(rnd.nextInt(numList.size() - 1));
				String item = itemMap.get(num);
				String surung = (rnd.nextInt(countMap.get(num)) + 1) + "";
				
				int member = rnd.nextInt(1000) + 1; //회원번호
				
				int year = rnd.nextInt(2) + 2017;
				int month= rnd.nextInt(3) + 7; //6 ~ 8월
				
				int day = 0;
				
				if(year == 2018) {
					
				}
				if (month == 9) {  //9월
					day = rnd.nextInt(30) + 1; //1 ~ 30일
				} else {
					day = rnd.nextInt(31) + 1; //1 ~ 31일
				}
				
				result = String.format("%s■%s■%s■%04d■%d%02d%02d", num, item, surung, member, year, month, day);
				writer.write(result);
				writer.newLine();
				
			}
			
			//10월 ~ 12월
			for(int i = 0; i < 1000; i++) { //1000개의 주문 만들기
				String result = "";
				
				String num = numList.get(rnd.nextInt(numList.size() - 1));
				String item = itemMap.get(num);
				String surung = (rnd.nextInt(countMap.get(num)) + 1) + "";
				
				int member = rnd.nextInt(1000) + 1; //회원번호
				
				int year = rnd.nextInt(2) + 2017;
				int month= rnd.nextInt(3) + 10; //10 ~ 12월
				
				int day = 0;
				if (month == 10) {  //10월
					day = rnd.nextInt(30) + 1; //1 ~ 30일
				} else {
					day = rnd.nextInt(31) + 1; //1 ~ 31일
				}
				
				result = String.format("%s■%s■%s■%04d■%d%02d%02d", num, item, surung, member, year, month, day);
				writer.write(result);
				writer.newLine();
			}
			
			writer.close();
			System.out.println("끝");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
