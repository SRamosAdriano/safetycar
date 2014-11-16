package br.com.safetycar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.dao.CombustivelVeiculosDAO;
import br.com.safetycar.modelos.CombustivelVeiculos;

public class CombustivelVeiculosDAOImpl implements CombustivelVeiculosDAO{
	
	private static Logger LOG = Logger.getLogger(CombustivelVeiculosDAOImpl.class.getName());
	
	private Connection cnn;
	
	public CombustivelVeiculosDAOImpl(Connection connection) {
		this.cnn = connection;
	}

	
	public List<CombustivelVeiculos> listarCombustivelVeiculos() {

		List<CombustivelVeiculos> combustivelVeiculos = new ArrayList<CombustivelVeiculos>();
		try {
			String strSql = "select descricao from combustivelVeiculos order by descricao";

			PreparedStatement stmt = (PreparedStatement) cnn
					.prepareStatement(strSql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				CombustivelVeiculos combustivelVeiculo = new CombustivelVeiculos();
				combustivelVeiculo.setDescricao(rs.getString("descricao"));

				combustivelVeiculos.add(combustivelVeiculo);
			}
			
			stmt.close();

		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		return combustivelVeiculos;
	}
}
