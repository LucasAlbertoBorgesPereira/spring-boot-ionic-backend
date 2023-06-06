package br.com.curso.spring.udemy.lucasborges.services;

import br.com.curso.spring.udemy.lucasborges.domain.Pedido;
import br.com.curso.spring.udemy.lucasborges.repositories.PedidoRepository;
import br.com.curso.spring.udemy.lucasborges.services.exceptions.DataIntegrityException;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Transactional
    public Optional<Pedido> findById(Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        obj.orElseThrow(() ->
                new DataIntegrityException(new StringBuilder()
                        .append("Objeto não encontrado! Id:")
                        .append(id)
                        .append(", Tipo:")
                        .append(Pedido.class.getName())
                        .toString()
                )
        );

        return obj;

    }
}
