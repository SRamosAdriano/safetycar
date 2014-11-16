package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.safetycar.dao.ClienteFornecedorDAO;
import br.com.safetycar.dao.MarcasVeiculosDAO;
import br.com.safetycar.dao.VeiculosDAO;
import br.com.safetycar.dao.VeiculosOpcionaisDAO;
import br.com.safetycar.dao.VendaVeiculoDAO;
import br.com.safetycar.dao.VendedorDAO;
import br.com.safetycar.dao.VlComissaoEVendaVeiculoDAO;
import br.com.safetycar.dao.impl.ClienteFornecedorDAOImpl;
import br.com.safetycar.dao.impl.MarcasVeiculosDAOImpl;
import br.com.safetycar.dao.impl.VeiculosDAOImpl;
import br.com.safetycar.dao.impl.VeiculosOpcionaisDAOImpl;
import br.com.safetycar.dao.impl.VendaVeiculoDAOImpl;
import br.com.safetycar.dao.impl.VendedorDAOImpl;
import br.com.safetycar.dao.impl.VlComissaoEVendaVeiculoDAOImpl;
import br.com.safetycar.modelos.ClienteFornecedor;
import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.modelos.VeiculosOpcionais;
import br.com.safetycar.modelos.VendaVeiculo;
import br.com.safetycar.modelos.Vendedor;
import br.com.safetycar.modelos.VlComissaoEVendaVeiculo;
import br.com.safetycar.servico.dao.CadastroVendaVeiculoDAO;

public class CadastroVendaVeiculoDAOImpl implements CadastroVendaVeiculoDAO{
	
	private Connection cnn;
	
	public CadastroVendaVeiculoDAOImpl(Connection connection) {
		this.cnn = connection;
	}

	public Veiculos pesquisaVeiculo(long id){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
		
		List<Veiculos> veiculos = dao.pesquisaVeiculo(id, null, null, null, null, null, null, null, null, null, null, null);
		
		return veiculos.size() == 0 ? null : veiculos.get(0);
	}
	
	public List<Veiculos> pesquisaVeiculo(String placa, String marca, String modelo){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
		
		return dao.pesquisaVeiculo(0, placa, marca, modelo, null, null, null, null, null, null, null, "Ativo");
	}
	
	public List<Veiculos> pesquisaVeiculo(String placa, String marca, String modelo, int numeroPaginaAtual, int qtdePorPagina){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
		
		return dao.pesquisaVeiculo(0, placa, marca, modelo, null, null, null, null, null, null, null, "Ativo", numeroPaginaAtual, qtdePorPagina);
	}
	
	public int countVeiculos(String placa, String marca, String modelo){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
		
		return dao.countVeiculos(0, placa, marca, modelo, null, null, null, null, null, null, null, null);
	}
	
	public String buscaOpcionaisVeiculo(long idVeiculo){		
		
		VeiculosOpcionaisDAO dao = new VeiculosOpcionaisDAOImpl(cnn);
		
		VeiculosOpcionais veiculosOpcionais = dao.buscaOpcionaisVeiculos(idVeiculo); 
		
		return veiculosOpcionais.getDescricaoOpcionaisVeiculo();
	}
	
	public List<MarcasVeiculos> getMarcasVeiculos(){
		
		MarcasVeiculosDAO dao = new MarcasVeiculosDAOImpl(cnn);
		
		return dao.listaMarcasVeiculos();
	}
	
	public double getValorVendaVeiculos(long idVeiculo){
		
		VlComissaoEVendaVeiculoDAO dao = new VlComissaoEVendaVeiculoDAOImpl(cnn);
		List<VlComissaoEVendaVeiculo> vlComissaoEVendaVeiculos = dao.pesquisaVlComissaoEVendaVeiculo (0, 0, 0, 0, idVeiculo);
		
		VlComissaoEVendaVeiculo vlComissaoEVendaVeiculo = vlComissaoEVendaVeiculos.size() == 0 ? null : vlComissaoEVendaVeiculos.get(0);
		
		if(vlComissaoEVendaVeiculo != null){			
			return vlComissaoEVendaVeiculo.getValorVenda();
		}else{
			return 0.0;
		}
	}
	
	public List<Vendedor> getVendedores(){
		
		VendedorDAO dao = new VendedorDAOImpl(cnn);
		
		return dao.pesquisaVendedor(0, null, null);
	}
	
	public int countCliente(String nome, String cpf, String rg, String status){
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);
		
		return dao.countClienteFornecedor(0, nome, cpf, rg, status, null);
	}
	
	public List<ClienteFornecedor> pesquisaCliente(String nome, String cpf, String rg, String status, int numeroPaginaAtual, int qtdePorPagina){
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);
		
		return dao.pesquisaClienteFornecedor(0, nome, cpf, rg, status, null, numeroPaginaAtual, qtdePorPagina);
		
	}
	
	public List<ClienteFornecedor> pesquisaCliente(String nome, String cpf, String rg, String status){
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);
		
		List<String> tipoCliFor = new ArrayList<String>(Arrays.asList("cliente/Fornecedor", "cliente"));
		
		return dao.pesquisaClienteFornecedor(0, nome, cpf, rg, status, tipoCliFor);
	}
	
	public ClienteFornecedor pesquisaCliente(long id){
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);
		
		List<String> tipoCliFor = new ArrayList<String>(Arrays.asList("cliente/Fornecedor", "cliente"));
		
		List<ClienteFornecedor> clienteFornecedor = dao.pesquisaClienteFornecedor(id, null, null, null, null, tipoCliFor);
		
		return clienteFornecedor.size() == 0 ? null : clienteFornecedor.get(0);
	}
	
	public void adicionaVendaVeiculo(Veiculos veiculo, VendaVeiculo vendaVeiculo){
		
		VendaVeiculoDAO dao1 = new VendaVeiculoDAOImpl(cnn);		
		VeiculosDAO dao2 = new VeiculosDAOImpl(cnn);
		
		dao1.adicionaVendaVeiculo(vendaVeiculo);
		dao2.atualizaVeiculo(veiculo);		
	}
}
