package br.com.dynamicdev.sigeprestapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.correios.bsb.sigep.master.bean.cliente.AutenticacaoException;
import br.com.correios.bsb.sigep.master.bean.cliente.ClienteERP;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import br.com.correios.bsb.sigep.master.bean.cliente.StatusCartao;
import br.com.dynamicdev.sigeprestapi.model.ServicoPostal;
import br.com.dynamicdev.sigeprestapi.service.CartaoPostagemService;

@RestController
@RequestMapping("/cartaopostagem")
public class CartaoPostagemResource {

	@Autowired
	private CartaoPostagemService service;

	@GetMapping("/servicoserp")
	public ResponseEntity<ClienteERP> getServicosERPDisponiveis()
			throws SigepClienteException, AutenticacaoException {

		var servicoErp = service.getServicosERPDisponiveis();

		if (servicoErp.isPresent())
			return ResponseEntity.ok(servicoErp.get());
		else
			return ResponseEntity.notFound().build();
	}

	@GetMapping("/servicospostais")
	public ResponseEntity<List<ServicoPostal>> getServicosPostaisDisponiveis()
			throws SigepClienteException, AutenticacaoException {

		return ResponseEntity.ok(service.getServicosPostaisDisponiveis());
	}

	@GetMapping("/status")
	public ResponseEntity<StatusCartao> getStatus() throws SigepClienteException, AutenticacaoException {

		return ResponseEntity.ok(service.GetStatusCartao());
	}

}
