INSERT INTO parametros_poliza
	(codigo_parametros_poliza, descuento_unidad_adicional, fin_vigencia, inicio_vigencia, porcentaje_ajuste_km, porcentaje_alarma, porcentaje_dos_siniestro, porcentaje_guarda_en_garage, porcentaje_mayor_a_dos_siniestro,
	 porcentaje_ningun_siniestro, porcentaje_por_hijo_registrado, porcentaje_rastreo_vehicular, porcentaje_tuercas_antirobo, porcentaje_un_siniestro, valor_derecho_emision, codigo_bitacora, idx)
VALUES
(CURRVAL('id_riesgo_cobertura_seq'), 10000, '2019-12-01', '2019-06-27', 0.0438, 0.0130, 0.0055, 0.5001, 0.5223, 0.3008, 0.1212, 0.1515, 0.3222, 0.1210, 12, null, 0),
(NEXTVAL('id_riesgo_cobertura_seq'), 10000, '2018-07-01', '2018-01-28', 0.0212, 0.4355, 0.0154, 0.2552, 0.1527, 0.2708, 0.1564, 0.1312, 0.3122, 0.1222, 12, null, 1),
(NEXTVAL('id_riesgo_cobertura_seq'), 10000, '2019-01-01', '2020-07-18', 0.0241, 0.3355, 0.3210, 0.1885, 0.1234, 0.1997, 0.4997, 0.0501, 0.0232, 0.2424, 12, null, 2),
(NEXTVAL('id_riesgo_cobertura_seq'), 10000, '2019-02-01', '2018-08-01', 0.0505, 0.1728, 0.1664, 0.3333, 0.5005, 0.4852, 0.1222, 0.1543, 0.0508, 0.2875, 12, null, 3),
(NEXTVAL('id_riesgo_cobertura_seq'), 10000, '2018-03-01', '2017-09-20', 0.0323, 0.4223, 0.1978, 0.0005, 0.4646, 0.5000, 0.0101, 0.0128, 0.1322, 0.3885, 12, null, 4),
(NEXTVAL('id_riesgo_cobertura_seq'), 10000, '2019-05-01', '2018-11-17', 0.0278, 0.5300, 0.1997, 0.0505, 0.4822, 0.4444, 0.0555, 0.1555, 0.1021, 0.4445, 12, null, 5),
(NEXTVAL('id_riesgo_cobertura_seq'), 10000, '2017-07-01', '2017-01-13', 0.0111, 0.1204, 0.3255, 0.1512, 0.1818, 0.2121, 0.1598, 0.4221, 0.1597, 0.4242, 12, null, 6),
(NEXTVAL('id_riesgo_cobertura_seq'), 10000,  null		, '2019-09-09', 0.0385, 0.0078, 0.1717, 0.4325, 0.2020, 0.3445, 0.3547, 0.4001, 0.1568, 0.4141, 12, null, 7),
(NEXTVAL('id_riesgo_cobertura_seq'), 10000, '2018-03-01', '2019-09-21', 0.0012, 0.1326, 0.2019, 0.0548, 0.1635, 0.1411, 0.1551, 0.1221, 0.2525, 0.4552, 12, null, 8),
(NEXTVAL('id_riesgo_cobertura_seq'), 10000, '2017-02-01', '2016-08-12', 0.0510, 0.5322, 0.5051, 0.1964, 0.1999, 0.4411, 0.3678, 0.1568, 0.4945, 0.2211, 12, null, 9);