-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123

INSERT INTO USERS (id, name, surname, picture, number, email, address, password, block, type) VALUES (1, 'user', 'user', 'picture1URL', '060000000', 'email1', 'adresa1', '$2a$10$CZoHRaELgVC6fV/6BkYCK.lmPMUIUm8aBd.KTNFY2PZJj1f1QYtBO', false, 'P');
INSERT INTO USERS (id, name, surname, picture, number, email, address, password, block, type) VALUES (2, 'admin', 'admin', 'picture1URL', '060000000', 'email2', 'adresa2', '$2a$10$CZoHRaELgVC6fV/6BkYCK.lmPMUIUm8aBd.KTNFY2PZJj1f1QYtBO', false, 'P');


INSERT INTO ROLE (id, name) VALUES (1, 'ROLE_PASSENGER');
INSERT INTO ROLE (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO ROLE (id, name) VALUES (3, 'ROLE_DRIVER');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); 
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2);


INSERT INTO LOCATION (id, longitude,latitude) values (1, 20.1, 30.1);
INSERT INTO LOCATION (id, longitude,latitude) values (2, 30.1, 40.1);
INSERT INTO LOCATION (id, longitude,latitude) values (3, 40.1, 50.1);

INSERT INTO PATH (id, start, end, departure_id, destination_id, kilometers, estimated_time, price) values (1, '2022-11-11', '2022-11-13', 1, 2, 40, '80:00', 20000);
INSERT INTO PATH (id, start, end, departure_id, destination_id, kilometers, estimated_time, price) values (2, '2023-05-11', '2022-05-15', 1, 2, 40, '80:00', 20000);
INSERT INTO PATH (id, start, end, departure_id, destination_id, kilometers, estimated_time, price) values (3, '2023-01-11', '2022-01-15', 1, 2, 40, '80:00', 20000);
INSERT INTO PATH (id, start, end, departure_id, destination_id, kilometers, estimated_time, price) values (4, '2023-06-11', '2022-06-15', 1, 2, 40, '80:00', 20000);


INSERT INTO VEHICLE_TYPE (id, name, price) values (1, 'Auto', 1000);
INSERT INTO VEHICLE_TYPE (id, name, price) values (2, 'Kombi', 2000);
INSERT INTO VEHICLE_TYPE (id, name, price) values (3, 'Autobus', 3000);


INSERT INTO VEHICLE (id, model, vehicle_type_id, tables, seats_number, location_id) values (1, 'XX', 1, 'RS123', 4, 1);
INSERT INTO VEHICLE (id, model, vehicle_type_id, tables, seats_number, location_id) values (2, 'YY', 2, 'RS987', 7, 2);
INSERT INTO VEHICLE (id, model, vehicle_type_id, tables, seats_number, location_id) values (3, 'AA', 2, 'RS555', 5, 2);
INSERT INTO VEHICLE (id, model, vehicle_type_id, tables, seats_number, location_id) values (4, 'BB', 3, 'RS733', 10, 2);

INSERT INTO USERS (id, name, surname, picture, number, email, address, password, block, documents, active, vehicle_id, type) VALUES (3, 'user3', 'user3', 'picture1URL', '060000000', 'email3', 'adresa1', '$2a$10$CZoHRaELgVC6fV/6BkYCK.lmPMUIUm8aBd.KTNFY2PZJj1f1QYtBO', false, 'abc', false, 1, 'D');
INSERT INTO USERS (id, name, surname, picture, number, email, address, password, block, documents, active, vehicle_id, type) VALUES (4, 'user4', 'user4', 'picture1URL', '060000000', 'email4', 'adresa1', '$2a$10$CZoHRaELgVC6fV/6BkYCK.lmPMUIUm8aBd.KTNFY2PZJj1f1QYtBO', false, 'def', false, 2, 'D');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 3); 
INSERT INTO USER_ROLE (user_id, role_id) VALUES (4, 3);

INSERT INTO DRIVING (id, start, end, price, driver_id, estimated_time, driving_status, vehicle_type_id) VALUES (1, '2022-11-11', '2022-11-13', 750, 3, '35:30', 0, 1);
INSERT INTO DRIVING (id, start, end, price, driver_id, estimated_time, driving_status, vehicle_type_id) VALUES (2, '2022-11-15', '2022-11-18', 1500, 4, '20:00', 1, 2);
INSERT INTO DRIVING (id, start, end, price, driver_id, estimated_time, driving_status, vehicle_type_id) VALUES (3, '2022-11-14', '2022-11-16', 12000, 4, '350:00', 3, 3);


INSERT INTO DRIVING_PATHS (driving_id, paths_id) values (1, 1);
INSERT INTO DRIVING_PATHS (driving_id, paths_id) values (1, 2);
INSERT INTO DRIVING_PATHS (driving_id, paths_id) values (2, 3);
INSERT INTO DRIVING_PATHS (driving_id, paths_id) values (3, 4);

INSERT INTO DRIVING_PASS (driving_id, pass_id) values (1, 1);
INSERT INTO DRIVING_PASS (driving_id, pass_id) values (2, 1);
INSERT INTO DRIVING_PASS (driving_id, pass_id) values (2, 2);
INSERT INTO DRIVING_PASS (driving_id, pass_id) values (3, 2);



