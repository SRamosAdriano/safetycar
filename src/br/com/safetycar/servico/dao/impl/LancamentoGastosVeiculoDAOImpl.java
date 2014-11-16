package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;
import java.util.List;

import br.com.safetycar.dao.CompraVeiculoDAO;
import br.com.safetycar.dao.FornecedorDespesasDAO;
import br.com.safetycar.dao.GastosVeiculosDAO;
import br.com.safetycar.dao.MarcasVeiculosDAO;
import br.com.safetycar.dao.VeiculosDAO;
import br.com.safetycar.dao.impl.CompraVeiculoDAOImpl;
import br.com.safetycar.dao.impl.FornecedorDespesasDAOImpl;
import br.com.safetycar.dao.impl.GastosVeiculosDAOImpl;
import br.com.safetycar.dao.impl.MarcasVeiculosDAOImpl;
import br.com.safetycar.dao.impl.VeiculosDAOImpl;
import br.com.safetycar.modelos.CompraVeiculo;
import br.com.safetycar.modelos.FornecedorDespesas;
import br.com.safetycar.modelos.GastosVeiculos;
import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.Veiculos;

import br.com.safetycar.servico.dao.LancamentoGastosVeiculoDAO;

public class LancamentoGastosVeiculoDAOImpl implements LancamentoGastosVeiculoDAO{
	
	private Connection cnn;
	
	public LancamentoGastosVeiculoDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public void adicionaGastosVeiculo(GastosVeiculos gastosVeiculos){
		
		GastosVeiculosDAO dao = new GastosVeiculosDAOImpl(cnn);
		
		dao.adicionaGastosVeiculo(gastosVeiculos);
	}
	
	public void removeGastosVeiculo(long idGastoVeiculo){
		
		GastosVeiculosDAO dao = new GastosVeiculosDAOImpl(cnn);
		
		dao.removeGastosVeiculo(idGastoVeiculo, 0);
		
	}
	
	public List<GastosVeiculos> buscaGastosVeiculo (long veiculoId){
		
		GastosVeiculosDAO dao = new GastosVeiculosDAOImpl(cnn);
		
		return dao.buscaGastosVeiculo(0, veiculoId, 0);
		
	}
	
	public boolean existeGastosVeiculo (String descricao, double valor, long veiculoId, long fornecedorDespesasId){
		
		GastosVeiculosDAO dao = new GastosVeiculosDAOImpl(cnn);
		
		return dao.existeGastosVeiculo(0, descricao, valor, veiculoId, fornecedorDespesasId);
		
	}
	
	public int countVeiculo(String placa, String marca, String modelo){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
		
		return dao.countVeiculos(0, placa, marca, modelo, null, null, null, null, null, null, null, null);
	}
	
	public List<Veiculos> pesquisaVeiculo(String placa, String marca, String modelo, int numeroPaginaAtual, int qtdePorPagina){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
		
		return dao.pesquisaVeiculo(0, placa, marca, modelo, null, null, null, null, null, null, null, null, numeroPaginaAtual, qtdePorPagina);
	}
	
	public List<Veiculos> pesquisaVeiculo(String placa, String marca, String modelo){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
		
		return dao.pesquisaVeiculo(0, placa, marca, modelo, null, null, null, null, null, null, null, "Ativo");
	}
	
	public Veiculos pesquisaVeiculo(long id){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
		
		List<Veiculos> veiculos = dao.pesquisaVeiculo(id, null, null, null, null, null, null, null, null, null, null, null);
		
		return veiculos.size() == 0 ? null : veiculos.get(0);
	}
	
	public List<MarcasVeiculos> getMarcasVeiculos(){
		
		MarcasVeiculosDAO dao = new MarcasVeiculosDAOImpl(cnn);
		
		return dao.listaMarcasVeiculos();
	}
	
	public int countFornecedorDespesas(String nome, String contato, String telefone){
		
		FornecedorDespesasDAO dao = new FornecedorDespesasDAOImpl(cnn);
		
		return dao.countFornecedorDespesas(0, nome, contato, telefone);
	}
	
	public List<FornecedorDespesas> pesquisaFornecedorDespesas(String nome, String contato, String telefone, int numeroPaginaAtual, int qtdePorPagina){
		
		FornecedorDespesasDAO dao = new FornecedorDespesasDAOImpl(cnn);
		
		return dao.pesquisaFornecedorDespesas(0, nome, contato, telefone, numeroPaginaAtual, qtdePorPagina);
	}
	
	public List<FornecedorDespesas> pesquisaFornecedorDespesas(String nome, String contato, String telefone){
		
		FornecedorDespesasDAO dao = new FornecedorDespesasDAOImpl(cnn);
		
		return dao.pesquisaFornecedorDespesas(0, nome, contato, telefone);
	}
	
	public FornecedorDespesas pesquisaFornecedorDespesas(long id){
		
		FornecedorDespesasDAO dao = new FornecedorDespesasDAOImpl(cnn);
		
		List<FornecedorDespesas> fornecedorDespesas = dao.pesquisaFornecedorDespesas(id, null, null, null); 
		
		return fornecedorDespesas.size() == 0 ? null : fornecedorDespesas.get(0);	
	}
	
	public CompraVeiculo buscaCompraVeiculo(long veiculoId){
		
		CompraVeiculoDAO dao = new CompraVeiculoDAOImpl(cnn);
		
		List<CompraVeiculo> compraVeiculo = dao.buscaCompraVeiculo(veiculoId, 0);
		
		return compraVeiculo.size() == 0 ? null : compraVeiculo.get(0);	
	}

}
