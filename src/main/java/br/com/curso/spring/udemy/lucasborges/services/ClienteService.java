package br.com.curso.spring.udemy.lucasborges.services;

import br.com.curso.spring.udemy.lucasborges.domain.Cliente;
import br.com.curso.spring.udemy.lucasborges.repositories.ClienteRepository;
import br.com.curso.spring.udemy.lucasborges.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public Cliente findCatById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" +
                        id + ", Tipo:" +
                        Cliente.class.getName()
                )
        );
    }
}
