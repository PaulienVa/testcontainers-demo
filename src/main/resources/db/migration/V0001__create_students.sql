CREATE SEQUENCE  IF NOT EXISTS student_id_seq AS integer INCREMENT BY 50 START 1;
CREATE  TABLE IF NOT EXISTS students(
    student_id             integer primary key,
    name                   text not null,
    year_of_registration   int not null,
    main_course            text
);