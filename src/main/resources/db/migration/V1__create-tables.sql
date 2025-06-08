CREATE TABLE TbVeiculo (
    id INT AUTO_INCREMENT,
    fabricante VARCHAR(75) NOT NULL,
    modelo VARCHAR(75) NOT NULL,
    placa CHAR(7) NOT NULL UNIQUE,
    ano_fabricacao YEAR NOT NULL,
    PRIMARY KEY (id),
    CHECK (CHAR_LENGTH(placa) = 7)
);

CREATE TABLE TbManutencao (
    id INT AUTO_INCREMENT,
    id_veiculo INT NOT NULL,
    id_oficina INT NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    custo DECIMAL(10, 2) NOT NULL,
    data DATE NOT NULL,
    ativa TINYINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_veiculo) REFERENCES TbVeiculo(id)
);
