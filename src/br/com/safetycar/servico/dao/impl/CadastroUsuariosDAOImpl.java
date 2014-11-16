package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;
import java.util.List;

import br.com.safetycar.dao.GruposDAO;
import br.com.safetycar.dao.UsuariosDAO;
import br.com.safetycar.dao.impl.GruposDAOImpl;
import br.com.safetycar.dao.impl.UsuariosDAOImpl;
import br.com.safetycar.modelos.Grupos;
import br.com.safetycar.modelos.Usuarios;
import br.com.safetycar.servico.dao.CadastroUsuariosDAO;

public class CadastroUsuariosDAOImpl implements CadastroUsuariosDAO{
	
	private Connection cnn;
	
	public CadastroUsuariosDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public boolean existeLogin(String login){
		
		UsuariosDAO dao = new UsuariosDAOImpl(cnn);
		
		return dao.existeUsuario(0, null, login);
	}
	
	public List<Grupos> getGrupos(){
		
		GruposDAO dao = new GruposDAOImpl(cnn);
		
		return dao.pesquisaGrupos(0, null, null);
	}
	
	public void adicionaUsuario(Usuarios usuario){
		if(usuario != null && usuario.getGrupos() != null){
			
			UsuariosDAO dao = new UsuariosDAOImpl(cnn);
			
			dao.adicionaUsuario(usuario);
		}		
	}

}
