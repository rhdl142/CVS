쌍용 교육센터 Java&Python 기반 응용SW개발자 양성과정  

CVS Project (무인 편의점 프로그램)
===================================

##### 기간 : 2018.08.08 ~ 2018.08.17
##### 환경 : 콘솔 기반
##### 주제 : 무인 편의점 시스템
##### 개발 : Eclipse Photon
##### 사용 언어 : JavaSE(JDK 1.8)
##### 사용 기술 : 파일 입출력 기반 데이터 처리
<hr />

프로젝트 개요
---------------------------------
기존 편의점에 무인 운영, 이벤트 상품, 가시화 등 추가한 프로그램.

### 문제점
* 편의점 운영

        * 점주의 개인 역량에 따른 매출 차이
        
        * 최저 시급 상승에 따른 인건비 상승
        
* 서비스

        * 추천 기능
        
                * 20~30대 기준 월별 높은 매출 상품 추천 부족
                
        * 위치 파악
        
                * 상품 구입시, 위치 파악 어려움
                
        * 결제 기능
        
                * 이용시 결제 도구를 가지고 있어야함
                
                * 사람이 많을 경우, 결제시간이 신속하지 않음
                
        * 정산 기능
        
                * 정산시, 오차가 생김
                
                * 인원이 바뀔시, 정산을 해야함
                
        * 재고 관리 기능
        
                * 상품 구매시, 유통기한 확인 인지 부족
                
                * 상품 구매시, 수작업이 많음(유통기한, 제고 확인)
                
### 구현 목표

* 관리자

        * 재고 수량 파악 기능
        
        * 낮은 재고 수량 메시지 알림
        
        * 매출 통계 카테고리 별 확인 기능
        
        * 상품 추가하기 및 폐기 처분 기능
        
        * 제품 주문 기능
        
        * 고객 정보 및 구매 내역 확인
        
        * 통계 기반 상위 제품 진열 시각화
        
        * 제품 정보 확인 기능
        
* 구매자

        * 상품 구매 및 장바구니 담기
        
        * 가시화된 상품의 위치 확인 가능
        
        * 등록된 카드에 결제 가능여부 확인
        
        * 구매 내역 확인
        
        * 장바구니에 담긴 목록 상품 정보 확인
        
        * 회원 정보 확인 및 수정 가능
        
* 통합

        * 회원 번호 입력 및 비밀번호 입력
        
        * 회원 번호 및 회원 비밀번호 찾기 구현
        
        * 회원가입 
        
<hr />

### 구현 내용

![5](https://user-images.githubusercontent.com/31428011/44733129-de7ffb80-ab21-11e8-98ab-ff9ab90c3399.JPG)
![5](https://user-images.githubusercontent.com/31428011/44733170-f5265280-ab21-11e8-9f14-36cd66e7212b.JPG)
![5](https://user-images.githubusercontent.com/31428011/44733171-f6577f80-ab21-11e8-8d3e-d0c557e56029.JPG)
![5](https://user-images.githubusercontent.com/31428011/44733175-f788ac80-ab21-11e8-94e5-5edd8bd66756.JPG)
![5](https://user-images.githubusercontent.com/31428011/44733178-f8b9d980-ab21-11e8-92de-e2af0a83b5db.JPG)


### 클래스 다이어그램

![image](https://user-images.githubusercontent.com/31428011/44733450-96150d80-ab22-11e8-9738-fc38597e4510.png)







                
