DROP SEQUENCE IF EXISTS poliza_cliente_seq;
CREATE SEQUENCE IF NOT EXISTS poliza_cliente_seq AS integer MINVALUE 10000015 MAXVALUE 19999999;
INSERT INTO poliza
	(numero_poliza, chasis, estado, fecha_emision, inicio_vigencia, fin_vigencia, forma_pago, guarda_garage, km_realizados_por_anio, motor, numeros_siniestros_ultimo_anios, patente,
																																								suma_asegurada, tiene_alarma, tiene_rastreo_vehicular, tiene_tuercas_antirobo, valor_bonificacion_pago_semestral,
																																														valor_descuento, valor_premio, valor_prima, valor_riesgo_modelo, valor_riesgo_cobertura, valor_riesgo_ciudad, anio_modelo, id_ciudad, numero_cliente, parametros_poliza, solicitud_poliza, tipo_cobertura) VALUES
(3528000000000, 'R1124234', 'NO_VIGENTE', '2018-02-10', '2018-02-11', '2018-08-10', 'SEMESTRAL', false, '10.000 - 19.999',   '8CAFP3138R1124234', 'NINGUNO',    'PID652',  	666666, true,  true,  true,  0.65, 30000, 58000, 86000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 110),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TERCEROS_COMPLETOS'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 3270), 110, 3270, 5400000001, 100, null, 'TERCEROS_COMPLETOS'),
(3528000000001, 'R1124234', 'NO_VIGENTE', '2018-08-07', '2018-08-11', '2019-02-10', 'SEMESTRAL', false, '20.000 - 29.999',   '8CAFP3138R1124234', 'NINGUNO',    'PID652',  	666666, true,  true,  true,  0.65, 30000, 59500, 86000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 110),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TERCEROS_COMPLETOS'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 3270), 110, 3270, 5400000001, 100, null, 'TERCEROS_COMPLETOS'),
(3528000000002, 'R1124234', 'NO_VIGENTE', '2019-02-08', '2019-02-11', '2019-08-10', 'SEMESTRAL', false, '30.000 - 39.999',   '8CAFP3138R1124234', 'NINGUNO',    'PID652',  	666666, true,  true,  true,  0.65, 30000, 61000, 86000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 110),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TERCEROS_COMPLETOS'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 3270), 110, 3270, 5400000001, 100, null, 'TERCEROS_COMPLETOS'),
(3528000000003, 'R1124234', 'VIGENTE',    '2019-08-09', '2019-08-11', '2020-02-10', 'SEMESTRAL', false, '30.000 - 39.999',   '8CAFP3138R1124234', 'NINGUNO',    'PID652',  	666666, true,  true,  true,  0.65, 30000, 63000, 86000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 110),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TERCEROS_COMPLETOS'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 3270), 110, 3270, 5400000001, 100, null, 'TERCEROS_COMPLETOS'),	

(3528000000100, 'H1102477', 'NO_VIGENTE', '2017-05-05', '2017-05-06', '2017-11-05', 'SEMESTRAL',   true,  '10.000 - 19.999',   '8ACEC19V4H1102477', 'NINGUNO',  'BC7821AD', 444444, true,  false, false, 0.65, 50000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 102),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 10782),  102, 10782, 5400000000, 100, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),		
(3528000000101, 'H1102477', 'NO_VIGENTE', '2017-11-05', '2017-11-06', '2018-05-05', 'SEMESTRAL',   true,  '20.000 - 29.999',   '8ACEC19V4H1102477', 'NINGUNO',  'BC7821AD', 444444, true,  false, false, 0.65, 50000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 102),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 10782),  102, 10782, 5400000000, 100, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
(3528000000102, 'H1102477', 'NO_VIGENTE', '2018-05-05', '2018-05-06', '2018-11-05', 'SEMESTRAL',   false, '30.000 - 39.999',   '8ACEC19V4H1102477', 'NINGUNO', 'BC7821AD', 444444, true,  false, false, 0.65, 50000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 102),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 10782),  102, 10782, 5400000000, 100, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
(3528000000103, 'H1102477', 'NO_VIGENTE', '2018-11-05', '2018-11-06', '2019-05-05', 'SEMESTRAL',   false, '40.000 - 49.999',   '8ACEC19V4H1102477', 'NINGUNO', 'BC7821AD', 444444, true,  false, false, 0.65, 50000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 102),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 10782),  102, 10782, 5400000000, 100, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
(3528000000104, 'H1102477', 'NO_VIGENTE', '2019-05-05', '2019-05-06', '2019-11-05', 'SEMESTRAL',   false, '50.000 - 59.999',   '8ACEC19V4H1102477', 'NINGUNO', 'BC7821AD', 444444, true,  false, false, 0.65, 50000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 102),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 10782),  102, 10782, 5400000000, 100, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
(3528000000105, 'H1102477', 'VIGENTE',    '2019-11-05', '2019-11-06', '2020-05-05', 'SEMESTRAL',   false, '60.000 - 69.999',   '8ACEC19V4H1102477', 'NINGUNO', 'BC7821AD', 444444, true,  false, false, 0.65, 50000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 102),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 10782),  102, 10782, 5400000000, 100, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),

(3528000000200, '28364781', 'NO_VIGENTE',  '2019-01-15', '2019-07-16', '2019-07-15', 'SEMESTRAL',   false, '60.000 - 69.999',   'DBASDA18628364781', 'UNO', 'MAA872', 555555, true,  false, false, 0.65, 50000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 120),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TERCEROS_COMPLETOS'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 5314),  120, 5314, 5400000002, 100, null, 'TERCEROS_COMPLETOS'),
(3528000000201, '28364781', 'VIGENTE',     '2019-07-15', '2019-07-16', '2020-01-15', 'SEMESTRAL',   false, '60.000 - 69.999',   'DBASDA18628364781', 'UNO', 'MAA872', 555555, true,  false, false, 0.65, 50000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 120),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TERCEROS_COMPLETOS'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 5314),  120, 5314, 5400000002, 100, null, 'TERCEROS_COMPLETOS'),
		
(3528000000300, '93842993', 'GENERADA',  '2019-11-11', '2019-12-12', '2020-06-11', 'MENSUAL',   false, '60.000 - 69.999',   'F8CV1204593842993', 'UNO', 'MAA872', 555555, true,  false, false, 0.65, 0, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 120),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TODO_TOTAL'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 5314),  120, 5314, 5400000003, 100, null, 'TODO_TOTAL'),
		
(3528000000400, '38912384', 'NO_VIGENTE',  '2019-03-25', '2019-03-26', '2019-09-25', 'SEMESTRAL',   false, '40.000 - 69.999',   'ASGFGF12A38912384', 'NINGUNO', 'AC8722AC', 777777, true,  false, false, 0.65, 50000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 132),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TODO_RIESGO_CON_FRANQUICIA'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 1230),  132, 1230, 5400000004, 100, null, 'TODO_RIESGO_CON_FRANQUICIA'),
(3528000000401, '38912384', 'VIGENTE',     '2019-09-25', '2019-09-26', '2020-03-25', 'MENSUAL',   false, '60.000 - 69.999',   'ASGFGF12A38912384', 'NINGUNO', 'AC8722AC', 777777, true,  false, false, 0.65, 0, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 132),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TODO_RIESGO_CON_FRANQUICIA'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 1230),  132, 1230, 5400000004, 100, null, 'TODO_RIESGO_CON_FRANQUICIA'),
(3528000001401, '38912100', 'NO_VIGENTE',     '2018-10-24', '2018-10-25', '2019-04-24', 'MENSUAL',   false, '60.000 - 69.999',   'ASGFGF12A38912100', 'NINGUNO', 'AC1111AC', 777777, true,  false, false, 0.65, 0, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 132),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TODO_RIESGO_CON_FRANQUICIA'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 1230),  132, 1230, 5400000004, 100, null, 'TODO_RIESGO_CON_FRANQUICIA'),
(3528000002401, '38912200', 'NO_VIGENTE',     '2018-05-11', '2018-05-12', '2018-11-11', 'MENSUAL',   false, '60.000 - 69.999',   'ASGFGF12A38912200', 'NINGUNO', 'AC2222AC', 777777, true,  false, false, 0.65, 0, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 132),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TODO_RIESGO_CON_FRANQUICIA'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 1230),  132, 1230, 5400000004, 100, null, 'TODO_RIESGO_CON_FRANQUICIA'),
(3528000003401, '38912300', 'NO_VIGENTE',     '2017-11-25', '2017-11-26', '2018-05-25', 'MENSUAL',   false, '60.000 - 69.999',   'ASGFGF12A38912300', 'NINGUNO', 'AC3333AC', 777777, true,  false, false, 0.65, 0, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 132),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TODO_RIESGO_CON_FRANQUICIA'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 1230),  132, 1230, 5400000004, 100, null, 'TODO_RIESGO_CON_FRANQUICIA'),
(3528000004401, '38912400', 'NO_VIGENTE',     '2017-05-28', '2017-05-29', '2017-11-28', 'MENSUAL',   false, '60.000 - 69.999',   'ASGFGF12A38912400', 'NINGUNO', 'AC4444AC', 777777, true,  false, false, 0.65, 0, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 132),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TODO_RIESGO_CON_FRANQUICIA'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 1230),  132, 1230, 5400000004, 100, null, 'TODO_RIESGO_CON_FRANQUICIA');
		
INSERT INTO pago (numero_recibo, fecha_pago, hora, importe_parcial, operador, premio) VALUES
(100, '2018-02-10', '10:10:10',  28000, 'Operador n', 58000),
(101, '2018-08-10', '16:30:25',  29500, 'Operador n', 59500),
(102, '2019-02-10', '17:15:11',  31000, 'Operador n', 61000),
(103, '2019-08-10', '11:22:40',  33000, 'Operador n', 63000),
(104, '2017-05-05', '18:52:45',  66000, 'Operador n', 116000),
(105, '2017-11-05', '17:45:01',  66000, 'Operador n', 116000),
(106, '2018-05-05', '13:33:31',  66000, 'Operador n', 116000),
(107, '2018-11-05', '11:11:11',  66000, 'Operador n', 116000),
(108, '2019-05-05', '07:42:48',  66000, 'Operador n', 116000),
(109, '2019-11-05', '18:19:07',  66000, 'Operador n', 116000),
(110, '2019-01-15', '10:24:35',  66000, 'Operador n', 116000),
(111, '2019-07-15', '15:07:19',  66000, 'Operador n', 116000),
(112, '2019-03-25', '09:21:03',  66000, 'Operador n', 116000),
(113, '2019-09-25', '12:30:35',  19333.3, 'Operador n', 116000),
(114, '2019-10-25', '16:15:14',  19333.3, 'Operador n', 116000),
(115, '2019-11-25', '19:11:25',  19333.3, 'Operador n', 116000);

INSERT INTO cuota (id_cuota, bonificacion_pago_adelantado, estado, estado_pago_cuota, monto, recargo_por_mora, ultimo_dia_pago, numero_recibo, numero_poliza, idx) VALUES
(NEXTVAL('id_cuota_seq'), 0, 'PAGO',  	'EN_TERMINO', 28000, 0, '2018-02-10', 100, 3528000000000, 0),
(NEXTVAL('id_cuota_seq'), 0, 'PAGO',  	'EN_TERMINO', 29500, 0, '2018-08-10', 101, 3528000000001, 0), 
(NEXTVAL('id_cuota_seq'), 0, 'PAGO',  	'EN_TERMINO', 31000, 0, '2019-02-10', 102, 3528000000002, 0),
(NEXTVAL('id_cuota_seq'), 0, 'PAGO',  	'EN_TERMINO', 33000, 0, '2019-08-10', 103, 3528000000003, 0),	

(NEXTVAL('id_cuota_seq'), 0, 'PAGO',	'EN_TERMINO', 66000, 0, '2017-05-05', 104, 3528000000100, 0),
(NEXTVAL('id_cuota_seq'), 0, 'PAGO',	'EN_TERMINO', 66000, 0, '2017-11-05', 105, 3528000000101, 0), 
(NEXTVAL('id_cuota_seq'), 0, 'PAGO',  	'EN_TERMINO', 66000, 0, '2018-05-05', 106, 3528000000102, 0),
(NEXTVAL('id_cuota_seq'), 0, 'PAGO',	'EN_TERMINO', 66000, 0, '2018-11-05', 107, 3528000000103, 0),
(NEXTVAL('id_cuota_seq'), 0, 'PAGO',  	'EN_TERMINO', 66000, 0, '2019-05-05', 108, 3528000000104, 0),
(NEXTVAL('id_cuota_seq'), 0, 'PAGO',	'EN_TERMINO', 66000, 0, '2019-11-05', 109, 3528000000105, 0),

(NEXTVAL('id_cuota_seq'), 0, 'PAGO',  	'EN_TERMINO', 66000, 0, '2019-01-15', 110, 3528000000200, 0),
(NEXTVAL('id_cuota_seq'), 0, 'PAGO',	'EN_TERMINO', 66000, 0, '2019-07-15', 111, 3528000000201, 0),

(NEXTVAL('id_cuota_seq'), 0, 'IMPAGO',	null, 19333.33, 0, '2019-12-11', null, 3528000000300, 0),
(NEXTVAL('id_cuota_seq'), 0, 'IMPAGO',	null, 19333.33, 0, '2020-01-11', null, 3528000000300, 1),
(NEXTVAL('id_cuota_seq'), 0, 'IMPAGO',	null, 19333.33, 0, '2020-02-11', null, 3528000000300, 2),
(NEXTVAL('id_cuota_seq'), 0, 'IMPAGO',	null, 19333.33, 0, '2020-03-11', null, 3528000000300, 3),
(NEXTVAL('id_cuota_seq'), 0, 'IMPAGO',	null, 19333.33, 0, '2020-04-11', null, 3528000000300, 4),
(NEXTVAL('id_cuota_seq'), 0, 'IMPAGO',	null, 19333.33, 0, '2020-05-11', null, 3528000000300, 5),

(NEXTVAL('id_cuota_seq'), 0, 'PAGO',  	'EN_TERMINO', 66000, 0, '2019-03-25', 112, 3528000000400, 0),
(NEXTVAL('id_cuota_seq'), 0, 'PAGO',	'EN_TERMINO', 19333.33, 0, '2019-09-25', 113, 3528000000401, 0),
(NEXTVAL('id_cuota_seq'), 0, 'PAGO',	'EN_TERMINO', 19333.33, 0, '2019-10-25', 114, 3528000000401, 1),
(NEXTVAL('id_cuota_seq'), 0, 'PAGO',	'EN_TERMINO', 19333.33, 0, '2019-11-25', 115, 3528000000401, 2),
(NEXTVAL('id_cuota_seq'), 0, 'IMPAGO',	null, 19333.33, 0, '2019-12-25', null, 3528000000401, 3),
(NEXTVAL('id_cuota_seq'), 0, 'IMPAGO',	null, 19333.33, 0, '2020-01-25', null, 3528000000401, 4),
(NEXTVAL('id_cuota_seq'), 0, 'IMPAGO',	null, 19333.33, 0, '2020-02-25', null, 3528000000401, 5);