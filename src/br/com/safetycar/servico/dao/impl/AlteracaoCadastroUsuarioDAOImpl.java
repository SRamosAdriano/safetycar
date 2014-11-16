package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import br.com.safetycar.dao.GruposDAO;
import br.com.safetycar.dao.UsuariosDAO;
import br.com.safetycar.dao.impl.GruposDAOImpl;
import br.com.safetycar.dao.impl.UsuariosDAOImpl;
import br.com.safetycar.modelos.Grupos;
import br.com.safetycar.modelos.Usuarios;
import br.com.safetycar.servico.dao.AlteracaoCadastroUsuarioDAO;

public class AlteracaoCadastroUsuarioDAOImpl implements AlteracaoCadastroUsuarioDAO{
	
	private Connection cnn;

	
	public AlteracaoCadastroUsuarioDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public Usuarios pesquisaUsuario(long id){
		
		UsuariosDAO dao = new UsuariosDAOImpl(cnn);
		
		List<Usuarios> usuario = dao.pesquisaUsuarios(id, null, null, null);
		
		return usuario.size() == 0 ? null : usuario.get(0);
	}
	
	public List<Usuarios> pesquisaUsuarios(String nomeUsuario, String loginUsuario, Calendar dataCadastro){
		
		UsuariosDAO dao = new UsuariosDAOImpl(cnn);
		
		return dao.pesquisaUsuarios(0, nomeUsuario, loginUsuario, dataCadastro);
	}
	
	public List<Usuarios> pesquisaUsuarios(String nomeUsuario, String loginUsuario, Calendar dataCadastro, int numeroPaginaAtual, int qtdePorPagina){
		
		UsuariosDAO dao = new UsuariosDAOImpl(cnn);
		
		return dao.pesquisaUsuarios(0, nomeUsuario, loginUsuario, dataCadastro, numeroPaginaAtual, qtdePorPagina);
	}
	
	public int countUsuarios(String nomeUsuario, String loginUsuario, Calendar dataCadastro){
		
		UsuariosDAO dao = new UsuariosDAOImpl(cnn);
		
		return dao.countUsuarios(0, nomeUsuario, loginUsuario, dataCadastro);
	}
	
	public List<Grupos> getGruposDisponiveis(){
		
		GruposDAO dao = new GruposDAOImpl(cnn);
		
		return dao.pesquisaGrupos(0, null, null);
	}
	
	public void removeUsuario(long idUsuario){
		if(idUsuario > 0){
			UsuariosDAO dao = new UsuariosDAOImpl(cnn);
			
			dao.removeUsuario(idUsuario);
		}
	}
	
	public void atualizaUsuario(Usuarios usuario){
		if(usuario != null && usuario.getId() > 0){
			UsuariosDAO dao = new UsuariosDAOImpl(cnn);
			
			dao.atualizaUsuario(usuario);
		}
	}
	
	public boolean existeLogin(long idUsuario, String login){
		
		UsuariosDAO dao = new UsuariosDAOImpl(cnn);
		
		return dao.existeUsuario(idUsuario, null, login);
	}
	

}
