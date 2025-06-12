package br.com.curso.spring.udemy.lucasborges.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartaoDTO extends PagamentoDTO implements Serializable  {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer numeroDeParcelas;
}
