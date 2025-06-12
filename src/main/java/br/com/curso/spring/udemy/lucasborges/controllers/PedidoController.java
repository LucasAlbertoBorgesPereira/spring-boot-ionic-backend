package br.com.curso.spring.udemy.lucasborges.controllers;

import br.com.curso.spring.udemy.lucasborges.controllers.mapper.PagamentoMapper;
import br.com.curso.spring.udemy.lucasborges.controllers.mapper.PedidoMapper;
import br.com.curso.spring.udemy.lucasborges.domain.Pedido;
import br.com.curso.spring.udemy.lucasborges.dto.PagamentoComBoletoDTO;
import br.com.curso.spring.udemy.lucasborges.dto.PagamentoComCartaoDTO;
import br.com.curso.spring.udemy.lucasborges.dto.PedidoDTO;
import br.com.curso.spring.udemy.lucasborges.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.hibernate.Hibernate.initialize;

@RestController
@RequestMapping(value = "/v1")
public class PedidoController {
    private final PedidoService service;
    private final PedidoMapper mapper;
    private final PagamentoMapper pagamentoMapper;

    public PedidoController(PedidoService service, PedidoMapper mapper, PagamentoMapper pagamentoMapper) {
        this.service = service;
        this.mapper = mapper;
        this.pagamentoMapper = pagamentoMapper;
    }

    @GetMapping(path = "/pedidos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoDTO> find(@PathVariable Integer id) {
        Pedido obj = service.findById(id).orElseThrow(() -> new RuntimeException("Pedido not found"));
        initialize(obj.getCliente().getTelefones());
        PedidoDTO dto = mapper.toDto(obj);
        if (obj.getPagamento() != null) {
            dto.setPagamentoDto(pagamentoMapper.toDto(obj.getPagamento()));
        } else {
            dto.setPagamentoDto(null);
        }
        return ResponseEntity.ok(dto);
    }


    @PostMapping(path = "/pedidos",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> criarNovoPedido(@Valid @RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = mapper.toEntity(pedidoDTO);
        if (pedidoDTO.getPagamentoDto() instanceof PagamentoComCartaoDTO dto) {
            pedido.setPagamento(pagamentoMapper.toEntity(dto));
        } else if (pedidoDTO.getPagamentoDto() instanceof PagamentoComBoletoDTO dto) {
            pedido.setPagamento(pagamentoMapper.toEntity(dto));
        }
        pedido = service.insert(pedido);

        return ResponseEntity.created(criarUriNovoPedido(pedido)).build();
    }

    private URI criarUriNovoPedido(Pedido pedido) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedido.getId())
                .toUri();
    }
}