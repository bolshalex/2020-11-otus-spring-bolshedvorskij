drop table if exists authors;
create table authors(id identity primary key, name varchar(255));

drop table if exists books;
create table books(id identity primary key, title varchar (512));

drop table if exists genres;
create table genres(id identity primary key, name varchar(255));

drop table if exists author_books;
create table author_books(id identity primary key,
                          book_id bigint,
                          author_id bigint,
                          constraint fk_book_id foreign key (book_id) references books(id),
                          constraint fk_author_id foreign key (book_id) references authors(id));

drop table if exists book_genres;
create table book_genres(id identity primary key,
                         book_id bigint,
                         genre_id bigint,
                         constraint fk_genre_book foreign  key (book_id) references books(id),
                         constraint fk_genre foreign key (genre_id) references genres(id));

