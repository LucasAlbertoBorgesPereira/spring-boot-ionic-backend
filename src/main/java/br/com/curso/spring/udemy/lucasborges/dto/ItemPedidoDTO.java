package br.com.curso.spring.udemy.lucasborges.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Double desconto;
    private Integer quantidade;
    private Double preco;
    private ProdutoDto produto;
    private Double subTotal;
}
