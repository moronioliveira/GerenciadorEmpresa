# Sistema de Gerenciamento de Funcionários

Aplicação console em Java focada no processamento de coleções de dados de funcionários, aplicando boas práticas de Programação Orientada a Objetos (POO), princípios **SOLID**, precisão financeira com `BigDecimal` e manipulação temporal com a API `java.time`.

## ⚙️ Tecnologias e Ferramentas

* **Linguagem:** Java 17+
* **IDE:** IntelliJ IDEA
* **Gerenciador de Dependências:** Maven
* **Controle de Versão:** Git / GitHub

---

## 🏗️ Arquitetura e Organização

A aplicação foi estruturada em camadas para garantir a separação de responsabilidades e facilitador de manutenção:

```text
src/main/java/org/moronioliveira/
├── business/
│   └── FuncionarioService.java   # Regras de negócio e operações na lista
├── infrastructure/
│   └── entity/
│       ├── Pessoa.java           # Classe base (Nome, Data de Nascimento)
│       └── Funcionario.java      # Subclasse (Salário, Função)
└── Main.java                     # Ponto de entrada e orquestração do fluxo
