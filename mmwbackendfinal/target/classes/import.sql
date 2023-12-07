INSERT INTO "public"."order_main" VALUES (1000000001, '76 Banana Lane', 'customer2@codearise.com', 'customer2', '0628491748', '2022-01-17 20:00:00', 100.00, 0, '2022-01-17 21:00:00');
INSERT INTO "public"."order_main" VALUES (1000000002, '76 Banana Lane', 'customer2@codearise.com', 'customer2', '0628491748', '2022-01-17 20:00:00', 4.00, 0, '2022-01-17 21:00:00');
INSERT INTO "public"."order_main" VALUES (1000000003, '76 Banana Lane', 'customer2@codearise.com', 'customer2', '0628491748', '2022-01-17 20:00:00', 180.00, 2, '2022-01-17 21:00:00');
INSERT INTO "public"."order_main" VALUES (1000000004, '76 Banana Lane', 'customer2@codearise.com', 'customer2', '0628491748', '2022-01-17 20:00:00', 2.00, 2, '2022-01-17 21:00:00');
INSERT INTO "public"."order_main" VALUES (1000000005, '76 Banana Lane', 'customer2@codearise.com', 'customer2', '0628491748', '2022-01-17 20:00:00', 150.00, 0, '2022-01-17 21:00:00');
INSERT INTO "public"."order_main" VALUES (1000000006, '28 Strawberry Street', 'customer1@codearise.com', 'customer1', '0684738299', '2022-01-17 20:00:00', 4.00, 2, '2022-01-17 21:00:00');
INSERT INTO "public"."order_main" VALUES (1000000007, '28 Strawberry Street', 'customer1@codearise.com', 'customer1', '0684738299', '2022-01-17 20:00:00', 20.00, 2, '2022-01-17 21:00:00');
INSERT INTO "public"."order_main" VALUES (1000000008, '28 Strawberry Street', 'customer1@codearise.com', 'customer1', '0684738299', '2022-01-17 20:00:00', 134.00, 1, '2022-01-17 21:00:00');

INSERT INTO "public"."product_category" VALUES (1000000003, 'Music', 0, '2022-01-17 20:00:00', '2022-01-17 21:00:00');
INSERT INTO "public"."product_category" VALUES (1000000002, 'Instruments', 1, '2022-01-17 20:00:00', '2022-01-17 21:00:00');
INSERT INTO "public"."product_category" VALUES (1000000006, 'Software', 2, '2022-01-17 20:00:00', '2022-01-17 21:00:00');
INSERT INTO "public"."product_category" VALUES (1000000009, 'Accessories', 3, '2022-01-17 20:00:00', '2022-01-17 21:00:00');

INSERT INTO "public"."product_in_order" VALUES (1000000006, 0,1, 'Motionless In White Album Released In 2019', 'https://upload.wikimedia.org/wikipedia/en/b/b1/DisguiseMIWcover.jpg', 'M001', 'Disguise', 15.00, 275, NULL, 1000000003);
INSERT INTO "public"."product_in_order" VALUES (1000000009, 0,1, 'Black Veil Brides Album Released In 2021', 'https://upload.wikimedia.org/wikipedia/en/7/77/Black_Veil_Brides_-_The_Phantom_Tomorrow.png', 'M002', 'The Phantom Tomorrow', 20.00, 300, NULL, 1000000001);
INSERT INTO "public"."product_in_order" VALUES (1000000010, 3,1, 'A Luxury Set', 'https://codearise.com/img/iprwc/plectrums.jpg', 'A001', 'Plectrums', 5.00, 390, NULL, 1000000002);
INSERT INTO "public"."product_in_order" VALUES (1000000008, 2,1, 'An Advanced Feature-Rich DAW For All Your Studio Recording Needs', 'https://codearise.com/img/iprwc/cubase.png', 'S002', 'Cubase', 375.00, 50, NULL, 1000000004);
INSERT INTO "public"."product_in_order" VALUES (1000000007, 3,1, 'A Luxury Set', 'https://codearise.com/img/iprwc/plectrums.jpg', 'A001', 'Plectrums', 5.00, 390, NULL, 1000000005);
INSERT INTO "public"."product_in_order" VALUES (1000000003, 1,1, 'The Perfect Instrument For Acoustic Performances', 'https://codearise.com/img/iprwc/guitar.jpg', 'I002', 'Classical Guitar', 125.00, 150, NULL, 1000000005);
INSERT INTO "public"."product_in_order" VALUES (1000000011, 3,1, 'The Perfect Device For Crisp And Clear Karaoke', 'https://codearise.com/img/iprwc/microphone.jpg', 'A002', 'Microphone', 95.00, 250, NULL, 1000000005);
INSERT INTO "public"."product_in_order" VALUES (1000000001, 0,1, 'Motionless In White Album Released In 2019', 'https://upload.wikimedia.org/wikipedia/en/b/b1/DisguiseMIWcover.jpg', 'M001', 'Disguise', 15.00, 275, NULL, 1000000008);
INSERT INTO "public"."product_in_order" VALUES (1000000012, 1,1, 'The Perfect Instrument For Rock And Metal Performances', 'https://codearise.com/img/iprwc/electrical_guitar.jpg', 'I001', 'Electrical Guitar', 175.00, 120, NULL, 1000000005);
INSERT INTO "public"."product_in_order" VALUES (1000000013, 0,1, 'Nickelback Rock Album Released In 2011', 'https://upload.wikimedia.org/wikipedia/en/f/f9/Nickelback_Here_and_Now_170x170-75.jpg', 'M005', 'Here and Now', 30.00, 0, NULL, 1000000002);
INSERT INTO "public"."product_in_order" VALUES (1000000004, 2,1, 'An Advanced Feature-Rich DAW For All Your Studio Recording Needs', 'https://codearise.com/img/iprwc/cubase.png', 'S002', 'Cubase', 375.00, 50, NULL, 1000000002);
INSERT INTO "public"."product_in_order" VALUES (1000000014, 0,1, 'Nickelback Rock Album Released In 2017', 'https://upload.wikimedia.org/wikipedia/en/9/9b/FeedtheMachineNickelbackAlbum.jpg', 'M004', 'Feed The Machine', 30.00, 250, NULL, 1000000002);
INSERT INTO "public"."product_in_order" VALUES (1000000005, 0,1, 'Motionless In White Album Released In 2019', 'https://upload.wikimedia.org/wikipedia/en/b/b1/DisguiseMIWcover.jpg', 'M001', 'Disguise', 15.00, 275, NULL, 1000000002);
INSERT INTO "public"."product_in_order" VALUES (1000000015, 3,1, 'The Perfect Device For Crisp And Clear Karaoke', 'https://codearise.com/img/iprwc/microphone.jpg', 'A002', 'Microphone', 95.00, 250, NULL ,1000000007);
INSERT INTO "public"."product_in_order" VALUES (1000000016, 3,1, 'A Luxury Set', 'https://codearise.com/img/iprwc/plectrums.jpg', 'A001', 'Plectrums', 5.00, 390, NULL ,1000000006);

INSERT INTO "public"."product_info" VALUES ('M001', 0, '2022-01-17 20:00:00', 'Motionless In White Album Released In 2019', 'https://upload.wikimedia.org/wikipedia/en/b/b1/DisguiseMIWcover.jpg', 'Disguise', 15.00, 0, 275, '2022-01-17 21:00:00');
INSERT INTO "public"."product_info" VALUES ('M002', 0, '2022-01-17 20:00:00', 'Black Veil Brides Album Released In 2021', 'https://upload.wikimedia.org/wikipedia/en/7/77/Black_Veil_Brides_-_The_Phantom_Tomorrow.png', 'The Phantom Tomorrow', 20.00, 0, 300, '2022-01-17 21:00:00');
INSERT INTO "public"."product_info" VALUES ('M003', 0, '2022-01-17 20:00:00', 'Black Veil Brides Album Released In 2020', 'https://upload.wikimedia.org/wikipedia/en/c/cf/Re-Stitch_These_Wounds_-_cover_artwork.jpg', 'Re-Stitch These Wounds', 15.00, 0, 150, '2022-01-17 21:00:00');
INSERT INTO "public"."product_info" VALUES ('M004', 0, '2022-01-17 20:00:00', 'Nickelback Rock Album Released In 2017', 'https://upload.wikimedia.org/wikipedia/en/9/9b/FeedtheMachineNickelbackAlbum.jpg', 'Feed The Machine', 30.00, 0, 250, '2022-01-17 21:00:00');
INSERT INTO "public"."product_info" VALUES ('M005', 0, '2022-01-17 20:00:00', 'Nickelback Rock Album Released In 2011', 'https://upload.wikimedia.org/wikipedia/en/f/f9/Nickelback_Here_and_Now_170x170-75.jpg', 'Here and Now', 30.00, 1, 0, '2022-01-17 21:00:00');
INSERT INTO "public"."product_info" VALUES ('M006', 0, '2022-01-17 20:00:00', 'Motionless In White Album Released In 2012', 'https://upload.wikimedia.org/wikipedia/en/d/da/Motionless_in_white_infamous.jpg', 'Infamous', 20.00, 1, 0, '2022-01-17 21:00:00');
INSERT INTO "public"."product_info" VALUES ('I001', 1, '2022-01-17 20:00:00', 'The Perfect Instrument For Rock And Metal Performances', 'https://codearise.com/img/iprwc/electrical_guitar.jpg', 'Electrical Guitar', 175.00, 0, 120, '2022-01-17 21:00:00');
INSERT INTO "public"."product_info" VALUES ('I002', 1, '2022-01-17 20:00:00', 'The Perfect Instrument For Acoustic Performances', 'https://codearise.com/img/iprwc/guitar.jpg', 'Classical Guitar', 125.00, 0, 150, '2022-01-17 21:00:00');
INSERT INTO "public"."product_info" VALUES ('I003', 1, '2022-01-17 20:00:00', 'The Instrument That Lets You Play  The Sound Of Any Instrument', 'https://codearise.com/img/iprwc/keyboard.jpg', 'Keyboard', 225.00, 0, 200, '2022-01-17 21:00:00');
INSERT INTO "public"."product_info" VALUES ('S001', 2, '2022-01-17 20:00:00', 'An Amazing DAW For Live Performances', 'https://codearise.com/img/iprwc/ableton.png', 'Ableton Live', 435.00, 0, 50, '2022-01-17 21:00:00');
INSERT INTO "public"."product_info" VALUES ('S002', 2, '2022-01-17 20:00:00', 'An Advanced Feature-Rich DAW For All Your Studio Recording Needs', 'https://codearise.com/img/iprwc/cubase.png', 'Cubase', 375.00, 0, 50, '2022-01-17 21:00:00');
INSERT INTO "public"."product_info" VALUES ('A001', 3, '2022-01-17 20:00:00', 'A Luxury Set Of Shiny 3D Printed Plectrums For Any Guitar', 'https://codearise.com/img/iprwc/plectrums.jpg', 'Plectrums', 5.00, 0, 390, '2022-01-17 21:00:00');
INSERT INTO "public"."product_info" VALUES ('A002', 3, '2022-01-17 20:00:00', 'The Perfect Device For Crisp And Clear Karaoke', 'https://codearise.com/img/iprwc/microphone.jpg', 'Microphone', 95.00, 0, 250, '2022-01-17 21:00:00');

INSERT INTO "public"."users" VALUES (1000000003, 't', '28 Strawberry Street', 'customer1@codearise.com', 'customer1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', '0684738299', 'ROLE_CUSTOMER');
INSERT INTO "public"."users" VALUES (1000000006, 't', '39 Peach Lane', 'admin@codearise.com', 'admin', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', '0684938449', 'ROLE_MANAGER');
INSERT INTO "public"."users" VALUES (1000000001, 't', '92 Mango Road', 'employee1@codearise.com', 'employee1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', '0637849589', 'ROLE_EMPLOYEE');
INSERT INTO "public"."users" VALUES (1000000002, 't', '76 Banana Lane', 'customer2@codearise.com', 'customer2', '$2a$10$0oho5eUbDqKrLH026A2YXuCGnpq07xJpuG/Qu.PYb1VCvi2VMXWNi', '0628491748', 'ROLE_CUSTOMER');

INSERT INTO "public"."cart" VALUES (1000000003);
INSERT INTO "public"."cart" VALUES (1000000006);
INSERT INTO "public"."cart" VALUES (1000000001);
INSERT INTO "public"."cart" VALUES (1000000002);


