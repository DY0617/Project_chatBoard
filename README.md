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
만약 로그인 상태가 아니라면 로그인 창으로 화면이 전환된다.

3. 게시글 등록

![글 작성](https://user-images.githubusercontent.com/79496557/224259174-3c45ac76-f3e5-4a5b-bba2-c0fb0dadef61.PNG)

자유게시판이나, 내가 작성한 글에서 우측 하단에 글쓰기 버튼이 있는데, 버튼을 클릭하면 해당 페이지로 전환된다. 로그인 상태가 아니라면 로그인 창으로 화면이 전환된다.
공지글 체크버튼은 체크 시 공지글이 되고, 체크 해제 시 평범한 글이 된다. 등록일은 글 작성 시간을 바로 가져오며 readonly 상태이다. 작성자도 로그인 한 유저의 nickname이 값이며, readonly 상태이다. 그리고 내용을 자유롭게 입력하고 저장 버튼을 누르면 게시글이 db의 post table에 저장되며, 뒤로 버튼을 누를 경우 다시 게시글 보기로 이동한다.

4. 게시글 상세보기

![글 상세보기](https://user-images.githubusercontent.com/79496557/224260462-b0a1a0c7-c44e-4133-bd04-b381e325548e.PNG)

자유게시판이나 내가 작성한 글에서 원하는 게시글을 누르면 그 게시글의 상세보기 페이지로 이동한다. 글 유형이 일반인지 공지인지, 글이 언제 작성되었는지, 누가 작성했는지, 조회수가 얼마인지, 내용이 무엇인지 등등 게시글의 상세 정보를 확인할 수 있고, 그 밑에 수정, 삭제, 뒤로 버튼이 있다.
수정과 삭제 버튼은 게시글의 작성자에게만 보인다. 즉 게시자 외에 다른 USER들은 게시글을 수정하거나 삭제할 수 없다. 게시글 수정 버튼을 클릭 시 게시글의 내용을 저장한 채로 다시 게시글 등록으로 넘어가고(새로운 게시글이 생기는 것이 아니고 기존 게시글을 수정하는 것임.) 해당 페이지에서 게시글을 수정 후 저장 버튼을 누르면 게시글이 성공적으로 수정된다. 
게시글 삭제 버튼 클릭 시 db에서 해당 게시글의 delete_yn이 0에서 1로 변환된다. db상에서 아예 삭제되는 것이 아니고, 유저에게 보이지 않게만 만드는 것이다.

5. 게시글 검색

![검색](https://user-images.githubusercontent.com/79496557/224263403-cbfbed33-7c9e-415a-af8d-da3365b6ef4c.PNG)

우측 상단에서 서치 박스에서 원하는 대상을 선택하고, 검색명을 입력 한 후 버튼을 누르면 해당 조건에 맞는 리스트만 출력된다.
검색 후에 게시글 수가 특정 수 이상이면 페이징 처리가 되고, 검색 후에는 무조건 첫 페이지로 되돌아간다. (만약 5페이지에서 특정 단어를 검색한다 해도 그 결과물을 1페이지부터 보여준다는 의미)
서치 박스에는 모든 내용, 작성자, 제목, 내용이 있고 이 중에서 원하는 대상을 정하면 된다.

---

### 2. USER
1. 로그인 화면

![로그인](https://user-images.githubusercontent.com/79496557/224265196-29a74d79-dce0-43b9-b758-473fead54560.PNG)

비로그인 상태에서 죄측 상단에 로그인과 회원가입 버튼이 있는데, 로그인 버튼을 클릭하면 해당 페이지가 호출된다. 올바른 username과 password를 입력하면 db와 대조하여 로그인이 완료되고, 특정 문제로 로그인이 실패하면 오류 메시지가 출력된다.
![로그인 실패](https://user-images.githubusercontent.com/79496557/224265586-7ab9f1a6-f1c7-4766-9d8e-fd984f23bbe7.PNG)
<로그인 실패시 화면


2. 회원가입 화면

![회원가입](https://user-images.githubusercontent.com/79496557/224266656-3624a52a-8874-47dc-8cc0-0471bf33965b.PNG)

로그인 버튼 우측 회원가입 버튼을 클릭하면 해당 페이지가 호출된다. 아이디, 비밀번호, 닉네임, 이메일을 필수적으로 입력해야 하고, 각각의 정규형에 맞지 않는 인풋값이 들어오면 해당 인풋값 밑에 경고 문구가 출력된다. 입력 정보를 저장한 채로 리다이렉트 하여 입력해 놓았던 값이 그대로 유지된다.

![회원가입 오류1](https://user-images.githubusercontent.com/79496557/224267393-7bdc3e32-2f4c-4b67-8d18-ea9fcb00714c.PNG)

db에서 아이디와 닉네임은 unique값인데, 만약 이 규칙을 어긴다면 해당 상황에 맞는 경고 문구가 출력된다.

![회원가입 오류 2](https://user-images.githubusercontent.com/79496557/224267703-0a9b053d-5ad5-4e2c-9833-6380609f1192.PNG)

---

### 3. COMMENT
1. 댓글 작성 화면

![댓글](https://user-images.githubusercontent.com/79496557/224269122-b0ece1dc-c8aa-4000-acf6-48bcf48de1d9.PNG)

게시글 상세보기 하단에 댓글록이 있다. created_date 순으로 정렬된 댓글이 rest api를 이용하여 호출된 후, 위와 같이 출력된다. 댓글란에 원하는 댓글을 입력 후 하단 작성 버튼을 클릭하면 js 함수가 호출되면서 해당 댓글이 db에 저장되고, 작성한 댓글이 바로 보이게 만들었다. 댓글에 보이는 수정과 삭제 버튼은 댓글을 단 사람에게만 보인다.

2. 댓글 수정 화면

![댓글 수정](https://user-images.githubusercontent.com/79496557/224270114-60088d0d-7ce9-461e-8995-a6a2bccf4d6d.PNG)

댓글 수정 버튼을 클릭하면 위와 같은 수정창이 나오게 된다. 취소 버튼을 누르면 댓글 수정이 취소되고, 댓글 수정 후 수정 버튼을 누르면 수정된 댓글과 modified_date가 db에 저장되고, 닉네임 옆에 (댓글 수정됨) 이 같이 출력된다.

3. 댓글 삭제

댓글 삭제 버튼을 누르면 js alert() 함수로 삭제하시겠습니까? 경고를 출력하고, 확인을 누르면 comment table의 delete_yn 변수를 0에서 1로 바꾼다. db상에서 데이터를 아예 삭제하는 것이 아니고 출력만 되지 않게 만드는 것이다.


## 5. DB 설계

![board - tb_comment](https://user-images.githubusercontent.com/79496557/224272577-7fdf36a4-2639-4f70-8494-4757d4046c25.png)

---

## 참고 블로그

https://congsong.tistory.com/ <<해당 블로그에서 제공한 부트스트랩 사용

https://velog.io/@juwonlee920/Spring-%EC%A1%B0%ED%9A%8C%EC%88%98-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84-%EC%A1%B0%ED%9A%8C%EC%88%98-%EC%A4%91%EB%B3%B5-%EB%B0%A9%EC%A7%80

https://dev-coco.tistory.com/

https://github.com/wmdwjddyd6/Board/blob/main/board/src/main/resources/templates/board/post.html
