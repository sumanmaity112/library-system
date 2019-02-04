CREATE USER library_admin WITH PASSWORD 'password' NOCREATEROLE SUPERUSER;
CREATE DATABASE library_test WITH OWNER library_admin;
CREATE DATABASE library WITH OWNER library_admin;
