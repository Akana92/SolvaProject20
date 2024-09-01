-- Создание таблицы users
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       bank_account INTEGER NOT NULL CHECK (bank_account >= 1000000000 AND bank_account <= 9999999999),
                       amount NUMERIC(10, 2),
                       currency VARCHAR(3) NOT NULL,
                        password VARCHAR(255) NOT NULL
);

-- Индекс на email для быстрого поиска
CREATE INDEX idx_users_email ON users(email);

-- Добавление уникального ограничения на bank_account
ALTER TABLE users ADD CONSTRAINT unique_bank_account UNIQUE (bank_account);


CREATE TABLE transactions (
                              id BIGSERIAL PRIMARY KEY,
                              account_from_id BIGINT NOT NULL,
                              account_to_id BIGINT NOT NULL,
                              sum NUMERIC(10, 2) NOT NULL,
                              currency_shortname VARCHAR(3) NOT NULL,
                              date_time TIMESTAMPTZ NOT NULL,

                              CONSTRAINT fk_account_from FOREIGN KEY (account_from_id)
                                  REFERENCES users (id)
                                  ON DELETE RESTRICT
                                  ON UPDATE CASCADE,

                              CONSTRAINT fk_account_to FOREIGN KEY (account_to_id)
                                  REFERENCES users (id)
                                  ON DELETE RESTRICT
                                  ON UPDATE CASCADE
);

CREATE TABLE exchange_rates (
                                id SERIAL PRIMARY KEY,
                                currency_pair VARCHAR(10) NOT NULL,
                                rate NUMERIC(10, 4) NOT NULL,
                                date DATE NOT NULL
);