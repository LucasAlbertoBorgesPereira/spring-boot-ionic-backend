package br.com.curso.spring.udemy.lucasborges.dto;

import br.com.curso.spring.udemy.lucasborges.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private BigDecimal preco;

    ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }
}