package br.com.curso.spring.udemy.lucasborges.repositories;

import br.com.curso.spring.udemy.lucasborges.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
