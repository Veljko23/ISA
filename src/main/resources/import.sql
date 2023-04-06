-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123

INSERT INTO USERS (id, name, surname, picture, number, email, address, password, block, type) VALUES (1, 'user', 'user', 'picture1URL', '060000000', 'email1', 'adresa1', '$2a$10$CZoHRaELgVC6fV/6BkYCK.lmPMUIUm8aBd.KTNFY2PZJj1f1QYtBO', false, 'P');
INSERT INTO USERS (id, name, surname, picture, number, email, address, password, block, type) VALUES (2, 'admin', 'admin', 'picture1URL', '060000000', 'email2', 'adresa2', '$2a$10$CZoHRaELgVC6fV/6BkYCK.lmPMUIUm8aBd.KTNFY2PZJj1f1QYtBO', false, 'P');

INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); -- user-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 1); -- admin-u dodeljujemo rolu USER
--INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2); -- user-u dodeljujemo rolu ADMIN

INSERT INTO LOCATION (id, longitude,latitude) values (1, 20.1, 30.1);

