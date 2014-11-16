package br.com.safetycar.servico.dao;

import br.com.safetycar.modelos.Vendedor;

public interface CadastroVendedorDAO {
	
	public boolean existeVendedor(String nome, String sobreNome);
	
	public void adicionaVendedor(Vendedor vendedor);
	
}
