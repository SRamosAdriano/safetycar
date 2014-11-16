package br.com.safetycar.dao;

import java.util.List;

import br.com.safetycar.modelos.ClienteFornecedor;


public interface ClienteFornecedorDAO {
	
	public void adicionaClienteFornecedor(ClienteFornecedor cliFor);
	
	public void atualizaClienteFornecedor(ClienteFornecedor cliFor);
	
	public boolean existeClienteFornecedor(String cpf, long idClienteFornecedor);
	
	public int countClienteFornecedor(long id, String nome, String cpf, String rg, String status, List<String> tipoCliFor);
	
	public List<ClienteFornecedor> pesquisaClienteFornecedor(long id, String nome, String cpf, String rg, String status, List<String> tipoCliFor);
	
	public List<ClienteFornecedor> pesquisaClienteFornecedor(long id, String nome, String cpf, String rg, String status, List<String> tipoCliFor, int paginaAtual,int qtdePorPagina);

}
