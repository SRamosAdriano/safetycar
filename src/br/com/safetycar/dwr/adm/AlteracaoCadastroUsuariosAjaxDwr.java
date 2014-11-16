package br.com.safetycar.dwr.adm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.Grupos;
import br.com.safetycar.modelos.Usuarios;
import br.com.safetycar.servico.dao.AlteracaoCadastroUsuarioDAO;
import br.com.safetycar.servico.dao.impl.AlteracaoCadastroUsuarioDAOImpl;

public class AlteracaoCadastroUsuariosAjaxDwr {
	
	private static Logger LOG = Logger.getLogger(AlteracaoCadastroUsuariosAjaxDwr.class.getName());
	
	public List<String> altUsuarios(Usuarios usuario, String[] idGrupos){		
		List<String> retorno = new ArrayList<String>();
		
		retorno = validaTudo(usuario, idGrupos);
		
		if(!retorno.get(0).equals("ERRO")){
			Connection connectionBD = new ConnectionFactory().getConnection();
			
			AlteracaoCadastroUsuarioDAO dao = new AlteracaoCadastroUsuarioDAOImpl(connectionBD);
			
			if(dao.existeLogin(usuario.getId(),usuario.getLogin())){
				retorno.add(0, "ERRO");
				retorno.add(1, "Login já existe. Digite outro login");
				return retorno;
			}							
			
			List<Grupos> grupos = new ArrayList<Grupos>();			
			for(int i = 0 ; i < idGrupos.length ; i++){				
				if(!idGrupos[i].equals("undefined")){
					Grupos gr = new Grupos();
					gr.setId(Long.valueOf(idGrupos[i]));
					grupos.add(gr);
				}				
			}
			usuario.setGrupos(grupos);
			
			/*Atualiza*/
			dao.atualizaUsuario(usuario);			
			retorno.add(1, "Usuário alterado com sucesso");								
			
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
	
	private List<String> validaTudo(Usuarios usuario, String[] idGrupos){
		List<String> retorno = new ArrayList<String>();
		
		if(usuario.getId() <= 0){
			retorno.add(0, "ERRO");
			retorno.add(1, "Usuário não localizado selecione o usuário novamente");
			return retorno;
		}else if(usuario.getNome() == null
				|| usuario.getNome().replaceAll(" ", "").length() == 0
				|| usuario.getNome().length() < 3){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Nome do Usuário");
			return retorno;
		}else if(usuario.getLogin() == null
				|| usuario.getLogin().replaceAll(" ", "").length() == 0
				|| usuario.getLogin().length() < 3){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Login do Usuário");
			return retorno;
		}else if (idGrupos == null
				|| idGrupos.length <= 0 
				|| idGrupos[0].equals("undefined")){
			retorno.add(0, "ERRO");
			retorno.add(1, "Selecione os Grupos que o usuário terá acesso");
			return retorno;
		}else{		
			retorno.add(0, "NAOERRO");
			retorno.add(1, "DADOS OK");
			return retorno;
		}
	}

}
