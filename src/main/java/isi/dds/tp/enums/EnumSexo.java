package isi.dds.tp.enums;

public enum EnumSexo {
	MASCULINO("Masculino"),
	FEMENINO("Femenino");

	private String value;

    private EnumSexo(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }	

    @Override
    public String toString() {
        return this.getValue();
    }

    public static EnumSexo getEnum(String value) {
        for(EnumSexo v : values()) {
            if(v.getValue().equalsIgnoreCase(value)) return v;
        }
        return null;
    }
}
