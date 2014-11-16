package br.com.safetycar.dwr.adm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.Grupos;
import br.com.safetycar.modelos.Paginas;
import br.com.safetycar.servico.dao.AlteracaoCadastroGrupoDAO;
import br.com.safetycar.servico.dao.impl.AlteracaoCadastroGrupoDAOImpl;

public class AlteracaoCadastroGrupoAjaxDwr {
	
	private static Logger LOG = Logger.getLogger(AlteracaoCadastroGrupoAjaxDwr.class.getName());
	
	public List<String> altGrupo(long idGrupo, String nomeGrupo, String[] idPaginas){		
		List<String> retorno = new ArrayList<String>();
		
		retorno = validaTudo(idGrupo, nomeGrupo, idPaginas);
		
		if(!retorno.get(0).equals("ERRO")){
			Connection connectionBD = new ConnectionFactory().getConnection();
			
			AlteracaoCadastroGrupoDAO dao = new AlteracaoCadastroGrupoDAOImpl(connectionBD);
			
			if(dao.existeGrupo(idGrupo,nomeGrupo)){
				retorno.add(0, "ERRO");
				retorno.add(1, "Grupo já existe. Digite outro nome do Grupo");
				return retorno;
			}
											
			Grupos grupo = new Grupos();
			
			grupo.setId(idGrupo);
			
			grupo.setNome(nomeGrupo);			
			
			List<Paginas> paginas = new ArrayList<Paginas>();			
			for(int i = 0 ; i < idPaginas.length ; i++){				
				if(!idPaginas[i].equals("undefined")){
					Paginas pg = new Paginas();
					pg.setId(Long.valueOf(idPaginas[i]));
					paginas.add(pg);
				}				
			}
			grupo.setPaginas(paginas);
			
			/*Atualiza*/
			dao.atualizaGrupo(grupo);			
			retorno.add(1, "Grupo alterado com sucesso");								
			
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
	
	private List<String> validaTudo(long idGrupo, String nomeGrupo, String[] idPaginas){
		List<String> retorno = new ArrayList<String>();
		
		if(idGrupo <= 0){
			retorno.add(0, "ERRO");
			retorno.add(1, "Grupo não localizado selecione o grupo novamente");
			return retorno;
		}else if(nomeGrupo == null
				|| nomeGrupo.replaceAll(" ", "").length() == 0
				|| nomeGrupo.length() < 3){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Nome do Grupo");
			return retorno;
		}else if (idPaginas == null
				|| idPaginas.length <= 0 
				|| idPaginas[0].equals("undefined")){
			retorno.add(0, "ERRO");
			retorno.add(1, "Selecione as páginas que o grupo terá acesso");
			return retorno;
		}else{		
			retorno.add(0, "NAOERRO");
			retorno.add(1, "DADOS OK");
			return retorno;
		}
	}

}
