CREATE DATABASE IF NOT EXISTS bibliotek;
USE bibliotek;

CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(50) NOT NULL,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL
);

CREATE TABLE books (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255) NOT NULL,
                       published_year INT,
                       available BOOLEAN DEFAULT TRUE
);

CREATE TABLE loans (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT,
                       book_id INT,
                       loan_date DATE,
                       return_date DATE,
                       FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                       FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

INSERT INTO users (username, password, name, email) VALUES
    ('anna', '1234', 'Anna Svensson', 'anna@mail.com');

INSERT INTO books (title, author, published_year, available) VALUES
                                                                 ('Djurfarmen', 'George Orwell', 1945, true),
                                                                 ('1984', 'George Orwell', 1949, true),
                                                                 ('Främlingen', 'Franz Kafka', 1915, true),
                                                                 ('Vita nätter', 'Fjodor Dostojevskij', 1848, true),
                                                                 ('Så talade Zarathustra', 'Friedrich Nietzsche', 1883, true);