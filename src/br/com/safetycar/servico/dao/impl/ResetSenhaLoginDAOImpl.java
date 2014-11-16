package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;

import br.com.safetycar.dao.UsuariosDAO;
import br.com.safetycar.dao.impl.UsuariosDAOImpl;
import br.com.safetycar.modelos.Usuarios;
import br.com.safetycar.servico.dao.ResetSenhaLoginDAO;

public class ResetSenhaLoginDAOImpl implements ResetSenhaLoginDAO{
	
	private Connection cnn;
	
	public ResetSenhaLoginDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public void atualizaSenha(Usuarios usuario){
		if(usuario != null && usuario.getId() > 0){
			
			UsuariosDAO dao = new UsuariosDAOImpl(cnn);
			
			dao.atualizaSenhaUsuario(usuario);
		}
	}

}
