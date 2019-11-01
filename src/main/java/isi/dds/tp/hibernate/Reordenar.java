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
			


			String content = "", lineaLeida = "";
			
			String tuercasAntiRobo = "tuercasAntiRobo", guardaEnGarage = "guardaEnGarage", tieneAlarma = "tieneAlarma", tieneRastreo = "tieneRastreo",
					ajusteKm = "ajusteKm", ceroSiniestros = "ceroSiniestros", unSiniestro = "unSiniestro", dosSiniestros = "dosSiniestros", 
					muchosSiniestros = "muchosSiniestros", 	ajusteHijo = "ajusteHijo", descuentoUniti = "descuentoUniti", 	derechoEmision = "derechoEmision";
			
			 DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
	            separadoresPersonalizados.setDecimalSeparator('.');
	            DecimalFormat formato = new DecimalFormat("0.00000000", separadoresPersonalizados);
	            
	        try {
	            final BufferedReader reader = new BufferedReader(
	                new FileReader("src/main/resources/database/parametrosPoliza.sql")
	            );
	            
	            Double ftuercasAntiRobo = 0.0038d, fguardaEnGarage = 0.0048d, ftieneAlarma = 0.0032d, ftieneRastreo = 0.0028d, fajusteKm = 0.0012d,
	            		fceroSiniestros = 0.0005d, funSiniestro = 0.0022d,
						fdosSiniestros = 0.00352d, fmuchosSiniestros = 0.00532d, fajusteHijo = 0.003d, fdescuentoUniti = 0.08d;
	            
	            
	            
	            while((lineaLeida = reader.readLine())!= null) 
	            {
	            	            	
	            	if (lineaLeida.contains(tuercasAntiRobo)) {
	            		Random r = new Random();
	            		Double randomNum = ftuercasAntiRobo + r.nextDouble() * 0.001;
	            		lineaLeida = lineaLeida.replaceAll(tuercasAntiRobo, formato.format(randomNum));
	                }
	            	
	            	if (lineaLeida.contains(guardaEnGarage)) {	
	            		Random r = new Random();
	            		Double randomNum = fguardaEnGarage + r.nextDouble() * ( 0.001 );
	            		lineaLeida = lineaLeida.replaceAll(guardaEnGarage, formato.format(randomNum));
	                }
	            	
	            	if (lineaLeida.contains(tieneAlarma)) {
	            		Random r = new Random();
	            		Double randomNum = ftieneAlarma + r.nextDouble() * (0.001);
	            		lineaLeida = lineaLeida.replaceAll(tieneAlarma, formato.format(randomNum));
	                }
	            	
	            	if (lineaLeida.contains(tieneRastreo)) {
	            		Random r = new Random();
	            		Double randomNum = ftieneRastreo + r.nextDouble() * 0.001;
	            		lineaLeida = lineaLeida.replaceAll(tieneRastreo, formato.format(randomNum));
	                }
	            	
	            	if (lineaLeida.contains(ajusteKm)) {	
	            		Random r = new Random();
	            		Double randomNum = fajusteKm + r.nextDouble() * 0.0012;
	            		lineaLeida = lineaLeida.replaceAll(ajusteKm, formato.format(randomNum));
	                }	            	
	            	
	            	if (lineaLeida.contains(ceroSiniestros)) {
	            		Random r = new Random();
	            		Double randomNum = fceroSiniestros + r.nextDouble() * 0.001;
	            		lineaLeida = lineaLeida.replaceAll(ceroSiniestros, formato.format(randomNum));
	                }
	            	
	            	if (lineaLeida.contains(unSiniestro)) {
	            		Random r = new Random();
	            		Double randomNum = funSiniestro + r.nextDouble() * 0.0015;
	            		lineaLeida = lineaLeida.replaceAll(unSiniestro, formato.format(randomNum));
	                }
	            	
	            	if (lineaLeida.contains(dosSiniestros)) {	
	            		Random r = new Random();
	            		Double randomNum = fdosSiniestros + r.nextDouble() * 0.0015;
	            		lineaLeida = lineaLeida.replaceAll(dosSiniestros, formato.format(randomNum));
	                }
	            	
	            	if (lineaLeida.contains(muchosSiniestros)) {
	            		Random r = new Random();
	            		Double randomNum = fmuchosSiniestros + r.nextDouble() * 0.0015;
	            		lineaLeida = lineaLeida.replaceAll(muchosSiniestros, formato.format(randomNum));
	                }
	            	
	            	if (lineaLeida.contains(ajusteHijo)) {
	            		Random r = new Random();
	            		Double randomNum = fajusteHijo + r.nextDouble() * 0.003;
	            		lineaLeida = lineaLeida.replaceAll(ajusteHijo, formato.format(randomNum));
	                }
	            	
	            	if (lineaLeida.contains(descuentoUniti)) {
	            		Random r = new Random();
	            		Double randomNum = fdescuentoUniti + r.nextDouble() * 0.04;
	            		lineaLeida = lineaLeida.replaceAll(descuentoUniti, formato.format(randomNum));
	                }
	            	
	            	if (lineaLeida.contains(derechoEmision)) {
	            		Random r = new Random();
	            		Integer randomNum = 100 + r.nextInt(300);	
	            		lineaLeida = lineaLeida.replaceAll(derechoEmision, randomNum.toString());
	                }
	            	
	            	/*if (lineaLeida.contains(riesgo)) {
	            		Random r = new Random();
	            		Double randomNum = 0.05 + r.nextDouble() * ( 0.60 - 0.05 );	
	            		lineaLeida = lineaLeida.replaceAll(riesgo, formato.format(randomNum));
	            		marca1++;
	               */


	                content += lineaLeida + "\r\n";
	               
	            }
	            reader.close();
	            
	            
	            String newContent = content;

	            FileWriter writer = new FileWriter("src/main/resources/database/parametrosPoliza2.sql");

	            writer.write(newContent);

	            writer.close();
	        } catch (FileNotFoundException e) {e.printStackTrace();
	        } catch (IOException           e) {e.printStackTrace();}
	 }

}
