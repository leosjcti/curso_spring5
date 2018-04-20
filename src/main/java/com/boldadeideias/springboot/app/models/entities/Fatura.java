package com.boldadeideias.springboot.app.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="faturas")
public class Fatura implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	String descricao;
	String observacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_criacao")
	Date dataCriacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	Cliente cliente;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="fatura_id")
	List<ItemFatura>itens;
	
	
	public Fatura() {
		this.itens = new ArrayList<ItemFatura>();
	}

	@PrePersist
	public void prePersist() {
		dataCriacao = new Date();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}	
			
	public List<ItemFatura> getItens() {
		return itens;
	}

	public void setItens(List<ItemFatura> itens) {
		this.itens = itens;
	}

	public void addItemFatura(ItemFatura item) {
		this.itens.add(item);
	}

	private static final long serialVersionUID = 1L;
	
}
