package br.com.safetycar.relatorios.dao;

import java.util.List;

import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.Veiculos;

public interface RelatorioCheckListDAO {
	
	public List<MarcasVeiculos> getMarcasVeiculos();

	public List<Veiculos> pesquisaVeiculo(String placa, String marca, String modelo);
	
	public Veiculos pesquisaVeiculo(long idVeiculo);
}
