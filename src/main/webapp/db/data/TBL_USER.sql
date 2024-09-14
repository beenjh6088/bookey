﻿SET DEFINE OFF;
--SQL Statement which produced this data:
--
--  SELECT *
--    FROM TBL_USER;
--
Insert into BOOKEY.TBL_USER
   (USERID, USERPW, EMAIL, NAME, ADDRESS, 
    IS_OPEN_TO_MARKETING, BIRTHDAY, GENDER, RANK, CREATED_DATE, 
    CREATED_USER, UPDATED_DATE)
 Values
   ('admin', '1234', 'admin@test.com', 'Admin', 'Dream City', 
    'Y', TO_DATE('2020/02/02', 'YYYY/MM/DD'), '1', 'A', TO_DATE('2024/09/01 오전 10:26:26', 'YYYY/MM/DD HH:MI:SS AM'), 
    'MANUAL', TO_DATE('2024/09/01 오전 10:26:26', 'YYYY/MM/DD HH:MI:SS AM'));
Insert into BOOKEY.TBL_USER
   (USERID, USERPW, EMAIL, NAME, ADDRESS, 
    IS_OPEN_TO_MARKETING, BIRTHDAY, GENDER, RANK, CREATED_DATE, 
    CREATED_USER, UPDATED_DATE)
 Values
   ('asdf', '1234', 'test@test.com', 'Testor', 'Nelkins', 
    'Y', TO_DATE('2023/03/04', 'YYYY/MM/DD'), '2', 'R', TO_DATE('2024/09/06 오후 1:49:24', 'YYYY/MM/DD HH:MI:SS AM'), 
    'MANUAL', TO_DATE('2024/09/06 오후 1:49:24', 'YYYY/MM/DD HH:MI:SS AM'));
Insert into BOOKEY.TBL_USER
   (USERID, USERPW, EMAIL, NAME, ADDRESS, 
    IS_OPEN_TO_MARKETING, BIRTHDAY, GENDER, RANK, AUTHNUM, 
    CREATED_DATE, CREATED_USER, UPDATED_DATE)
 Values
   ('qwer', '1234', 'beenjh6088@naver.com', 'Novin', 'Godam', 
    'Y', TO_DATE('2022/02/02', 'YYYY/MM/DD'), '2', 'R', 'test123', 
    TO_DATE('2024/09/01 오후 9:34:15', 'YYYY/MM/DD HH:MI:SS AM'), 'USER', TO_DATE('2024/09/01 오후 9:34:15', 'YYYY/MM/DD HH:MI:SS AM'));
Insert into BOOKEY.TBL_USER
   (USERID, USERPW, EMAIL, NAME, ADDRESS, 
    IS_OPEN_TO_MARKETING, BIRTHDAY, GENDER, RANK, AUTHNUM, 
    CREATED_DATE, CREATED_USER, UPDATED_DATE)
 Values
   ('dlaudtnr', '1234', 'rosa070815@naver.com', '이마리', '용인시 처인구', 
    'N', TO_DATE('2024/09/01', 'YYYY/MM/DD'), '1', 'R', '1236414', 
    TO_DATE('2024/09/02 오후 6:29:26', 'YYYY/MM/DD HH:MI:SS AM'), 'USER', TO_DATE('2024/09/02 오후 6:29:26', 'YYYY/MM/DD HH:MI:SS AM'));
Insert into BOOKEY.TBL_USER
   (USERID, USERPW, EMAIL, NAME, ADDRESS, 
    IS_OPEN_TO_MARKETING, BIRTHDAY, GENDER, RANK, AUTHNUM, 
    CREATED_DATE, CREATED_USER, UPDATED_DATE)
 Values
   ('zxcv', '1234', 'beenjh6088@naver.com', 'Nova', 'Super Nova', 
    'N', TO_DATE('2009/09/09', 'YYYY/MM/DD'), '1', 'R', '9529962', 
    TO_DATE('2024/09/07 오후 12:30:13', 'YYYY/MM/DD HH:MI:SS AM'), 'USER', TO_DATE('2024/09/07 오후 12:30:13', 'YYYY/MM/DD HH:MI:SS AM'));
Insert into BOOKEY.TBL_USER
   (USERID, USERPW, EMAIL, NAME, ADDRESS, 
    IS_OPEN_TO_MARKETING, BIRTHDAY, GENDER, RANK, AUTHNUM, 
    CREATED_DATE, CREATED_USER, UPDATED_DATE)
 Values
   ('1234', '1234', 'beenjh6088@naver.com', '1234', 'Supa Dupa', 
    'Y', TO_DATE('2021/09/30', 'YYYY/MM/DD'), '2', 'R', 'test123', 
    TO_DATE('2024/09/11 오전 10:15:16', 'YYYY/MM/DD HH:MI:SS AM'), 'USER', TO_DATE('2024/09/11 오전 10:15:16', 'YYYY/MM/DD HH:MI:SS AM'));
COMMIT;
