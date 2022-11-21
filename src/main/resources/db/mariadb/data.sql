insert into member (email, username, password, role)
values (
    'asd8500@naver.com',
    'user',
    1234,
    'USER'
);

select * from member;

# auto increment 초기화
alter table board auto_increment = 5;
SET @COUNT = 0;
UPDATE board SET board_id = @COUNT:=@COUNT+1;

# 테이블 상태 조회
SHOW TABLE STATUS WHERE name = 'board';

