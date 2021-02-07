insert into authors (`id`,`name`) values(1,'Eric Matthes');
insert into books(`id`,`title`) values ( 1,'Python Crash Course, 2nd Edition: A Hands-On, Project-Based Introduction to Programming' );
insert into books(`id`,`title`) values ( 2,'Python Flash Cards: Syntax, Concepts, and Examples Cards' );

insert into author_books(`author_id`,`book_id`) values (1,1 );
insert into author_books(`author_id`,`book_id`) values (1,2 );

insert into genres (`id`,`name`) values (1,'Programming'  );
insert into book_genres (`book_id`,`genre_id`) values (1,1 );
insert into book_genres (`book_id`,`genre_id`) values (2,1 );
