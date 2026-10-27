CREATE TABLE IF NOT EXISTS calleds_tb(
    called_id UUID PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    place VARCHAR(100) NOT NULL,
    priority VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'OPENED',
    solution TEXT,
    requester_id UUID NOT NULL,
    technical_id UUID NOT NULL,
    category_id UUID NOT NULL,
    opening_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    closed_date TIMESTAMP,

    CONSTRAINT calleds_users FOREIGN KEY (requester_id) REFERENCES users_tb(user_id),
    CONSTRAINT calleds_technicals FOREIGN KEY (technical_id) REFERENCES technicals_tb(user_id),
    CONSTRAINT calleds_categories FOREIGN KEY (category_id) REFERENCES categories_tb(category_id)
);