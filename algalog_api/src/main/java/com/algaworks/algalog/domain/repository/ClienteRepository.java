package com.algaworks.algalog.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algalog.domain.model.Cliente;

//Herda dessa outra interface que irá manipular os dados
@Repository //indica que é um componente do spring, porém tem uma semântica bem específica
//componente spring é gerenciado pelo container do spring 
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	//esse método é customizado. O nome dele segue um padrão findBy<delimitador>.
	//colocando certo não precisa fazer mais nada, o spring irá fornecer uma implementação de busca
	List<Cliente> findByNome(String nome);
	
	//O sufixo Containing indica o like do sql
	List<Cliente> findByNomeContaining(String nome);
	
}
