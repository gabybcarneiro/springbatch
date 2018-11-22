package br.com.cardif.etl.grafica.util;

import java.util.List;

/**
 * Enum para definição das situações S-Sim e N-Não
 */
public enum TipoCombustivel implements AbstractEnum{

	GASOLINA("G", "GASOLINA"),
	ALCOOL("A", "ALCOOL"),
	FLEX("F", "FLEX");

	private final String value;
	private final String desc;

	TipoCombustivel(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public static TipoCombustivel fromValue(String value) {
		return EnumUtil.getEnumFromValue(TipoCombustivel.class, value);
	}

	public static List<TipoCombustivel> listEnums(){
		return EnumUtil.getListEnums(TipoCombustivel.class);
	}

	public static String getDescByValue(String value) {
		return TipoCombustivel.fromValue(value).getDesc();
	}

	public String getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return value;
	}
}
