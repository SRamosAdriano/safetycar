package br.com.safetycar.dao;

import java.util.List;

import br.com.safetycar.modelos.CompraVeiculo;

public interface CompraVeiculoDAO {
	
	public void adicionaCompraVeiculo (CompraVeiculo compraVeiculo);
	
	public void atualizaCompraVeiculo (CompraVeiculo compraVeiculo);
	
	public void removeCompraVeiculo (long id, long veiculoId, long fornecedorId);
	
	public List<CompraVeiculo> buscaCompraVeiculo(long veiculoId, long fornecedorId);
	
	public boolean existeCompraVeiculo (long id, long veiculoId, long fornecedorId);
}
