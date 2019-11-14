package isi.dds.tp.enums;

public enum EnumCondicionIVA {
	RESPONSABLE_INSCRIPTO("Responsable inscripto"), 
	RESPONSABLE_NO_INSCRIPTO("Responsable no inscripto"), 
	NO_RESPONSABLE("No responsable"), 
	SUJETO_EXENTO("Sujeto exento"), 
	CONSUMIDOR_FINAL("Consumidor final"), 
	RESPONSABLE_MONOTRIBUTO("Responsable monotributo"),
	SUJETO_NO_CATEGORIZADO("Sujeto no categorizado"), 
	IVA_LIBERADO("IVA liberado"), 
	MONOTRIBUTISTA_SOCIAL("Monotributista social");

	private String value;

    private EnumCondicionIVA(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }	

    @Override
    public String toString() {
        return this.getValue();
    }

    public static EnumCondicionIVA getEnum(String value) {
        for(EnumCondicionIVA v : values()) {
            if(v.getValue().equalsIgnoreCase(value)) return v;
        }
        return null;
    }
}




