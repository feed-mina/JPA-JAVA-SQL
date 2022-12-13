show tables;

select * from employees;

# 칼럼의 합성 : concat을 통해 칼럼 두개를 합칠수 있다.
SELECT concat (first_name, ' ', last_name) AS 이름,
		hire_date AS 입사일
	FROM employees;

# 중복값 제거 : DISTNCT
SELECT DISTINCT manager_id FROM employees;

# WHERE 절 
SELECT * FROM employees WHERE hire_date > '1989-06-17' ORDER BY hire_date;

SELECT * FROM employees ;

SELECT * FROM employees WHERE commission_pct is null;


SELECT * FROM employees WHERE commission_pct is not null;

# 패턴매칭 
# MySQL 기본적으로 제공하는 것 
# 표준 SQL pattern matching / 정규 표현식 pattern matching

SELECT * FROM employees WHERE first_name like "b%";

# first_name 이 5글자 _ 가 5번
SELECT * FROM employees WHERE first_name like "_____";

# or 과 in 은 같다
SELECT * FROM employees WHERE department_id in (90,100,110);

# 문자형 함수 : Ucase, upper
SELECT UCASE(last_name) from employees;
SELECT upper(last_name) from employees;

# 문자형 함수 : LCASE, LOWER
SELECT LCASE(last_name) from employees;
SELECT LOWER(last_name) from employees;

# 문자형 함수 : SUBSTRING
SELECT SUBSTRING('happy day',4,2);
SELECT SUBSTRING(first_name,1,1) FROM employees;

# 두번째 방식이 sql 튜닝 방식에서 더 좋다.
SELECT * FROM EMPLOYEES WHERE SUBSTRING(first_name,1,1) = 'A';
SELECT * FROM employees WHERE first_name like 'A%';

# 좌변을 변화시키는 경우 성능을 떨어트릴 수 있다.
SELECT concat(first_name, '',last_name) AS 이름,
		hire_date AS 입사일
	FROM employees
WHERE substring(hire_date,1,4)='1989';

SELECT concat(first_name, '',last_name) AS 이름,
	hire_date AS 입사일
FROM employees
WHERE hire_date like '1989%';


# 실행계획 살펴보기
EXPLAIN SELECT concat(first_name, '',last_name) AS 이름,
		hire_date AS 입사일
	FROM employees
WHERE substring(hire_date,1,4)='1989';

EXPLAIN SELECT concat(first_name, '',last_name) AS 이름,
	hire_date AS 입사일
FROM employees
WHERE hire_date like '1989%';

CREATE INDEX employees_hire_date_idx ON employees (hire_date);
EXPLAIN SELECT concat(first_name, ' ', last_name) AS 이름,
	hire_date AS 입사일
FROM employees
WHERE substring(hire_date, 1, 4) = '1989';

EXPLAIN SELECT concat(first_name, ' ', last_name) AS 이름,
	hire_date AS 입사일
FROM employees
WHERE hire_date like '1989%';

# 데이터가 너무 적을때는 인덱스를 만들때 오히려 속도가 느릴 수 있다. 인덱스를 만들때는 데이터가 많을때 생긴다. 첫번째 케이스에 possible_keys 에는 null 이나 
# 두번째 케이스에 possible_keys 가 employees_hire_date_idx로 인덱스를 타는 것을 확인할 수 있다.

