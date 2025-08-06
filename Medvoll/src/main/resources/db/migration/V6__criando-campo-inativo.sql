ALTER TABLE medicos ADD COLUMN ativo smallint;
update medicos set ativo = 1;