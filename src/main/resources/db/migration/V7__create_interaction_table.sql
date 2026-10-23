CREATE TABLE IF NOT EXISTS interactions_tb (
    interaction_id UUID PRIMARY KEY,
    text TEXT NOT NULL,
    called_id UUID NOT NULL,
    author_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT interactions_calleds FOREIGN KEY (called_id) REFERENCES calleds_tb(called_id),
    CONSTRAINT interactions_users FOREIGN KEY (author_id) REFERENCES users_tb(user_id)
);