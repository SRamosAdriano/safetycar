package br.com.safetycar.relatorios.dao.Impl;

import java.sql.Connection;
import java.util.List;

import br.com.safetycar.dao.MarcasVeiculosDAO;
import br.com.safetycar.dao.VeiculosDAO;
import br.com.safetycar.dao.impl.MarcasVeiculosDAOImpl;
import br.com.safetycar.dao.impl.VeiculosDAOImpl;
import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.relatorios.dao.RelatorioCheckListDAO;

public class RelatorioCheckListDAOImpl implements RelatorioCheckListDAO{
	
	private Connection cnn;
	
	public RelatorioCheckListDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public List<MarcasVeiculos> getMarcasVeiculos(){
		
		MarcasVeiculosDAO dao = new MarcasVeiculosDAOImpl(cnn);
		
		return dao.listaMarcasVeiculos();
	}
	
	public List<Veiculos> pesquisaVeiculo(String placa, String marca, String modelo){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
		
		return dao.pesquisaVeiculo(0, placa, marca, modelo, null, null, null, null, null, null, null, "Ativo");
	}	

	public Veiculos pesquisaVeiculo(long id){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
		
		List<Veiculos> veiculos = dao.pesquisaVeiculo(id, null, null, null, null, null, null, null, null, null, null, null);
		
		return veiculos.size() == 0 ? null : veiculos.get(0);
	}
}
