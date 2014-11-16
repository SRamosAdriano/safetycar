package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;
import java.util.List;

import br.com.safetycar.dao.ClienteFornecedorDAO;
import br.com.safetycar.dao.EstadosDAO;
import br.com.safetycar.dao.impl.ClienteFornecedorDAOImpl;
import br.com.safetycar.dao.impl.EstadosDAOImpl;
import br.com.safetycar.modelos.ClienteFornecedor;
import br.com.safetycar.modelos.Estados;
import br.com.safetycar.servico.dao.AlteracaoCadastroClienteFornecedorDAO;

public class AlteracaoCadastroClienteFornecedorDAOImpl implements
		AlteracaoCadastroClienteFornecedorDAO {
	
	private Connection cnn;
	
	public AlteracaoCadastroClienteFornecedorDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public void atualizaClienteFornecedor(ClienteFornecedor cliFor){
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);
		
		dao.atualizaClienteFornecedor(cliFor);
	}
	
	public boolean existeCpfCadastrado(String cpf, long idClienteFornecedor) {
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);
		
		return dao.existeClienteFornecedor(cpf, idClienteFornecedor);
	}

	public List<Estados> getSiglaEstados() {

		EstadosDAO dao = new EstadosDAOImpl(cnn);

		return dao.listaSiglaEstados();
	}
	
	public int countClienteFornecedor(String nome, String cpf, String rg){
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);
		
		return dao.countClienteFornecedor(0, nome, cpf, rg, null, null);
	}
	
	public List<ClienteFornecedor> pesquisaCliFor(String nome, String cpf, String rg, int numeroPaginaAtual, int qtdePorPagina){
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);		
		
		return dao.pesquisaClienteFornecedor(0, nome, cpf, rg, null, null, numeroPaginaAtual, qtdePorPagina);
	}
	
	public ClienteFornecedor pesquisaCliFor(long id){
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);
						
		List<ClienteFornecedor> clienteFornecedor = dao.pesquisaClienteFornecedor(id, null, null, null, null, null);
		
		return clienteFornecedor.size() == 0 ? null : clienteFornecedor.get(0);
		
	}

}
