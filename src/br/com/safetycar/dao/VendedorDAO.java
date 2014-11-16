package br.com.safetycar.dao;

import java.util.List;

import br.com.safetycar.modelos.Vendedor;

public interface VendedorDAO {
	
	public void adicionaVendedor(Vendedor vendedor);
	
	public void atualizaVendedor (Vendedor vendedor);
	
	public void removeVendedor(long id);
	
	public int countVendedor(long id, String nome, String sobreNome);
	
	public List<Vendedor> pesquisaVendedor(long id, String nome, String sobreNome, int paginaAtual,int qtdePorPagina);
	
	public List<Vendedor> pesquisaVendedor(long id, String nome, String sobreNome);
	
	public boolean existeVendedor(long id, String nome, String sobreNome);

}
