package br.com.curso.spring.udemy.lucasborges.dto;

import br.com.curso.spring.udemy.lucasborges.domain.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
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
    private EnderecoDTO enderecoDeEntrega;
    private String pagamento;
    private List<ItemPedidoDTO> itens;
    private Double valorTotal;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.data = pedido.getInstante() != null ? pedido.getInstante().toString() : null;
        this.cliente = pedido.getCliente() != null ? pedido.getCliente().getNome() : null;
        this.enderecoDeEntrega = new EnderecoDTO(pedido.getEnderecoDeEntrega());
        this.pagamento = pedido.getPagamento() != null && pedido.getPagamento().getEstado() != null
                ? pedido.getPagamento().getEstado().getDescricao()
                : null;
        this.itens = pedido.getItens() != null
                ? pedido.getItens().stream()
                .map(ItemPedidoDTO::new)
                .toList()
                : List.of();
        this.valorTotal = pedido.getValorTotal();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PedidoDTO pedidoDTO = (PedidoDTO) o;
        return Objects.equals(getId(), pedidoDTO.getId()) && Objects.equals(getData(), pedidoDTO.getData()) && Objects.equals(getCliente(), pedidoDTO.getCliente()) && Objects.equals(getEnderecoDeEntrega(), pedidoDTO.getEnderecoDeEntrega()) && Objects.equals(getPagamento(), pedidoDTO.getPagamento()) && Objects.equals(getItens(), pedidoDTO.getItens()) && Objects.equals(getValorTotal(), pedidoDTO.getValorTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getData(), getCliente(), getEnderecoDeEntrega(), getPagamento(), getItens(), getValorTotal());
    }
}