package br.com.daysesoares.cartaocredito.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.daysesoares.cartaocredito.dominio.Cliente;
import br.com.daysesoares.cartaocredito.dominio.FaturaCartaoCredito;

@Component
public class CarregarDadosClienteProcessor implements ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> {

	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public FaturaCartaoCredito process(FaturaCartaoCredito faturaCartaoCredito) throws Exception {
		String url = String.format("http://my-json-server.typicode.com/giuliana-bezerra/demo/profile/%d", faturaCartaoCredito.getCartaoCredito().getCliente().getId());
		ResponseEntity<Cliente> response = restTemplate.getForEntity(url, Cliente.class);
		
		if(response.getStatusCode() != HttpStatus.OK) {
			throw new ValidationException("cliente não encontrado.");
		}
		
		faturaCartaoCredito.setCliente(response.getBody());
		return faturaCartaoCredito;
	}

}
