package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;

import br.com.safetycar.dao.VendedorDAO;
import br.com.safetycar.dao.impl.VendedorDAOImpl;
import br.com.safetycar.modelos.Vendedor;
import br.com.safetycar.servico.dao.CadastroVendedorDAO;

public class CadastroVendedorDAOImpl implements CadastroVendedorDAO{
	
	private Connection cnn;
	
	public CadastroVendedorDAOImpl(Connection connection) {
		this.cnn = connection;
	}

	
	public boolean existeVendedor(String nome, String sobreNome){
		
		VendedorDAO dao = new VendedorDAOImpl(cnn);
		
		return dao.existeVendedor(0, nome, sobreNome);
	}
	
	
	public void adicionaVendedor(Vendedor vendedor){
		
		VendedorDAO dao = new VendedorDAOImpl(cnn);
		
		dao.adicionaVendedor(vendedor);
	}
}
