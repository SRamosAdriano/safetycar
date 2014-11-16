package br.com.safetycar.dao;

import java.util.List;

import br.com.safetycar.modelos.GastosVeiculos;


public interface GastosVeiculosDAO {
	
	public void adicionaGastosVeiculo(GastosVeiculos gastosVeiculos);
	
	public void removeGastosVeiculo(long id, long veiculoId);
	
	public List<GastosVeiculos> buscaGastosVeiculo (long id, long veiculoId, long fornecedorDespesasId);
	
	public boolean existeGastosVeiculo (long id, String descricao, double valor, long veiculoId, long fornecedorDespesasId);

}
