# 💳 NizoPay System

**NizoPay** é um sistema de gestão de contas digitais que simula uma carteira virtual. Com ele, é possível criar usuários, contas bancárias, cartões e realizar transações financeiras. Ideal para fins educacionais, prototipagem de fintechs ou estudos de arquitetura de software com regras de negócio bem definidas.

### 🔗 Documentação da API

Acesse a documentação completa:\
👉 [Swagger UI](https://nizopay-system.onrender.com/swagger-ui/index.html#/)

---

## 📌 Funcionalidades

- Cadastro e gerenciamento de usuários;
- Criação automática de contas com número e agência;
- Solicitação, aprovação ou rejeição de crédito;
- Emissão de cartões com geração automática de número válido;
- Operações financeiras: depósitos, saques e transferências;
- Controle de status, saldo e limite de crédito;
- Validação de cartões por data de expiração e status de ativação.

---

## 🧱️ Modelo de Dados

### **Tabelas principais:**

#### `tb_users` — Usuários

| Coluna      | Tipo         | Descrição           |
| ----------- | ------------ | ------------------- |
| user\_id    | UUID         | Identificador único |
| full\_name  | VARCHAR(255) | Nome completo       |
| email       | VARCHAR(255) | E-mail (único)      |
| updated\_at | TIMESTAMP    | Última atualização  |
| deleted\_at | TIMESTAMP    | Deleção lógica      |

---

#### `tb_accounts` — Contas Bancárias

| Coluna          | Tipo          | Descrição                    |
| --------------- | ------------- | ---------------------------- |
| account\_id     | UUID          | Identificador da conta       |
| account\_number | VARCHAR(8)    | Número da conta              |
| user\_id        | UUID          | ID do usuário (FK)           |
| is\_active      | BOOLEAN       | Conta ativa                  |
| balance         | DECIMAL(15,2) | Saldo disponível             |
| credit\_limit   | DECIMAL(15,2) | Limite de crédito            |
| account\_status | VARCHAR(50)   | Status da conta (ex: ACTIVE) |
| created\_at     | TIMESTAMP     | Data de criação              |
| updated\_at     | TIMESTAMP     | Última atualização           |
| deleted\_at     | TIMESTAMP     | Deleção lógica               |

---

#### `tb_cards` — Cartões

| Coluna             | Tipo         | Descrição                       |
| ------------------ | ------------ | ------------------------------- |
| card\_id           | UUID         | Identificador do cartão         |
| user\_id           | UUID         | Dono do cartão (FK)             |
| account\_id        | UUID         | Conta associada (FK)            |
| card\_number       | VARCHAR      | Número do cartão                |
| card\_holder\_name | VARCHAR(255) | Nome impresso                   |
| cvv                | VARCHAR(3)   | Código de segurança             |
| expiration\_date   | DATE         | Data de expiração               |
| is\_card\_active   | BOOLEAN      | Status de ativação              |
| card\_type         | VARCHAR(50)  | Tipo (ex: CREDIT)               |
| credit\_limit      | DECIMAL      | Limite do cartão (caso crédito) |
| current\_balance   | DECIMAL      | Saldo atual (caso crédito)      |

---

#### `tb_transactions` — Transações

| Coluna              | Tipo        | Descrição                              |
| ------------------- | ----------- | -------------------------------------- |
| transaction\_id     | UUID        | Identificador da transação             |
| payer\_id           | UUID        | Conta pagadora (nullable)              |
| payee\_id           | UUID        | Conta recebedora (nullable)            |
| amount              | DECIMAL     | Valor transacionado                    |
| transaction\_status | VARCHAR(50) | Status (ex: COMPLETED, FAILED)         |
| transaction\_type   | VARCHAR(50) | Tipo (ex: DEPOSIT, WITHDRAW, TRANSFER) |
| description         | TEXT        | Descrição opcional                     |
| created\_at         | TIMESTAMP   | Data/hora da transação                 |

---

## ⚙️ Regras de Negócio

### Contas:

- Podem solicitar crédito (status `PENDING`);
- Podem ser aprovadas (`APPROVED`) ou rejeitadas (`REJECTED`);
- Podem ter múltiplos cartões vinculados;
- Realizam depósitos, saques e transferências com validações de saldo.

### Cartões:

- Podem ser ativados/desativados;
- Devem estar válidos (ativos e com data de expiração futura);
- Possuem saldo e limite de crédito independente da conta.

### Transações:

- Apenas permitidas se houver saldo suficiente (ou crédito disponível);
- Registradas com status (`PENDING`, `COMPLETED`, etc.) e tipo (`DEPOSIT`, `TRANSFER`, etc.).

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Lombok**
- **Swagger UI** (documentação interativa)
- **Banco de Dados:** PostgreSQL
- **Identificação via UUID**

---

## 🚀 Como Executar o Projeto

1. **Clone o repositório:**

```bash
git clone https://github.com/seu-usuario/nizopay-system.git
cd nizopay-system
```

2. **Configure o banco de dados**\
   Ajuste o `application.properties` com as credenciais do seu banco (ou utilize H2 em memória).

3. **Execute a aplicação:**

```bash
./mvnw spring-boot:run
```

4. **Acesse a API localmente:**

```
http://localhost:8080/swagger-ui/index.html
```

