package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import br.com.safetycar.dao.UsuariosDAO;
import br.com.safetycar.dao.impl.UsuariosDAOImpl;
import br.com.safetycar.modelos.Usuarios;
import br.com.safetycar.servico.dao.LoginDAO;

public class LoginDAOImpl implements LoginDAO{
	
	private Connection cnn;
	
	public LoginDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public boolean existeUsuario(String loginUsuario){
		if(loginUsuario != null && !loginUsuario.equals("")){
			
			UsuariosDAO dao = new UsuariosDAOImpl(cnn);
			
			return dao.existeUsuario(0, null, loginUsuario);
			
		}else{
			return false;
		}
	}
	
	public boolean verificaSenhaLogin(String loginUsuario, String senhaUsuario){
		if(loginUsuario != null && !loginUsuario.equals("") && loginUsuario.length() >= 4
				&& senhaUsuario != null && !senhaUsuario.equals("") && senhaUsuario.length() >= 4){
			
			UsuariosDAO dao = new UsuariosDAOImpl(cnn);
			
			return dao.verificaSenhaUsuario(loginUsuario, senhaUsuario);
			
		}else{
			return false;
		}
	}
	
	public Long getErroLogin(String loginUsuario){
		if(loginUsuario != null && !loginUsuario.equals("")){
			UsuariosDAO dao = new UsuariosDAOImpl(cnn);
			
			return dao.getErroLoginUsuario(loginUsuario);
		}
		return -1l;
	}
	
	public boolean getPrimeiroLogin(String loginUsuario){
		UsuariosDAO dao = new UsuariosDAOImpl(cnn);
		
		return dao.getPrimeiroLoginUsuario(loginUsuario);		
	}
	
	public Usuarios pesquisaUsuario(String login){
		if(login != null && !login.equals("")){
			UsuariosDAO dao = new UsuariosDAOImpl(cnn);
			
			List<Usuarios> usuarios = dao.pesquisaUsuarios(0, null, login, null);
			
			return usuarios.size() == 0 ? null : usuarios.get(0);
		}
		return null;
	}
	
	public void usuarioLogado(String loginUsuario){
		if(loginUsuario != null && !loginUsuario.equals("")){
			UsuariosDAO dao = new UsuariosDAOImpl(cnn);
			
			Calendar dataUltimoLogin = Calendar.getInstance();
			dao.atualizaDtUltimoLoginEErroLoginUsuario(loginUsuario, dataUltimoLogin, 0);
		}
	}
	
	public void somaErroLogin(String loginUsuario){
		if(loginUsuario != null && !loginUsuario.equals("")){
			UsuariosDAO dao = new UsuariosDAOImpl(cnn);
			
			dao.atualizaErroLoginUsuario(loginUsuario);
		}
	}
	
	public void atualizaSenha(Usuarios usuario){
		if(usuario != null){
			UsuariosDAO dao = new UsuariosDAOImpl(cnn);
			dao.atualizaSenhaUsuario(usuario);
		}
		
	}

}
