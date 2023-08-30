select lower('SEOUL'),lcase('sEoUl') from dual;
use employees;
-- lower
select lower(first_name) from employees;

-- substring(문자열,index,length)
select substring('Hello World',3,2) from dual;

-- employees 테이블에서 1989년에 입사한 직원들의 이름,입사일을 출력
select first_name, hire_date
from employees
where substring(hire_date,1,4) = '1989';

-- lpad, rpad
select lpad('1234',10,'-') from dual; 
select rpad('1234',10,'-') from dual;
-- 직원들의 월급을 오른쪽 정렬
select lpad(salary,10,'*')
from salaries;

-- trim ltrim rtrim
select concat('---',ltrim('   hello   '),'---'),
	   concat('---',rtrim('   hello   '),'---'),
       concat('---',trim(both 'x' from 'xxxhelloxxx'),'---')
from dual;

-- length
select length('Hello World') from dual;
