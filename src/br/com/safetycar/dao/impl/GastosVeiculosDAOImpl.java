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

import br.com.safetycar.dao.GastosVeiculosDAO;
import br.com.safetycar.modelos.GastosVeiculos;

public class GastosVeiculosDAOImpl implements GastosVeiculosDAO{
	
	private static Logger LOG = Logger.getLogger(GastosVeiculosDAOImpl.class.getName());
	
	private Connection cnn;
	
	public GastosVeiculosDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public void adicionaGastosVeiculo(GastosVeiculos gastosVeiculos){
		try {
			String strSql = "insert into gastosVeiculos (descricao, valor, dtLancamento, veiculo_id, fornecedordespesas_id, nDocumento) "
						  + "     values( ?, ?, ?, ?, ?, ?) ";
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setString(1, gastosVeiculos.getDescricao());
			stmt.setDouble(2, gastosVeiculos.getValor());
			stmt.setDate(3, new java.sql.Date(gastosVeiculos.getDtLancamento().getTimeInMillis()));			
			stmt.setLong(4, gastosVeiculos.getVeiculo_id());
			stmt.setLong(5, gastosVeiculos.getFornecedordespesas_id());
			stmt.setString(6, gastosVeiculos.getNDocumento());
			stmt.execute();
			
			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	public void removeGastosVeiculo(long id, long veiculoId){
		try {
			String strSql = "delete from gastosVeiculos where 1 = 1";
			
			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(veiculoId > 0){
				strSql = strSql + " and veiculo_id = " + veiculoId;
			}
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
						
			stmt.executeUpdate();
			
			stmt.close();
			
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}	
	
	public List<GastosVeiculos> buscaGastosVeiculo (long id, long veiculoId, long fornecedorDespesasId){
		
		List<GastosVeiculos> gastosVeiculos = new ArrayList<GastosVeiculos>();
		try {			
			String strSql = "select id, descricao, valor, dtLancamento, veiculo_id, fornecedordespesas_id, nDocumento "
                          + "  from gastosVeiculos "
                          + " where 1 = 1 ";		

			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(veiculoId > 0){
				strSql = strSql + " and veiculo_id = " + veiculoId;
			}
			if(fornecedorDespesasId > 0){
				strSql = strSql + " and fornecedordespesas_id = " + fornecedorDespesasId;
			}
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);			
			
			ResultSet rs = stmt.executeQuery();
						
			while (rs.next()) {
				GastosVeiculos gastoVeiculo = new GastosVeiculos();
				gastoVeiculo.setId(rs.getLong("id"));
				gastoVeiculo.setDescricao(rs.getString("descricao"));
				gastoVeiculo.setValor(rs.getDouble("valor"));
				
				Date data = rs.getDate("dtLancamento");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(data);
				gastoVeiculo.setDtLancamento(calendar);
								
				gastoVeiculo.setVeiculo_id(rs.getLong("veiculo_id"));
				gastoVeiculo.setFornecedordespesas_id(rs.getLong("fornecedordespesas_id"));
				gastoVeiculo.setNDocumento(rs.getString("nDocumento"));
				
				gastosVeiculos.add(gastoVeiculo);
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return gastosVeiculos;
	}
	
	
	public boolean existeGastosVeiculo (long id, String descricao, double valor, long veiculoId, long fornecedorDespesasId){
		
		if(descricao != null && descricao.equals("")){
			descricao = null;			
		}		
		try {			
			String strSql = " select id" +
					        " from gastosVeiculos " +
					        " where 1 = 1 ";		

			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(descricao != null){
				strSql = strSql + " and descricao = \"" + descricao.trim() + "\"";
			}
			if(valor > 0){
				strSql = strSql + " and valor = " + valor;
			}
			if(veiculoId > 0){
				strSql = strSql + " and veiculo_id = " + veiculoId;
			}
			if(fornecedorDespesasId > 0){
				strSql = strSql + " and fornecedordespesas_id = " + fornecedorDespesasId;
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
