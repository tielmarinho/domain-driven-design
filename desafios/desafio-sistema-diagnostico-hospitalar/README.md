# 💉 Desafio: Sistema de Diagnóstico Hospitalar

## 🧾 Enunciado

Você foi contratado(a) para desenvolver um sistema simples de **diagnóstico hospitalar**. O sistema deve ser capaz de **realizar diagnósticos com base nos sintomas informados** e retornar orientações ao paciente.

Para isso, você deverá usar uma **classe abstrata** chamada `Diagnostico`, que servirá de base para tipos específicos de diagnósticos (como **Gripe**, **Covid**, etc).

---

## 📌 Requisitos

### 1. Classe abstrata: `Diagnostico`

**Atributos:**
- `String[] sintomasComuns`

**Métodos abstratos:**
- `String avaliarPaciente(String[] sintomasInformados)`

**Método concreto:**
- `void exibirSintomasComuns()` → Exibe os sintomas comuns da doença.

---

### 2. Criar pelo menos duas classes concretas que herdem de `Diagnostico`:

- `DiagnosticoGripe`
- `DiagnosticoCovid`

Cada uma deve implementar o método `avaliarPaciente`, **comparando os sintomas informados com os sintomas comuns** da doença e retornando uma `String` com o diagnóstico e orientação.  
Exemplo:  
> "Provável Gripe. Repouso e hidratação recomendados."

---

### 3. Criar uma classe `Principal` com o método `main`:

- Simule o recebimento de sintomas do paciente (via `Scanner` ou `array`).
- Instancie objetos de `DiagnosticoGripe` e `DiagnosticoCovid`.
- Use os métodos para **avaliar os sintomas** e **imprimir o diagnóstico**.

---

## 💡 Dicas para Implementação

- Use:  
  ```java
  Arrays.asList(sintomasComuns).contains(sintoma)
  ```  
  para verificar se um sintoma informado pertence à doença.

- Utilize um **contador** para medir quantos sintomas coincidem e considere um **limiar** (ex: **2 ou mais sintomas** coincidentes).

- **Reutilize código** entre as subclasses, aproveitando o método concreto `exibirSintomasComuns()` da superclasse.