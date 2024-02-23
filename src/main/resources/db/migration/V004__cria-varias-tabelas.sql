CREATE TABLE forma_pagamento
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    descricao VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_formapagamento PRIMARY KEY (id)
);

CREATE TABLE grupo
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(80)           NOT NULL,
    CONSTRAINT pk_grupo PRIMARY KEY (id)
);

CREATE TABLE grupo_permissao
(
    grupo_id     BIGINT NOT NULL,
    permissao_id BIGINT NOT NULL
);

CREATE TABLE permissao
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    nome      VARCHAR(80)           NOT NULL,
    descricao VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_permissao PRIMARY KEY (id)
);

CREATE TABLE produto
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    nome           VARCHAR(80)           NOT NULL,
    descricao      VARCHAR(255)          NOT NULL,
    preco          DECIMAL(10, 2)        NOT NULL,
    ativo          BIT(1)                NOT NULL,
    restaurante_id BIGINT                NOT NULL,
    CONSTRAINT pk_produto PRIMARY KEY (id)
);

CREATE TABLE restaurante
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    nome                 VARCHAR(80)           NOT NULL,
    taxa_frete           DECIMAL(10, 2)        NOT NULL,
    cozinha_id           BIGINT                NOT NULL,
    data_cadastro        datetime              NOT NULL,
    data_atualizacao     datetime              NOT NULL,
    endereco_cep         VARCHAR(9)            NULL,
    endereco_logradouro  VARCHAR(100)          NULL,
    endereco_numero      VARCHAR(20)           NULL,
    endereco_complemento VARCHAR(60)           NULL,
    endereco_bairro      VARCHAR(60)           NULL,
    endereco_cidade_id   BIGINT                NULL,
    CONSTRAINT pk_restaurante PRIMARY KEY (id)
);

CREATE TABLE restaurante_forma_pagamento
(
    forma_pagamento_id BIGINT NOT NULL,
    restaurante_id     BIGINT NOT NULL
);

CREATE TABLE usuario
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    nome          VARCHAR(80)           NOT NULL,
    email         VARCHAR(255)          NOT NULL,
    senha         VARCHAR(255)          NOT NULL,
    data_cadastro datetime              NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);

CREATE TABLE usuario_grupo
(
    grupo_id   BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL
);

ALTER TABLE produto
    ADD CONSTRAINT FK_PRODUTO_ON_RESTAURANTE FOREIGN KEY (restaurante_id) REFERENCES restaurante (id);

ALTER TABLE restaurante
    ADD CONSTRAINT FK_RESTAURANTE_ON_COZINHA FOREIGN KEY (cozinha_id) REFERENCES cozinha (id);

ALTER TABLE restaurante
    ADD CONSTRAINT FK_RESTAURANTE_ON_ENDERECO_CIDADE FOREIGN KEY (endereco_cidade_id) REFERENCES cidade (id);

ALTER TABLE grupo_permissao
    ADD CONSTRAINT fk_gruper_on_grupo FOREIGN KEY (grupo_id) REFERENCES grupo (id);

ALTER TABLE grupo_permissao
    ADD CONSTRAINT fk_gruper_on_permissao FOREIGN KEY (permissao_id) REFERENCES permissao (id);

ALTER TABLE restaurante_forma_pagamento
    ADD CONSTRAINT fk_resforpag_on_forma_pagamento FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento (id);

ALTER TABLE restaurante_forma_pagamento
    ADD CONSTRAINT fk_resforpag_on_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurante (id);

ALTER TABLE usuario_grupo
    ADD CONSTRAINT fk_usugru_on_grupo FOREIGN KEY (grupo_id) REFERENCES grupo (id);

ALTER TABLE usuario_grupo
    ADD CONSTRAINT fk_usugru_on_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE cidade
    MODIFY nome VARCHAR(80);

ALTER TABLE cozinha
    MODIFY nome VARCHAR(80);

ALTER TABLE estado
    MODIFY nome VARCHAR(80);