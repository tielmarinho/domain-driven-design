# Clínica – Agenda de Consultas (DAO + Service + Console/Swing)

🎯 Objetivo

		Aplicar organização de pacotes e implementar funcionalidades do projeto de Agendamento de Consultas, garantindo execução em Console e em Swing. 

🔗 Repositório base: https://github.com/tielmarinho/domain-driven-design/tree/main/desafios/desafio-ddd-agendamento-consultas

🧱 Estrutura de Pacotes (esperada)

📂 config → OracleConnectionFactory

📂 dao → 📁 jdbc → Consulta, Medico e Paciente

📂 domain → Consulta, Medico, Paciente

📂 service → Consulta, Medico e Paciente

🧭 App

💡 Verificar possibilidade de incluir pacotes: 📂 ui → 📂console → ConsoleMain  |  📂 ui → 📂swing → SwingMain

✅ Tarefas Obrigatórias — marque ao concluir

[  ] Aplicar a estrutura de pacotes indicada nesse documento ao projeto.

[  ] Avaliar a distribuição e os nomes das classes se estão corretos e/ou sugerir uma nova nomenclatura.

[  ] Desenvolver/complementar a inclusão de Médicos(as) (service + dao + console/swing).

[  ] Desenvolver/complementar a inclusão de Pacientes (service + dao + console/swing).

[  ] Garantir o funcionamento em Console e Swing das inclusões de consultas, paciente e médico(a).

[  ] Rodar o projeto local (Oracle) e explicar ao professor o funcionamento de cada etapa desenvolvida/melhorada para Consultas, Médico(a) e Pacientes.

[  ] Apontar 3 evoluções possíveis para o projeto de Agendamento de Consultas e apresentar 1 evolução já implementada e rodando no seu projeto corrente.

📝 Campos para preencher (estudante)

Nome:

RM:

Link do repositório:

Evolução implementada (resumo):

Observações sobre nomes/classes/pacotes:


🖥️ Requisitos de Execução (Oracle)

Defina as variáveis de ambiente antes de rodar:

• ORACLE_URL (jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl)

• ORACLE_USER (rm...)

• ORACLE_PASSWORD (data_do_seu_nascimento)

---