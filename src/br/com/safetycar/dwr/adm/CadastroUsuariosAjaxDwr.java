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
import br.com.safetycar.modelos.Usuarios;
import br.com.safetycar.servico.dao.CadastroUsuariosDAO;
import br.com.safetycar.servico.dao.impl.CadastroUsuariosDAOImpl;

public class CadastroUsuariosAjaxDwr {
	
	private static Logger LOG = Logger.getLogger(CadastroUsuariosAjaxDwr.class.getName());
	
	public List<String> addUsuarios(Usuarios usuario, String dataCadastro, String[] idGrupos){		
		List<String> retorno = new ArrayList<String>();
				
		retorno = validaTudo(usuario, dataCadastro, idGrupos);
		
		if(!retorno.get(0).equals("ERRO")){
			Connection connectionBD = new ConnectionFactory().getConnection();
			
			CadastroUsuariosDAO dao = new CadastroUsuariosDAOImpl(connectionBD);
						
			try {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(format.parse(dataCadastro));
				usuario.setDataCadastro(calendar);								
			} catch (ParseException e) {
				LOG.error(e.getMessage(), e.getCause());
				e.printStackTrace();
			}			
			
			if(dao.existeLogin(usuario.getLogin())){
				retorno.add(0, "ERRO");
				retorno.add(1, "Login já cadastrado no sistema.");
				return retorno;
			}			
			
			String senha = Usuarios.gerarSenha();
			usuario.setSenha(Usuarios.criptografarSenha(senha));
			usuario.setPrimeiroLogin(true);
			
			
			List<Grupos> grupos = new ArrayList<Grupos>();			
			for(int i = 0 ; i < idGrupos.length ; i++){				
				if(!idGrupos[i].equals("undefined")){
					Grupos gr = new Grupos();
					gr.setId(Long.valueOf(idGrupos[i]));
					grupos.add(gr);
				}				
			}
			usuario.setGrupos(grupos);			
			
			/*Adiciona*/
			dao.adicionaUsuario(usuario);
			retorno.add(1, "Usuário cadastrado com sucesso!");
			retorno.add(2, "Senha do login " + usuario.getLogin() + ": <b>" + senha + "</b>");
			
			try {
				connectionBD.close();				
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}			
		}
		
		return retorno;
	}
	
	private List<String> validaTudo(Usuarios usuario, String dataCadastro, String[] idGrupos){
		List<String> retorno = new ArrayList<String>();
		
		if(usuario == null
				|| usuario.getNome().replaceAll(" ", "").length() == 0
				|| usuario.getNome().length() < 3){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Nome do Usuário. O nome deve conter 3 caracteres ou mais.");
			return retorno;
		}else if(usuario == null
				|| usuario.getLogin().replaceAll(" ", "").length() == 0
				|| usuario.getLogin().length() < 4){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Login do Usuário. O login deve conter 4 caracteres ou mais.");
			return retorno;			
		}else if(dataCadastro == null
				|| dataCadastro.equals("")
				|| dataCadastro.length() < 10 
				|| dataCadastro.length() > 10){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite a data de cadastro");
			return retorno;
		}else if (idGrupos == null
				|| idGrupos.length <= 0 
				|| idGrupos[0].equals("undefined")){
			retorno.add(0, "ERRO");
			retorno.add(1, "Selecione os grupos que o usuário terá acesso");
			return retorno;
		}else {	
			retorno.add(0, "NAOERRO");
			retorno.add(1, "DADOS OK");
			return retorno;
		}
	}

}
