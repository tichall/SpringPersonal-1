# SpringPersonal-1
입문 주차 - 일정 관리 시스템
<br>
<br>


# Usecase Diagram

![유스케이스 다이어그램](SpringPersonal.png)

<br>

# API 명세서
## 일정 작성
  
**Method**
- POST

**URL**
- /api/schedule

**Request**

{
"title" : "제목",
"contents" : "내용",
"owner" : "담당자",
"password" : "비밀번호"
}

**Response**

{ 
‘title’ : ‘제목’, 
‘contents’ : ‘내용’, 
‘owner’ : ‘담당자’,
’createdAt’ : 생성 날짜,
’modifiedAt’ : 수정 날짜
}


## 일정 조회
### 선택한 일정 조회
  
**Method**
- GET

**URL**
- /api/schedule/{scheduleId}

**Request**


**Response**
- 선택한 일정 정보


### 전체 일정 조회

**Method**
- GET

**URL**
- /api/schedules

**Request**


**Response**
- 모든 일정 정보

  
## 일정 수정

## 일정 삭제 
