CREATE TABLE IF NOT EXISTS categories_tb(
    category_id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    sector_id UUID NOT NULL,

    CONSTRAINT categories_sectors FOREIGN KEY (sector_id) REFERENCES sectors_tb(sector_id)
);