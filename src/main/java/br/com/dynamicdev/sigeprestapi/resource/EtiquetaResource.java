package br.com.dynamicdev.sigeprestapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.correios.bsb.sigep.master.bean.cliente.AutenticacaoException;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import br.com.dynamicdev.sigeprestapi.service.EtiquetaService;

@RestController
@RequestMapping("/etiquetas")
public class EtiquetaResource {

	@Autowired
	private EtiquetaService service;

	@GetMapping("/{idServico}/{qtdEtiquetas}")
	public ResponseEntity<String> getEtiquetas(@PathVariable long idServico, @PathVariable int qtdEtiquetas)
			throws SigepClienteException, AutenticacaoException {

		return ResponseEntity.ok(service.SolicitarEtiquetas(idServico, qtdEtiquetas));
	}

}
