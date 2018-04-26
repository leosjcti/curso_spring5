package com.boldadeideias.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.boldadeideias.springboot.app.models.entities.Fatura;

public interface IFaturaDao extends CrudRepository<Fatura, Long> {

}
