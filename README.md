# Board Service [Spring Boot Project]
---
![메인화면](https://github.com/user-attachments/assets/44359a3c-ffd5-4b1b-af23-e90da22ce39c)
---
## 목차
 - [1. 프로젝트 소개](#1-프로젝트-소개)
   - [1-1. 프로젝트 소개](#1-1-프로젝트-소개)
   - [1-2. 개발 기간](#1-2-개발-기간)
   - [1-3. 프로젝트 기능](#1-3-프로젝트-기능)
   - [1-4. 개발 환경](#1-4-개발-환경)
   - [1-5. 실행 화면](#1-5-실행-환경)
  
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

   **1. 회원가입 화면**

   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/fe32e1bc-cfeb-4c2b-9bc2-7ac7a2af3fea)  


   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/f7f03b03-c0d4-4526-a495-2cdcb6e0ff46)  

   ※ 회원가입 양식에 대한 경고 메시지 표시  



   **2. 로그인 화면**

   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/70aeecdb-783d-4469-b23a-b1b814896db5)  

   ※ 로그인 실패에 대한 경고 메시지 표시  



   **3. 회원정보 수정 화면**

   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/49fd5a13-5ff2-4ee5-96b1-8fef64824af3)  
   로그인 된 username 을 입력하여, 회원정보 화면으로 이동  

   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/1ffe8588-1a0d-4b49-8bb8-15a53608835e)  

  
   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/db057e23-35ee-4f4b-afcb-333b7dcc2956)  
   변경하려는 닉네임에 대한 중복 체크    
   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/8e879869-e2e9-4503-8ff2-80213486f736)  
   변경하려는 비밀번호에 대한 유효성 체크    



   **4. 회원 탈퇴**
   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/86e4f872-27d0-4d94-a470-5e5ad86565dd)  
   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/109b6351-9b3c-4f57-a1eb-26bc5373da76)  
   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/e0a6d5a1-82f8-41b2-bbde-ae1eeaeb9f09)  
   회원 탈퇴 처리 후, 메인 페이지로 리다이렉션, 로그아웃 처리가 된다.    
   또한, 탈퇴한 회원이 작성한 게시글 / 댓글의 작성자는 'unknown' 으로 표기된다.  

    
  </details>



  
  <details>
    <summary>게시글</summary>
    
   **1. 게시글 전체 목록**

   로그인 X 화면  
   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/5547af49-7724-4aeb-979f-7a6ad2590bdd)  

   로그인 O 화면  
   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/0871872a-720b-445f-bfe3-2055b252bd2e)  



   **1-1. 게시글 전체 목록 정렬**

   '조회수' 기준으로 내림차순 정렬  
   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/18250746-ccbb-4911-bcf1-f39d151f0f83)  

   ※ 로그인을 하지 않아도 게시글 정렬이 가능하다.  



   **2. 게시글 등록 화면**

   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/67099714-c576-4029-8b68-552aa2a8ef5e)  

   로그인한 사용자만 게시글 작성이 가능하며, 작성 후 '게시' 버튼을 누르면 메인 페이지로 리다이렉트 된다.  

   

   **3. 게시글 상세 정보**

   로그인 X  
   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/92b77621-66c2-4a46-83e7-e13552424ae3)  

   ※ 로그인 하지 않은 경우, 게시글 상세 정보에 접근 가능하지만, '게시글 설정' 옵션에 접근할 수 없다.  


   작성자 계정이 아닌 다른 계정으로 로그인 O  
   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/bef81a23-7918-4817-a5b6-b0dc5515f885)  

   ※ 작성자 계정이 아닌 다른 계정으로 로그인한 경우, '게시글 설정' 옵션에 접근 가능하지만, '게시글 작성' 기능만 접근 가능하다.  


   작성자 계정으로 로그인 O  
   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/15f21e93-5271-447d-b708-f7aa6e0feff0)  

   ※ 작성자 계정으로 로그인 한 경우, '게시글 수정' 과 '게시글 삭제' 를 할 수 있다.  



   **4. 게시글 수정 화면**

   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/2ecc1243-4c45-4f2f-9b42-4af5f6e7e914)  

   게시글 수정 후, '게시' 버튼을 눌러 수정을 마무리한다.    
   '게시' 버튼을 누르면 게시글 목록으로 이동한다.  

   [수정된 게시글 화면]  
   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/c828dff6-270e-4b36-a597-85969a196c0c)  
   
   

   **5. 게시글 삭제 화면**

   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/816ef173-4759-4456-b8b5-1ad39da2f7bb)  

   '게시글 삭제' 버튼을 눌러 삭제를 진행한다.  

   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/08081d6f-d02c-4c06-9a42-d1c9d963d53d)  

   '게시글 번호'를 포함한 삭제 완료 안내 메시지가 등장한다.

   [게시글 목록]  
   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/440f7a24-f7f1-4178-b6da-94319eda0f34)  

   게시글이 삭제 된 것을 볼 수 있다.


   **6. 게시글 검색 화면**

   ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/6b0fdefb-da6a-4dc7-9220-14c441fbb801)  



  **6-1. 게시글 검색 후 페이징 화면**

  ['by' 키워드로 검색한 화면]  
  ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/bbd81c39-1be6-45da-a29c-a9431b2a07ca)  

  ['by' 키워드로 검색 내용 中 사용자 기준 4페이지]  
  ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/c56913a8-1e71-4927-a828-27cf637fc195)  



  **6-2. 게시글 검색 후 페이징 + 정렬**

  ['by' 키워드로 검색 내용 && 조회수 기준 내림차순 정렬]  
  ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/79fef79f-8ff0-4c39-99ac-2677d557079b)  


  ['by' 키워드로 검색 내용 && 조회수 기준 내림차순 정렬 中 사용자 기준 1페이지]  
  ![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/d346d15f-fe23-401e-a321-e79a19fa6537)   
    
  </details>

  <details>
    <summary>댓글</summary>

  **1. 댓글 작성 화면**
  

  **2. 댓글 수정**



  **3. 댓글 삭제**

    
  </details>




## 2. 프로젝트 구조

### 2-1. 패키지 구조

<details>

<summary>패키지 구조 보기</summary>

```

```


</details>



### 2-2. DB 설계

![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/34bac54a-31d9-458a-83e3-33ca74f29413)

**MEMBER TABLE**   
![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/688dd71e-b194-41d7-8366-5634e666f748)  

**POST TABLE**  
![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/e56c4f17-526d-4c94-92b3-14cb63b0aee3)  

**COMMENT TABLE**  
![image](https://github.com/yashin20/BoardServiceV2/assets/92693776/422d6e53-7c6c-4d22-bc08-fd9febfa44a6)  



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


### 2. 프로젝트 과정에서 발생한 문제
- <a href="https://notorious.tistory.com/339" target="_blank">Spring Security 가 비로그인 상태에서 static rescoure 접근을 제한</a>


### 3. 후기




