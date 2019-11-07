INSERT INTO parametros_poliza
(codigo_parametros_poliza, inicio_vigencia, fin_vigencia, porcentaje_tuercas_antirobo, porcentaje_guarda_en_garage, porcentaje_alarma, porcentaje_rastreo_vehicular, porcentaje_ajuste_km, porcentaje_ningun_siniestro,
porcentaje_un_siniestro, porcentaje_dos_siniestro, porcentaje_mayor_a_dos_siniestro, porcentaje_por_hijo_registrado, descuento_unidad_adicional, codigo_bitacora, valor_derecho_emision)
VALUES
(NEXTVAL('codigo_parametros_poliza_seq'), '2019-09-09', null, 0.00398228, 0.00490365, 0.00346532, 0.00344266, 0.00140566, 0.00102278, 0.00225994, 0.00430689, 0.00557732, 0.00529591, 0.08678593, null, 364);