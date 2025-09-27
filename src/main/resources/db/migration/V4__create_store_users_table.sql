-- Migration para criar tabela de relacionamento entre Store e User
CREATE TABLE store_users (
    store_id BINARY(16) NOT NULL,
    user_id BINARY(16) NOT NULL,
    PRIMARY KEY (store_id, user_id),
    CONSTRAINT fk_store_users_store FOREIGN KEY (store_id) REFERENCES store(id) ON DELETE CASCADE,
    CONSTRAINT fk_store_users_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);
