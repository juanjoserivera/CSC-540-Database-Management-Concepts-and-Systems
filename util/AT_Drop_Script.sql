ALTER TABLE PAYMENT DROP CONSTRAINT PAYMENT_FK_EMPLOYEE_ID;

ALTER TABLE EMPLOYEE DROP CONSTRAINT EMPLOYEE_FK_SERVICE_CENTER_ID;

ALTER TABLE CAR DROP CONSTRAINT CAR_FK_CAR_TYPE_ID;

ALTER TABLE CAR DROP CONSTRAINT CAR_FK_CUSTOMER_ID;


DROP TABLE PAYMENT;

DROP TABLE EMPLOYEE;

DROP TABLE CUSTOMER;

DROP TABLE CAR;

DROP TABLE CAR_TYPE;
