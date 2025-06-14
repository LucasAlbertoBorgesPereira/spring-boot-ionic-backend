package br.com.curso.spring.udemy.lucasborges.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO implements Serializable {
    @JsonProperty("cliente")
    private ClienteDTO cliente;
    @JsonProperty("enderecoDeEntrega")
    private EnderecoDTO enderecoDTO;
    @JsonProperty("pagamento")
    private PagamentoDTO pagamentoDto;
    private List<ItemPedidoDTO> itens;
}