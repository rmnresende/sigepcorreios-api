package br.com.dynamicdev.sigeprestapi.service;

import org.springframework.stereotype.Service;

import br.com.correios.bsb.sigep.master.bean.cliente.AtendeCliente;
import br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteService;

@Service
public class CorreiosWebService {

	public AtendeCliente getCorreiosClienteWebService() {
		return new AtendeClienteService().getAtendeClientePort();
	}

}
