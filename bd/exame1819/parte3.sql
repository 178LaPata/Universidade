-- 1
SELECT * FROM Alugueres
JOIN Clientes ON Alugueres.Cliente = Clientes.Id
WHERE Clientes.Id = 1 OR Clientes.Id = 2 OR Clientes.Id = 3;

-- 2
UPDATE Trotinete
SET CustoDia = CustoDia * 1.1
WHERE YEAR(DataAquisicao) = 2018;

-- 3 nao sei se esta bem
CREATE VIEW Top10ClientesAlugueres AS
SELECT Telefones.Telefone AS ContactoTelefonico, COUNT(Alugueres.Id) AS NumeroAlugueres
FROM Alugueres
JOIN Clientes ON Alugueres.Cliente = Clientes.Id
JOIN Telefones ON Clientes.Id = Telefones.Cliente
GROUP BY Telefones.Telefone
ORDER BY NumeroAlugueres DESC
LIMIT 10;

-- 4 nao sei fazer





