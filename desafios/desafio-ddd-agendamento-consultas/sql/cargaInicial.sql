-- Script de Carga para Tabelas - Oracle

-- Carga de Pacientes
INSERT INTO pacientes (nome, email) VALUES ('João Silva', 'joao.silva@email.com');
INSERT INTO pacientes (nome, email) VALUES ('Maria Oliveira', 'maria.oliveira@email.com');
INSERT INTO pacientes (nome, email) VALUES ('Carlos Souza', 'carlos.souza@email.com');
INSERT INTO pacientes (nome, email) VALUES ('Ana Pereira', 'ana.pereira@email.com');
INSERT INTO pacientes (nome, email) VALUES ('Paulo Santos', 'paulo.santos@email.com');

-- Carga de Médicos
INSERT INTO medicos (nome, crm) VALUES ('Dr. Ricardo Lima', 'CRM12345');
INSERT INTO medicos (nome, crm) VALUES ('Dra. Beatriz Costa', 'CRM67890');
INSERT INTO medicos (nome, crm) VALUES ('Dr. Eduardo Rocha', 'CRM11223');
INSERT INTO medicos (nome, crm) VALUES ('Dra. Fernanda Almeida', 'CRM44556');
INSERT INTO medicos (nome, crm) VALUES ('Dr. Felipe Martins', 'CRM77889');

-- Carga de Consultas
-- Vamos supor que os IDs dos pacientes e médicos são gerados automaticamente
-- ou conhecidos após inserção, então vamos usar valores fictícios.

-- João Silva (Paciente ID = 1) com Dr. Ricardo Lima (Médico ID = 1)
INSERT INTO consultas (paciente_id, medico_id, inicio, fim)
VALUES (1, 1, TO_TIMESTAMP('2025-08-20 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2025-08-20 08:30:00', 'YYYY-MM-DD HH24:MI:SS'));

-- Maria Oliveira (Paciente ID = 2) com Dra. Beatriz Costa (Médico ID = 2)
INSERT INTO consultas (paciente_id, medico_id, inicio, fim)
VALUES (2, 2, TO_TIMESTAMP('2025-08-20 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2025-08-20 09:30:00', 'YYYY-MM-DD HH24:MI:SS'));

-- Carlos Souza (Paciente ID = 3) com Dr. Eduardo Rocha (Médico ID = 3)
INSERT INTO consultas (paciente_id, medico_id, inicio, fim)
VALUES (3, 3, TO_TIMESTAMP('2025-08-20 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2025-08-20 10:30:00', 'YYYY-MM-DD HH24:MI:SS'));

-- Ana Pereira (Paciente ID = 4) com Dra. Fernanda Almeida (Médico ID = 4)
INSERT INTO consultas (paciente_id, medico_id, inicio, fim)
VALUES (4, 4, TO_TIMESTAMP('2025-08-20 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2025-08-20 11:30:00', 'YYYY-MM-DD HH24:MI:SS'));

-- Paulo Santos (Paciente ID = 5) com Dr. Felipe Martins (Médico ID = 5)
INSERT INTO consultas (paciente_id, medico_id, inicio, fim)
VALUES (5, 5, TO_TIMESTAMP('2025-08-20 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2025-08-20 13:30:00', 'YYYY-MM-DD HH24:MI:SS'));

COMMIT;