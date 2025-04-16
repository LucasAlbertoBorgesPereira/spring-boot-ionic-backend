package br.com.curso.spring.udemy.lucasborges.domain.enums;

import lombok.Getter;

@Getter
public enum EstadoPagamento {

    PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

    EstadoPagamento(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    private final int cod;
    private final String descricao;

    public static EstadoPagamento toEnum(Integer cod) {

        if (null == cod) {
            return null;
        }
        for (EstadoPagamento x : EstadoPagamento.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }

        }
        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
