DROP SEQUENCE IF EXISTS poliza_cliente_seq;
CREATE SEQUENCE IF NOT EXISTS poliza_cliente_seq AS integer MINVALUE 10000015 MAXVALUE 19999999;
INSERT INTO poliza
	(numero_poliza, chasis, estado, fecha_emision, fin_vigencia, forma_pago, guarda_garage, inicio_vigencia, km_realizados_por_anio, motor, numeros_siniestros_ultimo_anios, patente,
																																								suma_asegurada, tiene_alarma, tiene_rastreo_vehicular, tiene_tuercas_antirobo, valor_bonificacion_pago_semestral,
																																														valor_descuento, valor_premio, valor_prima, valor_riesgo_modelo, valor_riesgo_cobertura, valor_riesgo_ciudad, anio_modelo, id_ciudad, numero_cliente, parametros_poliza, solicitud_poliza, tipo_cobertura) VALUES
(3528000000000, 'R1124234', 'NO_VIGENTE', '2018-02-10', '2018-08-10', 'SEMESTRAL', false, '2018-02-11', '10.000 - 19.999',   '8CAFP3138R1124234', 'NINGUNO',    'PID652',  	666666, true,  true,  true,  0.65, 650, 88900, 86000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 110),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TERCEROS_COMPLETOS'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 3270), 110, 3270, 5400000001, 100, null, 'TERCEROS_COMPLETOS'),
(3528000000001, 'R1124234', 'NO_VIGENTE', '2018-02-10', '2019-02-10', 'SEMESTRAL', false, '2018-02-11', '20.000 - 29.999',   '8CAFP3138R1124234', 'NINGUNO',    'PID652',  	666666, true,  true,  true,  0.65, 650, 88900, 86000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 110),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TERCEROS_COMPLETOS'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 3270), 110, 3270, 5400000001, 100, null, 'TERCEROS_COMPLETOS'),
(3528000000002, 'R1124234', 'NO_VIGENTE', '2019-02-10', '2019-08-10', 'SEMESTRAL', false, '2019-02-11', '30.000 - 39.999',   '8CAFP3138R1124234', 'NINGUNO',    'PID652',  	666666, true,  true,  true,  0.65, 650, 88900, 86000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 110),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TERCEROS_COMPLETOS'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 3270), 110, 3270, 5400000001, 100, null, 'TERCEROS_COMPLETOS'),
(3528000000003, 'R1124234', 'VIGENTE',    '2019-02-10', '2020-02-10', 'SEMESTRAL', false, '2019-02-11', '30.000 - 39.999',   '8CAFP3138R1124234', 'NINGUNO',    'PID652',  	666666, true,  true,  true,  0.65, 650, 88900, 86000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 110),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TERCEROS_COMPLETOS'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 3270), 110, 3270, 5400000001, 100, null, 'TERCEROS_COMPLETOS'),
		
		
(3528000000100, 'H1102477', 'NO_VIGENTE', '2017-05-05', '2017-11-05', 'SEMESTRAL',   true,  '2017-05-06', '10.000 - 19.999',   '8ACEC19V4H1102477', 'NINGUNO',  'BC7821AD', 444444, true,  false, false, 0.65, 5000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 102),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 10782),  102, 10782, 5400000000, 100, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),		
(3528000000101, 'H1102477', 'NO_VIGENTE', '2017-11-05', '2018-05-05', 'SEMESTRAL',   true,  '2017-11-06', '20.000 - 29.999',   '8ACEC19V4H1102477', 'NINGUNO',  'BC7821AD', 444444, true,  false, false, 0.65, 5000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 102),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 10782),  102, 10782, 5400000000, 100, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
(3528000000102, 'H1102477', 'NO_VIGENTE', '2018-05-05', '2018-11-05', 'SEMESTRAL',   false, '2018-05-06', '30.000 - 39.999',   '8ACEC19V4H1102477', 'NINGUNO', 'BC7821AD', 444444, true,  false, false, 0.65, 5000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 102),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 10782),  102, 10782, 5400000000, 100, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
(3528000000103, 'H1102477', 'NO_VIGENTE', '2018-11-05', '2019-05-05', 'SEMESTRAL',   false, '2018-11-06', '40.000 - 49.999',   '8ACEC19V4H1102477', 'NINGUNO', 'BC7821AD', 444444, true,  false, false, 0.65, 5000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 102),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 10782),  102, 10782, 5400000000, 100, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
(3528000000104, 'H1102477', 'NO_VIGENTE', '2019-05-05', '2019-11-05', 'SEMESTRAL',   false, '2019-05-06', '50.000 - 59.999',   '8ACEC19V4H1102477', 'NINGUNO', 'BC7821AD', 444444, true,  false, false, 0.65, 5000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 102),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 10782),  102, 10782, 5400000000, 100, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
(3528000000105, 'H1102477', 'VIGENTE',    '2019-11-05', '2020-05-05', 'SEMESTRAL',   false, '2019-11-06', '60.000 - 69.999',   '8ACEC19V4H1102477', 'NINGUNO', 'BC7821AD', 444444, true,  false, false, 0.65, 5000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 102),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 10782),  102, 10782, 5400000000, 100, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL'),

(3528000000200, '28364781', 'NO_VIGENTE',  '2019-01-15', '2019-07-15', 'SEMESTRAL',   false, '2019-07-16', '60.000 - 69.999',   'DBASDA18628364781', 'UNO', 'MAA872', 555555, true,  false, false, 0.65, 5000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 120),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TERCEROS_COMPLETOS'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 5314),  120, 5314, 5400000002, 100, null, 'TERCEROS_COMPLETOS'),
(3528000000201, '28364781', 'VIGENTE',     '2019-07-15', '2020-01-15', 'SEMESTRAL',   false, '2019-07-16', '60.000 - 69.999',   'DBASDA18628364781', 'UNO', 'MAA872', 555555, true,  false, false, 0.65, 5000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 120),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TERCEROS_COMPLETOS'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 5314),  120, 5314, 5400000002, 100, null, 'TERCEROS_COMPLETOS'),
		
(3528000000300, '93842993', 'NO_VIGENTE',  '2018-02-11', '2018-08-11', 'SEMESTRAL',   false, '2018-02-12', '60.000 - 69.999',   'F8CV1204593842993', 'UNO', 'MAA872', 555555, true,  false, false, 0.65, 5000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 120),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TODO_TOTAL'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 5314),  120, 5314, 5400000003, 100, null, 'TODO_TOTAL'),
		
(3528000000400, '38912384', 'NO_VIGENTE',  '2019-03-25', '2019-09-25', 'SEMESTRAL',   false, '2019-03-26', '40.000 - 69.999',   'ASGFGF12A38912384', 'NINGUNO', 'AC8722AC', 777777, true,  false, false, 0.65, 5000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 132),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TODO_RIESGO_CON_FRANQUICIA'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 1230),  132, 1230, 5400000004, 100, null, 'TODO_RIESGO_CON_FRANQUICIA'),
(3528000000401, '38912384', 'VIGENTE',     '2019-09-25', '2020-03-25', 'SEMESTRAL',   false, '2019-09-26', '60.000 - 69.999',   'ASGFGF12A38912384', 'NINGUNO', 'AC8722AC', 777777, true,  false, false, 0.65, 5000, 116000, 115000,
		(select r.valor_porcentual from riesgo_modelo r, anio_modelo an where r.fin_vigencia is null and r.id_modelo=an.id_modelo and an.id = 132),
		(select r.valor_porcentual from riesgo_tipo_cobertura r  where r.fin_vigencia is null and r.tipo_cobertura = 'TODO_RIESGO_CON_FRANQUICIA'),
		(select r.valor_porcentual from riesgo_ciudad r  where r.fin_vigencia is null and r.id_ciudad = 1230),  132, 1230, 5400000004, 100, null, 'TODO_RIESGO_CON_FRANQUICIA');




INSERT INTO cuota (id_cuota, estado, monto, ultimo_dia_pago, numero_poliza, idx) VALUES
(NEXTVAL('id_cuota_seq'), 'IMPAGO', 23424, '2019-09-25', 3528000000401, 0);