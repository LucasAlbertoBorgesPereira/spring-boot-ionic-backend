package br.com.curso.spring.udemy.lucasborges;

import br.com.curso.spring.udemy.lucasborges.domain.Categoria;
import br.com.curso.spring.udemy.lucasborges.domain.Cidade;
import br.com.curso.spring.udemy.lucasborges.domain.Cliente;
import br.com.curso.spring.udemy.lucasborges.domain.Endereco;
import br.com.curso.spring.udemy.lucasborges.domain.Estado;
import br.com.curso.spring.udemy.lucasborges.domain.ItemPedido;
import br.com.curso.spring.udemy.lucasborges.domain.Pagamento;
import br.com.curso.spring.udemy.lucasborges.domain.PagamentoComBoleto;
import br.com.curso.spring.udemy.lucasborges.domain.PagamentoComCartao;
import br.com.curso.spring.udemy.lucasborges.domain.Pedido;
import br.com.curso.spring.udemy.lucasborges.domain.Produto;
import br.com.curso.spring.udemy.lucasborges.domain.enums.EstadoPagamento;
import br.com.curso.spring.udemy.lucasborges.domain.enums.TipoCliente;
import br.com.curso.spring.udemy.lucasborges.repositories.CategoriaRepository;
import br.com.curso.spring.udemy.lucasborges.repositories.CidadeRepository;
import br.com.curso.spring.udemy.lucasborges.repositories.ClienteRepository;
import br.com.curso.spring.udemy.lucasborges.repositories.EnderecoRepository;
import br.com.curso.spring.udemy.lucasborges.repositories.EstadoRepository;
import br.com.curso.spring.udemy.lucasborges.repositories.ItemPedidoRepository;
import br.com.curso.spring.udemy.lucasborges.repositories.PagamentoRepository;
import br.com.curso.spring.udemy.lucasborges.repositories.PedidoRepository;
import br.com.curso.spring.udemy.lucasborges.repositories.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication(scanBasePackages = "br.com.curso")
@EntityScan(basePackages = "br.com.curso.spring.udemy.lucasborges.domain")
public class CursoMcApplication implements CommandLineRunner {

    final
    CategoriaRepository repository;
    final
    ProdutoRepository produtoRepository;
    final
    EstadoRepository estadoRepository;
    final
    CidadeRepository cidadeRepository;
    final
    EnderecoRepository enderecoRepository;
    final
    ClienteRepository clienteRepository;
    final
    PedidoRepository pedidoRepository;
    final
    PagamentoRepository pagamentoRepository;
    final
    ItemPedidoRepository itemPedidoRepository;


    public CursoMcApplication(CategoriaRepository repository, ProdutoRepository produtoRepository, EstadoRepository estadoRepository, CidadeRepository cidadeRepository, EnderecoRepository enderecoRepository, ClienteRepository clienteRepository, PedidoRepository pedidoRepository, PagamentoRepository pagamentoRepository, ItemPedidoRepository itemPedidoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CursoMcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = getOrCreateCategoria("Informatica");
        Categoria cat2 = getOrCreateCategoria("Escritório");
        Categoria cat3 = getOrCreateCategoria("Cama-mesa-banho");
        Categoria cat4 = getOrCreateCategoria("Eletrodoméstico");
        Categoria cat5 = getOrCreateCategoria("Móveis");
        Categoria cat6 = getOrCreateCategoria("SmartPhones");
        Categoria cat7 = getOrCreateCategoria("SmartTv");


        Produto p1 = new Produto(null, "Computador", BigDecimal.valueOf(2000.00));
        Produto p2 = new Produto(null, "Impressora", BigDecimal.valueOf(800.00));
        Produto p3 = new Produto(null, "Mouse", BigDecimal.valueOf(80.00));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().add(p2);

        p1.getCategorias().add(cat1);
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().add(cat1);

        est1.getCidades().add(c1);
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        Cliente cli1 = new Cliente(
                null,
                "Maria Silva",
                "maria@gmail.com",
                "36378912377",
                TipoCliente.PESSOAFISICA,
                "1234");

        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

        Endereco e1 = new Endereco("Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
        Endereco e2 = new Endereco("Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(
                null,
                EstadoPagamento.PENDENTE,
                ped2,
                sdf.parse("20/10/2017 00:00"),
                sdf.parse("20/10/2017 00:00"));
        ped2.setPagamento(pagto2);

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().add(ip3);

        p1.getItens().add(ip1);
        p2.getItens().add(ip3);
        p3.getItens().add(ip2);

        repository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
        clienteRepository.saveAll(List.of(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));
        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }

    private Categoria getOrCreateCategoria(String nome) {
        return repository.findByNome(nome)
                .orElseGet(() -> repository.save(new Categoria(null, nome)));
    }
}
