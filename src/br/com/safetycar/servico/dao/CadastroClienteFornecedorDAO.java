package br.com.safetycar.servico.dao;

import java.util.List;

import br.com.safetycar.modelos.ClienteFornecedor;
import br.com.safetycar.modelos.Estados;

public interface CadastroClienteFornecedorDAO {
	
	public void adicionaClienteFornecedor(ClienteFornecedor cliFor);
	
	public boolean existeCpfCadastrado(String cpf);
	
	public List<Estados> getSiglaEstados();

}
