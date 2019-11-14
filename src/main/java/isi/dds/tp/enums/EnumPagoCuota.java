package isi.dds.tp.enums;

public enum EnumPagoCuota {
	EN_TERMINO("En término"),
	EN_MORA("En mora"),
	ADELANTADA("Adelantada");
	
	private String value;

    private EnumPagoCuota(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }	

    @Override
    public String toString() {
        return this.getValue();
    }

    public static EnumPagoCuota getEnum(String value) {
        for(EnumPagoCuota v : values()) {
            if(v.getValue().equalsIgnoreCase(value)) return v;
        }
        return null;
    }
}
