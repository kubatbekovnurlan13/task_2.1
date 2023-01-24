-- creation of table of Group
CREATE TABLE groups
(
    group_id   serial primary key,
    group_name varchar(50) not null
);

-- creation of table of Courses
CREATE TABLE courses
(
    course_id          serial primary key,
    course_name        varchar(50)  not null,
    course_description varchar(200) not null
);

-- creation of table of Students
CREATE TABLE students
(
    student_id serial primary key,
    group_id   int,
    first_name varchar(50) not null,
    last_name  varchar(50) not null,
    foreign key (group_id) references groups (group_id)
);

-- creation of table of Students_courses
CREATE TABLE students_courses
(
    student_id int not null,
    course_id  int not null,
    primary key (student_id, course_id),
    foreign key (student_id) references students (student_id),
    foreign key (course_id) references courses (course_id)
);