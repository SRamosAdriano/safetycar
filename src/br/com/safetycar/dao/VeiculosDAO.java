package br.com.safetycar.dao;

import java.util.List;

import br.com.safetycar.modelos.Veiculos;


public interface VeiculosDAO {
	
	public void adicionaVeiculo(Veiculos veiculo);
	
	public void atualizaVeiculo(Veiculos veiculo);
	
	public void removeVeiculo (long id);
	
	public boolean existeVeiculoAtivo(String placa, long idVeiculo);
	
	public int countVeiculos(long id, String placa, String marca, String modelo
			,String versao, String anoMod, String anoFab, String renavan, String chassi
			, String combustivel, String cor , String status);
	
	public List<Veiculos> pesquisaVeiculo (long id, String placa, String marca, String modelo
			,String versao, String anoMod, String anoFab, String renavan, String chassi
			, String combustivel, String cor , String status);
	
	public List<Veiculos> pesquisaVeiculo (long id, String placa, String marca, String modelo
			,String versao, String anoMod, String anoFab, String renavan, String chassi
			, String combustivel, String cor , String status, int numeroPaginaAtual, int qtdePorPagina);
	
	
	
	
	
	
	
	
	
		

}
