package br.com.safetycar.servico.dao;

import java.util.List;

import br.com.safetycar.modelos.ClienteFornecedor;
import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.modelos.VendaVeiculo;
import br.com.safetycar.modelos.Vendedor;

public interface CadastroVendaVeiculoDAO {
	
	public int countVeiculos(String placa, String marca, String modelo);
	
	public List<Veiculos> pesquisaVeiculo(String placa, String marca, String modelo, int numeroPaginaAtual, int qtdePorPagina);
	
	public List<Veiculos> pesquisaVeiculo(String placa, String marca, String modelo);
	
	public Veiculos pesquisaVeiculo(long id);
	
	public String buscaOpcionaisVeiculo(long idVeiculo);
	
	public List<MarcasVeiculos> getMarcasVeiculos();
	
	public double getValorVendaVeiculos(long idVeiculo);
	
	public List<Vendedor> getVendedores();
	
	public int countCliente(String nome, String cpf, String rg, String status);
	
	public List<ClienteFornecedor> pesquisaCliente(String nome, String cpf, String rg, String status, int numeroPaginaAtual, int qtdePorPagina);
	
	public List<ClienteFornecedor> pesquisaCliente(String nome, String cpf, String rg, String status);
	
	public ClienteFornecedor pesquisaCliente(long id);	
	
	public void adicionaVendaVeiculo(Veiculos veiculo, VendaVeiculo vendaVeiculo);

}
