CREATE TABLE author
(
  id NUMBER,
  name VARCHAR2(255),
  CONSTRAINT id_pk PRIMARY KEY (id)
);
CREATE TABLE book
(
  id    NUMBER,
  name         VARCHAR2(255),
  pages   NUMBER,
  price   NUMBER,
  language  VARCHAR2(255),
  id_author    NUMBER,
  CONSTRAINT id_book_pk PRIMARY KEY (id),
  CONSTRAINT id_author_fk FOREIGN KEY (id_author) REFERENCES author (id)
);
CREATE TABLE customer
(
  id NUMBER,
  name VARCHAR2(255),
  email VARCHAR2(255),
  CONSTRAINT id_customer_pk PRIMARY KEY (id)
);
CREATE TABLE booking
(
  id NUMBER,
  id_customer NUMBER,
  id_book NUMBER,
  CONSTRAINT id_booking_pk PRIMARY KEY(id),
  CONSTRAINT id_customer_fk FOREIGN KEY (id_customer) REFERENCES customer (id),
  CONSTRAINT id_book_fk FOREIGN KEY (id_book) REFERENCES book (id)
);

CREATE SEQUENCE author_seq
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCACHE;

CREATE SEQUENCE book_seq
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCACHE;

CREATE SEQUENCE customer_seq
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCACHE;

CREATE SEQUENCE booking_seq
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCACHE;

INSERT INTO author (id,name) VALUES (author_seq.nextval,'Достоевский Федор Михайлович');
INSERT INTO author (id,name) VALUES (author_seq.nextval,'Пушкин Александр Сергеевич');
INSERT INTO author (id,name) VALUES (author_seq.nextval,'Джек Лондон');
INSERT INTO author (id,name) VALUES (author_seq.nextval,'Агата Кристи');
INSERT INTO author (id,name) VALUES (author_seq.nextval,'Булгаков Михаил Афанасьевич');

INSERT INTO book (id,name,pages,price,language,id_author) VALUES (book_seq.nextval,'Преступление и наказание',608,200,'русский',1);
INSERT INTO book (id,name,pages,price,language,id_author) VALUES (book_seq.nextval,'Евгений Онегин',352,100,'русский',2);
INSERT INTO book (id,name,pages,price,language,id_author) VALUES (book_seq.nextval,'Собачье сердце',640,250,'русский',5);
