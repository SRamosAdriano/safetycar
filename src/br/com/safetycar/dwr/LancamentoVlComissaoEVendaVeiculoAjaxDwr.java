package br.com.safetycar.dwr;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.VlComissaoEVendaVeiculo;
import br.com.safetycar.servico.dao.LancamentoVlComissaoEVendaVeiculoDAO;
import br.com.safetycar.servico.dao.impl.LancamentoVlComissaoEVendaVeiculoDAOImpl;

public class LancamentoVlComissaoEVendaVeiculoAjaxDwr {
	
	private static Logger LOG = Logger.getLogger(LancamentoVlComissaoEVendaVeiculoAjaxDwr.class.getName());
	
	public List<String> addVlComissaoEVendaVeiculo(String idVeiculo, String vlVenda, String vlComissao, String vlBonus){
		
		List<String> retorno = new ArrayList<String>();
				
		if(idVeiculo != null){
			if(idVeiculo.equals("")){
				idVeiculo = null;
			}
		}
		if(vlVenda != null){
			if(vlVenda.equals("")){
				vlVenda = null;
			}
		}
		if(vlComissao != null){
			if(vlComissao.equals("")){
				vlComissao = null;
			}
		}
		if(vlBonus != null){
			if(vlBonus.equals("")){
				vlBonus = null;
			}
		}
		
		if (vlVenda == null){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o valor de Venda");
			return retorno;		
		}else{
			retorno.add(0, "NAOERRO");
			retorno.add(1, "DADOS OK");
		}		
		
		if(!retorno.get(0).equals("ERRO")){
			Connection connectionBD = new ConnectionFactory().getConnection();
			
			LancamentoVlComissaoEVendaVeiculoDAO dao = new LancamentoVlComissaoEVendaVeiculoDAOImpl(connectionBD);
			
			
			VlComissaoEVendaVeiculo vlComiVenda = new VlComissaoEVendaVeiculo();
			
			vlComiVenda.setVeiculo_id(Long.valueOf(idVeiculo));
			
			if(vlVenda != null){
				vlVenda = vlVenda.replace("R$", "");
			}
			if(vlVenda != null && !vlVenda.equals("") && !vlVenda.equals("0") && vlVenda.length() > 1){							
				vlComiVenda.setValorVenda(new Double(vlVenda.substring(0,vlVenda.length()-3).replace(".", "") + vlVenda.substring(vlVenda.length()-3).replace(",", ".")));
			}else if(vlVenda != null && vlVenda.length() == 1){
				vlComiVenda.setValorVenda(Double.valueOf(vlVenda));			
			}
			if(vlComissao != null){
				vlComissao = vlComissao.replace("R$", "");
			}
			if(vlComissao != null && !vlComissao.equals("") && !vlComissao.equals("0") && vlComissao.length() > 1){							
				vlComiVenda.setValorComissao(new Double(vlComissao.substring(0,vlComissao.length()-3).replace(".", "") + vlComissao.substring(vlComissao.length()-3).replace(",", ".")));
			}else if(vlComissao != null &&vlComissao.length() == 1){
				vlComiVenda.setValorComissao(Double.valueOf(vlComissao));			
			}
			if(vlBonus != null){
				vlBonus = vlBonus.replace("R$", "");
			}
			if(vlBonus != null && !vlBonus.equals("") && !vlBonus.equals("0") && vlBonus.length() > 1){						
				vlComiVenda.setValorBonus(new Double(vlBonus.substring(0,vlBonus.length()-3).replace(".", "") + vlBonus.substring(vlBonus.length()-3).replace(",", ".")));
			}else if(vlBonus != null && vlBonus.length() == 1){
				vlComiVenda.setValorBonus(Double.valueOf(vlBonus));			
			}			
			
			if(dao.existeVlComissaoEVendaVeiculo(vlComiVenda.getVeiculo_id())){
				vlComiVenda.setId(dao.pesquisaVlComissaoEVendaVeiculo(vlComiVenda.getVeiculo_id()).getId());
				dao.atualizaVlComissaoEVendaVeiculo(vlComiVenda);
			}else{				
				dao.adicionaVlComissaoEVendaVeiculo(vlComiVenda);
			}			
						
			try {
				connectionBD.close();				
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}			
		}		
		return retorno;
	}
}
