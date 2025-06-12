package br.com.curso.spring.udemy.lucasborges.services;

import br.com.curso.spring.udemy.lucasborges.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class GeradorBoletoService {

    /**
     * Gera um boleto básico com valor definido, data de vencimento
     * e um identificador simples.
     *
     * @param valor          Valor total do boleto
     * @param dataVencimento Data de vencimento do boleto
     */
    public void gerarBoleto(BigDecimal valor, Date dataVencimento) {
        PagamentoComBoleto boleto = new PagamentoComBoleto();
        boleto.setValor(valor);
        boleto.setDataVencimento(dataVencimento);
        boleto.setNumeroDoBoleto("12345.67890 12345.678901 12345.678901 1 23456789012345");
    }
}