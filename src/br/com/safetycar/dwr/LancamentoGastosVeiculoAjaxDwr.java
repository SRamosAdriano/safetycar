package br.com.safetycar.dwr;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.GastosVeiculos;
import br.com.safetycar.servico.dao.LancamentoGastosVeiculoDAO;
import br.com.safetycar.servico.dao.impl.LancamentoGastosVeiculoDAOImpl;

public class LancamentoGastosVeiculoAjaxDwr {
	
	private static Logger LOG = Logger.getLogger(LancamentoGastosVeiculoAjaxDwr.class.getName());
	
	public List<String> addGastoVeiculo(GastosVeiculos gastoVeiculo, String dtLancamento, String idVeiculo, String idForDesp, String valorGastoVeiculo){
		
		List<String> retorno = new ArrayList<String>();
		
		if(dtLancamento != null){
			if(dtLancamento.length() < 10 || dtLancamento.length() > 10){
				dtLancamento = null;
			}
		}		
		if(idVeiculo != null){
			if(idVeiculo.equals("")){
				idVeiculo = null;
			}
		}
		if(idForDesp != null){
			if(idForDesp.equals("")){
				idForDesp = null;
			}
		}
		if(valorGastoVeiculo != null){
			if(valorGastoVeiculo.equals("")){
				valorGastoVeiculo = null;
			}
		}
		
		retorno = validaTudo(gastoVeiculo, dtLancamento, idVeiculo, idForDesp, valorGastoVeiculo);
		
		if(!retorno.get(0).equals("ERRO")){
			Connection connectionBD = new ConnectionFactory().getConnection();
			LancamentoGastosVeiculoDAO dao = new LancamentoGastosVeiculoDAOImpl(connectionBD);
			
			gastoVeiculo.setVeiculo_id(Long.valueOf(idVeiculo));
			gastoVeiculo.setFornecedordespesas_id(Long.valueOf(idForDesp));
									
			if(valorGastoVeiculo != null){
				valorGastoVeiculo = valorGastoVeiculo.replace("R$", "");
			}
			if(valorGastoVeiculo != null && !valorGastoVeiculo.equals("") && !valorGastoVeiculo.equals("0") && valorGastoVeiculo.length() > 1){							
				gastoVeiculo.setValor(new Double(valorGastoVeiculo.substring(0,valorGastoVeiculo.length()-3).replace(".", "") + valorGastoVeiculo.substring(valorGastoVeiculo.length()-3).replace(",", ".")));
			}else if(valorGastoVeiculo != null && valorGastoVeiculo.length() == 1){
				gastoVeiculo.setValor(Double.valueOf(valorGastoVeiculo));			
			}
			
			try {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(format.parse(dtLancamento));
				gastoVeiculo.setDtLancamento(calendar);				
			} catch (ParseException e) {
				LOG.error(e.getMessage(), e.getCause());
				e.printStackTrace();
			}
			if(dao.existeGastosVeiculo(gastoVeiculo.getDescricao(), gastoVeiculo.getValor(), gastoVeiculo.getVeiculo_id(), gastoVeiculo.getFornecedordespesas_id())){
				retorno.add(0, "ERRO");
				retorno.add(1, "Dados ja estão dadastrados");
				return retorno;		
			}else{
				/*Adiciona*/
				dao.adicionaGastosVeiculo(gastoVeiculo);
				retorno.add(1, "Gasto veiculo adicionado com sucesso");
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
	
	private List<String> validaTudo(GastosVeiculos gastoVeiculo, String dtLancamento, String idVeiculo, String idForDesp, String valorGastoVeiculo){
		List<String> retorno = new ArrayList<String>();
		
		if (idVeiculo == null){
			retorno.add(0, "ERRO");
			retorno.add(1, "Veiculo não selecionado");
			return retorno;		
		}else if (idForDesp == null){
			retorno.add(0, "ERRO");
			retorno.add(1, "Fornecedor Despesa não selecionado");
			return retorno;		
		} else if (gastoVeiculo.getDescricao() == null
					||gastoVeiculo.getDescricao().replaceAll(" ", "").length() == 0){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite a Descrição");
			return retorno;
		}else if (valorGastoVeiculo == null){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Valor do Gasto");
			return retorno;
		} else if (dtLancamento == null){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite a Data de Lançamento");
			return retorno;
		}else{
			retorno.add(0, "NAOERRO");
			retorno.add(1, "DADOS OK");
			return retorno;
		}
	}
}
