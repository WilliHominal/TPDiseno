package isi.dds.tp.enums;

public enum EnumEstadoCivil {
	SOLTERO("Soltera", "Soltero"),
	CASADO("Casada", "Casado"),
	VIUDO("Viuda", "Viudo"),
	DIVORCIADO("Divorciada", "Divorciado"),
	SEPARADO("Separada", "Separado"),
	EN_RELACION("En relación", "En relación");

	private String valueFem;
	private String valueMasc;

    private EnumEstadoCivil(String valueFem, String valueMasc) {
        this.valueFem = valueFem;
        this.valueMasc = valueMasc;
    }

    private String getValueFem() {
        return valueFem;
    }
    
    private String getValueMasc() {
        return valueMasc;
    }	

    @Override
    public String toString() {
        return this.getValueMasc();
    }
    
    public String toString(EnumSexo sexo) {
    	switch(sexo) {
			case MASCULINO:
			return this.getValueMasc();
		    
			case FEMENINO:
			return this.getValueFem();	
		    
			default:
			return this.getValueMasc();
    	}
    }

    public static EnumEstadoCivil getEnum(String value) {
    	for(EnumEstadoCivil v : values()) {
            if(v.getValueMasc().equalsIgnoreCase(value)) return v;
    		if(v.getValueFem().equalsIgnoreCase(value)) return v;
    	}
        return null;		
    }
	
}
