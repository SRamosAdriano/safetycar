package br.com.safetycar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.dao.EstadosDAO;
import br.com.safetycar.modelos.Estados;

public class EstadosDAOImpl implements EstadosDAO{
	
	private static Logger LOG = Logger.getLogger(EstadosDAOImpl.class.getName());
	
	private Connection cnn;
	
	public EstadosDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public List<Estados> listaSiglaEstados() {

		List<Estados> estados = new ArrayList<Estados>();
		try {
			String strSql = "select sigla from estados order by sigla";

			PreparedStatement stmt = (PreparedStatement) cnn
					.prepareStatement(strSql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Estados estado = new Estados();
				estado.setSigla(rs.getString("sigla"));

				estados.add(estado);
			}
			
			stmt.close();

		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		return estados;
	}

}
