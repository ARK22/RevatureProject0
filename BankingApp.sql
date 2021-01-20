-- DDL to make tables
CREATE TABLE users (
user_id NUMBER(10) PRIMARY KEY,
username VARCHAR2(25) UNIQUE,
password VARCHAR2(100),
admin VARCHAR2(1)
CONSTRAINT sys_status CHECK (admin IN ('Y', 'N') )
); 
CREATE TABLE account(
name VARCHAR2(50),
owner_id NUMBER(10),
account_id NUMBER(10) PRIMARY KEY,
balance NUMBER(10)
);



------------------Procedures for the USER entity---------------------------------
CREATE OR REPLACE PROCEDURE register_users(username VARCHAR2,password VARCHAR2)
IS
BEGIN
INSERT INTO users VALUES(user_seq.nextval,username,password,'N');
END;
CREATE OR REPLACE PROCEDURE register_super_users(username VARCHAR2,password VARCHAR2)
IS
BEGIN
INSERT INTO users VALUES(user_seq.nextval,username,password,'Y');
END;
CREATE OR REPLACE PROCEDURE update_user_info(username_provided VARCHAR2, password_inp VARCHAR2,user_id_provided NUMBER)
IS
BEGIN
UPDATE users SET username = username_provided ,password = password_inp WHERE  user_id = user_id_provided;
END;
CREATE OR REPLACE PROCEDURE delete_users(provided_id NUMBER)
IS
BEGIN
DELETE users WHERE user_id = provided_id;
END;


------------------Procedures for the ACCOUNT entity ----------------------------
CREATE OR REPLACE PROCEDURE add_account(name_to_use VARCHAR2, owner NUMBER,balance NUMBER )
IS
BEGIN

INSERT INTO account VALUES(name_to_use,owner,account_seq.nextval,balance);
END;
CREATE OR REPLACE PROCEDURE update_account(name_to_use VARCHAR2, act_id_provided NUMBER )
IS
BEGIN
UPDATE account SET  name = name_to_use WHERE account_id = act_id_provided;
END;
CREATE OR REPLACE PROCEDURE delete_account(provided_id NUMBER)
IS
BEGIN
DELETE account WHERE account_id = provided_id;
END;
CREATE OR REPLACE PROCEDURE update_account_amount(act_id NUMBER,balance_given NUMBER)
IS
BEGIN
UPDATE account SET  balance = balance_given WHERE account_id = act_id ;
END;




--constraints for account
ALTER TABLE account ADD CONSTRAINT owner_ref FOREIGN KEY (owner_id) REFERENCES users(user_id) ON DELETE CASCADE;

--sequences to track ID for user, account.
CREATE SEQUENCE user_seq
    START WITH 1
    INCREMENT BY 1;
    
CREATE SEQUENCE account_seq
    START WITH 1
    INCREMENT BY 1;

SELECT * FROM users;
SELECT * FROM account ;

commit;