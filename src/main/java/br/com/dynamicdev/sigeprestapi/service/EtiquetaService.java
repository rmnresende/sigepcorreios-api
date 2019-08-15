package br.com.dynamicdev.sigeprestapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.correios.bsb.sigep.master.bean.cliente.AutenticacaoException;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import br.com.dynamicdev.sigeprestapi.constantes.Credenciais;

@Service
public class EtiquetaService {

	@Autowired
	private CorreiosWebService correiosWebService;

	public String SolicitarEtiquetas(long idServico, int qtdEtiquetas)
			throws SigepClienteException, AutenticacaoException {

		try {
			return correiosWebService.getCorreiosClienteWebService().solicitaEtiquetas("C", Credenciais.CNPJ, idServico,
					qtdEtiquetas, Credenciais.USUARIO, Credenciais.SENHA);
		} catch (SigepClienteException e) {
			e.printStackTrace();
			return null;
		}

	}

}
