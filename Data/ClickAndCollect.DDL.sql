-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema practica
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema practica
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `practica` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `practica` ;

-- -----------------------------------------------------
-- Table `practica`.`baskets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `practica`.`baskets` (
  `basket_id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`basket_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `practica`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `practica`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `image_ref` VARCHAR(200) NOT NULL,
  `price` DOUBLE NOT NULL,
  `stock` INT NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `practica`.`baskets_has_products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `practica`.`baskets_has_products` (
  `basket_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`basket_id`, `product_id`),
  INDEX `fk_baskets_has_products_products1_idx` (`product_id` ASC),
  INDEX `fk_baskets_has_products_baskets1_idx` (`basket_id` ASC),
  CONSTRAINT `fk_baskets_has_products_baskets1`
    FOREIGN KEY (`basket_id`)
    REFERENCES `practica`.`baskets` (`basket_id`),
  CONSTRAINT `fk_baskets_has_products_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `practica`.`products` (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `practica`.`details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `practica`.`details` (
  `detail_id` INT NOT NULL AUTO_INCREMENT,
  `attribute` VARCHAR(60) NULL DEFAULT NULL,
  PRIMARY KEY (`detail_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `practica`.`ingredients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `practica`.`ingredients` (
  `ingredient_id` INT NOT NULL AUTO_INCREMENT,
  `ingredient` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`ingredient_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `practica`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `practica`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(30) NOT NULL,
  `customer_name` VARCHAR(45) NOT NULL,
  `customer_email` VARCHAR(45) NOT NULL,
  `customer_telephone` VARCHAR(45) NOT NULL,
  `amount` DECIMAL(10,2) NOT NULL,
  `date` DATE NOT NULL,
  `time` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `practica`.`orders_has_products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `practica`.`orders_has_products` (
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `unit_price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`order_id`, `product_id`),
  INDEX `fk_orders_has_products_products1_idx` (`product_id` ASC),
  INDEX `fk_orders_has_products_orders_idx` (`order_id` ASC),
  CONSTRAINT `fk_orders_has_products_orders`
    FOREIGN KEY (`order_id`)
    REFERENCES `practica`.`orders` (`order_id`),
  CONSTRAINT `fk_orders_has_products_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `practica`.`products` (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `practica`.`products_has_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `practica`.`products_has_details` (
  `product_id` INT NOT NULL,
  `detail_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `detail_id`),
  INDEX `fk_products_has_details_details1_idx` (`detail_id` ASC),
  INDEX `fk_products_has_details_products1_idx` (`product_id` ASC),
  CONSTRAINT `fk_products_has_details_details1`
    FOREIGN KEY (`detail_id`)
    REFERENCES `practica`.`details` (`detail_id`),
  CONSTRAINT `fk_products_has_details_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `practica`.`products` (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `practica`.`products_has_ingredients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `practica`.`products_has_ingredients` (
  `product_id` INT NOT NULL,
  `ingredient_id` INT NOT NULL,
  `grams_per_unit` INT NOT NULL,
  PRIMARY KEY (`product_id`, `ingredient_id`),
  INDEX `fk_products_has_ingredients_ingredients1_idx` (`ingredient_id` ASC),
  INDEX `fk_products_has_ingredients_products1_idx` (`product_id` ASC),
  CONSTRAINT `fk_products_has_ingredients_ingredients1`
    FOREIGN KEY (`ingredient_id`)
    REFERENCES `practica`.`ingredients` (`ingredient_id`),
  CONSTRAINT `fk_products_has_ingredients_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `practica`.`products` (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
