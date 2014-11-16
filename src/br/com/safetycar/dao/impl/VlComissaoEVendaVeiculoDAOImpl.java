package br.com.safetycar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.dao.VlComissaoEVendaVeiculoDAO;
import br.com.safetycar.modelos.VlComissaoEVendaVeiculo;

public class VlComissaoEVendaVeiculoDAOImpl implements VlComissaoEVendaVeiculoDAO{
	
	private static Logger LOG = Logger.getLogger(VlComissaoEVendaVeiculoDAOImpl.class.getName());
	
	private Connection cnn;

	public VlComissaoEVendaVeiculoDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public void adicionaVlComissaoEVendaVeiculo(VlComissaoEVendaVeiculo vlComiVenda){
		try {
			String strSql = "insert into vlcomissaoevendaveiculo (valorComissao, valorBonus, valorVenda, veiculo_id) "
						  + "     values( ?, ?, ?, ?) ";
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			
			if(vlComiVenda.getValorComissao() == null){
				stmt.setDate(1, null);
			}else{
				stmt.setDouble(1, vlComiVenda.getValorComissao());					
			}			
			
			if(vlComiVenda.getValorBonus() == null){
				stmt.setDate(2, null);
			}else{
				stmt.setDouble(2, vlComiVenda.getValorBonus());					
			}			
			stmt.setDouble(3, vlComiVenda.getValorVenda());			
			stmt.setLong(4, vlComiVenda.getVeiculo_id());
			stmt.execute();
			
			stmt.close();
			
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	public void atualizaVlComissaoEVendaVeiculo(VlComissaoEVendaVeiculo vlComiVenda){
		
		try {			
			String strSql = " update vlcomissaoevendaveiculo set valorComissao = ?, valorBonus = ?, valorVenda = ? " +
					        "        where id = ? ";		

			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);			
			
			if(vlComiVenda.getValorComissao() == null){
				stmt.setDate(1, null);
			}else{
				stmt.setDouble(1, vlComiVenda.getValorComissao());					
			}			
			
			if(vlComiVenda.getValorBonus() == null){
				stmt.setDate(2, null);
			}else{
				stmt.setDouble(2, vlComiVenda.getValorBonus());					
			}
			
			stmt.setDouble(3, vlComiVenda.getValorVenda());
			stmt.setLong(4, vlComiVenda.getId());			
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}
	
	public void removeVlComissaoEVendaVeiculo(long id, long veiculoId){
		try {
			String strSql = "delete from vlcomissaoevendaveiculo where 1 = 1 ";
			
			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(veiculoId > 0){
				strSql = strSql + " and veiculo_id = " + veiculoId;
			}
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			
			stmt.execute();
			
			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	public List<VlComissaoEVendaVeiculo> pesquisaVlComissaoEVendaVeiculo(long id, double valorComissao, double valorBonus, double valorVenda, long veiculoId){		
			
		List<VlComissaoEVendaVeiculo> vlComiVendas = new ArrayList<VlComissaoEVendaVeiculo>();
		
		try {			
			String strSql = "select id, valorComissao, valorBonus, valorVenda, veiculo_id "
                          + "  from vlcomissaoevendaveiculo "
                          + " where 1 = 1 ";		

			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(valorComissao > 0){
				strSql = strSql + " and valorComissao = " + valorComissao;
			}
			if(valorBonus > 0){
				strSql = strSql + " and valorBonus = " + valorBonus;
			}	
			if(valorVenda > 0){
				strSql = strSql + " and valorVenda = " + valorVenda;
			}	
			if(veiculoId > 0){
				strSql = strSql + " and veiculo_id = " + veiculoId;
			}			
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);					
			
			ResultSet rs = stmt.executeQuery();
						
			while (rs.next()) {
				VlComissaoEVendaVeiculo vlComiVenda = new VlComissaoEVendaVeiculo();
				vlComiVenda.setId(rs.getLong("id"));
				vlComiVenda.setValorComissao(rs.getDouble("valorComissao"));
				vlComiVenda.setValorBonus(rs.getDouble("valorBonus"));
				vlComiVenda.setValorVenda(rs.getDouble("valorVenda"));
				vlComiVenda.setVeiculo_id(id);
				
				vlComiVendas.add(vlComiVenda);
			}
			
			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return vlComiVendas;
	}
	
	
	
	public boolean existeVlComissaoEVendaVeiculo(long id , long veiculoId){
		
		try {			
			String strSql = " select id " +
					        "   from vlcomissaoevendaveiculo" +
					        "  where 1 = 1 ";		

			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(veiculoId > 0){
				strSql = strSql + " and veiculo_id = " + veiculoId;
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