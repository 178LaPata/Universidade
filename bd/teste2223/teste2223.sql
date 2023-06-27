USE `gdb`;

-- a
CREATE TABLE IF NOT EXISTS `Administradores`(
    `Id` INT NOT NULL AUTO_INCREMENT,
    `Nome` VARCHAR(75) NOT NULL,
    `Categoria` VARCHAR(45) NOT NULL,
    `Competencias` TEXT NOT NULL,
    `Supervisor` INT NOT NULL,
    PRIMARY KEY (`Id`),
    FOREIGN KEY (`Supervisor`)
        REFERENCES `Administradores` (`Id`)
);

-- b
ALTER TABLE `Tabelas`
	DROP COLUMN Motor;
    
SELECT * FROM `Tabelas`;

-- c
SELECT BasesdeDados.Designacao AS Nome, BasesdeDados.Dimensao
FROM `BasesdeDados`
JOIN `Administradores` ON BasesdeDados.Administrador = Administradores.Id
WHERE Administradores.Nome = 'Hipólito Mestre';

-- d
SELECT SGBD.Designacao AS Nome
FROM `SGBD`
JOIN `SGBDBasesdeDados` ON SGBD.Id = SGBDBasesdeDados.SGBD
WHERE SGBDBasesdeDados.BasesdeDados = 1 OR SGBDBasesdeDados.BasesdeDados = 4 OR SGBDBasesdeDados.BasesdeDados = 20

-- e
CREATE VIEW `Vista` AS
    SELECT BasesdeDados.Designacao AS Nome, BasesdeDados.Dimensao AS Dimensao, BasesdeDados.Administrador AS Administrador
    FROM `BasesdeDados`
    JOIN `Administradores` ON BasesdeDados.Administrador = Administradores.Id
    WHERE BasesdeDados.Dimensao > 500
    ORDER BY BasesdeDados.Dimensao DESC;

-- f
UPDATE `BasesdeDados`
SET Administrador = 3
WHERE Id = 1 OR Id = 9;

-- g
UPDATE `BasesdeDados`
    SET Dimensao = (SELECT SUM(tab.Tamanho) FROM Tabelas AS tab WHERE tab.BasedeDados = 5)
    WHERE Id = 5;

-- h
CREATE PROCEDURE `todasBasesdeDados`(IN `id` INT)
    BEGIN
        SELECT *
        FROM `BasesdeDados`
        WHERE Administrador = id;
    END;