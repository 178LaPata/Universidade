-- 1
CREATE TABLE IF NOT EXISTS `AutorLivro`
(
    `Autor`   INTEGER NOT NULL,
    `Livro`   INTEGER NOT NULL,
    FOREIGN KEY(`Autor`) REFERENCES `Autor`(`id`),
    FOREIGN KEY(`Livro`) REFERENCES `Livro`(`id`)
);

-- 2
SELECT Livro.Editora, Livro.Titulo
FROM Livro
JOIN Editora ON Livro.Editora = Editora.Id
WHERE Editora.Nome = 'Prensar'
ORDER BY Livro.Titulo ASC;

-- 3
SELECT Autor.Nome 
FROM Autor
JOIN AutorLivro ON Autor.Id = AutorLivro.Autor
JOIN Livro ON AutorLivro.Livro = Livro.Id
JOIN Categoria ON Livro.Categoria = Categoria.Id
WHERE Categoria.Designacao = 'Aventura' 
AND Livro.DataLancamento >= '2020-01-01' 
AND Livro.DataLancamento <= '2020-12-31';

-- 4
DELETE FROM Livro WHERE Livro.Texto LIKE '%razoavel%';

-- 5
CREATE PROCEDURE GetCriticaByLivroId (IN livroId INT)
BEGIN
    DECLARE critica TEXT;

    SELECT Critica INTO critica
    FROM Livro
    WHERE Livro.Id = livroId;

    IF critica IS NOT NULL THEN
        SELECT Critica AS 'Critica do Livro';
    ELSE
        SELECT 'Nenhuma crítica encontrada para o livro.';
    END IF;
END;

