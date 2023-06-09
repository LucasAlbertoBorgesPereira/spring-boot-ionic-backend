package br.com.curso.spring.udemy.lucasborges.dto;

import br.com.curso.spring.udemy.lucasborges.domain.Categoria;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 a 80 caracteres")
    private String nome;

}
