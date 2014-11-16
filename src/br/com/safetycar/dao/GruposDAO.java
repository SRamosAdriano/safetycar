package br.com.safetycar.dao;

import java.util.Calendar;
import java.util.List;

import br.com.safetycar.modelos.Grupos;

public interface GruposDAO {
	
	public int countGrupos(long id, String nomeGrupo, Calendar dataCadastro);
	
	public List<Grupos> pesquisaGrupos(long id, String nomeGrupo, Calendar dataCadastro, int numeroPaginaAtual, int qtdePorPagina);
			
	public List<Grupos> pesquisaGrupos(long id, String nomeGrupo, Calendar dataCadastro);
	
	public boolean existeGrupo(long id, String nomeGrupo);
	
	public void adicionaGrupo(Grupos grupo);
	
	public void atualizaGrupo(Grupos grupo);
	
	public void removeGrupo(long idGrupo);
	
}
