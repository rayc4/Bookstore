insert into publisher values ('Simon & Schuster', 'Simon&schuster.com', 'SimonandSchuster@gmail.com', '77 West 66th St, New City, NY, 12342', 15425, 234, 194395343);
insert into publisher values ('Nature America', 'NatureAmerica.com', 'NatureAmerica@gmail.com', '1200 Prospect St, Ste 575, La Jolla, California 92037, US', 54245, 543, 154392940);
insert into publisher values ('Scholastic', 'Scholastic.com', 'Scholastic.contact', '84 Basswood DrGuelph, ON N1G 4X7, Canada', 55341, 423, 854903285);
insert into publisher values ('HarperCollins Publishers', 'HarperCollins.com', 'HarperCollins@gmail.com', '44-46 Morningside Road, Edinburgh, Scotland, EH10 4BF', 94504, 343, 490345219);
insert into publisher values ('Random House', 'randomhouse.com', 'randomhouse@gmail.com', '831 Cedarwood Drive, Minneapolis, Minnesota 29440', 84395, 253, 193548930);
insert into publisher values ('Warner Bros', 'wawrnerbros.com', 'warwnerbros@gmail.com', '130 Cross Road, Clermont, FL 34711', 39485, 103, 204569403);
insert into publisher values ('American Library', 'americanlibrary.com', 'americanlibrary@gmail.com', '9696 W. Carriage Lane, Rome, NY 13440', 49302, 143, 493021345);
insert into publisher values ('O Reilly Media', 'OReillyMedia.com', 'oreillymediar@gmail.com', '285 Shore Dr.Powder Springs, GA 30127', 65932, 243, 543948967);
insert into publisher values ('Association for Computing Machinery', 'AssociationComputerMmachinery.com', 'AssociationComputerMmachinery@gmail.com', '569 N. Lake View Ave, New City, NY 10956', 39456, 513, 430243985);
insert into publisher values ('Getty', 'gettyPublications.com', 'gettyPublications@gmail.com', '64 Edgefield Street, Mchenry, IL 60050', 91322, 823, 105894539);

insert into publisher_phone values ('Simon & Schuster', '647-555-1239');
insert into publisher_phone values ('Simon & Schuster', '647-555-1321');
insert into publisher_phone values ('Nature America', '647-555-5342');
insert into publisher_phone values ('Nature America', '647-555-5432');
insert into publisher_phone values ('Scholastic', '647-555-1234');
insert into publisher_phone values ('Scholastic', '647-555-5123');
insert into publisher_phone values ('HarperCollins Publishers', '647-555-6542');
insert into publisher_phone values ('Random House', '647-555-5433');
insert into publisher_phone values ('Warner Bros', '647-555-7654');
insert into publisher_phone values ('American Library', '647-555-5332');
insert into publisher_phone values ('O Reilly Media', '647-555-6444');
insert into publisher_phone values ('Association for Computing Machinery', '647-555-6544');
insert into publisher_phone values ('Getty', '647-555-2345');

insert into author values ('Alice', 'Walker', 8255347513994);
insert into author values ('Marilynne', 'Robinson', 1901984610855);
insert into author values ('Jonathan', 'Franzen', 4561584776906);
insert into author values ('Michael', 'Chabon', 6093593457995);
insert into author values ('William', 'Shakespeare', 9515492220137);
insert into author values ('Rick', 'Riordan', 2392878760439);
insert into author values ('Rick', 'Riordan', 8364100116396);
insert into author values ('Rick', 'Riordan', 0900370524255);
insert into author values ('Rick', 'Riordan', 4387335043958);
insert into author values ('Rick', 'Riordan', 7159994282645);
insert into author values ('Rick', 'Riordan', 1902180409328);
insert into author values ('Rick', 'Riordan', 1979203008307);
insert into author values ('Rick', 'Riordan', 6180927678594);
insert into author values ('Rick', 'Riordan', 6289443139174);
insert into author values ('Rick', 'Riordan', 1654738988868);
insert into author values ('Rick', 'Riordan', 2610937160587);
insert into author values ('Rick', 'Riordan', 1654738988868);
insert into author values ('Rick', 'Riordan', 5018417289514);
insert into author values ('Rick', 'Riordan', 0181569208181);
insert into author values ('Rick', 'Riordan', 2069463866612);

insert into book_genre values (8255347513994, 'Novel');
insert into book_genre values (1901984610855, 'Novel');
insert into book_genre values (4561584776906, 'Sci-Fi');
insert into book_genre values (6093593457995, 'Romance');
insert into book_genre values (9515492220137, 'literature');
insert into book_genre values (2392878760439, 'Fantasy');
insert into book_genre values (8364100116396 , 'Fantasy');
insert into book_genre values (0900370524255 , 'Fantasy');
insert into book_genre values (4387335043958 , 'Fantasy');
insert into book_genre values (7159994282645 , 'Fantasy');
insert into book_genre values (1902180409328 , 'Fantasy');
insert into book_genre values (1979203008307 , 'Fantasy');
insert into book_genre values (6180927678594 , 'Fantasy');
insert into book_genre values (6289443139174 , 'Fantasy');
insert into book_genre values (1654738988868 , 'Fantasy');
insert into book_genre values (2610937160587 , 'Fantasy');
insert into book_genre values (1654738988868 , 'Fantasy');
insert into book_genre values (5018417289514 , 'Fantasy');
insert into book_genre values (0181569208181 , 'Fantasy');
insert into book_genre values (2069463866612 , 'Fantasy');

insert into book values (8255347513994, 'The Colour Purple', 283, 9.99, 10, 'Simon & Schuster', 0.3);
insert into book values (1901984610855, 'Gilead', 256, 11.99, 10, 'Nature America', 0.2);
insert into book values (4561584776906, 'Purity', 563, 12.99, 10, 'HarperCollins Publishers', 0.1);
insert into book values (6093593457995, 'Moonglow', 430, 14.99, 10, 'Random House', 0.35);
insert into book values (9515492220137, 'Hamlet', 645, 21.99, 10, 'American Library', 0.40);
insert into book values (2392878760439, 'The Lightning Thief', 377, 13.99, 10, 'Scholastic, Inc.', 0.6);
insert into book values (8364100116396, 'The Sea of Monsters', 279, 13.99, 10, 'Scholastic, Inc.', 0.6);
insert into book values (0900370524255, 'The Titans Curse', 312, 13.99, 10, 'Scholastic, Inc.', 0.6);
insert into book values (4387335043958, 'The Battle of the Labyrinth', 361, 13.99, 10, 'Scholastic, Inc.', 0.6);
insert into book values (7159994282645, 'Percy Jackson and the Last Olympian', 381, 13.99, 10, 'Scholastic, Inc.', 0.6);
insert into book values (1902180409328, 'The Red Pyramid', 516, 15.99, 10, 'Scholastic, Inc.', 0.6);
insert into book values (1979203008307, 'The Throne of Fire', 452, 15.99, 10, 'Scholastic, Inc.', 0.6);
insert into book values (6180927678594, 'The Serpents Shadow', 406, 15.99, 10, 'Scholastic, Inc.', 0.6);
insert into book values (6289443139174, 'The Lost Hero', 557, 20.99, 10, 'Scholastic, Inc.', 0.6);
insert into book values (1654738988868, 'The Son of Neptune', 513, 20.99, 10, 'Scholastic, Inc.', 0.6);
insert into book values (2610937160587, 'The Mark of Athena', 586, 20.99, 10, 'Scholastic, Inc.', 0.6);
insert into book values (1654738988868, 'The Son of Neptune', 586, 20.99, 10, 'Scholastic, Inc.', 0.6);
insert into book values (5018417289514, 'The House of Hades', 597, 20.99, 10, 'Scholastic, Inc.', 0.6);
insert into book values (0181569208181, 'The Blood of Olympus', 516, 20.99, 10, 'Scholastic, Inc.', 0.6);
insert into book values (2069463866612, 'The Demigod Diaries', 224, 20.99, 10, 'Scholastic, Inc.', 0.6);
