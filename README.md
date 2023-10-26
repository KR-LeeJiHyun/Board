Commynity Board
====
|목차|
|----|
|1. 개요|
|2. 기능|
|&nbsp;&nbsp;&nbsp;2.1. 회원|
|&nbsp;&nbsp;&nbsp;2.2. 게시판|
|&nbsp;&nbsp;&nbsp;2.3. 댓글|
|&nbsp;&nbsp;&nbsp;2.4. UI|
|3. 환경|
|&nbsp;&nbsp;&nbsp;3.1. 개발자 환경|
|&nbsp;&nbsp;&nbsp;3.2. Maven Dependency|
|4. 사용법|
|&nbsp;&nbsp;&nbsp;4.1. 사전 요구사항|
|&nbsp;&nbsp;&nbsp;4.2. 순서|
|&nbsp;&nbsp;&nbsp;4.3. 배포자 예시|
|&nbsp;&nbsp;&nbsp;4.4. 주의사항|
|5. 멤버|
|6. 개선점|
|7. 에러 시 대처사항|

# 1. 개요
여러 커뮤니티 게시판 사이트를 참조한 게시판 프로젝트입니다.
# 2. 기능
## 2.1. 회원
- 회원가입
- 회원정보 수정
- 로그인
- 자동 로그인
## 2.2. 게시판
- 게시글 분류
- 게시글 검색
- 게시글 작성, 수정, 조회, 삭제
- 게시글 좋아요, 싫어요
## 2.3. 댓글
- 댓글 및 대댓글 작성, 수정, 조회, 삭제
- 댓글 좋아요, 싫어요

## 2.4. UI

<img width="100%" src="https://user-images.githubusercontent.com/46846455/278188572-48bdf372-cd55-4d5c-9add-63c9f234c988.jpg"/>

#

<img width="100%" src="https://user-images.githubusercontent.com/46846455/278186273-505c4066-a62d-4c28-ba82-817feeff58ad.jpg"/>

#

<img width="100%" src="https://user-images.githubusercontent.com/46846455/278186099-424cc463-32c6-4eb1-af5f-3f28e21e0c7e.jpg"/>

#

<img width="100%" src="https://user-images.githubusercontent.com/46846455/278186238-c0e567ab-296d-4b18-8326-0f2ff324a1b1.jpg"/>

#

<img width="100%" src="https://user-images.githubusercontent.com/46846455/278188561-77b229cd-e475-4c2b-961f-1e32dfafd2a0.jpg"/>

#

<img width="100%" src="https://user-images.githubusercontent.com/72476869/278185798-b4ebc9b3-634b-4d6e-b62a-bbdc6b797a04.png"/>

#

<img width="100%" src="https://user-images.githubusercontent.com/72476869/278185734-3df4c84b-d7ec-48f6-9b99-dd8949e0545b.png"/>

#

<img width="100%" src="https://user-images.githubusercontent.com/46846455/278186084-0474d8dd-3dba-47dd-b5d6-bda5c0231689.jpg"/>

#

<img width="100%" src="https://user-images.githubusercontent.com/72476869/278185830-a1855d70-a0d4-4b9a-b0ee-c4d630107efc.png"/>

#

<img width="100%" src="https://user-images.githubusercontent.com/72476869/278185872-923007d2-682b-475b-8b3b-0aeab667fb8e.png"/>

#

<img width="100%" src="https://user-images.githubusercontent.com/46846455/278186194-7a8b1317-446b-4e7f-ba11-2c2db80bd9b6.jpg"/>

#

<img width="100%" src="https://user-images.githubusercontent.com/46846455/278186202-bad26264-63a8-496b-ab2f-5c40ea503ca3.jpg"/>


# 3. 환경
## 3.1. 개발자 환경
- 이클립스
- 크롬
- SQL Developer
- Oracle 21c Express Edition Release 21.0.0.0.0
- Windows 10, 11
## 3.2. Maven Dependency
- tomcat 9.0.55
- spring 5.2.22.RELEASE
- tiles-jsp 3.0.8
- jstl 1.2
- ojdbc 19.14.0.0
- spring-jdbc 5.3.20
- jackson-databind 2.11.2
- spring-security-core 5.7.5
# 4. 사용법
## 4.1. 사전 요구사항
- [tomcat](https://tomcat.apache.org/download-80.cgi)
- [Oracle DB](https://www.oracle.com/kr/downloads/#category-database)
- [테이블 설정](https://github.com/KR-LeeJiHyun/Board/issues/16)
- [git](https://git-scm.com/download/win)(선택사항)
## 4.2. 순서
- git 혹은 zip파일을 내려받는다.
  ``` shell
  git clone https://github.com/KR-LeeJiHyun/Board.git
  ```
- 본인의 IDE에서 Maven Project를 불러온다.
- tomcat 디렉토리를 지정한다.
- Maven Project를 빌드한다.
- Oracle databse 연결 정보(url, username, password)를 프로젝트명/webapp/WEB-INF/spring/service-context.xml에 설정한다.
- Maven Project를 실행시킨다.
## 4.3. 배포자 예시
- git 혹은 zip파일을 내려받는다.
  ``` shell
  git clone https://github.com/KR-LeeJiHyun/Board.git
  ```
- 이클립스를 실행시킨다.
- 프로젝트 불러오기
  ``` shell
  FILE -> Import -> Maven -> Existing Maven Projects
  ```
- Maven Project를 빌드한다.
  ``` shell
  프로젝트명 -> Run As -> Maven install
  프로젝트명 -> Maven -> Update project
  ```
- Oracle databse 연결 정보(url, username, password)를 프로젝트명/webapp/WEB-INF/spring/service-context.xml에 설정한다.
- 프로젝트 실행
  ``` shell
  - Windows -> Show View -> other -> server
  - creat a new Server -> Apache -> tomcat v9.0 server -> add -> Tomcat installation directory에 톰캣 디렉토리 지정 -> finish
  - 프로젝트명 -> Run as -> Run on Server
  ```
- [에러 시 참고사항](https://github.com/KR-LeeJiHyun/Board/edit/main/README.md#7-%EC%97%90%EB%9F%AC-%EC%8B%9C-%EB%8C%80%EC%B2%98%EC%82%AC%ED%95%AD)
## 4.4. 주의사항
- 다른 버전에서 테스트를 한적 없으므로 개발자 환경과 맞추는 것을 권장한다.
# 5. 멤버
- KR-LeeJiHyun : 게시글
- kr-seominwoo : 댓글
- 공통 : 그 외
# 6. 개선점
- 게시판 관리자 기능
- 회원탈퇴 기능
- 이미지 및 파일 첨부 기능
- 클라이언트와 서버 세션 연결 시 리프레시 토큰을 보내는 방법이 있으면 개선이 가능
- 새로고침 시 댓글 페이지 유지 기능
- 글목록 돌아가기 기능
# 7. 에러 시 대처사항
- tomcat 서버가 실행되지 않을 시 startup 파일로 tomcat 서버 한 번 실행시킨다.
- 실행 오류 시
    ```shell
    프로젝트 속성 -> Deployment Assembly -> Add -> Java Build Path Entries -> Maven Depedencies -> finish
    ```
