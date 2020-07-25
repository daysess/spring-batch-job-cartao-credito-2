package br.com.daysesoares.cartaocredito.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.daysesoares.cartaocredito.dominio.FaturaCartaoCredito;
import br.com.daysesoares.cartaocredito.dominio.Transacao;
import br.com.daysesoares.cartaocredito.reader.FaturaCartaoCreditoReader;

@Configuration
public class FaturaCartaoCreditoStepConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step faturaCartaoCreditoStep(
			ItemStreamReader<Transacao> lerTransacoesReader,
			ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> carregarDadosClienteProcessor,
			ItemWriter<FaturaCartaoCredito> escreverFaturaCartaoCredito
			) {
		return stepBuilderFactory 
				.get("faturaCartaoCreditoStep")
				.<FaturaCartaoCredito, FaturaCartaoCredito> chunk(1)
				.reader(new FaturaCartaoCreditoReader(lerTransacoesReader))
				.processor(carregarDadosClienteProcessor)
				.writer(escreverFaturaCartaoCredito)
				.build();
	}
	
}
