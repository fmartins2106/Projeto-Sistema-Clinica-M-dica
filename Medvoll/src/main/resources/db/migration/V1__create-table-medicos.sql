CREATE TABLE medicos (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL CHECK( nome <> ''),
    crm VARCHAR(6) NOT NULL UNIQUE CHECK( crm <> ''),
    especialidade VARCHAR(100) NOT NULL CHECK( especialidade <> ''),
    logradouro VARCHAR(100) NOT NULL CHECK( logradouro <> ''),
    bairro VARCHAR(100) NOT NULL CHECK( bairro <> ''),
    cep VARCHAR(9) NOT NULL CHECK( cep <> ''),
    complemento VARCHAR(100) CHECK( complemento <> ''),
    numero VARCHAR(20) CHECK( numero <> ''),
    uf CHAR(2) NOT NULL CHECK( uf <> ''),
    cidade VARCHAR(100) NOT NULL CHECK( cidade <> '')
);

