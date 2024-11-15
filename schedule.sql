-- Create
-- 1. schedule 테이블 생성
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

-- 2. user 테이블 생성
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

-- Insert
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

-- Select
-- 1. 전체 일정 조회
SELECT *
FROM schedule
WHERE id=1;

-- 2. 선택 일정 조회
UPDATE schedule
SET title = '놀기',
start_datetime = '2024-11-31 09:00:00',
end_datetime = '2024-11-31 11:00:00',
description = '게임하기'
WHERE id=1;

-- delete
DELETE FROM schedule
WHERE id=1;