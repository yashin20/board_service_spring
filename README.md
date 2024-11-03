# Board Service [Spring Boot Project]

![메인화면](https://github.com/user-attachments/assets/1a502db6-2167-47cd-94ee-f8cfabb044fa)

---
## 목차
 - [1. 프로젝트 소개](#1-프로젝트-소개)
   - [1-1. 프로젝트 소개](#1-1-프로젝트-소개)
   - [1-2. 개발 기간](#1-2-개발-기간)
   - [1-3. 프로젝트 기능](#1-3-프로젝트-기능)
   - [1-4. 개발 환경](#1-4-개발-환경)
   - [1-5. 실행 화면](#1-5-실행-화면)
  
 - [2. 프로젝트 구조](#2-프로젝트-구조)
   - [2-1. 패키지 구조](#2-1-패키지-구조)
   - [2-2. DB 설계](#2-2-DB-설계)
   - [2-3. API 설계](#2-3-API-설계)
  
 - [개발 내용](#개발-내용)

 - [마치며](#마치며)
   - [1. 프로젝트 보완사항](#1-프로젝트-보완사항)
   - [2. 프로젝트 과정에서 발생한 문제](#2-프로젝트-과정에서-발생한-문제)
   - [3. 후기](#3-후기)
---

## 1. 프로젝트 소개

### 1-1. 프로젝트 소개



### 1-2. 개발 기간

2024.10.11 - 2024.10.31 (21일간)


### 1-3. 프로젝트 기능

게시판 서비스의 주요 기능은 다음과 같습니다.

- 회원 (Member)
1. Security 회원가입 및 로그인 기능
2. OAuth2 - 소셜 로그인 기능 (Google, KaKao, Naver)
3. 유효성 및 중복 검사
4. 회원 정보 조회
5. 회원 정보 수정
6. 회원 탈퇴

- 게시글 (Post)
1. 게시글 작성
2. 게시글 조회
3. 게시글 검색 (제목 기반 - title)
4. 게시글 정렬 (views, likes, createdAt)
5. 게시글 수정
6. 게시글 삭제
7. 게시글 좋아요

- 댓글 (Comment)
1. 댓글 작성
2. 댓글 조회
3. 댓글 수정
4. 댓글 삭제
5. 댓글 좋아요


### 1-4. 개발 환경

#### Back-end
 - Java 21
 - SpringBoot 3.3.4
 - JPA(Spring Data JPA)
 - Spring Security
 - OAuth2

##### Build Tool
 - Gradle 8.10.2

##### DataBase
 - MySQL 8.0.36

#### Front-end
 - html/css
 - JavaScript
 - Thymeleaf
 - Bootstrap 5.3.3


### 1-5. 실행 화면

  <details>
    <summary>회원 (Member)</summary>

   **1. 로그인 화면 - ("/members/login")**  
   ![image](https://github.com/user-attachments/assets/f8f20f6d-d922-42a6-8f64-7d9cc976f1e9)  
  
   [로그인 여부에 따른 헤더 버튼 구성]
   로그인 X  
   ![image](https://github.com/user-attachments/assets/b56e72a4-0d1c-48a2-9951-d630386ad558)  
  
   로그인 O
   ![image](https://github.com/user-attachments/assets/3c0fb534-b1c3-4d56-9c4f-466f0120c9bb)  


   [소셜 로그인 - Google]  
   ![image](https://github.com/user-attachments/assets/842da38c-2ecf-4413-a65b-1d42a196a9a5)  
   ![image](https://github.com/user-attachments/assets/8f8ce3ea-602e-46d7-99bf-000d0de689ab)  
   Google 로그인 화면으로 이동한다.  

   [소셜 로그인 - Naver]  
   ![image](https://github.com/user-attachments/assets/670edb23-4afe-45e1-bd15-ffa3cdef0870)  
   ![image](https://github.com/user-attachments/assets/37a051fc-53e4-4755-b472-3580ffd68daf)  
   Naver 로그인 화면으로 이동한다.  

   [소셜 로그인 - Kakao]  
   ![image](https://github.com/user-attachments/assets/c7f5bb47-b502-4e4f-ac7e-c3c8b2b442ab)  
   ![image](https://github.com/user-attachments/assets/91307a71-1c8f-4e57-a116-6b19b4629143)  
   Kakao 로그인 화면으로 이동한다.  


   **2. 회원가입 화면 - ("/members/join")**  
   ![image](https://github.com/user-attachments/assets/ffdddc0d-6cd2-4904-ae8b-d723986b363f)  

   ※ 회원가입 입력값에 대한 유효성 검사  
   ![image](https://github.com/user-attachments/assets/881711b1-ba8b-4fda-be5e-08babc761dc9)  

   Example) 새로운 회원가입  
   ※ 'username' 중복 검사 - 같은 'username' 을 가지고 있는 기존 회원 존재  
   ![image](https://github.com/user-attachments/assets/e3613693-e6bd-467e-a412-77466d0ffee7)  

   ※ 정상적으로 회원가입 완료!  
   ![image](https://github.com/user-attachments/assets/747c4549-6075-428a-8323-9521a085f712)  
   ![image](https://github.com/user-attachments/assets/dd7c7c19-2c38-46ac-b36a-bf78e467b195)  
   DB 에 저장된 모습  

   

   **3. 회원정보 화면 - ("/members/info)**  
   ![image](https://github.com/user-attachments/assets/82930cf6-7614-4a94-a1bb-fffd31db2443)  
   ![image](https://github.com/user-attachments/assets/22780dff-1d65-4439-bae2-3c93226aa30b)  
   '정보 수정' 버튼을 눌러 '회원정보 수정 화면'으로 이동한다.  



   **4-1. 회원정보 수정 화면 - ("/members/info/update")**  
   ![image](https://github.com/user-attachments/assets/0839d853-8c80-422d-b8a5-0b2b39d511f9)  

   이메일 형식 유효성 검사  
   ![image](https://github.com/user-attachments/assets/2cbc2a55-878f-4995-bb05-791c2687d408)    
  
   ![image](https://github.com/user-attachments/assets/6bdaffcb-d2d9-4525-a24a-551ccf820b10)  
   nickname : 'newNickname' -> 'otherNickname'  
   email : 'newMember@example.com' -> 'otherMember@example.com'  
   이와 같이 수정 후 'Submit' 버튼을 눌러 정보 수정을 완료한다.  
   
   ![image](https://github.com/user-attachments/assets/0ec162cc-810a-4cfa-a541-b5e88956a969)  
   수정사항이 적용된 것을 확인할 수 있다.  

   

   **4-2. 비밀번호 수정 화면 - ("/members/info/updatePassword")**  
   ![image](https://github.com/user-attachments/assets/b90e57bd-2f44-4070-9258-adb01d771242)  

   현재 비밀번호는 '1q2w3e4r~!' 이다.  
   비밀번호를 변경하기 위해서는 
   '현재 비밀번호'  
   '변경하고자 하는 비밇번호'  
   '변경하고자 하는 비밀번호 확인'  
   이렇게 3개의 입력 필드를 갖는다.  

   1) '현재 비밀번호'를 잘못입력하는 경우  
   ![image](https://github.com/user-attachments/assets/2937aabd-9e80-4caa-9fca-d836e7c731f2)  

   2) '변경하고자 하는 비밀번호' 와 '변경하고자 하는 비밀번호 확인'이 서로 다른 경우  
   ![image](https://github.com/user-attachments/assets/82e00a95-d149-40b6-bf30-c820a1b48daa)  


   ![image](https://github.com/user-attachments/assets/2acba68e-c28b-440c-bc1a-ba9deb0bd249)  
   password : '1q2w3e4r~!' -> '11dnjfekf~!'로 수정하였다.  



   **5. 회원 탈퇴**  

    
  </details>



  
  <details>
    <summary>게시글 (Post)</summary>
    
   **1. 게시글 전체 목록 - ("/")**
   ![image](https://github.com/user-attachments/assets/eb2ffe3c-c87e-4f3b-a1d3-5fe837a87c64)  




   **1-1. 게시글 전체 목록 정렬 - (Views, Likes, Created At)**

   '조회수' 기준으로 내림차순 정렬  
   ![image](https://github.com/user-attachments/assets/ba12d056-b2ca-449e-b84a-5b072e1897d6)  

   'Views' 항목을 클릭하여 '조회수' 기준 내림차순 정렬을 한다.  
   ※ 로그인을 하지 않아도 게시글 정렬이 가능하다.  


   '좋아요 수' 기준으로 내림차순 정렬  
   ![image](https://github.com/user-attachments/assets/708404d2-d611-4a48-b62a-701ee634c793)  

   'Likes' 항목을 클릭하여 '좋아요 수' 기준 내림차순 정렬을 한다.  
   ※ 로그인을 하지 않아도 게시글 정렬이 가능하다.  

   +) 'createdAt' 항목을 클릭하여 '작성일자' 기준 내림차순 정렬을 한다.  



   **2. 게시글 등록 화면 - ("/posts/new")**  
   ![image](https://github.com/user-attachments/assets/bad9dc45-6c45-4c0e-ae0e-0f4214adf0f7)  
   
   로그인한 사용자만 게시글 작성이 가능하며, 작성 후 '작성하기' 버튼을 누르면 메인 페이지로 리다이렉트 된다.  

   ![image](https://github.com/user-attachments/assets/119453d7-eaab-4ff0-9b49-ee82904b74f8)
   ※로그인 하지 않은 상태에서 '글작성' 버튼을 누르게되면, 로그인 화면("/members/login")으로 이동한다.  

     

   **3. 게시글 상세 정보 - ("/posts/{postId}")**  

   1) 로그인 X  
   ![image](https://github.com/user-attachments/assets/3dbf3788-e454-446e-aac2-a9d60ad6cc67)  

   2) 게시글 작성자로 로그인 한 경우  
   ![image](https://github.com/user-attachments/assets/ba85c4da-15fa-451a-9ed7-528622407c56)  
   게시글 작성자인 "member1" 으로 로그인 한 경우,  
   '게시글 수정', '게시글 삭제' 버튼이 보이는 것을 확인할 수 있다.  
  
   4) 게시글 작성자가 아닌 회원으로 로그인 한 경우  
   ![image](https://github.com/user-attachments/assets/0f9c36d3-68a4-427e-8665-17e44a1b6392)  
   게시글 작성자 "member1" 과 다른 회원인 "member2"로 로그인 한 경우,  
   '게시글 수정', '게시글 삭제' 버튼을 확인할 수 없다.  




   **4. 게시글 수정 화면 - ("/posts/update/{postId}")**
   ![image](https://github.com/user-attachments/assets/c1ef377d-fba7-4e13-a7de-3c87c7337951)  

   기존 게시글 내용이 'textarea'에 입력되어 있는 것을 볼 수 있다.  
   게시글 수정 후, '저장하기' 버튼을 눌러 수정을 마무리한다.  
   '저장하' 버튼을 누르면 게시글 목록으로 이동한다.  

   [수정된 게시글 화면]  
   ![image](https://github.com/user-attachments/assets/456dcff8-aa14-4973-adc5-0321a64ce4cf)  

   

   **5. 게시글 삭제 화면**  
   ![image](https://github.com/user-attachments/assets/cf2c8c41-5bc3-481a-86c5-c52f93fdaeca)  
   '게시글 삭제' 버튼을 눌러 게시글 삭제를 진행한다. 

   [기존 게시글 목록]  
   ![image](https://github.com/user-attachments/assets/3674cc31-e14b-47b4-9a90-4eae363b5773)  

   [삭제 후 게시글 목록]  
   ![image](https://github.com/user-attachments/assets/aa859a43-ff95-4d25-9339-ae432b0db8fe)  
   '154'번 게시글	"REALLY REALLY - WINNER 中"이 삭제된 것을 확인할 수 있다.  
  

   **6. 게시글 검색 화면**  
   "Hello"란 키워드를 포함한 게시글을 검색한다.  
   ![image](https://github.com/user-attachments/assets/bb9828b1-03c9-4c44-bb04-2ee8a23be3f7)  
   제목에 Hello가 포함된 게시글을 검색 결과로 산출한 것을 확인할 수 있다.  


  **6-1. 게시글 검색 후 페이징 화면**

  ['Hello' 키워드로 검색한 화면]  
  ![image](https://github.com/user-attachments/assets/bb9828b1-03c9-4c44-bb04-2ee8a23be3f7)  
  
  ['Hello' 키워드로 검색 내용 中 사용자 기준 3페이지]  
  ![image](https://github.com/user-attachments/assets/904c9477-5099-4bee-a9d3-0dc8b9b67b8c)  


  **6-2. 게시글 검색 후 페이징 + 정렬**

  ['Hello' 키워드로 검색 내용 && 조회수 기준 내림차순 정렬]  
  ![image](https://github.com/user-attachments/assets/33e0dcc2-40dc-4c52-952a-8137d446786a)  

  ['Hello' 키워드로 검색 내용 && 조회수 기준 내림차순 정렬 中 사용자 기준 1페이지]  
  ![image](https://github.com/user-attachments/assets/33e0dcc2-40dc-4c52-952a-8137d446786a)  


  **7. 게시글 돟아요 기능**  
  ![image](https://github.com/user-attachments/assets/e5fa3a6a-d261-4965-a5f5-322238e1a3d2)  
  현재 로그인된 회원이 좋아요를 누른 게시글은 '좋아요'버튼이 색칠된 것을 확인할 수 있다.   

  
    
  </details>

  <details>
    <summary>댓글 (Comment)</summary>


  **1. 댓글 작성 화면**  
  ![image](https://github.com/user-attachments/assets/1fdb6be1-4554-4ffb-92e7-ac847bedb1b4)  
  게시글 하단에 해당 게시글 소속의 댓글을 작성할 수 있다.  
  ![image](https://github.com/user-attachments/assets/ad619178-c9bf-4cd9-a02b-44fe85a8dcd1)  


  **2. 댓글 수정**  
  ![image](https://github.com/user-attachments/assets/a667385a-5cc2-4265-9587-93b7e4461b3f)  
  현재 로그인 회원 : "member3" (nickname : 'nic_member3')  
  
  ![image](https://github.com/user-attachments/assets/7f14c63d-1735-436f-b53a-e6f854810fda)  
  현재 로그인 회원이 작성한 댓글/대댓글만 '수정', '삭제' 버튼이 확인된다.  

  ![image](https://github.com/user-attachments/assets/9ce9e132-057d-48b3-aeb8-19cc7c23598b)  
  '수정' 버튼을 눌러 수정 모드로 변환한다.  

  ![image](https://github.com/user-attachments/assets/a3453711-b601-42d0-8338-de43efa2342b)  
  댓글내용을 수정한다. '게시' 버튼을 눌러 수정을 완료한다.  

  [수정된 댓글]  
  ![image](https://github.com/user-attachments/assets/e4fe1357-9b2a-4dbe-997e-9ef82570309e)  
  댓글이 수정된 것을 확인할 수 있다.  
  수정된 댓글은 '작성일자' 뒤에 "(수정됨)" 문구가 붙은 것을 확인할 수 있다.  


  **3. 댓글 좋아요**  
  ![image](https://github.com/user-attachments/assets/295a1511-c7ec-40fa-8430-b58b45c60822)  
  현재 로그인된 회원이 좋아요를 누른 댓글은 '좋아요'버튼이 색칠된 것을 확인할 수 있다.   

  ![image](https://github.com/user-attachments/assets/a991a653-ff7e-4c2a-acf6-467a69bb055c)  
  이미 좋아요가 눌러진 댓글의 '좋아요'버튼을 다시 누른다면, 좋아요가 취소되고, 버튼이 원상태로 복귀된다.  



  **4. 댓글 삭제**  

  ![image](https://github.com/user-attachments/assets/dc27f438-ebfe-4183-a3f6-fe152ddffb4e)  
  '삭제' 버튼을 눌러 댓글/대댓글 삭제를 진행한다.    

  ![image](https://github.com/user-attachments/assets/10d16db1-dea0-4af7-9dcc-5a951b1763ca)  
  해당 댓글이 삭제된 것을 확인할 수 있다.  

    
  </details>




## 2. 프로젝트 구조

### 2-1. 패키지 구조

<details>

<summary>패키지 구조 보기</summary>

```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂project
 ┃ ┃ ┃ ┗ 📂board_service
 ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomUserDetailsService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜WebSecurityConfig.java
 ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentApiController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentLikesController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜HomeController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜PostController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜PostLikesController.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜TestController.java
 ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentDto.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberDto.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜PostDto.java
 ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┣ 📜BaseEntity.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Comment.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentLikes.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Member.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberRole.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Post.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜PostLikes.java
 ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┣ 📜DataAlreadyExistsException.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜DataNotFoundException.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜GlobalExceptionHandler.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜PasswordCheckFailedException.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UnauthorizedAccessException.java
 ┃ ┃ ┃ ┃ ┣ 📂oauth
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomOAuth2UserService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜OAuthAttributes.java
 ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentLikesRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜PostLikesRepository.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜PostRepository.java
 ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentLikesService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜PostLikesService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜PostService.java
 ┃ ┃ ┃ ┃ ┣ 📜BoardServiceApplication.java
 ┃ ┃ ┃ ┃ ┗ 📜InitData.java
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┃ ┣ 📂css
 ┃ ┃ ┃ ┃ ┣ 📜bootstrap.min.css
 ┃ ┃ ┃ ┃ ┗ 📜styles.css
 ┃ ┃ ┃ ┣ 📂img
 ┃ ┃ ┃ ┃ ┣ 📜google-logo.png
 ┃ ┃ ┃ ┃ ┣ 📜kakao-logo.png
 ┃ ┃ ┃ ┃ ┗ 📜naver-logo.png
 ┃ ┃ ┃ ┗ 📂js
 ┃ ┃ ┃ ┃ ┗ 📜scripts.js
 ┃ ┃ ┣ 📂templates
 ┃ ┃ ┃ ┣ 📂fragments
 ┃ ┃ ┃ ┃ ┣ 📜footer.html
 ┃ ┃ ┃ ┃ ┗ 📜header.html
 ┃ ┃ ┃ ┣ 📂members
 ┃ ┃ ┃ ┃ ┣ 📜info.html
 ┃ ┃ ┃ ┃ ┣ 📜infoUpdate.html
 ┃ ┃ ┃ ┃ ┣ 📜join.html
 ┃ ┃ ┃ ┃ ┣ 📜login.html
 ┃ ┃ ┃ ┃ ┗ 📜passwordUpdate.html
 ┃ ┃ ┃ ┣ 📂posts
 ┃ ┃ ┃ ┃ ┣ 📜create.html
 ┃ ┃ ┃ ┃ ┣ 📜information.html
 ┃ ┃ ┃ ┃ ┗ 📜update.html
 ┃ ┃ ┃ ┗ 📜index.html
 ┃ ┃ ┣ 📜application-oauth.yml
 ┃ ┃ ┗ 📜application.yml
 ┗ 📂test
 ┃ ┗ 📂java
 ┃ ┃ ┗ 📂project
 ┃ ┃ ┃ ┗ 📂board_service
 ┃ ┃ ┃ ┃ ┗ 📜BoardServiceApplicationTests.java
```


</details>



### 2-2. DB 설계  

![image](https://github.com/user-attachments/assets/c7d6d2d5-aae8-48d0-88c1-c3b01336b406)  

**MEMBER TABLE**   
![image](https://github.com/user-attachments/assets/86b64260-8c85-4e3f-8be2-923eedd537bf)  
  
**POST TABLE**  
![image](https://github.com/user-attachments/assets/ecd73066-81db-4de3-ad31-cf937db9b5d5)  

**COMMENT TABLE**  
![image](https://github.com/user-attachments/assets/801938bf-9677-4a54-8701-bc94114e9735)  
  
**POST_LIKES TABLE**  
![image](https://github.com/user-attachments/assets/7162804c-3de1-47aa-a36c-6bbf8b913a1a)  

**COMMENT_LIKES TABLE**  
![image](https://github.com/user-attachments/assets/52c7fdf2-c44e-4df0-b4ac-cab269ecb928)  
  

### 2-3. API 설계

**Post 관련 API**  
![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/04184267-86fe-41fd-af99-a6772c85633a)  
  
**Member 관련 API**  
![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/22b3ae7b-34c0-4746-86cc-bdf8feadf447)  
  
**Comment 관련 API**  
![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/bc8aa6de-89c6-4e65-97a5-374b5d8df839)  
  



## 개발 내용

- <a href="https://notorious.tistory.com/340" target="_blank">게시글 페이징 처리 구현</a>
- <a href="https://notorious.tistory.com/341" target="_blank">게시글 키워드 검색 + 정렬 + 페이징 기능 구현</a>
- <a href="https://notorious.tistory.com/342" target="_blank">회원 탈퇴시, 게시글 / 댓글 처리</a>



## 마무리

### 1. 프로젝트 보완사항

- 기능 추가
  1. 게시글에 이미지/동영상 파일 추가 기능
  2. 단일 게시판이 아닌 여러개의 게시판 수용 기능

### 2. 프로젝트 과정에서 발생한 문제
- <a href="https://notorious.tistory.com/339" target="_blank">Spring Security 가 비로그인 상태에서 static rescoure 접근을 제한</a>


### 3. 후기




