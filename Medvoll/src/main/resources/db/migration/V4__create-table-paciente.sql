CREATE TABLE paciente(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(100) not null CHECK( nome <> ''),
    email VARCHAR(50) not null UNIQUE UNIQUE CHECK( email <> ''),
    cpf VARCHAR(14) not null UNIQUE CHECK( cpf <> ''),
    logradouro VARCHAR(100) NOT NULL CHECK( logradouro <> ''),
    bairro VARCHAR(100) NOT NULL CHECK( bairro <> ''),
    cep VARCHAR(9) NOT NULL CHECK( cep <> ''),
    complemento VARCHAR(100) CHECK( complemento <> ''),
    numero VARCHAR(20) CHECK( numero <> ''),
    uf CHAR(2) NOT NULL CHECK( uf <> ''),
    cidade VARCHAR(100) NOT NULL CHECK( cidade <> '')


)