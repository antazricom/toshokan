CREATE SEQUENCE artist_id_seq START WITH 1 INCREMENT BY 1 MINVALUE 1;
CREATE SEQUENCE vinyl_id_seq START WITH 1 INCREMENT BY 1 MINVALUE 1;
CREATE SEQUENCE author_id_seq START WITH 1 INCREMENT BY 1 MINVALUE 1;
CREATE SEQUENCE book_id_seq START WITH 1 INCREMENT BY 1 MINVALUE 1;
CREATE SEQUENCE category_id_seq START WITH 1 INCREMENT BY 1 MINVALUE 1;

CREATE TABLE category
(
    id         INTEGER NOT NULL SEQUENCE category_id_seq,
    uuid       VARCHAR NOT NULL,
    name       VARCHAR NOT NULL,
    type       VARCHAR NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT category_pk PRIMARY KEY (id)
);

CREATE TABLE artist
(
    id         INTEGER NOT NULL SEQUENCE artist_id_seq,
    uuid       VARCHAR NOT NULL,
    name       VARCHAR NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT artist_pk PRIMARY KEY (id)
);

CREATE TABLE vinyl
(
    id               INTEGER NOT NULL SEQUENCE vinyl_id_seq,
    uuid             VARCHAR NOT NULL,
    title            VARCHAR NOT NULL,
    publication_year INTEGER NOT NULL,
    artist_id        INTEGER NOT NULL,
    category_id      INTEGER NOT NULL,
    created_at       TIMESTAMP,
    updated_at       TIMESTAMP,
    CONSTRAINT vinyl_pk PRIMARY KEY (id)
);

ALTER TABLE vinyl
    ADD CONSTRAINT category_vinyl_fk
        FOREIGN KEY (category_id)
            REFERENCES category (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE vinyl
    ADD CONSTRAINT artist_vinyl_fk
        FOREIGN KEY (artist_id)
            REFERENCES artist (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

CREATE TABLE author
(
    id         INTEGER NOT NULL SEQUENCE author_id_seq,
    uuid       VARCHAR NOT NULL,
    firstname  VARCHAR NOT NULL,
    lastname   VARCHAR NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT author_pk PRIMARY KEY (id)
);

CREATE TABLE book
(
    id               INTEGER NOT NULL SEQUENCE book_id_seq,
    uuid             VARCHAR NOT NULL,
    title            VARCHAR NOT NULL,
    publication_year INTEGER NOT NULL,
    isbn             VARCHAR NOT NULL,
    author_id        INTEGER NOT NULL,
    category_id      INTEGER NOT NULL,
    created_at       TIMESTAMP,
    updated_at       TIMESTAMP,
    CONSTRAINT book_pk PRIMARY KEY (id)
);

ALTER TABLE book
    ADD CONSTRAINT category_book_fk
        FOREIGN KEY (category_id)
            REFERENCES category (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE book
    ADD CONSTRAINT author_book_fk
        FOREIGN KEY (author_id)
            REFERENCES author (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

/*DROP FUNCTION IF EXISTS created_at() CASCADE;
CREATE FUNCTION created_at() RETURNS TRIGGER
AS $$
BEGIN
    NEW.created_at = now();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION IF EXISTS updated_at() CASCADE;
CREATE FUNCTION updated_at() RETURNS TRIGGER
AS $$
BEGIN
    NEW.updated_at = now();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS category_create ON public.category;
CREATE TRIGGER category_create
    BEFORE INSERT ON public.category
    FOR EACH ROW EXECUTE PROCEDURE created_at();
DROP TRIGGER IF EXISTS category_update ON public.category;
CREATE TRIGGER category_update
    BEFORE UPDATE ON public.category
    FOR EACH ROW EXECUTE PROCEDURE updated_at();
DROP TRIGGER IF EXISTS author_create ON public.author;
CREATE TRIGGER author_create
    BEFORE INSERT ON public.author
    FOR EACH ROW EXECUTE PROCEDURE created_at();
DROP TRIGGER IF EXISTS author_update ON public.author;
CREATE TRIGGER author_update
    BEFORE UPDATE ON public.author
    FOR EACH ROW EXECUTE PROCEDURE updated_at();
DROP TRIGGER IF EXISTS artist_create ON public.artist;
CREATE TRIGGER artist_create
    BEFORE INSERT ON public.artist
    FOR EACH ROW EXECUTE PROCEDURE created_at();
DROP TRIGGER IF EXISTS artist_update ON public.artist;
CREATE TRIGGER artist_update
    BEFORE UPDATE ON public.artist
    FOR EACH ROW EXECUTE PROCEDURE updated_at();
DROP TRIGGER IF EXISTS vinyl_create ON public.vinyl;
CREATE TRIGGER vinyl_create
    BEFORE INSERT ON public.vinyl
    FOR EACH ROW EXECUTE PROCEDURE created_at();
DROP TRIGGER IF EXISTS vinyl_update ON public.vinyl;
CREATE TRIGGER vinyl_update
    BEFORE UPDATE ON public.vinyl
    FOR EACH ROW EXECUTE PROCEDURE updated_at();
DROP TRIGGER IF EXISTS book_create ON public.book;
CREATE TRIGGER book_create
    BEFORE INSERT ON public.book
    FOR EACH ROW EXECUTE PROCEDURE created_at();
DROP TRIGGER IF EXISTS book_update ON public.book;
CREATE TRIGGER book_update
    BEFORE UPDATE ON public.book
    FOR EACH ROW EXECUTE PROCEDURE updated_at();
*/