create database if not exists PlayMusic;
use PlayMusic;

create table artist(artistId int primary key, artistName varchar(20) not null );

insert into artist values(1,'Hasley');
insert into artist values(2,'TaylorSwift');
insert into artist values(3,'ShreyaGoshal');

select * from artist;
drop table artist;

create table genre(genreId int primary key,genreName varchar (20) not null);

insert into genre values (1,'Retro');
insert into genre values (2,'ElectroPop');
insert into genre values (3,'Melody');

select * from genre;
drop table genre;

create table album(albumId int primary key,albumName varchar(20) not null);

insert into album values(111,'AmericanPop');
insert into album values(112,'Bollywood');

select * from album;
drop table album;

create table song(songId int primary key,songName varchar(40) not null,artistId int,genreId int,albumId int,
filePath varchar(100),
constraint c1 foreign key (artistId) references artist(artistId),
constraint c2 foreign key (genreId) references genre(genreId),
constraint c3 foreign key (albumId) references album(albumId));

insert into song values(101,'Closer',1,1,111,'Songs and pocasts/Closer - The Chainsmokers (1).wav');
insert into song values(102,'BlankSpace',2,3,111,'Songs and pocasts/Taylor-Swift-Blank-Space.wav');
insert into song values(103,'ParamSundari',3,3,112,'Songs and pocasts/Param Sundari - Mimi.wav');
insert into song values(104,'ThisLove',2,2,111,'Songs and pocasts/Taylor_Swift_This_Love_Taylor_s_Version__(thinkNews.com.ng).wav');
insert into song values(105,'SunnRaha',3,3,112,'Songs and pocasts/Sunn Raha Hai (Female).wav');

select * from song;
drop table song;

create table user(userId int primary key,userName varchar(25),phoneNo varchar(15) unique,
email varchar(20)not null,password varchar(20) not null);

select * from user;
drop table user;

drop table playlist;

create table celebrity(celebId int primary key,celebName varchar(20) not null);

drop table celebrity;

insert into celebrity values(1,'jay');
insert into celebrity values(2,'kenny');

create table playlistSongs (pListSong int,songId int,pListName varchar(20),primary key(pListSong,songId),
foreign key (songId) references song(songId) );

drop table playlistSongs;

create table playlistPodcasts (pListPod int,podId int,podName varchar(20),primary key(pListPod,podId),
foreign key (podId) references podcast(podId));

drop table playlistPodcasts;

create table podcast(podId int primary key,podName varchar(25) not null, podEpisodes int not null,
celebId int ,podDop date not null,filepath varchar(100));

insert into podcast values(301,'health',2,1,20/7/2020,'Songs and pocasts/FAKE LOVE - BTS 128(PagalWorld).wav');
insert into podcast values(302,'relation',2,2,22/7/2020,'Songs and pocasts/Mehbooba Main Teri Mehbooba (KGF Chapter 2)_320(PagalWorld.com.se).wav');

select * from podcast;
drop table podcast;

select * from user;
select * from playlistSongs;