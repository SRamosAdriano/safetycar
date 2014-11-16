package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;
import java.util.List;

import br.com.safetycar.dao.VendedorDAO;
import br.com.safetycar.dao.impl.VendedorDAOImpl;
import br.com.safetycar.modelos.Vendedor;
import br.com.safetycar.servico.dao.AlteracaoCadastroVendedorDAO;

public class AlteracaoCadastroVendedorDAOImpl implements AlteracaoCadastroVendedorDAO{
	
	private Connection cnn;
	
	public AlteracaoCadastroVendedorDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public void atualizaVendedor(Vendedor vendedor){
		
		VendedorDAO dao = new VendedorDAOImpl(cnn);
		
		dao.atualizaVendedor(vendedor);
	}
	
	public boolean existeVendedor(String nome, String sobreNome){
		
		VendedorDAO dao = new VendedorDAOImpl(cnn);
		
		return dao.existeVendedor(0, nome, sobreNome);
	}
	
	public int countVendedor(String nome, String sobreNome){
		
		VendedorDAO dao = new VendedorDAOImpl(cnn);
		
		return dao.countVendedor(0, nome, sobreNome); 
	}
	
	public List<Vendedor> pesquisaVendedor(String nome, String sobreNome, int numeroPaginaAtual, int qtdePorPagina){
		
		VendedorDAO dao = new VendedorDAOImpl(cnn);
		
		return dao.pesquisaVendedor(0, nome, sobreNome, numeroPaginaAtual, qtdePorPagina);
	}
	
	public List<Vendedor> pesquisaVendedor(String nome, String sobreNome){
		
		VendedorDAO dao = new VendedorDAOImpl(cnn);
		
		return dao.pesquisaVendedor(0, nome, sobreNome);
	}
	
	public Vendedor pesquisaVendedor(long id){
		
		VendedorDAO dao = new VendedorDAOImpl(cnn);
		
		List<Vendedor> vendedores = dao.pesquisaVendedor(id, null, null); 
		
		return vendedores.size() == 0 ? null : vendedores.get(0);	
	}
	
	public void removeVendedor (long idVendedor){
		
		if(idVendedor > 0){
			VendedorDAO dao = new VendedorDAOImpl(cnn);
			
			dao.removeVendedor(idVendedor);
		}		
	}

}
