package cvs.function;


/**
 * 회원 정보를 입력
 * @author 장시영
 *
 */
public class Member {

   String memberNum;   //회원 번호
   String password;   //비밀번호
   String name;      //회원명
   String gender;      //성별
   String birth;      //생년월일
   String cardNum;      //카드번호
   String phonNum;      //핸드폰 번호
   int age;         //나이
   
   /**
    * Member 생성자 오버라이딩 (기본)
    */
   public Member() {
      super();
   }
   
   /**
    * Member 생성자 오버라이딩
    * @param memberNum - 회원번호
    * @param password - 비밀번호
    * @param name - 회원명
    * @param gender - 성별
    * @param birth - 생년월일
    * @param cardNum - 카드번호
    * @param phonNum - 휴대폰번호
    */
   public Member(String memberNum, String password, String name, String gender, String birth, String cardNum, String phonNum) {
      super();
      this.memberNum = memberNum;
      this.password = password;
      this.name = name;
      this.gender = gender;
      this.birth = birth;
      this.cardNum = cardNum;
      this.phonNum = phonNum;
      
      String a = birth.substring(0, 1);
      
      if(!a.equals("0")) { // ~ 1999년생
         this.age = 2018 - Integer.parseInt("19" + birth.substring(0, 2)) + 1;
      } else { // 2000년생 ~
         this.age = 2018 - Integer.parseInt("20" + birth.substring(0, 2)) + 1;
      }
   }

   /**
    * toString 오버라이딩
    */
   @Override
   public String toString() {
      return String.format("회원번호 : %s, 비밀번호 : %s, 이름 : %s, 성별 : %s, 생년월일 : %s, 나이 : %d, 카드번호 : %s, 핸드폰 번호 : %s"
                      , this.memberNum
                      , this.password
                      , this.name
                      , this.gender
                      , this.birth
                      , this.age
                      , this.cardNum
                      , this.phonNum);
   }
   
   /**
    * memberNum getter
    * @return memberNum - 회원번호
    */
   public String getMemberNum() {
      return memberNum;
   }
   /**
    * memberNum setter
    * @param memberNum - 쓰여진 회원번호
    */
   public void setMemberNum(String memberNum) {
      this.memberNum = memberNum;
   }
   
   /**
    * password getter
    * @return password - 비밀번호
    */
   public String getPassword() {
      return password;
   }
   
   /**
    * password setter
    * @param password - 쓰여진 비밀번호
    */
   public void setPassword(String password) {
      this.password = password;
   }
   
   /**
    * name getter
    * @return name - 회원명
    */
   public String getName() {
      return name;
   }
   
   /**
    * name setter
    * @param name - 쓰여진 회원명
    */
   public void setName(String name) {
      this.name = name;
   }
   
   /**
    * gender getter
    * @return gender - 성별
    */
   public String getGender() {
      return gender;
   }
   
   /**
    * gender setter
    * @param gender - 쓰여진 성별
    */
   public void setGender(String gender) {
      this.gender = gender;
   }
   
   /**
    * birth getter
    * @return birth - 생년월일
    */
   public String getBirth() {
      return birth;
   }
   
   /**
    * birth setter
    * @param birth - 쓰여진 생년월일
    */
   public void setBirth(String birth) {
      this.birth = birth;
   }
   
   /**
    * cardNum getter
    * @return cardNum - 카드번호
    */
   public String getCardNum() {
      return cardNum;
   }
   
   /**
    * cardNum setter
    * @param cardNum - 쓰여진 카드번호
    */
   public void setCardNum(String cardNum) {
      this.cardNum = cardNum;
   }
   
   /**
    * age getter
    * @return age - 나이
    */
   public int getAge() {
      return age;
   }
   
   /**
    * age setter
    * @param age - 쓰여진 나이
    */
   public void setAge(int age) {
      this.age = age;
      
   }
   
   /**
    * phoneNum getter
    * @return phoneNum - 휴대폰 번호
    */
   public String getPhonNum() {
      return phonNum;
   }

   /**
    * phone setter
    * @param phonNum - 쓰여진 휴대폰 번호
    */
   public void setPhonNum(String phonNum) {
      this.phonNum = phonNum;
   }
}