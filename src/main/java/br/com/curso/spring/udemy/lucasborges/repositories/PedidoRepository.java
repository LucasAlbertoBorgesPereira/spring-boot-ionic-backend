package br.com.curso.spring.udemy.lucasborges.repositories;


import br.com.curso.spring.udemy.lucasborges.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
