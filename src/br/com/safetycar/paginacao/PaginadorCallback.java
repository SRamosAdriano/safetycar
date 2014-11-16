package br.com.safetycar.paginacao;

import java.util.*;


/**
 * Callback para customza��o do processo de obten��o dos dados para pagina��o.
 * Usado com o m�todo <code>br.com.intelignet.controle.paginacao.Paginador.paginar(PaginadorCallback, int, Filtro...)</code>.
 */
public interface PaginadorCallback {
	/**
	 * Obt�m os total de dados a paginar.
	 * 
	 * @param filtros Filtros que devem ser usados.
	 * @return Total de dados a paginar.
	 */
	int getTotalDados(Filtro... filtros);
	
	/**
	 * Obt�m os dados de uma p�gina.
	 * 
	 * @param indicePrimeiro �ndice do primeiro item.
	 * @param quantidade Quantidade de itens a recuperar.
	 * @param filtros Filtros que devem ser usados.
	 * @return Lista com os objetos.
	 */
	@SuppressWarnings("unchecked")
	List getDados(int indicePrimeiro, int quantidade, Filtro... filtros);
}