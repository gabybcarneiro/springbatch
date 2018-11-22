package br.com.cardif.etl.grafica.util;

import java.util.ArrayList;
import java.util.List;

public enum TipoBase implements AbstractEnum {

	VIDA("LIFEVIDA", 1),
	GARANTIA("LIFE_GAR", 2),
	LUIZASEG("LIFELSEG", 3),
	BVP("LIFE_BVP", 4);
	
	public String nomeBase;
	public Integer tipo;

	private TipoBase(String nomeBase, Integer tipo) {
		this.nomeBase = nomeBase;
		this.tipo = tipo;
	}

	public String getNomeBase() {
		return nomeBase;
	}

	public void setNomeBase(String nomeBase) {
		this.nomeBase = nomeBase;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public static List<TipoBase> listEnums(){
		return EnumUtil.getListEnums(TipoBase.class);
	}
	
	public static List<String> listValues(){
		List<String> values = new ArrayList<String>();
		for(TipoBase tp : listEnums()) {
			values.add(tp.getNomeBase());
		}
		return values;
	}

	@Override
	public String getValue() {
		return null;
	}

}
