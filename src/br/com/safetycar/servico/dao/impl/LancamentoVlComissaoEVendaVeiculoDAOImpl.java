package br.com.safetycar.servico.dao.impl;

import java.sql.Connection;
import java.util.List;

import br.com.safetycar.dao.CompraVeiculoDAO;
import br.com.safetycar.dao.GastosVeiculosDAO;
import br.com.safetycar.dao.MarcasVeiculosDAO;
import br.com.safetycar.dao.VeiculosDAO;
import br.com.safetycar.dao.VlComissaoEVendaVeiculoDAO;
import br.com.safetycar.dao.impl.CompraVeiculoDAOImpl;
import br.com.safetycar.dao.impl.GastosVeiculosDAOImpl;
import br.com.safetycar.dao.impl.MarcasVeiculosDAOImpl;
import br.com.safetycar.dao.impl.VeiculosDAOImpl;
import br.com.safetycar.dao.impl.VlComissaoEVendaVeiculoDAOImpl;
import br.com.safetycar.modelos.CompraVeiculo;
import br.com.safetycar.modelos.GastosVeiculos;
import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.modelos.VlComissaoEVendaVeiculo;
import br.com.safetycar.servico.dao.LancamentoVlComissaoEVendaVeiculoDAO;

public class LancamentoVlComissaoEVendaVeiculoDAOImpl implements LancamentoVlComissaoEVendaVeiculoDAO{
	
	private Connection cnn;
	
	public LancamentoVlComissaoEVendaVeiculoDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public void adicionaVlComissaoEVendaVeiculo(VlComissaoEVendaVeiculo vlComiVenda){
		
		VlComissaoEVendaVeiculoDAO dao = new VlComissaoEVendaVeiculoDAOImpl(cnn);
		
		dao.adicionaVlComissaoEVendaVeiculo(vlComiVenda);		
	}
	
	public void atualizaVlComissaoEVendaVeiculo(VlComissaoEVendaVeiculo vlComiVenda){
		
		VlComissaoEVendaVeiculoDAO dao = new VlComissaoEVendaVeiculoDAOImpl(cnn);
		
		dao.atualizaVlComissaoEVendaVeiculo(vlComiVenda);
	}	
	
	public VlComissaoEVendaVeiculo pesquisaVlComissaoEVendaVeiculo (long veiculoId){
		
		VlComissaoEVendaVeiculoDAO dao = new VlComissaoEVendaVeiculoDAOImpl(cnn);
		
		List<VlComissaoEVendaVeiculo> vlComissaoEVendaVeiculo = dao.pesquisaVlComissaoEVendaVeiculo(0, 0, 0, 0, veiculoId);
		
		return vlComissaoEVendaVeiculo.size() == 0 ? null : vlComissaoEVendaVeiculo.get(0);
	}
	
	public boolean existeVlComissaoEVendaVeiculo (long veiculoId){
		
		VlComissaoEVendaVeiculoDAO dao = new VlComissaoEVendaVeiculoDAOImpl(cnn);
		
		return dao.existeVlComissaoEVendaVeiculo(0, veiculoId);
		
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
		
		return dao.pesquisaVeiculo(0, placa, marca, modelo, null, null, null, null, null, null, null,"Ativo");
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
	
	public CompraVeiculo buscaCompraVeiculo(long veiculoId){
		
		CompraVeiculoDAO dao = new CompraVeiculoDAOImpl(cnn);
		
		List<CompraVeiculo> compraVeiculo = dao.buscaCompraVeiculo(veiculoId, 0);
		
		return compraVeiculo.size() == 0 ? null : compraVeiculo.get(0);	
	}
	
	public List<GastosVeiculos> buscaGastosVeiculo (long veiculoId){
		
		GastosVeiculosDAO dao = new GastosVeiculosDAOImpl(cnn);
		
		return dao.buscaGastosVeiculo(0, veiculoId, 0);
		
	}

}
