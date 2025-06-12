package br.com.curso.spring.udemy.lucasborges.config;

import br.com.curso.spring.udemy.lucasborges.domain.PagamentoComBoleto;
import br.com.curso.spring.udemy.lucasborges.domain.PagamentoComCartao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.lang.NonNull;

import java.util.List;


@Configuration
public class JacksonConfig {

    private static final List<Class<?>> PAYMENT_SUBTYPES = List.of(
            PagamentoComCartao.class,
            PagamentoComBoleto.class
    );

    @Bean
    public Jackson2ObjectMapperBuilder pagamentoObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder() {
            @Override
            public void configure(@NonNull ObjectMapper objectMapper) {
                configurarSubtiposPagamento(objectMapper);
                super.configure(objectMapper);
            }
        };
    }

    private void configurarSubtiposPagamento(@NonNull ObjectMapper objectMapper) {
        PAYMENT_SUBTYPES.forEach(objectMapper::registerSubtypes);
    }
}