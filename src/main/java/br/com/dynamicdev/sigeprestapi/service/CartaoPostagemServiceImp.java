package br.com.dynamicdev.sigeprestapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.correios.bsb.sigep.master.bean.cliente.AutenticacaoException;
import br.com.correios.bsb.sigep.master.bean.cliente.CartaoPostagemERP;
import br.com.correios.bsb.sigep.master.bean.cliente.ClienteERP;
import br.com.correios.bsb.sigep.master.bean.cliente.ContratoERP;
import br.com.correios.bsb.sigep.master.bean.cliente.ServicoERP;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import br.com.correios.bsb.sigep.master.bean.cliente.StatusCartao;
import br.com.dynamicdev.sigeprestapi.CollectionsUtils;
import br.com.dynamicdev.sigeprestapi.constantes.Credenciais;
import br.com.dynamicdev.sigeprestapi.model.ServicoPostal;

@Service
public class CartaoPostagemServiceImp implements CartaoPostagemService {

	private CorreiosWebServiceImp correiosWebService;

	public CartaoPostagemServiceImp(CorreiosWebServiceImp correiosWebService) {
		this.correiosWebService = correiosWebService;
	}

	public Optional<ClienteERP> consulatrServicosERPDisponiveis() throws SigepClienteException, AutenticacaoException {

		return Optional.ofNullable(this.correiosWebService.recuperarCorreiosClienteWebService()
				.buscaCliente(Credenciais.CONTRATO, Credenciais.CARTAO, Credenciais.USUARIO, Credenciais.SENHA));
	}

	public List<ServicoPostal> consultarServicosPostaisDisponiveis()
			throws SigepClienteException, AutenticacaoException {

		var clienteERP = consulatrServicosERPDisponiveis();

		if (clienteERP.isPresent()
				&& CollectionsUtils.isCollectionNotNullAndNotEmpty(clienteERP.get().getContratos())) {
			var servicosERPOptional = getServicosERPDoClienteERP(clienteERP.get().getContratos());

			if (servicosERPOptional.isPresent()) {

				var servicosERPDisponiveis = servicosERPOptional.get();
				var servicosPostaisDisponiveis = new ArrayList<ServicoPostal>();

				for (ServicoERP s : servicosERPDisponiveis) {
					servicosPostaisDisponiveis
							.add(new ServicoPostal(s.getId(), s.getCodigo().trim(), s.getDescricao().trim()));
				}
				return servicosPostaisDisponiveis;
			}
		}

		return null;
	}

	public StatusCartao consultarStatusCartao() throws SigepClienteException, AutenticacaoException {

		return this.correiosWebService.recuperarCorreiosClienteWebService().getStatusCartaoPostagem(Credenciais.CARTAO,
				Credenciais.USUARIO, Credenciais.SENHA);
	}

	private Optional<List<ServicoERP>> getServicosERPDoClienteERP(List<ContratoERP> contratos) {

		var servicosDisponiveis = new ArrayList<ServicoERP>();

		for (ContratoERP c : contratos) {

			if (CollectionsUtils.isCollectionNotNullAndNotEmpty(c.getCartoesPostagem())) {

				for (CartaoPostagemERP cp : c.getCartoesPostagem()) {

					if (CollectionsUtils.isCollectionNotNullAndNotEmpty(cp.getServicos())) {
						servicosDisponiveis.addAll(cp.getServicos());
					}
				}
			}
		}

		return servicosDisponiveis.isEmpty() ? Optional.empty() : Optional.of(servicosDisponiveis);
	}

}
