package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.safetycar.dao.ClienteFornecedorDAO;
import br.com.safetycar.dao.CombustivelVeiculosDAO;
import br.com.safetycar.dao.CompraVeiculoDAO;
import br.com.safetycar.dao.EstadosDAO;
import br.com.safetycar.dao.GastosVeiculosDAO;
import br.com.safetycar.dao.MarcasVeiculosDAO;
import br.com.safetycar.dao.VeiculosDAO;
import br.com.safetycar.dao.VeiculosOpcionaisDAO;
import br.com.safetycar.dao.VendaVeiculoDAO;
import br.com.safetycar.dao.VlComissaoEVendaVeiculoDAO;
import br.com.safetycar.dao.impl.ClienteFornecedorDAOImpl;
import br.com.safetycar.dao.impl.CombustivelVeiculosDAOImpl;
import br.com.safetycar.dao.impl.CompraVeiculoDAOImpl;
import br.com.safetycar.dao.impl.EstadosDAOImpl;
import br.com.safetycar.dao.impl.GastosVeiculosDAOImpl;
import br.com.safetycar.dao.impl.MarcasVeiculosDAOImpl;
import br.com.safetycar.dao.impl.VeiculosDAOImpl;
import br.com.safetycar.dao.impl.VeiculosOpcionaisDAOImpl;
import br.com.safetycar.dao.impl.VendaVeiculoDAOImpl;
import br.com.safetycar.dao.impl.VlComissaoEVendaVeiculoDAOImpl;
import br.com.safetycar.modelos.ClienteFornecedor;
import br.com.safetycar.modelos.CombustivelVeiculos;
import br.com.safetycar.modelos.CompraVeiculo;
import br.com.safetycar.modelos.Estados;
import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.modelos.VeiculosOpcionais;
import br.com.safetycar.servico.dao.AlteracaoCadastroVeiculoDAO;

public class AlteracaoCadastroVeiculoDAOImpl implements AlteracaoCadastroVeiculoDAO{
	
	private Connection cnn;
	
	public AlteracaoCadastroVeiculoDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public int countVeiculos(String placa, String marca, String modelo){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
		
		return dao.countVeiculos(0, placa, marca, modelo, null, null, null, null, null, null, null, null);
	}
	
	public List<Veiculos> pesquisaVeiculo(String placa, String marca, String modelo, int numeroPaginaAtual, int qtdePorPagina){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
		
		return dao.pesquisaVeiculo(0, placa, marca, modelo, null, null, null, null, null, null, null, null, numeroPaginaAtual, qtdePorPagina);
	}
	
	public Veiculos pesquisaVeiculo(long id){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
		
		List<Veiculos> veiculos = dao.pesquisaVeiculo(id, null, null, null, null, null, null, null, null, null, null, null);
		
		return veiculos.size() == 0 ? null : veiculos.get(0);
	}
	
	
	public VeiculosOpcionais buscaOpcionaisVeiculo(long idVeiculo){
		VeiculosOpcionaisDAO dao = new VeiculosOpcionaisDAOImpl(cnn);
		
		return dao.buscaOpcionaisVeiculos(idVeiculo);
	}
	
	
	public CompraVeiculo buscaCompraVeiculo(long veiculoId){
		
		CompraVeiculoDAO dao = new CompraVeiculoDAOImpl(cnn);
		
		List<CompraVeiculo> compraVeiculos = dao.buscaCompraVeiculo(veiculoId, 0);
		
		return compraVeiculos.size() == 0 ? null : compraVeiculos.get(0);		
	}
	
	public int countFornecedor(String nome, String cpf, String rg, String status){
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);
		
		return dao.countClienteFornecedor(0, nome, cpf, rg, status, null);
	}
	
	public List<ClienteFornecedor> pesquisaFornecedor(String nome, String cpf, String rg, String status , int numeroPaginaAtual, int qtdePorPagina){
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);		
		
		return dao.pesquisaClienteFornecedor(0, nome, cpf, rg, null, null, numeroPaginaAtual, qtdePorPagina);
		
	}
	
	public List<ClienteFornecedor> pesquisaFornecedor(String nome, String cpf, String rg, String status){
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);
		
		List<String> tipoCliFor = new ArrayList<String>(Arrays.asList("cliente/Fornecedor", "fornecedor"));
		
		return dao.pesquisaClienteFornecedor(0, nome, cpf, rg, status, tipoCliFor);
	}
	
	
	public ClienteFornecedor pesquisaFornecedor(long id){
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);
		
		List<String> tipoCliFor = new ArrayList<String>(Arrays.asList("cliente/Fornecedor", "fornecedor"));
		
		List<ClienteFornecedor> clienteFornecedor = dao.pesquisaClienteFornecedor(id, null, null, null, null, tipoCliFor);
		
		return clienteFornecedor.size() == 0 ? null : clienteFornecedor.get(0);
	}
	
	
	public List<MarcasVeiculos> getMarcasVeiculos(){
		
		MarcasVeiculosDAO dao = new MarcasVeiculosDAOImpl(cnn);
		
		return dao.listaMarcasVeiculos();
	}
	
	
	public List<Estados> getSiglaEstados(){
		
		EstadosDAO dao = new EstadosDAOImpl(cnn);
		
		return dao.listaSiglaEstados();	
	}
	
	
	public List<CombustivelVeiculos> getCombustivelVeiculos(){
		
		CombustivelVeiculosDAO dao = new CombustivelVeiculosDAOImpl(cnn);
		
		return dao.listarCombustivelVeiculos();
	}
	
	public boolean existeVeiculoAtivo(String placa, long idVeiculo){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
						
		return dao.existeVeiculoAtivo(placa, idVeiculo);
	}
	
	public void removeVeiculo (long veiculoId){
		
		if(veiculoId > 0){
			VeiculosDAO dao1 = new VeiculosDAOImpl(cnn);
			VeiculosOpcionaisDAO dao2 = new VeiculosOpcionaisDAOImpl(cnn);
			CompraVeiculoDAO dao3 = new CompraVeiculoDAOImpl(cnn);
			GastosVeiculosDAO dao4 = new GastosVeiculosDAOImpl(cnn);
			VlComissaoEVendaVeiculoDAO dao5 = new VlComissaoEVendaVeiculoDAOImpl(cnn);
			VendaVeiculoDAO dao6 = new VendaVeiculoDAOImpl(cnn);			
			
			
			dao6.removeVendaVeiculo(0, veiculoId, 0, 0);
			dao5.removeVlComissaoEVendaVeiculo(0, veiculoId);
			dao4.removeGastosVeiculo(0, veiculoId);
			dao3.removeCompraVeiculo(0, veiculoId, 0);
			dao2.removeOpcionaisVeiculos(0, veiculoId);
			dao1.removeVeiculo(veiculoId);			
		}		
	}
	
	public void atualizaCadastroVeiculo(Veiculos veiculo, VeiculosOpcionais veiculosOpcionais, CompraVeiculo compraVeiculo){
		
		VeiculosDAO dao1 = new VeiculosDAOImpl(cnn);
		VeiculosOpcionaisDAO dao2 = new VeiculosOpcionaisDAOImpl(cnn);
		CompraVeiculoDAO dao3 = new CompraVeiculoDAOImpl(cnn);
		
		dao1.atualizaVeiculo(veiculo);
		
		if(dao2.existeOpcionaisVeiculos(0, veiculosOpcionais.getVeiculo_id())){
			dao2.atualizaOpcionaisVeiculos(veiculosOpcionais);
		}else{
			dao2.adicionaOpcionaisVeiculos(veiculosOpcionais);
		}
		
		if(dao3.existeCompraVeiculo(0, compraVeiculo.getVeiculo_id(), 0)){
			dao3.atualizaCompraVeiculo(compraVeiculo);
		}else{
			dao3.adicionaCompraVeiculo(compraVeiculo);
		}
	}

}
