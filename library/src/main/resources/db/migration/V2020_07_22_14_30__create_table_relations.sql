CREATE TABLE BOOK_AUTHOR(
    ID BIGINT PRIMARY KEY auto_increment,
    BOOK_ID BIGINT,
    AUTHOR_ID BIGINT,
    foreign key (BOOK_ID) references book(ID),
    foreign key (AUTHOR_ID) references AUTHOR(ID) );
CREATE TABLE BOOK_GENRE(
    ID BIGINT PRIMARY KEY auto_increment,
    BOOK_ID BIGINT,
    GENRE_ID BIGINT,
    foreign key (BOOK_ID) references book(ID),
    foreign key (GENRE_ID) references GENRE(ID) );
