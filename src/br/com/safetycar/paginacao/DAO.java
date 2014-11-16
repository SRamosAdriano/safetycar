package br.com.safetycar.paginacao;

import java.util.*;



/**
 * Define um DAO com operações básicas.
 */
public interface DAO<T> {
	/**
	 * Inclui um objeto no macanismo de persistência.
	 * 
	 * @param obj Objeto a ser incluído.
	 */
	void incluir(T obj);
	
	/**
	 * Recupera um objeto do mecanismo de persistência.
	 * 
	 * @param id ID do objeto.
	 * @return Objeto recuperado ou null se não encontrou.
	 */
	T recuperar(Long id);
	
	/**
	 * Atualiza os dados do objeto no mecanismo de persistência.
	 * 
	 * @param obj Objeto novo.
	 */
	void atualizar(T obj);
	
	/**
	 * Remove um objeto do mecanismo de persistência.
	 * 
	 * @param id ID do objeto a ser removido.
	 * @return Objeto removido ou null se não encontrou.
	 */
	T remover(Long id);
	
	/**
	 * Remove um objeto do Fisicamente.
	 * 
	 * @param id ID do objeto a ser removido.
	 * @return Objeto removido ou null se não encontrou.
	 */
	void removerFisico(Long id);
	
	/**
	 * Inclui ou atualiza o objeto dependendo do ID.
	 * Se possuir ID, atualiza, se não, inclui.
	 * 
	 * @param obj Objeto a persistir.
	 */
	void incluirOuAtualizar(T obj);
	
	/**
	 * Recupera o número de objetos no mecanismo de persistência.
	 * 
	 * @param filtros Filtros que devem ser usados.
	 * @return Número de objetos no mecanismo de persistência.
	 */
	Long recuperarTotal(Filtro... filtros);
	
	/**
	 * Obtém uma lista com os objetos no mecanismo de persistência.
	 * 
	 * @param filtros Filtros que devem ser usados na busca.
	 * @return Lista com os objetos.
	 */
	List<T> listar(Filtro... filtros);
	
	/**
	 * Obtém uma lista com os objetos no mecanismo de persistência,
	 * iniciando no item de índice "primeiro" e recuperando no 
	 * máximo "quantidade".
	 * 
	 * @param primeiro Índice do primeiro objeto a recuperar.
	 * @param quantidade Quantidade de objetos a recuperar.
	 * @param filtros filtros que devem ser usados na busca.
	 * @return Lista com os objetos.
	 */
	List<T> listar(int primeiro, int quantidade, Filtro... filtros);

}