package br.com.dynamicdev.sigeprestapi.service;

import java.util.List;
import java.util.Optional;

import br.com.correios.bsb.sigep.master.bean.cliente.AutenticacaoException;
import br.com.correios.bsb.sigep.master.bean.cliente.ClienteERP;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import br.com.correios.bsb.sigep.master.bean.cliente.StatusCartao;
import br.com.dynamicdev.sigeprestapi.model.ServicoPostal;

public interface CartaoPostagemService {
	
	Optional<ClienteERP> consulatrServicosERPDisponiveis() throws SigepClienteException, AutenticacaoException;
	List<ServicoPostal> consultarServicosPostaisDisponiveis() throws SigepClienteException, AutenticacaoException;
	StatusCartao consultarStatusCartao() throws SigepClienteException, AutenticacaoException;

}
