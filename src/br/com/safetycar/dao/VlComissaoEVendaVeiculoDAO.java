package br.com.safetycar.dao;

import java.util.List;

import br.com.safetycar.modelos.VlComissaoEVendaVeiculo;


public interface VlComissaoEVendaVeiculoDAO {
	
	public void adicionaVlComissaoEVendaVeiculo(VlComissaoEVendaVeiculo vlComiVenda);
	
	public void atualizaVlComissaoEVendaVeiculo(VlComissaoEVendaVeiculo vlComiVenda);
	
	public void removeVlComissaoEVendaVeiculo(long id, long veiculoId);
	
	public List<VlComissaoEVendaVeiculo> pesquisaVlComissaoEVendaVeiculo (long id, double valorComissao, double valorBonus, double valorVenda, long veiculoId);
	
	public boolean existeVlComissaoEVendaVeiculo (long id , long veiculoId);
	
}
