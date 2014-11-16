package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;
import java.util.List;

import br.com.safetycar.dao.ClienteFornecedorDAO;
import br.com.safetycar.dao.EstadosDAO;
import br.com.safetycar.dao.impl.ClienteFornecedorDAOImpl;
import br.com.safetycar.dao.impl.EstadosDAOImpl;
import br.com.safetycar.modelos.ClienteFornecedor;
import br.com.safetycar.modelos.Estados;
import br.com.safetycar.servico.dao.CadastroClienteFornecedorDAO;

public class CadastroClienteFornecedorDAOImpl implements CadastroClienteFornecedorDAO {
	
	private Connection cnn;
	
	public CadastroClienteFornecedorDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public void adicionaClienteFornecedor(ClienteFornecedor cliFor) {
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);
		
		dao.adicionaClienteFornecedor(cliFor);
	}
	
	
	public boolean existeCpfCadastrado(String cpf) {
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);
		
		return dao.existeClienteFornecedor(cpf, 0);
	}
	
	
	public List<Estados> getSiglaEstados(){
		
		EstadosDAO dao = new EstadosDAOImpl(cnn);
		
		return dao.listaSiglaEstados();		
	}

}
