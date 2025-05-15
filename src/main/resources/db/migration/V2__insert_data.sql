INSERT INTO brands (name, country_of_origin, description) VALUES
                                                              ('Nike', 'USA', 'Leading global sportswear and footwear brand.'),
                                                              ('Adidas', 'Germany', 'Multinational corporation focused on sports shoes, clothing, and accessories.'),
                                                              ('Puma', 'Germany', 'Designs and manufactures athletic and casual footwear, apparel and accessories.'),
                                                              ('New Balance', 'USA', 'American sports footwear and apparel brand known for comfort and performance.'),
                                                              ('Converse', 'USA', 'Known for Chuck Taylor All-Stars and other iconic sneakers.');

INSERT INTO categories (name, description) VALUES
                                               ('Running Shoes', 'Footwear designed for the sport of running.'),
                                               ('Sneakers', 'Casual shoes for everyday wear, often with an athletic look.'),
                                               ('Basketball Shoes', 'High-performance footwear for basketball players.'),
                                               ('Boots', 'Durable footwear covering the foot and ankle, for various purposes.'),
                                               ('Sandals', 'Open footwear, consisting of a sole held to the wearer''s foot by straps.');

INSERT INTO shoe_models (name, brand_id, category_id, description, base_price, main_image_url) VALUES
                                                                                                   ('Air Max 270', 1, 2, 'Features Nike''s biggest heel Air unit yet for a super-soft ride.', 150.00, '/images/shoes/placeholder.jpg'),
                                                                                                   ('React Infinity Run Flyknit 3', 1, 1, 'One of our most tested shoes, designed to help you stay on the run.', 160.00, '/images/shoes/placeholder.jpg'),
                                                                                                   ('Ultraboost 5.0 DNA', 2, 1, 'Comfortable running shoes with responsive Boost cushioning.', 180.00, '/images/shoes/placeholder.jpg'),
                                                                                                   ('Stan Smith', 2, 2, 'A timeless court style sneaker, recognized globally.', 90.00, '/images/shoes/placeholder.jpg'),
                                                                                                   ('Suede Classic XXI', 3, 2, 'The iconic PUMA Suede, a footwear legend.', 70.00, '/images/shoes/placeholder.jpg'),
                                                                                                   ('Fresh Foam X 1080v12', 4, 1, 'Delivers top-of-the-line performance cushioning and comfort.', 159.99, '/images/shoes/placeholder.jpg'),
                                                                                                   ('Chuck Taylor All Star Classic', 5, 2, 'The unmistakable silhouette loved by generations.', 60.00, '/images/shoes/placeholder.jpg');

INSERT INTO shoe_items (shoe_model_id, sku_code, size, color, price, quantity_in_stock) VALUES
                                                                                            (1, 'NK-AM270-BLK-42', 'EU 42', 'Black/White', 150.00, 10),
                                                                                            (1, 'NK-AM270-WH-43', 'EU 43', 'White/Blue', 155.00, 5),
                                                                                            (1, 'NK-AM270-RED-41', 'EU 41', 'Red/Black', 152.00, 0);

INSERT INTO shoe_items (shoe_model_id, sku_code, size, color, price, quantity_in_stock) VALUES
                                                                                            (2, 'NK-RIR3-GRY-44', 'EU 44', 'Grey/Volt', 160.00, 8),
                                                                                            (2, 'NK-RIR3-BLU-42.5', 'EU 42.5', 'Ocean Blue', 165.00, 12);

INSERT INTO shoe_items (shoe_model_id, sku_code, size, color, price, quantity_in_stock) VALUES
                                                                                            (3, 'AD-UB5-BLK-UK8', 'UK 8', 'Core Black', 180.00, 15),
                                                                                            (3, 'AD-UB5-WH-UK9', 'UK 9', 'Cloud White', 185.00, 7);

INSERT INTO shoe_items (shoe_model_id, sku_code, size, color, price, quantity_in_stock) VALUES
    (4, 'AD-SS-WHGR-US7', 'US 7', 'White/Green', 90.00, 25);

INSERT INTO shoe_items (shoe_model_id, sku_code, size, color, price, quantity_in_stock) VALUES
                                                                                            (5, 'PU-SCXXI-BLK-42', 'EU 42', 'Black', 70.00, 18),
                                                                                            (5, 'PU-SCXXI-NAV-43', 'EU 43', 'Navy', 72.00, 10);

INSERT INTO shoe_items (shoe_model_id, sku_code, size, color, price, quantity_in_stock) VALUES
    (6, 'NB-1080V12-BLU-US10', 'US 10', 'Electric Blue', 159.99, 9);

INSERT INTO shoe_items (shoe_model_id, sku_code, size, color, price, quantity_in_stock) VALUES
                                                                                            (7, 'CN-CTAS-RED-M9', 'Men US 9', 'Red', 60.00, 30),
                                                                                            (7, 'CN-CTAS-BLK-W7', 'Women US 7', 'Black', 60.00, 22);

INSERT INTO orders (status, total_amount, shipping_address, customer_name, customer_email, customer_phone) VALUES
    ('PENDING', 220.00, 'CHNU, 8-th building', 'John Doe', 'john.doe@example.com', '555-1234');


INSERT INTO order_items (order_id, shoe_item_id, quantity, price_at_purchase) VALUES
                                                                                  (1, 1, 1, 150.00),
                                                                                  (1, 8, 1, 90.00);
UPDATE orders SET total_amount = 240.00 WHERE id = 1;