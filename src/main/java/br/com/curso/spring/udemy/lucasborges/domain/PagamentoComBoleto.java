package br.com.curso.spring.udemy.lucasborges.domain;

import java.util.Date;

import br.com.curso.spring.udemy.lucasborges.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class PagamentoComBoleto extends Pagamento {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyy")
	private Date dataVencimento;

	@JsonFormat(pattern = "dd/MM/yyy")
	private Date dataPagamento;

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date datavencimento,
			Date dataPAgamento) {
		super(id, estado.getCod(), pedido);
		this.dataPagamento = dataPAgamento;
		this.dataVencimento = datavencimento;
	}

}
