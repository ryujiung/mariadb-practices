-- 서브쿼리(SUBQUERY) SQL 문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(a.emp_no) as '직원 수'
from employees a, salaries b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
and b.salary > (select avg(salary)
					from salaries 
					where to_date = '9999-01-01');

-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 
select c.dept_name,a.emp_no,a.first_name,d.salary
from employees a,dept_emp b,departments c, salaries d
where a.emp_no = b.emp_no
and b.dept_no = c.dept_no
and a.emp_no = d.emp_no
and b.to_date = '9999-01-01'
and d.to_date = '9999-01-01'
and (b.dept_no,d.salary) in (select b.dept_no,max(a.salary)
								from salaries a , dept_emp b
								where a.emp_no = b.emp_no
								and a.to_date = '9999-01-01'
								and b.to_date = '9999-01-01'
								group by b.dept_no)
order by d.salary desc;
-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select a.emp_no,a.first_name,d.salary
from employees a,dept_emp b,salaries d, (select avg(a.salary) as avg_salary, b.dept_no as bb
															from salaries a , dept_emp b
															where a.emp_no = b.emp_no
															and a.to_date = '9999-01-01'
															and b.to_date = '9999-01-01'
															group by b.dept_no) e
where a.emp_no = b.emp_no
and a.emp_no = d.emp_no
and b.to_date = '9999-01-01'
and d.to_date = '9999-01-01'
and d.salary > e.avg_salary;

-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.


-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.

-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 

-- 문제7.
-- 평균 연봉이 가장 높은 직책?

-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
select d.dept_name, a.first_name, b.salary, e.first_name, e.salary
  from employees a, salaries b, dept_emp c, departments d,(select d.dept_no as dept_no, b.salary as salary, a.first_name as first_name
															  from employees a, salaries b, dept_emp c, dept_manager d
															  where a.emp_no = b.emp_no
															  and a.emp_no = c.emp_no
															  and a.emp_no = d.emp_no
															  and b.to_date = '9999-01-01'
															  and c.to_date = '9999-01-01'
															  and d.to_date = '9999-01-01') e
 where a.emp_no = b.emp_no
 and a.emp_no = c.emp_no
 and d.dept_no = c.dept_no
 and c.dept_no = e.dept_no
 and b.to_date = '9999-01-01'
 and c.to_date = '9999-01-01'
 and b.salary > e.salary;
