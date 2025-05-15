CREATE TABLE brands (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL UNIQUE,
                        country_of_origin VARCHAR(100),
                        description TEXT
);

CREATE TABLE categories (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL UNIQUE,
                            description TEXT
);

CREATE TABLE shoe_models (
                             id BIGSERIAL PRIMARY KEY,
                             name VARCHAR(255) NOT NULL,
                             brand_id BIGINT NOT NULL,
                             category_id BIGINT NOT NULL,
                             description TEXT,
                             base_price DECIMAL(10, 2) NOT NULL CHECK (base_price >= 0),
                             main_image_url VARCHAR(512),
                             created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                             CONSTRAINT fk_shoe_model_brand FOREIGN KEY (brand_id) REFERENCES brands(id) ON DELETE RESTRICT,
                             CONSTRAINT fk_shoe_model_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE RESTRICT
);
CREATE INDEX idx_shoe_model_name ON shoe_models(name);

CREATE TABLE shoe_items (
                            id BIGSERIAL PRIMARY KEY,
                            shoe_model_id BIGINT NOT NULL,
                            sku_code VARCHAR(100) UNIQUE NOT NULL,
                            size VARCHAR(20) NOT NULL,
                            color VARCHAR(50) NOT NULL,
                            price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
                            quantity_in_stock INT NOT NULL CHECK (quantity_in_stock >= 0) DEFAULT 0,
                            additional_image_urls TEXT,
                            created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                            CONSTRAINT fk_shoe_item_model FOREIGN KEY (shoe_model_id) REFERENCES shoe_models(id) ON DELETE CASCADE,
                            UNIQUE (shoe_model_id, size, color)
);
CREATE INDEX idx_shoe_item_sku ON shoe_items(sku_code);

CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        order_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        status VARCHAR(50) NOT NULL,
                        total_amount DECIMAL(12, 2) NOT NULL,
                        shipping_address TEXT,
                        customer_name VARCHAR(255),
                        customer_email VARCHAR(255),
                        customer_phone VARCHAR(50),
                        created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_items (
                             id BIGSERIAL PRIMARY KEY,
                             order_id BIGINT NOT NULL,
                             shoe_item_id BIGINT NOT NULL,
                             quantity INT NOT NULL CHECK (quantity > 0),
                             price_at_purchase DECIMAL(10, 2) NOT NULL,
                             CONSTRAINT fk_order_item_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
                             CONSTRAINT fk_order_item_shoe_item FOREIGN KEY (shoe_item_id) REFERENCES shoe_items(id) ON DELETE RESTRICT
);

CREATE OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_shoe_models_updated_at
    BEFORE UPDATE ON shoe_models
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_shoe_items_updated_at
    BEFORE UPDATE ON shoe_items
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_orders_updated_at
    BEFORE UPDATE ON orders
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();