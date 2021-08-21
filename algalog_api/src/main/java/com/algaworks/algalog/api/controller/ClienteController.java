package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor //gera um construtor com todas as propriedades da classe clienteRepository
@RestController //indica que essa classe é um componente do Spring e que pode tratar requisiçõs HTTP 
public class ClienteController {
	
	// poderia ter @Autowired caso não tivesse o @AllargsConstructor
	// que define que queremos injetar uma instancia que está sendo gerenciada pelo Spring
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes") //mapeamento desse método
	public List<Cliente> listar() {
		return clienteRepository.findAll(); 
	}
	
	@GetMapping("/conterExemplo")
	public List<Cliente> lis(){
		return clienteRepository.findByNomeContaining("el");
	}
}
