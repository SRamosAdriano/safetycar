package br.com.safetycar.dwr.relatorios;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.relatorios.RelatorioVeiculosDisponiveis;
import br.com.safetycar.relatorios.dao.RelatorioVeiculosDisponiveisDAO;
import br.com.safetycar.relatorios.dao.Impl.RelatorioVeiculosDisponiveisDAOImpl;

public class RelatorioVeiculosDisponiveisAjaxDwr {
	
	private static Logger LOG = Logger.getLogger(RelatorioVeiculosDisponiveisAjaxDwr.class.getName());
	
	private RelatorioVeiculosDisponiveis veiculosDispVenda;
	
	public void setVeiculosDispVenda(RelatorioVeiculosDisponiveis veiculosDispVenda) {
		this.veiculosDispVenda = veiculosDispVenda;
	}

	public RelatorioVeiculosDisponiveis getVeiculosDispVenda() {
		return veiculosDispVenda;
	}
	
	public RelatorioVeiculosDisponiveis consultaDetVeiculoDisp(String idVeiculo){
						
		if(idVeiculo != null){
			if(idVeiculo.equals("")){				
				return veiculosDispVenda;
			}			
		}		
	
		Connection connectionBD = new ConnectionFactory().getConnection();
		RelatorioVeiculosDisponiveisDAO dao = new RelatorioVeiculosDisponiveisDAOImpl(connectionBD);
		
		setVeiculosDispVenda(dao.pesquisaVeiculo(Long.valueOf(idVeiculo)));
		getVeiculosDispVenda().setOpcionais(dao.buscaOpcionaisVeiculo(Long.valueOf(idVeiculo)));
		getVeiculosDispVenda().setValorVenda(dao.pesquisaVlVendaVeiculo(Long.valueOf(idVeiculo)));
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}	
		return getVeiculosDispVenda();
	}

	

}
