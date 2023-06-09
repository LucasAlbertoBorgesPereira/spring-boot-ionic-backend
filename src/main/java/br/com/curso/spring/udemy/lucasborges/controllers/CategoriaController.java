package br.com.curso.spring.udemy.lucasborges.controllers;

import br.com.curso.spring.udemy.lucasborges.domain.Categoria;
import br.com.curso.spring.udemy.lucasborges.dto.CategoriaDTO;
import br.com.curso.spring.udemy.lucasborges.services.CategoriaService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/categorias")
public class CategoriaController {
    final
    CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        var obj = service.findAll();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(path = "/page", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CategoriaDTO>>
    findAllPagination(@RequestParam(value = "page") @Nullable Integer page,
                      @RequestParam(value = "lines") @Nullable Integer linesPerPage,
                      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                      @RequestParam(value = "direction", defaultValue = "ASC") String direction) {


        return ResponseEntity.ok().body(service.findPage(page, linesPerPage, orderBy, direction));
    }

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Optional<Categoria> obj = service.findCatById(id);
        return ResponseEntity.ok().body(obj.get());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO catObjDTO) {
        Categoria obj = service.fromDTO(catObjDTO);
        obj = service.insert(obj);
       
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(obj.getId())
                        .toUri())
                .build();

    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id) {
        Categoria categoria = service.fromDTO(categoriaDTO);
        categoria.setId(id);

        service.update(categoria);

        return ResponseEntity
                .noContent()
                .build();

    }

    @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity
                .noContent()
                .build();

    }
}
