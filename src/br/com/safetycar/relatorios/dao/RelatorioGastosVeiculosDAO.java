package br.com.safetycar.relatorios.dao;

import java.util.List;

import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.relatorios.RelatorioGastosVeiculos;

public interface RelatorioGastosVeiculosDAO {
	
	public List<RelatorioGastosVeiculos> pesquisaGastosVeiculos(String dtInicio, String dtFim, String placa, String marca, String modelo, String nomeFornecedor);
	
	public List<MarcasVeiculos> getMarcasVeiculos();
	
	public List<String> getNomeForcedores();

}
