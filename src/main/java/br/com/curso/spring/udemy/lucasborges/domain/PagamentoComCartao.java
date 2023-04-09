package br.com.curso.spring.udemy.lucasborges.domain;

import br.com.curso.spring.udemy.lucasborges.domain.enums.EstadoPagamento;
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
public class PagamentoComCartao extends Pagamento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDaParcelas) {
		super(id, estado.getCod(), pedido);
		this.numeroDeParcelas = numeroDaParcelas;

	}

	private Integer numeroDeParcelas;

}
