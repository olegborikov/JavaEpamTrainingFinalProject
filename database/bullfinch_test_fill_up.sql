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
VALUES (250);
INSERT INTO bullfinch_test.wallet (balance)
VALUES (2000);

INSERT INTO bullfinch_test.user_account (login, password, is_blocked, is_activated, role_id_fk)
VALUES ('admin', '', 0, 1, 3);
INSERT INTO bullfinch_test.user_account (email, login, password, first_name, second_name, phone_number,
                                    is_blocked, is_activated, role_id_fk, wallet_id_fk)
VALUES ('alex@example.com', 'alex', '', 'Alex', 'Black', '375251111111', 0, 1, 2, 1);
INSERT INTO bullfinch_test.user_account (email, login, password, first_name, second_name, phone_number,
                                         is_blocked, is_activated, role_id_fk, wallet_id_fk)
VALUES ('alexey@example.com', 'alexey', '', 'Alexey', 'White', '80291234567', 0, 0, 2, 2);
INSERT INTO bullfinch_test.user_account (email, login, password, first_name, second_name, phone_number,
                                         is_blocked, is_activated, role_id_fk, wallet_id_fk)
VALUES ('oleg@example.com', 'oleg', '', 'Oleg', 'Brown', '+375337654321', 0, 1, 2, 3);
INSERT INTO bullfinch_test.user_account (email, login, password, first_name, second_name, phone_number,
                                         is_blocked, is_activated, role_id_fk, wallet_id_fk)
VALUES ('noah@example.com', 'noah', '', 'Noah', 'Davis', '375292222222', 1, 1, 2, 4);
INSERT INTO bullfinch_test.user_account (email, login, password, first_name, second_name, phone_number,
                                         is_blocked, is_activated, role_id_fk, wallet_id_fk)
VALUES ('john@example.com', 'john', '', 'John', 'Doe', '375333333333', 0, 1, 2, 5);

INSERT INTO bullfinch_test.image (image_name)
VALUES ('935aeb3c-b0b5-4337-b59e-a2546a8fa94b');
INSERT INTO bullfinch_test.image (image_name)
VALUES ('7e505ced-f395-49dd-9cd6-169d920ec320');
INSERT INTO bullfinch_test.image (image_name)
VALUES ('01cc692c-0e32-4dc2-83bd-ce90eca3768f');
INSERT INTO bullfinch_test.image (image_name)
VALUES ('e5559236-4de7-43b3-afea-188869dba019');
INSERT INTO bullfinch_test.image (image_name)
VALUES ('3fcd5108-5e1d-4724-ad9d-684cf59323db');
INSERT INTO bullfinch_test.image (image_name)
VALUES ('2df00de6-b1b3-4e01-91ed-7d7ec4f4f5cf');

INSERT INTO bullfinch_test.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Girl', 'Beautiful', 1000, 1, 0, 1, 1);
INSERT INTO bullfinch_test.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Warrior', 'Cool', 750, 1, 0, 2, 1);
INSERT INTO bullfinch_test.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Street', 'Dark', 800, 0, 0, 3, 1);
INSERT INTO bullfinch_test.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Hand', 'Nice', 200, 1, 0, 4, 3);
INSERT INTO bullfinch_test.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Boy', 'Handsome', 300, 1, 0, 5, 2);
INSERT INTO bullfinch_test.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Night', 'Bright', 500, 1, 1, 6, 1);
