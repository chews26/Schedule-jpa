# 📆 JPA 일정관리 앱 📆
### [💻 JPA 일정관리 앱 만들기 작성 내용 바로가기 ](https://github.com/chews26/Schedule-jpa/tree/main/src/main/java/com/example/schedulejpa)
### [📓 JPA 일정관리 앱 만들기 작성 노션 바로가기](https://shinelee26.notion.site/JPA-13e86ea33f9480f9878dc5c32b6f7a64?pvs=4)
### [🏠 JPA 일정관리 앱 만들기 작성 블로그 바로가기](https://shinelee26.tistory.com/tag/spring%20jpa%20%EC%9D%BC%EC%A0%95%EA%B4%80%EB%A6%AC%EC%95%B1)
### [🌠 JPA 일정관리 앱 만들기 API 테스트 내용 바로가기](https://www.postman.com/research-candidate-37887438/jpa/documentation/ueaib34/jpa)

## API 명세서
#### [Notion - API 명세서 작성내용 바로가기](https://www.erdcloud.com/d/zDQNGkHLaqenumNhz)
![page1_1](https://github.com/user-attachments/assets/cbf4bddd-ac30-4940-ad19-6c10cb99bc32)


## ERD 다이어그램
#### [ERD Cloud - 일정관리 앱 작성내용 바로가기](https://www.erdcloud.com/d/Cm6RebkEhPmJ6nJ3f)
![image](https://github.com/user-attachments/assets/ab5e45b0-ec1f-4623-b007-b50a468e617d)

## SQL 쿼리 작성 (schedule.sql)
### Create
```sql
 create table schedule
 (
     created_date  datetime(6)  null,
     end_date      datetime(6)  not null,
     modified_date datetime(6)  null,
     schedule_id   bigint       auto_increment,
     start_date    datetime(6)  not null,
     user_id       bigint       null,
     contents      longtext     not null,
     title         varchar(255) not null,
     primary key (schedule_id),
     FOREIGN KEY (user_id) REFERENCES users(user_id)
 );

create table user
(
    created_date  datetime(6)  null,
    modified_date datetime(6)  null,
    user_id       bigint       auto_increment,
    email         varchar(255) not null,
    name          varchar(255) not null,
    password      varchar(255) not null,
    primary key (user_id),
    unique (email)
);
```
### Insert
```sql
INSERT INTO schedule
(
title,
startDateTime,
endDateTime,
contents
)
VALUE(
'공부하기'
'2024-10-30 16:00:00'
'2024-10-31 17:00:00'
'zep에 접속하기'
)
;
```
### Select
```sql
SELECT *
FROM schedule;
```
### update
```sql
UPDATE schedule
SET title = '놀기',
start_datetime = '2024-11-31 09:00:00',
end_datetime = '2024-11-31 11:00:00',
description = '게임하기'
WHERE id=1;
```
### Delete
```sql
DELETE FROM schedule
WHERE id=1;
```
