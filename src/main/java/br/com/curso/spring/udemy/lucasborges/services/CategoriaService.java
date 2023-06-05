package br.com.curso.spring.udemy.lucasborges.services;


import br.com.curso.spring.udemy.lucasborges.domain.Categoria;
import br.com.curso.spring.udemy.lucasborges.domain.Cliente;
import br.com.curso.spring.udemy.lucasborges.repositories.CategoriaRepository;
import org.hibernate.ObjectNotFoundException;
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
						new StringBuilder().append("Objeto não encontrado! Id:").append(id).append(", Tipo:").append(Cliente.class.getName()).toString(), id));
		return obj;

	}

    public Categoria insert(Categoria catObj) {
		return repository.save(catObj);
    }

	public Categoria update(Categoria id) {
		findCatById(id.getId());
		return repository.save(id);
	}
}
