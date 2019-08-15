package br.com.dynamicdev.sigeprestapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.correios.bsb.sigep.master.bean.cliente.AutenticacaoException;
import br.com.correios.bsb.sigep.master.bean.cliente.EnderecoERP;
import br.com.correios.bsb.sigep.master.bean.cliente.SQLException_Exception;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import br.com.dynamicdev.sigeprestapi.service.CEPService;

@RestController
@RequestMapping("/cep")
public class CEPResource {

	@Autowired
	private CEPService service;

	@GetMapping("/{cep}")
	public ResponseEntity<EnderecoERP> consultarCep(@PathVariable String cep)
			throws SQLException_Exception, SigepClienteException {

		return ResponseEntity.ok(service.consultarCEP(cep));
	}

	@GetMapping("/servico/{cepOrigem}/{cepDestino}/{codigoServico}")
	public ResponseEntity<String> consultarDisponibilidadeServicoFaixaCEP(@PathVariable String cepOrigem,
			@PathVariable String cepDestino, @PathVariable String codigoServico)
			throws SigepClienteException, AutenticacaoException {

		return ResponseEntity.ok(service.consultarDisponibilidadeServicoFaixaCEP(cepOrigem, cepDestino, codigoServico));
	}

}
