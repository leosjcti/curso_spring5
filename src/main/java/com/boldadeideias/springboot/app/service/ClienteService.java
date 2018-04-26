package com.boldadeideias.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boldadeideias.springboot.app.models.dao.IClienteDao;
import com.boldadeideias.springboot.app.models.dao.IFaturaDao;
import com.boldadeideias.springboot.app.models.dao.IprodutoDao;
import com.boldadeideias.springboot.app.models.entities.Cliente;
import com.boldadeideias.springboot.app.models.entities.Fatura;
import com.boldadeideias.springboot.app.models.entities.Produto;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IprodutoDao produtoDao;
	
	@Autowired
	private IFaturaDao faturaDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);		
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente findOne(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);		
	}

	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	public List<Produto> buscarPorNome(String nome) {
		return produtoDao.findByNomeLikeIgnoreCase("%"+nome+"%");
	}

	@Override
	@Transactional
	public void saveFatura(Fatura fatura) {
		faturaDao.save(fatura);		
	}

	@Override
	public Produto findProdutoById(Long id) {
		return produtoDao.findById(id).orElse(null);
	}
	
	
	
}
