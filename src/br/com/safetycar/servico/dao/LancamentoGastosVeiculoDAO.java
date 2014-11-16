package br.com.safetycar.servico.dao;

import java.util.List;

import br.com.safetycar.modelos.CompraVeiculo;
import br.com.safetycar.modelos.FornecedorDespesas;
import br.com.safetycar.modelos.GastosVeiculos;
import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.Veiculos;

public interface LancamentoGastosVeiculoDAO {
	
	public void adicionaGastosVeiculo(GastosVeiculos gastosVeiculos);
	
	public void removeGastosVeiculo(long idGastoVeiculo);
	
	public List<GastosVeiculos> buscaGastosVeiculo (long veiculoId);
	
	public boolean existeGastosVeiculo (String descricao, double valor, long veiculoId, long fornecedorDespesasId);
	
	public int countVeiculo(String placa, String marca, String modelo);
	
	public List<Veiculos> pesquisaVeiculo(String placa, String marca, String modelo, int numeroPaginaAtual, int qtdePorPagina);
	
	public List<Veiculos> pesquisaVeiculo(String placa, String marca, String modelo);
	
	public Veiculos pesquisaVeiculo(long id);
	
	public List<MarcasVeiculos> getMarcasVeiculos();
	
	public int countFornecedorDespesas(String nome, String contato, String telefone);
	
	public List<FornecedorDespesas> pesquisaFornecedorDespesas(String nome, String contato, String telefone, int numeroPaginaAtual, int qtdePorPagina);
	
	public List<FornecedorDespesas> pesquisaFornecedorDespesas(String nome, String contato, String telefone);
	
	public FornecedorDespesas pesquisaFornecedorDespesas(long id);
	
	public CompraVeiculo buscaCompraVeiculo(long veiculoId);
	

}
