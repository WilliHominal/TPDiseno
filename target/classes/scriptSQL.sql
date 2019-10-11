#MARCA | columnas = id_marca, nombre
#MODELO | columnas = id_modelo, nombre, id_marca, idx (indice para la obtencion en orden)
#ANIO MODELO | id, anio, suma_asegurada, id_modelo, idx
#RIESGO MODELO | columnas = id, fin_vigencia, inicio_vigencia, ultimo, valor_porcentual, codigo_bitacora, id_modelo, idx
insert into marca values(1,'Volskwagen');
	insert into modelo values(1, 'Passat', 1, 0);
		insert into anio_modelo values(1, 2018, 86231, 1, 0);
		insert into anio_modelo values(2, 2019, 92000, 1, 1);
			#insert into riesgo_modelo values();
	insert into modelo values(2, 'Golf', 1, 1);
		insert into anio_modelo values(3, 2017, 54120, 2, 0);
		insert into anio_modelo values(4, 2019, 67210, 2, 1);
			#insert into riesgo_modelo values();
	insert into modelo values(3, 'Scirocco', 1, 2);
		insert into anio_modelo values(5, 2018, 117800, 3, 0);
		insert into anio_modelo values(6, 2019, 129630, 3, 1);
			#insert into riesgo_modelo values();
insert into  marca values(2,'Fiat');
	insert into modelo values(4, 'Argo',  2, 0);
		insert into anio_modelo values(7, 2014, 45800, 4, 0);
		insert into anio_modelo values(8, 2015, 51222, 4, 1);
		insert into anio_modelo values(9, 2016, 58120, 4, 2);
			#insert into riesgo_modelo values();
	insert into modelo values(5, 'Cronos', 2, 1);
		insert into anio_modelo values(10, 2017, 134010, 5, 0);
		insert into anio_modelo values(11, 2018, 149800, 5, 1);
			#insert into riesgo_modelo values();
insert into marca values(3,'Ford');
	insert into modelo values(6, 'Focus', 3, 0);
		insert into anio_modelo values(12, 2017, 64761, 6, 0);
		insert into anio_modelo values(13, 2018, 67120, 6, 1);
		insert into anio_modelo values(14, 2019, 76900, 6, 2);
			#insert into riesgo_modelo values();
	insert into modelo values(7, 'Fiesta', 3, 1);
		insert into anio_modelo values(15, 2013, 44890, 7, 0);
			#insert into riesgo_modelo values();
	insert into modelo values(8, 'Ka', 3, 2);
		insert into anio_modelo values(16, 2015, 45901, 8, 0);
			#insert into riesgo_modelo values();
		insert into anio_modelo values(17, 2016, 46491, 8, 1);
insert into marca values(4,'Reanult');
	insert into modelo values(9, 'Kwid', 4, 0);
		insert into anio_modelo values(18, 2015, 69120, 9, 0);
		insert into anio_modelo values(19, 2016, 76000, 9, 1);
		insert into anio_modelo values(20, 2018, 91222, 9, 2);
		insert into anio_modelo values(21, 2019, 97120, 9, 3);
			#insert into riesgo_modelo values();
	insert into modelo values(10, 'Duster', 4, 1);
		insert into anio_modelo values(22, 2019, 178290, 10, 0);
			#insert into riesgo_modelo values();

#TIPOS COBERTURA | columnas = enumTipoCobertura, nombre, descripcion
#RIESGOS TIPO COBERTURA | columnas = id, fin_vigencia, inicio_vigencia, ultimo, valor_porcentual, codigo_bitacora, tipo_cobertura, idx
insert into tipo_cobertura values('RESPONSABILIDAD_CIVIL','Responsabilidad civil', 'Descripcion uno');
	insert into riesgo_tipo_cobertura values(1, '29-08-2019', '21-05-2019', false, 0.2, null, 'RESPONSABILIDAD_CIVIL', 0);
	insert into riesgo_tipo_cobertura values(2, null, '30-08-2019', true, 0.23, null, 'RESPONSABILIDAD_CIVIL', 1);
insert into tipo_cobertura values('RESP_CIVIL_ROBO_O_INCENDIO_TOTAL', 'Responsabilidad civil, robo o incendio total', 'Descripcion dos');
	insert into riesgo_tipo_cobertura values(3, '14-06-2019', '01-04-2018', false, 0.41, null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL', 0);
	insert into riesgo_tipo_cobertura values(4, null, '15-06-2019', true, 0.39,  null, 'RESP_CIVIL_ROBO_O_INCENDIO_TOTAL', 1);
insert into tipo_cobertura values('TODO_TOTAL', 'Todo total', 'Descripcion tres');
	insert into riesgo_tipo_cobertura values(5, '29-09-2019', '11-07-2018', false, 0.72, null, 'TODO_TOTAL', 0);
	insert into riesgo_tipo_cobertura values(6, null, '30-09-2019', true, 0.65, null, 'TODO_TOTAL', 1);
insert into tipo_cobertura values('TERCEROS_COMPLETOS', 'Terceros completos', 'Descripcion cuatro');
	insert into riesgo_tipo_cobertura values(7, null,'05-02-2018',  true, 0.51, null, 'TERCEROS_COMPLETOS', 0);
insert into tipo_cobertura values('TODO_RIESGO_CON_FRANQUICIA', 'Todo riesgo con franquiia', 'Descripcion cinco');	
	insert into riesgo_tipo_cobertura values(8, '02-03-2019', '28-12-2018', false, 0.48, null, 'TODO_RIESGO_CON_FRANQUICIA', 0);
	insert into riesgo_tipo_cobertura values(9, null, '03-03-2019', true, 0.44, null, 'TODO_RIESGO_CON_FRANQUICIA', 1);