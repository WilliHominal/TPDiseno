INSERT INTO parametros_poliza
(codigo_parametros_poliza, inicio_vigencia, fin_vigencia, porcentaje_tuercas_antirobo, porcentaje_guarda_en_garage, porcentaje_alarma, porcentaje_rastreo_vehicular, porcentaje_ajuste_km, porcentaje_ningun_siniestro,
porcentaje_un_siniestro, porcentaje_dos_siniestro, porcentaje_mayor_a_dos_siniestro, porcentaje_por_hijo_registrado, descuento_unidad_adicional, codigo_bitacora, valor_derecho_emision)
VALUES
(CURRVAL('id_riesgo_cobertura_seq'), '2016-06-27', '2016-11-27', 0.0520, 0.0521, 0.0518, 0.0355, 0.0137, 0.0059, 0.0334, 0.0419, 0.0546, 0.0361, 0.8054, null, 291),
(NEXTVAL('id_riesgo_cobertura_seq'), '2016-11-28', '2017-03-17', 0.0478, 0.0516, 0.0358, 0.0287, 0.0139, 0.0109, 0.0319, 0.0452, 0.0626, 0.0406, 0.8270, null, 202),
(NEXTVAL('id_riesgo_cobertura_seq'), '2017-03-18', '2017-09-30', 0.0425, 0.0509, 0.0330, 0.0327, 0.0177, 0.0085, 0.0259, 0.0452, 0.0615, 0.0578, 0.8243, null, 378),
(NEXTVAL('id_riesgo_cobertura_seq'), '2017-10-01', '2017-12-09', 0.0507, 0.0619, 0.0459, 0.0439, 0.0188, 0.0059, 0.0299, 0.0355, 0.0650, 0.0592, 0.8221, null, 243),
(NEXTVAL('id_riesgo_cobertura_seq'), '2017-12-10', '2018-04-25', 0.0521, 0.0507, 0.0335, 0.0395, 0.0209, 0.0131, 0.0359, 0.0473, 0.0678, 0.0364, 0.8175, null, 326),
(NEXTVAL('id_riesgo_cobertura_seq'), '2018-04-26', '2018-08-12', 0.0539, 0.0654, 0.0473, 0.0294, 0.0226, 0.0136, 0.0242, 0.0365, 0.0588, 0.0440, 0.8107, null, 131),
(NEXTVAL('id_riesgo_cobertura_seq'), '2018-08-13', '2019-02-01', 0.0501, 0.0515, 0.0343, 0.0296, 0.0126, 0.0088, 0.0317, 0.0457, 0.0536, 0.0588, 0.8385, null, 105),
(NEXTVAL('id_riesgo_cobertura_seq'), '2019-02-03', '2019-06-20', 0.0452, 0.0541, 0.0480, 0.0355, 0.0150, 0.0087, 0.0227, 0.0498, 0.0561, 0.0545, 0.8304, null, 281),
(NEXTVAL('id_riesgo_cobertura_seq'), '2019-06-21', '2019-09-10', 0.0503, 0.0563, 0.0401, 0.0357, 0.0222, 0.0064, 0.0330, 0.0432, 0.0557, 0.0554, 0.8226, null, 218),
(NEXTVAL('id_riesgo_cobertura_seq'), '2019-09-09', null 	   , 0.0395, 0.0625, 0.0433, 0.0459, 0.0208, 0.0090, 0.0339, 0.0382, 0.0532, 0.0506, 0.8135, null, 215);
