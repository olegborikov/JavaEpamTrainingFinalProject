USE bullfinch;

INSERT INTO bullfinch.role (role_name)
VALUES ('guest');
INSERT INTO bullfinch.role (role_name)
VALUES ('user');
INSERT INTO bullfinch.role (role_name)
VALUES ('admin');

INSERT INTO bullfinch.rating (rating_name)
VALUES ('beginner');
INSERT INTO bullfinch.rating (rating_name)
VALUES ('middle');
INSERT INTO bullfinch.rating (rating_name)
VALUES ('advanced');
INSERT INTO bullfinch.rating (rating_name)
VALUES ('master');

INSERT INTO bullfinch.wallet (balance)
VALUES (0);

INSERT INTO bullfinch.user_account (email, login, password, first_name, second_name, phone_number,
                                    is_blocked, is_activated, role_id_fk, wallet_id_fk,
                                    rating_id_fk)
VALUES (null, 'admin', 'a753c776ff3ed4fefa2af948af87448910153281',
        null, null, null, null, null, 3, null, null);
INSERT INTO bullfinch.user_account (email, login, password, first_name, second_name, phone_number,
                                    is_blocked, is_activated, role_id_fk, wallet_id_fk,
                                    rating_id_fk)
VALUES ('ob375259542181@gmail.com', 'oleg', 'e43713ad9e2fc4c55c1e2b373d3f548bd1ffed6e', 'Oleg',
        'Borikov', '375251234567', 0, 1, 2, 1, 1)

INSERT INTO bullfinch.image (image_name)
 VALUES ('c56bf5bc-0727-4475-b9a2-0ea6053c5ef5');
INSERT INTO bullfinch.image (image_name)
VALUES ('02d0e060-80ec-4e14-9a4a-9b474c8f3d89');
INSERT INTO bullfinch.image (image_name)
VALUES ('a6f43e7e-1b4b-4503-a0b3-b48726a48f02');
INSERT INTO bullfinch.image (image_name)
VALUES ('925ce14f-4b8a-4036-937d-37577bfe9629');
INSERT INTO bullfinch.image (image_name)
VALUES ('747d0b93-b9a5-40e1-b9b4-cbc841951a85');
INSERT INTO bullfinch.image (image_name)
VALUES ('5edae083-e474-469d-ad16-2eb2e6a9e702');
INSERT INTO bullfinch.image (image_name)
VALUES ('8758a576-88a4-4559-b727-26bd067e62ea');
INSERT INTO bullfinch.image (image_name)
VALUES ('0328104a-b8fa-4fa8-8b58-cf59746d02f7');

INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              tattoo_rating, is_allowed, is_archived, image_id)
VALUES ('Wolf', 'dark, cool', 100, 5, 1, 0, 1);
INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              tattoo_rating, is_allowed, is_archived, image_id)
VALUES ('Samurai', 'dark, cool', 50, 5, 1, 0, 2);
INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              tattoo_rating, is_allowed, is_archived, image_id)
VALUES ('Black queen', 'dark, cool', 150, 5, 1, 0, 3);
INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              tattoo_rating, is_allowed, is_archived, image_id)
VALUES ('Sfinks', 'dark, cool', 200, 5, 1, 0, 4);
INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              tattoo_rating, is_allowed, is_archived, image_id)
VALUES ('Man', 'dark, cool', 75, 5, 1, 0, 5);
INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              tattoo_rating, is_allowed, is_archived, image_id)
VALUES ('Hand', 'dark, cool', 125, 5, 1, 0, 6);
INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              tattoo_rating, is_allowed, is_archived, image_id)
VALUES ('Samurai', 'dark, cool', 250, 5, 1, 0, 7);
INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price,
                              tattoo_rating, is_allowed, is_archived, image_id)
VALUES ('Dead space', 'dark, cool', 175, 5, 1, 0, 8);