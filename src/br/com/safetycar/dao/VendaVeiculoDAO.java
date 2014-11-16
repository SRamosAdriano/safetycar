package br.com.safetycar.dao;

import java.util.List;

import br.com.safetycar.modelos.VendaVeiculo;

public interface VendaVeiculoDAO {
	
	public void adicionaVendaVeiculo (VendaVeiculo vendaVeiculo);
	
	public void atualizaVendaVeiculo (VendaVeiculo vendaVeiculo);
	
	public void removeVendaVeiculo (long id, long veiculoId, long clienteId, long vendedorId);
	
	public List<VendaVeiculo> buscaVendaVeiculo(long veiculoId, long clineteId, long vendedorId);
	
	public boolean existeVendaVeiculo (long id, long veiculoId, long clienteId);

}
