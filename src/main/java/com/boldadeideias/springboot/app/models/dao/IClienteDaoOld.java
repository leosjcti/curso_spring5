package com.boldadeideias.springboot.app.models.dao;

import java.util.List;

import com.boldadeideias.springboot.app.models.entities.Cliente;

public interface IClienteDaoOld {
	
	public List<Cliente>findAll();
	
	public void save(Cliente cliente);
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);

}
