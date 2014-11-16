package br.com.safetycar.servico.dao;

import java.util.List;

import br.com.safetycar.modelos.ClienteFornecedor;
import br.com.safetycar.modelos.Estados;

public interface AlteracaoCadastroClienteFornecedorDAO {
	
	public void atualizaClienteFornecedor(ClienteFornecedor cliFor);

	public boolean existeCpfCadastrado(String cpf, long idClienteFornecedor);

	public List<Estados> getSiglaEstados();
	
	public int countClienteFornecedor(String nome, String cpf, String rg);
	
	public List<ClienteFornecedor> pesquisaCliFor(String nome, String cpf, String rg, int numeroPaginaAtual, int qtdePorPagina);
	
	public ClienteFornecedor pesquisaCliFor(long id);

}
