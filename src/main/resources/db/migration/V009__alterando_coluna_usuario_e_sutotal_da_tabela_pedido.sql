ALTER TABLE pedido
    ADD subtotal DECIMAL NULL;

ALTER TABLE pedido
    DROP COLUMN sub_total;