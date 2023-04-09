package br.com.curso.spring.udemy.lucasborges.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso.spring.udemy.lucasborges.domain.Cliente;
import br.com.curso.spring.udemy.lucasborges.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;

	public Cliente findCatById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj
				.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder().append("Objeto não encontrado! Id:")
						.append(id).append(", Tipo:").append(Cliente.class.getName()).toString(), id));

	}
}
