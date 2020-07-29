CREATE TABLE COMMENT(
    ID BIGINT PRIMARY KEY auto_increment,
    TEXT VARCHAR(3000),
     BOOK_ID BIGINT,
     foreign key (BOOK_ID) references book(ID) ON DELETE CASCADE);