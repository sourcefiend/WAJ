DELETE FROM review;
DELETE FROM hardware;
DELETE FROM user_authority;
DELETE FROM authority;
DELETE FROM users_table;

INSERT INTO hardware (name, code, price, hardware_type, stock_amount) VALUES ('test', '001', 42.60, 'GPU', 50);
INSERT INTO hardware (name, code, price, hardware_type, stock_amount) VALUES ('tes2', '002', 42.70, 'CPU', 3);
INSERT INTO hardware (name, code, price, hardware_type, stock_amount) VALUES ('test3', '003', 1424.60, 'MBO', 20);

INSERT into review (title, text, rating_id, hardware_id) VALUES ('review1', 'ovo je review 1', 'ONE', 2);
INSERT into review (title, text, rating_id, hardware_id) VALUES ('review2', 'ovo je review 2', 'FOUR', 2);
INSERT into review (title, text, rating_id, hardware_id) VALUES ('review3', 'ovo je review 3', 'TWO', 2);
INSERT into review (title, text, rating_id, hardware_id) VALUES ('review4', 'ovo je review 4', 'THREE', 2);
INSERT into review (title, text, rating_id, hardware_id) VALUES ('review5', 'ovo je review 5', 'FIVE', 3);
INSERT into review (title, text, rating_id, hardware_id) VALUES ('review6', 'ovo je review 6', 'TWO', 3);

INSERT INTO users_table (id, username, password) VALUES (1, 'user', '$2a$12$qIfhnfF8PHVNHsHpgaE1y.dfklY/cp4fbsic.J3/zq5U4zfDPeE0C'); -- password = user
INSERT INTO users_table (id, username, password) VALUES (2, 'admin', '$2a$12$OR9SeNM.1txwlt7IUemGJ.MUhPe3wp2kCRrWtuY/wJ9gQ9D.GkhuO'); -- password = admin

INSERT INTO authority (id, authority_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, authority_name) VALUES (2, 'ROLE_USER');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 1);