package br.com.curso.spring.udemy.lucasborges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.curso.spring.udemy.lucasborges.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
