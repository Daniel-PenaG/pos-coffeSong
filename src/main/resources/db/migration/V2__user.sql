CREATE TABLE users(
	
	id BIGSERIAL PRIMARY KEY,
	
	role_id BIGINT NOT NULL,
	
	username VARCHAR(100)
	NOT NULL UNIQUE,
	
	password VARCHAR(255)
	NOT NULL,
	
	first_name VARCHAR(100),
	
	last_name VARCHAR(100),
	
	email VARCHAR(200),
	
	active BOOLEAN DEFAULT TRUE,
	
	created_at TIMESTAMP
	DEFAULT CURRENT_TIMESTAMP,
	
	CONSTRAINT fk_user_role
	FOREIGN KEY(role_id)
	REFERENCES roles(id)
);