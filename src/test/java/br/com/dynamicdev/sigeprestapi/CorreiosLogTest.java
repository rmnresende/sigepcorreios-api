package br.com.dynamicdev.sigeprestapi;

import java.math.BigDecimal;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.dynamicdev.sigeprestapi.constantes.Credenciais;
import generated.Correioslog;
import generated.Destinatario;
import generated.DimensaoObjeto;
import generated.Nacional;
import generated.ObjetoPostal;
import generated.Plp;
import generated.Remetente;
import generated.ServicoAdicional;

public class CorreiosLogTest {

	@Test
	public void test() throws JsonProcessingException {

		Remetente rem = new Remetente();
		rem.setNumeroContrato("0067599079");
		rem.setNumeroDiretoria(Byte.valueOf("10"));
		rem.setCodigoAdministrativo("17000190");
		rem.setNomeRemetente("José dos Santos");
		rem.setLogradouroRemetente("Avenida Brasil");
		rem.setNumeroRemetente("10");
		rem.setBairroRemetente("Bairro");
		rem.setCepRemetente("80002900");
		rem.setCidadeRemetente("Curitiba");
		rem.setUfRemetente("PR");
		rem.setTelefoneRemetente("4130795008");
		rem.setEmailRemetente("email@email.com");

		Destinatario dest = new Destinatario();
		dest.setNomeDestinatario("João da Silva");
		dest.setTelefoneDestinatario("3134867622");
		dest.setCelularDestinatario("31991383005");
		dest.setEmailDestinatario("email@gmail.com");
		dest.setLogradouroDestinatario("Rua A");
		dest.setComplementoDestinatario("casa");
		dest.setNumeroEndDestinatario("1");

		Nacional nacional = new Nacional();
		nacional.setBairroDestinatario("SetorIndustrial");
		nacional.setCidadeDestinatario("Goiânia");
		nacional.setUfDestinatario("GO");
		nacional.setCepDestinatario("74503100");
		nacional.setNumeroNotaFiscal("112233");
		nacional.setDescricaoObjeto("");
		nacional.setValorACobrar("0,00");

		ServicoAdicional servAdcional = new ServicoAdicional();
		servAdcional.getCodigoServicoAdicional().add(Short.valueOf("025"));
		servAdcional.getCodigoServicoAdicional().add(Short.valueOf("001"));
		servAdcional.getCodigoServicoAdicional().add(Short.valueOf("019"));
		servAdcional.setValorDeclarado("300,00");

		DimensaoObjeto dimensao = new DimensaoObjeto();
		dimensao.setTipoObjeto(Short.valueOf("002"));
		dimensao.setDimensaoAltura(50);
		dimensao.setDimensaoLargura(30);
		dimensao.setDimensaoComprimento(60);
		dimensao.setDimensaoDiametro(0);


		ObjetoPostal op = new ObjetoPostal();
		op.setNumeroEtiqueta("SO000641962BR");
		op.setCodigoServicoPostagem("41068");
		op.setCubagem("0,00");
		op.setStatusProcessamento(Byte.valueOf("0"));
		op.setDestinatario(dest);
		op.setNacional(nacional);
		op.setServicoAdicional(servAdcional);
		op.setDimensaoObjeto(dimensao);

		Plp plp = new Plp();
		plp.setCartaoPostagem(Credenciais.CARTAO);

		Correioslog correioslog = new Correioslog();
		correioslog.setRemetente(rem);
		correioslog.setTipoArquivo("Postagem");
		correioslog.setVersaoArquivo(BigDecimal.valueOf(2.3));
		correioslog.setPlp(plp);
		correioslog.getObjetoPostal().add(op);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(correioslog);

		System.out.println("JSON ==>>> " + json);
	}

}
