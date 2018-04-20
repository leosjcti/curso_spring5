package com.boldadeideias.springboot.app.util.paginator;

public class PageItem {

	private int numero;
	private boolean atual;
	
	public PageItem(int numero, boolean atual) {
		super();
		this.numero = numero;
		this.atual = atual;
	}

	public int getNumero() {
		return numero;
	}

	public boolean isAtual() {
		return atual;
	}		
}
