package br.com.curso.spring.udemy.lucasborges.controllers;

import br.com.curso.spring.udemy.lucasborges.controllers.mapper.PagamentoMapper;
import br.com.curso.spring.udemy.lucasborges.controllers.mapper.PedidoMapper;
import br.com.curso.spring.udemy.lucasborges.domain.Pedido;
import br.com.curso.spring.udemy.lucasborges.dto.PagamentoComBoletoDTO;
import br.com.curso.spring.udemy.lucasborges.dto.PagamentoComCartaoDTO;
import br.com.curso.spring.udemy.lucasborges.dto.PedidoDTO;
import br.com.curso.spring.udemy.lucasborges.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1")
public class PedidoController {
    private final PedidoService service;
    private final PedidoMapper mapper;
    private final PagamentoMapper pagamentoMapper;

    public PedidoController(PedidoService service, PedidoMapper mapper, PagamentoMapper pagamentoMapper) {
        this.service = service;
        this.mapper = mapper;
        this.pagamentoMapper = pagamentoMapper;
    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<PedidoDTO> find(@PathVariable Integer id) {
        Pedido pedido = service.findById(id).orElseThrow(() -> new RuntimeException("Pedido not found"));
        PedidoDTO dto = mapper.toDto(pedido);
        dto.setPagamentoDto(pedido.getPagamento() != null ? pagamentoMapper.toDto(pedido.getPagamento()) : null);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/pedidos")
    public ResponseEntity<Void> criarNovoPedido(@Valid @RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = mapper.toEntity(pedidoDTO);
        if (pedidoDTO.getPagamentoDto() instanceof PagamentoComCartaoDTO cartaoDTO) {
            pedido.setPagamento(pagamentoMapper.toEntity(cartaoDTO));
        } else if (pedidoDTO.getPagamentoDto() instanceof PagamentoComBoletoDTO boletoDTO) {
            pedido.setPagamento(pagamentoMapper.toEntity(boletoDTO));
        }
        pedido = service.insert(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}