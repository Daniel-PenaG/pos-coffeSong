CREATE TABLE roles(
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO roles(
    code,
    name
)
VALUES
('ADMIN','Administrador'),
('CASHIER','Cajera'),
('BARISTA','Barista');