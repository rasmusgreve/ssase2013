CREATE DATABASE ssase13
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Danish_Denmark.1252'
       LC_CTYPE = 'Danish_Denmark.1252'
       CONNECTION LIMIT = -1;
GRANT CONNECT, TEMPORARY ON DATABASE ssase13 TO public;
GRANT ALL ON DATABASE ssase13 TO postgres;
GRANT CONNECT ON DATABASE ssase13 TO tomcat;

