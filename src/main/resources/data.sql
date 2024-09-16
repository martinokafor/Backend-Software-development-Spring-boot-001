INSERT INTO customer VALUES
(1, 0, "2024-08-21", null,"karlsruhe", "victor"),
(2, 0, "2024-08-21", null, "berlin", "new customer"),
(3, 0, "2024-08-21", null,"karlsruhe", "martin"),
(4, 0, "2024-08-21", null, "berlin", "paul"),
(5, 0, "2024-08-21", null,"karlsruhe", "silas"),
(6, 0, "2024-08-21", null, "berlin", "father"),
(7, 0, "2024-08-21", null,"karlsruhe", "mother"),
(8, 0, "2024-08-21", null, "berlin", "miriam"),
(9, 0, "2024-08-21", null,"karlsruhe", "brother"),
(10, 0, "2024-08-21", null, "berlin", "samuel");

---
INSERT INTO customer_order VALUES
(true, 1, 1, "VW", "Euro", "10000"),
(true, 1, 2, "Mercedes", "Euro", "70000");

---
INSERT INTO vehicle (vin, vehicle_name, model, customer_id, order_id, created_on, updated_on)
VALUES
("8720c4ef-81e1-4f83-9c03-e18b44171106", "Volkswagen", "POLO", 1, 1, "2024-08-21", "2024-08-21"),
("283fe532-f7a4-4a27-a477-d49ed32afa3a", "Volkswagen", "PHATFARM", 1, 1, "2024-08-21", "2024-08-21"),
("0e340e45-8275-4e3a-aafa-d173b690584b", "Volkswagen", "PASSAT", 1, 1, "2024-08-21", "2024-08-21"),
("265aee11-7e42-42dd-acd3-c9c0a541ca67", "Volkswagen", "PASSAT", 1, 1, "2024-08-21", "2024-08-21"),
("0399c0cf-c06c-442f-9478-8a878ec719ac", "Mercedes", "C class", 1, 2, "2024-08-21", "2024-08-21"),
("07264d31-6e64-492b-b1b8-39369e0eefae", "Mercedes", "Beatle", 1, 2, "2024-08-21", "2024-08-21"),
("7dcac1a3-ddd0-491f-8c4b-b8dfe20db348", "Mercedes", "Coupe", 1, 2, "2024-08-21", "2024-08-21"),
("169a51fa-f006-46a2-af48-68a5963c05ec", "Mercedes", "E class", 1, 2, "2024-08-21", "2024-08-21"),
("89ca9d0d-c28e-4748-954e-6d047da47130", "Mercedes", "benz", 1, 2, "2024-08-21", "2024-08-21"),
("6f8f97d7-c785-4c0a-81a3-1bdc464901a8", "Mercedes", "benz", 1, 2, "2024-08-21", "2024-08-21");

---
INSERT INTO customer_users (customer_id, id, password, role, user_name)
VALUES
(1, 1, "aaaaaaa", "user", "AAA"),
(1, 2, "xxxxxxx", "user", "ABC"),
(1, 3, "xxxxxxx", "user", "BCA");

---
INSERT INTO user_vehicle (user_id, vehicle_vin)
VALUES
(1, "8720c4ef-81e1-4f83-9c03-e18b44171106"),
(2, "8720c4ef-81e1-4f83-9c03-e18b44171106"),
(3, "8720c4ef-81e1-4f83-9c03-e18b44171106"),
(1, "283fe532-f7a4-4a27-a477-d49ed32afa3a"),
(2, "283fe532-f7a4-4a27-a477-d49ed32afa3a"),
(3, "283fe532-f7a4-4a27-a477-d49ed32afa3a"),
(1, "0e340e45-8275-4e3a-aafa-d173b690584b"),
(2, "265aee11-7e42-42dd-acd3-c9c0a541ca67"),
(3, "0399c0cf-c06c-442f-9478-8a878ec719ac"),
(2, "0399c0cf-c06c-442f-9478-8a878ec719ac"),
(3, "8720c4ef-81e1-4f83-9c03-e18b44171106"),
(1, "7dcac1a3-ddd0-491f-8c4b-b8dfe20db348"),
(3, "7dcac1a3-ddd0-491f-8c4b-b8dfe20db348"),
(1, "07264d31-6e64-492b-b1b8-39369e0eefae"),
(2, "0399c0cf-c06c-442f-9478-8a878ec719ac"),
(2, "07264d31-6e64-492b-b1b8-39369e0eefae"),
(1, "7dcac1a3-ddd0-491f-8c4b-b8dfe20db348");
