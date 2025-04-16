package br.com.curso.spring.udemy.lucasborges.controllers;

import br.com.curso.spring.udemy.lucasborges.domain.Categoria;
import br.com.curso.spring.udemy.lucasborges.dto.CategoriaDTO;
import br.com.curso.spring.udemy.lucasborges.services.CategoriaService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/categorias")
public class CategoriaController {
    final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        var obj = service.findAll();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(path = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CategoriaDTO>> findAllPagination(
            @RequestParam(value = "page") @Nullable Integer page,
            @RequestParam(value = "lines") @Nullable Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok().body(service.findPage(page, linesPerPage, orderBy, direction));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaDTO> find(@PathVariable Integer id) {
        Categoria obj = service.findCatById(id).orElseThrow(() -> new RuntimeException("Categoria not found"));
        CategoriaDTO dto = new CategoriaDTO(obj); // Converte para DTO
        return ResponseEntity.ok().body(dto);
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

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}