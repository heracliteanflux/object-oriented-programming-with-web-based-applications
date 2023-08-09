drop table schedules;
drop table students;
drop table courses;
drop table semesters;

create table semesters (
  semester varchar(100) not null primary key
);

create table courses (
  semester    varchar(100) not null,
  courseCode  varchar(100) not null,
  description varchar(100) not null,
  seats           int      not null,
  foreign key             (semester) references semesters (semester),
  primary key (courseCode, semester)
);

create table students (
  studentID varchar(100) not null primary key,
  firstName varchar(100) not null,
  lastName  varchar(100) not null
);

create table schedules (
  semester   varchar(100) not null,
  courseCode varchar(100) not null,
  studentID  varchar(100) not null,
  status        char(1)   not null,
  timestamp  timestamp    not null,
  foreign key             (semester) references semesters           (semester),
  foreign key (courseCode, semester) references courses (courseCode, semester),
  foreign key (studentID)            references students (studentID),
  primary key (studentID, courseCode, semester)
);
