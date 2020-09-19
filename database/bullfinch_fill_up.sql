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
        'Borikov', '375251234567', 1, 1, 2, 1, 1)
