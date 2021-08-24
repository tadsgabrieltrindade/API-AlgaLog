package com.algaworks.algalog.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.servece.CatalogoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor //gera um construtor com todas as propriedades da classe clienteRepository
@RestController //indica que essa classe é um componente do Spring e que pode tratar requisiçõs HTTP 
@RequestMapping("/clientes")
public class ClienteController {
	
	// poderia ter @Autowired caso não tivesse o @AllargsConstructor
	// que define que queremos injetar uma instancia que está sendo gerenciada pelo Spring
	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;
	
	@GetMapping //mapeamento desse método
	public List<Cliente> listar() {
		return clienteRepository.findAll(); 
	}
	
	
	@GetMapping("/{clienteId}") //pois é uma variável, a anotação path... é uma vínculo com a variável da uri 
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		//Optional<Cliente> cliente = clienteRepository.findById(clienteId);
//		if(cliente.isPresent())
//			return ResponseEntity.ok(cliente.get()); //código 200 HTTP e pega o que está dentro do optional
//		return ResponseEntity.notFound().build(); //código 404 HTTP e constrói a requisição
		
		//dá para fazer com expressão lambda tbm
		return clienteRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) //apresenta o código de retorno "mais certo", digamos o 201
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) { 
		//a anotação @Requ... vincula o parametro do método ao corpo da requisição
		return catalogoClienteService.salvar(cliente); 
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente){
		if(!clienteRepository.existsById(clienteId)) //buscar para ver se há com esse id
			return ResponseEntity.notFound().build();
		
		cliente.setId(clienteId); //forço a atualização no cliente com o id passado na uri
		cliente = catalogoClienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> excluir(@PathVariable Long clienteId){ //ResponseEntity<Void> sem corpo na resposta
		if(!clienteRepository.existsById(clienteId)) 
			return ResponseEntity.notFound().build();
		catalogoClienteService.excluir(clienteId); 
		return ResponseEntity.noContent().build(); //código 204 HTTP, retorno ok mas sem conteúdo 
	}
	
	 
	
	
	@GetMapping("/conterExemplo")
	public List<Cliente> lis(){
		return clienteRepository.findByNomeContaining("el");
	}
}
