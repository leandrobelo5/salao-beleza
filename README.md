# 💇‍♂️ Sistema de Gestão para Salão de Beleza

Um sistema desktop em Java para gerenciamento de salões de beleza, com suporte a cadastro de clientes, funcionários, fornecedores, produtos, controle de estoque e vendas, utilizando o padrão de arquitetura MVC, JDBC para conexão com banco de dados MySQL (XAMPP) e interface em Swing.

---

## 📌 Funcionalidades

### 1. 📝 Cadastro
- **Clientes**: Nome, CPF, telefone, endereço e data de cadastro.
- **Funcionários**: Nome, CPF, telefone, cargo, salário e data de contratação.
- **Fornecedores**: Nome da empresa, CNPJ, telefone e tipo de produto fornecido.
- **Produtos**: Código único, nome, preço, quantidade em estoque e categoria.

### 2. 💰 Vendas
- Registro de vendas com:
  - Data e hora
  - Cliente (opcional)
  - Funcionário (obrigatório)
  - Lista de produtos e quantidades
  - Valor total calculado automaticamente
  - Atualização automática do estoque
  - Formas de pagamento (dinheiro e cartão, com suporte para futuras adições)

### 3. 🔍 Consultas
- Listagem de:
  - Clientes
  - Funcionários
  - Produtos
  - Vendas

---

## 🧠 Arquitetura do Projeto

- **MVC (Model - View - Controller)**
- **DAO**: Acesso e manipulação dos dados via JDBC
- **Swing**: Interface gráfica simples e leve
- **MySQL (XAMPP)**: Banco de dados local

---

## 🔧 Tecnologias Utilizadas

- **Java 17**
- **JDBC**
- **MySQL (XAMPP)**
- **Swing**
- **Maven/Manual Setup** (sem frameworks externos)

---

## 🚀 Como Executar

1. Instale o [XAMPP](https://www.apachefriends.org/) e inicie o MySQL.
2. Crie o banco de dados com o script SQL disponível [aqui](#).
3. Configure a URL de conexão no arquivo `ConnectionFactory.java`:
   ```java
   String url = "jdbc:mysql://localhost:3306/salao_beleza";
   String user = "root";
   String password = "";
