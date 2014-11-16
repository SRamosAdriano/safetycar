package br.com.safetycar.dwr.adm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.Usuarios;
import br.com.safetycar.servico.dao.ResetSenhaLoginDAO;
import br.com.safetycar.servico.dao.impl.ResetSenhaLoginDAOImpl;

public class ResetSenhaLoginsAjaxDwr {
	
	private static Logger LOG = Logger.getLogger(ResetSenhaLoginsAjaxDwr.class.getName());
	
	public List<String> resetSenhaLogin(Usuarios usuario){		
		List<String> retorno = new ArrayList<String>();	
				
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		ResetSenhaLoginDAO dao = new ResetSenhaLoginDAOImpl(connectionBD);
					
		String senha = Usuarios.gerarSenha();
		usuario.setSenha(Usuarios.criptografarSenha(senha));
		usuario.setPrimeiroLogin(true);
		usuario.setErroLogin(0);		
								
		/*Atualiza*/
		dao.atualizaSenha(usuario);
		retorno.add(0, "Senha alterada com sucesso!");
		retorno.add(1, "Senha do login " + usuario.getLogin() + ": <b>" + senha + "</b>");
		
		try {
			connectionBD.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return retorno;
	}

}
