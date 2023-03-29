package br.com.curso.spring.udemy.lucasborges.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso.spring.udemy.lucasborges.domain.Categoria;
import br.com.curso.spring.udemy.lucasborges.services.CategoriaService;

@RestController
@RequestMapping(value = "/v1")
public class CategoriaController {
	@Autowired
	CategoriaService service;

	@GetMapping(path = "/categorias/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria obj = service.findCatById(id);
	

		return ResponseEntity.ok(obj);
	}

}
