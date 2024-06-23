# 🗒 아이 돌봄 서비스

미취학아동 육아(돌봄, 배움) 서비스 입니다.

## 프로젝트 기능 및 설계
- 회원가입 기능
  - 사용자는 회원가입을 할 수 있다.
  - 회원가입시 이메일과 패스워드를 입력받으며, 이메일은 unique 해야한다.

- 로그인 기능
  - 사용자는 로그인을 할 수 있다. 로그인시 회원가입때 사용한 아이디와 패스워드가 일치해야한다. 

- 프로그램 등록 기능
  - 프로그램 등록은 관리자만 할 수 있다.

- 프로그램 목록 조회 기능 
  - 로그인하지 않은 사용자를 포함한 모든 사용자는 프로그램 목록을 조회할 수 있다.
  - 프로그램 목록은 종류가 많을수 있으므로 paging 처리를 한다.

- 특정 프로그램 검색 기능
  - 로그인하지 않은 사용자를 포함한 모든 사용자는 프로그램을 검색할 수 있다. 
  - 프로그램 제목, 유형 등이 조회된다.

- 프로그램 상세페이지 기능
  - 각 프로그램 세부사항을 확인 할 수 있다.
  - 각 프로그램 댓글 목록을 확인 할 수 있다.

- 장바구니 기능
  - 로그인한 사용자는 프로그램을 장바구니에 추가, 삭제할 수 있다.
  - 로그인한 사용자는 자신의 장바구니를 조회할 수 있다.

- 댓글 작성 기능
  - 로그인한 사용자는 프로그램에 대해 댓글을 작성할 수 있다.

## ERD 
![erd](https://github.com/totorotail/BabyCare/assets/130023711/18592f5d-1a74-452d-be0e-d24e9576c0f0)

### Tech Stack
<div align=center> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>
