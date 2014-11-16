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

import br.com.safetycar.dao.CompraVeiculoDAO;
import br.com.safetycar.modelos.CompraVeiculo;

public class CompraVeiculoDAOImpl implements CompraVeiculoDAO{
	
	private static Logger LOG = Logger.getLogger(CompraVeiculoDAOImpl.class.getName());
	
	private Connection cnn;
	
	public CompraVeiculoDAOImpl(Connection connection) {
		this.cnn = connection;
	}

	public void adicionaCompraVeiculo (CompraVeiculo compraVeiculo){
		
		try {			
			String strSql = "insert into compraVeiculo (dataCompra, valorCompra, consignado" +
			"                           , veiculo_id, fornecedor_id) " +
			"                   values (?, ?, ?, ?, ?) ";			
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
	
			stmt.setDate(1, new java.sql.Date(compraVeiculo.getDataCompra().getTimeInMillis()));
			stmt.setDouble(2, compraVeiculo.getValorCompra());
			stmt.setBoolean(3, compraVeiculo.isConsignado());
			stmt.setLong(4, compraVeiculo.getVeiculo_id());			
			stmt.setLong(5, compraVeiculo.getFornecedor_id());
			
			stmt.execute();
			
			stmt.close();
		
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}
	
	public void atualizaCompraVeiculo (CompraVeiculo compraVeiculo){
		
		try {			
			String strSql = " update compraveiculo set dataCompra = ?, valorCompra = ?, consignado = ?, veiculo_id = ?, fornecedor_id = ? " +
					        "        where veiculo_id = ? ";		

			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setDate(1, new java.sql.Date(compraVeiculo.getDataCompra().getTimeInMillis()));
			stmt.setDouble(2, compraVeiculo.getValorCompra());
			stmt.setBoolean(3, compraVeiculo.isConsignado());
			stmt.setLong(4, compraVeiculo.getVeiculo_id());
			stmt.setLong(5, compraVeiculo.getFornecedor_id());
			stmt.setLong(6, compraVeiculo.getVeiculo_id());			
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}
	
	public void removeCompraVeiculo (long id, long veiculoId, long fornecedorId){
		try {
			String strSql = "delete from compraveiculo " +
			"                      where 1 = 1 ";
			
			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(veiculoId > 0){
				strSql = strSql + " and veiculo_id = " + veiculoId;
			}
			if(fornecedorId > 0){
				strSql = strSql + " and fornecedor_id = " + fornecedorId;
			}
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			
			stmt.executeUpdate();
			
			stmt.close();		

		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}
	
	public List<CompraVeiculo> buscaCompraVeiculo(long veiculoId, long fornecedorId){
		
		try {
			String strSql = "select id, dataCompra, valorCompra, consignado, veiculo_id, fornecedor_id " +
			"          from compraveiculo " +
			"         where 1 = 1 ";
			
			if(veiculoId > 0){
				strSql = strSql + " and veiculo_id = " + veiculoId;
			}
			if(fornecedorId > 0){
				strSql = strSql + " and fornecedor_id = " + fornecedorId;
			}
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			ResultSet rs = stmt.executeQuery();
			
			List<CompraVeiculo> compraVeiculos = new ArrayList<CompraVeiculo>();
			
			while(rs.next()){
				CompraVeiculo compraVeiculo = new CompraVeiculo();
				
				compraVeiculo.setId(rs.getLong("id"));
				
				Date data = rs.getDate("dataCompra");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(data);
				compraVeiculo.setDataCompra(calendar);
				
				compraVeiculo.setValorCompra(rs.getDouble("valorCompra"));
				compraVeiculo.setConsignado(rs.getBoolean("consignado"));
				compraVeiculo.setVeiculo_id(rs.getLong("veiculo_id"));
				compraVeiculo.setFornecedor_id(rs.getLong("fornecedor_id"));
				
				compraVeiculos.add(compraVeiculo);
			}			
			stmt.close();
			
			return compraVeiculos;

		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}
	
	public boolean existeCompraVeiculo (long id, long veiculoId, long fornecedorId){
		
		try {			
			String strSql = " select id " +
					        "   from compraveiculo" +
					        "  where 1 = 1 ";		

			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(veiculoId > 0){
				strSql = strSql + " and veiculo_id = " + veiculoId;
			}
			if(fornecedorId > 0){
				strSql = strSql + " and fornecedor_id = " + fornecedorId;
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
