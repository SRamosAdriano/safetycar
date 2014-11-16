package br.com.safetycar.paginacao;

import java.util.*;



/**
 * Define um DAO com opera��es b�sicas.
 */
public interface DAO<T> {
	/**
	 * Inclui um objeto no macanismo de persist�ncia.
	 * 
	 * @param obj Objeto a ser inclu�do.
	 */
	void incluir(T obj);
	
	/**
	 * Recupera um objeto do mecanismo de persist�ncia.
	 * 
	 * @param id ID do objeto.
	 * @return Objeto recuperado ou null se n�o encontrou.
	 */
	T recuperar(Long id);
	
	/**
	 * Atualiza os dados do objeto no mecanismo de persist�ncia.
	 * 
	 * @param obj Objeto novo.
	 */
	void atualizar(T obj);
	
	/**
	 * Remove um objeto do mecanismo de persist�ncia.
	 * 
	 * @param id ID do objeto a ser removido.
	 * @return Objeto removido ou null se n�o encontrou.
	 */
	T remover(Long id);
	
	/**
	 * Remove um objeto do Fisicamente.
	 * 
	 * @param id ID do objeto a ser removido.
	 * @return Objeto removido ou null se n�o encontrou.
	 */
	void removerFisico(Long id);
	
	/**
	 * Inclui ou atualiza o objeto dependendo do ID.
	 * Se possuir ID, atualiza, se n�o, inclui.
	 * 
	 * @param obj Objeto a persistir.
	 */
	void incluirOuAtualizar(T obj);
	
	/**
	 * Recupera o n�mero de objetos no mecanismo de persist�ncia.
	 * 
	 * @param filtros Filtros que devem ser usados.
	 * @return N�mero de objetos no mecanismo de persist�ncia.
	 */
	Long recuperarTotal(Filtro... filtros);
	
	/**
	 * Obt�m uma lista com os objetos no mecanismo de persist�ncia.
	 * 
	 * @param filtros Filtros que devem ser usados na busca.
	 * @return Lista com os objetos.
	 */
	List<T> listar(Filtro... filtros);
	
	/**
	 * Obt�m uma lista com os objetos no mecanismo de persist�ncia,
	 * iniciando no item de �ndice "primeiro" e recuperando no 
	 * m�ximo "quantidade".
	 * 
	 * @param primeiro �ndice do primeiro objeto a recuperar.
	 * @param quantidade Quantidade de objetos a recuperar.
	 * @param filtros filtros que devem ser usados na busca.
	 * @return Lista com os objetos.
	 */
	List<T> listar(int primeiro, int quantidade, Filtro... filtros);

}