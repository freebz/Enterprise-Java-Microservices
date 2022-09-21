# 예제 10-1 데이터베이스를 만들고 데이터 적재하기

mysql -h127.0.0.1 -P 32768 -uroot
create user 'cayambe'@'172.17.0.1' identified by 'cayambe';
grant all privileges on *.* to 'cayambe'@'172.17.0.1' with grant option;
create database cayambe;
use cayambe;
source \cayambe\sql\mysql.sql
source \cayambe\sql\test_data.sql
