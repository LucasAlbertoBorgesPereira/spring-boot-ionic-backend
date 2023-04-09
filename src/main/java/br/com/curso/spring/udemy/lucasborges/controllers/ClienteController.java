package br.com.curso.spring.udemy.lucasborges.controllers;

import br.com.curso.spring.udemy.lucasborges.domain.Cliente;
import br.com.curso.spring.udemy.lucasborges.services.ClienteService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/v1")
public class ClienteController {
	final
	ClienteService service;

	public ClienteController(ClienteService service) {
		this.service = service;
	}

	@GetMapping(path = "/clientes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Cliente> find(@PathVariable Integer id) {

		Optional<Cliente> obj = Optional.ofNullable(service.findCatById(id));
		return obj;
	}

}
