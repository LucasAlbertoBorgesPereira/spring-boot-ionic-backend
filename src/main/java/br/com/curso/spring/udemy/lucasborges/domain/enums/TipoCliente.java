package br.com.curso.spring.udemy.lucasborges.domain.enums;

import lombok.Getter;

@Getter
public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Juridica");

	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	private int cod;
	private String descricao;

	public static TipoCliente toEnum(Integer cod) {

		if (null == cod) {
			return null;
		}
		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}

		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
