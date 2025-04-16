package br.com.curso.spring.udemy.lucasborges.services;

import br.com.curso.spring.udemy.lucasborges.domain.Pedido;
import br.com.curso.spring.udemy.lucasborges.repositories.PedidoRepository;
import br.com.curso.spring.udemy.lucasborges.services.exceptions.DataIntegrityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Pedido> findById(Integer id) {
        Optional<Pedido> obj = pedidoRepository.findById(id);

        obj.orElseThrow(() -> new DataIntegrityException("Objeto não encontrado! Id:" + id + ", Tipo:" + Pedido.class.getName()));

        return obj;
    }
}
