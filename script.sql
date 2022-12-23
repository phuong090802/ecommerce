-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema auction_system_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema auction_system_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `auction_system_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `auction_system_db` ;

-- -----------------------------------------------------
-- Table `auction_system_db`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction_system_db`.`role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `auction_system_db`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction_system_db`.`account` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `expire` DATETIME(6) NULL DEFAULT NULL,
  `otp` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NOT NULL,
  `user_name` VARCHAR(50) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE INDEX `UK_f6xpj7h12wr185bqhfi1hqlbr` (`user_name` ASC) VISIBLE,
  INDEX `FKd4vb66o896tay3yy52oqxr9w0` (`role_id` ASC) VISIBLE,
  CONSTRAINT `FKd4vb66o896tay3yy52oqxr9w0`
    FOREIGN KEY (`role_id`)
    REFERENCES `auction_system_db`.`role` (`role_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `auction_system_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction_system_db`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `full_name` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(11) NOT NULL,
  `account_account_id` INT NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `UK_ob8kqyqqgmefl0aco34akdtpe` (`email` ASC) VISIBLE,
  UNIQUE INDEX `UK_589idila9li6a4arw1t8ht1gx` (`phone` ASC) VISIBLE,
  INDEX `fk_user_account1_idx` (`account_account_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_account1`
    FOREIGN KEY (`account_account_id`)
    REFERENCES `auction_system_db`.`account` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `auction_system_db`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction_system_db`.`address` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `degree` TINYINT(1) NULL DEFAULT NULL,
  `full_address` VARCHAR(255) NOT NULL,
  `is_primary` TINYINT(1) NULL DEFAULT '0',
  `state` VARCHAR(255) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`address_id`),
  INDEX `FKda8tuywtf0gb6sedwk7la1pgi` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKda8tuywtf0gb6sedwk7la1pgi`
    FOREIGN KEY (`user_id`)
    REFERENCES `auction_system_db`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `auction_system_db`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction_system_db`.`genre` (
  `genre_id` INT NOT NULL AUTO_INCREMENT,
  `genre_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`genre_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `auction_system_db`.`item_auction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction_system_db`.`item_auction` (
  `item_auction_id` INT NOT NULL AUTO_INCREMENT,
  `auto_accept_amount` DOUBLE NULL DEFAULT NULL,
  `current_price` DECIMAL(12,2) NOT NULL DEFAULT '0.00',
  `description` TEXT NOT NULL,
  `end_date` DATETIME(6) NOT NULL,
  `increment` DECIMAL(12,2) NOT NULL DEFAULT '0.00',
  `start_bid_amount` DECIMAL(12,2) NOT NULL DEFAULT '0.00',
  `start_date` DATETIME(6) NOT NULL,
  `status` TINYINT(1) NULL DEFAULT '0',
  `title` VARCHAR(255) NOT NULL,
  `genre_id` INT NOT NULL,
  `seller_id` INT NOT NULL,
  PRIMARY KEY (`item_auction_id`),
  INDEX `FKgq3m1pnr7597sb472ouqdxw8e` (`genre_id` ASC) VISIBLE,
  INDEX `FKidx7f4aratssfm05m5s0rprcv` (`seller_id` ASC) VISIBLE,
  CONSTRAINT `FKgq3m1pnr7597sb472ouqdxw8e`
    FOREIGN KEY (`genre_id`)
    REFERENCES `auction_system_db`.`genre` (`genre_id`),
  CONSTRAINT `FKidx7f4aratssfm05m5s0rprcv`
    FOREIGN KEY (`seller_id`)
    REFERENCES `auction_system_db`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `auction_system_db`.`bid`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction_system_db`.`bid` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(255) NOT NULL,
  `time` DATETIME(6) NOT NULL,
  `value` DOUBLE NOT NULL,
  `item_id` INT NOT NULL,
  `buy_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKg1v6q0dm1wkl2j62naej4qwt6` (`item_id` ASC) VISIBLE,
  INDEX `FKdm3qqblx452jwhs78wfa05agr` (`buy_id` ASC) VISIBLE,
  CONSTRAINT `FKdm3qqblx452jwhs78wfa05agr`
    FOREIGN KEY (`buy_id`)
    REFERENCES `auction_system_db`.`user` (`user_id`),
  CONSTRAINT `FKg1v6q0dm1wkl2j62naej4qwt6`
    FOREIGN KEY (`item_id`)
    REFERENCES `auction_system_db`.`item_auction` (`item_auction_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `auction_system_db`.`bid_winner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction_system_db`.`bid_winner` (
  `ship_cost` DOUBLE NOT NULL,
  `value` DOUBLE NOT NULL,
  `buy_id` INT NOT NULL,
  `item_auction_id` INT NOT NULL,
  PRIMARY KEY (`buy_id`, `item_auction_id`),
  INDEX `FKpab5809u1w0n5sls9gpkeery9` (`item_auction_id` ASC) VISIBLE,
  CONSTRAINT `FK1g3td20yedsqp8925pgjguemj`
    FOREIGN KEY (`buy_id`)
    REFERENCES `auction_system_db`.`user` (`user_id`),
  CONSTRAINT `FKpab5809u1w0n5sls9gpkeery9`
    FOREIGN KEY (`item_auction_id`)
    REFERENCES `auction_system_db`.`item_auction` (`item_auction_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `auction_system_db`.`feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction_system_db`.`feedback` (
  `feedback_id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NOT NULL,
  `review` VARCHAR(255) NULL DEFAULT NULL,
  `buy_id` INT NOT NULL,
  `seller_id` INT NOT NULL,
  PRIMARY KEY (`feedback_id`),
  INDEX `FK81iywn8d7cn93yf0emrgqa6h3` (`buy_id` ASC) VISIBLE,
  INDEX `FKncextct5r0c38o7wga3gcxd3o` (`seller_id` ASC) VISIBLE,
  CONSTRAINT `FK81iywn8d7cn93yf0emrgqa6h3`
    FOREIGN KEY (`buy_id`)
    REFERENCES `auction_system_db`.`user` (`user_id`),
  CONSTRAINT `FKncextct5r0c38o7wga3gcxd3o`
    FOREIGN KEY (`seller_id`)
    REFERENCES `auction_system_db`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `auction_system_db`.`photo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction_system_db`.`photo` (
  `photo_id` INT NOT NULL AUTO_INCREMENT,
  `mime` VARCHAR(50) NULL DEFAULT NULL,
  `photo_data` MEDIUMBLOB NULL DEFAULT NULL,
  `photo_name` VARCHAR(255) NULL DEFAULT NULL,
  `item_auction_id` INT NOT NULL,
  PRIMARY KEY (`photo_id`),
  INDEX `FKqfxur722030n6esvoc99q4hvn` (`item_auction_id` ASC) VISIBLE,
  CONSTRAINT `FKqfxur722030n6esvoc99q4hvn`
    FOREIGN KEY (`item_auction_id`)
    REFERENCES `auction_system_db`.`item_auction` (`item_auction_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
