package br.com.safetycar.dao;

import java.util.List;

import br.com.safetycar.modelos.Paginas;

public interface PaginasDAO {
	
	public Paginas pesquisaPagina(long id);
	
	public List<Paginas> pesquisaPaginas(List<String> nomePagina);
	
	public List<Paginas> getPaginas();

}
