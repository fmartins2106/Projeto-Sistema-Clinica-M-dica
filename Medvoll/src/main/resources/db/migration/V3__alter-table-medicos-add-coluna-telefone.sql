-- 1. Adiciona a coluna como NULL permitida
ALTER TABLE medicos ADD COLUMN telefone VARCHAR(20);

-- 2. Atualiza os registros antigos com algum valor padrão ou baseado em regra
UPDATE medicos SET telefone = '000000000';  -- ou qualquer valor default aceitável

-- 3. Torna a coluna obrigatória (NOT NULL)
ALTER TABLE medicos ALTER COLUMN telefone SET NOT NULL;
