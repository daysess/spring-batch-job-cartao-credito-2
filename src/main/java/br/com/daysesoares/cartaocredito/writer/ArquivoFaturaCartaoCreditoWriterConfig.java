package br.com.daysesoares.cartaocredito.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import br.com.daysesoares.cartaocredito.dominio.Transacao;

@Configuration
public class ArquivoFaturaCartaoCreditoWriterConfig {
	
	@Bean
	public FlatFileItemWriter<Transacao> arquivoFaturaCartaoCredito() {
		return new FlatFileItemWriterBuilder<Transacao>()
				.name("arquivoFaturaCartaoCredito")
				.resource(new FileSystemResource("files/fatura.txt"))
				.lineAggregator(lineAggregator())
				.build();
	}
	
	private LineAggregator<Transacao> lineAggregator() {
		return new LineAggregator<Transacao>() {
			@Override
			public String aggregate(Transacao transacao) {
				StringBuilder writer = new StringBuilder();
				writer.append(transacao.getDescricao());
				return writer.toString();
			}
		};
	}

}
