CREATE TABLE IF NOT EXISTS technicals_tb (
    user_id UUID PRIMARY KEY,
    sector_id UUID NOT NULL,

    CONSTRAINT fk_technicals_users FOREIGN KEY (user_id) REFERENCES users_tb(user_id),
    CONSTRAINT fk_technicals_sectors FOREIGN KEY (sector_id) REFERENCES sectors_tb(sector_id)
);