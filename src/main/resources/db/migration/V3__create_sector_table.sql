CREATE TABLE IF NOT EXISTS sectors_tb (
    sector_id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    acronym VARCHAR(10) NOT NULL
);