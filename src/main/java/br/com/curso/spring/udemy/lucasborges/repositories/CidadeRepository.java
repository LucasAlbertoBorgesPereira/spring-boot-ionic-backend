package br.com.curso.spring.udemy.lucasborges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.curso.spring.udemy.lucasborges.domain.Cidade;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
