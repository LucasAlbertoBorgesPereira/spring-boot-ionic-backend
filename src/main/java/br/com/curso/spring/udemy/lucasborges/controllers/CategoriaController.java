package br.com.curso.spring.udemy.lucasborges.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso.spring.udemy.lucasborges.domain.Categoria;

@RestController
@RequestMapping(value = "/v1")
public class CategoriaController {

	@GetMapping(path = "/categorias", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Categoria> listar() {

		Categoria c1 = new Categoria(1, "Informática");
		Categoria c2 = new Categoria(2, "Escritório");

		return new ArrayList<>(Arrays.asList(c1, c2));
	}

}
