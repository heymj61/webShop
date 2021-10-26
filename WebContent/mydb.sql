create table t_member(
id varchar(10) primary key
, pwd varchar2(20)
, name varchar2(50)
, email varchar2(20)
, joinDate date
);

insert into t_member values ('hong', '1234', '홍길동', 'aaa', sysdate);

commit