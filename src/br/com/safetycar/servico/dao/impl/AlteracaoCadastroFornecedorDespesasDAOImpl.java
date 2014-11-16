package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;
import java.util.List;

import br.com.safetycar.dao.FornecedorDespesasDAO;
import br.com.safetycar.dao.impl.FornecedorDespesasDAOImpl;
import br.com.safetycar.modelos.FornecedorDespesas;
import br.com.safetycar.servico.dao.AlteracaoCadastroFornecedorDespesasDAO;

public class AlteracaoCadastroFornecedorDespesasDAOImpl implements AlteracaoCadastroFornecedorDespesasDAO{
	
	private Connection cnn;

	
	public AlteracaoCadastroFornecedorDespesasDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public void atualizaFornecedorDespesas(FornecedorDespesas fornecedorDespesas){
		
		FornecedorDespesasDAO dao = new FornecedorDespesasDAOImpl(cnn);
		
		dao.atualizaFornecedorDespesas(fornecedorDespesas);
	}
	
	public boolean existeFornecedorDespesas(long idFornecedorDespesas, String nome){
		
		FornecedorDespesasDAO dao = new FornecedorDespesasDAOImpl(cnn);
		
		return dao.existeFornecedorDespesas(idFornecedorDespesas, nome);
	}
	
	public int countFornecedorDespesas(String nome, String contato, String telefone){
		
		FornecedorDespesasDAO dao = new FornecedorDespesasDAOImpl(cnn);
		
		return dao.countFornecedorDespesas(0, nome, contato, telefone);
	}
	
	
	public List<FornecedorDespesas> pesquisaFornecedorDespesas(String nome, String contato, String telefone, int numeroPaginaAtual, int qtdePorPagina){
		
		FornecedorDespesasDAO dao = new FornecedorDespesasDAOImpl(cnn);
		
		return dao.pesquisaFornecedorDespesas(0, nome, contato, telefone, numeroPaginaAtual, qtdePorPagina);
	}
	
	public FornecedorDespesas pesquisaFornecedorDespesas(long id){
		
		FornecedorDespesasDAO dao = new FornecedorDespesasDAOImpl(cnn);
		
		List<FornecedorDespesas> fornecedorDespesas = dao.pesquisaFornecedorDespesas(id, null, null, null); 
		
		return fornecedorDespesas.size() == 0 ? null : fornecedorDespesas.get(0);	
	}
	
	public void removeFornecedorDespesas (long idForDesp){
		
		if(idForDesp > 0){
			FornecedorDespesasDAO dao = new FornecedorDespesasDAOImpl(cnn);
			
			dao.removeFornecedorDespesas(idForDesp);
		}		
	}

}
