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
import br.com.dynamicdev.sigeprestapi.service.CartaoPostagemServiceImp;

@RestController
@RequestMapping("/cartaopostagem")
public class CartaoPostagemResource {

	@Autowired
	private CartaoPostagemServiceImp service;

	@GetMapping("/servicoserp")
	public ResponseEntity<ClienteERP> consultarServicosERPDisponiveis()
			throws SigepClienteException, AutenticacaoException {

		var servicoErp = service.consulatrServicosERPDisponiveis();

		if (servicoErp.isPresent())
			return ResponseEntity.ok(servicoErp.get());
		else
			return ResponseEntity.notFound().build();
	}

	@GetMapping("/servicospostais")
	public ResponseEntity<List<ServicoPostal>> consultarServicosPostaisDisponiveis()
			throws SigepClienteException, AutenticacaoException {

		return ResponseEntity.ok(service.consultarServicosPostaisDisponiveis());
	}

	@GetMapping("/status")
	public ResponseEntity<StatusCartao> consultarStatus() throws SigepClienteException, AutenticacaoException {

		return ResponseEntity.ok(service.consultarStatusCartao());
	}

}
