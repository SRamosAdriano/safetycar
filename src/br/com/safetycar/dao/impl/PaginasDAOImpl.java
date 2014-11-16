package br.com.safetycar.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.dao.PaginasDAO;
import br.com.safetycar.modelos.Paginas;

public class PaginasDAOImpl implements PaginasDAO{
	
	private static Logger LOG = Logger.getLogger(PaginasDAOImpl.class.getName());
	
	private Connection cnn;
	
	public PaginasDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public Paginas pesquisaPagina(long id){
		
		Paginas pagina = pesquisaPagina(id, null).get(0);
		
		return pagina.getId() > 0 ? pagina : null;	
	}
	
	public List<Paginas> pesquisaPaginas(List<String> nomePagina){
		
		if(nomePagina != null && nomePagina.equals("")){
			nomePagina = null;
		}
		
		List<Paginas> paginas = new ArrayList<Paginas>();
		if(nomePagina != null){
			for (String pagina : nomePagina) {								
				Paginas pg = pesquisaPagina(0, pagina).get(0);				
				if(pg.getId() > 0){
					paginas.add(pg);
				}				
			}
		}
		
		return paginas;		
	}
	
	public List<Paginas> getPaginas(){
		
		return pesquisaPagina(0, null);
	}
	
	private List<Paginas> pesquisaPagina(long id, String nomePagina){
		
		List<Paginas> paginas = new ArrayList<Paginas>();
				
		try {			
			String strSql = "select id,nome,dataCadastro " +
					        "  from paginas " +
					        " where 1=1 ";
			
			if(nomePagina != null && !nomePagina.equals("")){
				strSql = strSql + " and nome = '" + nomePagina.trim() + "' ";
			}
			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			
			strSql = strSql + " order by nome, dataCadastro ";
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Paginas pagina = new Paginas();
				
				pagina.setId(rs.getLong("id"));
				pagina.setNome(rs.getString("nome"));
				
				Date data = rs.getDate("dataCadastro");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(data);
				pagina.setDataCadastro(calendar);
				
				paginas.add(pagina);
			}
			
			for (Paginas pagina : paginas) {
				
				List<String>urls = new ArrayList<String>();
				
				strSql = "select urls.url " +
						"  from paginas paginas " +
						"       , associado_paginas_url urls " +
						" where paginas.id = urls.pagina_id " +
						"   and paginas.nome = '" + pagina.getNome() + "' ";
				
				stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				rs = stmt.executeQuery();					
				
				while (rs.next()) {
					urls.add(rs.getString("url"));
				}
				pagina.setUrl(urls);
			}								
			
			stmt.close();				
		
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
			
		return paginas;
	}

}
