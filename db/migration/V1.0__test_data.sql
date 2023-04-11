CREATE TABLE IF NOT EXISTS books (
	id bigint NOT NULL,
	caption varchar NOT NULL,
	description varchar NULL,
	genre varchar NULL,
	"year" varchar(8) NULL,
	CONSTRAINT books_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS authors (
	id bigint NOT NULL,
	"name" varchar NOT NULL,
	CONSTRAINT authors_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS author_book (
    book_id bigint NOT NULL,
    author_id bigint NOT NULL,
    CONSTRAINT book_author_pk PRIMARY KEY (book_id, author_id),
    CONSTRAINT book_author_book_id_fk FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE,
    CONSTRAINT book_author_author_id_fk FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE
);


CREATE SEQUENCE public.hibernate_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;