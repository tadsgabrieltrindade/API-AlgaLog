package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable //indica que essa classe tem capacidade de ser usada embedded em outra entidade
public class Destinatario {

	@Column(name = "destinatario_nome")
	private String nome;
	
	@Column(name = "destinatario_logradouro")
	private String logradouro;
	
	@Column(name = "destinatario_numero")
	private String numero;
	
	@Column(name = "destinatario_complemento")
	private String complemento;
	
	@Column(name = "destinatario_bairro")
	private String bairro;
	
	
}
