package br.com.curso.spring.udemy.lucasborges.domain;

import br.com.curso.spring.udemy.lucasborges.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataVencimento;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataPagamento;

	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
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

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		PagamentoComBoleto that = (PagamentoComBoleto) o;
		return Objects.equals(dataVencimento, that.dataVencimento) && Objects.equals(dataPagamento, that.dataPagamento);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), dataVencimento, dataPagamento);
	}
}
