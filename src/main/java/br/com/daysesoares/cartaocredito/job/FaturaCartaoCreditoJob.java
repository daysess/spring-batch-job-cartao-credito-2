package br.com.daysesoares.cartaocredito.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class FaturaCartaoCreditoJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job fatartaoCreditoJob(Step faturaCartaoCreditoStep) {
		return jobBuilderFactory
				.get("fatCartaoCreditoJob")
				.start(faturaCartaoCreditoStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
}
