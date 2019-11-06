insert into tipo_cobertura values('RESPONSABILIDAD_CIVIL', 'Descripcion uno', 'Responsabilidad civil');
insert into riesgo_tipo_cobertura values(NEXTVAL('id_riesgo_cobertura_seq'), null, '2019-10-23', 0.00487467, null, 'RESPONSABILIDAD_CIVIL', 0);

insert into tipo_cobertura values('RESP_CIVIL_ROBO_O_INCENDIO_TOTAL', 'Descripcion dos', 'Responsabilidad civil, robo o incendio total');
insert into riesgo_tipo_cobertura values(NEXTVAL('id_riesgo_cobertura_seq'), null, '2019-10-23', 0.01597880, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL', 0);

insert into tipo_cobertura values('TODO_TOTAL', 'Descripcion tres', 'Todo total');
insert into riesgo_tipo_cobertura values(NEXTVAL('id_riesgo_cobertura_seq'), null, '2019-10-23', 0.00830324, null, 'TODO_TOTAL', 0);

insert into tipo_cobertura values('TERCEROS_COMPLETOS', 'Descripcion cuatro', 'Terceros completos');
insert into riesgo_tipo_cobertura values(NEXTVAL('id_riesgo_cobertura_seq'), null,'2019-10-23',  0.00456913, null, 'TERCEROS_COMPLETOS', 0);

insert into tipo_cobertura values('TODO_RIESGO_CON_FRANQUICIA', 'Descripcion cinco', 'Todo riesgo con franquicia');
insert into riesgo_tipo_cobertura values(NEXTVAL('id_riesgo_cobertura_seq'), null, '2019-10-23', 0.01522073, null, 'TODO_RIESGO_CON_FRANQUICIA', 0);
