package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.safetycar.dao.ClienteFornecedorDAO;
import br.com.safetycar.dao.CombustivelVeiculosDAO;
import br.com.safetycar.dao.CompraVeiculoDAO;
import br.com.safetycar.dao.EstadosDAO;
import br.com.safetycar.dao.MarcasVeiculosDAO;
import br.com.safetycar.dao.VeiculosDAO;
import br.com.safetycar.dao.VeiculosOpcionaisDAO;
import br.com.safetycar.dao.impl.ClienteFornecedorDAOImpl;
import br.com.safetycar.dao.impl.CombustivelVeiculosDAOImpl;
import br.com.safetycar.dao.impl.CompraVeiculoDAOImpl;
import br.com.safetycar.dao.impl.EstadosDAOImpl;
import br.com.safetycar.dao.impl.MarcasVeiculosDAOImpl;
import br.com.safetycar.dao.impl.VeiculosDAOImpl;
import br.com.safetycar.dao.impl.VeiculosOpcionaisDAOImpl;
import br.com.safetycar.modelos.ClienteFornecedor;
import br.com.safetycar.modelos.CombustivelVeiculos;
import br.com.safetycar.modelos.CompraVeiculo;
import br.com.safetycar.modelos.Estados;
import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.modelos.VeiculosOpcionais;
import br.com.safetycar.servico.dao.CadastroVeiculoDAO;

public class CadastroVeiculoDAOImpl implements CadastroVeiculoDAO {

	private Connection cnn;

	public CadastroVeiculoDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public int countFornecedor(String nome, String cpf, String rg, String status){
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);
		
		return dao.countClienteFornecedor(0, nome, cpf, rg, status, null);
	}
	
	public List<ClienteFornecedor> pesquisaFornecedor(String nome, String cpf, String rg, String status,  int numeroPaginaAtual, int qtdePorPagina){
		
		ClienteFornecedorDAO dao = new ClienteFornecedorDAOImpl(cnn);		
		
		return dao.pesquisaClienteFornecedor(0, nome, cpf, rg, status, null, numeroPaginaAtual, qtdePorPagina);
	}

	public List<ClienteFornecedor> pesquisaFornecedor(String nome, String cpf, String rg, String status) {
		
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
	
	public void adicionaVeiculo(Veiculos veiculo, VeiculosOpcionais veiculosOpcionais, CompraVeiculo compraVeiculo){
		
		VeiculosDAO dao1 = new VeiculosDAOImpl(cnn);		
		VeiculosOpcionaisDAO dao2 = new VeiculosOpcionaisDAOImpl(cnn);
		CompraVeiculoDAO dao3 = new CompraVeiculoDAOImpl(cnn);
		
		dao1.adicionaVeiculo(veiculo);
		
		Veiculos veiculoInserido = dao1.pesquisaVeiculo(0, veiculo.getPlaca(), veiculo.getMarca()
									, veiculo.getModelo(), veiculo.getVersao(), String.valueOf(veiculo.getAnoMod())
									, String.valueOf(veiculo.getAnoFab()), String.valueOf(veiculo.getRenavan())
									, veiculo.getChassi(), veiculo.getCombustivel(), veiculo.getCor(), null).get(0);
		
		veiculosOpcionais.setVeiculo_id(veiculoInserido.getId());
		compraVeiculo.setVeiculo_id(veiculoInserido.getId());
		
		dao2.adicionaOpcionaisVeiculos(veiculosOpcionais);		
		dao3.adicionaCompraVeiculo(compraVeiculo);		
	}
	
	public boolean existeVeiculoAtivo(String placa){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
						
		return dao.existeVeiculoAtivo(placa, 0);
	}
	
	public List<Estados> getSiglaEstados(){
		
		EstadosDAO dao = new EstadosDAOImpl(cnn);
		
		return dao.listaSiglaEstados();		
	}
	
	public List<CombustivelVeiculos> getCombustivelVeiculos(){
		
		CombustivelVeiculosDAO dao = new CombustivelVeiculosDAOImpl(cnn);
		
		return dao.listarCombustivelVeiculos();
	}
	
	public List<MarcasVeiculos> getMarcasVeiculos(){
		
		MarcasVeiculosDAO dao = new MarcasVeiculosDAOImpl(cnn);
		
		return dao.listaMarcasVeiculos();
	}

}
