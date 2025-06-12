package br.com.curso.spring.udemy.lucasborges.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoletoDTO extends PagamentoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Date dataVencimento;
    private Date dataPagamento;
    private String numeroDoBoleto;
    private BigDecimal valor;
}
