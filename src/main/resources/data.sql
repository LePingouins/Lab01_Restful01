INSERT  INTO customers ( customer_id, last_name, first_name, email_address, street_address, postal_code, city, province,
                        username, password)
VALUES
    ('123e4567-e89b-12d3-a456-556642440000', 'Smith', 'John', 'john.smith@example.com', '123 Maple Street', 'M1M 1M1', 'Toronto', 'Ontario', 'sjhon', 'pwd1'),
    ('223e4567-e89b-12d3-a456-556642440001', 'Johnson', 'Emily', 'emily.johnson@example.com', '456 Oak Avenue', 'V6B 2W1', 'Vancouver', 'British Columbia' ,'jemily', 'pwd2'),
    ('323e4567-e89b-12d3-a456-556642440002', 'Wong', 'Michael', 'michael.wong@example.com', '789 Elm Street', 'H3H 2N2', 'Montreal', 'Quebec','wmichael', 'pwd3'),
    ('423e4567-e89b-12d3-a456-556642440003', 'Patel', 'Sara', 'sara.patel@example.com', '321 Pine Street', 'T2N 4T4', 'Calgary', 'Alberta','psara' , 'pwd4'),
    ('523e4567-e89b-12d3-a456-556642440004', 'Lee', 'David', 'david.lee@example.com', '987 Cedar Avenue', 'K1K 7L7', 'Ottawa', 'Ontario', 'ldavid', 'pwd5'),
    ('623e4567-e89b-12d3-a456-556642440005', 'Singh', 'Alisha', 'alisha.singh@example.com', '741 Birch Street', 'L5A 1X2', 'Mississauga', 'Ontario','salisha' , 'pwd6'),
    ('723e4567-e89b-12d3-a456-556642440006', 'Chen', 'Jason', 'jason.chen@example.com', '852 Elmwood Drive', 'B3A 2K6', 'Halifax', 'Nova Scotia','cjason', 'pwd7' ),
    ('823e4567-e89b-12d3-a456-556642440007', 'Garcia', 'Sophia', 'sophia.garcia@example.com', '963 Spruce Road', 'G1P 3T5', 'Quebec City', 'Quebec','gsophia' , 'pwd8'),
    ('923e4567-e89b-12d3-a456-556642440008', 'Martinez', 'Daniel', 'daniel.martinez@example.com', '654 Oak Lane', 'E1A 4R7', 'Fredericton', 'New Brunswick','mdaniel', 'pwd9' ),
    ('a23e4567-e89b-12d3-a456-556642440009', 'Kim', 'Jessica', 'jessica.kim@example.com', '852 Pinecrest Boulevard', 'A1A 5W3', 'St. Johns', 'Newfoundland and Labrador', 'kjessica', 'pwd10');;
INSERT INTO customer_phonenumbers (customer_id, type, number) VALUES
                                                                  (1, 'HOME', '5143456750'),(2, 'MOBILE', '438345675'),
                                                                  (3, 'MOBILE', '514345675'),(4, 'MOBILE', '514345675'),
                                                                  (5, 'HOME', '514345675'),(6, 'MOBILE', '4383450675'),
                                                                  (7, 'MOBILE', '5143456795'),(8, 'MOBILE', '4383456744'),
                                                                  (9, 'HOME', '5143456836'),(10, 'MOBILE', '4383456773');

INSERT INTO employees (employee_id, first_name, last_name, dob, email, title, salary)
VALUES
    ('E001', 'John', 'Doe', '1985-05-15', 'john.doe@example.com', 'Software Engineer', 75000.00),
    ('E002', 'Jane', 'Smith', '1990-08-22', 'jane.smith@example.com', 'Project Manager', 85000.00),
    ('E003', 'Michael', 'Johnson', '1988-03-10', 'michael.johnson@example.com', 'Data Analyst', 70000.00),
    ('E004', 'Emily', 'Brown', '1992-11-30', 'emily.brown@example.com', 'HR Specialist', 65000.00),
    ('E005', 'David', 'Wilson', '1987-07-18', 'david.wilson@example.com', 'Marketing Coordinator', 68000.00);


-- Insert data into the vehicles table
insert into inventories (inventory_id, type)
values ('d846a5a7-2e1c-4c79-809c-4f3f471e826d', 'vehicles');
insert into inventories (inventory_id, type)
values ('3fe5c169-c1ef-42ea-9e5e-870f30ba9dd0', 'vehicles-luxury');

insert into vehicles (vin, inventory_id, vehicle_status, usage_type, vehicle_year, manufacturer, make, model, body_class, msrp, cost, total_options_cost)
values ('JN8AS5MTXDW375966', 'd846a5a7-2e1c-4c79-809c-4f3f471e826d', 'AVAILABLE', 'NEW', 2013, 'NISSAN MOTOR COMPANY, LTD', 'NISSAN', 'Rogue', 'Crossover Utility Vehicle (CUV)', 73536.64, 64330.65, 15470.82);
insert into vehicles (vin, inventory_id, vehicle_status, usage_type, vehicle_year, manufacturer, make, model, body_class, msrp, cost, total_options_cost)
values ('3VW507AT0FM145078', 'd846a5a7-2e1c-4c79-809c-4f3f471e826d', 'AVAILABLE', 'USED', 2015, 'VOLKSWAGEN DE MEXICO SA DE CV', 'VOLKSWAGEN', 'Beetle', 'Convertible/Cabriolet', 56915.94, 51199.68, 00.00);
insert into vehicles (vin, inventory_id, vehicle_status, usage_type, vehicle_year, manufacturer, make, model, body_class, msrp, cost, total_options_cost) values ('2T1KU4EE5BC676204', 'd846a5a7-2e1c-4c79-809c-4f3f471e826d', 'SALE_PENDING', 'USED', 2011, 'TOYOTA MOTOR MANUFACTURING CANADA', 'TOYOTA', 'Corolla Matrix', 'Sedan/Saloon', 65712.51, 60259.06, 00.00);
insert into vehicles (vin, inventory_id, vehicle_status, usage_type, vehicle_year, manufacturer, make, model, body_class, msrp, cost, total_options_cost) values ('5N1AR1NB3CC804298', '3fe5c169-c1ef-42ea-9e5e-870f30ba9dd0', 'SALE_PENDING', 'NEW', 2012, 'NISSAN NORTH AMERICA, INC.', 'NISSAN', 'Pathfinder', 'Crossover Utility Vehicle (CUV)', 39112.98, 36915.15, 1900.00);
insert into vehicles (vin, inventory_id, vehicle_status, usage_type, vehicle_year, manufacturer, make, model, body_class, msrp, cost, total_options_cost) values ('WAUDGAFL4EA518776', '3fe5c169-c1ef-42ea-9e5e-870f30ba9dd0', 'SOLD', 'NEW', 2014, 'AUDI AG', 'AUDI', 'S4', 'Sedan/Saloon', 81384.04, 55761.93, 2900.00);
insert into vehicles (vin, inventory_id, vehicle_status, usage_type, vehicle_year, manufacturer, make, model, body_class, msrp, cost, total_options_cost) values ('SCFAB05D69G083403', 'd846a5a7-2e1c-4c79-809c-4f3f471e826d', 'SOLD', 'USED', 2009, 'ASTON MARTIN LAGONDA LIMITED', 'ASTON MARTIN', 'DBS', 'Coupe', 96447.83, 90442.16, 00.00);
insert into vehicles (vin, inventory_id, vehicle_status, usage_type, vehicle_year, manufacturer, make, model, body_class, msrp, cost, total_options_cost) values ('1N6AF0KX3FN483814', '3fe5c169-c1ef-42ea-9e5e-870f30ba9dd0', 'SOLD', 'NEW', 2015, 'NISSAN NORTH AMERICA, INC.', 'NISSAN', 'NV', 'Cargo Van', 87370.77, 76372.27, 10005.65);
insert into vehicles (vin, inventory_id, vehicle_status, usage_type, vehicle_year, manufacturer, make, model, body_class, msrp, cost, total_options_cost) values ('WBAYA8C50FG933061', '3fe5c169-c1ef-42ea-9e5e-870f30ba9dd0', 'AVAILABLE', 'USED', 2015, 'BMW AG', 'BMW', '750i / ALPINA B7', 'Sedan/Saloon', 44048.52, 42538.11, 00.00);
insert into vehicles (vin, inventory_id, vehicle_status, usage_type, vehicle_year, manufacturer, make, model, body_class, msrp, cost, total_options_cost) values ('4T1BF1FK5FU100601', '3fe5c169-c1ef-42ea-9e5e-870f30ba9dd0', 'AVAILABLE', 'NEW', 2015, 'TOYOTA MOTOR MANUFACTURING, KENTUCKY, INC.', 'TOYOTA', 'Camry', 'Sedan/Saloon', 73319.16, 46483.09, 5950.00);
insert into vehicles (vin, inventory_id, vehicle_status, usage_type, vehicle_year, manufacturer, make, model, body_class, msrp, cost, total_options_cost) values ('JH4CL96808C431274', 'd846a5a7-2e1c-4c79-809c-4f3f471e826d', 'AVAILABLE', 'USED', 2008, 'HONDA MOTOR CO., LTD', 'ACURA', 'TSX', 'Sedan/Saloon', 48970.84, 48097.54, 00.00);

insert into vehicle_options (vehicle_id, name, description, cost)
values (4, 'Driving Assistance Professional Package', '-inc: Active Cruise Control w/Stop & Go, Traffic Jam Assistant', 1900.00);
insert into vehicle_options (vehicle_id, name, description, cost)
values (5, 'Primary Paint', 'Aventurin Red Metallic', 1950.00);
insert into vehicle_options (vehicle_id, name, description, cost)
values (5, 'Wheels', '-inc: Increased Top Speed Limiter, Tires: 275/40R22 Performance Non Run-Flat', 950.00);
insert into vehicle_options (vehicle_id, name, description, cost)
values (9, 'Sports Package', '-inc: Shadowline Exterior Trim, Aluminum Tetragon Trim, M Steering Wheel', 3050.00);
insert into vehicle_options (vehicle_id, name, description, cost)
values (9, 'Seat Trim', 'Black w/Blue Stitching, Vernasca Leather Upholstery', 1500.00);
insert into vehicle_options (vehicle_id, name, description, cost)
values (9, 'Premium Package', '-inc: Comfort Access Keyless Entry, Driver Lumbar Support, Live Cockpit Pro w/Navi, Heated Steering Wheel, Heated Front Seats', 1400.00);
insert into vehicle_options (vehicle_id, name, description, cost)
values (1,'Wheels', '-inc: Style 1023, Increased Top Speed Limiter, Tires: 275/40R22 Performance Non Run-Flat', 1794.17);
insert into vehicle_options (vehicle_id, name, description, cost)
values (1, 'Seat Trim', 'Castanea Chestnut, Olive Leaf Tanned Perforated Leather Upholstery', 4038.33);
insert into vehicle_options (vehicle_id, name, description, cost)
values (1, 'Executive Package', '-inc: Active Cruise Control w/Stop & Go, Glass & Wood Controls, Soft-Close Automatic Doors, Traffic Jam Assistant, Active Lane', 9638.32);


insert into sales (purchase_id, inventory_id, vin, customer_id, employee_id, amount, currency, sale_status,
                   number_of_monthly_payments, monthly_payment_amount, down_payment_amount, payment_currency,
                   sale_offer_date, warranty_end_date, warranty_terms )
values('05c8ab76-4f75-45c1-b6e2-aa8e914ea08f',
       'd846a5a7-2e1c-4c79-809c-4f3f471e826d',
       'JN8AS5MTXDW375966', 'c3540a89-cb47-4c96-888e-ff96708db4d8', 'e5913a79-9b1e-4516-9ffd-06578e7af261',
       75000.00, 'USD', 'PURCHASE_OFFER', 36, 1805.56, 10000.00, 'CAD', '2024-2-13', '2027-2-13',
       'Basic Warranty: The basic warranty covers the vehicle against defects in materials or workmanship for a specific
      period, typically around 3 to 5 years.');

