package br.com.curso.spring.udemy.lucasborges.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso.spring.udemy.lucasborges.domain.Categoria;
import br.com.curso.spring.udemy.lucasborges.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repository;

	public Categoria findCatById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj
				.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder().append("Objeto não encontrado! Id:")
						.append(id).append(", Tipo:").append(Categoria.class.getName()).toString(), id));

	}
}
