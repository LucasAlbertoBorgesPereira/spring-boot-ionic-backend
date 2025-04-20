package br.com.curso.spring.udemy.lucasborges.dto;

import br.com.curso.spring.udemy.lucasborges.domain.ItemPedido;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ItemPedidoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Double desconto;
    private Integer quantidade;
    private Double preco;
    private ProdutoDto produto;
    private Double subTotal;


    public ItemPedidoDTO(ItemPedido itemPedido) {
        this.desconto = itemPedido.getDesconto();
        this.quantidade = itemPedido.getQuantidade();
        this.preco = itemPedido.getPreco();
        this.produto = new ProdutoDto(itemPedido.getProduto());
        this.subTotal = itemPedido.getSubTotal();
    }
}
