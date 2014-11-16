package br.com.safetycar.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.dao.GruposDAO;
import br.com.safetycar.dao.UsuariosDAO;
import br.com.safetycar.modelos.Grupos;
import br.com.safetycar.modelos.Usuarios;
import br.com.safetycar.utils.PaginacaoUtils;

public class UsuariosDAOImpl extends PaginacaoUtils implements UsuariosDAO{
	
	private static Logger LOG = Logger.getLogger(UsuariosDAOImpl.class.getName());
	
	private Connection cnn;
	
	public UsuariosDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public int countUsuarios(long id, String nomeUsuario, String loginUsuario, Calendar dataCadastro){
				
		try {			
			String strSql = "select count(1) as totalPaginas " +
					        "  from usuarios " +
					        " where ativo = true ";
			
			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(nomeUsuario != null && !nomeUsuario.equals("")){
				strSql = strSql + " and nome = '" + nomeUsuario.trim() + "' ";
			}
			if(loginUsuario != null && !loginUsuario.equals("")){
				strSql = strSql + " and login = '" + loginUsuario.trim() + "' ";
			}			
			if(dataCadastro != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				strSql = strSql + " and dataCadastro = '" + sdf.format(dataCadastro.getTime()).trim() + "' ";
			}
						
							
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			ResultSet rs = stmt.executeQuery();
			
			int totalPaginas = 0;
			while (rs.next()) {
				totalPaginas = rs.getInt("totalPaginas");
			}		
			
			stmt.close();

			return totalPaginas;
			
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}
	}
	
	public List<Usuarios> pesquisaUsuarios(long id, String nomeUsuario, String loginUsuario, Calendar dataCadastro, int paginaAtual,int qtdePorPagina){
		
		return pesquisaUsuario(id, nomeUsuario, loginUsuario, dataCadastro, paginaAtual, qtdePorPagina);
	}	
	
	public List<Usuarios> pesquisaUsuarios(long id, String nomeUsuario, String loginUsuario, Calendar dataCadastro){		
		
		return pesquisaUsuario(id, nomeUsuario, loginUsuario, dataCadastro, 0, 0);
	}	
	
	private List<Usuarios> pesquisaUsuario(long id, String nomeUsuario, String loginUsuario, Calendar dataCadastro, int paginaAtual, int qtdePorPagina){
		
		List<Usuarios> usuarios = new ArrayList<Usuarios>();		
		
		try {			
			String strSql = "select id, nome, login, primeiroLogin, dataCadastro, dataUltimoLogin, erroLogin " +
					        "  from usuarios " +
					        " where ativo = true ";
			
			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(nomeUsuario != null && !nomeUsuario.equals("")){
				strSql = strSql + " and nome = '" + nomeUsuario.trim() + "' ";
			}
			if(loginUsuario != null && !loginUsuario.equals("")){
				strSql = strSql + " and login = '" + loginUsuario.trim() + "' ";
			}			
			if(dataCadastro != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				strSql = strSql + " and dataCadastro = '" + sdf.format(dataCadastro.getTime()).trim() + "' ";
			}
			
			strSql = strSql + " order by nome, login, dataCadastro ";
			
			if(paginaAtual > 0 && qtdePorPagina > 0){
				strSql = strSql + " LIMIT "+ parametroLimit(paginaAtual, qtdePorPagina) +", "+ qtdePorPagina;
			}
							
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Usuarios usuario = new Usuarios();
				
				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(null);
				usuario.setErroLogin(rs.getInt("erroLogin"));
				
				if(rs.getInt("primeiroLogin") == 1){
					usuario.setPrimeiroLogin(true);
				}					
				
				Date data = rs.getDate("dataCadastro");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(data);
				usuario.setDataCadastro(calendar);
				
				data = rs.getDate("dataUltimoLogin");
				calendar = Calendar.getInstance();
				if(data != null){
					calendar.setTime(data);
					usuario.setDataUltimoLogin(calendar);
				}
				
				usuarios.add(usuario);
			}
			
			for (Usuarios usuario : usuarios) {
				
				List<Grupos> grupos = new ArrayList<Grupos>();				
				strSql = "select grupo_id " +
		                 "  from associado_grupos_usuarios " +
		                 " where usuario_id = " + usuario.getId();
				
				stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				rs = stmt.executeQuery();
				
				while (rs.next()) {
					long idGrupo = rs.getLong("grupo_id");
					
					GruposDAO dao = new GruposDAOImpl(cnn);
					
					List<Grupos> grupo = dao.pesquisaGrupos(idGrupo,null,null);
					
					if(grupo != null && !grupo.isEmpty() && grupo.get(0).getId() > 0){
						grupos.add(grupo.get(0));
					}
				}
				usuario.setGrupos(grupos);				
			}			
			
			stmt.close();
			
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}
					
		return usuarios;
	}
	
	
	public boolean verificaSenhaUsuario(String loginUsuario, String senhaCripUsuario){
		
		if(loginUsuario == null || loginUsuario.length() < 4){
			return false;
		}else if(senhaCripUsuario == null || senhaCripUsuario.length() < 4){
			return false;
		}else{
			try {
				String strSql = "select id "
							  + "  from usuarios "
							  + " where ativo = true "
							  +	"   and  login = ?"
							  +	"   and  senha = ?";
				
				PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setString(1, loginUsuario);
				stmt.setString(2, senhaCripUsuario);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					stmt.close();
					return true;
				}

				stmt.close();
				return false;			
				
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}
		}		
	}
	
	public Long getErroLoginUsuario(String loginUsuario){
		if(loginUsuario == null || loginUsuario.length() < 4){
			return -1l;
		}else{
			try {
				String strSql = "select erroLogin "
							  + "  from usuarios "
							  + " where ativo = true "
							  +	"   and  login = ?";
				
				PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setString(1, loginUsuario);				
				ResultSet rs = stmt.executeQuery();				
				
				while (rs.next()) {					
					Long erro = rs.getLong("erroLogin");
					stmt.close();
					return erro;
				}
				
				stmt.close();
				return -1l;
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}
		}
	}
	
	public boolean getPrimeiroLoginUsuario(String loginUsuario){
		
		try {
			String strSql = "select primeiroLogin "
						  + "  from usuarios "
						  + " where ativo = true "
						  +	"   and  login = ?";
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setString(1, loginUsuario);				
			ResultSet rs = stmt.executeQuery();			
			
			while (rs.next()) {				
				int primeiro = rs.getInt("primeiroLogin");
				stmt.close();
				if(primeiro == 1){
					return true;
				}else if (primeiro == 0){
					return false;
				}
				
			}
			
			stmt.close();
			return false;
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		
	}
	
	public boolean existeUsuario(long id, String nome, String login){
		
		if(nome != null && nome.equals("")){
			nome = null;			
		}
		if(login != null && login.equals("")){
			login = null;			
		}
		
		try {
			String strSql = "select id"
					      + "  from usuarios "
						  + " where ativo = true ";						   
			
			if(id > 0){
				strSql = strSql + " and id <> " + id;
			}
			if(nome != null){
				strSql = strSql + " and nome = \"" + nome.trim() + "\"";
			}
			if(login != null){
				strSql = strSql + " and login = \"" + login.trim() + "\"";
			}
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);						
			
			ResultSet rs = stmt.executeQuery();	
			
			while (rs.next()) {
				stmt.close();
				return true;
			}

			stmt.close();
			return false;

		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	public void adicionaUsuario(Usuarios usuario){
		if(usuario != null && usuario.getGrupos() != null && usuario.getDataCadastro() != null 
				&& !usuario.getNome().equals("") && !usuario.getLogin().equals("") && !usuario.getSenha().equals("")){
			
			try {
				String strSql = "insert into usuarios (nome, login, senha, primeiroLogin" +
						"                              , dataCadastro, dataUltimoLogin, erroLogin, ativo) "
							  + "     values( ?, ?, ?, ?, ?, ?, ?, ? ) ";
				
				PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setString(1, usuario.getNome());
				stmt.setString(2, usuario.getLogin());
				stmt.setString(3, usuario.getSenha());
				stmt.setBoolean(4, usuario.getPrimeiroLogin());
				stmt.setDate(5, new java.sql.Date(usuario.getDataCadastro().getTimeInMillis()));
				stmt.setDate(6, null);
				stmt.setLong(7, usuario.getErroLogin());
				stmt.setBoolean(8, true);
				stmt.execute();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				strSql = "select id from usuarios " 
					   + "  where nome = '" + usuario.getNome().trim() + "' "
					   + " and login ='" + usuario.getLogin().trim() + "' "
					   + " and dataCadastro >='" + sdf.format(usuario.getDataCadastro().getTime()).trim() + "' "
				       + "    and ativo = true ";
				
				stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					usuario.setId(rs.getLong("id"));
				}
				
				for (Grupos grupo : usuario.getGrupos()) {
					strSql = "insert into associado_grupos_usuarios (usuario_id, grupo_id) "
						   + "     values( ?, ? ) ";
					stmt = (PreparedStatement) cnn.prepareStatement(strSql);
					stmt.setLong(1, usuario.getId());
					stmt.setLong(2, grupo.getId());
					stmt.execute();
				}
				
				stmt.close();
				
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}						
		}
	}
	
	public void atualizaUsuario(Usuarios usuario){
		if(usuario != null && usuario.getId() > 0 && usuario.getNome() != null && !usuario.getNome().equals("")
				&& usuario.getLogin() != null && !usuario.getLogin().equals("") && usuario.getGrupos() != null && !usuario.getGrupos().isEmpty()){
			try {
				String strSql = "update usuarios set nome = ?, login = ? "
					  		  + " where id = ? ";
		
				PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setString(1, usuario.getNome());
				stmt.setString(2, usuario.getLogin());
				stmt.setLong(3, usuario.getId());
				stmt.executeUpdate();
				
				strSql = "delete from associado_grupos_usuarios "
					   + "where usuario_id = ? ";
				
				stmt = (PreparedStatement) cnn.prepareStatement(strSql);				
				stmt.setLong(1, usuario.getId());
				stmt.executeUpdate();
				
				for (Grupos grupo : usuario.getGrupos()) {
					strSql = "insert into associado_grupos_usuarios (usuario_id, grupo_id) "
						   + "     values( ?, ? ) ";
					stmt = (PreparedStatement) cnn.prepareStatement(strSql);
					stmt.setLong(1, usuario.getId());
					stmt.setLong(2, grupo.getId());
					stmt.execute();
				}
				
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}
		}		
	}
	
	public void atualizaDtUltimoLoginEErroLoginUsuario(String loginUsuario, Calendar dataUltimoLogin, int erroLogin){
		
		if(loginUsuario != null && !loginUsuario.equals("") && dataUltimoLogin != null){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				String strSql = "update usuarios set dataUltimoLogin = '" + sdf.format(dataUltimoLogin.getTime()).trim() + "' "
				 			  + "                , erroLogin = " + erroLogin
					  		  + " where ativo = true "
					  		  + "   and login = ? ";
		
				PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setString(1, loginUsuario);
				stmt.executeUpdate();
				
				
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}
		}		
	}
	
	public void atualizaErroLoginUsuario(String loginUsuario){
		if(loginUsuario != null && !loginUsuario.equals("")){
			try {				
				String strSql = "update usuarios set usuarios.erroLogin = ( " +
						        "                                          select erro " +
						        "                                            from ( " +
						        "                                                  select u.erroLogin + 1 as erro " +
						        "                                                    from usuarios u " +
						        "                                                   where u.ativo = true " +
						        "                                                     and u.login = ? " +
						        "                                                 ) as sub " +
						        "                                         ) " +
						        " where usuarios.ativo = true " +
						        "   and usuarios.login = ? ";
				
				PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setString(1, loginUsuario);
				stmt.setString(2, loginUsuario);
				stmt.executeUpdate();
				
				
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}
		}
	}
	
	public void atualizaSenhaUsuario(Usuarios usuario){
		if(usuario != null && usuario.getId() > 0 && usuario.getNome() != null && !usuario.getNome().equals("")
				&& usuario.getLogin() != null && !usuario.getLogin().equals("")
				&& usuario.getSenha() != null && !usuario.getSenha().equals("")){
			try {
				String strSql = "update usuarios set senha = ?, primeiroLogin = ?, erroLogin = ? "
					  		  + " where id = ? "
					  		  + "   and login = ?"
					  		  + "   and nome = ? ";
		
				PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setString(1, usuario.getSenha());
				stmt.setBoolean(2, usuario.getPrimeiroLogin());
				stmt.setLong(3, usuario.getErroLogin());
				stmt.setLong(4, usuario.getId());
				stmt.setString(5, usuario.getLogin());
				stmt.setString(6, usuario.getNome());
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}
		}	
	}
	
	public void removeUsuario(long idUsuario){
		if(idUsuario > 0){
			try {
				String strSql = "update usuarios set ativo = false "
					  		  + " where id = ? ";
		
				PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setLong(1, idUsuario);
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}
		}
	}

}
