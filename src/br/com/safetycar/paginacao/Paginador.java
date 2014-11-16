package br.com.safetycar.paginacao;

import java.util.*;


/**
 * Cont�m a l�gica para se fazer pagina��o.
 * Pode ser usado para paginar automaticamente com o m�todo
 * <code>paginar()</code> ou calcular o intervalo de registros
 * para uma dada p�gina com <code>getPrimeiroIndice()</code> e
 * <code>getUltimoIndice()</code>.
 */
public class Paginador {
	
	private static final int OBJETOS_POR_PAGINA_DEFAULT = 10;

	/**
	 * DAO que recuperar� os dados.
	 */
	@SuppressWarnings("unchecked")
	private DAO dao;
	
	/**
	 * N�mero de objetos por p�gina.
	 */
	private int objetosPorPagina;
	
	public Paginador() {
		objetosPorPagina = OBJETOS_POR_PAGINA_DEFAULT;
	}

	@SuppressWarnings("unchecked")
	public void setDao(DAO dao) {
		this.dao = dao;
	}
	
	@SuppressWarnings("unchecked")
	public DAO getDao() {
		return dao;
	}
	
	public void setObjetosPorPagina(int objetosPorPagina) {
		this.objetosPorPagina = objetosPorPagina;
	}
	
	public int getObjetosPorPagina() {
		return objetosPorPagina;
	}
	
	public ListaPaginacao paginar(int numeroPaginaAtual, Filtro... filtros) {
		int total = getTotalDados(filtros);
		
		numeroPaginaAtual = verificarNumeroPagina(numeroPaginaAtual, total);
		int indicePrimeiro = getIndicePrimeiro(numeroPaginaAtual, total);
		int indiceUltimo = getIndiceUltimo(numeroPaginaAtual, total);

		return criarListaPaginacao(getDados(indicePrimeiro, indiceUltimo, filtros),
								   total,
								   numeroPaginaAtual);
	}
	
	@SuppressWarnings("unchecked")
	public ListaPaginacao paginar(PaginadorCallback callback, int paginaAtual, Filtro... filtros) {
		int total = callback.getTotalDados(filtros);
		
		int numeroPaginaAtual = verificarNumeroPagina(paginaAtual, total);
		int indicePrimeiro = getIndicePrimeiro(numeroPaginaAtual, total);
		int indiceUltimo = getIndiceUltimo(numeroPaginaAtual, total);
		List dados = callback.getDados(indicePrimeiro, indiceUltimo - indicePrimeiro, filtros);
		
		return criarListaPaginacao(dados, total, numeroPaginaAtual);
	}
	
	@SuppressWarnings("unchecked")
	public ListaPaginacao paginarCollection(int paginaAtual, Collection collection) {
		int total = collection.size();
		int indicePrimeiro = getIndicePrimeiro(paginaAtual, total);
		int indiceUltimo = getIndiceUltimo(paginaAtual, total);

		List subCollection = new ArrayList(indiceUltimo - indicePrimeiro);
		int contador = 0;
		for (Object obj : collection) {
			if (contador >= indicePrimeiro && contador < indiceUltimo) {
				subCollection.add(obj);
			}
			contador++;
		}
		
		return criarListaPaginacao(subCollection,
								   total,
								   paginaAtual);
	}
	
    public int getIndicePrimeiro(int numeroPagina, int total) {
    	// Redundante pois esse m�todo pode ser chamado de fora
    	numeroPagina = verificarNumeroPagina(numeroPagina, total);

    	return (numeroPagina - 1) * getObjetosPorPagina();
    }

    public int getIndiceUltimo(int numeroPagina, int total) {
        int resultado = getIndicePrimeiro(numeroPagina, total) + getObjetosPorPagina();
        if (resultado >= total) {
            resultado = total;
        }
        if (resultado < 0) {
        	resultado = 0;
        }
        return resultado;
    }

    public int getTotalPaginas(int total) {
        int totalPaginas = total / Math.max(1, getObjetosPorPagina());
        if (total % getObjetosPorPagina() > 0) {
            totalPaginas++;
        }
        if (totalPaginas == 0) {
        	totalPaginas = 1;
        }
        return totalPaginas;
    }

    @SuppressWarnings("unchecked")
	public ListaPaginacao criarListaPaginacao(List lista,
    										  int total,
    										  int numeroPaginaAtual) {
    	return new ListaPaginacao(lista, getObjetosPorPagina(),
    							  total, numeroPaginaAtual);
    }

    protected int verificarNumeroPagina(int numeroPagina, int total) {
		if (numeroPagina > getTotalPaginas(total)) {
			numeroPagina = getTotalPaginas(total);
		}
		return numeroPagina;
    }

    /**
     * Obt�m o n�mero de total dos objetos sendo paginados.
     * 
     * @param filtros Filtros a serem usados.
     * @return N�mero total de objetos sendo paginados.
     */
    protected int getTotalDados(Filtro... filtros) {
		return getDao().recuperarTotal(filtros).intValue();
    }
    
    /**
     * Obt�m os dados de uma p�gina.
     * 
     * @param indicePrimeiro �ndice do primeiro item da p�gina.
     * @param indiceUltimo �ndice do �ltimo item da p�gina.
     * @param filtros Filtros a serem usados.
     * @return Lista com os objetos recuperados.
     */
    @SuppressWarnings("unchecked")
	protected List getDados(int indicePrimeiro, int indiceUltimo, Filtro... filtros) {
    	return getDao().listar(indicePrimeiro, indiceUltimo - indicePrimeiro, filtros);
    }
}