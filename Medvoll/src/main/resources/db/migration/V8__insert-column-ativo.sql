ALTER TABLE paciente ADD COLUMN IF NOT EXISTS ativo BOOLEAN;

UPDATE paciente SET ativo = true where ativo is null;