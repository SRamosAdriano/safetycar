package br.com.safetycar.servico.dao;

import java.util.Calendar;
import java.util.List;

import br.com.safetycar.modelos.Grupos;
import br.com.safetycar.modelos.Paginas;

public interface AlteracaoCadastroGrupoDAO {
	
	public Grupos pesquisaGrupos(long id);
	
	public int countGrupos(String nomeGrupo, Calendar dataCadastro);
	
	public List<Grupos> pesquisaGrupos(String nomeGrupo, Calendar dataCadastro);
	
	public List<Grupos> pesquisaGrupos(String nomeGrupo, Calendar dataCadastro, int numeroPaginaAtual, int qtdePorPagina);
	
	public void removeGrupo(long idGrupo);
	
	public List<Paginas> getPaginasDisponiveis();
	
	public boolean existeGrupo(long idGrupo, String nomeGrupo);
	
	public void atualizaGrupo(Grupos grupo);

}
