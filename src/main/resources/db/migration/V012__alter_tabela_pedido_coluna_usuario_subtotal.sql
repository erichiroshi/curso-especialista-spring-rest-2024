ALTER TABLE `pedido` 
CHANGE `subtotal` `subtotal` DECIMAL(10,2) NULL DEFAULT NULL;

ALTER TABLE `pedido`
CHANGE `cliente_id` `usuario_cliente_id` BIGINT NOT NULL;