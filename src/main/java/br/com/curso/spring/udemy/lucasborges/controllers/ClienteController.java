package br.com.curso.spring.udemy.lucasborges.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso.spring.udemy.lucasborges.domain.Cliente;
import br.com.curso.spring.udemy.lucasborges.services.ClienteService;

@RestController
@RequestMapping(value = "/v1")
public class ClienteController {
	@Autowired
	ClienteService service;

	@GetMapping(path = "/clientes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {

		Cliente obj = service.findCatById(id);

		return ResponseEntity.ok(obj);
	}

}
