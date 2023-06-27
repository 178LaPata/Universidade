-- DROP SCHEMA `GBD`;
CREATE SCHEMA IF NOT EXISTS `GBD`;
USE `GBD`;

-------------------------------------------------------- a)

CREATE TABLE `Administradores`(
	Id INT NOT NULL,
    Nome VARCHAR(75) NOT NULL,
    Categoria VARCHAR(45) NOT NULL,
    Competencias TEXT NOT NULL,
    Supervisor INT NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (Supervisor)
		REFERENCES `Administradores`(Id)
);

--------------------------------------------------------

CREATE TABLE `BasesDeDados`(
	Id INT NOT NULL,
    Designacao VARCHAR(75) NOT NULL,
    Administrador INT NOT NULL,
    NrTabelas INT NOT NULL,
    TipoCarateres VARCHAR(45) NOT NULL,
    Dimensao DECIMAL(8,2) NOT NULL,
    Seguranca TEXT NOT NULL,
    Observacoes TEXT,
    PRIMARY KEY (Id),
    FOREIGN KEY (Administrador)
		REFERENCES `Administradores` (Id)
);

CREATE TABLE `Tabelas`(
	Id INT NOT NULL,
    Designacao VARCHAR(75) NOT NULL,
    Motor VARCHAR(75) NOT NULL,
    TipoRegistos VARCHAR(45) NOT NULL,
	Tamanho DECIMAL(8,2) NOT NULL,
    Grau INT NOT NULL,
    Cardinalidade INT NOT NULL,
    BasedeDados INT NOT NULL,
    Observacoes TEXT,
    PRIMARY KEY (Id),
    FOREIGN KEY (BasedeDados)
		REFERENCES `BasesDeDados` (Id)
);

CREATE TABLE `SGBD`(
	Id INT NOT NULL,
    Designacao VARCHAR(75) NOT NULL,
    Host_ VARCHAR(75),
    Porta INT,
    Versao VARCHAR(45),
    PRIMARY KEY(Id)
);

CREATE TABLE `SGBDBasesDeDados`(
	SGBD INT NOT NULL,
    BasesDeDados INT NOT NULL,
    PRIMARY KEY (SGBD,BasesDeDados)
);

