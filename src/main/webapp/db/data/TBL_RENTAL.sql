﻿SET DEFINE OFF;
--SQL Statement which produced this data:
--
--  SELECT *
--    FROM TBL_RENTAL;
--
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    STATUS, CREATED_DATE, CREATED_USER)
 Values
   (1, 'A0000001', 'zxcv', TO_DATE('2024/08/18', 'YYYY/MM/DD'), TO_DATE('2024/09/01', 'YYYY/MM/DD'), 
    'R', TO_DATE('2024/09/08 1:40:10', 'YYYY/MM/DD HH:MI:SS AM'), 'MANUAL');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (2, 'A0000001', 'qwer', TO_DATE('2024/09/03', 'YYYY/MM/DD'), TO_DATE('2024/09/17', 'YYYY/MM/DD'), 
    'R', TO_DATE('2024/09/03 10:08:38', 'YYYY/MM/DD HH:MI:SS AM'), 'MANUAL', TO_DATE('2024/09/09 1:41:18', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    RETURN_DATE, STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, 
    UPDATED_USER)
 Values
   (3, 'A0000001', 'dlaudtnr', TO_DATE('2024/09/09 11:24:50', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/23 11:24:50', 'YYYY/MM/DD HH:MI:SS AM'), 
    TO_DATE('2024/09/11 2:59:27', 'YYYY/MM/DD HH:MI:SS AM'), 'R', TO_DATE('2024/09/06 1:45:05', 'YYYY/MM/DD HH:MI:SS AM'), 'MANUAL', TO_DATE('2024/09/11 2:59:27', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, STATUS, CREATED_DATE, 
    CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (4, 'A0000001', 'asdf', 'T', TO_DATE('2024/09/06 1:50:55', 'YYYY/MM/DD HH:MI:SS AM'), 
    'MANUAL', TO_DATE('2024/09/11 2:56:26', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    STATUS, CREATED_DATE, CREATED_USER)
 Values
   (5, 'A0000007', 'asdf', TO_DATE('2024/09/08', 'YYYY/MM/DD'), TO_DATE('2024/09/22', 'YYYY/MM/DD'), 
    'G', TO_DATE('2024/09/08 1:39:03', 'YYYY/MM/DD HH:MI:SS AM'), 'MANUAL');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    RETURN_DATE, STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, 
    UPDATED_USER)
 Values
   (8, 'A0000013', 'qwer', TO_DATE('2024/09/09 12:47:43', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/23 12:47:43', 'YYYY/MM/DD HH:MI:SS AM'), 
    TO_DATE('2024/09/10 8:27:57', 'YYYY/MM/DD HH:MI:SS AM'), 'R', TO_DATE('2024/09/09 12:47:43', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM', TO_DATE('2024/09/10 8:27:57', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    RETURN_DATE, STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, 
    UPDATED_USER)
 Values
   (6, 'A0000008', 'qwer', TO_DATE('2024/09/08 3:07:51', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/22 3:07:51', 'YYYY/MM/DD HH:MI:SS AM'), 
    TO_DATE('2024/09/09 6:04:40', 'YYYY/MM/DD HH:MI:SS AM'), 'R', TO_DATE('2024/09/08 3:07:51', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM', TO_DATE('2024/09/09 6:04:40', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, STATUS, CREATED_DATE, 
    CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (7, 'A0000001', 'qwer', 'T', TO_DATE('2024/09/08 7:25:17', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM', TO_DATE('2024/09/11 2:57:31', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, STATUS, CREATED_DATE, 
    CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (11, 'A0000001', 'asdf', 'T', TO_DATE('2024/09/10 1:05:39', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM', TO_DATE('2024/09/11 2:27:15', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    RETURN_DATE, STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, 
    UPDATED_USER)
 Values
   (9, 'A0000013', 'zxcv', TO_DATE('2024/09/10 8:28:37', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/24 8:28:37', 'YYYY/MM/DD HH:MI:SS AM'), 
    TO_DATE('2024/09/11 2:22:52', 'YYYY/MM/DD HH:MI:SS AM'), 'R', TO_DATE('2024/09/09 12:55:16', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM', TO_DATE('2024/09/11 2:22:52', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (10, 'A0000013', 'asdf', TO_DATE('2024/09/11 2:23:54', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/25 2:23:54', 'YYYY/MM/DD HH:MI:SS AM'), 
    'G', TO_DATE('2024/09/09 12:55:45', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM', TO_DATE('2024/09/11 2:23:54', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    RETURN_DATE, STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, 
    UPDATED_USER)
 Values
   (12, 'A0000008', 'qwer', TO_DATE('2024/09/11 9:35:29', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/25 9:35:29', 'YYYY/MM/DD HH:MI:SS AM'), 
    TO_DATE('2024/09/11 9:35:47', 'YYYY/MM/DD HH:MI:SS AM'), 'R', TO_DATE('2024/09/11 9:35:29', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM', TO_DATE('2024/09/11 9:35:47', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, QUEUE, CREATED_DATE, 
    CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (13, 'A0000013', '1234', 1, TO_DATE('2024/09/11 10:15:36', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM', TO_DATE('2024/09/11 1:51:45', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, STATUS, CREATED_DATE, 
    CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (14, 'A0000013', 'qwer', 'T', TO_DATE('2024/09/11 1:44:47', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM', TO_DATE('2024/09/11 1:49:59', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, STATUS, CREATED_DATE, 
    CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (16, 'A0000013', 'qwer', 'T', TO_DATE('2024/09/11 1:50:30', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM', TO_DATE('2024/09/11 1:51:45', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, QUEUE, CREATED_DATE, 
    CREATED_USER)
 Values
   (15, 'A0000007', 'qwer', 1, TO_DATE('2024/09/11 1:45:48', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (18, 'A0000001', 'zxcv', TO_DATE('2024/09/11 3:00:06', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/25 3:00:06', 'YYYY/MM/DD HH:MI:SS AM'), 
    'G', TO_DATE('2024/09/11 2:26:36', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM', TO_DATE('2024/09/11 3:00:06', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, STATUS, CREATED_DATE, 
    CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (17, 'A0000013', 'qwer', 'T', TO_DATE('2024/09/11 1:54:57', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM', TO_DATE('2024/09/11 2:21:43', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, STATUS, CREATED_DATE, 
    CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (19, 'A0000001', 'asdf', 'T', TO_DATE('2024/09/11 2:57:03', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM', TO_DATE('2024/09/11 2:58:05', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, QUEUE, CREATED_DATE, 
    CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (20, 'A0000001', 'qwer', 1, TO_DATE('2024/09/11 2:58:42', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM', TO_DATE('2024/09/11 3:00:06', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, QUEUE, CREATED_DATE, 
    CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (21, 'A0000001', 'asdf', 2, TO_DATE('2024/09/11 2:58:57', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM', TO_DATE('2024/09/11 3:00:06', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, QUEUE, CREATED_DATE, 
    CREATED_USER)
 Values
   (22, 'A0000013', 'asdf', 2, TO_DATE('2024/09/11 3:03:37', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, QUEUE, CREATED_DATE, 
    CREATED_USER)
 Values
   (23, 'A0000007', 'asdf', 2, TO_DATE('2024/09/11 5:46:11', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    RETURN_DATE, STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, 
    UPDATED_USER)
 Values
   (24, 'A0000008', 'asdf', TO_DATE('2024/09/11 6:07:07', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/25 6:07:07', 'YYYY/MM/DD HH:MI:SS AM'), 
    TO_DATE('2024/09/12 2:41:06', 'YYYY/MM/DD HH:MI:SS AM'), 'R', TO_DATE('2024/09/11 6:07:07', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM', TO_DATE('2024/09/12 2:41:06', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    STATUS, CREATED_DATE, CREATED_USER)
 Values
   (25, 'A0000014', 'asdf', TO_DATE('2024/09/11 6:07:12', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/25 6:07:12', 'YYYY/MM/DD HH:MI:SS AM'), 
    'G', TO_DATE('2024/09/11 6:07:12', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    STATUS, CREATED_DATE, CREATED_USER)
 Values
   (26, 'A0000011', 'qwer', TO_DATE('2024/09/11 6:11:10', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/25 6:11:10', 'YYYY/MM/DD HH:MI:SS AM'), 
    'G', TO_DATE('2024/09/11 6:11:10', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, QUEUE, CREATED_DATE, 
    CREATED_USER)
 Values
   (27, 'A0000011', 'asdf', 1, TO_DATE('2024/09/11 6:11:27', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, STATUS, CREATED_DATE, 
    CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (28, 'A0000008', 'asdf', 'T', TO_DATE('2024/09/12 2:39:53', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM', TO_DATE('2024/09/12 2:40:28', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    RETURN_DATE, STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, 
    UPDATED_USER)
 Values
   (29, 'A0000008', 'asdf', TO_DATE('2024/09/12 2:45:33', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/26 2:45:33', 'YYYY/MM/DD HH:MI:SS AM'), 
    TO_DATE('2024/09/12 2:48:09', 'YYYY/MM/DD HH:MI:SS AM'), 'R', TO_DATE('2024/09/12 2:45:33', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM', TO_DATE('2024/09/12 2:48:09', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    RETURN_DATE, STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, 
    UPDATED_USER)
 Values
   (31, 'A0000008', 'asdf', TO_DATE('2024/09/12 2:53:15', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/26 2:53:15', 'YYYY/MM/DD HH:MI:SS AM'), 
    TO_DATE('2024/09/12 2:54:03', 'YYYY/MM/DD HH:MI:SS AM'), 'R', TO_DATE('2024/09/12 2:53:15', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM', TO_DATE('2024/09/12 2:54:03', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    RETURN_DATE, STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, 
    UPDATED_USER)
 Values
   (30, 'A0000008', 'asdf', TO_DATE('2024/09/12 2:52:42', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/26 2:52:42', 'YYYY/MM/DD HH:MI:SS AM'), 
    TO_DATE('2024/09/12 2:53:00', 'YYYY/MM/DD HH:MI:SS AM'), 'R', TO_DATE('2024/09/12 2:52:42', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM', TO_DATE('2024/09/12 2:53:00', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    RETURN_DATE, STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, 
    UPDATED_USER)
 Values
   (35, 'A0000008', 'asdf', TO_DATE('2024/09/12 3:23:16', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/26 3:23:16', 'YYYY/MM/DD HH:MI:SS AM'), 
    TO_DATE('2024/09/12 3:23:50', 'YYYY/MM/DD HH:MI:SS AM'), 'R', TO_DATE('2024/09/12 3:23:16', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM', TO_DATE('2024/09/12 3:23:50', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    RETURN_DATE, STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, 
    UPDATED_USER)
 Values
   (34, 'A0000008', 'asdf', TO_DATE('2024/09/12 3:22:52', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/26 3:22:52', 'YYYY/MM/DD HH:MI:SS AM'), 
    TO_DATE('2024/09/12 3:23:00', 'YYYY/MM/DD HH:MI:SS AM'), 'R', TO_DATE('2024/09/12 3:22:52', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM', TO_DATE('2024/09/12 3:23:00', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    RETURN_DATE, STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, 
    UPDATED_USER)
 Values
   (32, 'A0000008', 'asdf', TO_DATE('2024/09/12 2:57:16', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/26 2:57:16', 'YYYY/MM/DD HH:MI:SS AM'), 
    TO_DATE('2024/09/12 2:58:54', 'YYYY/MM/DD HH:MI:SS AM'), 'R', TO_DATE('2024/09/12 2:57:16', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM', TO_DATE('2024/09/12 2:58:54', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    RETURN_DATE, STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, 
    UPDATED_USER)
 Values
   (33, 'A0000008', 'asdf', TO_DATE('2024/09/12 2:59:01', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/26 2:59:01', 'YYYY/MM/DD HH:MI:SS AM'), 
    TO_DATE('2024/09/12 2:59:54', 'YYYY/MM/DD HH:MI:SS AM'), 'R', TO_DATE('2024/09/12 2:58:45', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM', TO_DATE('2024/09/12 2:59:54', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, RENTAL_DATE, DUE_DATE, 
    STATUS, CREATED_DATE, CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (36, 'A0000008', 'qwer', TO_DATE('2024/09/12 3:24:26', 'YYYY/MM/DD HH:MI:SS AM'), TO_DATE('2024/09/26 3:24:26', 'YYYY/MM/DD HH:MI:SS AM'), 
    'G', TO_DATE('2024/09/12 3:23:29', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM', TO_DATE('2024/09/12 3:24:26', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, STATUS, CREATED_DATE, 
    CREATED_USER, UPDATED_DATE, UPDATED_USER)
 Values
   (37, 'A0000008', 'zxcv', 'T', TO_DATE('2024/09/12 3:24:15', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM', TO_DATE('2024/09/12 3:24:36', 'YYYY/MM/DD HH:MI:SS AM'), 'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, QUEUE, CREATED_DATE, 
    CREATED_USER)
 Values
   (38, 'A0000008', 'zxcv', 1, TO_DATE('2024/09/12 3:24:43', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
Insert into BOOKEY.TBL_RENTAL
   (RENTALID, BOOKID, USERID, QUEUE, CREATED_DATE, 
    CREATED_USER)
 Values
   (39, 'A0000008', 'zxcv', 2, TO_DATE('2024/09/12 8:20:48', 'YYYY/MM/DD HH:MI:SS AM'), 
    'SYSTEM');
COMMIT;
