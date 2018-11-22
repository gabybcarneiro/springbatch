package br.com.cardif.etl.grafica.util;

import java.util.List;

/**
 * Enum para definição das situações S-Sim e N-Não
 */
public enum TipoSimNao implements AbstractEnum{

	SIM("S", "Sim"),
	NAO("N", "Não");

	private final String value;
	private final String desc;

	TipoSimNao(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public static TipoSimNao fromValue(String value) {
		return EnumUtil.getEnumFromValue(TipoSimNao.class, value);
	}

	public static List<TipoSimNao> listEnums(){
		return EnumUtil.getListEnums(TipoSimNao.class);
	}

	public static TipoSimNao fromValue(Boolean value) {
		TipoSimNao retorno = null;
		if (value != null) {
			if (value) {
				retorno = SIM;
			}
			else {
				retorno = NAO;
			}
		}
		return retorno;
	}

	public static String getDescByValue(String value) {
		return TipoSimNao.fromValue(value).getDesc();
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
