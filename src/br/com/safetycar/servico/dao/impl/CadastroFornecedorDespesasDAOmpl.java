package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;

import br.com.safetycar.dao.FornecedorDespesasDAO;
import br.com.safetycar.dao.impl.FornecedorDespesasDAOImpl;
import br.com.safetycar.modelos.FornecedorDespesas;
import br.com.safetycar.servico.dao.CadastroFornecedorDespesasDAO;

public class CadastroFornecedorDespesasDAOmpl implements CadastroFornecedorDespesasDAO{
	
	private Connection cnn;
	
	public CadastroFornecedorDespesasDAOmpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public boolean existeFornecedorDespesas(String nome){
		
		FornecedorDespesasDAO dao = new FornecedorDespesasDAOImpl(cnn);
		
		return dao.existeFornecedorDespesas(0, nome);
	}
	
	
	public void adicionaFornecedorDespesas(FornecedorDespesas forDesp){
		
		FornecedorDespesasDAO dao = new FornecedorDespesasDAOImpl(cnn);
		
		dao.adicionaFornecedorDespesas(forDesp);
	}

}
