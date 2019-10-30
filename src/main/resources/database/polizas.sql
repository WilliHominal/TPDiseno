INSERT INTO poliza
	(numero_poliza, chasis, es_propuesta, esta_emitida, estado, fecha_emision, fin_vigencia, forma_pago, guarda_garage, inicio_vigencia, km_realizados_por_anio,
	 motor, numeros_siniestros_ultimo_anios, patente, porcentaje_valor_asegurado, suma_asegurada, tiene_alarma, tiene_rastreo_vehicular, tiene_tuercas_antirobo,
	 valor_bonificacion_pago_semestral, valor_derecho_emision, valor_descuento, valor_descuento_por_unidad_adicional, valor_interes_genero, valor_premio, valor_prima,
	 valor_riesgo_modelo, valor_riesgo_cobertura, valor_riesgo_ciudad, anio_modelo, ciudad_id_ciudad, numero_cliente, parametros_poliza, solicitud_poliza,
	 tipo_cobertura, idx)
VALUES
(0001000001200, '8ACEC19V4H1102477', false, true,  'VIGENTE',    '2019-05-30', '2019-12-01', 'MENSUAL',   true,  '2019-06-01', 30000, 'A52WVC0010338', 'DOS',        'AG759LH', 60, 101928,  true,  true,  true,  0,     12, 0,     0,     0, 12,    0,     0,  100, 1585,  100, 20094, 5464838321, 113, null, 'RESPONSABILIDAD_CIVIL',            0),
(0001000012212, '8CADM5A35D6098261', true,  true,  'VIGENTE',    '2019-06-26', '2019-12-27', 'MENSUAL',   false, '2019-06-27', 20000, 'F8CV120123345', 'UNO',        'AB123CD', 40, 45800,   true,  false, false, 0,     12, 0,     0,     0, 20012, 20000, 1,  101, 10897, 102, 10782, 5440924746, 112, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL', 1),
(0003000015701, '8EDCG56632C001709', true,  true,  'NO_VIGENTE', '2019-10-09', '2018-07-28', 'SEMESTRAL', true,  '2018-01-28', 30000, 'F798110000001', 'NINGUNO',    'AE000AA', 20, 58120,   false, true,  true,  10000, 12, 20000, 10000, 0, 10012, 10000, 2,  101, 7839,  104, 13840, 5476486543, 111, null, 'TODO_TOTAL',                       2),
(0003000156705, '8CEGD21A760152075', true,  false, 'GENERADA',   '2019-10-15', '2020-06-23', 'SEMESTRAL', true,  '2019-12-23', 40000, 'CX921M5003427', 'MAS_DE_DOS', 'NV415AS', 20, 184010,  false, false, false, 8000,  12, 10000, 0,     0, 30012, 30000, 3,  102, 5503,  106, 16176, 5434192256, 110, null, 'TODO_TOTAL',                       3),
(0012008411300, '8DCUKREC8EG228494', false, true,  'SUSPENDIDA', '2019-07-17', '2020-01-18', 'MENSUAL',   false, '2019-07-18', 40000, 'AK00536S11432', 'DOS',        'XX117DD', 10, 28200,   false, false, true,  0,     12, 0,     0,     0, 20012, 20000, 4,  103, 8304,  108, 13375, 5456659755, 104, null, 'RESPONSABILIDAD_CIVIL',            4),
(0012000032006, '8ETEC19K6R1512631', true,  false, 'GENERADA',   '2019-10-02', '2020-05-05', 'MENSUAL',   false, '2019-11-05', 30000, 'CB750E3055641', 'UNO',        'AB108BZ', 40, 35712,   true,  true,  false, 0,     12, 0,     0,     0, 10012, 10000, 5,  103, 7094,  110, 14585, 5485950363, 105, null, 'TERCEROS_COMPLETOS',               5),
(0005000045206, '8AVDE3F70DE301060', false, true,  'VIGENTE',    '2019-07-25', '2020-02-01', 'MENSUAL',   true,  '2019-08-01', 10000, 'AS21497003787', 'NINGUNO',    'BZ781AF', 30, 134020,  false, false, false, 0,     12, 0,     0,     0, 30012, 30000, 6,  104, 14130, 111, 7549,  5405329643, 106, null, 'TODO_RIESGO_CON_FRANQUICIA',       6),
(0005000738500, '8BYFNDEY2BS647670', false, true,  'NO_VIGENTE', '2017-09-19', '2018-03-20', 'SEMESTRAL', true,  '2017-09-20', 10000, 'CB750K1000234', 'MAS_DE_DOS', 'HG155ST', 20, 34500,   false, true,  true,  8000,  12, 10000, 0,     0, 10012, 10000, 7,  103, 624,   109, 21055, 5427641313, 107, null, 'TERCEROS_COMPLETOS',               7),
(0017001234507, '8FRGN23U37H112375', true,  true,  'VIGENTE',    '2019-10-16', '2020-05-17', 'MENSUAL',   false, '2019-11-17', 30000, '7A25C5AE6015E', 'DOS',        'XT085RY', 10, 199800,  false, true,  false, 0,     12, 0,     0,     0, 20012, 20000, 8,  102, 4980,  107, 16699, 5431483258, 108, null, 'RESPONSABILIDAD_CIVIL',            8),
(0017001115709, '8A4WS52M9W1404036', true,  false, 'GENERADA',   '2019-10-24', '2020-07-13', 'MENSUAL',   true,  '2020-01-13', 20000, 'D2AECA3F30012', 'UNO',        'YT777YT', 30, 149800,  true,  true,  true,  0,     12, 0,     0,     0, 12,    0,     9,  102, 12901, 105, 8778,  5492365145, 109, null, 'TODO_TOTAL',                       9),
(0002000012011, '8C4AL3AP0DN460573', false, true,  'VIGENTE',    '2019-09-08', '2020-03-09', 'SEMESTRAL', true,  '2019-09-09', 20000, 'AK00536S12001', 'NINGUNO',    'NM550WY', 20, 51222,   false, false, false, 15000, 12, 25000, 10000, 0, 30012, 30000, 10, 101, 7290,  103, 14389, 5456421329, 105, null, 'TERCEROS_COMPLETOS',               10),
(0007000151800, '8B4CJASBXFB083040', false, true,  'SUSPENDIDA', '2019-09-04', '2020-03-05', 'SEMESTRAL', false, '2019-09-05', 10000, 'CA77E01022564', 'MAS_DE_DOS', 'VT477VS', 20, 58120,   false, true,  false, 10000, 12, 10000, 0,     0, 20012, 20000, 11, 101, 13526, 104, 8153,  5415287846, 113, null, 'RESPONSABILIDAD_CIVIL',            11),
(0004000988700, '8DTPX04506KC11857', true,  true,  'NO_VIGENTE', '2017-08-20', '2018-03-21', 'MENSUAL',   true,  '2017-09-21', 30000, 'XE19264000302', 'DOS',        'ST738UK', 10, 142000,  false, false, true,  0,     12, 0,     0,     0, 20012, 20000, 12, 100, 12817, 101, 8862,  5443281451, 111, null, 'RESPONSABILIDAD_CIVIL',            12),
(0009000151501, '8ECJTDFE6B8118998', true,  false, 'GENERADA',   '2019-10-25', '2020-07-17', 'MENSUAL',   true,  '2020-01-17', 20000, 'CB750E1000712', 'UNO',        'AR978GN', 20, 149800,  false, true,  false, 0,     12, 0,     0,     0, 20012, 20000, 13, 102, 16556, 105, 5123,  5474248578, 106, null, 'TODO_RIESGO_CON_FRANQUICIA',       13),
(0013000012302, '8CAFP31381R124234', false, true,  'SUSPENDIDA', '2019-08-11', '2020-02-12', 'SEMESTRAL', false, '2019-08-12', 10000, 'DOZC6015C1005', 'NINGUNO',    'PI465CA', 30, 35712,   true,  true,  true,  12000, 12, 10000, 0,     0, 12,    0,     5,  103, 18409, 110, 3270,  5433275540, 108, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL', 14);