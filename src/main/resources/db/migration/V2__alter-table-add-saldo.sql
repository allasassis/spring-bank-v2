ALTER TABLE clientes ADD saldo DOUBLE NOT NULL DEFAULT 0.0;
UPDATE clientes SET saldo = 0.0;

