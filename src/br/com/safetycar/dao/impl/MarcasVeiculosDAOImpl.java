package br.com.safetycar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.dao.MarcasVeiculosDAO;
import br.com.safetycar.modelos.MarcasVeiculos;

public class MarcasVeiculosDAOImpl implements MarcasVeiculosDAO{
	
	private static Logger LOG = Logger.getLogger(MarcasVeiculosDAOImpl.class.getName());
	
	private Connection cnn;
	
	public MarcasVeiculosDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public List<MarcasVeiculos> listaMarcasVeiculos(){
		
		List<MarcasVeiculos> marcasVeiculos = new ArrayList<MarcasVeiculos>();
		try {			
			String strSql = "select nomeMarca from marcasVeiculos order by nomeMarca";		

			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				MarcasVeiculos marcaVeiculo = new MarcasVeiculos();
				marcaVeiculo.setNomeMarca(rs.getString("nomeMarca"));
				
				marcasVeiculos.add(marcaVeiculo);
			}
			
			stmt.close();
		
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}	
		return marcasVeiculos;
	}

}
