-- Migration para vincular usuários e produtos a lojas específicas
-- V5__add_store_relations_to_user_and_product.sql

-- Adicionar coluna store_id na tabela user
ALTER TABLE `user` ADD COLUMN `store_id` binary(16) NULL;

-- Adicionar coluna store_id na tabela product  
ALTER TABLE `product` ADD COLUMN `store_id` binary(16) NULL;

-- Adicionar foreign keys
ALTER TABLE `user` 
ADD CONSTRAINT `fk_user_store` 
FOREIGN KEY (`store_id`) REFERENCES `store`(`id`) ON DELETE SET NULL;

ALTER TABLE `product` 
ADD CONSTRAINT `fk_product_store` 
FOREIGN KEY (`store_id`) REFERENCES `store`(`id`) ON DELETE SET NULL;

-- Criar índices para melhor performance
CREATE INDEX `idx_user_store_id` ON `user`(`store_id`);
CREATE INDEX `idx_product_store_id` ON `product`(`store_id`);

-- Atualizar produtos existentes para vincular à primeira loja (se existir)
-- Isso é apenas para dados de teste - em produção você deve fazer manualmente
UPDATE `product` p 
SET `store_id` = (SELECT `id` FROM `store` LIMIT 1) 
WHERE `store_id` IS NULL;

-- Atualizar usuários STORE_OWNER existentes para vincular à primeira loja
UPDATE `user` u 
SET `store_id` = (SELECT `id` FROM `store` LIMIT 1) 
WHERE u.`role` = 'STORE_OWNER' AND u.`store_id` IS NULL;
