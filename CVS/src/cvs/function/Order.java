package cvs.function;

/**
 * 주문 정보를 입력
 * @author 장시영
 *
 */
public class Order {
   
   String itemNum;      //제품번호
   String itemName;   //제품명
   String buyNum;         //수량
   String memberNum;   //회원번호
   String buyDate;      //구입날짜
   
   /**
    * Order 생성자 오버라이딩 (기본)
    */
   public Order() {
         super();
      }
   
   /**
    * Order 생성자 오버라이딩
    * @param itemNum - 제품번호
    * @param itemName - 제품명
    * @param buyNum - 구매 수량
    * @param memberNum - 회원번호
    * @param buyDate - 구매 날짜
    */
   public Order(String itemNum, String itemName, String buyNum, String memberNum, String buyDate) {
      super();
      this.itemNum = itemNum;
      this.itemName = itemName;
      this.buyNum = buyNum;
      this.memberNum = memberNum;
      this.buyDate = buyDate;
   }
   
   
   /**
    * toString 오버라이팅
    */
   @Override
   public String toString() {
      return String.format("제품번호 : %s, 제품명 : %s, 수량 : %d, 회원번호 : %s, 구매날짜 : %s"
                      , this.itemNum
                      , this.itemName
                      , this.buyNum
                      , this.memberNum
                      , this.buyDate);
   }
   
   /**
    * itemNum getter
    * @return itemNum - 제품번호
    */
   public String getItemNum() {
      return itemNum;
   }
   
   /**
    * itemNum setter
    * @param itemNum - 쓰여진 제품번호
    */
   public void setItemNum(String itemNum) {
      this.itemNum = itemNum;
   }
   
   /**
    * itemName getter
    * @return itemName - 제품명
    */
   public String getItemName() {
      return itemName;
   }
   
   /**
    * itemName setter
    * @param itemName - 쓰여진 제품명
    */
   public void setItemName(String itemName) {
      this.itemName = itemName;
   }
   
   /**
    * buyNum getter
    * @return buyNum - 구매수량
    */
   public String getBuyNum() {
      return buyNum;
   }
   
   /**
    * buyNum setter
    * @param buyNum - 쓰여진 구매 수량
    */
   public void setBuyNum(String buyNum) {
      this.buyNum = buyNum;
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
    * buyDate getter
    * @return buyDate - 구매날짜
    */
   public String getBuyDate() {
      return buyDate;
   }
   
   /**
    * buyDate setter
    * @param buyDate - 쓰여진 구매날짜
    */
   public void setBuyDate(String buyDate) {
      this.buyDate = buyDate;
   }
}