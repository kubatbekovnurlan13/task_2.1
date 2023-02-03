-- creation of testTable
create table if not exists testTable
(
    testTable_id   serial primary key,
    testTable_name varchar(50) not null
);