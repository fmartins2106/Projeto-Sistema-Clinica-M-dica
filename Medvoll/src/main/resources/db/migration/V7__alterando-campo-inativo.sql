ALTER TABLE medicos DROP COLUMN ativo;

-- Adiciona a coluna 'ativo' se não existir
ALTER TABLE medicos ADD COLUMN IF NOT EXISTS ativo BOOLEAN;

-- Garante que todos comecem como ativos
UPDATE medicos SET ativo = true WHERE ativo IS NULL;

