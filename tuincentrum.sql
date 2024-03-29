set names utf8mb4;
set charset utf8mb4;

drop database if exists tuincentrum;
create database tuincentrum charset utf8mb4;
use tuincentrum;

create table leveranciers (
  id int unsigned not null auto_increment primary key,
  naam varchar(30) NOT NULL,
  adres varchar(30) NOT NULL,
  postcode smallint unsigned NOT NULL,
  woonplaats varchar(30) NOT NULL,
  sinds date not null,
  key naam(naam),
  key woonplaats(woonplaats)
);

insert into leveranciers (naam,adres,postcode,woonplaats,sinds) values
 ('Hovenier','Zandweg 50',8560,'Wevelgem', '1996-01-01'),
 ('Baumgarten','Takstraat 13',8500,'Kortrijk','1997-02-04'),
 ('Struik','Bessenlaan 1',8560,'Wevelgem','1998-03-06'),
 ('Spitman','Achtertuin 9',8930,'Menen','1999-04-08'),
 ('Dezaaier','De Gronden 11',8560,'Wevelgem','2000-05-10'),
 ('Mooiweer','Verlengde zomerstraat 24',8930,'Menen', '2001-06-12'),
 ('Bloem','Linnaeushof 17',8500,'Kortrijk','2002-07-14'),
 ('De Plukker','Koeleplekstraat 10',8560,'Wevelgem','2003-08-16'),
 ('Erica','Berkenweg 87',8930,'Menen','2004-09-18'),
 ('De groene kas','Glasweg 1',8930,'Menen','2005-10-20'),
 ('Flora','Oeverstraat 76',8930,'Menen','2006-11-22');


create table soorten (
  id int unsigned not null auto_increment primary key,
  naam varchar(10) NOT NULL unique
);

insert into soorten(naam) values
 ('1-jarig'),
 ('2-jarig'),
 ('Bol'),
 ('Boom'),
 ('Heester'),
 ('Heide'),
 ('Klim'),
 ('Kruid'),
 ('Vast'),
 ('Water');

create table `planten` (
  id int unsigned not null auto_increment primary key,
  naam varchar(30) not null,
  soortId int unsigned not null,
  leverancierId int unsigned not null,
  kleur varchar(10) not null,
  prijs decimal(9,2) not null,
  key naam(naam),
  constraint FK_planten_soorten foreign key(soortId) references soorten(id),
  constraint FK_planten_leveranciers foreign key(leverancierId) references leveranciers(id)
);

insert into planten (naam,soortId,leverancierId,kleur,prijs) values
 ('Rododendron',5,6,'Rood',390),
 ('Sering',5,6,'Paars',390),
 ('Kruisdistel',9,6,'Blauw',60),
 ('Vuurdoorn',5,2,'Wit',100),
 ('Klokjesbloem',2,4,'Blauw',36),
 ('Kogeldistel',9,6,'Blauw',60),
 ('Paardekastanje',4,10,'Wit',350),
 ('Linde',4,4,'Geel',850),
 ('Wijnstok',7,3,'Onbekend',200),
 ('Violier',2,10,'Gemengd',20),
 ('Zonnebloem',2,4,'Geel',36),
 ('Populier',4,3,'Wit',90),
 ('Cypergras',10,2,'Onbekend',100),
 ('Zilverspar',4,6,'Onbekend',390),
 ('Boterbloem',9,6,'Wit',60),
 ('Hondstong',9,7,'Blauw',50),
 ('Stokroos',9,8,'Rood',40),
 ('Dwergcypres',4,7,'Onbekend',270),
 ('Ganzerik',9,2,'Rood',45),
 ('Daglelie',9,6,'Rood',60),
 ('Dovenetel',9,8,'Geel',40),
 ('Muurbloem',2,4,'Bruin',36),
 ('Pioen',9,3,'Rood',90),
 ('Korenbloem',9,8,'Blauw',40),
 ('Bereklauw',9,5,'Wit',70),
 ('Klokjesbloem',9,6,'Blauw',60),
 ('Lupine',9,7,'Gemengd',50),
 ('Violier',8,11,'Gemengd',20),
 ('Judaspenning',2,10,'Lila',20),
 ('Azalea',5,10,'Oranje',350),
 ('Esdoorn',4,10,'Groen',350),
 ('Dragon',8,8,'Wit',40),
 ('Forsythia',5,1,'Geel',110),
 ('Kornoelje',5,1,'Geel',110),
 ('Basilicum',8,8,'Wit',40),
 ('Begonia',7,3,'Rood',13.00),
 ('Sierui',3,10,'Blauw',75.00),
 ('Vingerhoedskruid',9,8,'Gemengd',40),
 ('Vlambloem',8,9,'Gemengd',30),
 ('Aster',4,5,'Gemengd',15),
 ('Akelei',9,7,'Blauw',50),
 ('Viooltje',2,11,'Gemengd',10),
 ('Azijnboom',4,4,'Rood',190),
 ('Margriet',9,7,'Wit',50),
 ('Krokus',3,3,'Wit',2),
 ('Monnikskap',9,6,'Violet',60),
 ('Lijsterbes',4,8,'Wit',150),
 ('Bosrank',7,10,'Paars',130),
 ('Iris',3,3,'Blauw',2),
 ('Kikkerbeet',10,4,'Wit',25),
 ('Magnolia',5,5,'Wit',290),
 ('Acacia',4,10,'Wit',350),
 ('Dotterbloem',10,3,'Geel',90),
 ('Rozemarijn',8,8,'Blauw',40),
 ('Meidoorn',4,6,'Roze',390),
 ('Liguster',5,9,'Wit',8),
 ('Ridderspoor',9,6,'Lila',60),
 ('Dille',8,8,'Geel',40),
 ('Engels gras',9,8,'Rood',40),
 ('Kamille',9,6,'Wit',60),
 ('Zuurbes',5,5,'Oranje',70),
 ('Blauw druifje',3,3,'Blauw',2),
 ('Winterheide',6,8,'Wit',40),
 ('Berk',4,8,'Onbekend',150),
 ('Passiebloem',7,4,'Blauw',190),
 ('Toverhazelaar',5,3,'Geel',640),
 ('Korenbloem',1,10,'Gemengd',20),
 ('Beuk',4,9,'Groen',250),
 ('Waterhyacint',10,2,'Blauw',100),
 ('Klaproos',9,6,'Rood',60),
 ('Tulpeboom',4,11,'Geel',450),
 ('Peterselie',8,8,'Onbekend',40),
 ('Majoraan',8,8,'Paars',40),
 ('Pampusgras',9,4,'Wit',190),
 ('Leverkruid',9,7,'Paars',50),
 ('Lisdodde',10,3,'Geel',90),
 ('Chrysant',1,6,'Geel',16),
 ('Nagelkruid',9,6,'Oranje',60),
 ('Zuring',8,8,'Rood',40),
 ('Waterlelie',10,10,'Wit',240),
 ('Struikheide',6,8,'Gemengd',40),
 ('Blaasjeskruid',10,7,'Geel',50),
 ('Boomheide',6,1,'Roze',110),
 ('Tijm',8,7,'Paars',50),
 ('Ridderspoor',1,10,'Gemengd',20),
 ('Spar',4,9,'Onbekend',250),
 ('Gipskruid',9,3,'Wit',90),
 ('Brem',5,2,'Geel',100),
 ('Dopheide',6,9,'Rood',30),
 ('Wolfsmelk',9,7,'Geel',50),
 ('Klaproos',2,10,'Gemengd',20),
 ('Dahlia',1,10,'Gemengd',20),
 ('Vuurwerkplant',9,5,'Roze',70),
 ('Anemoon',9,5,'Roze',70),
 ('Salie',8,8,'Violet',40),
 ('Judasboom',4,4,'Roze',190),
 ('Peperboompje',5,4,'Roze',300),
 ('Hondstong',2,10,'Blauw',20),
 ('Hulst',5,11,'Onbekend',450),
 ('Gouden regen',4,1,'Geel',440),
 ('Wolgras',10,5,'Wit',70),
 ('Bieslook',8,8,'Paars',40),
 ('Alyssum',1,1,'Paars',11),
 ('Klimop',7,8,'Onbekend',150),
 ('Kalmoes',10,3,'Onbekend',90),
 ('Petunia',1,6,'Roze',16),
 ('Munt',8,8,'Paars',40),
 ('Anjer',9,6,'Wit',60),
 ('Sleutelbloem',2,8,'Gemengd',40),
 ('Kervel',8,8,'Wit',40),
 ('Zonnebloem',9,8,'Geel',40),
 ('Leeuwebekje',1,6,'Gemengd',16),
 ('Tulp',3,9,'Geel',8),
 ('Gipskruid',1,10,'Wit',20),
 ('Olijfwilg',5,3,'Geel',200),
 ('Klaproos',1,10,'Gemengd',20),
 ('Vuurpijl',9,5,'Rood',70),
 ('Jeneverbes',4,10,'Onbekend',130);