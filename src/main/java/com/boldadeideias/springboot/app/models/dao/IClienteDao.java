package com.boldadeideias.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.boldadeideias.springboot.app.models.entities.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
