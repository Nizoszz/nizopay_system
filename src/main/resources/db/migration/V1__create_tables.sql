CREATE TABLE tb_users (
    user_id UUID PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP NULL
);

CREATE TABLE tb_accounts (
    account_id UUID PRIMARY KEY,
    account_number VARCHAR(8) UNIQUE NOT NULL,
    agency VARCHAR(4) NOT NULL,
    user_id UUID NOT NULL,
    is_active BOOLEAN DEFAULT TRUE NOT NULL,
    balance DECIMAL(15,2) DEFAULT 0.00 NOT NULL,
    credit_limit DECIMAL(15,2) DEFAULT 0.00 NOT NULL,
    account_status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP NULL,
    CONSTRAINT fk_account_user FOREIGN KEY (user_id) REFERENCES tb_users(user_id) ON DELETE CASCADE
);
CREATE TABLE tb_cards (
    card_id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    account_id UUID NOT NULL,
    card_number VARCHAR(8) UNIQUE NOT NULL,
    card_holder_name VARCHAR(255) NOT NULL,
    cvv VARCHAR(3) NOT NULL,
    expiration_date DATE NOT NULL,
    is_card_active BOOLEAN DEFAULT TRUE NOT NULL,
    cart_type VARCHAR(50) NOT NULL,
    credit_limit DECIMAL(15,2) DEFAULT NULL,
    current_balance DECIMAL(15,2) DEFAULT NULL
    CONSTRAINT fk_card_user FOREIGN KEY (user_id) REFERENCES tb_users(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_card_account FOREIGN KEY (account_id) REFERENCES tb_accounts(account_id) ON DELETE CASCADE
);

CREATE TABLE tb_transactions (
    transaction_id UUID PRIMARY KEY,
    payer_id UUID NULL,
    payee_id UUID NULL,
    amount DECIMAL(15,2) NOT NULL,
    transaction_status VARCHAR(50) NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    description TEXT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_transaction_payer FOREIGN KEY (payer_id) REFERENCES tb_accounts(account_id) ON DELETE SET NULL,
    CONSTRAINT fk_transaction_payee FOREIGN KEY (payee_id) REFERENCES tb_accounts(account_id) ON DELETE SET NULL
);
