-- MySQL Script generated by MySQL Workbench
-- Sun Nov 15 17:13:51 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bullfinch_test
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bullfinch_test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bullfinch_test` DEFAULT CHARACTER SET utf8;
USE `bullfinch_test`;

-- -----------------------------------------------------
-- Table `bullfinch_test`.`wallet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bullfinch_test`.`wallet`
(
    `wallet_id` BIGINT        NOT NULL AUTO_INCREMENT,
    `balance`   DECIMAL(7, 2) NOT NULL,
    PRIMARY KEY (`wallet_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bullfinch_test`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bullfinch_test`.`role`
(
    `role_id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `role_name` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`role_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bullfinch_test`.`user_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bullfinch_test`.`user_account`
(
    `user_account_id` BIGINT      NOT NULL AUTO_INCREMENT,
    `email`           VARCHAR(55) NULL,
    `login`           VARCHAR(20) NOT NULL,
    `password`        VARCHAR(40) NOT NULL,
    `first_name`      VARCHAR(25) NULL,
    `second_name`     VARCHAR(25) NULL,
    `phone_number`    VARCHAR(13) NULL,
    `is_blocked`      TINYINT     NULL,
    `is_activated`    TINYINT     NULL,
    `role_id_fk`      BIGINT      NOT NULL,
    `wallet_id_fk`    BIGINT      NULL,
    PRIMARY KEY (`user_account_id`),
    UNIQUE INDEX `login_UNIQUE` (`email` ASC) VISIBLE,
    INDEX `fk_user_account_wallet1_idx` (`wallet_id_fk` ASC) VISIBLE,
    INDEX `fk_user_account_role1_idx` (`role_id_fk` ASC) VISIBLE,
    CONSTRAINT `fk_user_account_wallet1`
        FOREIGN KEY (`wallet_id_fk`)
            REFERENCES `bullfinch_test`.`wallet` (`wallet_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_account_role1`
        FOREIGN KEY (`role_id_fk`)
            REFERENCES `bullfinch_test`.`role` (`role_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bullfinch_test`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bullfinch_test`.`image`
(
    `image_id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `image_name` VARCHAR(36) NOT NULL,
    PRIMARY KEY (`image_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bullfinch_test`.`tattoo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bullfinch_test`.`tattoo`
(
    `tattoo_id`          BIGINT        NOT NULL AUTO_INCREMENT,
    `tattoo_name`        VARCHAR(25)   NOT NULL,
    `tattoo_description` VARCHAR(1000) NOT NULL,
    `tattoo_price`       DECIMAL(7, 2) NOT NULL,
    `is_allowed`         TINYINT       NOT NULL,
    `is_archived`        TINYINT       NOT NULL,
    `user_account_id_fk` BIGINT        NOT NULL,
    `image_id_fk`        BIGINT        NOT NULL,
    PRIMARY KEY (`tattoo_id`),
    INDEX `fk_tattoo_image1_idx` (`image_id_fk` ASC) VISIBLE,
    INDEX `fk_tattoo_user_account1_idx` (`user_account_id_fk` ASC) VISIBLE,
    CONSTRAINT `fk_tattoo_image1`
        FOREIGN KEY (`image_id_fk`)
            REFERENCES `bullfinch_test`.`image` (`image_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_tattoo_user_account1`
        FOREIGN KEY (`user_account_id_fk`)
            REFERENCES `bullfinch_test`.`user_account` (`user_account_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bullfinch_test`.`tattoo_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bullfinch_test`.`tattoo_order`
(
    `tattoo_order_id`          BIGINT        NOT NULL AUTO_INCREMENT,
    `tattoo_order_price`       DECIMAL(7, 2) NOT NULL,
    `date`                     BIGINT        NOT NULL,
    `tattoo_order_description` VARCHAR(250)  NOT NULL,
    `is_confirmed`             TINYINT       NOT NULL,
    `user_account_id_fk`       BIGINT        NOT NULL,
    `tattoo_id_fk`             BIGINT        NOT NULL,
    PRIMARY KEY (`tattoo_order_id`),
    INDEX `fk_order_user_account1_idx` (`user_account_id_fk` ASC) VISIBLE,
    INDEX `fk_tattoo_order_tattoo1_idx` (`tattoo_id_fk` ASC) VISIBLE,
    CONSTRAINT `fk_order_user_account1`
        FOREIGN KEY (`user_account_id_fk`)
            REFERENCES `bullfinch_test`.`user_account` (`user_account_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_tattoo_order_tattoo1`
        FOREIGN KEY (`tattoo_id_fk`)
            REFERENCES `bullfinch_test`.`tattoo` (`tattoo_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bullfinch_test`.`discount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bullfinch_test`.`discount`
(
    `discount_id`        BIGINT  NOT NULL AUTO_INCREMENT,
    `percent`            TINYINT NOT NULL,
    `user_account_id_fk` BIGINT  NOT NULL,
    PRIMARY KEY (`discount_id`),
    INDEX `fk_discount_user_account1_idx` (`user_account_id_fk` ASC) VISIBLE,
    CONSTRAINT `fk_discount_user_account1`
        FOREIGN KEY (`user_account_id_fk`)
            REFERENCES `bullfinch_test`.`user_account` (`user_account_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
