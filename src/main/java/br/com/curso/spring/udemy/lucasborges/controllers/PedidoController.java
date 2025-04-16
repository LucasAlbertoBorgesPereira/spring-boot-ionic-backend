package br.com.curso.spring.udemy.lucasborges.controllers;

import br.com.curso.spring.udemy.lucasborges.domain.Pedido;
import br.com.curso.spring.udemy.lucasborges.dto.PedidoDTO;
import br.com.curso.spring.udemy.lucasborges.services.PedidoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.hibernate.Hibernate.initialize;

@RestController
@RequestMapping(value = "/v1")
public class PedidoController {
    final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping(path = "/pedidos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoDTO> find(@PathVariable Integer id) {
        Pedido obj = service.findById(id).orElseThrow(() -> new RuntimeException("Pedido not found"));
        initialize(obj.getCliente().getTelefones());
        PedidoDTO dto = new PedidoDTO(obj);
        return ResponseEntity.ok().body(dto);
    }
}