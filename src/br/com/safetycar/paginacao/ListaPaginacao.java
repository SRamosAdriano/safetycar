package br.com.safetycar.paginacao;

import java.io.*;
import java.util.*;

/**
 * Resultado do processamento do <code>br.com.intelignet.controle.paginacao.Paginador</code>.
 * Possui informações sobre o número da página e os objetos nela.
 */
public class ListaPaginacao implements Serializable {
	
	private static final long serialVersionUID = 1289776492148954999L;
	
	// Valores default
	public static final int NUMERO_INDICES_DEFAULT = 10;
	public static final int TOTAL_POR_PAGINA_DEFAULT = 10;
	
	/**
	 * Objetos presentes na página.
	 */
	@SuppressWarnings("unchecked")
	private List itens;
	
	/**
	 * Máximo de objetos por página.
	 */
	private int totalPorPagina;
	
	/**
	 * Número de objetos na página atual.
	 */
	private int total;
	
	/**
	 * Número da página atual.
	 */
	private int numeroPagina;
	
	/**
	 * Número de páginas disponíveis no índice.
	 */
	private int numeroIndices;
	
	@SuppressWarnings("unchecked")
	public ListaPaginacao(List itens) {
		this(itens, TOTAL_POR_PAGINA_DEFAULT, (itens != null) ? itens.size() : 0, 1);
	}
	
	@SuppressWarnings("unchecked")
	public ListaPaginacao(List itens,
						  int totalPorPagina,
						  int total,
						  int numeroPagina) {
		this.itens = itens;
		this.totalPorPagina = totalPorPagina;
		this.total = total;
		this.numeroPagina = numeroPagina;
		this.numeroIndices = NUMERO_INDICES_DEFAULT;
		
		if (this.numeroPagina < 1) {
			this.numeroPagina = 1;
		}
		if (this.numeroPagina > getTotalPaginas()) {
			this.numeroPagina = getTotalPaginas();
		}
	}

	@SuppressWarnings("unchecked")
	public List getItens() {
		return itens;
	}

	public int getNumeroPagina() {
		return numeroPagina;
	}

	public int getTotal() {
		return total;
	}

	public int getTotalPorPagina() {
		return totalPorPagina;
	}
	
	public int getNumeroIndices() {
		return numeroIndices;
	}
	
	public boolean isVazia() {
		return getTotal() == 0;
	}
	
	public boolean isPaginaAnteriorValida() {
		return getNumeroPagina() > 1;
	}
	
	public boolean isProximaPaginaValida() {
		return getNumeroPagina() < getTotalPaginas();
	}
	
	/**
	 * Obtém o total de páginas.
	 * 
	 * @return
	 */
	public int getTotalPaginas() {
		int totalPaginas = getTotal() / getTotalPorPagina();
		if (getTotal() % getTotalPorPagina() > 0) {
			totalPaginas++;
		}
		if (totalPaginas == 0) {
			totalPaginas = 1;
		}
		return totalPaginas;
	}
	
	/**
	 * Obtém o índice do primeiro item sendo exibido em relação a lista inteira.
	 * 
	 * @return
	 */
	public int getPosicaoInicial() {
		int posicaoInicial = getNumeroPagina() * getTotalPorPagina() - getTotalPorPagina() + 1;
		
		return posicaoInicial;
	}
	
	/**
	 * Obtém o índice do último item sendo exibido em relação a lista inteira.
	 * 
	 * @return
	 */
	public int getPosicaoFinal() {
		int posicaoFinal = getNumeroPagina() * getTotalPorPagina();
		posicaoFinal = (posicaoFinal > getTotal()) ? getTotal() : posicaoFinal;
		
		return posicaoFinal;
	}

	/**
	 * Obtém o número da página do primeiro item do índice.
	 *  
	 * @return
	 */
	public int getNumeroPaginaInicial() {
		int indiceInicial = getNumeroPagina() - (getNumeroIndices() / 2);
		indiceInicial = (indiceInicial <= 0) ? 1 : indiceInicial;
		indiceInicial = (indiceInicial + getNumeroIndices() > getTotalPaginas()) ? getTotalPaginas() - getNumeroIndices() + 1 : indiceInicial;
		indiceInicial = (indiceInicial <= 0) ? 1 : indiceInicial;

		return indiceInicial;
	}
	
	/**
	 * Obtém o número da página do último item do índice.
	 *  
	 * @return
	 */
	public int getNumeroPaginaFinal() {
		int indiceFinal = getNumeroPaginaInicial() + getNumeroIndices() - 1;
		indiceFinal = (indiceFinal > getTotalPaginas()) ? getTotalPaginas() : indiceFinal;

		return indiceFinal;
	}
}