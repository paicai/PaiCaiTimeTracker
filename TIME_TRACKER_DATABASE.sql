-- A script to create mysql database for Time Tracking at PAI/CAI
--
--
CREATE DATABASE TIME_TRACKING;

USE TIME_TRACKING;

create table USERS (
	USER_ID INT NOT NULL AUTO_INCREMENT,
	USERNAME VARCHAR(255) NOT NULL UNIQUE,
	PASSWORD VARCHAR(255) NOT NULL,
	FIRST_NAME VARCHAR(50) NOT NULL,
	LAST_NAME VARCHAR(50) NOT NULL,
	USER_ID_CONFIRM INT,
	USER_CREATED VARCHAR(255),
	TIME_CREATED DATETIME NOT NULL DEFAULT current_timestamp,
	USER_MODIFIED VARCHAR(255),
	TIME_MODIFIED DATETIME,
	PRIMARY KEY (USER_ID),
	FOREIGN KEY (USER_ID_CONFIRM) REFERENCES USERS(USER_ID)
);

create trigger users_ins before insert on USERS for each row set
new.USER_CREATED = USER();

create trigger users_upd before update on USERS for each row set
new.USER_MODIFIED = USER(),
new.TIME_MODIFIED = current_timestamp;

create table CHECK_IN (
	CHECK_IN_ID INT NOT NULL AUTO_INCREMENT,
	CHECK_IN_TIME DATETIME NOT NULL,
	CHECK_IN_TYPE VARCHAR(1) NOT NULL,	--'A' for arrived and 'L' for left
	USER_ID INT NOT NULL,
	USER_CREATED VARCHAR(255),
	TIME_CREATED DATETIME NOT NULL DEFAULT current_timestamp,
	USER_MODIFIED VARCHAR(255),
	TIME_MODIFIED DATETIME,
	PRIMARY KEY (CHECK_IN_ID),
	FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID)
);

create trigger check_in_ins before insert on CHECK_IN for each row set
new.USER_CREATED = USER();

create trigger check_in_upd before update on CHECK_IN for each row set
new.USER_MODIFIED = USER(),
new.TIME_MODIFIED = current_timestamp;
