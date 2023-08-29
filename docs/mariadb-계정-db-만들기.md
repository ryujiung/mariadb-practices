3. 사용자 생성

MariaDB [(none)] > create user 'webdb'@'localhost' identified by 'webdb';

4.권한주기

MariaDb [(none)] > grant all privileges on webdb.* to 'webdb'@'localhost';
MariaDb [(none)] > flush privileges;

5.확인하기
sh
# mysql -u webdb -D webdb -p