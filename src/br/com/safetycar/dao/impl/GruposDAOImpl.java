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
import br.com.safetycar.dao.PaginasDAO;
import br.com.safetycar.modelos.Grupos;
import br.com.safetycar.modelos.Paginas;
import br.com.safetycar.utils.PaginacaoUtils;

public class GruposDAOImpl extends PaginacaoUtils implements GruposDAO{
	
	private static Logger LOG = Logger.getLogger(GruposDAOImpl.class.getName());
	
	private Connection cnn;
	
	public GruposDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public int countGrupos(long id, String nomeGrupo, Calendar dataCadastro){
		
		try {			
			String strSql = "select count(1) as totalPaginas " +
					        "  from grupos " +
					        " where ativo = 'true' ";
			
			if(nomeGrupo != null && !nomeGrupo.equals("")){
				strSql = strSql + " and nome = '" + nomeGrupo.trim() + "' ";
			}
			if(id > 0){
				strSql = strSql + " and id = " + id;
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
		
	public List<Grupos> pesquisaGrupos(long id, String nomeGrupo, Calendar dataCadastro, int numeroPaginaAtual, int qtdePorPagina){
		
		return pesqGrupo(id, nomeGrupo, dataCadastro, numeroPaginaAtual, qtdePorPagina);
	}
	
	public List<Grupos> pesquisaGrupos(long id, String nomeGrupo, Calendar dataCadastro){
				
		return pesqGrupo(id,nomeGrupo, dataCadastro, 0, 0);
	}
	
	private List<Grupos> pesqGrupo(long id, String nomeGrupo, Calendar dataCadastro, int numeroPaginaAtual, int qtdePorPagina){
		
		List<Grupos> grupos = new ArrayList<Grupos>();
		
		try {			
			String strSql = "select id,nome,dataCadastro " +
					        "  from grupos " +
					        " where ativo = 'true' ";
			
			if(nomeGrupo != null && !nomeGrupo.equals("")){
				strSql = strSql + " and nome = '" + nomeGrupo.trim() + "' ";
			}
			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(dataCadastro != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				strSql = strSql + " and dataCadastro = '" + sdf.format(dataCadastro.getTime()).trim() + "' ";
			}
			
			strSql = strSql + " order by nome, dataCadastro ";
			
			if(numeroPaginaAtual > 0 && qtdePorPagina > 0){
				strSql = strSql + " LIMIT "+ parametroLimit(numeroPaginaAtual, qtdePorPagina) +", "+ qtdePorPagina;
			}
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Grupos grupo = new Grupos();
				grupo.setId(rs.getLong("id"));
				grupo.setNome(rs.getString("nome"));
				
				Date data = rs.getDate("dataCadastro");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(data);
				grupo.setDataCadastro(calendar);
				
				grupos.add(grupo);
			}
			
			for (Grupos grupo : grupos) {
				List<Paginas>paginas = new ArrayList<Paginas>();				
				strSql = "select pagina_id " +
		                 "  from associado_paginas_grupos " +
		                 " where grupo_id = " + grupo.getId();
				
				stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				rs = stmt.executeQuery();
				
				while (rs.next()) {
					long idPagina = rs.getLong("pagina_id");
					
					PaginasDAO dao = new PaginasDAOImpl(cnn);
					Paginas pagina = dao.pesquisaPagina(idPagina);
					
					if(pagina.getId() > 0){
						paginas.add(pagina);
					}
				}
				grupo.setPaginas(paginas);
			}				
			
			stmt.close();
		
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
			
		return grupos;
	}
	
	public boolean existeGrupo(long id, String nomeGrupo){
		
		if(nomeGrupo != null && nomeGrupo.equals("")){
			nomeGrupo = null;			
		}
		
		try {
			String strSql = "select id"
					      + "  from grupos "
						  + " where ativo = 'true' ";						   
			
			if(id > 0){
				strSql = strSql + " and id <> " + id;
			}
			if(nomeGrupo != null){
				strSql = strSql + " and nome = \"" + nomeGrupo.trim() + "\"";
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
	
	public void adicionaGrupo(Grupos grupo){		
		if(grupo != null && grupo.getPaginas() != null && grupo.getDataCadastro() != null 
				&& !grupo.getNome().equals("")){
			
			try {
				String strSql = "insert into grupos (nome, dataCadastro, ativo) "
							  + "     values( ?, ?, ? ) ";
				
				PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setString(1, grupo.getNome());
				stmt.setDate(2, new java.sql.Date(grupo.getDataCadastro().getTimeInMillis()));
				stmt.setString(3, "true");
				stmt.execute();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				strSql = "select id from grupos " 
					   + "  where nome = '" + grupo.getNome().trim() + "' "
					   + " and dataCadastro >='" + sdf.format(grupo.getDataCadastro().getTime()).trim() + "' "
				       + "    and ativo = 'true' ";
				
				stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					grupo.setId(rs.getLong("id"));
				}
				
				for (Paginas pagina : grupo.getPaginas()) {
					strSql = "insert into associado_paginas_grupos (grupo_id, pagina_id) "
						   + "     values( ?, ? ) ";
					stmt = (PreparedStatement) cnn.prepareStatement(strSql);
					stmt.setLong(1, grupo.getId());
					stmt.setLong(2, pagina.getId());
					stmt.execute();
				}
				
				stmt.close();
				
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}						
		}		
	}
	
	public void atualizaGrupo(Grupos grupo){
		if(grupo != null && grupo.getId() > 0 && grupo.getNome() != null && !grupo.getNome().equals("")
				&& grupo.getPaginas() != null && !grupo.getPaginas().isEmpty()){
			try {
				String strSql = "update grupos set nome = ? "
					  		  + " where id = ? ";
		
				PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setString(1, grupo.getNome());
				stmt.setLong(2, grupo.getId());
				stmt.executeUpdate();
				
				strSql = "delete from associado_paginas_grupos "
					   + "where grupo_id = ? ";
				
				stmt = (PreparedStatement) cnn.prepareStatement(strSql);				
				stmt.setLong(1, grupo.getId());
				stmt.executeUpdate();
				
				for (Paginas pagina : grupo.getPaginas()) {
					strSql = "insert into associado_paginas_grupos (grupo_id, pagina_id) "
						   + "     values( ?, ? ) ";
					stmt = (PreparedStatement) cnn.prepareStatement(strSql);
					stmt.setLong(1, grupo.getId());
					stmt.setLong(2, pagina.getId());
					stmt.execute();
				}
				
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}
		}
	}
	
	public void removeGrupo(long idGrupo){
		if(idGrupo > 0){
			try {
				String strSql = "update grupos set ativo = 'false' "
					  		  + " where id = ? ";
		
				PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setLong(1, idGrupo);
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}
		}		
	}
}
