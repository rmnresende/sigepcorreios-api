package br.com.dynamicdev.sigeprestapi.resource;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.correios.bsb.sigep.master.bean.cliente.AutenticacaoException;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import br.com.dynamicdev.sigeprestapi.service.EtiquetaService;
import generated.Correioslog;

@RestController
@RequestMapping("/etiquetas")
@Validated
public class EtiquetaResource {

	@Autowired
	private EtiquetaService service;

	@GetMapping("/{idServico}/{qtdEtiquetas}")
	public ResponseEntity<String> getEtiquetas(@PathVariable long idServico, @PathVariable int qtdEtiquetas)
			throws SigepClienteException, AutenticacaoException {

		return ResponseEntity.ok(service.SolicitarEtiquetas(idServico, qtdEtiquetas));
	}
	
	@PostMapping("/digito-verificador")
	public ResponseEntity<List<Integer>> gerarDigitoVerificador(@RequestBody List<String> etiquetas)
			throws SigepClienteException, AutenticacaoException {

		return ResponseEntity.ok(service.GerarDigitoVerificadorEtiquetas(etiquetas));
	}

	@PutMapping("/bloquear/{numeroEtiqueta}/{idPlp}")
	public ResponseEntity<String> bloquearEtiqueta(
			@PathVariable @Size(max = 13, min = 13, message = "Número de etiqueta inválido") String numeroEtiqueta,
			@PathVariable long idPlp) throws SigepClienteException, AutenticacaoException {

		return ResponseEntity.ok(service.bloquearEtiqueta(numeroEtiqueta, idPlp));
	}

	@PutMapping("/fechar")
	public ResponseEntity<Long> fecharPlp(@RequestBody @Valid Correioslog correioslog) {
		return null;
	}

}
