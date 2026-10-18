CREATE TABLE IF NOT EXISTS tokens_tb(
    token_id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    access_token TEXT NOT NULL,
    refresh_token TEXT NOT NULL,
    expired BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT fk_tokens_users FOREIGN KEY (user_id) REFERENCES users_tb(user_id)
);