package isi.dds.tp.gestor;

import isi.dds.tp.enums.*;

public class GestorEnum {

	private static GestorEnum instanciaGestor = null;
	 
    private GestorEnum() {

    }

    public static GestorEnum get() {
        if (instanciaGestor == null){
        	instanciaGestor = new GestorEnum();
        }    
        return instanciaGestor;
    }
    
    public EnumSexo getEnumSexo(String sexo) {
    	if(sexo.equals("Masculino")) {
    		return EnumSexo.MASCULINO;
    	}
    	
    	if(sexo.equals("Femenino")) {
    		return EnumSexo.FEMENINO;
    	}
    	return null;
    }
    
    public String getStringSexo(EnumSexo sexo) {
    	if(sexo.equals(EnumSexo.MASCULINO)) {
    		return "Masculino";
    	}
    	
    	if(sexo.equals(EnumSexo.FEMENINO)) {
    		return "Femenino";
    	}
    	return null;
    }
    
    public EnumEstadoCivil getEnumEstadoCivil(String estadoCivil) {
    	if(estadoCivil.equals("Soltero") || estadoCivil.equals("Soltera")) {
    		return EnumEstadoCivil.SOLTERO;
    	}
    	if(estadoCivil.equals("Casado") || estadoCivil.equals("Casada")) {
    		return EnumEstadoCivil.CASADO;
    	}
    	if(estadoCivil.equals("Viudo") || estadoCivil.equals("Viuda")) {
    		return EnumEstadoCivil.VIUDO;
    	}
    	if(estadoCivil.equals("Divorciado") || estadoCivil.equals("Divorciada")) {
    		return EnumEstadoCivil.DIVORCIADO;
    	}
    	if(estadoCivil.equals("Separado") || estadoCivil.equals("Separada")) {
    		return EnumEstadoCivil.SEPARADO;
    	}
    	if(estadoCivil.equals("En relación")) {
    		return EnumEstadoCivil.EN_RELACION;
    	}
    	return null;    	
    }
    
    public String getStringEstadoCivil(EnumEstadoCivil estadoCivil) {
    	if(estadoCivil.equals(EnumEstadoCivil.SOLTERO)) {
    		return "Soltero";
    	}
    	if(estadoCivil.equals(EnumEstadoCivil.CASADO)) {
    		return "Casado";
    	}
    	if(estadoCivil.equals(EnumEstadoCivil.VIUDO)) {
    		return "Viudo";
    	}
    	if(estadoCivil.equals(EnumEstadoCivil.DIVORCIADO)) {
    		return "Divorciado";
    	}
    	if(estadoCivil.equals(EnumEstadoCivil.SEPARADO)) {
    		return "Separado";
    	}
    	if(estadoCivil.equals(EnumEstadoCivil.EN_RELACION)) {
    		return "En relación";
    	}
    	return null;    	
    }
    
    public String getStringEstadoCivilFem(EnumEstadoCivil estadoCivil) {
    	if(estadoCivil.equals(EnumEstadoCivil.SOLTERO)) {
    		return "Soltera";
    	}
    	if(estadoCivil.equals(EnumEstadoCivil.CASADO)) {
    		return "Casada";
    	}
    	if(estadoCivil.equals(EnumEstadoCivil.VIUDO)) {
    		return "Viuda";
    	}
    	if(estadoCivil.equals(EnumEstadoCivil.DIVORCIADO)) {
    		return "Divorciada";
    	}
    	if(estadoCivil.equals(EnumEstadoCivil.SEPARADO)) {
    		return "Separada";
    	}
    	if(estadoCivil.equals(EnumEstadoCivil.EN_RELACION)) {
    		return "En relación";
    	}
    	return null;    	
    }
    
    public EnumSiniestros getEnumSiniestros(String siniestro) {
    	if(siniestro.equals("Ninguno")) {
    		return EnumSiniestros.NINGUNO;
    	}
    	if(siniestro.equals("Uno")) {
    		return EnumSiniestros.UNO;
    	}
    	if(siniestro.equals("Dos")) {
    		return EnumSiniestros.DOS;
    	}
    	if(siniestro.equals("Más de dos")) {
    		return EnumSiniestros.MAS_DE_DOS;
    	}
    	return null;    	
    }
    
    public String getStringSiniestros(EnumSiniestros siniestro) {
    	if(siniestro.equals(EnumSiniestros.NINGUNO)) {
    		return "Ninguno";
    	}
    	if(siniestro.equals(EnumSiniestros.UNO)) {
    		return "Uno";
    	}
    	if(siniestro.equals(EnumSiniestros.DOS)) {
    		return "Dos";
    	}
    	if(siniestro.equals(EnumSiniestros.MAS_DE_DOS)) {
    		return "Más de dos";
    	}
    	return null;
    }
    
    public EnumTipoDocumento getEnumTipoDocumento(String tipoDocumento) {
    	if(tipoDocumento.equals("DNI")) {
    		return EnumTipoDocumento.DNI;
    	}
    	if(tipoDocumento.equals("Pasaporte")) {
    		return EnumTipoDocumento.PASAPORTE;
    	}
    	if(tipoDocumento.equals("Libreta civil")) {
    		return EnumTipoDocumento.LIBRETA_CIVIL;
    	}
    	if(tipoDocumento.equals("Libreta de enrolamiento")) {
    		return EnumTipoDocumento.LIBRETA_DE_ENROLAMIENTO;
    	}
    	return null;    	
    }
    
    public String getStringTipoDocumento(EnumTipoDocumento tipoDocumento) {
    	if(tipoDocumento.equals(EnumTipoDocumento.DNI)) {
    		return "DNI";
    	}
    	if(tipoDocumento.equals(EnumTipoDocumento.PASAPORTE)) {
    		return "Pasaporte";
    	}
    	if(tipoDocumento.equals(EnumTipoDocumento.LIBRETA_CIVIL)) {
    		return "Libreta civil";
    	}
    	if(tipoDocumento.equals(EnumTipoDocumento.LIBRETA_DE_ENROLAMIENTO)) {
    		return "Libreta de enrolamiento";
    	}
    	return null;
    }
    
    public EnumCondicionIVA getEnumCondicionIva(String condicionIva) {
    	if(condicionIva.equals("Responsable inscripto")) {
    		return EnumCondicionIVA.RESPONSABLE_INSCRIPTO;
    	}
    	if(condicionIva.equals("Responsable no inscripto")) {
    		return EnumCondicionIVA.RESPONSABLE_NO_INSCRIPTO;
    	}
    	if(condicionIva.equals("No responsable")) {
    		return EnumCondicionIVA.NO_RESPONSABLE;
    	}
    	if(condicionIva.equals("Sujeto exento")) {
    		return EnumCondicionIVA.SUJETO_EXENTO;
    	}
    	if(condicionIva.equals("Consumidor final")) {
    		return EnumCondicionIVA.CONSUMIDOR_FINAL;
    	}
    	if(condicionIva.equals("Responsable monotributo")) {
    		return EnumCondicionIVA.RESPONSABLE_MONOTRIBUTO;
    	}
    	if(condicionIva.equals("Sujeto no categorizado")) {
    		return EnumCondicionIVA.SUJETO_NO_CATEGORIZADO;
    	}
    	if(condicionIva.equals("IVA liberado")) {
    		return EnumCondicionIVA.IVA_LIBERADO;
    	}
    	if(condicionIva.equals("Monotributista social")) {
    		return EnumCondicionIVA.MONOTRIBUTISTA_SOCIAL;
    	}
    	return null;    	
    }
    
    public String getStringCondicionIva(EnumCondicionIVA tipoDocumento) {
    	if(tipoDocumento.equals(EnumCondicionIVA.RESPONSABLE_INSCRIPTO)) {
    		return "Responsable inscripto";
    	}
    	if(tipoDocumento.equals(EnumCondicionIVA.RESPONSABLE_NO_INSCRIPTO)) {
    		return "Responsable no inscripto";
    	}
    	if(tipoDocumento.equals(EnumCondicionIVA.NO_RESPONSABLE)) {
    		return "No responsable";
    	}
    	if(tipoDocumento.equals(EnumCondicionIVA.SUJETO_EXENTO)) {
    		return "Sujeto exento";
    	}
    	if(tipoDocumento.equals(EnumCondicionIVA.CONSUMIDOR_FINAL)) {
    		return "Consumidor final";
    	}
    	if(tipoDocumento.equals(EnumCondicionIVA.RESPONSABLE_MONOTRIBUTO)) {
    		return "Responsable monotributo";
    	}
    	if(tipoDocumento.equals(EnumCondicionIVA.SUJETO_NO_CATEGORIZADO)) {
    		return "Sujeto no categorizado";
    	}
    	if(tipoDocumento.equals(EnumCondicionIVA.IVA_LIBERADO)) {
    		return "IVA liberado";
    	}
    	if(tipoDocumento.equals(EnumCondicionIVA.MONOTRIBUTISTA_SOCIAL)) {
    		return "Monotributista social";
    	}
    	return null;
    }
}
