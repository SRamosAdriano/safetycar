package br.com.safetycar.relatorios.dao;

import java.util.List;

import br.com.safetycar.modelos.relatorios.RelatorioComissaoVendedor;

public interface RelatorioComissaoVendedorDAO {

	public List<RelatorioComissaoVendedor> pesquisaComissaoVendedor(String dtInicio, String dtFim, String nomeVendedor);	
	
	public List<String> getVendedores();
}