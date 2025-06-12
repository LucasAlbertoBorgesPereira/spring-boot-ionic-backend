package br.com.curso.spring.udemy.lucasborges.controllers.mapper;

import br.com.curso.spring.udemy.lucasborges.domain.Pagamento;
import br.com.curso.spring.udemy.lucasborges.domain.PagamentoComBoleto;
import br.com.curso.spring.udemy.lucasborges.domain.PagamentoComCartao;
import br.com.curso.spring.udemy.lucasborges.dto.PagamentoComBoletoDTO;
import br.com.curso.spring.udemy.lucasborges.dto.PagamentoComCartaoDTO;
import br.com.curso.spring.udemy.lucasborges.dto.PagamentoDTO;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {
    Pagamento toEntity(PagamentoDTO dto);

    default Pagamento toEntity(PagamentoComCartaoDTO dto) {
        if (dto == null) {
            return null;
        }
        PagamentoComCartao pagamento = new PagamentoComCartao();
        pagamento.setNumeroDeParcelas(dto.getNumeroDeParcelas());
        return pagamento;
    }

    default PagamentoDTO toDto(Pagamento pagamento) {
        if (pagamento instanceof PagamentoComCartao c) {
            return toDto(c);
        } else if (pagamento instanceof PagamentoComBoleto b) {
            return toDto(b);
        }
        return null;
    }
    PagamentoComCartaoDTO toDto(PagamentoComCartao entidade);

    PagamentoComBoletoDTO toDto(PagamentoComBoleto entidade);
}