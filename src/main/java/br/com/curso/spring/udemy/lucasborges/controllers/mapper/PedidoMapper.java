package br.com.curso.spring.udemy.lucasborges.controllers.mapper;

import br.com.curso.spring.udemy.lucasborges.domain.Pedido;
import br.com.curso.spring.udemy.lucasborges.dto.PedidoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface PedidoMapper {
    @Mapping(target = "instante", ignore = true)
    @Mapping(source = "enderecoDTO", target = "enderecoDeEntrega")
    Pedido toEntity(PedidoDTO dto);

    @Mapping(source = "enderecoDeEntrega", target = "enderecoDTO")
    PedidoDTO toDto(Pedido pedido);
}