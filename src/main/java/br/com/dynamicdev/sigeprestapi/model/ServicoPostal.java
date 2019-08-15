package br.com.dynamicdev.sigeprestapi.model;

import java.io.Serializable;

public class ServicoPostal implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String codigo;
	private String decricao;

	public ServicoPostal(long id, String codigo, String descricao) {
		this.id = id;
		this.codigo = codigo;
		this.decricao = descricao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
