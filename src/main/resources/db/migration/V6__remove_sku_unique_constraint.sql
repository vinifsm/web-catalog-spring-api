-- Migration para remover a restrição UNIQUE do campo SKU na tabela product
-- V6__remove_sku_unique_constraint.sql

-- Remover a restrição UNIQUE do campo SKU
ALTER TABLE `product` DROP INDEX `sku`;

-- O campo SKU ainda será NOT NULL, mas não será mais único
-- Isso permite que múltiplos produtos tenham o mesmo SKU
