package br.com.curso.spring.udemy.lucasborges.repositories;

import br.com.curso.spring.udemy.lucasborges.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByNome(String nome);
}
