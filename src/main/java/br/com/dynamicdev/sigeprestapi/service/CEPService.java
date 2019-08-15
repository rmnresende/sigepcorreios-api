package br.com.dynamicdev.sigeprestapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.correios.bsb.sigep.master.bean.cliente.AutenticacaoException;
import br.com.correios.bsb.sigep.master.bean.cliente.EnderecoERP;
import br.com.correios.bsb.sigep.master.bean.cliente.SQLException_Exception;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import br.com.dynamicdev.sigeprestapi.constantes.Credenciais;

@Service
public class CEPService {

	@Autowired
	private CorreiosWebService correiosWebService;

	public EnderecoERP consultarCEP(String cep) throws SQLException_Exception, SigepClienteException {
		return correiosWebService.getCorreiosClienteWebService().consultaCEP(cep);
	}

	public String consultarDisponibilidadeServicoFaixaCEP(String cepOrigem, String cepDestino, String codServico)
			throws SigepClienteException, AutenticacaoException {

		return correiosWebService.getCorreiosClienteWebService().verificaDisponibilidadeServico(
				Credenciais.CODIGO_ADMINISTRATIVO,
				codServico, cepOrigem, cepDestino, Credenciais.USUARIO, Credenciais.SENHA);
	}


}
