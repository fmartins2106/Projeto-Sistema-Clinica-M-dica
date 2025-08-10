create table consultas(
    id smallint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    medico_id integer not null,
    paciente_id integer not null,
    data_consulta TIMESTAMP,
    CONSTRAINT FK_id_medico_consulta FOREIGN KEY (medico_id) references medicos(id),
    CONSTRAINT FK_id_paciente_consulta FOREIGN KEY (paciente_id) references paciente(id)
);