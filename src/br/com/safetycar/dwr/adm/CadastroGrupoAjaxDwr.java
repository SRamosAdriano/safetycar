package br.com.safetycar.dwr.adm;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.Grupos;
import br.com.safetycar.modelos.Paginas;
import br.com.safetycar.servico.dao.CadastroGrupoDAO;
import br.com.safetycar.servico.dao.impl.CadastroGrupoDAOImpl;

public class CadastroGrupoAjaxDwr {
	
	private static Logger LOG = Logger.getLogger(CadastroGrupoAjaxDwr.class.getName());
	
	public List<String> addGrupo(String nomeGrupo, String dataCadastro, String[] idPaginas){		
		List<String> retorno = new ArrayList<String>();
				
		retorno = validaTudo(nomeGrupo, dataCadastro, idPaginas);
		
		if(!retorno.get(0).equals("ERRO")){
			Connection connectionBD = new ConnectionFactory().getConnection();
			
			CadastroGrupoDAO dao = new CadastroGrupoDAOImpl(connectionBD);
			
			if(dao.existeGrupo(nomeGrupo)){
				retorno.add(0, "ERRO");
				retorno.add(1, "Grupo já existe. Digite outro nome do Grupo");
				return retorno;
			}
											
			Grupos grupo = new Grupos();
			
			grupo.setNome(nomeGrupo);
			
			try {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(format.parse(dataCadastro));
				grupo.setDataCadastro(calendar);								
			} catch (ParseException e) {
				LOG.error(e.getMessage(), e.getCause());
				e.printStackTrace();
			}
			
			List<Paginas> paginas = new ArrayList<Paginas>();			
			for(int i = 0 ; i < idPaginas.length ; i++){				
				if(!idPaginas[i].equals("undefined")){
					Paginas pg = new Paginas();
					pg.setId(Long.valueOf(idPaginas[i]));
					paginas.add(pg);
				}				
			}
			grupo.setPaginas(paginas);
			
			/*Adiciona*/
			dao.adicionaGrupo(grupo);
			retorno.add(1, "Grupo cadastrado com sucesso");								
			
			try {
				connectionBD.close();				
			} catch (SQLException e) {	
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}			
		}
		
		return retorno;
	}
	
	private List<String> validaTudo(String nomeGrupo, String dataCadastro, String[] idPaginas){
		List<String> retorno = new ArrayList<String>();
		
		if(nomeGrupo == null
				|| nomeGrupo.replaceAll(" ", "").length() == 0
				|| nomeGrupo.length() < 3){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Nome do Grupo");
			return retorno;
		}else if(dataCadastro == null
				|| dataCadastro.equals("")
				|| dataCadastro.length() < 10 
				|| dataCadastro.length() > 10){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite a data de cadastro");
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
