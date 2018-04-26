INSERT INTO CLIENTES(nome, sobrenome, email, data_criacao, foto)values('Leonardo', 'Lima', 'leo@gmail.com', '2018-04-12','');
INSERT INTO CLIENTES(nome, sobrenome, email, data_criacao, foto)values('Juliana', 'Lima', 'ju@gmail.com', '2018-04-13','');
INSERT INTO CLIENTES(nome, sobrenome, email, data_criacao, foto)values('Maria', 'Lima', 'leo@gmail.com', '2018-04-12','');
INSERT INTO CLIENTES(nome, sobrenome, email, data_criacao, foto)values('Joao', 'Lima', 'ju@gmail.com', '2018-04-13','');
INSERT INTO CLIENTES(nome, sobrenome, email, data_criacao, foto)values('Pedro', 'Lima', 'leo@gmail.com', '2018-04-12','');
INSERT INTO CLIENTES(nome, sobrenome, email, data_criacao, foto)values('Manuela', 'Lima', 'ju@gmail.com', '2018-04-13','');
INSERT INTO CLIENTES(nome, sobrenome, email, data_criacao, foto)values('Otavio', 'Lima', 'leo@gmail.com', '2018-04-12','');
INSERT INTO CLIENTES(nome, sobrenome, email, data_criacao, foto)values('Ricardo', 'Lima', 'ju@gmail.com', '2018-04-13','');
INSERT INTO CLIENTES(nome, sobrenome, email, data_criacao, foto)values('Jasmine', 'Lima', 'leo@gmail.com', '2018-04-12','');
INSERT INTO CLIENTES(nome, sobrenome, email, data_criacao, foto)values('Elaine', 'Lima', 'ju@gmail.com', '2018-04-13','');
INSERT INTO CLIENTES(nome, sobrenome, email, data_criacao, foto)values('Livia', 'Lima', 'leo@gmail.com', '2018-04-12','');
INSERT INTO CLIENTES(nome, sobrenome, email, data_criacao, foto)values('Guilherme', 'Lima', 'ju@gmail.com', '2018-04-13','');
INSERT INTO CLIENTES(nome, sobrenome, email, data_criacao, foto)values('Eduardo', 'Lima', 'leo@gmail.com', '2018-04-12','');
INSERT INTO CLIENTES(nome, sobrenome, email, data_criacao, foto)values('Carlos', 'Lima', 'ju@gmail.com', '2018-04-13','');
INSERT INTO CLIENTES(nome, sobrenome, email, data_criacao, foto)values('Jose', 'Lima', 'leo@gmail.com', '2018-04-12','');

INSERT INTO produtos(nome, preco, data_criacao)values('IPAD MINI 16 GB', 2100, NOW());
INSERT INTO produtos(nome, preco, data_criacao)values('GALAXY S9', 4300, NOW());
INSERT INTO produtos(nome, preco, data_criacao)values('IPHONE 8', 3500, NOW());
INSERT INTO produtos(nome, preco, data_criacao)values('MONITOR SAMSUNG', 500, NOW());
INSERT INTO produtos(nome, preco, data_criacao)values('TV SAMSUMG 55', 3500, NOW());
INSERT INTO produtos(nome, preco, data_criacao)values('TV LG 49', 2100, NOW());

INSERT INTO faturas (descricao, observacao, cliente_id, data_criacao) VALUES('Celulares e IPAD', null, 1, NOW());
INSERT INTO faturas_items (qtd, fatura_id, produto_id) VALUES(1, 1, 1);
INSERT INTO faturas_items (qtd, fatura_id, produto_id) VALUES(2, 1, 2);
INSERT INTO faturas_items (qtd, fatura_id, produto_id) VALUES(1, 1, 3);

INSERT INTO faturas (descricao, observacao, cliente_id, data_criacao) VALUES('Eletronicos', 'TVs e Computadores', 1, NOW());
INSERT INTO faturas_items (qtd, fatura_id, produto_id) VALUES(2, 2, 4);

