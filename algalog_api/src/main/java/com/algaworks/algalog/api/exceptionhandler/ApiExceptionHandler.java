package com.algaworks.algalog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algalog.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice //indica que é um componente do spring e que trata as exceções de forma global
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	//atributo responsável por customizar as exceções
	private MessageSource messageSource;
	
	//método customizado para o corpo da resposta 
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Problema.Campo> campos = new ArrayList<>();
		//ex é a exceção e pegamos todos os erros que foram atribuídos a ela
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField(); 
			//a mensagem é pega pelo messageSource passando o erro 
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			campos.add(new Problema.Campo(nome, mensagem));
		}
			
			
		Problema problema = new Problema();
		problema.setStatus(status.value()); //retorna o código de status HTTP
		problema.setDataHora(LocalDateTime.now());
		problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente. ");
		problema.setCampos(campos);
		return handleExceptionInternal( ex, problema, headers, status, request);
	}
	
	//trata a NegocioException
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problema problema = new Problema();
		problema.setStatus(status.value()); 
		problema.setDataHora(LocalDateTime.now());
		problema.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	
}
