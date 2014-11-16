package br.com.safetycar.servico.dao;

import java.util.List;

import br.com.safetycar.modelos.CompraVeiculo;
import br.com.safetycar.modelos.GastosVeiculos;
import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.modelos.VlComissaoEVendaVeiculo;

public interface LancamentoVlComissaoEVendaVeiculoDAO {
	
	public void adicionaVlComissaoEVendaVeiculo(VlComissaoEVendaVeiculo vlComiVenda);
	
	public void atualizaVlComissaoEVendaVeiculo(VlComissaoEVendaVeiculo vlComiVenda);
		
	public VlComissaoEVendaVeiculo pesquisaVlComissaoEVendaVeiculo (long veiculoId);
	
	public boolean existeVlComissaoEVendaVeiculo (long veiculoId);
	
	public int countVeiculo(String placa, String marca, String modelo);
	
	public List<Veiculos> pesquisaVeiculo(String placa, String marca, String modelo, int numeroPaginaAtual, int qtdePorPagina);
	
	public List<Veiculos> pesquisaVeiculo(String placa, String marca, String modelo);
	
	public Veiculos pesquisaVeiculo(long id);
	
	public List<MarcasVeiculos> getMarcasVeiculos();
	
	public CompraVeiculo buscaCompraVeiculo(long veiculoId);
	
	public List<GastosVeiculos> buscaGastosVeiculo (long veiculoId);

}
