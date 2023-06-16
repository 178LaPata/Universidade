-- 1
ALTER TABLE Investigador
ADD DataNascimento DATE NOT NULL;
ADD eMail VARCHAR(70) NOT NULL;

-- 2
SELECT Projeto.Id, Projeto.Orçamento
FROM Projeto
JOIN Investigador ON Projeto.Id = Investigador.Projeto
WHERE Projeto.Orçamento > 100000 AND Investigador.Categoria = 'A';

-- 3
CREATE VIEW ex3 AS 
SELECT Investigador.Nome, Projeto.Orçamento
FROM Investigador
JOIN Projeto ON Investigador.Projeto = Projeto.Id
ORDER BY Projeto.Orçamento DESC;

-- 4
DELETE FROM InvestigadorTarefa 
WHERE Tarefa IN (SELECT Id FROM Tarefa WHERE Designacao = 'Limpeza de Microscópio');
DELETE FROM Tarefa WHERE Designacao = 'Limpeza de Microscópio';

-- 5
CREATE FUNCTION ObterTempoTotal(investigador_id INT) RETURNS TIME
BEGIN
    DECLARE tempo_total TIME;
    
    SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(InvestigadorTarefa.Duracao))) INTO tempo_total
    FROM InvestigadorTarefa
    JOIN Investigador ON InvestigadorTarefa.Investigador = Investigador.Id
    WHERE Investigador.Id = investigador_id;
    
    RETURN tempo_total;
END;
