# Equipamentos — MVC CRUD (Thymeleaf + H2)

## Requisitos
- Java 17+
- Maven 3.9+

## Como rodar
```bash
mvn spring-boot:run
# ou
mvn -DskipTests clean package
java -jar target/equipamentos-mvc-crud-1.0.0.jar
```

Acesse:
- App: http://localhost:8080/equipamentos
- H2 Console: http://localhost:8080/h2-console  
  JDBC URL: `jdbc:h2:file:./data/equipdb` | user: `teste` | 1234

## Estrutura
- `br.com.exemplo.equipamentos.controller`
  - `HomeController` (redirect para `/equipamentos`)
  - `EquipamentoController` (CRUD completo)
- `br.com.exemplo.equipamentos.model`
  - `Equipamento` (entidade JPA, validação)
  - `EquipamentoRepository` (Spring Data JPA)
- `resources/templates`
  - `layout.html` (navbar + container)
  - `fragments/_messages.html` (alerts)
  - `equipamentos/lista.html`, `form.html`, `detalhes.html`
- `resources/static`
  - `css/style.css`, `js/app.js`

Direitos Reservados - Prof. Salatiel Luz Marinho - FIAP