package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	
	@NotBlank //impede inserir no banco o null e uma string vazia
	@Size(max = 60) //tamanho máximo do valor do nome
	private String nome;
	
	@NotBlank
	@Email //verifica a sintaxe do email antes de inseri no banco, tendo o @ o . (ponto)
	@Size(max = 255)
	private String email;
	
	@NotBlank
	@Size(max = 20)
	@Column(name = "fone") //define que o atributo abaixo tem o nome fone no banco de dados
	private String telefone;
	
	 
}
