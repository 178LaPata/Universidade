USE `GBD`;

INSERT INTO `Administradores` 
VALUES
	(1, 'Hipólito Mestre', 'Principal', 'Conhecimento completo da BD', 1),
	(2, 'João Silva', 'Intermedio', 'DBA parcial', 1),
	(3, 'Ana Francisca Tolerante', 'Junior', 'DBA supervisão', 2),
	(4, 'Maria Almeida', 'Junior', 'Suporte a utilizadores', 3),
	(5, 'Pedro Ponte', 'Junior', 'Monitorização de query', 1),
	(6, 'Antonio Pereira', 'Sénior', 'Segurança de dados', 1),
	(7, 'Paula Dias', 'Sénior', 'Otimização de queries', 1),
	(8, 'Rui Costa', 'Intermédio', 'Resolução de problemas', 6);

INSERT INTO `BasesDeDados` 
VALUES
	(1, 'Los Pollos Hermanos', 5, 10, 'VARCHAR', 350.25, 'Backup diário', NULL),
	(2, 'C&B', 1, 12, 'VARCHAR', 425.50, 'Credenciais de acesso', NULL),
	(3, 'CartmanLand', 3, 14, 'INT', 625.75, 'Monitorização contínua', NULL),
	(4, 'Umbrella Corporation', 4, 8, 'DECIMAL', 212.85, 'Bloqueio parcial', NULL),
	(5, 'Kwik E Mart', 2, 5, 'VARCHAR', 250.10, 'Cópias de segurança diárias', 'Cena Random'),
	(6, 'Recursos Humanos', 6, 9, 'VARCHAR', 150.25, 'Credenciais de acesso', 'Departamento de RH'),
	(7, 'Gringotts', 1, 10, 'DECIMAL', 425.75, 'Bloqueio total', NULL),
	(8, 'AquiExpress', 8, 8, 'VARCHAR', 212.50, 'Credenciais de acesso', NULL),
	(9, 'Wayne Industries', 1, 11, 'INT', 654.85, 'Bloqueio parcial', NULL),
	(10, 'Ghostbusters Inc', 2, 14, 'VARCHAR', 450.70, 'Bloqueio total', NULL),
    (11, 'Monsters Inc', 3, 12, 'VARCHAR', 275.50, 'Backup diário', NULL),
	(12, 'Skaples', 5, 9, 'INT', 425.25, 'Monitorização contínua', NULL),
	(13, 'Vórtane', 4, 15, 'VARCHAR', 750.00, 'Credenciais de acesso', NULL),
	(14, 'Restaurante Gusteaus', 6, 7, 'DECIMAL', 185.35, 'Cópias de segurança diárias',NULL),
	(15, 'Stark Industries', 2, 10, 'VARCHAR', 500.50, 'Bloqueio total', NULL),
	(16, 'Dunder Mifflin', 1, 8, 'DECIMAL', 312.75, 'Credenciais de acesso', NULL),
	(17, 'Fábrica Willy Wonka', 1, 11, 'INT', 625.25, 'Bloqueio parcial', NULL),
	(18, 'Star Labs', 2, 14, 'VARCHAR', 875.00, 'Cópias de segurança diárias', NULL),
	(19, 'Honex Industries', 6, 6, 'INT', 225.85, 'Monitorização contínua', NULL),
	(20, 'Oscorp', 1, 13, 'VARCHAR', 450.70, 'Bloqueio total', NULL);
    
INSERT INTO `Tabelas` 
VALUES
	(1, 'Caixas', 'InnoDB', 'INT', 25.50, 3, 5000, 5, NULL),
	(2, 'Produtos', 'InnoDB', 'INT', 15.25, 2, 200000, 5, NULL),
	(3, 'Lojas', 'InnoDB', 'VARCHAR', 35.75, 1, 3000, 5, NULL),
    (4, 'Clientes', 'InnoDB', 'INT, VARCHAR', 75.75, 1, 600000, 5, NULL),
	(5, 'Seccao', 'InnoDB', 'VARCHAR', 35.75, 1, 600000, 5, NULL);
    
INSERT INTO `SGBD`
VALUES 
	(1,'MySQL', 'localhost', 3306, '8.0.21'),
	(2,'PostgreSQL', 'localhost', 5432, '13.2'),
	(3,'Oracle', 'localhost', 1521, '19c'),
	(4,'SQL Server', 'localhost', 1433, '2019'),
	(5,'MongoDB', 'localhost', 27017, '4.4'),
	(6,'Couchbase', 'localhost', 8091, '7.0'),
	(7,'DB2', 'localhost', 50000, '11.5.6');
       
INSERT INTO `SGBDBasesDeDados`
VALUES 
	(1, 1),
	(1, 2),
	(1, 3),
	(4, 4),
	(4, 5),
	(5, 6),
	(2, 7),
	(5, 8),
	(2, 9),
	(3, 10),
    (3, 11),
	(1, 12),
	(6, 13),
	(7, 14),
	(6, 15),
	(5, 16),
	(2, 17),
	(7, 18),
	(1, 19),
	(6, 20);
       
