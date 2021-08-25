package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service //componente do spring e representa serviços que são executados - regras de negócio
public class CatalogoClienteService {
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Long clienteId) {
		return 	clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
	}
	
	//responsável por adiocionar e atualizar um cliente
	@Transactional //indica que o método deve ser executado dentro de uma transação. Se algo der errado aqui, todo o resto é descartado (banco de dados)
	public Cliente salvar(Cliente cliente) {
		//Regra de negócio para impedir que tenha clientes com e-mails duplicados
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if (emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
		}
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		 clienteRepository.deleteById(clienteId);
	}
	
	

}
