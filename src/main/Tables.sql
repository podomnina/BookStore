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

INSERT INTO author (id,name) VALUES (author_seq.nextval,'����������� ����� ����������');
INSERT INTO author (id,name) VALUES (author_seq.nextval,'������ ��������� ���������');
INSERT INTO author (id,name) VALUES (author_seq.nextval,'���� ������');
INSERT INTO author (id,name) VALUES (author_seq.nextval,'����� ������');
INSERT INTO author (id,name) VALUES (author_seq.nextval,'�������� ������ �����������');

INSERT INTO book (id,name,pages,price,language,id_author) VALUES (book_seq.nextval,'������������ � ���������',608,200,'�������',1);
INSERT INTO book (id,name,pages,price,language,id_author) VALUES (book_seq.nextval,'������� ������',352,100,'�������',2);
INSERT INTO book (id,name,pages,price,language,id_author) VALUES (book_seq.nextval,'������� ������',640,250,'�������',5);
