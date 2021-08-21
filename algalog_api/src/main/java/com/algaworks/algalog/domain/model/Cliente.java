package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//Getter e Setter só é possível preparando a IDE e colocando o lombok como dependência
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //somente o atributo que eu defini com o include
@Getter
@Setter 
@Entity //defini que essa classe representa uma tabela no banco
public class Cliente {
	
	@EqualsAndHashCode.Include
	@Id //define que o atributo abaixo é uma PK da tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY) //define a estratégia de geração da pk, no caso, pega do banco (auto_increment)
	private Long id;
	private String nome;
	private String email;
	
	@Column(name = "fone") //define que o atributo abaixo tem o nome fone no banco de dados
	private String telefone;
	
	 
}
