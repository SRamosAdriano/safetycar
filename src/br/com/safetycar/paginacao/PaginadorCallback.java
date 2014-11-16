package br.com.safetycar.paginacao;

import java.util.*;


/**
 * Callback para customzação do processo de obtenção dos dados para paginação.
 * Usado com o método <code>br.com.intelignet.controle.paginacao.Paginador.paginar(PaginadorCallback, int, Filtro...)</code>.
 */
public interface PaginadorCallback {
	/**
	 * Obtém os total de dados a paginar.
	 * 
	 * @param filtros Filtros que devem ser usados.
	 * @return Total de dados a paginar.
	 */
	int getTotalDados(Filtro... filtros);
	
	/**
	 * Obtém os dados de uma página.
	 * 
	 * @param indicePrimeiro Índice do primeiro item.
	 * @param quantidade Quantidade de itens a recuperar.
	 * @param filtros Filtros que devem ser usados.
	 * @return Lista com os objetos.
	 */
	@SuppressWarnings("unchecked")
	List getDados(int indicePrimeiro, int quantidade, Filtro... filtros);
}