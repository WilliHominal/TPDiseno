package isi.dds.tp.enums;

public enum EnumFormaPago {
	MENSUAL("Mensual"),
	SEMESTRAL("Semestral");
	
	private String value;

    private EnumFormaPago(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }	

    @Override
    public String toString() {
        return this.getValue();
    }

    public static EnumFormaPago getEnum(String value) {
        for(EnumFormaPago v : values()) {
            if(v.getValue().equalsIgnoreCase(value)) return v;
        }
        return null;
    }
}
