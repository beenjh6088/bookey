﻿SET DEFINE OFF;
--SQL Statement which produced this data:
--
--  SELECT *
--    FROM TBL_MASTER;
--
Insert into BOOKEY.TBL_MASTER
   (TABLE_NAME, COLUMN_NAME, CODE, VALUE, CREATED_DATE, 
    CREATED_USER)
 Values
   ('TBL_BOOK', 'APPERANCE', 'S', 'Best', TO_DATE('2024/09/04 오후 12:42:06', 'YYYY/MM/DD HH:MI:SS AM'), 
    'MANUAL');
Insert into BOOKEY.TBL_MASTER
   (TABLE_NAME, COLUMN_NAME, CODE, VALUE, CREATED_DATE, 
    CREATED_USER)
 Values
   ('TBL_BOOK', 'APPERANCE', 'A', 'Good', TO_DATE('2024/09/04 오후 12:42:17', 'YYYY/MM/DD HH:MI:SS AM'), 
    'MANUAL');
Insert into BOOKEY.TBL_MASTER
   (TABLE_NAME, COLUMN_NAME, CODE, VALUE, CREATED_DATE, 
    CREATED_USER)
 Values
   ('TBL_BOOK', 'APPERANCE', 'B', 'Normal', TO_DATE('2024/09/04 오후 12:42:34', 'YYYY/MM/DD HH:MI:SS AM'), 
    'MANUAL');
Insert into BOOKEY.TBL_MASTER
   (TABLE_NAME, COLUMN_NAME, CODE, VALUE, CREATED_DATE, 
    CREATED_USER)
 Values
   ('TBL_BOOK', 'APPERANCE', 'C', 'Bad', TO_DATE('2024/09/04 오후 12:43:01', 'YYYY/MM/DD HH:MI:SS AM'), 
    'MANUAL');
Insert into BOOKEY.TBL_MASTER
   (TABLE_NAME, COLUMN_NAME, CODE, VALUE, CREATED_DATE, 
    CREATED_USER)
 Values
   ('TBL_RENTAL', 'STATUS', 'G', 'Ongoing', TO_DATE('2024/09/04 오후 12:43:28', 'YYYY/MM/DD HH:MI:SS AM'), 
    'MANUAL');
Insert into BOOKEY.TBL_MASTER
   (TABLE_NAME, COLUMN_NAME, CODE, VALUE, CREATED_DATE, 
    CREATED_USER)
 Values
   ('TBL_RENTAL', 'STATUS', 'D', 'Overdue', TO_DATE('2024/09/04 오후 12:43:57', 'YYYY/MM/DD HH:MI:SS AM'), 
    'MANUAL');
Insert into BOOKEY.TBL_MASTER
   (TABLE_NAME, COLUMN_NAME, CODE, VALUE, CREATED_DATE, 
    CREATED_USER)
 Values
   ('TBL_RENTAL', 'STATUS', 'R', 'Returned', TO_DATE('2024/09/04 오후 12:44:12', 'YYYY/MM/DD HH:MI:SS AM'), 
    'MANUAL');
Insert into BOOKEY.TBL_MASTER
   (TABLE_NAME, COLUMN_NAME, CODE, VALUE, CREATED_DATE, 
    CREATED_USER)
 Values
   ('TBL_LOCATION', 'PLACE_NO', 'PLC00001', 'Library A', TO_DATE('2024/09/06 오전 9:58:47', 'YYYY/MM/DD HH:MI:SS AM'), 
    'MANUAL');
Insert into BOOKEY.TBL_MASTER
   (TABLE_NAME, COLUMN_NAME, CODE, VALUE, CREATED_DATE, 
    CREATED_USER)
 Values
   ('TBL_BOOK', 'STATUS', 'A', 'Available', TO_DATE('2024/09/06 오전 11:04:22', 'YYYY/MM/DD HH:MI:SS AM'), 
    'MANUAL');
Insert into BOOKEY.TBL_MASTER
   (TABLE_NAME, COLUMN_NAME, CODE, VALUE, CREATED_DATE, 
    CREATED_USER)
 Values
   ('TBL_BOOK', 'STATUS', 'R', 'Reserved', TO_DATE('2024/09/06 오전 11:04:58', 'YYYY/MM/DD HH:MI:SS AM'), 
    'MANUAL');
Insert into BOOKEY.TBL_MASTER
   (TABLE_NAME, COLUMN_NAME, CODE, VALUE, CREATED_DATE, 
    CREATED_USER)
 Values
   ('TBL_BOOK', 'STATUS', 'C', 'Check Out', TO_DATE('2024/09/06 오전 11:05:39', 'YYYY/MM/DD HH:MI:SS AM'), 
    'MANUAL');
Insert into BOOKEY.TBL_MASTER
   (TABLE_NAME, COLUMN_NAME, CODE, VALUE, CREATED_DATE, 
    CREATED_USER)
 Values
   ('TBL_RENTAL', 'STATUS', 'T', 'Terminated', TO_DATE('2024/09/10 오후 12:43:12', 'YYYY/MM/DD HH:MI:SS AM'), 
    'MANUAL');
COMMIT;
