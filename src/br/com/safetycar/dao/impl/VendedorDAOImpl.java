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

import br.com.safetycar.dao.VendedorDAO;
import br.com.safetycar.modelos.Vendedor;
import br.com.safetycar.utils.PaginacaoUtils;

public class VendedorDAOImpl extends PaginacaoUtils implements VendedorDAO{
	
	private static Logger LOG = Logger.getLogger(VendedorDAOImpl.class.getName());
	
	private Connection cnn;
	
	
	public VendedorDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public void adicionaVendedor(Vendedor vendedor){
		try {
			String strSql = "insert into vendedor (nome, sobreNome, dataCadastro) "
						  + "     values( ?, ?, ?) ";
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setString(1, vendedor.getNome());
			stmt.setString(2, vendedor.getSobreNome().equals("")? null : vendedor.getSobreNome());		
			stmt.setDate(3, new java.sql.Date(vendedor.getDataCadastro().getTimeInMillis()));
			stmt.execute();
			
			stmt.close();
			
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	public void atualizaVendedor(Vendedor vendedor){
		
		try {			
			String strSql = " update vendedor set nome = ?, sobreNome = ? " +
					        "        where id = ? ";		

			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setString(1, vendedor.getNome());
			stmt.setString(2, vendedor.getSobreNome().equals("")? null : vendedor.getSobreNome());
			stmt.setLong(3, vendedor.getId());
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}
	
	public void removeVendedor(long id){
		try {
			String strSql = " update vendedor set ativo = false "  +
			                "  where id = ? "; 
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setLong(1, id);
			
			stmt.executeUpdate();
			
			stmt.close();
			
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	public int countVendedor(long id, String nome, String sobreNome){
		
		if(nome != null && nome.equals("")){
			nome = null;			
		}
		if(sobreNome != null && sobreNome.equals("")){
			sobreNome = null;			
		}		
				
		try {
			String strSql = "select count(1) as totalPaginas from vendedor";
			
			
			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(nome != null){
				strSql = strSql + " and nome like ('%" + nome.trim() + "%') ";
			}
			if(sobreNome != null){
				strSql = strSql + " and sobreNome like ('%" + sobreNome.trim() + "%') ";
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
	
	public List<Vendedor> pesquisaVendedor(long id, String nome, String sobreNome){
		
		return pesqVendedor(id, nome, sobreNome, 0, 0);
	}
	
	public List<Vendedor> pesquisaVendedor(long id, String nome, String sobreNome, int paginaAtual,int qtdePorPagina){
		
		return pesqVendedor(id, nome, sobreNome, paginaAtual, qtdePorPagina);
	}
	
	private List<Vendedor> pesqVendedor(long id, String nome, String sobreNome, int paginaAtual,int qtdePorPagina){
		
		if(nome != null && nome.equals("")){
			nome = null;			
		}
		if(sobreNome != null && sobreNome.equals("")){
			sobreNome = null;			
		}		
		
		List<Vendedor> vendedores = new ArrayList<Vendedor>();
		
		try {
			String strSql = "select id, nome, sobreNome, dataCadastro "
					      + "  from vendedor "
						  + " where ativo = true"; 
			
			
			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(nome != null){
				strSql = strSql + " and nome like ('%" + nome.trim() + "%') ";
			}
			if(sobreNome != null){
				strSql = strSql + " and sobreNome like ('%" + sobreNome.trim() + "%') ";
			}			
			
			strSql = strSql + " order by nome ";
			
			if(paginaAtual > 0 && qtdePorPagina > 0){
				strSql = strSql + " LIMIT "+ parametroLimit(paginaAtual, qtdePorPagina) +", "+ qtdePorPagina;
			}
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);			
			
			ResultSet rs = stmt.executeQuery();
						
			while(rs.next()){
				Vendedor vendedor = new Vendedor();
				vendedor.setId(rs.getLong("id"));
				vendedor.setNome(rs.getString("nome"));
				vendedor.setSobreNome(rs.getString("sobreNome"));
				
				Date data = rs.getDate("dataCadastro");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(data);
				vendedor.setDataCadastro(calendar);
				
				vendedores.add(vendedor);
			}	
			
			stmt.close();
			
			return vendedores;
			
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	public boolean existeVendedor(long id, String nome, String sobreNome){
		
		if(nome != null && nome.equals("")){
			nome = null;			
		}
		if(sobreNome != null && sobreNome.equals("")){
			sobreNome = null;			
		}
		
		try {
			String strSql = "select id"
					      + "  from vendedor "
						  + " where ativo = true ";						   
			
			if(id > 0){
				strSql = strSql + " and id <> " + id;
			}
			if(nome != null){
				strSql = strSql + " and nome = \"" + nome.trim() + "\"";
			}
			if(sobreNome != null){
				strSql = strSql + " and sobreNome = \"" + sobreNome.trim() + "\"";
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

}
