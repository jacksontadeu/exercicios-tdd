# 🧪 TDD Exercícios
Projeto de exercícios práticos de **Test-Driven Development (TDD)** utilizando Java 21, JUnit 5 e JaCoCo para cobertura de código.
---
## 📋 Pré-requisitos
|
 Ferramenta 
|
 Versão mínima 
|
|
-----------
|
--------------
|
|
 Java (JDK) 
|
 21 
|
|
 Maven 
|
 3.8+ 
|
Verifique as versões instaladas:
```bash
java -version
mvn -version
```
---
## 🏗️ Build
### Compilar o projeto
```bash
mvn compile
```
### Compilar incluindo as classes de teste
```bash
mvn test-compile
```
### Gerar o artefato (JAR) sem executar os testes
```bash
mvn package -DskipTests
```
### Build completo (compilação + testes + empacotamento)
```bash
mvn package
```
---
## ✅ Execução dos Testes
### Executar todos os testes
```bash
mvn test
```
### Executar um teste específico
```bash
mvn test -Dtest=TrianguloTest
mvn test -Dtest=CalculadoraSalarioTest
mvn test -Dtest=PersonDAOTest
```
### Executar um método de teste específico
```bash
mvn test -Dtest=TrianguloTest#nomeDoMetodo
```
### Executar testes e forçar re-execução (ignorando cache)
```bash
mvn clean test
```
---
## 📊 Relatório de Cobertura de Código (JaCoCo)
O projeto utiliza o plugin **JaCoCo 0.8.12** para análise de cobertura de código.  
O relatório HTML é gerado automaticamente após a execução dos testes.
### Gerar o relatório
```bash
mvn test
```
> O relatório é gerado automaticamente na fase `test` através do goal `jacoco:report`.
### Acessar o relatório
Após a execução dos testes, abra o arquivo abaixo no navegador:
<img width="1280" height="720" alt="image" src="https://github.com/user-attachments/assets/3ccbb6a6-d98f-493e-9c73-f0d7337119c7" />

---
## 📁 Estrutura do Projeto
```
Tdd-Exercicios/
├── src/
│   ├── main/java/org/example/
│   │   ├── Main.java
│   │   ├── person/
│   │   │   ├── Email.java
│   │   │   ├── Person.java
│   │   │   └── PersonDAO.java
│   │   ├── salario/
│   │   │   ├── CalculadoraSalario.java
│   │   │   ├── Cargo.java
│   │   │   └── Funcionario.java
│   │   └── triangulo/
│   │       ├── Triangulo.java
│   │       └── TipoTriangulo.java
│   └── test/java/org/example/
│       ├── TrianguloTest.java
│       ├── CalculadoraSalarioTest.java
│       └── PersonDAOTest.java
├── target/
│   ├── site/jacoco/
│   │   └── index.html          ← Relatório de cobertura JaCoCo
│   └── surefire-reports/       ← Relatórios de execução dos testes
├── pom.xml
└── README.md
```
---

---
## 🔧 Tecnologias
- **Java 21**
- **JUnit Jupiter 5.10.2** — framework de testes
- **Maven Surefire Plugin 3.2.5** — execução dos testes via Maven
- **JaCoCo 0.8.12** — análise e relatório de cobertura de código

