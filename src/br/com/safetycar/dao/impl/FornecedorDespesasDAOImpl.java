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

import br.com.safetycar.dao.FornecedorDespesasDAO;
import br.com.safetycar.modelos.FornecedorDespesas;
import br.com.safetycar.utils.PaginacaoUtils;

public class FornecedorDespesasDAOImpl extends PaginacaoUtils implements FornecedorDespesasDAO{
	
	private static Logger LOG = Logger.getLogger(FornecedorDespesasDAOImpl.class.getName());
	
	private Connection cnn;

	
	public FornecedorDespesasDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public void adicionaFornecedorDespesas(FornecedorDespesas forDesp){
		try {
			String strSql = "insert into fornecedorDespesas (nome, contato, telefone, dtCadastro) "
						  + "     values( ?, ?, ?, ?) ";
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setString(1, forDesp.getNome());
			stmt.setString(2, forDesp.getContato());
			stmt.setString(3, forDesp.getTelefone());			
			stmt.setDate(4, new java.sql.Date(forDesp.getDtCadastro().getTimeInMillis()));
			stmt.execute();
			
			stmt.close();
			
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	public void atualizaFornecedorDespesas (FornecedorDespesas forDesp){
		
		try {			
			String strSql = " update fornecedorDespesas set nome = ?, contato = ?, telefone = ?, dtCadastro = ? " +
					        "        where id = ? ";		

			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setString(1, forDesp.getNome());
			stmt.setString(2, forDesp.getContato());
			stmt.setString(3, forDesp.getTelefone());
			stmt.setDate(4, new java.sql.Date(forDesp.getDtCadastro().getTimeInMillis()));
			stmt.setLong(5, forDesp.getId());
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}
	
	public void removeFornecedorDespesas(long id){
		try {
			String strSql = " update fornecedorDespesas set ativo = false "  +
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
	
	public int countFornecedorDespesas(long id, String nome, String contato, String telefone){
		
		if(nome != null && nome.equals("")){
			nome = null;			
		}
		if(contato != null && contato.equals("")){
			contato = null;			
		}
		if(telefone != null && telefone.equals("")){
			telefone = null;			
		}		
		
		try {
			String strSql = "select count(1) as totalPaginas "
					      + "  from fornecedorDespesas "
						  + " where ativo = true"; 
			
			
			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(nome != null){
				strSql = strSql + " and nome like ('%" + nome.trim() + "%') ";
			}
			if(contato != null){
				strSql = strSql + " and contato like ('%" + contato.trim() + "%') ";
			}
			if(telefone != null){
				strSql = strSql + " and telefone = \"" + telefone.trim() + "\"";
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
	
	public List<FornecedorDespesas> pesquisaFornecedorDespesas(long id, String nome, String contato, String telefone){
		
		return pesqFornecedorDespesas(id, nome, contato, telefone, 0, 0);
	}
	
	public List<FornecedorDespesas> pesquisaFornecedorDespesas(long id, String nome, String contato, String telefone, int numeroPaginaAtual, int qtdePorPagina){
		
		return pesqFornecedorDespesas(id, nome, contato, telefone, numeroPaginaAtual, qtdePorPagina);
	}
	
	private List<FornecedorDespesas> pesqFornecedorDespesas(long id, String nome, String contato, String telefone, int numeroPaginaAtual, int qtdePorPagina){
		
		if(nome != null && nome.equals("")){
			nome = null;			
		}
		if(contato != null && contato.equals("")){
			contato = null;			
		}
		if(telefone != null && telefone.equals("")){
			telefone = null;			
		}
		
		List<FornecedorDespesas> fornecedorDespesas = new ArrayList<FornecedorDespesas>();
		
		try {
			String strSql = "select id, nome, contato, telefone, dtCadastro "
					      + "  from fornecedorDespesas "
						  + " where ativo = true"; 
			
			
			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(nome != null){
				strSql = strSql + " and nome like ('%" + nome.trim() + "%') ";
			}
			if(contato != null){
				strSql = strSql + " and contato like ('%" + contato.trim() + "%') ";
			}
			if(telefone != null){
				strSql = strSql + " and telefone = \"" + telefone.trim() + "\"";
			}
			
			strSql = strSql + " order by nome ";
			
			if(numeroPaginaAtual > 0 && qtdePorPagina > 0){
				strSql = strSql + " LIMIT "+ parametroLimit(numeroPaginaAtual, qtdePorPagina) +", "+ qtdePorPagina;
			}
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);			
			
			ResultSet rs = stmt.executeQuery();
						
			while(rs.next()){
				FornecedorDespesas forDesp = new FornecedorDespesas();
				forDesp.setId(rs.getLong("id"));
				forDesp.setNome(rs.getString("nome"));
				forDesp.setContato(rs.getString("contato"));
				forDesp.setTelefone(rs.getString("telefone"));
				
				Date data = rs.getDate("dtCadastro");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(data);
				forDesp.setDtCadastro(calendar);
				
				fornecedorDespesas.add(forDesp);
			}	
			
			stmt.close();
			
			return fornecedorDespesas;
			
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	public boolean existeFornecedorDespesas(long id, String nome){
		
		if(nome != null && nome.equals("")){
			nome = null;			
		}
		
		try {
			String strSql = "select id"
					      + "  from fornecedorDespesas "
						  + " where ativo = true ";						   
			
			if(id > 0){
				strSql = strSql + " and id <> " + id;
			}
			if(nome != null){
				strSql = strSql + " and nome = \"" + nome.trim() + "\"";
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
