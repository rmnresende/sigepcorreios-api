package br.com.dynamicdev.sigeprestapi.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServicoPostal implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String codigo;
	private String decricao;	
}
