package br.com.daysesoares.cartaocredito.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.daysesoares.cartaocredito.dominio.Transacao;

@Configuration
public class FaturaCartaoCreditoStepConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step faturaCartaoCreditoStep(
			ItemReader<Transacao> lerTransacoesReader,
			ItemWriter<Transacao> escreverFaturaCartaoCredito
			) {
		return stepBuilderFactory 
				.get("faturaCartaoCreditoStep")
				.<Transacao,Transacao> chunk(1)
				.reader(lerTransacoesReader)
				.writer(escreverFaturaCartaoCredito)
				.build();
	}
	
}
