package br.com.curso.spring.udemy.lucasborges.services;


import br.com.curso.spring.udemy.lucasborges.domain.Categoria;
import br.com.curso.spring.udemy.lucasborges.domain.Cliente;
import br.com.curso.spring.udemy.lucasborges.repositories.CategoriaRepository;
import br.com.curso.spring.udemy.lucasborges.services.exceptions.DataIntegrityException;
import br.com.curso.spring.udemy.lucasborges.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
                        new StringBuilder()
                                .append("Objeto não encontrado! Id:")
                                .append(id)
                                .append(", Tipo:")
                                .append(Cliente.class.getName())
                                .toString()
                )
        );
        return obj;

    }

    public Categoria insert(Categoria catObj) {
        return repository.save(catObj);
    }

    public Categoria update(Categoria id) {
        findCatById(id.getId());
        return repository.save(id);
    }

    public void delete(Integer id) {
        findCatById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos", e.getCause());
        }

    }
}
