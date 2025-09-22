CREATE TABLE image (
    id BINARY(16) PRIMARY KEY,
    file_name VARCHAR(255),
    url VARCHAR(500)
);

CREATE TABLE user (
    id BINARY(16) PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(50)
);

CREATE TABLE store (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(500),
    phone VARCHAR(50),
    image_id BINARY(16),
    CONSTRAINT fk_store_image FOREIGN KEY (image_id) REFERENCES image(id)
);

CREATE TABLE category (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255),
    store_id BINARY(16),
    CONSTRAINT fk_category_store FOREIGN KEY (store_id) REFERENCES store(id)
);

CREATE TABLE product (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    price DECIMAL(10,2),
    sku VARCHAR(100) NOT NULL UNIQUE,
    category_id BINARY(16),
    image_id BINARY(16),
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(id),
    CONSTRAINT fk_product_image FOREIGN KEY (image_id) REFERENCES image(id)
);


