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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String sobrenome;
	
	@NotEmpty
	@Email
	private String email;
	
	@Column(name="data_criacao")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull
	private Date dataCriacao;
	
	private String foto;
	
	@OneToMany(mappedBy="cliente" ,fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	List<Fatura> faturas;
	
	
	public Cliente() {
		faturas = new ArrayList<Fatura>();
	}
	
	/*@PrePersist
	public void prePersist() {
		dataCriacao = new Date();
	}*/
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public List<Fatura> getFaturas() {
		return faturas;
	}
	public void setFaturas(List<Fatura> faturas) {
		this.faturas = faturas;
	}
	
	public void addFatura(Fatura fatura) {
		faturas.add(fatura);
	}

}
