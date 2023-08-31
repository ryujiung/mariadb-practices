
-- subquery

-- select 절의 칼럼 프로젝션
-- insert into t values()


-- select from절의 서브쿼리 
select a.n, a.s, a.r
from (select now() as n,sysdate() as s, 3+1 as r from dual) a;


-- select where(having)절의 서브쿼리

-- Fai Bale이 근무하는 부서에서 근무하는 다른 직원의 사번,이름을 출력

-- solution 1: not a good answer
select b.dept_no
from employees a, dept_emp b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
and concat(first_name,' ' ,last_name) = 'Fai Bale';

select a.emp_no,a.first_name
from employees a, dept_emp b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
and b.dept_no = 'd004';

-- solution 2: use subquery
select a.emp_no,a.first_name
from employees a, dept_emp b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
and b.dept_no = (select b.dept_no
					from employees a, dept_emp b
					where a.emp_no = b.emp_no
					and b.to_date = '9999-01-01'
					and concat(first_name,' ' ,last_name) = 'Fai Bale');
                    
-- 단일행 연산자: =,>,<,>=,<=,<>,!=
-- 현재 전체 사원의 평균 연봉보다 적은 급여를 받는 사원의 이름과 급여를 출력
select avg(salary) from salaries where to_date = '9999-01-01';

select a.first_name,b.salary
from employees a, salaries b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
and b.salary < (select avg(salary)
					from salaries 
					where to_date = '9999-01-01')
order by b.salary desc;

-- 현재 가장 적은 평균 급여의 직책과 그 급여를 출력
select b.title,avg(a.salary)
from salaries a, titles b
where a.emp_no = b.emp_no
and a.to_date ='9999-01-01'
and b.to_date = '9999-01-01'
group by b.title
order by avg(a.salary)
limit 1;


select a.title,min(avg_salary)
from(select avg(a.salary) as avg_salary,b.title
from salaries a, titles b
where a.emp_no = b.emp_no
and a.to_date ='9999-01-01'
and b.to_date = '9999-01-01'
group by b.title) a;

select b.title,avg_salary
from salaries a, titles b
where a.emp_no = b.emp_no
and a.to_date ='9999-01-01'
and b.to_date = '9999-01-01'
group by b.title
having avg_salary = (select min(avg_salary)
						from(select avg(a.salary) as avg_salary,b.title
									from salaries a, titles b
									where a.emp_no = b.emp_no
									and a.to_date ='9999-01-01'
									and b.to_date = '9999-01-01'
									group by b.title) a);
                                    

-- 복수행 연산자: in,not in, 비교연산자any, 비교연산자 all

-- any 사용법
-- 1. =any: in
-- 2. >any, >=any : 최소값
-- 3. <any, <=any : 최대값
-- 4. != any, <>any : not in 

-- all 사용법
-- 1. =all :(x)
-- 2. >=all, >all : 최대값
-- 3. <= all, < all :최소값
-- 4. !=all,<>all

-- 현재 급여가 50000이상인 직원의 이름과 급여를 출력(급여 내림차순)
select a.first_name , b.salary
from employees a, salaries b
where a.emp_no = b.emp_no
and b.salary >= 50000
and b.to_date = '9999-01-01'
order by b.salary;

-- 현재 각 부서별로 최고 급여를 받고 있는 직원의 이름과 연봉을 출력
select b.dept_no,max(a.salary)
from salaries a , dept_emp b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by b.dept_no;

select c.dept_name,a.first_name,d.salary
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
								group by b.dept_no);


select d.dept_name,b.first_name,a.max
from (select b.dept_no,max(a.salary) as max
		from salaries a , dept_emp b
		where a.emp_no = b.emp_no
		and a.to_date = '9999-01-01'
		and b.to_date = '9999-01-01'
		group by b.dept_no) a,
		employees b,
        dept_emp c,
        deparments d,
        salaries e
where a.emp_no = b.emp_no
and b.emp_no = c.emp_no
and c.dept_no= d.dept_no
and e.emp_no = b.emp_no
and c.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by d.dept_name;

-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
select b.dept_no,max(a.salary)
from salaries a , dept_emp b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by b.dept_no;

select a.dept_no, max(b.salary)
  from dept_emp a, salaries b
 where a.emp_no = b.emp_no
   and a.to_date = '9999-01-01'
   and b.to_date = '9999-01-01'
group by a.dept_no;

select c.dept_name, a.first_name, c.dept_name,d.salary
  from employees a, dept_emp b, departments c, salaries d
 where a.emp_no = b.emp_no
   and b.dept_no = c.dept_no
   and a.emp_no = d.emp_no
   and b.to_date = '9999-01-01'
   and d.to_date = '9999-01-01'
   and (b.dept_no, d.salary) in (  select a.dept_no, max(b.salary)
								     from dept_emp a, salaries b
								    where a.emp_no = b.emp_no
                                      and a.to_date = '9999-01-01'
                                      and b.to_date = '9999-01-01'
                                 group by a.dept_no);
select *
from departments a, salary b
where a
