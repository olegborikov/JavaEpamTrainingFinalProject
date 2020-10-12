USE bullfinch;

INSERT INTO bullfinch.role (role_name)
VALUES ('guest');
INSERT INTO bullfinch.role (role_name)
VALUES ('user');
INSERT INTO bullfinch.role (role_name)
VALUES ('admin');

INSERT INTO bullfinch.wallet (balance)
VALUES (0);

INSERT INTO bullfinch.user_account (login, password, is_blocked, is_activated, role_id_fk)
VALUES ('admin', 'a753c776ff3ed4fefa2af948af87448910153281', 0, 1, 3);
INSERT INTO bullfinch.user_account (email, login, password, first_name, second_name, phone_number,
                                    is_blocked, is_activated, role_id_fk, wallet_id_fk)
VALUES ('ob375259542181@gmail.com', 'oleg', 'e43713ad9e2fc4c55c1e2b373d3f548bd1ffed6e', 'Oleg',
        'Borikov', '375251234567', 0, 1, 2, 1);

INSERT INTO bullfinch.image (image_name)
VALUES ('77a7cd94-6a94-4151-b7e0-18853e16a7f8');
INSERT INTO bullfinch.image (image_name)
VALUES ('02d0e060-80ec-4e14-9a4a-9b474c8f3d89');
INSERT INTO bullfinch.image (image_name)
VALUES ('a6f43e7e-1b4b-4503-a0b3-b48726a48f02');
INSERT INTO bullfinch.image (image_name)
VALUES ('9eda23d2-a264-42b0-8dd2-329085843bb7');
INSERT INTO bullfinch.image (image_name)
VALUES ('0328104a-b8fa-4fa8-8b58-cf59746d02f7');
INSERT INTO bullfinch.image (image_name)
VALUES ('c56bf5bc-0727-4475-b9a2-0ea6053c5ef5');
INSERT INTO bullfinch.image (image_name)
VALUES ('747d0b93-b9a5-40e1-b9b4-cbc841951a85');
INSERT INTO bullfinch.image (image_name)
VALUES ('5edae083-e474-469d-ad16-2eb2e6a9e702');

INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price, tattoo_rating,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Warrior',
        'If your looking for a symbolic fighter image or something else ferocious, look no further then the warrior. These are as bad as tattoos can get. Perfect for the fighter in all of us. Just like most tattoos, the warrior has symbolic value.',
        900, 5, 1, 0, 1, 1);

INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price, tattoo_rating,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Samurai',
        'Samurai tattoos are very popular in Japanese culture. Even though it is not a mainstream tattoo in America, it still has a following, especially among people who admire Japan. Their designs are very detailed and quite colorful. Though they come in a variety of sizes and styles, most designs are larger due to the detail in this tattoo.They usually represent the traits and attributes of the samurai. It often symbolizes strength and courage, along with a host of other meanings. The designs are quite stunning and perfect for sleeve tattoos, with various elements and symbols that can accompany their theme.',
        750, 5, 1, 0, 2, 1);
INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price, tattoo_rating,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Child of the devil',
        'Devil tattoos are tattoos of Satan or one of his minions and have come under a lot of fire recently from fundamentalist Christians and Christian groups that do not have a clear understanding of the meaning of the tattoos. Articles are appearing on the web that denounce devil tattoos and make outrageous claims that the devil tattoo is the mark of Satan and you advocate his following by having his image on your body. They are clearly ignorant of the tradition of body art and the decisions made by the body art enthusiast. The devil tattoo can have many ramifications and meanings, but only a small part of the population of tattooed people use them as a symbol of evil or benevolence.',
        700, 5, 1, 0, 3, 1);
INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price, tattoo_rating,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Odi et amo',
        'The cat it’s the magical animal par excellence, it has always been surrounded by an aura of mystery.Adored as a goddess in ancient Egypt, than became a symbol of diabolical evil for Christianity, it was rehabilitated by the Enlightenment.The cat is however an animal with mysterious characteristics and those who live with one of them or decide to get a tattoo can experience very special experiences.',
        950, 5, 1, 0, 4, 1);
INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price, tattoo_rating,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Dead space',
        'Translated from ancient Greek, the word “astronaut” means a sailor and a universe. People who have made such a tattoo are very quiet and calm. They never enter into a conflict first and try not to provoke it. However, it does not prevent them from being dedicated and courageous. They are always ready to help somebody or even to save a man’s life. Moreover, they love to travel around the world, explore something new. Nature is the ideal place of pastime for such a person. People with a cosmonaut tattoo usually like to watch leaves fall from the trees or a river flows. These people are excited to explore themselves, so they usually like to study psychology. They are interested in science because it is a great chance to discover something new.',
        650, 5, 1, 0, 5, 1);
INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price, tattoo_rating,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Wolf',
        'Wolf tattoos to represent the family. Wolves are animals that live with their families and choose only one partner for their entire life. A wolf tattoo on your body can represent your family ties and the value you give to loved ones.These tattoos are also used to represent spiritual guidance. Wolves are very good track animals and have been helping people throughout history. In many cultures it is believed that wolves are spiritually guided.',
        100, 5, 1, 0, 6, 1);
INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price, tattoo_rating,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Demons in my head',
        'In the most general sense the value of a demon tattoo ( from the word " daemon " - " gives wisdom" ) - is a temptation, which aims - to awaken in man the fall, as well as the temptation, weakness, deceit, evil spirits patronage. More detailed values ​​can be found by considering the history of a particular character. You need to know that demons and devils -not the same, so a careful approach to the choice of a sketch of tattoo with the image of a demon.',
        800, 5, 1, 0, 7, 1);
INSERT INTO bullfinch.tattoo (tattoo_name, tattoo_description, tattoo_price, tattoo_rating,
                              is_allowed, is_archived, image_id_fk, user_account_id_fk)
VALUES ('Key to all doors',
        'The key tattoo is not a mainstream tattoo, but it is a popular choice. It is sometimes seen alone or in combination with other symbols. A common combination is the key and lock tattoo. If you think about it, keys are a very big part of our every day life. They open doors for us. They also close things. Because of this double meaning, key tattoo meanings can be looked at in a number of ways. Key tattoos come in different varieties. It can be one single key, a key and lock or designed as a skeleton key. Some are rather simple while others are very artistic in style. Though these are more common, other designs include combinations with heart tattoos, ribbon tattoos, padlock tattoos, start tattoos and chain tattoos. The design can often be seen as a bracelet or necklace.',
        500, 5, 1, 0, 8, 1);
