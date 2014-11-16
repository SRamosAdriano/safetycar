package br.com.safetycar.servico.dao;

import java.util.List;

import br.com.safetycar.modelos.ClienteFornecedor;
import br.com.safetycar.modelos.CombustivelVeiculos;
import br.com.safetycar.modelos.CompraVeiculo;
import br.com.safetycar.modelos.Estados;
import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.modelos.VeiculosOpcionais;

public interface CadastroVeiculoDAO {
		
	public void adicionaVeiculo(Veiculos veiculo, VeiculosOpcionais veiculosOpcionais, CompraVeiculo compraVeiculo);
	
	public boolean existeVeiculoAtivo(String placa);
	
	public int countFornecedor(String nome, String cpf, String rg, String status);
	
	public List<ClienteFornecedor> pesquisaFornecedor(String nome, String cpf, String rg, String status, int numeroPaginaAtual, int qtdePorPagina);
	
	public List<ClienteFornecedor> pesquisaFornecedor(String nome, String cpf, String rg, String status);
	
	public ClienteFornecedor pesquisaFornecedor(long id);
	
	public List<Estados> getSiglaEstados();
	
	public List<CombustivelVeiculos> getCombustivelVeiculos();
	
	public List<MarcasVeiculos> getMarcasVeiculos();

}
