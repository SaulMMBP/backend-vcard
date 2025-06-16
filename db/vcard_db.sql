DROP SCHEMA IF EXISTS vcard_db;
CREATE SCHEMA IF NOT EXISTS vcard_db;

USE vcard_db;

CREATE TABLE users (
	id VARCHAR(36) NOT NULL UNIQUE,
    email VARCHAR(200) NOT NULL,
    created_date DATE NOT NULL,
    last_modified_date DATE,
    PRIMARY KEY (id)
);

CREATE TABLE contacts (
	id BIGINT NOT NULL AUTO_INCREMENT,
	user_id VARCHAR(36) NOT NULL,
    identifier VARCHAR(200) NOT NULL,
    name VARCHAR(200) NOT NULL,
    email VARCHAR(200),
    position VARCHAR(200),
    company VARCHAR(200),
    web VARCHAR(200),
    created_date DATE NOT NULL,
    last_modified_date DATE,
    PRIMARY KEY (id),
    CONSTRAINT fk_contacts_users FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE phones (
	id BIGINT NOT NULL AUTO_INCREMENT,
    contact_id BIGINT NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    created_date DATE NOT NULL,
    last_modified_date DATE,
    PRIMARY KEY (id),
    CONSTRAINT fk_phones_contacts FOREIGN KEY (contact_id) REFERENCES contacts(id)
);

CREATE TABLE vcards (
	id BIGINT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(36) NOT NULL,
    contact_id BIGINT NOT NULL,
    name VARCHAR(200) NOT NULL,
    color VARCHAR(9),
    qr BLOB,
    created_date DATE NOT NULL,
    last_modified_date DATE,
    PRIMARY KEY (id),
    CONSTRAINT fk_vcards_contacts FOREIGN KEY (contact_id) REFERENCES contacts(id),
    CONSTRAINT fk_vcards_users FOREIGN KEY (user_id) REFERENCES users(id)
);