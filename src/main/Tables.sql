CREATE TABLE book
(
  id_book    NUMBER,
  name         VARCHAR2(255),
  pages   NUMBER,
  price   NUMBER,
  language  VARCHAR2(255),
  id_author    NUMBER,
  CONSTRAINT book_pk PRIMARY KEY (id_book),
  CONSTRAINT author_fk FOREIGN KEY (id_author) REFERENCES author (id_author)
)
CREATE TABLE author
(
  id_author NUMBER,
  name VARCHAR2(255),
  CONSTRAINT author_pk PRIMARY KEY (id_author)
)

INSERT INTO author (id_author,name) VALUES (1,'����������� ����� ����������');
INSERT INTO author (id_author,name) VALUES (2,'������ ��������� ���������');
INSERT INTO author (id_author,name) VALUES (3,'���� ������');
INSERT INTO author (id_author,name) VALUES (4,'����� ������');
INSERT INTO author (id_author,name) VALUES (5,'�������� ������ �����������');

INSERT INTO book (id_book,name,pages,price,language,id_author) VALUES (1,'������������ � ���������',608,200,'�������',1);
INSERT INTO book (id_book,name,pages,price,language,id_author) VALUES (2,'������� ������',352,100,'�������',2);
INSERT INTO book (id_book,name,pages,price,language,id_author) VALUES (3,'������� ������',640,250,'�������',5);