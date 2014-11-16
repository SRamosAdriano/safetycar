package br.com.safetycar.relatorios.dao;

import java.util.List;

import br.com.safetycar.modelos.relatorios.RelatorioLucroVeiculo;

public interface RelatorioLucroVeiculoDAO {

	
	public List<RelatorioLucroVeiculo> pesquisaLucroVeiculos(String dtInicio, String dtFim, String nomeVendedor, Boolean consignado);
	
	public List<String> getVendedores();
}
