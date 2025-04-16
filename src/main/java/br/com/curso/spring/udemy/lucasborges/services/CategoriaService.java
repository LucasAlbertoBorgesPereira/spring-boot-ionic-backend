package br.com.curso.spring.udemy.lucasborges.services;


import br.com.curso.spring.udemy.lucasborges.domain.Categoria;
import br.com.curso.spring.udemy.lucasborges.domain.Cliente;
import br.com.curso.spring.udemy.lucasborges.dto.CategoriaDTO;
import br.com.curso.spring.udemy.lucasborges.repositories.CategoriaRepository;
import br.com.curso.spring.udemy.lucasborges.services.exceptions.DataIntegrityException;
import br.com.curso.spring.udemy.lucasborges.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    final
    CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Optional<Categoria> findCatById(Integer id) {
        Optional<Categoria> obj = repository.findById(id);
        obj.orElseThrow(() ->
                new ObjectNotFoundException(
                        "Objeto não encontrado! Id:" +
                                id +
                                ", Tipo:" +
                                Cliente.class.getName()
                )
        );
        return obj;
    }

    public List<CategoriaDTO> findAll() {
        List<Categoria> categoriaList = repository.findAll();
        return categoriaList.stream().map(CategoriaDTO::new).toList();
    }

    @Transactional
    public Categoria insert(Categoria catObj) {
        return repository.save(catObj);
    }

    @Transactional
    public void update(Categoria id) {
        findCatById(id.getId());
        repository.save(id);
    }

    @Transactional
    public void delete(Integer id) {
        findCatById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos", e.getCause());
        }

    }

    public Page<CategoriaDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page - 1, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<Categoria> categoriaPages = repository.findAll(pageRequest);
        return categoriaPages.map(CategoriaDTO::new);
    }

    public Categoria fromDTO(CategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }
}
