CREATE TABLE pedido
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    sub_total            DECIMAL(10, 2)        not NULL,
    taxa_frete           DECIMAL(10, 2)        not NULL,
    valor_total          DECIMAL(10, 2)        not NULL,
    status               varchar(10)           not NULL,
    data_criacao         datetime              not NULL,
    data_confirmacao     datetime              NULL,
    data_cancelamento    datetime              NULL,
    data_entrega         datetime              NULL,
    forma_pagamento_id   BIGINT                NOT NULL,
    restaurante_id       BIGINT                NOT NULL,
    cliente_id           BIGINT                NOT NULL,
    endereco_cep         VARCHAR(255)          NULL,
    endereco_logradouro  VARCHAR(255)          NULL,
    endereco_numero      VARCHAR(255)          NULL,
    endereco_complemento VARCHAR(255)          NULL,
    endereco_bairro      VARCHAR(255)          NULL,
    endereco_cidade_id   BIGINT                NULL,
    CONSTRAINT pk_pedido PRIMARY KEY (id)
);

CREATE TABLE item_pedido
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    preco_unitario DECIMAL(10, 2)        not NULL,
    preco_total    DECIMAL(10, 2)        not NULL,
    quantidade     smallint           not NULL,
    observacao     VARCHAR(255)          not NULL,
    pedido_id      BIGINT                NOT NULL,
    produto_id     BIGINT                NOT NULL,
    CONSTRAINT pk_itempedido PRIMARY KEY (id),
    unique key uk_item_pedido_produto (pedido_id, produto_id)
);

ALTER TABLE item_pedido
    ADD CONSTRAINT FK_ITEMPEDIDO_ON_PEDIDO FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE item_pedido
    ADD CONSTRAINT FK_ITEMPEDIDO_ON_PRODUTO FOREIGN KEY (produto_id) REFERENCES produto (id);

ALTER TABLE pedido
    ADD CONSTRAINT FK_PEDIDO_ON_CLIENTE FOREIGN KEY (cliente_id) REFERENCES usuario (id);

ALTER TABLE pedido
    ADD CONSTRAINT FK_PEDIDO_ON_ENDERECO_CIDADE FOREIGN KEY (endereco_cidade_id) REFERENCES cidade (id);

ALTER TABLE pedido
    ADD CONSTRAINT FK_PEDIDO_ON_FORMA_PAGAMENTO FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento (id);

ALTER TABLE pedido
    ADD CONSTRAINT FK_PEDIDO_ON_RESTAURANTE FOREIGN KEY (restaurante_id) REFERENCES restaurante (id);

ALTER TABLE cidade
    MODIFY nome VARCHAR(80) NOT NULL;

ALTER TABLE cozinha
    MODIFY nome VARCHAR(80) NOT NULL;

ALTER TABLE estado
    MODIFY nome VARCHAR(80) NOT NULL;