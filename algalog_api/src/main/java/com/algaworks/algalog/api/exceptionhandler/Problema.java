package com.algaworks.algalog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL) //inclui propriedades que não estão null
@Getter
@Setter
//Esta classe representa um erro
public class Problema {
	private Integer status;
	private LocalDateTime dataHora;
	private String titulo;
	private List<Campo> campos;
	
	@Getter
	@AllArgsConstructor 
	public static class Campo{
		private String nome;
		private String mensagem;
		
	}
	
	
}
