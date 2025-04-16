package br.com.curso.spring.udemy.lucasborges.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


@Entity
public class Cidade implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@ManyToOne
	@JoinColumn(name="estado_id")
	private Estado estado;

	public Cidade() {
	}

	public Cidade(Integer id, String nome, Estado estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Cidade cidade = (Cidade) o;
		return Objects.equals(id, cidade.id) && Objects.equals(nome, cidade.nome) && Objects.equals(estado, cidade.estado);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, estado);
	}
}
