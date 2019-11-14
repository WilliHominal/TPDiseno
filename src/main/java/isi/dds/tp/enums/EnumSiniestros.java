package isi.dds.tp.enums;

public enum EnumSiniestros {
	NINGUNO("Ninguno"),
	UNO("Uno"),
	DOS("Dos"),
	MAS_DE_DOS("Más de dos");
	
    private String value;

    private EnumSiniestros(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }	

    @Override
    public String toString() {
        return this.getValue();
    }

    public static EnumSiniestros getEnum(String value) {
        for(EnumSiniestros v : values()) {
            if(v.getValue().equalsIgnoreCase(value)) return v;
        }
        return null;
    }
}
