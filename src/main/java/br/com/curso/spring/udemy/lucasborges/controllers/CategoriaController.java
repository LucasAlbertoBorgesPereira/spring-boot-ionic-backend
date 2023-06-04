package br.com.curso.spring.udemy.lucasborges.controllers;

import br.com.curso.spring.udemy.lucasborges.domain.Categoria;
import br.com.curso.spring.udemy.lucasborges.services.CategoriaService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/v1")
public class CategoriaController {
    final
    CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping(path = "/categorias/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Categoria find(@PathVariable Integer id) {
        Optional<Categoria> obj = service.findCatById(id);
        return obj.orElse(null);
    }

}
