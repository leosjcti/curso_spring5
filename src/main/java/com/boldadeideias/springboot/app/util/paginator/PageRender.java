package com.boldadeideias.springboot.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	
	private String url;
	private Page<T> page;
	
	private int totalPaginas;
	private int numElementosPorPagina;
	private int paginaAtual;
	private List<PageItem> paginas;
	
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();
		
		numElementosPorPagina = page.getSize();
		totalPaginas = page.getTotalPages();
		paginaAtual = page.getNumber() + 1;
		
		int desde, ate;
		if(totalPaginas <= numElementosPorPagina) {
			desde = 1;
			ate = totalPaginas;
		}else {
			if(paginaAtual <= numElementosPorPagina/2) {
				desde = 1;
				ate = numElementosPorPagina;
			}else if(paginaAtual >= totalPaginas - numElementosPorPagina/2) {
				desde = totalPaginas - numElementosPorPagina +1 ;
				ate = numElementosPorPagina;
			}else {
				desde = paginaAtual - numElementosPorPagina / 2;
				ate = numElementosPorPagina;
			}
		}
		
		for (int i = 0; i < ate; i++) {
			paginas.add(new PageItem(desde + i, paginaAtual == desde + i));
		}		
	}

	public String getUrl() {
		return url;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaAtual() {
		return paginaAtual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}
	
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
}
