INSERT INTO customers (name, gender, available_amount_of_dollars, phone_number, account_id, number_document) VALUES ('Ilon', 'MALE', 100000, '+79888877777', 2, 414);

INSERT INTO appeals (created_date, currency, sum, account_id, is_verified_code_word, appeal, customer_id) VALUES ('Mon Sep 06 01:38:10 NOVT 2021', 1, 1000, 424, true, 'feedback', 1);
INSERT INTO appeals (created_date, currency, sum, caller_phone_number, is_verified_sms_confirmation, appeal, customer_id) VALUES ('Mon Sep 06 13:13:14 NOVT 2021', 2, 500, '+7988646545', true, 'call', 1);