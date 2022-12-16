-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema framework
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema framework
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `framework` ;
USE `framework` ;

-- -----------------------------------------------------
-- Table `framework`.`Producenci`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `framework`.`Producenci` (
  `idProducenci` INT NOT NULL,
  `nazwa_firmy` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idProducenci`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `framework`.`Kategorie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `framework`.`Kategorie` (
  `idKategorie` INT NOT NULL,
  `nazwa_kategorii` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idKategorie`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `framework`.`Produkty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `framework`.`Produkty` (
  `idProdukty` INT NOT NULL,
  `nazwa` VARCHAR(45) NOT NULL,
  `cena` DOUBLE NOT NULL,
  `ilosc` INT NOT NULL,
  `Kategorie_idKategorie` INT NOT NULL,
  `Producenci_idProducenci` INT NOT NULL,
  PRIMARY KEY (`idProdukty`),
  INDEX `fk_Produkty_Kategorie_idx` (`Kategorie_idKategorie` ASC) VISIBLE,
  INDEX `fk_Produkty_Producenci1_idx` (`Producenci_idProducenci` ASC) VISIBLE,
  CONSTRAINT `fk_Produkty_Kategorie`
    FOREIGN KEY (`Kategorie_idKategorie`)
    REFERENCES `framework`.`Kategorie` (`idKategorie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produkty_Producenci1`
    FOREIGN KEY (`Producenci_idProducenci`)
    REFERENCES `framework`.`Producenci` (`idProducenci`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `framework`.`Adres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `framework`.`Adres` (
  `idAdres` INT NOT NULL,
  `miejscowość` VARCHAR(45) NOT NULL,
  `ulica` VARCHAR(45) NULL,
  `nr_domu` VARCHAR(45) NOT NULL,
  `nr_lokalu` VARCHAR(45) NULL,
  `kod_pocztowy` VARCHAR(45) NOT NULL,
  `miasto` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAdres`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `framework`.`Użytkownicy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `framework`.`Użytkownicy` (
  `idUżytkownicy` INT NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `hasło` VARCHAR(45) NOT NULL,
  `imię` VARCHAR(45) NOT NULL,
  `nazwisko` VARCHAR(45) NOT NULL,
  `Adres_idAdres` INT NOT NULL,
  PRIMARY KEY (`idUżytkownicy`),
  INDEX `fk_Użytkownicy_Adres1_idx` (`Adres_idAdres` ASC) VISIBLE,
  CONSTRAINT `fk_Użytkownicy_Adres1`
    FOREIGN KEY (`Adres_idAdres`)
    REFERENCES `framework`.`Adres` (`idAdres`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `framework`.`Zamówienia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `framework`.`Zamówienia` (
  `idZamówienia` INT NOT NULL,
  `Użytkownicy_idUżytkownicy` INT NOT NULL,
  `Produkty_idProdukty` INT NOT NULL,
  PRIMARY KEY (`idZamówienia`),
  INDEX `fk_Zamówienia_Użytkownicy1_idx` (`Użytkownicy_idUżytkownicy` ASC) VISIBLE,
  INDEX `fk_Zamówienia_Produkty1_idx` (`Produkty_idProdukty` ASC) VISIBLE,
  CONSTRAINT `fk_Zamówienia_Użytkownicy1`
    FOREIGN KEY (`Użytkownicy_idUżytkownicy`)
    REFERENCES `framework`.`Użytkownicy` (`idUżytkownicy`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Zamówienia_Produkty1`
    FOREIGN KEY (`Produkty_idProdukty`)
    REFERENCES `framework`.`Produkty` (`idProdukty`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `framework`.`Opinie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `framework`.`Opinie` (
  `idOpinie` INT NOT NULL,
  `opis` VARCHAR(45) NOT NULL,
  `Użytkownicy_idUżytkownicy` INT NOT NULL,
  `Produkty_idProdukty` INT NOT NULL,
  PRIMARY KEY (`idOpinie`),
  INDEX `fk_Opinie_Użytkownicy1_idx` (`Użytkownicy_idUżytkownicy` ASC) VISIBLE,
  INDEX `fk_Opinie_Produkty1_idx` (`Produkty_idProdukty` ASC) VISIBLE,
  CONSTRAINT `fk_Opinie_Użytkownicy1`
    FOREIGN KEY (`Użytkownicy_idUżytkownicy`)
    REFERENCES `framework`.`Użytkownicy` (`idUżytkownicy`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Opinie_Produkty1`
    FOREIGN KEY (`Produkty_idProdukty`)
    REFERENCES `framework`.`Produkty` (`idProdukty`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
