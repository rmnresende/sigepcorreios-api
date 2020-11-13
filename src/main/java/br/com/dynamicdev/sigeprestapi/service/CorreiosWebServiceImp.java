package br.com.dynamicdev.sigeprestapi.service;

import org.springframework.stereotype.Service;

import br.com.correios.bsb.sigep.master.bean.cliente.AtendeCliente;
import br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteService;

@Service
public class CorreiosWebServiceImp implements CorreiosWebService{

	public AtendeCliente recuperarCorreiosClienteWebService() {
		return new AtendeClienteService().getAtendeClientePort();
	}

}
