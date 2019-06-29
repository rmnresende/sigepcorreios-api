package br.com.dynamicdev.sigeprestapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.correios.bsb.sigep.master.bean.cliente.AutenticacaoException;
import br.com.correios.bsb.sigep.master.bean.cliente.CartaoPostagemERP;
import br.com.correios.bsb.sigep.master.bean.cliente.ClienteERP;
import br.com.correios.bsb.sigep.master.bean.cliente.ContratoERP;
import br.com.correios.bsb.sigep.master.bean.cliente.ServicoERP;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import br.com.dynamicdev.sigeprestapi.ListUtils;
import br.com.dynamicdev.sigeprestapi.constantes.Credenciais;
import br.com.dynamicdev.sigeprestapi.model.ServicoPostal;

@Service
public class ClienteService {

	@Autowired
	private CorreiosWebService correiosWebService;

	public ClienteERP getServicosERPDisponiveis() throws SigepClienteException, AutenticacaoException {

		return correiosWebService.getCorreiosClienteWebService().buscaCliente(Credenciais.CONTRATO, Credenciais.CARTAO,
				Credenciais.USUARIO, Credenciais.SENHA);
	}

	public List<ServicoPostal> getServicosPostaisDisponiveis()
			throws SigepClienteException, AutenticacaoException {

		var servicosERPOptional = getServicosERPDoClienteERP(Optional.ofNullable(getServicosERPDisponiveis()));

		if (servicosERPOptional.isPresent()) {

			var servicosERPDisponiveis = servicosERPOptional.get();
			var servicosPostaisDisponiveis = new ArrayList<ServicoPostal>();

			for (ServicoERP s : servicosERPDisponiveis) {
				servicosPostaisDisponiveis.add(new ServicoPostal(s.getCodigo(), s.getDescricao()));
			}

			return servicosPostaisDisponiveis;
		}

		return null;
	}



	public Optional<List<ServicoERP>> getServicosERPDoClienteERP(Optional<ClienteERP> clienteERP) {

		if (clienteERP.isPresent()) {

			var clienteCorreios = clienteERP.get();
			var servicosDisponiveis = new ArrayList<ServicoERP>();

			if (ListUtils.isListNotNullAndNotEmpty(clienteCorreios.getContratos())) {

				for (ContratoERP c : clienteCorreios.getContratos()) {

					if (ListUtils.isListNotNullAndNotEmpty(c.getCartoesPostagem())) {

						for (CartaoPostagemERP cp : c.getCartoesPostagem()) {

							if (ListUtils.isListNotNullAndNotEmpty(cp.getServicos())) {
								servicosDisponiveis.addAll(cp.getServicos());
							}
						}
					}
				}

				return servicosDisponiveis.isEmpty() ? Optional.empty() : Optional.of(servicosDisponiveis);
			}
		}

		return Optional.empty();
	}

}
