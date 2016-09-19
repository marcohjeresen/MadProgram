use madddb;

drop procedure getProduktGrup;
drop procedure søgVare;
drop procedure getvare;
drop procedure getRetter;
drop procedure getRetIndhold;
drop procedure getChoosenRetter;
drop procedure getRetTyper;
drop procedure getChoosenVareGrup;
drop procedure IndsetProdukt;
drop procedure IndsetRet;
drop procedure IndsetVareLine;

DELIMITER $$

create procedure getProduktGrup()
BEGIN
select * from varegruppe
order by varegruppe.Navn;
																									
END $$

DELIMITER $$

create procedure getChoosenVareGrup(choosenId int)
BEGIN
select vare.Nummer, vare.Navn, vare.Gruppe_Id, varegruppe.Id, varegruppe.Navn, vare.Pris from vare, varegruppe
where vare.Gruppe_Id = choosenId
and vare.Gruppe_Id = varegruppe.Id
order by vare.Navn;

END $$

DELIMITER $$

create procedure getRetTyper()
BEGIN
select * from rettype
order by rettype.Navn;
																									
END $$


DELIMITER $$

create procedure getChoosenRetter(choosenId int)
BEGIN
select * from ret, rettype
where ret.RetType_Id = choosenId
and ret.RetType_Id = rettype.Id
order by ret.Navn;
																									
END $$

DELIMITER $$

create procedure getRetIndhold(choosenId int)
BEGIN
SELECT * FROM vareline, vare, varegruppe
where vareline.Vare_Nummer = vare.Nummer
and vareline.Ret_Nummer = choosenId
and vare.Gruppe_Id = varegruppe.Id
order by vare.Navn;
																									
END $$

DELIMITER $$

create procedure getRetter()
BEGIN
select * from ret, rettype
where ret.RetType_Id = rettype.Id
order by ret.Navn;
																									
END $$

DELIMITER $$

create procedure getvare()
BEGIN
select * from vare
order by vare.Navn;
																									
END $$


DELIMITER $$

create procedure søgVare(søgString varchar(50))
BEGIN
select * from vare
where vare.Navn like søgString 
order by vare.Navn;
																									
END $$

DELIMITER $$

create procedure IndsetProdukt(Navn varchar(50), Pris int, GruppeId int)
BEGIN
declare id int;
set id = (select max(vare.Nummer) from vare) +1;


insert into Vare values
(id, navn, GruppeId, Pris);
																									
END $$

DELIMITER $$

create procedure IndsetRet(navn varchar(50), retTypeId int , TotalPris int, AntalDage int)
BEGIN
declare id int;
set id = (select max(ret.Nummer) from ret) +1;

insert into ret values
(id, navn, retTypeId, TotalPris, AntalDage);
																									
END $$


DELIMITER $$

create procedure IndsetVareLine(vareNummer int, vareAntal int, TotalPris int)
BEGIN

declare retNummer int;
set retNummer = (select max(ret.Nummer) from ret);
insert into vareline values
(vareNummer, retNummer, vareAntal, TotalPris);
																									
END $$





