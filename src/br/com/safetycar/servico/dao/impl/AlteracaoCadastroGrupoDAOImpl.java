package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import br.com.safetycar.dao.GruposDAO;
import br.com.safetycar.dao.PaginasDAO;
import br.com.safetycar.dao.impl.GruposDAOImpl;
import br.com.safetycar.dao.impl.PaginasDAOImpl;
import br.com.safetycar.modelos.Grupos;
import br.com.safetycar.modelos.Paginas;
import br.com.safetycar.servico.dao.AlteracaoCadastroGrupoDAO;

public class AlteracaoCadastroGrupoDAOImpl implements AlteracaoCadastroGrupoDAO{
	
	
	private Connection cnn;

	
	public AlteracaoCadastroGrupoDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public int countGrupos(String nomeGrupo, Calendar dataCadastro){
		
		GruposDAO dao = new GruposDAOImpl(cnn);
		
		return dao.countGrupos(0, nomeGrupo, dataCadastro);
	}
	
	public Grupos pesquisaGrupos(long id){
		
		GruposDAO dao = new GruposDAOImpl(cnn);
		
		List<Grupos> grupo = dao.pesquisaGrupos(id, null, null);
		
		return grupo.size() == 0 ? null : grupo.get(0);
	}
	
	public List<Grupos> pesquisaGrupos(String nomeGrupo, Calendar dataCadastro){
		
		GruposDAO dao = new GruposDAOImpl(cnn);
		
		return dao.pesquisaGrupos(0, nomeGrupo, dataCadastro);
	}
	
	public List<Grupos> pesquisaGrupos(String nomeGrupo, Calendar dataCadastro, int numeroPaginaAtual, int qtdePorPagina){
		
		GruposDAO dao = new GruposDAOImpl(cnn);
		
		return dao.pesquisaGrupos(0, nomeGrupo, dataCadastro, numeroPaginaAtual, qtdePorPagina);
	}
	
	public void removeGrupo(long idGrupo){
		if(idGrupo > 0){
			GruposDAO dao = new GruposDAOImpl(cnn);
			dao.removeGrupo(idGrupo);
		}
	}
	
	public List<Paginas> getPaginasDisponiveis(){
		
		PaginasDAO dao = new PaginasDAOImpl(cnn);
		
		return dao.getPaginas();
	}
	
	public boolean existeGrupo(long idGrupo, String nomeGrupo){
		
		GruposDAO dao = new GruposDAOImpl(cnn);
		
		return dao.existeGrupo(idGrupo, nomeGrupo);		
	}
	
	public void atualizaGrupo(Grupos grupo){
		
		if(grupo != null && grupo.getPaginas() != null){			
			GruposDAO dao = new GruposDAOImpl(cnn);			
			dao.atualizaGrupo(grupo);
		}		
	}

}
