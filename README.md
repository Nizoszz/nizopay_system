💳 NizoPay System
NizoPay é um sistema de gestão de contas digitais que simula uma carteira virtual com funcionalidades de criação de usuários, contas bancárias, cartões e transações. Ideal para fins educacionais, prototipagem de fintechs ou estudos de arquitetura de software com regras de negócio bem definidas.

🔗 Acesse a documentação da API:
https://nizopay-system.onrender.com/swagger-ui/index.html#/

📌 Funcionalidades
Cadastro e gerenciamento de usuários;

Criação automática de contas bancárias com número e agência;

Solicitação, aprovação e rejeição de crédito;

Emissão de cartões de crédito e geração de números válidos;

Depósitos, saques e transferências entre contas;

Controle de status, limite e saldo de crédito;

Validação de cartões com base em data de expiração e status.

🧱 Estrutura do Banco de Dados
Tabelas principais:
tb_users
Armazena dados dos usuários.

Coluna	Tipo	Descrição
user_id	UUID	Identificador único
full_name	VARCHAR(255)	Nome completo
email	VARCHAR(255)	E-mail único
created_at	TIMESTAMP	Data de criação
updated_at	TIMESTAMP	Última atualização
deleted_at	TIMESTAMP	Deleção lógica
tb_accounts
Armazena dados das contas bancárias.

Coluna	Tipo	Descrição
account_id	UUID	Identificador único
account_number	VARCHAR(8)	Número da conta
agency	VARCHAR(4)	Número da agência
user_id	UUID	Dono da conta (chave estrangeira)
balance	DECIMAL(15,2)	Saldo disponível
credit_limit	DECIMAL(15,2)	Limite de crédito
account_status	VARCHAR(50)	Status da conta
tb_cards
Armazena dados dos cartões associados às contas.

Coluna	Tipo	Descrição
card_id	UUID	Identificador do cartão
card_number	VARCHAR(8)	Número do cartão (gerado)
card_holder_name	VARCHAR(255)	Nome impresso no cartão
cvv	VARCHAR(3)	Código de verificação
expiration_date	DATE	Data de expiração
is_card_active	BOOLEAN	Status do cartão
card_type	VARCHAR(50)	Tipo do cartão (CREDIT)
credit_limit	DECIMAL	Limite (caso crédito)
current_balance	DECIMAL	Saldo atual (caso crédito)
tb_transactions
Registra as movimentações financeiras entre contas.

Coluna	Tipo	Descrição
transaction_id	UUID	ID da transação
payer_id	UUID	Conta pagadora (nullable)
payee_id	UUID	Conta recebedora (nullable)
amount	DECIMAL	Valor transacionado
transaction_status	VARCHAR(50)	Status da transação
transaction_type	VARCHAR(50)	Tipo: DEPÓSITO, SAQUE, etc.
description	TEXT	Descrição opcional
created_at	TIMESTAMP	Data e hora
⚙️ Regras de Negócio
Uma conta pode:

Solicitar crédito (status muda para PENDING);

Ser aprovada (APPROVED) ou rejeitada (REJECTED);

Ter múltiplos cartões (com validações de limite e titularidade);

Realizar depósitos e saques com verificação de saldo;

Cartões de crédito:

Podem ser ativados/desativados;

Devem ser válidos (data futura e ativos);

Possuem limite e saldo independentes da conta;

Transações:

Só são permitidas se o saldo for suficiente;

São registradas com status e tipo (ex: TRANSFER, WITHDRAW);

🛠️ Tecnologias Utilizadas
Java 17+

Spring Boot

Lombok

Swagger UI (documentação da API)

Banco de dados relacional (PostgreSQL ou H2 para testes)

UUID para identificação única

Arquitetura baseada em domínio (DDD)

🚀 Como executar o projeto
Clone o repositório:

bash
Copy
Edit
git clone https://github.com/seu-usuario/nizopay-system.git
cd nizopay-system
Configure o banco de dados no arquivo application.properties (ou use H2 em memória).

Execute a aplicação:

bash
Copy
Edit
./mvnw spring-boot:run
Acesse a API:

bash
Copy
Edit
http://localhost:8080/swagger-ui/index.html
