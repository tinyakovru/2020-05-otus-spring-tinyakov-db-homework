insert into author (`first_name`,`middle_name`,`last_name`) values ('Александр', 'Сергеевич', 'Пушкин');
insert into author (`first_name`,`middle_name`,`last_name`) values ('Сергей', 'Александрович', 'Есенин');
insert into author (`first_name`,`middle_name`,`last_name`) values ('Владимир', 'Владимирович', 'Маяковский');
insert into author (`first_name`,`middle_name`,`last_name`) values ('Агата', '', 'Кристи');
insert into author (`first_name`,`middle_name`,`last_name`) values ('Стивен', '', 'Кинг');
insert into author (`first_name`,`middle_name`,`last_name`) values ('Илья', 'Арнольдович', 'Ильф');
insert into author (`first_name`,`middle_name`,`last_name`) values ('Евгений', 'Петрович', 'Петров');

insert into genre (`title`) values ('детская литература');
insert into genre (`title`) values ('проза');
insert into genre (`title`) values ('поэзия');
insert into genre (`title`) values ('драма');
insert into genre (`title`) values ('исторический роман');
insert into genre (`title`) values ('детектив');
insert into genre (`title`) values ('фантастика');
insert into genre (`title`) values ('триллер');
insert into genre (`title`) values ('ужасы');
insert into genre (`title`) values ('комедия');

insert into book (`title`) values ('Сказка о попе и работнике его балде');
insert into book (`title`) values ('Капитанская дочка');
insert into book (`title`) values ('Евгений Онегин');

insert into book (`title`) values ('Москва кабацкая');
insert into book (`title`) values ('Анна Снегина');

insert into book (`title`) values ('Что такое хорошо и что такое плохо');
insert into book (`title`) values ('Облако в штанах');

insert into book (`title`) values ('Десять негритят');
insert into book (`title`) values ('Убийство в восточном экспрессе');

insert into book (`title`) values ('Ловец снов');
insert into book (`title`) values ('Сияние');
insert into book (`title`) values ('Оно');

insert into book (`title`) values ('Двенадцать стульев');
insert into book (`title`) values ('Золотой телёнок');


insert into book_author (`book_id`, `author_id`) values (1,1);
insert into book_author (`book_id`, `author_id`) values (2,1);
insert into book_author (`book_id`, `author_id`) values (3,1);

insert into book_author (`book_id`, `author_id`) values (4,2);
insert into book_author (`book_id`, `author_id`) values (5,2);

insert into book_author (`book_id`, `author_id`) values (6,3);
insert into book_author (`book_id`, `author_id`) values (7,3);

insert into book_author (`book_id`, `author_id`) values (8,4);
insert into book_author (`book_id`, `author_id`) values (9,4);

insert into book_author (`book_id`, `author_id`) values (10,5);
insert into book_author (`book_id`, `author_id`) values (11,5);
insert into book_author (`book_id`, `author_id`) values (12,5);

insert into book_author (`book_id`, `author_id`) values (13,6);
insert into book_author (`book_id`, `author_id`) values (14,6);
insert into book_author (`book_id`, `author_id`) values (13,7);
insert into book_author (`book_id`, `author_id`) values (14,7);

insert into book_genre (`book_id`, `genre_id`) values (1,1);
insert into book_genre (`book_id`, `genre_id`) values (1,3);

insert into book_genre (`book_id`, `genre_id`) values (2,2);
insert into book_genre (`book_id`, `genre_id`) values (2,4);
insert into book_genre (`book_id`, `genre_id`) values (2,5);

insert into book_genre (`book_id`, `genre_id`) values (3,3);
insert into book_genre (`book_id`, `genre_id`) values (3,4);

insert into book_genre (`book_id`, `genre_id`) values (4,3);
insert into book_genre (`book_id`, `genre_id`) values (5,3);

insert into book_genre (`book_id`, `genre_id`) values (6,1);
insert into book_genre (`book_id`, `genre_id`) values (6,3);

insert into book_genre (`book_id`, `genre_id`) values (7,3);

insert into book_genre (`book_id`, `genre_id`) values (8,2);
insert into book_genre (`book_id`, `genre_id`) values (8,6);

insert into book_genre (`book_id`, `genre_id`) values (9,2);
insert into book_genre (`book_id`, `genre_id`) values (9,6);

insert into book_genre (`book_id`, `genre_id`) values (10,2);
insert into book_genre (`book_id`, `genre_id`) values (10,7);
insert into book_genre (`book_id`, `genre_id`) values (10,8);
insert into book_genre (`book_id`, `genre_id`) values (10,9);

insert into book_genre (`book_id`, `genre_id`) values (11,2);
insert into book_genre (`book_id`, `genre_id`) values (11,7);
insert into book_genre (`book_id`, `genre_id`) values (11,8);
insert into book_genre (`book_id`, `genre_id`) values (11,9);

insert into book_genre (`book_id`, `genre_id`) values (12,2);
insert into book_genre (`book_id`, `genre_id`) values (12,7);
insert into book_genre (`book_id`, `genre_id`) values (12,8);
insert into book_genre (`book_id`, `genre_id`) values (12,9);

insert into book_genre (`book_id`, `genre_id`) values (13,2);
insert into book_genre (`book_id`, `genre_id`) values (13,10);

insert into book_genre (`book_id`, `genre_id`) values (14,2);
insert into book_genre (`book_id`, `genre_id`) values (14,10);
