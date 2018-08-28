package cvs.function;

import cvs.mainpage.Join;

/**
 * 
 * @author 임광민 전체 유효성 검사
 */
public class Effectiveness {
	private static Member member;
	private static Join join;
	
	static {
		join = new Join();
		member = new Member();
	}

	/**
	 * 
	 * @param number - 일련번호
	 * @return 유효성 검사후 true,false 반환 사용자가 입력한 일련번호의 길이 검사
	 */
	public boolean numberInspection(String number) {
		boolean check = true;

		// 길이
		if (number.length() != 10) {
			check = false;
		}

		return check;
	}

	/**
	 * 
	 * @param number - 카테고리 중분류 및 소분류
	 * @return 유효성 검사후 true false 반환 사용자가 입력한 카테고리 중분류 및 소분류 번호 길이 검사
	 */
	public boolean categoryInspection(String number) {
		boolean check = true;

		// 길이
		if (number.length() == 5 || number.length() == 7) {
		} else {
			check = false;
		}

		return check;
	}

	/**
	 * 비밀번호 유효성 검사 메서드
	 */
	public static void checkPassWord(String memberPassword) {
		if (memberPassword.length() == 6) {
			member.setPassword(memberPassword);
		} else {
			join.errorKind += "패스워드";
			join.checkCount++;
		}
	}// checkPassword

	/**
	 * 카드번호 유효성 검사 메서드
	 */
	public static void checkCardNum(String memberCardNum) {
		if (memberCardNum.length() == 16) {
			member.setCardNum(memberCardNum);
		} else {
			join.errorKind += " 카드번호";
			join.checkCount++;
		}
	}// checkCardNum

	/**
	 * 전화번호 유효성 검사 메서드
	 */
	public static void checkPhoneNum(String memberPhoneNum) {
		if (memberPhoneNum.length() == 11) {
			member.setPhonNum(memberPhoneNum);
		} else {
			join.errorKind += " 전화번호";
			join.checkCount++;
		}
	}// checkPhoneNum

	/**
	 * 생년월일 유효성 검사 메서드
	 */
	public static void checkBirth(String memberBirth) {
		if (memberBirth.length() == 6) {
			member.setBirth(memberBirth);
		} else {
			join.errorKind += " 생년월일";
			join.checkCount++;
		}

	}// checkBirth

	/**
	 * 성별 유효성 검사 메서드
	 */
	public static void checkGender(String memberGender) {
		if (memberGender.equals("0")) {
			member.setGender("남자");
		} else if (memberGender.equals("1")) {
			member.setGender("여자");
		} else {
			join.errorKind += " 성별";
			join.checkCount++;
		}
	}// checkGender

	/**
	 * 비밀번호 유효성 검사 메서드
	 */
	public static void checkName(String memberName) {

		if (memberName.length() > 1 && memberName.length() < 6) {
			member.setName(memberName);
		} else {
			join.errorKind = "이름 ";
			join.checkCount++;
		}
	}// checkName
}
