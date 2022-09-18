insert into product_type (description, id) values ('Eletrodomestico', 'a263327f-e5b4-4f4e-bae9-5ffe3fb4f950');
insert into product_type (description, id) values ('Celular', '2978ba40-e731-47d5-91e7-a953758453e2');
insert into product_type (description, id) values ('Moveis', '0136d610-a3b2-4986-8258-0e6caddb123f');
insert into product_type (description, id) values ('Roupa', '95a4fdef-bcbe-4705-8edb-7c2c4368d500');

insert into product (code, description, price, quantity, type_id, id) values ('1234567890', 'Fogao 5 bocas', 450, 6, 'a263327f-e5b4-4f4e-bae9-5ffe3fb4f950', '175fe0cf-9611-437b-ba5a-5545eb4533a7');
insert into product (code, description, price, quantity, type_id, id) values ('0987654321', 'Celular Xiaomi 10S', 1450, 12, '2978ba40-e731-47d5-91e7-a953758453e2', '580d337e-d1f6-42cf-8ccd-5d4a623d54a7');
insert into product (code, description, price, quantity, type_id, id) values ('43785687456', 'Mesa com 4 Cadeiras Branca', 550, 4, '0136d610-a3b2-4986-8258-0e6caddb123f', '082b6e8c-7491-4f88-b921-ed72640c6d65');
insert into product (code, description, price, quantity, type_id, id) values ('3241423423', 'Camisa Polo G', 40, 10, '95a4fdef-bcbe-4705-8edb-7c2c4368d500', '33fb1c42-8a6b-4273-aa95-233e2bf195fd');

insert into stock_movement (date_sale, movement_type, product_id, quantity, total_sale, id) values ('20220914', 'SAIDA', '175fe0cf-9611-437b-ba5a-5545eb4533a7', 1, 600, 'd53ba76e-5da4-44a4-b9c4-12c15d710ad6');
insert into stock_movement (date_sale, movement_type, product_id, quantity, total_sale, id) values ('20220918', 'SAIDA', '580d337e-d1f6-42cf-8ccd-5d4a623d54a7', 1, 1700, '506d9cfe-55eb-4e63-bcf2-00b98a3fadb3');
insert into stock_movement (date_sale, movement_type, product_id, quantity, total_sale, id) values ('20220913', 'SAIDA', '082b6e8c-7491-4f88-b921-ed72640c6d65', 2, 1400, '91c06d43-ff3c-4801-9d88-7f42de1594a7');
insert into stock_movement (date_sale, movement_type, product_id, quantity, total_sale, id) values ('20220917', 'SAIDA', '33fb1c42-8a6b-4273-aa95-233e2bf195fd', 3, 160, '995b6b98-173d-4b6e-9f60-9b098fd99e2a');