package br.com.safetycar.dao;

import java.util.List;

import br.com.safetycar.modelos.FornecedorDespesas;


public interface FornecedorDespesasDAO{
	
	public void adicionaFornecedorDespesas(FornecedorDespesas forDesp);
	
	public void atualizaFornecedorDespesas (FornecedorDespesas forDesp);
	
	public void removeFornecedorDespesas(long id);
	
	public int countFornecedorDespesas(long id, String nome, String contato, String telefone);
	
	public List<FornecedorDespesas> pesquisaFornecedorDespesas(long id, String nome, String contato, String telefone, int numeroPaginaAtual, int qtdePorPagina);
	
	public List<FornecedorDespesas> pesquisaFornecedorDespesas(long id, String nome, String contato, String telefone);
	
	public boolean existeFornecedorDespesas(long id, String nome);	
	
}
