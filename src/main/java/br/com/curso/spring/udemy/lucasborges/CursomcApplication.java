package br.com.curso.spring.udemy.lucasborges;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.curso.spring.udemy.lucasborges.domain.Categoria;
import br.com.curso.spring.udemy.lucasborges.domain.Cidade;
import br.com.curso.spring.udemy.lucasborges.domain.Estado;
import br.com.curso.spring.udemy.lucasborges.domain.Produto;
import br.com.curso.spring.udemy.lucasborges.repositories.CategoriaRepository;
import br.com.curso.spring.udemy.lucasborges.repositories.CidadeRepository;
import br.com.curso.spring.udemy.lucasborges.repositories.EstadoRepository;
import br.com.curso.spring.udemy.lucasborges.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	@Autowired
	CategoriaRepository repository;
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	EstadoRepository estadoRepository;
	@Autowired
	CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria("Informatica");
		Categoria cat2 = new Categoria("Escritorio");

		Produto p1 = new Produto("Computador", BigDecimal.valueOf(2000.00));
		Produto p2 = new Produto("Impressora", BigDecimal.valueOf(800.00));
		Produto p3 = new Produto("Mouse", BigDecimal.valueOf(80.00));

		Estado est1 = new Estado("Minas Gerais");
		Estado est2 = new Estado("São Paulo");
		
		Cidade c1 =new Cidade(null, "Uberlândia",est1);
		Cidade c2 =new Cidade(null, "São Paulo",est2);
		Cidade c3 =new Cidade(null, "Campinas",est2);

	
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		repository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

	}

}
