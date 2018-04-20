package com.boldadeideias.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.boldadeideias.springboot.app.models.entities.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

}
