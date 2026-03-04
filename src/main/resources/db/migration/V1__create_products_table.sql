CREATE TABLE IF NOT EXISTS products (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255)   NOT NULL,
    description TEXT,
    price      DECIMAL(10, 2) NOT NULL,
    quantity   INT            NOT NULL DEFAULT 0,
    category   VARCHAR(100)   NOT NULL,
    created_at DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME       ON UPDATE CURRENT_TIMESTAMP
);