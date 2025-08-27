# Clínica – Agenda de Consultas (DDD – DAO + Service + Console/Swing)

**Pontos-chave:**
- Domínio: `domain/` (Entidades `Paciente`, `Medico`, `Consulta`).
- Portas de saída (DDD): `dao/` + `dao.jdbc/` (implementações Oracle via JDBC).
- Serviços de aplicação: `service/` (casos de uso – agendar, alterar, excluir, listar).
- Interfaces de usuário: `ui.console/` e `ui.swing/` (listar/alterar/excluir/agendar).
- Infraestrutura: `config/OracleConnectionFactory.java` (lê `application.properties` com override por env).

## Requisitos
- Java 17+
- Maven 3.8+
- Banco: **Oracle** (mantido conforme solicitado)

## Configuração de conexão
Edite `src/main/resources/application.properties` **ou** use variáveis de ambiente:
```bash
export ORACLE_URL="jdbc:oracle:thin:@//localhost:1521/FREEPDB1"
export ORACLE_USER="system"
export ORACLE_PASSWORD="oracle"
```

## Scripts Oracle
Em `scripts/oracle/` há:
- `01_schema.sql` com criação de tabelas e exemplos.
- `02_drop.sql` para limpeza.

## Executar (Swing – padrão)
```bash
mvn -q -DskipTests package
java -cp target/clinica-agenda-ddd-1.1.0.jar:$(mvn -q -Dexec.classpathScope=runtime -Dexec.executable=echo --non-recursive exec:classpath) com.example.clinica.App
```

## Executar (Console)
```bash
mvn -q -DskipTests package
java -Dmode=console -cp target/clinica-agenda-ddd-1.1.0.jar:$(mvn -q -Dexec.classpathScope=runtime -Dexec.executable=echo --non-recursive exec:classpath) com.example.clinica.App
```

> No Windows, substitua `:` por `;` no classpath.

## Telas / Funcionalidades
- **Console:** menu com `Listar`, `Agendar`, `Alterar`, `Excluir`.
- **Swing:** abas `Listar` (tabela + atualizar), `Alterar` (formulário), `Excluir` (ID).  
  Datas devem ser informadas no formato `yyyy-MM-dd HH:mm`.

## Observações de DDD
- Entidades puras no domínio (sem dependência de infraestrutura).
- DAOs representam **portas de saída** (persistência) e são injetados nos **Serviços** (casos de uso).
- UI não conhece JDBC – fala com `service`.

Bom proveito! — Prof. Salatiel Marinho

---

## Solução de problemas – ORA-12541 (no listener)

Se ao abrir o Swing aparecer erro **ORA-12541: No listener**:

1) **Confirme o listener**  
   - Windows (Prompt):

     ```bat
     lsnrctl status
     lsnrctl start
     ```
   - Windows (GUI): abra `services.msc` e inicie o serviço **TNS Listener** (ex.: *OracleOraDB23Home1TNSListener*).

   - Linux/macOS:

     ```bash
     lsnrctl status
     sudo lsnrctl start
     ```

2) **Teste a conexão via SQL\*Plus/SQLcl**  
   ```bash
   sqlplus system/oracle@//localhost:1521/FREEPDB1
   # ou, para Oracle XE 21c:
   sqlplus system/oracle@//localhost:1521/XEPDB1
   # em instalações antigas (SID):
   sqlplus system/oracle@localhost:1521/XE
   ```

3) **Ajuste o `oracle.url` conforme o seu serviço**  
   Edite `src/main/resources/application.properties` **ou** defina variáveis de ambiente antes de executar:
   ```bash
   export ORACLE_URL="jdbc:oracle:thin:@//localhost:1521/XEPDB1"
   export ORACLE_USER="system"
   export ORACLE_PASSWORD="oracle"
   ```
   Exemplos de URL:
   - Serviço (recomendado): `jdbc:oracle:thin:@//localhost:1521/FREEPDB1`
   - XE 21c: `jdbc:oracle:thin:@//localhost:1521/XEPDB1`
   - Com SID (legado): `jdbc:oracle:thin:@localhost:1521:XE`

4) **Docker (se aplicável)**  
   Garanta o mapeamento de porta: `-p 1521:1521` e que o container está *running*:
   ```bash
   docker ps
   docker logs <nome_container>
   ```

> A partir desta correção, a UI Swing não fecha ao falhar a primeira listagem – será mostrado um diálogo e a tabela ficará vazia até a conexão estar ok.
