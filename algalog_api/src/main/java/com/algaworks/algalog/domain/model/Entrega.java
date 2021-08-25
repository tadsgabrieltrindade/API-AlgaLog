package com.algaworks.algalog.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {
 
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne //mapeamento muitos-para-um entre entrega e cliente
	private Cliente cliente;
	
	@Embedded //utilizada quando as classes aqui são diferentes, mas queremos que no banco de 
	//dados estejam os dados de entrega e destinatário  na mesma tabela
	private Destinatario destinatario; 
	
	private BigDecimal taxa;
	
	@JsonProperty(access = Access.READ_ONLY) 
	@Enumerated(EnumType.STRING) //indica que queremos persistir no banco uma das string do enum e não o número
	private StatusEntrega status;
	
	@JsonProperty(access = Access.READ_ONLY)  
	private LocalDateTime dataPedido;
	
	@JsonProperty(access = Access.READ_ONLY) //somente leitura, não dá pra fazer post
	private LocalDateTime dataFinalizacao;
	
	
	
}
