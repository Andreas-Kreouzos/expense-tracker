CREATE TABLE EXPENSE_TRACKER.USERS
(ID BIGINT AUTO_INCREMENT PRIMARY KEY,
USERNAME VARCHAR(255) NOT NULL UNIQUE,
PASSWORD VARCHAR(255) NOT NULL,
FIRSTNAME VARCHAR(255) NOT NULL,
LASTNAME VARCHAR(255) NOT NULL,
EMAIL VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE EXPENSE_TRACKER.EXPENSES
(ID BIGINT AUTO_INCREMENT PRIMARY KEY,
DESCRIPTION VARCHAR(255),
AMOUNT DECIMAL(10, 2),
DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
USER_ID BIGINT,
FOREIGN KEY (USER_ID) REFERENCES USERS (ID)
);
