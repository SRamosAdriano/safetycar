package br.com.safetycar.dwr.adm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.Usuarios;
import br.com.safetycar.servico.dao.AlteracaoSenhaUsuarioDAO;
import br.com.safetycar.servico.dao.impl.AlteracaoSenhaUsuarioDAOImpl;

public class AlteracaoSenhaUsuarioAjaxDwr {
	
	private static Logger LOG = Logger.getLogger(AlteracaoSenhaUsuarioAjaxDwr.class.getName());
	
	public List<String> altSenhaUsuario(String senha, String senhaNova, String senhaNovaConf
			, HttpServletRequest request, HttpSession session){		
		
		List<String> retorno = new ArrayList<String>();
		
		retorno = validaTudo(senha, senhaNova, senhaNovaConf);
		
		if(!retorno.get(0).equals("ERRO")){
			
			senha = Usuarios.criptografarSenha(senha);
			senhaNova = Usuarios.criptografarSenha(senhaNova);
			senhaNovaConf = Usuarios.criptografarSenha(senhaNovaConf);
			
			Connection connectionBD = new ConnectionFactory().getConnection();
			
			AlteracaoSenhaUsuarioDAO dao = new AlteracaoSenhaUsuarioDAOImpl(connectionBD);
			
			Usuarios usuario = (Usuarios)request.getSession().getAttribute("usrLogado");
			
			if(dao.getErroLogin(usuario.getLogin()) >= 5){
				retorno.add(0, "ERRO");
				retorno.add(1, "Usuário Bloqueado");
				
			}else if(!dao.verificaSenhaLogin(usuario.getLogin(), senha)){
				retorno.add(0, "ERRO");
				retorno.add(1, "Senha atual invalida");
				
				dao.somaErroLogin(usuario.getLogin());				
				session.setAttribute("usrLogado", dao.pesquisaUsuario(usuario.getLogin()));				
				
			}else{				
				usuario.setSenha(senhaNova);
				usuario.setPrimeiroLogin(false);
				usuario.setErroLogin(0);
				
				/*Atualiza*/
				dao.atualizaSenha(usuario);		
				retorno.add(1, "Senha alterada com sucesso");		
			}									
			
			try {
				connectionBD.close();				
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}			
		}
		
		/* Retorno primeiro parametro (ERRO / NAOERRO) e depois  a MENSAGEM */
		return retorno;
	}
	
	private List<String> validaTudo(String senha, String senhaNova, String senhaNovaConf){
		List<String> retorno = new ArrayList<String>();
		
		if(senha == null
				|| senha.replaceAll(" ", "").length() == 0
				|| senha.length() <= 3){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite a senha do Usuário. A senha deve conter no minimo 4 caracteres");
			return retorno;
		}else if(senhaNova == null
				|| senhaNova.replaceAll(" ", "").length() == 0
				|| senhaNova.length() <= 3){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite a senha nova do Usuário.A senha nova deve conter no minimo 4 caracteres");
			return retorno;
		}else if(senhaNovaConf == null
				|| senhaNovaConf.replaceAll(" ", "").length() == 0
				|| senhaNovaConf.length() <= 3){
			retorno.add(0, "ERRO");
			retorno.add(1, "Confirme a senha nova do Usuário. A senha deve conter no minimo 4 caracteres");
			return retorno;
		}else if (!senhaNova.equals(senhaNovaConf)){
			retorno.add(0, "ERRO");
			retorno.add(1, "A senha nova diferente da confirmação da  nova senha");
			return retorno;
		}else{		
			retorno.add(0, "NAOERRO");
			retorno.add(1, "DADOS OK");
			return retorno;
		}
	}

}
