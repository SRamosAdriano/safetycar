package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;
import java.util.List;

import br.com.safetycar.dao.GruposDAO;
import br.com.safetycar.dao.PaginasDAO;
import br.com.safetycar.dao.impl.GruposDAOImpl;
import br.com.safetycar.dao.impl.PaginasDAOImpl;
import br.com.safetycar.modelos.Grupos;
import br.com.safetycar.modelos.Paginas;
import br.com.safetycar.servico.dao.CadastroGrupoDAO;

public class CadastroGrupoDAOImpl implements CadastroGrupoDAO{
	
	private Connection cnn;
	
	public CadastroGrupoDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public List<Paginas> getPaginasDisponiveis(){
		
		PaginasDAO dao = new PaginasDAOImpl(cnn);
		
		return dao.getPaginas();
	}
	
	public boolean existeGrupo(String nomeGrupo){
		
		GruposDAO dao = new GruposDAOImpl(cnn);
		
		return dao.existeGrupo(0, nomeGrupo);		
	}
	
	public void adicionaGrupo(Grupos grupo){
		
		GruposDAO dao = new GruposDAOImpl(cnn);
		
		dao.adicionaGrupo(grupo);		
	}

}
