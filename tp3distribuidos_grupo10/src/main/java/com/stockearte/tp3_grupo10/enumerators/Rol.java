package com.stockearte.tp3_grupo10.enumerators;

public enum Rol {
	CASA_CENTRAL("Casa Central"),
	TIENDA("Tienda");

	private final String value;
	
	Rol(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static Rol fromValue(String value) {
        for (Rol rol : Rol.values()) {
            if (rol.getValue().equalsIgnoreCase(value)) {
                return rol;
            }
        }
        throw new IllegalArgumentException("No existe el Rol: " + value);
    }
	
}
