/*Open SQL Plus OS and log in as Administrator in  */
sqlplus / as sysdba

/*Create tablespace and temp tablespaces*/
select tablespace_name from dba_tablespaces;
create tablespace odcAccess_ts datafile 'odcAccess.dat' size 10M autoextend on;
create temporary tablespace odcAccess_ts_temp tempfile 'odcAccess_temp.dat' size 5M autoextend on;
select tablespace_name from dba_tablespaces;

/*Create user and grant permissions*/
create user odcAccess identified by odcAccess default tablespace odcAccess_ts temporary tablespace odcAccess_ts_temp;
grant connect,resource to odcAccess;
grant create session to odcAccess;
grant unlimited tablespace to odcAccess;
select username from dba_users;

/*Exit from sqlplus and Login as odcAccess and check what privileges he has:*/
select * from session_privs;

/* Create Application Tables */
create table Employee(
  item_code number(10) not null,
  item_name varchar(50),
  price number DEFAULT 0,
  qty number DEFAULT 0,
  createdOn date,
  CONSTRAINT item_code_pk PRIMARY KEY (item_code)
);

select * from user_tables;

CREATE SEQUENCE employee_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
 


select * from employee;
  
