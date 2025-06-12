package br.com.curso.spring.udemy.lucasborges.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serial;
import java.io.Serializable;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PagamentoComCartaoDTO.class, name = "pagamentoComCartao"),
        @JsonSubTypes.Type(value = PagamentoComBoletoDTO.class, name = "pagamentoComBoleto")
})
public class PagamentoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer estado;
}