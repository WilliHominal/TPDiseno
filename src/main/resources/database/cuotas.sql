INSERT INTO cuota
VALUES
   (101, null, 'IMPAGO', 'ADELANTADA', 23424, null, '2019-09-25', null, 3528000000401, 1),
   (102, null, 'IMPAGO', 'ADELANTADA', 23735, null, '2019-09-25', null, 3528000000401, 2),
   (103, null, 'PAGO', 'EN_TERMINO', 23987, null, '2019-09-25', null, 3528000000401, 3),
   (104, null, 'PAGO', 'EN_TERMINO', 24025, null, '2019-09-25', null, 3528000000401, 4),
   (105, null, 'IMPAGO', 'EN_TERMINO', 24411, null, '2019-09-25', null, 3528000000401, 5);

UPDATE cuota SET estado_pago_cuota = 'EN_MORA' WHERE id_cuota = 100;