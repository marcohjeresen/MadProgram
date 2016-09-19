use madddb;
set foreign_key_checks = 0;

delete from VareGruppe;
insert into VareGruppe(Id, Navn) values
(1,'Kød'),
(2,'Grønt'),
(3,'Pålæg'),
(4,'Dåser/Glas'),
(5,'Katoffler/Ris/Spagithi'),
(6,'Mælke Produkt'),
(7,'Brød'),
(8,'Drikke'),
(9,'Andet Fødevare'),
(10,'Andet');

delete from Vare;
insert into Vare(Nummer, Navn, Gruppe_Id, Pris) values
(1,'OkseKød',1,1900),
(2,'SvineKød',1,2200),
(3,'Kotelleter',1,2900),
(4,'Kylling',1,4500),
(5,'KyllingeLår',1,3000),
(6,'KyllingeBryst',1,3000),
(7,'PartyPølser',1,1600),
(65,'Røde Pølser',1,1700),
(68,'SkinkeKød I Tern',1,2700),

(8,'Agurker',2,500),
(9,'CherryTomater',2,1000),
(10,'Løg',2,500),
(11,'PeberFrugt',2,500),
(12,'IceBerg',2,500),
(13,'GuleRødder',2,500),
(14,'Blomkål',2,1000),
(62,'Frossent Spinat',2,1500),

(15,'KødPølse',3,500),
(16,'SpegePølse',3,1500),
(17,'Salami',3,1500),
(18,'ChokoladePålæg',3,1500),
(19,'Bierwarst',3,700),
(20,'BjørnePølse',3,1000),
(21,'KyllingePølseSkiver',3,500),
(22,'KyllingePølser',3,1000),
(23,'Makral Salat',3,1000),
(24,'RullePølse',3,1050),
(25,'Hamberryg',3,1100),
(26,'LeverPostej',3,1500),

(27,'Hakkede Tomat',4,500),
(28,'Hakkede Tomat m Chile',4,500),
(29,'Glas Champignon',4,450),
(30,'Dåse Majs',4,1000),
(31,'CocktailPølser',4,2200),

(32,'Katofler',5,100),
(33,'Pomfritter',5,1100),
(34,'Ris',5,500),
(35,'Spageti Skruer',5,500),
(36,'Spagiti',5,1000),
(37,'KatoffelBåde',5,1000),
(38,'KatoffelSkiver',5,1000),
(66,'Glas Katofler',5,600),

(39,'LetMælk',6,650),
(40,'SkummeMælk',6,550),
(41,'SødMælk',6,650),
(42,'Smør',6,1400),
(43,'Magerine',6,550),
(44,'Fløde 25ml',6,900),
(45,'Fløde 50ml',6,1400),
(46,'Fløde Havarti',6,2100),
(61,'Revet Ost',6,1600),

(47,'RugBrød',7,1500),
(48,'Toast',7,1500),

(49,'Kaffe',8,2000),
(50,'JordbærSaft',8,800),
(51,'Sodavand',8,1000),
(52,'Øl',8,500),

(53,'Bearnaise',9,1000),
(54,'Sukker',9,2000),
(55,'Æg',9,2000),
(58,'Mel',9,1000),
(59,'Lasagne Plader',9,1000),
(60,'Chile Sovs',9,1900),
(63,'Ketchup',9,1000),
(64,'Bechamel Sovs',9,2200),
(67,'Tomat Pura',9,250),
(69,'Karry Pulver',9,500),

(56,'Klude',10,1000),
(57,'ToiletPapir',10,1300);

delete from RetType;
insert into RetType(Id, Navn) values
(1,'GryddeRet'),
(2,'Almindelig'),
(3,'OvnRet'),
(4,'SalatRet');

delete from Ret;
insert into Ret(Nummer, Navn, RetType_Id, TotalPris, AntalDage) values
(1,'Kødsovs med Spaggeti',1,6350,2),
(2,'Kødsovs med Ris',1,6350,2),
(3,'HakkeBøffer M Katofler O BrunSovs',2,0,1),
(4,'HakkeBøffer M Ris O Bernaise',2,0,1),
(5,'Lasagne M Kylling O Spinat.',3,0,1),
(6,'Lasagne',3,0,1),
(7,'Svensk Pølse Ret',1,0,2),
(8,'Kotteleter I Gryde',1,0,2),
(9,'Skinkekød I Karry',1,0,2),
(10,'Ovn Bagte Katofler Med Kylling',3,0,1);
#(11,'Stegtflæsk Med Persille',2,0,1),
#(12,'Frekedeller Med Katofler',2,0,1),
#(13,'Pasta Salat',4,0,1),
#(14,'Hamberryg M Stuvet Ærder o Gulerøder',2,0,2);

delete from VareLine;
insert into VareLine(Vare_Nummer, Ret_Nummer, VareAntal, TotalPris) values
(1,1,1,1900),
(10,1,1,500),
(11,1,1,1000),
(28,1,4,2000),
(30,1,1,1000),
(29,1,2,900),
(35,1,1,900),

(1,2,1,1900),
(10,2,1,500),
(11,2,2,1000),
(28,2,4,2000),
(30,2,1,1000),
(34,2,1,900),
(29,2,2,900),

(1,3,1,1900),
(10,3,1,500),
(32,3,12,1200),
(39,3,1,650),
(43,3,1,550),
(58,3,1,1000),

(1,4,1,1900),
(10,4,1,500),
(34,4,1,500),
(39,4,1,650),
(43,4,1,550),
(53,4,1,1000),

(28,5,2,1000),
(30,5,1,1000),
(62,5,1,1500),
(6,5,1,3000),
(61,5,1,1600),
(60,5,1,1900),
(59,5,1,1000),

(28,6,3,1500),
(30,6,1,1000),
(61,6,1,1600),
(59,6,1,1000),
(11,6,2,1000),
(63,6,1,1000),
(1,6,1,1900),
(64,6,1,2200),

(65,7,1,1700),
(66,7,4,2400),
(67,7,1,250),
(45,7,1,1400),

(3,8,1,2900),
(30,8,1,1000),
(11,8,1,500),
(28,8,3,1500),
(45,8,1,1400),
(29,8,1,450),
(10,8,1,500),
(43,8,1,500),

(68,9,1,2700),
(29,9,2,900),
(31,9,1,2200),
(58,9,1,1000),
(69,9,1,500),
(34,9,1,500),

(32,10,12,1200),
(5,10,1,3000),
(53,10,1,1000),
(39,10,1,650),
(43,10,1,550),
(60,10,1,1900);



set foreign_key_checks = 1;