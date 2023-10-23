Commynity Board
====
|목차|
|----|
|1. 개요|
|2. 기능|
|3. 환경|
|&nbsp;&nbsp;&nbsp;3.1. 개발자 환경|
|&nbsp;&nbsp;&nbsp;3.2. Maven Dependency|
|4. 사용법|
|5. 멤버|
|6. 개선점|

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
# 3. 환경
## 3.1. 개발자 환경
- 이클립스
- 크롬
- SQL Developer
- [Oracle 21c Express Edition Release 21.0.0.0.0](https://github.com/KR-LeeJiHyun/Board/issues/16)
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
사용법입니다.
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
