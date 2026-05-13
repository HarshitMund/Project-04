create database project04;
use project04;

CREATE TABLE st_role (
    id BIGINT PRIMARY KEY,
    name VARCHAR(45),
    description VARCHAR(45),   
    created_by VARCHAR(45),
    modified_by VARCHAR(45),
    created_datetime DATETIME,
    modified_datetime DATETIME
);

SELECT * FROM ST_ROLE;

CREATE TABLE st_user (
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(45),
    last_name VARCHAR(45),
    login VARCHAR(45),
    password VARCHAR(45),
    dob DATETIME,
    mobile_no VARCHAR(45),
    role_id BIGINT,
    gender VARCHAR(45),
    created_by VARCHAR(45),
    modified_by VARCHAR(45),
    created_datetime DATETIME,
    modified_datetime DATETIME
);
SELECT * FROM ST_USER;

CREATE TABLE st_college (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255),
    state VARCHAR(45),
    city VARCHAR(45),
    phone_no VARCHAR(45),
    created_by VARCHAR(45),
    modified_by VARCHAR(45),
    created_datetime DATETIME,
    modified_datetime DATETIME
);

SELECT * FROM ST_COLLEGE;

CREATE TABLE st_student (
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(45),
    last_name VARCHAR(45),
    dob DATETIME,
    gender VARCHAR(45),
    mobile_no VARCHAR(45),
    email VARCHAR(255),
    college_id BIGINT,
    college_name VARCHAR(255),
    created_by VARCHAR(45),
    modified_by VARCHAR(45),
    created_datetime DATETIME,
    modified_datetime DATETIME
);

SELECT * FROM st_student;

CREATE TABLE st_marksheet (
    id BIGINT PRIMARY KEY,
    roll_no VARCHAR(45),
    student_id BIGINT,
    name VARCHAR(255),
    physics INTEGER,
    chemistry INTEGER,
    maths INTEGER,
    created_by VARCHAR(45),
    modified_by VARCHAR(45),
    created_datetime DATETIME,
    modified_datetime DATETIME
);

select * from st_marksheet;


CREATE TABLE st_course (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    duration VARCHAR(45),
    description  VARCHAR(45),     
    created_by VARCHAR(45),
    modified_by VARCHAR(45),
    created_datetime DATETIME,
    modified_datetime DATETIME
);

select * from st_course;

CREATE TABLE st_subject (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    course_id BIGINT,
    course_name VARCHAR(255),
    description  VARCHAR(45),
    created_by VARCHAR(45),
    modified_by VARCHAR(45),
    created_datetime DATETIME,
    modified_datetime DATETIME
);

CREATE TABLE st_timetable (
    id BIGINT PRIMARY KEY,
    semester VARCHAR(45),
    description  VARCHAR(255),
    exam_date DATETIME,
    exam_time VARCHAR(45),
    course_id BIGINT,
    course_name VARCHAR(255),
    subject_id BIGINT,
    subject_name VARCHAR(255),
    created_by VARCHAR(45),
    modified_by VARCHAR(45),
    created_datetime DATETIME,
    modified_datetime DATETIME
);


CREATE TABLE st_faculty (
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(45),
    last_name VARCHAR(45),
    dob DATETIME,
    gender VARCHAR(45),
    mobile_no VARCHAR(45),
    email VARCHAR(255),
    college_id BIGINT,
    college_name VARCHAR(255),
    course_id BIGINT,
    course_name VARCHAR(255),
    subject_id BIGINT,
    subject_name VARCHAR(255),
    created_by VARCHAR(45),
    modified_by VARCHAR(45),
    created_datetime DATETIME,
    modified_datetime DATETIME
);

use project04;

-- =========================================
-- INSERT DATA FOR ST_ROLE
-- =========================================

INSERT INTO st_role VALUES
(1,'Admin','System Administrator','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(2,'Student','Student Role','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(3,'College','College Management','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(4,'Kiosk','Kiosk Operator','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(5,'Faculty','Faculty Member','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00');

-- =========================================
-- INSERT DATA FOR ST_USER
-- =========================================

INSERT INTO st_user VALUES
(1,'Deepak','Sharma','admin@gmail.com','admin@123','1995-01-10','9876543210',1,'Male','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(2,'Aman','Verma','student1@gmail.com','test123','2001-03-12','9876543211',2,'Male','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(3,'Neha','Patel','college1@gmail.com','test123','1990-04-14','9876543212',3,'Female','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(4,'Raj','Singh','kiosk1@gmail.com','test123','1992-05-15','9876543213',4,'Male','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(5,'Priya','Joshi','faculty1@gmail.com','test123','1988-06-20','9876543214',5,'Female','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(6,'Rohit','Yadav','student2@gmail.com','test123','2000-02-11','9876543215',2,'Male','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(7,'Pooja','Mishra','faculty2@gmail.com','test123','1987-08-18','9876543216',5,'Female','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(8,'Karan','Dubey','college2@gmail.com','test123','1991-09-22','9876543217',3,'Male','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(9,'Simran','Jain','student3@gmail.com','test123','2002-12-01','9876543218',2,'Female','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(10,'Arjun','Tiwari','faculty3@gmail.com','test123','1985-07-07','9876543219',5,'Male','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(11,'Nisha','Gupta','student4@gmail.com','test123','2001-01-19','9876543220',2,'Female','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(12,'Vikas','Chouhan','kiosk2@gmail.com','test123','1993-03-21','9876543221',4,'Male','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(13,'Riya','Saxena','college3@gmail.com','test123','1994-11-13','9876543222',3,'Female','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(14,'Mohit','Rajput','student5@gmail.com','test123','2003-06-16','9876543223',2,'Male','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(15,'Kajal','Mehta','faculty4@gmail.com','test123','1989-10-25','9876543224',5,'Female','system','system','2026-05-12 10:00:00','2026-05-12 10:00:00');

-- =========================================
-- INSERT DATA FOR ST_COLLEGE
-- =========================================

INSERT INTO st_college VALUES
(1,'IPS Academy','AB Road','Madhya Pradesh','Indore','073140001','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(2,'Medicaps University','Bypass Road','Madhya Pradesh','Indore','073140002','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(3,'LNCT College','Kolar Road','Madhya Pradesh','Bhopal','075540003','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(4,'SIRT College','Ayodhya Bypass','Madhya Pradesh','Bhopal','075540004','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(5,'Acropolis Institute','Manglia','Madhya Pradesh','Indore','073140005','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00');

-- =========================================
-- INSERT DATA FOR ST_STUDENT
-- =========================================

INSERT INTO st_student VALUES
(1,'Aman','Verma','2001-03-12','Male','9876500001','aman@gmail.com',1,'IPS Academy','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(2,'Rohit','Yadav','2000-02-11','Male','9876500002','rohit@gmail.com',2,'Medicaps University','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(3,'Simran','Jain','2002-12-01','Female','9876500003','simran@gmail.com',3,'LNCT College','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(4,'Nisha','Gupta','2001-01-19','Female','9876500004','nisha@gmail.com',4,'SIRT College','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(5,'Mohit','Rajput','2003-06-16','Male','9876500005','mohit@gmail.com',5,'Acropolis Institute','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00');

-- =========================================
-- INSERT DATA FOR ST_MARKSHEET
-- =========================================

INSERT INTO st_marksheet VALUES
(1,'RN101',1,'Aman Verma',78,82,91,'admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(2,'RN102',2,'Rohit Yadav',88,74,69,'admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(3,'RN103',3,'Simran Jain',92,89,95,'admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(4,'RN104',4,'Nisha Gupta',67,72,81,'admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(5,'RN105',5,'Mohit Rajput',85,78,88,'admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00');

-- =========================================
-- INSERT DATA FOR ST_COURSE
-- =========================================

INSERT INTO st_course VALUES
(1,'BCA','3 Years','Computer Application','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(2,'MCA','2 Years','Master Application','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(3,'BTech','4 Years','Engineering Course','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(4,'MBA','2 Years','Management Course','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(5,'BSc','3 Years','Science Course','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00');

-- =========================================
-- INSERT DATA FOR ST_SUBJECT
-- =========================================

INSERT INTO st_subject VALUES
(1,'Java',1,'BCA','Programming','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(2,'Spring Boot',2,'MCA','Backend Framework','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(3,'Data Structure',3,'BTech','Algorithms','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(4,'Finance',4,'MBA','Accounts','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(5,'Physics',5,'BSc','Science Subject','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00');

-- =========================================
-- INSERT DATA FOR ST_TIMETABLE
-- =========================================

INSERT INTO st_timetable VALUES
(1,'Semester 1','Java Exam','2026-06-01 10:00:00','10:00 AM',1,'BCA',1,'Java','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(2,'Semester 2','Spring Exam','2026-06-03 11:00:00','11:00 AM',2,'MCA',2,'Spring Boot','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(3,'Semester 3','DS Exam','2026-06-05 09:00:00','09:00 AM',3,'BTech',3,'Data Structure','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(4,'Semester 1','Finance Exam','2026-06-07 12:00:00','12:00 PM',4,'MBA',4,'Finance','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(5,'Semester 2','Physics Exam','2026-06-09 01:00:00','01:00 PM',5,'BSc',5,'Physics','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00');

-- =========================================
-- INSERT DATA FOR ST_FACULTY
-- =========================================

INSERT INTO st_faculty VALUES
(1,'Priya','Joshi','1988-06-20','Female','9999900001','priya@gmail.com',1,'IPS Academy',1,'BCA',1,'Java','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(2,'Pooja','Mishra','1987-08-18','Female','9999900002','pooja@gmail.com',2,'Medicaps University',2,'MCA',2,'Spring Boot','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(3,'Arjun','Tiwari','1985-07-07','Male','9999900003','arjun@gmail.com',3,'LNCT College',3,'BTech',3,'Data Structure','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(4,'Kajal','Mehta','1989-10-25','Female','9999900004','kajal@gmail.com',4,'SIRT College',4,'MBA',4,'Finance','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(5,'Rahul','Sharma','1986-11-15','Male','9999900005','rahul@gmail.com',5,'Acropolis Institute',5,'BSc',5,'Physics','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00');







-- =====================================================
-- MORE 10 RECORDS FOR ST_COLLEGE
-- =====================================================

INSERT INTO st_college VALUES
(6,'Oriental College','Raisen Road','Madhya Pradesh','Bhopal','075540006','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(7,'Patel College','Airport Road','Madhya Pradesh','Indore','073140007','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(8,'RKDF University','Kasturba Nagar','Madhya Pradesh','Bhopal','075540008','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(9,'Sage University','Dewas Naka','Madhya Pradesh','Indore','073140009','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(10,'Truba College','Kolar Road','Madhya Pradesh','Bhopal','075540010','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(11,'IES College','Ratibad','Madhya Pradesh','Bhopal','075540011','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(12,'VIT College','Ring Road','Madhya Pradesh','Indore','073140012','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(13,'MIST College','Sehore Road','Madhya Pradesh','Bhopal','075540013','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(14,'Chameli Devi College','Vijay Nagar','Madhya Pradesh','Indore','073140014','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(15,'Technocrats Institute','Anand Nagar','Madhya Pradesh','Bhopal','075540015','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00');

-- =====================================================
-- MORE 10 RECORDS FOR ST_COURSE
-- =====================================================

INSERT INTO st_course VALUES
(6,'MTech','2 Years','Technical Course','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(7,'BCom','3 Years','Commerce Course','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(8,'MCom','2 Years','Master Commerce','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(9,'BA','3 Years','Arts Course','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(10,'MA','2 Years','Master Arts','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(11,'BBA','3 Years','Business Course','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(12,'LLB','3 Years','Law Course','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(13,'MBBS','5 Years','Medical Course','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(14,'Diploma CS','3 Years','Diploma Course','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(15,'PhD CS','4 Years','Research Course','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00');

-- =====================================================
-- MORE 10 RECORDS FOR ST_SUBJECT
-- =====================================================

INSERT INTO st_subject VALUES
(6,'Python',6,'MTech','Programming','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(7,'Accounting',7,'BCom','Commerce','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(8,'Business Law',8,'MCom','Law Subject','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(9,'History',9,'BA','Arts Subject','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(10,'Political Science',10,'MA','Theory Subject','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(11,'Marketing',11,'BBA','Management','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(12,'Constitution Law',12,'LLB','Legal Subject','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(13,'Anatomy',13,'MBBS','Medical Subject','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(14,'Networking',14,'Diploma CS','Computer Subject','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(15,'Machine Learning',15,'PhD CS','AI Subject','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00');

-- =====================================================
-- MORE 10 RECORDS FOR ST_STUDENT
-- =====================================================

INSERT INTO st_student VALUES
(6,'Rahul','Patidar','2001-02-14','Male','9000000001','rahul1@gmail.com',6,'Oriental College','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(7,'Sneha','Joshi','2002-03-16','Female','9000000002','sneha@gmail.com',7,'Patel College','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(8,'Aakash','Sharma','2000-07-18','Male','9000000003','aakash@gmail.com',8,'RKDF University','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(9,'Ritu','Verma','2003-05-20','Female','9000000004','ritu@gmail.com',9,'Sage University','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(10,'Vivek','Dubey','2001-09-22','Male','9000000005','vivek@gmail.com',10,'Truba College','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(11,'Anjali','Mishra','2002-10-12','Female','9000000006','anjali@gmail.com',11,'IES College','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(12,'Saurabh','Jain','2000-12-11','Male','9000000007','saurabh@gmail.com',12,'VIT College','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(13,'Payal','Gupta','2001-08-15','Female','9000000008','payal@gmail.com',13,'MIST College','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(14,'Nitin','Soni','2002-11-09','Male','9000000009','nitin@gmail.com',14,'Chameli Devi College','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(15,'Kritika','Rajput','2003-04-05','Female','9000000010','kritika@gmail.com',15,'Technocrats Institute','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00');

-- =====================================================
-- MORE 10 RECORDS FOR ST_MARKSHEET
-- =====================================================

INSERT INTO st_marksheet VALUES
(6,'RN106',6,'Rahul Patidar',78,67,88,'admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(7,'RN107',7,'Sneha Joshi',91,84,76,'admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(8,'RN108',8,'Aakash Sharma',65,71,69,'admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(9,'RN109',9,'Ritu Verma',88,92,95,'admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(10,'RN110',10,'Vivek Dubey',72,68,81,'admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(11,'RN111',11,'Anjali Mishra',93,89,90,'admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(12,'RN112',12,'Saurabh Jain',84,77,86,'admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(13,'RN113',13,'Payal Gupta',69,74,72,'admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(14,'RN114',14,'Nitin Soni',81,79,88,'admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(15,'RN115',15,'Kritika Rajput',95,94,97,'admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00');

-- =====================================================
-- MORE 10 RECORDS FOR ST_FACULTY
-- =====================================================

INSERT INTO st_faculty VALUES
(6,'Rakesh','Patel','1980-02-14','Male','9888800001','rakesh@gmail.com',6,'Oriental College',6,'MTech',6,'Python','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(7,'Meena','Jain','1982-03-16','Female','9888800002','meena@gmail.com',7,'Patel College',7,'BCom',7,'Accounting','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(8,'Ashok','Sharma','1979-07-18','Male','9888800003','ashok@gmail.com',8,'RKDF University',8,'MCom',8,'Business Law','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(9,'Pallavi','Verma','1985-05-20','Female','9888800004','pallavi@gmail.com',9,'Sage University',9,'BA',9,'History','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(10,'Lokesh','Dubey','1981-09-22','Male','9888800005','lokesh@gmail.com',10,'Truba College',10,'MA',10,'Political Science','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(11,'Swati','Mishra','1984-10-12','Female','9888800006','swati@gmail.com',11,'IES College',11,'BBA',11,'Marketing','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(12,'Mahesh','Jain','1978-12-11','Male','9888800007','mahesh@gmail.com',12,'VIT College',12,'LLB',12,'Constitution Law','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(13,'Kavita','Gupta','1983-08-15','Female','9888800008','kavita@gmail.com',13,'MIST College',13,'MBBS',13,'Anatomy','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(14,'Rohit','Soni','1986-11-09','Male','9888800009','rohitfaculty@gmail.com',14,'Chameli Devi College',14,'Diploma CS',14,'Networking','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(15,'Neetu','Rajput','1987-04-05','Female','9888800010','neetu@gmail.com',15,'Technocrats Institute',15,'PhD CS',15,'Machine Learning','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00');

-- =====================================================
-- MORE 10 RECORDS FOR ST_TIMETABLE
-- =====================================================

INSERT INTO st_timetable VALUES
(6,'Semester 1','Python Exam','2026-06-11 10:00:00','10:00 AM',6,'MTech',6,'Python','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(7,'Semester 2','Accounting Exam','2026-06-12 11:00:00','11:00 AM',7,'BCom',7,'Accounting','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(8,'Semester 3','Business Law Exam','2026-06-13 09:00:00','09:00 AM',8,'MCom',8,'Business Law','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(9,'Semester 1','History Exam','2026-06-14 12:00:00','12:00 PM',9,'BA',9,'History','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(10,'Semester 2','Political Science Exam','2026-06-15 01:00:00','01:00 PM',10,'MA',10,'Political Science','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(11,'Semester 3','Marketing Exam','2026-06-16 10:30:00','10:30 AM',11,'BBA',11,'Marketing','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(12,'Semester 1','Law Exam','2026-06-17 11:30:00','11:30 AM',12,'LLB',12,'Constitution Law','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(13,'Semester 2','Anatomy Exam','2026-06-18 09:30:00','09:30 AM',13,'MBBS',13,'Anatomy','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(14,'Semester 3','Networking Exam','2026-06-19 02:00:00','02:00 PM',14,'Diploma CS',14,'Networking','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00'),
(15,'Semester 4','ML Exam','2026-06-20 03:00:00','03:00 PM',15,'PhD CS',15,'Machine Learning','admin','admin','2026-05-12 10:00:00','2026-05-12 10:00:00');





