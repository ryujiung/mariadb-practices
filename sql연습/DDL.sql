-- ddl
drop table member;
create table member(
	no int not null auto_increment,
    email varchar(200) not null,
    password varchar(64) not null,
    name varchar(100) not null,
    department varchar(100),
    primary key(no)
);

desc member;

alter table member add column juminbunho char(13) not null;

desc member;

alter table member drop column juminbunho;

desc member;

alter table member add column juminbunho char(13) not null after email;
desc member;

alter table member change column department dept varchar(200) not null;
desc member;

alter table member add self_intro text;
desc member;

alter table member drop column juminbunho;
desc member;

-- dml
insert
into member(no,email,password,name,dept,self_intro)
values (null,'rju1202@naver.com',password('1234'),'류지웅','개발팀',null);
select * from member;

insert
into member(email,name,dept,password)
values ('rju1202@naver.com','류지웅1','개발팀',password('1234'));

update member
set email = 'rjnk@gmail.com', name = '지웅'
where no = 3;
select * from member;

delete 
from member
where no =3;
select * from member;

-- transaction begin
set autocommit = 0;
select @@autocommit from dual;

select * from member;
select no,email,dept 
from member;