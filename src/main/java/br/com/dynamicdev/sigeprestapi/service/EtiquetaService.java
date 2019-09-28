package br.com.dynamicdev.sigeprestapi.service;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.SchemaFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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

		var etiquetas = correioslog.getObjetoPostal().stream().map(ObjetoPostal::getNumeroEtiqueta)
				.collect(Collectors.toList());

		var etiquetasSemCodigo = new ArrayList<String>();

		for (String e : etiquetas) {

			var etiquetaSemCodVerificador = new StringBuilder(e);
			etiquetasSemCodigo.add(etiquetaSemCodVerificador.deleteCharAt(10).toString());
		}

		var xml = converterCorriosLogParaXml(correioslog);
		System.out.println(xml);

		return correiosWebService.getCorreiosClienteWebService().fechaPlpVariosServicos(xml, 1L, Credenciais.CARTAO,
				etiquetasSemCodigo, Credenciais.USUARIO, Credenciais.SENHA);
	}

	public String solicitarXmlPlp(long idPlpMaster)
			throws SigepClienteException, AutenticacaoException {

		return correiosWebService.getCorreiosClienteWebService()
				.solicitaXmlPlp(idPlpMaster, Credenciais.USUARIO, Credenciais.SENHA);
	}

	private String converterCorriosLogParaXml(Correioslog correioslog) throws JAXBException {

		var context = JAXBContext.newInstance(Correioslog.class);
		var marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");

		var sw = new StringWriter();
		marshaller.marshal(correioslog, sw);
		var xml = sw.toString();

		validarXML(correioslog);
		return xml;
	}
	
	private void validarXML(Correioslog correioslog) {

		try {

			var jaxbContext = JAXBContext.newInstance(Correioslog.class);
			// Setup schema validator
			var sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			var schema = sf.newSchema(
					new File(getClass().getClassLoader().getResource("xsd/SIGEPWEB_VALIDADOR_XML_V2.XSD").getFile()));

			var marshaller = jaxbContext.createMarshaller();
			marshaller.setSchema(schema);
			marshaller.marshal(correioslog, new DefaultHandler());

		} catch (JAXBException | SAXException e) {
			e.printStackTrace();
		}
    }

}
