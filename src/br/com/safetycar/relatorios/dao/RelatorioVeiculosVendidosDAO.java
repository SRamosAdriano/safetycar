package br.com.safetycar.relatorios.dao;

import java.util.List;

import br.com.safetycar.modelos.relatorios.RelatorioVeiculosVendidos;

public interface RelatorioVeiculosVendidosDAO {
	
	public List<RelatorioVeiculosVendidos> pesquisaVeiculosVendidos(String dtInicio, String dtFim, String nomeVendedor, Boolean consignado);	
	
	public List<String> getVendedores();
}
