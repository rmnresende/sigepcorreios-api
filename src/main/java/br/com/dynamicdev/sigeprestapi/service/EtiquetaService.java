package br.com.dynamicdev.sigeprestapi.service;

import java.util.List;

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

			return correiosWebService.getCorreiosClienteWebService().solicitaEtiquetas("C", Credenciais.CNPJ, idServico,
					qtdEtiquetas, Credenciais.USUARIO, Credenciais.SENHA);
	}
	
	public List<Integer> GerarDigitoVerificadorEtiquetas(List<String> etiquetas)
			throws SigepClienteException, AutenticacaoException {

		return correiosWebService.getCorreiosClienteWebService().geraDigitoVerificadorEtiquetas(etiquetas,
				Credenciais.USUARIO,
				Credenciais.SENHA);
	}

}
