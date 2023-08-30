-- 현재 근무하고 있는 직원 사번,이름,직책을 모두 출력
select a.emp_no as 사번 ,a.first_name as 이름, b.title as 직책
from employees a, titles b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01';