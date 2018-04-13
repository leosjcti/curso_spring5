package com.boldadeideias.springboot.app.models.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.boldadeideias.springboot.app.models.entities.Cliente;

@Repository
public class ClienteDaoOld {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findAll() {
		return em.createQuery("from Cliente").getResultList();
	}

	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}
		
	public Cliente save(Cliente cliente) {
		if(cliente.getId() != null && cliente.getId() > 0) {
			em.merge(cliente);
		}else {
			em.persist(cliente);
		}
		return null;
	}
	
	public void delete(Long id) {
		Cliente cliente = findOne(id);
		em.remove(cliente);
	}	

}
