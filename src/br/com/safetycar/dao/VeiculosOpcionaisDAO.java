package br.com.safetycar.dao;

import br.com.safetycar.modelos.VeiculosOpcionais;


public interface VeiculosOpcionaisDAO {
	
	public void adicionaOpcionaisVeiculos(VeiculosOpcionais veiculosOpcionais);
	
	public void atualizaOpcionaisVeiculos(VeiculosOpcionais veiculosOpcionais);
	
	public void removeOpcionaisVeiculos(long id, long veiculoId);
	
	public boolean existeOpcionaisVeiculos (long id , long veiculoId);
	
	public VeiculosOpcionais buscaOpcionaisVeiculos(long idVeiculo);

	
}
