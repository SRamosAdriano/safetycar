package br.com.safetycar.servico.dao;

import java.util.List;

import br.com.safetycar.modelos.Vendedor;

public interface AlteracaoCadastroVendedorDAO {
	
	public void atualizaVendedor(Vendedor vendedor);
	
	public void removeVendedor (long idVendedor);

	public boolean existeVendedor(String nome, String sobreNome);
	
	public int countVendedor(String nome, String sobreNome);
	
	public List<Vendedor> pesquisaVendedor(String nome, String sobreNome, int numeroPaginaAtual, int qtdePorPagina);
	
	public List<Vendedor> pesquisaVendedor(String nome, String sobreNome);
	
	public Vendedor pesquisaVendedor(long id);

}
