package br.com.dynamicdev.sigeprestapi.service;

import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.correios.bsb.sigep.master.bean.cliente.Acao;
import br.com.correios.bsb.sigep.master.bean.cliente.AutenticacaoException;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import br.com.correios.bsb.sigep.master.bean.cliente.TipoBloqueio;
import br.com.dynamicdev.sigeprestapi.constantes.Credenciais;
import generated.Correioslog;
import generated.ObjetoPostal;

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
				Credenciais.USUARIO, Credenciais.SENHA);
	}

	public String bloquearEtiqueta(String numeroEtiqueta, long idPlp)
			throws SigepClienteException, AutenticacaoException {

		return correiosWebService.getCorreiosClienteWebService().bloquearObjeto(numeroEtiqueta, idPlp,
				TipoBloqueio.FRAUDE_BLOQUEIO, Acao.DEVOLVIDO_AO_REMETENTE, Credenciais.USUARIO, Credenciais.SENHA);
	}

	public long fecharPlp(Correioslog correioslog) throws JAXBException, SigepClienteException, AutenticacaoException {

		var xml = converterCorriosLogParaXml(correioslog);
		var eitquetas = correioslog.getObjetoPostal().stream().map(ObjetoPostal::getNumeroEtiqueta)
				.collect(Collectors.joining(","));
		

		return correiosWebService.getCorreiosClienteWebService().fechaPlp(xml, 1L, Credenciais.CARTAO,
				eitquetas, Credenciais.USUARIO, Credenciais.SENHA);

	}

	private String converterCorriosLogParaXml(Correioslog correioslog) throws JAXBException {

		var context = JAXBContext.newInstance(Correioslog.class);
		var marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);

		var sw = new StringWriter();
		marshaller.marshal(correioslog, sw);
		return sw.toString();
	}

}
