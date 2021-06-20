package com.sistema.springboot.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T>page;
	
	private List<PageItem> paginas;
	
	private int totalPaginas;
	private int numeroElementoPorPagina;
	private int paginaActual;
	
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas= new ArrayList<PageItem>();
		numeroElementoPorPagina= page.getSize();
		totalPaginas = page.getTotalPages();
		paginaActual = page.getNumber()+1;
		
		int desde, hasta;
		if(totalPaginas <=numeroElementoPorPagina) {
			desde=1;
			hasta=totalPaginas;
		}else {
			if(paginaActual<=numeroElementoPorPagina/2) {
				desde = 1;
				hasta = numeroElementoPorPagina;
			}else if(paginaActual >= totalPaginas- numeroElementoPorPagina/2 ) {
				desde = totalPaginas - numeroElementoPorPagina+1;
				hasta=numeroElementoPorPagina;
			}else {
				desde = paginaActual - numeroElementoPorPagina/2;
				hasta = numeroElementoPorPagina;
			}
			
		}
		
		for (int i = 0; i < hasta; i++) {
			paginas.add(new PageItem(desde+i, paginaActual== desde +i));
		}
		
		
	}

	public String getUrl() {
		return url;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
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
