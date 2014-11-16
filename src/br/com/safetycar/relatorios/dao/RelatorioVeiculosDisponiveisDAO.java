package br.com.safetycar.relatorios.dao;

import java.util.List;

import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.relatorios.RelatorioVeiculosDisponiveis;

public interface RelatorioVeiculosDisponiveisDAO {
	
	public List<RelatorioVeiculosDisponiveis> pesquisaVeiculosDisponiveisParaVenda(String placa, String marca, String modelo);
	
	public RelatorioVeiculosDisponiveis pesquisaVeiculo(long id);
	
	public String buscaOpcionaisVeiculo(long idVeiculo);
	
	public String pesquisaVlVendaVeiculo (long veiculoId);
	
	public List<MarcasVeiculos> getMarcasVeiculos();
}
