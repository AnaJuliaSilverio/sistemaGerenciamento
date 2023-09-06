CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    teachers_id BIGINT NOT NULL,
    CONSTRAINT fk_courses_teachers FOREIGN KEY (teachers_id) REFERENCES teachers(id)
);

CREATE TABLE registration (
    id SERIAL PRIMARY KEY,
    students_id BIGINT NOT NULL,
    courses_id BIGINT NOT NULL,
    registration_date DATE NOT NULL,
    CONSTRAINT fk_registration_students FOREIGN KEY (students_id) REFERENCES students(id) ,
    CONSTRAINT fk_registration_courses FOREIGN KEY (courses_id) REFERENCES courses(id)
);

CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    address TEXT NOT NULL
);
CREATE TABLE teachers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
