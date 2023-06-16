-- 1
CREATE TABLE IF NOT EXISTS `Explicacoes` (
    `Nr` INTEGER NOT NULL,
    `Data` DATE NOT NULL,
    `HoraInicio` TIME NOT NULL,
    `HoraFim` TIME NOT NULL,
    `Disciplina` INTEGER NOT NULL,
    `Monitor` INTEGER NOT NULL,
    `Estudante` INTEGER NOT NULL,
    `Sumario` VARCHAR(150) NOT NULL,
    `Observacoes` TEXT,
    FOREIGN KEY(`Disciplina`) REFERENCES `Disciplina`(`Nr`),
    FOREIGN KEY(`Monitor`) REFERENCES `Monitor`(`Nr`),
    FOREIGN KEY(`Estudante`) REFERENCES `Estudante`(`Nr`)
);

-- 2
SELECT Estudante.Nome
FROM Estudante
JOIN Explicacoes ON Estudante.Nr = Explicacoes.Estudante
JOIN Monitor ON Explicacoes.Monitor = Monitor.Nr
WHERE Monitor.Nome 'Josefa Bracara Silva' OR Monitor.Nome = 'Alberto Ramos Pinto' 
AND Explicacoes.Data >= '2018-12-01' AND Explicacoes.Data <= '2018-12-31'; 

-- 3
DELETE FROM Explicacoes 
WHERE Estudante IN (SELECT Id FROM Estudante WHERE Ano = 4 AND Escola = 'Ases do Saber');

-- 4
CREATE PROCEDURE GetTotalHorasExplicacoes (IN alunoId INT, IN disciplinaId INT)
BEGIN
    DECLARE horaInicio TIME;
    DECLARE horaFim TIME;
    DECLARE totalHoras TIME;

    SELECT HoraInicio INTO horaInicio
    FROM Explicacoes
    JOIN Estudante ON Explicacoes.Estudante = Estudante.Nr
    WHERE Estudante.Nr = alunoId AND Disciplina.Nr = disciplinaId;
    
    SELECT HoraFim INTO horaFim
    FROM Explicacoes
    JOIN Estudante ON Explicacoes.Estudante = Estudante.Nr
    WHERE Estudante.Nr = alunoId AND Disciplina.Nr = disciplinaId;

    SET totalHoras = TIMEDIFF(horaFim, horaInicio);

    IF totalHoras IS NOT NULL THEN
        SELECT totalHoras AS 'Total de horas de explicação';
    ELSE
        SELECT 'Nenhuma explicação encontrada para o aluno.';
    END IF;
END;
