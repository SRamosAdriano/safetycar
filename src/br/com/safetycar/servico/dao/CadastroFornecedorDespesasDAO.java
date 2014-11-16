package br.com.safetycar.servico.dao;

import br.com.safetycar.modelos.FornecedorDespesas;

public interface CadastroFornecedorDespesasDAO {
	
	public boolean existeFornecedorDespesas(String nome);
	
	public void adicionaFornecedorDespesas(FornecedorDespesas forDesp);

}
