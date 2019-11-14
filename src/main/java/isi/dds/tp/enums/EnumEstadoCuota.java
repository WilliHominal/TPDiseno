package isi.dds.tp.enums;

public enum EnumEstadoCuota {
	PAGO("Pago"),
	IMPAGO("Impago");
	
	private String value;

    private EnumEstadoCuota(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }	

    @Override
    public String toString() {
        return this.getValue();
    }

    public static EnumEstadoCuota getEnum(String value) {
        for(EnumEstadoCuota v : values()) {
            if(v.getValue().equalsIgnoreCase(value)) return v;
        }
        return null;
    }
}
