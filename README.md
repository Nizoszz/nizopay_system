💳 NizoPay System
NizoPay é um sistema de gestão de contas digitais que simula uma carteira virtual. Com ele, é possível criar usuários, contas bancárias, cartões e realizar transações financeiras. Ideal para fins educacionais, prototipagem de fintechs ou estudos de arquitetura de software com regras de negócio bem definidas.

🔗 Documentação da API
Acesse a documentação completa:
👉 Swagger UI

📌 Funcionalidades
Cadastro e gerenciamento de usuários;

Criação automática de contas com número e agência;

Solicitação, aprovação ou rejeição de crédito;

Emissão de cartões com geração automática de número válido;

Operações financeiras: depósitos, saques e transferências;

Controle de status, saldo e limite de crédito;

Validação de cartões por data de expiração e status de ativação.

🧱 Modelo de Dados
Tabelas principais:
tb_users — Usuários
Coluna	Tipo	Descrição
user_id	UUID	Identificador único
full_name	VARCHAR(255)	Nome completo
email	VARCHAR(255)	E-mail (único)
updated_at	TIMESTAMP	Última atualização
deleted_at	TIMESTAMP	Deleção lógica
tb_accounts — Contas Bancárias
Coluna	Tipo	Descrição
account_id	UUID	Identificador da conta
account_number	VARCHAR(8)	Número da conta
user_id	UUID	ID do usuário (FK)
is_active	BOOLEAN	Conta ativa
balance	DECIMAL(15,2)	Saldo disponível
credit_limit	DECIMAL(15,2)	Limite de crédito
account_status	VARCHAR(50)	Status da conta (ex: ACTIVE)
created_at	TIMESTAMP	Data de criação
updated_at	TIMESTAMP	Última atualização
deleted_at	TIMESTAMP	Deleção lógica
tb_cards — Cartões
Coluna	Tipo	Descrição
card_id	UUID	Identificador do cartão
user_id	UUID	Dono do cartão (FK)
account_id	UUID	Conta associada (FK)
card_number	VARCHAR	Número do cartão
card_holder_name	VARCHAR(255)	Nome impresso
cvv	VARCHAR(3)	Código de segurança
expiration_date	DATE	Data de expiração
is_card_active	BOOLEAN	Status de ativação
card_type	VARCHAR(50)	Tipo (ex: CREDIT)
credit_limit	DECIMAL	Limite do cartão (caso crédito)
current_balance	DECIMAL	Saldo atual (caso crédito)
tb_transactions — Transações
Coluna	Tipo	Descrição
transaction_id	UUID	Identificador da transação
payer_id	UUID	Conta pagadora (nullable)
payee_id	UUID	Conta recebedora (nullable)
amount	DECIMAL	Valor transacionado
transaction_status	VARCHAR(50)	Status (ex: COMPLETED, FAILED)
transaction_type	VARCHAR(50)	Tipo (ex: DEPOSIT, WITHDRAW, TRANSFER)
description	TEXT	Descrição opcional
created_at	TIMESTAMP	Data/hora da transação
⚙️ Regras de Negócio
Contas:
Podem solicitar crédito (status PENDING);

Podem ser aprovadas (APPROVED) ou rejeitadas (REJECTED);

Podem ter múltiplos cartões vinculados;

Realizam depósitos, saques e transferências com validações de saldo.

Cartões:
Podem ser ativados/desativados;

Devem estar válidos (ativos e com data de expiração futura);

Possuem saldo e limite de crédito independente da conta.

Transações:
Apenas permitidas se houver saldo suficiente (ou crédito disponível);

Registradas com status (PENDING, COMPLETED, etc.) e tipo (DEPOSIT, TRANSFER, etc.).

🛠️ Tecnologias Utilizadas
Java 17+

Spring Boot

Lombok

Swagger UI (documentação interativa)

Banco de Dados: PostgreSQL (produção) / H2 (testes)

Identificação via UUID

Arquitetura em camadas com DDD (Domain-Driven Design)

🚀 Como Executar o Projeto
Clone o repositório:

bash
Copy
Edit
git clone https://github.com/seu-usuario/nizopay-system.git
cd nizopay-system
Configure o banco de dados
Ajuste o application.properties com as credenciais do seu banco (ou utilize H2 em memória).

Execute a aplicação:

bash
Copy
Edit
./mvnw spring-boot:run
Acesse a API localmente:

bash
Copy
Edit
http://localhost:8080/swagger-ui/index.html
