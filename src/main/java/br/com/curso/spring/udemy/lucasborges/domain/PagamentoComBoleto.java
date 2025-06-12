package br.com.curso.spring.udemy.lucasborges.domain;

import br.com.curso.spring.udemy.lucasborges.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Date dataVencimento;
    private Date dataPagamento;
    private String numeroDoBoleto;
    private BigDecimal valor;

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento, String numeroDoBoleto, BigDecimal valor) {
        super(id, estado, pedido);
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
        this.numeroDoBoleto = numeroDoBoleto;
        this.valor = valor;
    }

    public PagamentoComBoleto() {
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getNumeroDoBoleto() {
        return numeroDoBoleto;
    }

    public void setNumeroDoBoleto(String numeroDoBoleto) {
        this.numeroDoBoleto = numeroDoBoleto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PagamentoComBoleto that = (PagamentoComBoleto) o;
        return Objects.equals(dataVencimento, that.dataVencimento) && Objects.equals(dataPagamento, that.dataPagamento) && Objects.equals(numeroDoBoleto, that.numeroDoBoleto) && Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dataVencimento, dataPagamento, numeroDoBoleto, valor);
    }

}
