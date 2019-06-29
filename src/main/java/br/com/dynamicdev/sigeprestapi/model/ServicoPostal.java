package br.com.dynamicdev.sigeprestapi.model;

import java.io.Serializable;

public class ServicoPostal implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String decricao;

	public ServicoPostal(String codigo, String descricao) {
		this.codigo = codigo;
		this.decricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDecricao() {
		return decricao;
	}

	public void setDecricao(String decricao) {
		this.decricao = decricao;
	}
}
