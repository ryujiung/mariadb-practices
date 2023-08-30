show databases;
use employees;
select * from employees;
select * from salaries where emp_no = 10002;
select count(*) as male from employees where gender = "M";
select count(*) as female from employees where gender = "F";
select count(*) as ne from salaries where to_date like "9999%";
select count(*) as total from departments;
-- 예제 1
select * from departments order by length(dept_name) desc;

-- 프로젝션
-- 예제 2
select first_name as '이름', gender as '성별', hire_date as '입사일' 
from employees
order by hire_date;

-- titles 테이블에서 모든 직급을 출력
select title from titles;

-- titles 테이블에서 직급은 어떤 것들이 있는지 직급 이름을 한번씩만 출력
select distinct(title) from titles;

-- 비교연산자: employees 테이블에서 1991년 이전에 입사한 직원의 이름,성별,입사일을 출력
select first_name as '이름', gender as '성별', hire_date as '입사일'
from employees
where hire_date < '1991-01-01'
order by hire_date desc;

-- 논리연산자: employees 테이블에서 1989년 이전에 입사한 여직원을 출력
select first_name as '이름', gender as '성별', hire_date as '입사일'
from employees
where hire_date < '1989-01-01' and gender = "F"
order by hire_date desc;

-- in연산자 dept_emp 테이블에서 부서번호가 d005이거나 d009에 속한 사원의 사번,부서번호 출력
select emp_no, dept_no
from dept_emp
where dept_no in ('d005','d009');

-- like 검색 employees 테이블에서 1989년에 입사한 직원들의 이름,입사일 출력
select first_name as 이름, hire_date as 입사일
from employees
where hire_date like '1989%';

-- order by
select concat(first_name, ' ', last_name) as 이름,gender as 성별,hire_date as 입사일
from employees
order by hire_date;

-- salaries 테이블에서 2001년 월급이 가장 높은 순으로 사번 월급을 출력 
select emp_no, salary,to_date,from_date
from salaries
where to_date like '2001%'
or from_date like '2001%'
order by salary desc;

-- 남자직원의 이름, 성별, 입사일을 선임순으로 출력
select first_name, gender,hire_date
from employees
where gender = "M"
order by hire_date;

-- 직원들의 사번, 월급을 사번순으로 출력
select emp_no,salary
from salaries
order by emp_no asc,salary desc;
