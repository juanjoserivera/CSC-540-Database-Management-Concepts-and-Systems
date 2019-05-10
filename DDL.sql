-- tables
-- Table: BASIC_SERVICE
CREATE TABLE BASIC_SERVICE (
    ID number(10,0)  NOT NULL,
    EXPECTED_HOURS number(10,0)  NOT NULL,
    WARRANTY_PERIOD number(10,0)  NOT NULL,
    CHARGE_RATE_ID number(10)  NOT NULL,
    CONSTRAINT BASIC_SERVICE_pk PRIMARY KEY (ID)
) ;

-- Table: BSXMS
CREATE TABLE BSXMS (
    BASIC_SERVICE_ID number(10,0)  NOT NULL,
    MAINTENANCE_SERVICE_ID number(10,0)  NOT NULL,
    CONSTRAINT BSXMS_pk PRIMARY KEY (BASIC_SERVICE_ID,MAINTENANCE_SERVICE_ID)
) ;

-- Table: BSXRS
CREATE TABLE BSXRS (
    BASIC_SERVICE_ID number(10,0)  NOT NULL,
    REPAIR_SERVICE_ID number(10,0)  NOT NULL,
    CONSTRAINT BSXRS_pk PRIMARY KEY (BASIC_SERVICE_ID,REPAIR_SERVICE_ID)
) ;

-- Table: CAR
CREATE TABLE CAR (
    PLATE_NUMBER number(10)  NOT NULL,
    LAST_SERVICE_MILEAGE number(10)  NULL,
    CUSTOMER_ID number(10,0)  NOT NULL,
    CAR_TYPE_ID number(10,0)  NOT NULL,
    LAST_SERVICE_DATE date  NULL,
    LAST_SERVICE_TYPE varchar2(20)  NULL,
    CONSTRAINT CAR_pk PRIMARY KEY (PLATE_NUMBER)
) ;

-- Table: CAR_TYPE
CREATE TABLE CAR_TYPE (
    ID number(10,0)  NOT NULL,
    MAKE varchar2(20)  NOT NULL,
    MODEL varchar2(20)  NOT NULL,
    YEAR number(4,0)  NOT NULL,
    CONSTRAINT CAR_TYPE_pk PRIMARY KEY (ID)
) ;

-- Table: CAUSE
CREATE TABLE CAUSE (
    ID number(10,0)  NOT NULL,
    DEFINITION varchar2(20)  NOT NULL,
    CONSTRAINT CAUSE_pk PRIMARY KEY (ID)
) ;

-- Table: CAUSEXREPAIR_SERVICE
CREATE TABLE CAUSEXREPAIR_SERVICE (
    CAUSE_ID number(10,0)  NOT NULL,
    REPAIR_SERVICE_ID number(10,0)  NOT NULL,
    CONSTRAINT CAUSEXREPAIR_SERVICE_pk PRIMARY KEY (CAUSE_ID,REPAIR_SERVICE_ID)
) ;

-- Table: CHARGE_RATE
CREATE TABLE CHARGE_RATE (
    ID number(10)  NOT NULL,
    TYPE varchar2(20)  NOT NULL,
    COST_PER_HOUR number(10)  NOT NULL,
    CONSTRAINT CHARGE_RATE_pk PRIMARY KEY (ID)
) ;

-- Table: CUSTOMER
CREATE TABLE CUSTOMER (
    ID number(10,0)  NOT NULL,
    NAME varchar2(20)  NOT NULL,
    ADDRESS varchar2(20)  NOT NULL,
    EMAIL varchar2(20)  NOT NULL,
    PHONE_NUMBER number(10,0)  NOT NULL,
    CONSTRAINT CUSTOMER_pk PRIMARY KEY (ID)
) ;

-- Table: CUSTOMERXINVOICE
CREATE TABLE CUSTOMERXINVOICE (
    CUSTOMER_ID number(10,0)  NOT NULL,
    INVOICE_ID number(10,0)  NOT NULL,
    CONSTRAINT CUSTOMERXINVOICE_pk PRIMARY KEY (CUSTOMER_ID,INVOICE_ID)
) ;

-- Table: EMPLOYEE
CREATE TABLE EMPLOYEE (
    ID number(10)  NOT NULL,
    SERVICE_CENTER_ID number(10)  NOT NULL,
    NAME varchar2(20)  NOT NULL,
    ADDRESS varchar2(50)  NOT NULL,
    EMAIL varchar2(20)  NOT NULL,
    PHONE number(10)  NOT NULL,
    FREQUENCY varchar2(10)  NOT NULL,
    ROLE varchar2(20)  NOT NULL,
    WAGES number(10)  NOT NULL,
    START_DATE date  NOT NULL,
    CONSTRAINT EMPLOYEE_pk PRIMARY KEY (ID)
) ;

-- Table: INVOICE
CREATE TABLE INVOICE (
    ID number(10,0)  NOT NULL,
    "DATE" date  NOT NULL,
    TOTAL_COST number(10,2)  NOT NULL,
    STATUS varchar2(20)  NOT NULL,
    CONSTRAINT INVOICE_pk PRIMARY KEY (ID)
) ;

-- Table: MAINTENANCE_SERVICE
CREATE TABLE MAINTENANCE_SERVICE (
    ID number(10,0)  NOT NULL,
    TYPE varchar2(20)  NOT NULL,
    MILEAGE number(10,0)  NOT NULL,
    NUMBER_OF_MONTHS number(10,0)  NOT NULL,
    CAR_TYPE_ID number(10,0)  NOT NULL,
    CONSTRAINT MAINTENANCE_SERVICE_pk PRIMARY KEY (ID)
) ;

-- Table: PART
CREATE TABLE PART (
    ID number(10)  NOT NULL,
    NAME varchar2(20)  NULL,
    UNIT_PRICE number(10)  NULL,
    MIN_ORDER_QTY number(10)  NULL,
    DELIVERY_TIME number(10)  NULL,
    CONSTRAINT PART_PK PRIMARY KEY (ID)
) ;

-- Table: PARTXBASIC_SERVICE
CREATE TABLE PARTXBASIC_SERVICE (
    BASIC_SERVICE_ID number(10,0)  NOT NULL,
    PART_ID number(10)  NOT NULL,
    CONSTRAINT PARTXBASIC_SERVICE_pk PRIMARY KEY (BASIC_SERVICE_ID)
) ;

-- Table: PART_ORDER
CREATE TABLE PART_ORDER (
    ID number(10)  NOT NULL,
    QTY number(10)  NULL,
    EXPECTED_DATE date  NULL,
    EXPECTED_TIME date  NULL,
    DELIVERY_DATE date  NULL,
    DELIVERY_TIME date  NULL,
    COST number(10)  NULL,
    STATUS varchar2(10)  NULL,
    FROM_SERVICE_ID number(10)  NULL,
    TO_ID number(10)  NULL,
    PART_ID number(10)  NOT NULL,
    FROM_SUPPLIER_ID number(10)  NOT NULL,
    CONSTRAINT PART_ORDER_SUP_PK PRIMARY KEY (ID)
) ;

-- Table: PART_SUPPLIER
CREATE TABLE PART_SUPPLIER (
    ID number(10)  NOT NULL,
    PART_ID number(10)  NOT NULL,
    CONSTRAINT PART_SUP_PK PRIMARY KEY (ID)
) ;

-- Table: PAYMENT
CREATE TABLE PAYMENT (
    ID number(10)  NOT NULL,
    EMPLOYEE_ID number(10)  NOT NULL,
    PAYMENT_DATE date  NOT NULL,
    COMP_FREQUENCY number(10)  NOT NULL,
    START_DATE date  NOT NULL,
    END_DATE date  NOT NULL,
    HOURS_OR_DAYS_WORKED number(10)  NOT NULL,
    EARNINGS_YEAR_TO_DATE number(10)  NOT NULL,
    EARNINGS number(5)  NOT NULL,
    COMPENSATION number(5)  NOT NULL,
    CONSTRAINT PAYMENT_pk PRIMARY KEY (ID)
) ;

-- Table: REPAIR_SERVICE
CREATE TABLE REPAIR_SERVICE (
    ID number(10,0)  NOT NULL,
    PROBLEM varchar2(20)  NOT NULL,
    CONSTRAINT REPAIR_SERVICE_pk PRIMARY KEY (ID)
) ;

-- Table: SERVICE_CENTER
CREATE TABLE SERVICE_CENTER (
    ID number(10)  NOT NULL,
    NAME varchar2(20)  NULL,
    ADDRESS varchar2(50)  NULL,
    PHONE number(10)  NULL,
    STATE_ID number(10)  NULL,
    STATES_ID number(10)  NOT NULL,
    CONSTRAINT SC_PK PRIMARY KEY (ID)
) ;

-- Table: SERVICE_CENTER_INVENTORY
CREATE TABLE SERVICE_CENTER_INVENTORY (
    SERVICE_CENTER_ID number(10)  NOT NULL,
    PART_ID number(10)  NOT NULL,
    FREE_QTY number(10)  NULL,
    BOOKED_QTY number(10)  NULL,
    ORDER_THRESHOLD number(10)  NULL,
    CONSTRAINT SC_INV_PK PRIMARY KEY (SERVICE_CENTER_ID,PART_ID)
) ;

-- Table: STATES
CREATE TABLE STATES (
    ID number(10)  NOT NULL,
    NAME varchar2(20)  NULL,
    CONSTRAINT STATES_PK PRIMARY KEY (ID)
) ;

-- Table: WORK_APPOINTMENT
CREATE TABLE WORK_APPOINTMENT (
    ID number(10)  NOT NULL,
    TOTAL_COST number(10)  NOT NULL,
    STATUS varchar2(20)  NOT NULL,
    START_DATE date  NOT NULL,
    END_DATE date  NOT NULL,
    CAR_ID number(10)  NOT NULL,
    REPAIR_SERVICE_ID number(10,0)  NOT NULL,
    MAINTENANCE_SERVICE_ID number(10,0)  NOT NULL,
    SERVICE_CENTRE_ID number(10)  NOT NULL,
    CONSTRAINT WORK_APPOINTMENT_pk PRIMARY KEY (ID)
) ;

-- Table: WORK_APPOINTMENT_INVOICE
CREATE TABLE WORK_APPOINTMENT_INVOICE (
    INVOICE_ID number(10,0)  NOT NULL,
    WORK_APPOINTMENT_ID number(10)  NOT NULL,
    CONSTRAINT WORK_APPOINTMENT_INVOICE_pk PRIMARY KEY (INVOICE_ID)
) ;

-- Table: WORK_SCHEDULE
CREATE TABLE WORK_SCHEDULE (
    WORK_APPOINTMENT_ID number(10)  NOT NULL,
    EMPLOYEE_ID number(10)  NOT NULL,
    BASIC_SERVICE_COST number(10)  NOT NULL,
    EXPECTED_TIME number(10)  NOT NULL,
    TIME_SPENT number(10)  NOT NULL,
    TIME_SLOT_START number(10)  NOT NULL,
    BASIC_SERVICE_STATUS varchar2(20)  NOT NULL,
    "DATE" date  NOT NULL,
    BASIC_SERVICE_ID number(10,0)  NOT NULL,
    CONSTRAINT WORK_SCHEDULE_pk PRIMARY KEY (WORK_APPOINTMENT_ID,EMPLOYEE_ID)
) ;

-- foreign keys
-- Reference: FK_APPOINTMENT_CAR (table: WORK_APPOINTMENT)
ALTER TABLE WORK_APPOINTMENT ADD CONSTRAINT FK_APPOINTMENT_CAR
    FOREIGN KEY (CAR_ID)
    REFERENCES CAR (PLATE_NUMBER);

-- Reference: FK_APPOINTMENT_INVOICE (table: WORK_APPOINTMENT_INVOICE)
ALTER TABLE WORK_APPOINTMENT_INVOICE ADD CONSTRAINT FK_APPOINTMENT_INVOICE
    FOREIGN KEY (WORK_APPOINTMENT_ID)
    REFERENCES WORK_APPOINTMENT (ID);

-- Reference: FK_APPOINTMENT_M_SERVICE (table: WORK_APPOINTMENT)
ALTER TABLE WORK_APPOINTMENT ADD CONSTRAINT FK_APPOINTMENT_M_SERVICE
    FOREIGN KEY (MAINTENANCE_SERVICE_ID)
    REFERENCES MAINTENANCE_SERVICE (ID);

-- Reference: FK_APPOINTMENT_REPAIR_SERVICE (table: WORK_APPOINTMENT)
ALTER TABLE WORK_APPOINTMENT ADD CONSTRAINT FK_APPOINTMENT_REPAIR_SERVICE
    FOREIGN KEY (REPAIR_SERVICE_ID)
    REFERENCES REPAIR_SERVICE (ID);

-- Reference: FK_APPOINTMENT_SERVICE_CENTER (table: WORK_APPOINTMENT)
ALTER TABLE WORK_APPOINTMENT ADD CONSTRAINT FK_APPOINTMENT_SERVICE_CENTER
    FOREIGN KEY (SERVICE_CENTRE_ID)
    REFERENCES SERVICE_CENTER (ID);

-- Reference: FK_BASIC_SERVICE_CHARGE_RATE (table: BASIC_SERVICE)
ALTER TABLE BASIC_SERVICE ADD CONSTRAINT FK_BASIC_SERVICE_CHARGE_RATE
    FOREIGN KEY (CHARGE_RATE_ID)
    REFERENCES CHARGE_RATE (ID);

-- Reference: FK_BSOXMSO_BASIC_SERVICE (table: BSXMS)
ALTER TABLE BSXMS ADD CONSTRAINT FK_BSOXMSO_BASIC_SERVICE
    FOREIGN KEY (BASIC_SERVICE_ID)
    REFERENCES BASIC_SERVICE (ID);

-- Reference: FK_BSXMS_MAINTENANCE_SERVICE (table: BSXMS)
ALTER TABLE BSXMS ADD CONSTRAINT FK_BSXMS_MAINTENANCE_SERVICE
    FOREIGN KEY (MAINTENANCE_SERVICE_ID)
    REFERENCES MAINTENANCE_SERVICE (ID);

-- Reference: FK_BSXRSO_BASIC_SERVICE (table: BSXRS)
ALTER TABLE BSXRS ADD CONSTRAINT FK_BSXRSO_BASIC_SERVICE
    FOREIGN KEY (BASIC_SERVICE_ID)
    REFERENCES BASIC_SERVICE (ID);

-- Reference: FK_BSXRS_REPAIR_SERVICE (table: BSXRS)
ALTER TABLE BSXRS ADD CONSTRAINT FK_BSXRS_REPAIR_SERVICE
    FOREIGN KEY (REPAIR_SERVICE_ID)
    REFERENCES REPAIR_SERVICE (ID);

-- Reference: FK_CAR_CAR_TYPE (table: CAR)
ALTER TABLE CAR ADD CONSTRAINT FK_CAR_CAR_TYPE
    FOREIGN KEY (CAR_TYPE_ID)
    REFERENCES CAR_TYPE (ID);

-- Reference: FK_CAR_CUSTOMER (table: CAR)
ALTER TABLE CAR ADD CONSTRAINT FK_CAR_CUSTOMER
    FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (ID);

-- Reference: FK_CAUSEXREPAIR_SERVICE_CAUSE (table: CAUSEXREPAIR_SERVICE)
ALTER TABLE CAUSEXREPAIR_SERVICE ADD CONSTRAINT FK_CAUSEXREPAIR_SERVICE_CAUSE
    FOREIGN KEY (CAUSE_ID)
    REFERENCES CAUSE (ID);

-- Reference: FK_CAUSEXREPAIR_SERVICE_RS (table: CAUSEXREPAIR_SERVICE)
ALTER TABLE CAUSEXREPAIR_SERVICE ADD CONSTRAINT FK_CAUSEXREPAIR_SERVICE_RS
    FOREIGN KEY (REPAIR_SERVICE_ID)
    REFERENCES REPAIR_SERVICE (ID);

-- Reference: FK_CUSTOMERXINVOICE_CUSTOMER (table: CUSTOMERXINVOICE)
ALTER TABLE CUSTOMERXINVOICE ADD CONSTRAINT FK_CUSTOMERXINVOICE_CUSTOMER
    FOREIGN KEY (CUSTOMER_ID)
    REFERENCES CUSTOMER (ID);

-- Reference: FK_CUSTOMERXINVOICE_INVOICE (table: CUSTOMERXINVOICE)
ALTER TABLE CUSTOMERXINVOICE ADD CONSTRAINT FK_CUSTOMERXINVOICE_INVOICE
    FOREIGN KEY (INVOICE_ID)
    REFERENCES INVOICE (ID);

-- Reference: FK_EMPLOYEE_SERVICE_CENTER (table: EMPLOYEE)
ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_SERVICE_CENTER
    FOREIGN KEY (SERVICE_CENTER_ID)
    REFERENCES SERVICE_CENTER (ID);

-- Reference: FK_INVENTORY_PART (table: SERVICE_CENTER_INVENTORY)
ALTER TABLE SERVICE_CENTER_INVENTORY ADD CONSTRAINT FK_INVENTORY_PART
    FOREIGN KEY (PART_ID)
    REFERENCES PART (ID);

-- Reference: FK_INVENTORY_SERVICE_CENTER (table: SERVICE_CENTER_INVENTORY)
ALTER TABLE SERVICE_CENTER_INVENTORY ADD CONSTRAINT FK_INVENTORY_SERVICE_CENTER
    FOREIGN KEY (SERVICE_CENTER_ID)
    REFERENCES SERVICE_CENTER (ID);

-- Reference: FK_MAINT_SERVI_OFFE_CAR_TYPE (table: MAINTENANCE_SERVICE)
ALTER TABLE MAINTENANCE_SERVICE ADD CONSTRAINT FK_MAINT_SERVI_OFFE_CAR_TYPE
    FOREIGN KEY (CAR_TYPE_ID)
    REFERENCES CAR_TYPE (ID);

-- Reference: FK_PARTXBASIC_SERVICE_BS (table: PARTXBASIC_SERVICE)
ALTER TABLE PARTXBASIC_SERVICE ADD CONSTRAINT FK_PARTXBASIC_SERVICE_BS
    FOREIGN KEY (BASIC_SERVICE_ID)
    REFERENCES BASIC_SERVICE (ID);

-- Reference: FK_PARTXBASIC_SERVICE_PART (table: PARTXBASIC_SERVICE)
ALTER TABLE PARTXBASIC_SERVICE ADD CONSTRAINT FK_PARTXBASIC_SERVICE_PART
    FOREIGN KEY (PART_ID)
    REFERENCES PART (ID);

-- Reference: FK_PART_ORDER_PART (table: PART_ORDER)
ALTER TABLE PART_ORDER ADD CONSTRAINT FK_PART_ORDER_PART
    FOREIGN KEY (PART_ID)
    REFERENCES PART (ID);

-- Reference: FK_PART_ORDER_PART_SUPPLIER (table: PART_ORDER)
ALTER TABLE PART_ORDER ADD CONSTRAINT FK_PART_ORDER_PART_SUPPLIER
    FOREIGN KEY (FROM_SUPPLIER_ID)
    REFERENCES PART_SUPPLIER (ID);

-- Reference: FK_PART_SUPPLIER_PART (table: PART_SUPPLIER)
ALTER TABLE PART_SUPPLIER ADD CONSTRAINT FK_PART_SUPPLIER_PART
    FOREIGN KEY (PART_ID)
    REFERENCES PART (ID);

-- Reference: FK_PAYMENT_EMPLOYEE (table: PAYMENT)
ALTER TABLE PAYMENT ADD CONSTRAINT FK_PAYMENT_EMPLOYEE
    FOREIGN KEY (EMPLOYEE_ID)
    REFERENCES EMPLOYEE (ID);

-- Reference: FK_SERVICE_CENTER_STATES (table: SERVICE_CENTER)
ALTER TABLE SERVICE_CENTER ADD CONSTRAINT FK_SERVICE_CENTER_STATES
    FOREIGN KEY (STATES_ID)
    REFERENCES STATES (ID);

-- Reference: FK_WORK_APPOINTMENTXINVOICE_I (table: WORK_APPOINTMENT_INVOICE)
ALTER TABLE WORK_APPOINTMENT_INVOICE ADD CONSTRAINT FK_WORK_APPOINTMENTXINVOICE_I
    FOREIGN KEY (INVOICE_ID)
    REFERENCES INVOICE (ID);

-- Reference: FK_WORK_SCHEDULE_APPOINTMENT (table: WORK_SCHEDULE)
ALTER TABLE WORK_SCHEDULE ADD CONSTRAINT FK_WORK_SCHEDULE_APPOINTMENT
    FOREIGN KEY (WORK_APPOINTMENT_ID)
    REFERENCES WORK_APPOINTMENT (ID);

-- Reference: FK_WORK_SCHEDULE_BASIC_SERVICE (table: WORK_SCHEDULE)
ALTER TABLE WORK_SCHEDULE ADD CONSTRAINT FK_WORK_SCHEDULE_BASIC_SERVICE
    FOREIGN KEY (BASIC_SERVICE_ID)
    REFERENCES BASIC_SERVICE (ID);

-- Reference: FK_WORK_SCHEDULE_EMPLOYEE (table: WORK_SCHEDULE)
ALTER TABLE WORK_SCHEDULE ADD CONSTRAINT FK_WORK_SCHEDULE_EMPLOYEE
    FOREIGN KEY (EMPLOYEE_ID)
    REFERENCES EMPLOYEE (ID);

-- End of file.
