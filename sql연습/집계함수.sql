-- 집계쿼리: select절에 통계함수(avg,max,min,count,...)

select avg(salary),sum(salary) from salaries;

-- select절에 그룹함수 있는 경우, 어떤 칼럼도 select 절에 올 수 없다!!
-- emp_no는 아무런 의마가 없다.->오류!!

select emp_no, avg(salary)
from salaries;

-- 쿼리 순서
--  	1.from:테이블에 접근
-- 		2.where: 조건에 맞는 row를 선택
-- 		3.projection: 집계
-- 		4.결과를 반환: 출력

select avg(salary)
from salaries
where emp_no =10060;

-- group by
select emp_no, avg(salary)
from salaries
group by emp_no
order by avg(salary) desc;

-- Having
select emp_no, avg(salary)
from salaries
group by emp_no
having avg(salary) >= 60000
order by avg(salary);

-- 문접적으로 오류!!

select avg(salary),sum(salary)
from salaries
where emp_no = 10060;

select emp_no, avg(salary),sum(salary)
from salaries
group by emp_no
having emp_no = 10060;