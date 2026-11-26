CREATE TABLE categories(
	
	id BIGSERIAL PRIMARY KEY,
	
	code VARCHAR(50)
	NOT NULL UNIQUE,
	
	name VARCHAR(100)
	NOT NULL,
	
	description VARCHAR(250),
	
	active BOOLEAN
	DEFAULT TRUE,
	
	created_at TIMESTAMP
	DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO categories
	(code,name)
	VALUES
	('COFFE', 'Cafes'),
	('DRINKS', 'Bebidas'),
	('DESSERT', 'Postres'),
	('SNACK', 'Snacks');