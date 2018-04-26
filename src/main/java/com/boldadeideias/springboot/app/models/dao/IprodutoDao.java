package com.boldadeideias.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.boldadeideias.springboot.app.models.entities.Produto;

public interface IprodutoDao extends CrudRepository<Produto, Long> {

	@Query("select p from Produto p where p.nome like %?1%") // % ?1 % Pega o primeiro paremtro enviado
	public List<Produto> buscarPorNome(String term);
	
	public List<Produto> findByNomeLikeIgnoreCase(String nome);
	
}
