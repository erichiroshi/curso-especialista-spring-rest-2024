CREATE TABLE cidade
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    nome_cidade     VARCHAR(80)     NOT NULL,
    nome_estado     VARCHAR(80)     NOT NULL,
    PRIMARY KEY (id)
) engine=InnoDB;