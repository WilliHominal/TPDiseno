package isi.dds.tp.hibernate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Random;

public class Reordenar {

	 public static void main(String[] args) {
			Integer marca1=0, marca2=0, marca3 = 0, marca4=0, marca5=0, marca6 = 0, marca7=0, marca8=0, marca9 = 0, marca10=0, marca11=0;

	        try {
	            final BufferedReader reader = new BufferedReader(
	                new FileReader("src/main/resources/database/parametrosVehiculo.sql")
	            );

	            String content = "", lineaLeida = "", anio1 = "ANIO1", anio2 = "ANIO2",
	            		anio3 = "ANIO3";

	            
	            while((lineaLeida = reader.readLine())!= null) 
	            {
	            	
	            	if (lineaLeida.contains(anio1)) {
	            		Random r = new Random();
	            		Integer randomNum = 2017 + r.nextInt(3);	
	            		lineaLeida = lineaLeida.replaceAll(anio1, randomNum.toString());
	                }
	            	
	            	if (lineaLeida.contains(anio2)) {
	            		Random r = new Random();
	            		Integer randomNum = 2014 + r.nextInt(3);	
	            		lineaLeida = lineaLeida.replaceAll(anio2, randomNum.toString());
	                }
	            	
	            	if (lineaLeida.contains(anio3)) {
	            		Random r = new Random();
	            		Integer randomNum = 2011 + r.nextInt(3);	
	            		lineaLeida = lineaLeida.replaceAll(anio3, randomNum.toString());
	                }
	            	
	            	/*if (lineaLeida.contains(riesgo)) {
	            		Random r = new Random();
	            		Double randomNum = 0.05 + r.nextDouble() * ( 0.60 - 0.05 );	
	            		lineaLeida = lineaLeida.replaceAll(riesgo, formato.format(randomNum));
	            		marca1++;
	                }
	            		            
	            	if (lineaLeida.contains(marquita1)) {
	            		lineaLeida = lineaLeida.replaceAll(marquita1, 101+", "+marca1);
	            		marca1++;
	                }
	            	
	            	if (lineaLeida.contains(marquita2)) {
	            		lineaLeida = lineaLeida.replaceAll(marquita2, 102+", "+marca2);
	            		marca2++;
	                }
	            	
	            	if (lineaLeida.contains(marquita3)) {
	            		lineaLeida = lineaLeida.replaceAll(marquita3, 103+", "+marca3);
	            		marca3++;
	                }
	            	
	            	if (lineaLeida.contains(marquita4)) {
	            		lineaLeida = lineaLeida.replaceAll(marquita4, 104+", "+marca4);
	            		marca4++;
	                }
	            	
	            	if (lineaLeida.contains(marquita5)) {
	            		lineaLeida = lineaLeida.replaceAll(marquita5, 105+", "+marca5);
	            		marca5++;
	                }
	            	
	            	if (lineaLeida.contains(marquita5)) {
	            		lineaLeida = lineaLeida.replaceAll(marquita5, 101+", "+marca5);
	            		marca5++;
	                }
	            	
	            	if (lineaLeida.contains(marquita6)) {
	            		lineaLeida = lineaLeida.replaceAll(marquita6, 106+", "+marca6);
	            		marca6++;
	                }
	            	
	            	
	            	if (lineaLeida.contains(marquita7)) {
	            		lineaLeida = lineaLeida.replaceAll(marquita7, 107+", "+marca7);
	            		marca7++;
	                }
	            	
	            	if (lineaLeida.contains(marquita8)) {
	            		lineaLeida = lineaLeida.replaceAll(marquita8, 108+", "+marca8);
	            		marca8++;
	                }
	            	
	            	if (lineaLeida.contains(marquita9)) {
	            		lineaLeida = lineaLeida.replaceAll(marquita9, 109+", "+marca9);
	            		marca9++;
	                }
	            	
	            	if (lineaLeida.contains(marquita10)) {
	            		lineaLeida = lineaLeida.replaceAll(marquita10, 110+", "+marca10);
	            		marca10++;
	                }
	            	
	            	if (lineaLeida.contains(marquita11)) {
	            		lineaLeida = lineaLeida.replaceAll(marquita11, 111+", "+marca11);
	            		marca11++;
	                }*/


	                content += lineaLeida + "\r\n";
	               
	            }
	            reader.close();
/*
	            DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
	            separadoresPersonalizados.setDecimalSeparator('.');
	            DecimalFormat formato = new DecimalFormat("0.0000", separadoresPersonalizados);
	            
	            
	            while(contCiudades>0){
	            	content += "INSERT INTO riesgo_ciudad (id, inicio_vigencia, valor_porcentual, ultimo, id_ciudad, idx) VALUES" + "\r\n";
	            	Boolean condicion  = true;
	            	while(condicion) {
	            		contCiudades--;
	            		contRiesgos++;

	            		Random r = new Random();
	            		
	            		Double randomNum = 0.05 + r.nextDouble() * ( 0.60 - 0.05 );
	            		
	            		if(contRiesgos<500&&contCiudades>0) {
	            			condicion = true;
	            			content += "("+contRiesgosTotal+", '2019-10-23', "+formato.format(randomNum)+", true, "+contCiudades+", 0),"+"\r\n"; 
	            		}
	            		else {
	            			content += "("+contRiesgosTotal+", '2019-10-23', "+formato.format(randomNum)+", true, "+contCiudades+", 0);"+"\r\n"; 
	            			condicion = false;
	            		}
	            		contRiesgosTotal++;
	            	}
	            	contRiesgos = 0 ;
	            	//contCiudades--;
	            }*/
	            
	            
	            String newContent = content;

	            FileWriter writer = new FileWriter("src/main/resources/database/parametrosVehiculo.sql");

	            writer.write(newContent);

	            writer.close();
	        } catch (FileNotFoundException e) {e.printStackTrace();
	        } catch (IOException           e) {e.printStackTrace();}
	 }

}
