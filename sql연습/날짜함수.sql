-- 날짜함수 

-- curdate(), current_date
select curdate(), current_date() from dual;

-- now() vs sysdate()
select now(),sysdate() from dual;

select now(),sleep(2),now() from dual;
select now(),sleep(2),sysdate() from dual;

-- date_format
select date_format(now(),'%Y년 %m월 %d일 %h시 %i분 %s초') from dual;

-- period_diff
-- 근무개월 
select first_name,hire_date,period_diff(date_format(curdate(),'%y%m'),date_format(hire_date,'%y%m')) as month
from employees;

-- date_add(adddate) data_sub(=subdate)
-- 날짜를 date 타입이 칼럼이나 값에 type(year,month,day)의 표현식으로 더하거나 뺄 수 있다. 
-- 사원의 근속 년 수가 5년이 되는 날에 휴가를 보내준다면 휴가날을 출력 

select first_name,hire_date,date_add(hire_date, interval 5 year)
from employees;

-- cast
select '12345' + 10, cast('12345' as int ) + 10 from dual;
select date_format('2023-08-30','%Y년 %m월 %d일') from dual;
select cast(cast(1-2 as unsigned) as signed) from dual;

-- type