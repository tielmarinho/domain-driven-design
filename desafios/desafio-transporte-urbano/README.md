# Sistema Orientado a Objetos: Meios de Transporte Urbano

## 📝 Enunciado

Implemente um sistema orientado a objetos para representar meios de transporte urbano (**ônibus, metrô, bicicleta, patinete, etc**). 

Crie uma **classe abstrata `TransporteUrbano`** que define comportamentos e atributos comuns a todos os meios de transporte. 

Em seguida, implemente **subclasses** que representem tipos específicos de transporte, cada uma com suas próprias características.

---

## 📌 Requisitos

### Classe Abstrata: `TransporteUrbano`

#### Atributos:

- `String nome`
- `int capacidade`

#### Métodos:

- `void exibirInfo()`  
  ➝ método concreto que imprime as informações do transporte.

- `abstract void mover()`  
  ➝ método abstrato que deve ser implementado por cada transporte.

---

## 🚍 Subclasses Concretas

Crie ao menos três subclasses concretas, cada uma implementando o método `mover()` com um comportamento específico:

- `Ônibus`
- `Metrô`
- `Bicicleta`

---

## 🧪 Teste no `main`

- Instancie objetos de diferentes transportes.
- Utilize **polimorfismo** para chamar `mover()` e `exibirInfo()`.

### 🛠 Dicas:

- Utilize uma **lista (`ArrayList`)** para armazenar os transportes.
- Itere sobre a lista utilizando **polimorfismo**.

---

## 🌟 Bônus

### 💰 Método Opcional

Adicione o método:

```java
double calcularCustoViagem()
```

- Declare-o na classe abstrata `TransporteUrbano`.
- Implemente com regras específicas em cada transporte.

---

### 🌱 Interface `MeioEcologico`

Crie uma interface com o método:

```java
boolean ehEcologico()
```

- Faça com que **`Bicicleta`** e **`PatineteEletrico`** (caso criado) implementem essa interface.
- No `main`, verifique quais transportes são considerados **ecológicos**.

---

## ✅ Objetivos Trabalhados

- Programação Orientada a Objetos (Herança, Polimorfismo, Abstração e Interface)
- Boas práticas de modelagem
- Aplicação prática de conceitos como listas e estruturas de controle