-- MySQL Script generated by MySQL Workbench
-- 09/04/14 10:01:09
-- Model: New Model    Version: 1.0
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema maddb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `madddb` DEFAULT CHARACTER SET utf8 ;
USE `madddb` ;

-- -----------------------------------------------------
-- Table `maddb`.`rettype`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `madddb`.`rettype` ;

CREATE TABLE IF NOT EXISTS `madddb`.`rettype` (
  `Id` INT(10) NOT NULL,
  `Navn` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `maddb`.`ret`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `madddb`.`ret` ;

CREATE TABLE IF NOT EXISTS `madddb`.`ret` (
  `Nummer` INT(50) NOT NULL,
  `Navn` VARCHAR(50) NOT NULL,
  `RetType_Id` INT(10) NOT NULL,
  `TotalPris` INT(50) NOT NULL,
  `AntalDage` INT(10) NOT NULL,
  PRIMARY KEY (`Nummer`),
  INDEX `RetType_Id` (`RetType_Id` ASC),
  CONSTRAINT `ret_ibfk_1`
    FOREIGN KEY (`RetType_Id`)
    REFERENCES `madddb`.`rettype` (`Id`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `maddb`.`varegruppe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `madddb`.`varegruppe` ;

CREATE TABLE IF NOT EXISTS `madddb`.`varegruppe` (
  `Id` INT(10) NOT NULL,
  `Navn` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `maddb`.`vare`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `madddb`.`vare` ;

CREATE TABLE IF NOT EXISTS `madddb`.`vare` (
  `Nummer` INT(50) NOT NULL,
  `Navn` VARCHAR(50) NOT NULL,
  `Gruppe_Id` INT(10) NOT NULL,
  `Pris` INT(50) NOT NULL,
  PRIMARY KEY (`Nummer`),
  INDEX `Gruppe_Id` (`Gruppe_Id` ASC),
  CONSTRAINT `vare_ibfk_1`
    FOREIGN KEY (`Gruppe_Id`)
    REFERENCES `madddb`.`varegruppe` (`Id`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `maddb`.`vareline`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `madddb`.`vareline` ;

CREATE TABLE IF NOT EXISTS `madddb`.`vareline` (
  `Vare_Nummer` INT(50) NOT NULL,
  `Ret_Nummer` INT(50) NOT NULL,
  `VareAntal` INT(50) NOT NULL,
  `TotalPris` INT(50) NOT NULL,
  PRIMARY KEY (`Vare_Nummer`, `Ret_Nummer`),
  INDEX `Ret_Nummer` (`Ret_Nummer` ASC),
  CONSTRAINT `vareline_ibfk_1`
    FOREIGN KEY (`Vare_Nummer`)
    REFERENCES `madddb`.`vare` (`Nummer`)
    ON UPDATE CASCADE,
  CONSTRAINT `vareline_ibfk_2`
    FOREIGN KEY (`Ret_Nummer`)
    REFERENCES `madddb`.`ret` (`Nummer`)
    ON UPDATE CASCADE ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
