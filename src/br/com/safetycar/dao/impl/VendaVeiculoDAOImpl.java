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

import br.com.safetycar.dao.VendaVeiculoDAO;
import br.com.safetycar.modelos.VendaVeiculo;

public class VendaVeiculoDAOImpl implements VendaVeiculoDAO{
	
	private static Logger LOG = Logger.getLogger(VendaVeiculoDAOImpl.class.getName());
	
	private Connection cnn;
	
	public VendaVeiculoDAOImpl(Connection connection) {
		this.cnn = connection;
	}

	public void adicionaVendaVeiculo (VendaVeiculo vendaVeiculo){
		
		try {			
			String strSql = "insert into vendaVeiculo (dataVenda, valorVenda " +
			"                           , veiculo_id, cliente_id, vendedor_id) " +
			"                   values (?, ?, ?, ?, ?) ";			
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
	
			stmt.setDate(1, new java.sql.Date(vendaVeiculo.getDataVenda().getTimeInMillis()));
			stmt.setDouble(2, vendaVeiculo.getValorVenda());
			stmt.setLong(3, vendaVeiculo.getVeiculo_id());			
			stmt.setLong(4, vendaVeiculo.getCliente_id());
			stmt.setLong(5, vendaVeiculo.getVendedor_id());
			
			stmt.execute();
			
			stmt.close();
		
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}
	
	public void atualizaVendaVeiculo (VendaVeiculo vendaVeiculo){
		
		try {			
			String strSql = " update vendaVeiculo set dataVenda = ?, valorVenda = ?, veiculo_id = ?, cliente_id = ?, vendedor_id = ? " +
					        "        where veiculo_id = ? ";		

			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setDate(1, new java.sql.Date(vendaVeiculo.getDataVenda().getTimeInMillis()));
			stmt.setDouble(2, vendaVeiculo.getValorVenda());
			stmt.setLong(3, vendaVeiculo.getVeiculo_id());
			stmt.setLong(4, vendaVeiculo.getCliente_id());
			stmt.setLong(5, vendaVeiculo.getVendedor_id());
			stmt.setLong(6, vendaVeiculo.getVeiculo_id());			
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}
	
	public void removeVendaVeiculo (long id, long veiculoId, long clienteId, long vendedorId){
		try {
			String strSql = "delete from vendaVeiculo " +
			"                      where 1 = 1 ";
			
			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(veiculoId > 0){
				strSql = strSql + " and veiculo_id = " + veiculoId;
			}
			if(clienteId > 0){
				strSql = strSql + " and cliente_id = " + clienteId;
			}
			if(vendedorId > 0){
				strSql = strSql + " and vendedor_id = " + vendedorId;
			}
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			
			stmt.executeUpdate();
			
			stmt.close();		

		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}
	
	public List<VendaVeiculo> buscaVendaVeiculo(long veiculoId, long clienteId, long vendedorId){
		
		try {
			String strSql = "select id, dataVenda, valorVenda, veiculo_id, cliente_id, vendedor_id " +
			"          from vendaVeiculo " +
			"         where 1 = 1 ";
			
			if(veiculoId > 0){
				strSql = strSql + " and veiculo_id = " + veiculoId;
			}
			if(clienteId > 0){
				strSql = strSql + " and fornecedor_id = " + clienteId;
			}
			if(vendedorId > 0){
				strSql = strSql + " and vendedor_id = " + vendedorId;
			}
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			ResultSet rs = stmt.executeQuery();
			
			List<VendaVeiculo> vendaVeiculos = new ArrayList<VendaVeiculo>();
			
			while(rs.next()){
				VendaVeiculo vendaVeiculo = new VendaVeiculo();
				
				vendaVeiculo.setId(rs.getLong("id"));
				
				Date data = rs.getDate("dataVenda");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(data);
				vendaVeiculo.setDataVenda(calendar);
				
				vendaVeiculo.setValorVenda(rs.getDouble("valorVenda"));				
				vendaVeiculo.setVeiculo_id(rs.getLong("veiculo_id"));
				vendaVeiculo.setCliente_id(rs.getLong("cliente_id"));
				vendaVeiculo.setVendedor_id(rs.getLong("vendedor_id"));
				
				vendaVeiculos.add(vendaVeiculo);
			}			
			stmt.close();
			
			return vendaVeiculos;

		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}
	
	public boolean existeVendaVeiculo (long id, long veiculoId, long clienteId){
		
		try {			
			String strSql = " select id " +
					        "   from vendaVeiculo" +
					        "  where 1 = 1 ";		

			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(veiculoId > 0){
				strSql = strSql + " and veiculo_id = " + veiculoId;
			}
			if(clienteId > 0){
				strSql = strSql + " and cliente_id = " + clienteId;
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
