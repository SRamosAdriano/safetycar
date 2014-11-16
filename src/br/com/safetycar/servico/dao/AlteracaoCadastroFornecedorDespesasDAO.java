package br.com.safetycar.servico.dao;

import java.util.List;

import br.com.safetycar.modelos.FornecedorDespesas;

public interface AlteracaoCadastroFornecedorDespesasDAO {
	
	public void atualizaFornecedorDespesas(FornecedorDespesas fornecedorDespesas);
	
	public void removeFornecedorDespesas (long idForDesp);

	public boolean existeFornecedorDespesas(long idFornecedorDespesas, String nome);
	
	public int countFornecedorDespesas(String nome, String contato, String telefone);
	
	public List<FornecedorDespesas> pesquisaFornecedorDespesas(String nome, String contato, String telefone, int numeroPaginaAtual, int qtdePorPagina);
	
	public FornecedorDespesas pesquisaFornecedorDespesas(long id);

}
