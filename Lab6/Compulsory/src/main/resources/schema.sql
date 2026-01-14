CREATE TABLE IF NOT EXISTS students (
    id SERIAL PRIMARY KEY,
    code VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    year INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS instructors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS packs (
    id SERIAL PRIMARY KEY,
    year INTEGER NOT NULL,
    semester INTEGER NOT NULL,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS courses (
    id SERIAL PRIMARY KEY,
    type VARCHAR(20) NOT NULL,
    code VARCHAR(20) UNIQUE NOT NULL,
    abbr VARCHAR(20),
    name VARCHAR(100) NOT NULL,
    instructor_id INTEGER REFERENCES instructors(id),
    pack_id INTEGER REFERENCES packs(id),
    group_count INTEGER,
    description TEXT
);

CREATE TABLE IF NOT EXISTS preferences (
    id SERIAL PRIMARY KEY,
    student_id INTEGER NOT NULL REFERENCES students(id),
    course_id INTEGER NOT NULL REFERENCES courses(id),
    pack_id INTEGER NOT NULL REFERENCES packs(id),
    rank INTEGER NOT NULL,
    tie_group INTEGER NOT NULL DEFAULT 0,
    version INTEGER NOT NULL DEFAULT 0,
    UNIQUE (student_id, course_id)
);
