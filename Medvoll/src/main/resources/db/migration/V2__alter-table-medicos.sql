ALTER TABLE medicos add column email VARCHAR(100) UNIQUE NOT NULL CHECK(email <> '' );
