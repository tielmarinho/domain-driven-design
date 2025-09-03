# FIAP – Restaurante (MVC + DAO + Factory + Singleton + BO)

Projeto didático em Java 17 para aula de DDD/Arquitetura, usando a metáfora do restaurante.

## Rodar rápido (sem banco) – DAO em memória
```bash
# Linux/macOS/WSL
export USE_INMEMORY=true
mvn -q exec:java

# Windows PowerShell
$env:USE_INMEMORY="true"
mvn -q exec:java
```

## Rodar com Oracle
1. Crie a tabela:
   ```sql
   -- use o script em script/01 - Carga.sql
   ```
2. Defina as variáveis de ambiente:
   ```bash
   export ORACLE_URL="jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl"
   export ORACLE_USER="rm...."
   export ORACLE_PASSWORD="ddmmaa"
   ```
   No Windows PowerShell:
   ```powershell
   $env:ORACLE_URL="jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl"
   $env:ORACLE_USER="rm...."
   $env:ORACLE_PASSWORD="ddmmaa"
   ```
3. Execute:
   ```bash
   mvn -q exec:java
   ```

## Estrutura
```
domain/       -> Objetos de domínio (Business Objects)
interfaces/   -> Contratos (ex.: IPrato)
dao/          -> Implementações de acesso a dados (jdbc / memória)
factory/      -> ConnectionFactory e DaoFactory 
service/      -> Regras/orquestração de caso de uso
singleton/    -> LoggerSingleton
ui/           -> ConsoleView 
controller/   -> PedidoController
App.java      -> Main
```

## Exemplos de comandos na aplicação
- Informe IDs dos pratos para montar um pedido (ex.: `1,2,3`).
- O serviço calcula total e marca pagamento, registrando logs pelo Singleton.

---

Agora você pode os requisitos abaixo e participar do **desafio**:

- Criar entidades Java: Cliente, Pedido, ItemPedido, Conta em domain;
- Adicionar DAOs e interfaces (interfaces/ICliente, IPedido, IItemPedido, IConta) e implementações JDBC

Service:

- No PedidoService, adicionar fluxo para abrir pedido, incluir itens, fechar pedido.
- Criar/atualizar Conta (status PENDENTE → PAGO, PAGO_EM).

Controller/View:

- Menu no Console: Novo Pedido, Adicionar Item, Listar Pedidos.