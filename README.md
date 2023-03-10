# Project_chatBoard

제작 기간 : 2023-02-28 ~~ 2023-03-10

![chat_board_main](https://user-images.githubusercontent.com/79496557/224250034-b5cb3652-33d9-438a-a5cb-85c9f2ad2278.PNG)


## 목차
- [프로젝트 소개](#1-프로젝트-소개)
- [프로젝트 기능](#2-프로젝트-기능)
- [이용한 스택](#3-이용한-스택)
- [실행 화면](#4-실행-화면)

---
- [DB 설계](#5-DB-설계)
---
- [개발 내용 상세설명](#6-개발-내용-상세설명)
---
- [후기](#7-후기)
---


## 1. 프로젝트 소개

Project_chatBoard는 Spring boot를 이용하여 만든 게시판 프로젝트입니다. 2023-02-28부터 2023-03-10까지 프로젝트를 진행하였습니다.

---
## 2. 프로젝트 기능

ChatBoard 프로젝트는 크게 세가지 기능으로 나뉘어 있습니다. 게시판을 나타내는 POST, 유저를 나타내는 USER, 댓글을 나타내는 COMMENT가 프로젝트의 가장 주된 기능입니다.
* POST : 기본적인 CRUD 기능 구현, HttpServlet 쿠키를 이용한 조회수 구현, 페이징 처리, 특정 단어로 게시글 전체 검색, 내용 검색, 제목 검색, 작성자 검색 기능 구현.
* USER : Spring Security를 이용한 회원가입, 로그인 구현. 회원가입 시 기존 db와 비교해 동일한 아이디가 있으면 경고 메세지를 출력. 정규식을 이용하여 맞지 않는 양식의 밸류값 인풋 시 경고 메시지 출력.
* COMMENT : Rest API를 이용한 CRUD 구현. 댓글 수정 시 Modified_date를 사용해 수정된 댓글임을 보여줄 수 있도록 함.

---
## 3. 이용한 스택

- java 11
- Spring Boot 2.7.1
- Thymeleaf
- Spring Security
- MyBatis
- MariaDB(Dbeaver)
- Gradle
- Html
- css
- JavaScript

---
## 4. 실행 화면

### 1. POST
1. 전체 게시글 보기
![chat_board_main](https://user-images.githubusercontent.com/79496557/224257083-a74ed0b8-713b-433f-bb35-049036556548.PNG)

2. 본인이 쓴 게시글만 보기
![내가 쓴 글](https://user-images.githubusercontent.com/79496557/224258276-0055f3a8-0973-4bcd-bd09-7075862a220e.PNG)
좌측 바에서 '내가 작성한 글'을 클릭하면 USER의 id(PK)와 POST의 user_id(FK) 를 대조하여 내가 작성한 글들을 가져온다.


## 참고 블로그

https://congsong.tistory.com/

https://velog.io/@juwonlee920/Spring-%EC%A1%B0%ED%9A%8C%EC%88%98-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84-%EC%A1%B0%ED%9A%8C%EC%88%98-%EC%A4%91%EB%B3%B5-%EB%B0%A9%EC%A7%80

https://dev-coco.tistory.com/

https://github.com/wmdwjddyd6/Board/blob/main/board/src/main/resources/templates/board/post.html
