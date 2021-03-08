insert into genres (`id`,`name`) values (1,'Programming'  );
insert into genres (`id`,`name`) values (2,'Computer Science');


insert into authors (`id`,`name`) values(1,'Eric Matthes');
insert into books(`id`,`title`) values ( 1,'Python Crash Course, 2nd Edition: A Hands-On, Project-Based Introduction to Programming' );
insert into book_comments (`id`,`text`,`book_id`) values (4,'same comment', 1);


insert into books(`id`,`title`) values ( 2,'Python Flash Cards: Syntax, Concepts, and Examples Cards' );
insert into author_books(`author_id`,`book_id`) values (1,1 );
insert into author_books(`author_id`,`book_id`) values (1,2 );
insert into book_genres (`book_id`,`genre_id`) values (1,1 );
insert into book_genres (`book_id`,`genre_id`) values (2,1 );

insert into authors (`id`,`name`) values( 2,'Paul Graham');
insert into books(`id`,`title`) values ( 3,'ANSI Common LISP' );
insert into book_genres (`book_id`,`genre_id`) values ( 3,1 );
insert into author_books(`author_id`,`book_id`) values ( 2,3 );

insert into authors (`id`,`name`) values( 3,'Erich Gamma');
insert into authors (`id`,`name`) values( 4,'Richard Helm');
insert into authors (`id`,`name`) values( 5,'Ralph Johnson');
insert into authors (`id`,`name`) values( 6,'John Vlissides');
insert into authors (`id`,`name`) values( 7,'Grady Booch');

insert into books(`id`,`title`) values ( 4,'Design Patterns: Elements of Reusable Object-Oriented Software' );
insert into book_genres (`book_id`,`genre_id`) values (4,1 );
insert into book_genres (`book_id`,`genre_id`) values (4,2 );
insert into author_books(`author_id`,`book_id`) values ( 3,4 );
insert into author_books(`author_id`,`book_id`) values ( 4,4 );
insert into author_books(`author_id`,`book_id`) values ( 5,4 );
insert into author_books(`author_id`,`book_id`) values ( 6,4 );
insert into author_books(`author_id`,`book_id`) values ( 7,4 );
insert into book_comments (`id`,`text`,`book_id`) values (2,'left comment', 4);
insert into book_comments (`id`,`text`,`book_id`) values (3,'recommend', 4);