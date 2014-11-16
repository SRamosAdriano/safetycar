package br.com.safetycar.utils;

public class PaginacaoUtils {
	
	public int parametroLimit(int numeroPaginaAtual, int qdtPorPagina){
		if(numeroPaginaAtual > 0 && qdtPorPagina > 0){
			return (numeroPaginaAtual * qdtPorPagina) - qdtPorPagina;
		}else{
			return 0;
		}		
	}

}
