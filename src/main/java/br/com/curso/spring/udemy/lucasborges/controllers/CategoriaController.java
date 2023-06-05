package br.com.curso.spring.udemy.lucasborges.controllers;

import br.com.curso.spring.udemy.lucasborges.domain.Categoria;
import br.com.curso.spring.udemy.lucasborges.services.CategoriaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Optional<Categoria> obj = service.findCatById(id);
        return ResponseEntity.ok().body(obj.get());
    }

    @PostMapping(path = "/categorias", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert(@RequestBody Categoria catObj) {
        Categoria obj = service.insert(catObj);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(obj.getId())
                        .toUri())
                .build();

    }

    @PutMapping (path = "/categorias/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Integer id) {
        categoria.setId(id);
        Categoria obj = service.update(categoria);
        return ResponseEntity
                .noContent()
                .build();

    }
    @DeleteMapping  (path = "/categorias/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity
                .noContent()
                .build();

    }
}
