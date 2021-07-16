
CREATE TABLE category (
                id IDENTITY NOT NULL,
                uuid VARCHAR NOT NULL,
                name VARCHAR NOT NULL,
                CONSTRAINT category_pk PRIMARY KEY (id)
);


CREATE TABLE author (
                id IDENTITY NOT NULL,
                uuid VARCHAR NOT NULL,
                firstname VARCHAR NOT NULL,
                lastname VARCHAR NOT NULL,
                CONSTRAINT author_pk PRIMARY KEY (id)
);


CREATE TABLE book (
                id INTEGER NOT NULL,
                uuid VARCHAR NOT NULL,
                title VARCHAR NOT NULL,
                publication_year INTEGER NOT NULL,
                synopsis VARCHAR NOT NULL,
                isbn VARCHAR NOT NULL,
                author_id INTEGER NOT NULL,
                category_id INTEGER NOT NULL,
                CONSTRAINT book_pk PRIMARY KEY (id)
);


CREATE TABLE note (
                id INTEGER NOT NULL,
                uuid VARCHAR NOT NULL,
                content VARCHAR NOT NULL,
                book_id INTEGER NOT NULL,
                CONSTRAINT note_pk PRIMARY KEY (id)
);


ALTER TABLE book ADD CONSTRAINT category_book_fk
FOREIGN KEY (category_id)
REFERENCES category (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE book ADD CONSTRAINT author_book_fk
FOREIGN KEY (author_id)
REFERENCES author (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE note ADD CONSTRAINT book_note_fk
FOREIGN KEY (book_id)
REFERENCES book (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

CREATE SEQUENCE author_id_seq;
CREATE SEQUENCE note_id_seq;
CREATE SEQUENCE book_id_seq;
CREATE SEQUENCE category_id_seq;