CREATE TABLE IF NOT EXISTS users_tb(
    user_id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password TEXT NOT NULL,
    registration BIGINT NOT NULL,
    role VARCHAR(20) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT u_email UNIQUE(email),
    CONSTRAINT u_registration UNIQUE(registration)
);