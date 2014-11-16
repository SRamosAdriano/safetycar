package br.com.safetycar.servico.dao;

import java.util.List;

import br.com.safetycar.modelos.ClienteFornecedor;
import br.com.safetycar.modelos.CombustivelVeiculos;
import br.com.safetycar.modelos.CompraVeiculo;
import br.com.safetycar.modelos.Estados;
import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.modelos.VeiculosOpcionais;

public interface AlteracaoCadastroVeiculoDAO {
	
	public List<Estados> getSiglaEstados();
	
	public List<CombustivelVeiculos> getCombustivelVeiculos();
	
	public List<MarcasVeiculos> getMarcasVeiculos();
	
	public int countVeiculos(String placa, String marca, String modelo);
	
	public List<Veiculos> pesquisaVeiculo(String placa, String marca, String modelo, int numeroPaginaAtual, int qtdePorPagina);
	
	public boolean existeVeiculoAtivo(String placa, long idVeiculo);
	
	public Veiculos pesquisaVeiculo(long id);
	
	public VeiculosOpcionais buscaOpcionaisVeiculo(long idVeiculo);
	
	public CompraVeiculo buscaCompraVeiculo(long veiculoId);
	
	public int countFornecedor(String nome, String cpf, String rg, String status);
	
	public List<ClienteFornecedor> pesquisaFornecedor(String nome, String cpf, String rg, String status , int numeroPaginaAtual, int qtdePorPagina);
	
	public List<ClienteFornecedor> pesquisaFornecedor(String nome, String cpf, String rg, String status);
	
	public ClienteFornecedor pesquisaFornecedor(long id);
	
	public void removeVeiculo (long veiculoId);
	
	public void atualizaCadastroVeiculo(Veiculos veiculo, VeiculosOpcionais veiculosOpcionais, CompraVeiculo compraVeiculo);

}
