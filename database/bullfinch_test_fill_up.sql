USE bullfinch_test;

INSERT INTO bullfinch_test.role (role_name)
VALUES ('guest');
INSERT INTO bullfinch_test.role (role_name)
VALUES ('user');
INSERT INTO bullfinch_test.role (role_name)
VALUES ('admin');

INSERT INTO bullfinch_test.wallet (balance)
VALUES (0);
INSERT INTO bullfinch_test.wallet (balance)
VALUES (1000);
INSERT INTO bullfinch_test.wallet (balance)
VALUES (500);
INSERT INTO bullfinch_test.wallet (balance)
VALUES (100);

INSERT INTO bullfinch_test.user_account (login, password, is_blocked, is_activated, role_id_fk)
VALUES ('admin', '', 0, 1, 3);
INSERT INTO bullfinch_test.user_account (email, login, password, first_name, second_name, phone_number,
                                    is_blocked, is_activated, role_id_fk, wallet_id_fk)
VALUES ('alex@example.com', 'alex', 'e43713ad9e2fc4c55c1e2b373d3f548bd1ffed6e', 'Alex', 'Black', '375251111111', 0, 1, 2, 1);
INSERT INTO bullfinch_test.user_account (email, login, password, first_name, second_name, phone_number,
                                         is_blocked, is_activated, role_id_fk, wallet_id_fk)
VALUES ('alexey@example.com', 'alexey', 'e43713ad9e2fc4c55c1e2b373d3f548bd1ffed6e', 'Alexey', 'White', '80291234567', 0, 0, 2, 2);
INSERT INTO bullfinch_test.user_account (email, login, password, first_name, second_name, phone_number,
                                         is_blocked, is_activated, role_id_fk, wallet_id_fk)
VALUES ('oleg@example.com', 'oleg', 'e43713ad9e2fc4c55c1e2b373d3f548bd1ffed6e', 'Oleg', 'Brown', '+375337654321', 0, 1, 2, 3);

INSERT INTO bullfinch_test.image (image_name)
VALUES ('935aeb3c-b0b5-4337-b59e-a2546a8fa94b');
INSERT INTO bullfinch_test.image (image_name)
VALUES ('7e505ced-f395-49dd-9cd6-169d920ec320');
INSERT INTO bullfinch_test.image (image_name)
VALUES ('01cc692c-0e32-4dc2-83bd-ce90eca3768f');

INSERT INTO bullfinch_test.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Girl', 'Beautiful', 1000, 1, 0, 1, 1);
INSERT INTO bullfinch_test.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Warrior', 'Cool', 750, 1, 0, 2, 1);
INSERT INTO bullfinch_test.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Street', 'Dark', 800, 0, 0, 3, 1);

INSERT INTO bullfinch_test.discount (discount_percent, user_account_id_fk)
VALUES (50, 2);
INSERT INTO bullfinch_test.discount (discount_percent, user_account_id_fk)
VALUES (10, 2);
INSERT INTO bullfinch_test.discount (discount_percent, user_account_id_fk)
VALUES (30, 3);

INSERT INTO bullfinch_test.tattoo_order (tattoo_order_price, date, tattoo_order_description,
                                         is_confirmed, user_account_id_fk, tattoo_id_fk)
VALUES (500, 11111111111111, 'Good', 1, 2, 1);
INSERT INTO bullfinch_test.tattoo_order (tattoo_order_price, date, tattoo_order_description,
                                         is_confirmed, user_account_id_fk, tattoo_id_fk)
VALUES (300, 11111111111161, 'Cool', 0, 2, 3);
INSERT INTO bullfinch_test.tattoo_order (tattoo_order_price, date, tattoo_order_description,
                                         is_confirmed, user_account_id_fk, tattoo_id_fk)
VALUES (600, 11111111112111, 'Nice', 0, 3, 2);
