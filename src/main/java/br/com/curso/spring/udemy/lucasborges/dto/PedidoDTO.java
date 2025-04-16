package br.com.curso.spring.udemy.lucasborges.dto;

import br.com.curso.spring.udemy.lucasborges.domain.Endereco;
import br.com.curso.spring.udemy.lucasborges.domain.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String data;
    private String cliente;
    private Endereco enderecoDeEntrega;
    private String pagamento;
    private String itens;
    private Double valorTotal;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.data = pedido.getInstante().toString();
        this.cliente = pedido.getCliente().getNome();
        this.enderecoDeEntrega = pedido.getEnderecoDeEntrega();
        this.pagamento = pedido.getPagamento().toString();
        this.valorTotal = pedido.getValorTotal();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PedidoDTO pedidoDTO = (PedidoDTO) o;
        return Objects.equals(id, pedidoDTO.id) && Objects.equals(data, pedidoDTO.data) && Objects.equals(cliente, pedidoDTO.cliente) && Objects.equals(enderecoDeEntrega, pedidoDTO.enderecoDeEntrega) && Objects.equals(pagamento, pedidoDTO.pagamento) && Objects.equals(itens, pedidoDTO.itens) && Objects.equals(valorTotal, pedidoDTO.valorTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, cliente, enderecoDeEntrega, pagamento, itens, valorTotal);
    }
}
