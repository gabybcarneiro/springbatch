package br.com.cardif.etl.grafica.util;

import java.util.Arrays;
import java.util.List;

public class EnumUtil {

	/**
	 * Método para obter a Lista de constantes Enums, a partir de uma Classe Enum
	 *
	 * @param enumm Classe Enum que contém os Enums (Propriedades) a serem listados
	 * @return Lista de Enums
	 */
	public static <T extends AbstractEnum> List<T> getListEnums(Class<T> enumm) {
		try {
			Class<T> c = (Class<T>)Class.forName(enumm.getName());
			return Arrays.asList(c.getEnumConstants());
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Método para obter um Array de constantes Enums, a partir de uma Classe Enum
	 *
	 * @param enumm Classe Enum que contém os Enums (Propriedades) a serem listados
	 * @return Lista de Enums
	 */
	public static <T extends AbstractEnum> T[] getArrayEnums(Class<T> enumm) {
		try {
			Class<T> c = (Class<T>)Class.forName(enumm.getName());
			return c.getEnumConstants();
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Método para localizar um Enum, à partir do seu valor
	 *
	 * @param enumm Classe Enum ao qual irá localizar o Enum (Propriedade)
	 * @param value Valor do Enum
	 * @return Enum de acordo com a classe e valor informado
	 */
	public static <T extends AbstractEnum> T getEnumFromValue(Class<T> enumm, Object value) {
		List<T> enums = getListEnums(enumm);
		for (T e : enums) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * Obtém o valor do enum caso o mesmo não for null
	 *
	 * @param enumn
	 * @param <T>
	 * @return
	 */
	public static <T extends AbstractEnum> Object getValueOrNull(T enumn) {
		if (enumn != null) {
			return enumn.getValue();
		}
		else {
			return null;
		}
	}


}
