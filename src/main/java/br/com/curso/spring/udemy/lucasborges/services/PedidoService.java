package br.com.curso.spring.udemy.lucasborges.services;

import br.com.curso.spring.udemy.lucasborges.domain.PagamentoComBoleto;
import br.com.curso.spring.udemy.lucasborges.domain.Pedido;
import br.com.curso.spring.udemy.lucasborges.domain.Produto;
import br.com.curso.spring.udemy.lucasborges.domain.enums.EstadoPagamento;
import br.com.curso.spring.udemy.lucasborges.repositories.ItemPedidoRepository;
import br.com.curso.spring.udemy.lucasborges.repositories.PagamentoRepository;
import br.com.curso.spring.udemy.lucasborges.repositories.PedidoRepository;
import br.com.curso.spring.udemy.lucasborges.repositories.ProdutoRepository;
import br.com.curso.spring.udemy.lucasborges.services.exceptions.DataIntegrityException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {
    private static final String ERRO_PEDIDO_NAO_ENCONTRADO = "Objeto não encontrado! Id: %d, Tipo: %s";

    private final PedidoRepository pedidoRepository;
    private final GeradorBoletoService geradorBoletoService;
    private final PagamentoRepository pagamentoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository, GeradorBoletoService geradorBoletoService, PagamentoRepository pagamentoRepository, ProdutoRepository produtoRepository, ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.geradorBoletoService = geradorBoletoService;
        this.pagamentoRepository = pagamentoRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public Optional<Pedido> findById(Integer id) {
        Optional<Pedido> obj = pedidoRepository.findById(id);
        obj.orElseThrow(() -> new DataIntegrityException(
                String.format(ERRO_PEDIDO_NAO_ENCONTRADO, id, Pedido.class.getName())
        ));
        return obj;
    }

    /**
     * Cria um novo pedido no sistema.
     *
     * @param pedido O pedido a ser criado
     * @return O pedido criado com seu ID gerado
     * @throws IllegalArgumentException se o pedido for nulo ou inválido
     */
    @Transactional(rollbackOn = Exception.class)
    public Pedido insert(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        if (pedido.getCliente() == null || pedido.getEnderecoDeEntrega() == null) {
            throw new IllegalArgumentException("Pedido deve conter cliente e endereço de entrega");
        }

        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("Pedido deve conter pelo menos um item");
        }
        if (pedido.getPagamento() instanceof PagamentoComBoleto pagamentoComBoleto) {
            geradorBoletoService.gerarBoleto(
                    pagamentoComBoleto.getValor(),
                    pagamentoComBoleto.getDataVencimento()
            );
        }
        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());
        Pedido finalPedido = pedido;
        pedido.getItens().forEach(item -> {
            item.setDesconto(0.0);
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + item.getProduto().getId()));
            item.setPreco(produto.getPreco().doubleValue());
            item.setPedido(finalPedido);
        });
        itemPedidoRepository.saveAll(pedido.getItens());
        return pedido;
    }
}