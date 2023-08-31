-- 현재 근무하고 있는 직원 사번,이름,직책을 모두 출력
select a.emp_no as 사번 ,a.first_name as 이름, b.title as 직책
from employees a, titles b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01';

-- 현재 근무하고 있는 직원 사번,이름,직책을 모두 출력하되 여성 엔지니어만 출력
select a.emp_no as 사번 ,a.first_name as 이름, a.gender as 성별,b.title as 직책
from employees a, titles b
where a.emp_no = b.emp_no and a.gender = "F" and b.title = "Engineer"
and b.to_date = '9999-01-01';

-- ANSI / ISO SQL1999 JOIN 표준 문법

-- Join ~ on *
-- 현재 직책별 평균 연봉을 큰 순서대로 출력
select a. title as 직책, avg(b.salary) as 평균연봉
from titles a, salaries b
where a.emp_no = b.emp_no 
and a.to_date = '9999-01-01' 
and b.to_date = '9999-01-01'
group by a.title
order by avg(b.salary) desc;

select a. title as 직책, avg(b.salary) as 평균연봉
from titles a join salaries b on a.emp_no = b.emp_no
where a.to_date = '9999-01-01' 
and b.to_date = '9999-01-01'
group by a.title
order by avg(b.salary) desc;

-- Natural Join
-- 조인 대상이 되는 테이블들에 이름이 같은 공통 칼럼이 있는 경우
-- 조인 조건을 명시하지 않고 암묵적으로 조인이 된다(자동으로 조인)
-- 현재 근무하고 있는 직원의 이름과 직책을 출력
select a.first_name,b.title
from employees a, titles b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'; 

select a.first_name, b.title
from employees a natural join titles b 
where b.to_date = '9999-01-01'
order by a.first_name asc;

-- Join ~ using
-- natural join의 문제점
-- 현재 근무하고 있는 직원의 직책과 연봉을 출력
select a.title, b.salary
from titles a natural join salaries b
where a.to_date = '9999-01-01'
and b.to_date = '9999-01-01';

-- 해결 1
select a.title, b.salary
from titles a join salaries b using (emp_no)
where a.to_date = '9999-01-01'
and b.to_date = '9999-01-01';

-- 해결 2
 select a.title, b.salary
from titles a join salaries b on a.emp_no = b.emp_no
where a.to_date = '9999-01-01'
and b.to_date = '9999-01-01';

select * from departments;
select * from employees;
select * from dept_emp;
-- 실습문제 1
-- 현재 직원별 근무 부서를 출력
-- 사번 직원이름 부서명 순으로 출력
select a.emp_no as '사번' ,a.first_name '직원이름' , c.dept_name '부서명'
from employees a join dept_emp b on a.emp_no = b.emp_no 
join departments c on c.dept_no = b.dept_no
where b.to_date = '9999-01-01'
order by c.dept_name;



-- 실습문제 2
-- 현재, 직책별 평균연봉과 직원수를 구하되 직책별 직원수가 100명 이상인 직책만 출력
-- 직책 평균연봉 직원만 출력
select b.title, avg(a.salary),count(*)
from salaries a join titles b on a.emp_no = b.emp_no
where a.to_date = '9999-01-01' 
and b.to_date = '9999-01-01'  
group by b.title
having count(*) > 100;
-- 실습문제 3
-- 현재 부서별로 직책이 Engineer인 직원들에 대해서만 평균연봉을 구하세요
-- 부서이름 평균급여 순으로 출력gkrh 평균연봉이 높은순으로 정렬
select a.dept_name,(d.salary)
from departments a, dept_emp b, titles c, salaries d
where a.dept_no = b.dept_no
and b.emp_no = c.emp_no
and c.emp_no =d.emp_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
and d.to_date = '9999-01-01'
and c.title = 'Engineer'
group by a.dept_name
order by avg(d.salary);


