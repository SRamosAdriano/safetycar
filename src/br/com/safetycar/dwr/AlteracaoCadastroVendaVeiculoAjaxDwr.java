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
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.modelos.VendaVeiculo;
import br.com.safetycar.servico.dao.AlteracaoCadastroVendaVeiculoDAO;
import br.com.safetycar.servico.dao.impl.AlteracaoCadastroVendaVeiculoDAOImpl;

public class AlteracaoCadastroVendaVeiculoAjaxDwr {
	
	private static Logger LOG = Logger.getLogger(AlteracaoCadastroVendaVeiculoAjaxDwr.class.getName());
	
	public List<String> altVendaVeiculo(Long idVeiculo, Long idCliente, String valorVenda, String vendedor, String dataVenda){		
		List<String> retorno = new ArrayList<String>();
		
		retorno = validaTudo(idVeiculo, idCliente, vendedor, dataVenda);
		
		if(!retorno.get(0).equals("ERRO")){
			Connection connectionBD = new ConnectionFactory().getConnection();
			
			AlteracaoCadastroVendaVeiculoDAO dao = new AlteracaoCadastroVendaVeiculoDAOImpl(connectionBD);			
			
			VendaVeiculo vendaVeiculo = new VendaVeiculo();
			
			if(!valorVenda.equals("")){
				double vlVendaAlterado = 0d;
				valorVenda = valorVenda.replace("R$", "");
				if(valorVenda.trim().length() > 1){
					vlVendaAlterado = new Double(valorVenda.substring(0,valorVenda.length()-3).replace(".", "") + valorVenda.substring(valorVenda.length()-3).replace(",", "."));
				}else if(valorVenda.trim().length() == 1){
					vlVendaAlterado = Double.valueOf(valorVenda);
				}				
				
				if(dao.getValorVendaVeiculos(idVeiculo) > vlVendaAlterado){
					retorno.add(0, "ERRO");
					retorno.add(1, "O veiculo não pode ser vendido por um valor inferior ao estipulado");
					return retorno;
				}				
				valorVenda = valorVenda.replace("R$", "");
				vendaVeiculo.setValorVenda(vlVendaAlterado);
				vendaVeiculo.setVendedor_id(Long.valueOf(vendedor));
			}else{
				retorno.add(0, "ERRO");
				retorno.add(1, "O veiculo não pode ser vendido por um valor inferior ao estipulado");
				return retorno;
			}
			
			
			
			try {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(format.parse(dataVenda));
				vendaVeiculo.setDataVenda(calendar);				
			} catch (ParseException e) {
				LOG.error(e.getMessage(), e.getCause());
				e.printStackTrace();
			}
			
			vendaVeiculo.setVeiculo_id(idVeiculo);
			vendaVeiculo.setCliente_id(idCliente);
			
			Veiculos veiculo = dao.pesquisaVeiculo(idVeiculo);
			veiculo.setStatus("Vendido");
						
			/*Altera*/
			dao.atualizaVendaVeiculo(veiculo, vendaVeiculo);
			retorno.add(1, "Veiculo alterado com sucesso");			
			
			try {
				connectionBD.close();				
			} catch (SQLException e) {		
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}
			
		}
		
		return retorno;
	}
	
	private List<String> validaTudo(Long idVeiculo, Long idCliente, String vendedor, String dataVenda){
		List<String> retorno = new ArrayList<String>();
		
		if(idVeiculo == null || idVeiculo == 0){			
			retorno.add(0, "ERRO");
			retorno.add(1, "Veiculo não localizado");
			return retorno;
		}else if(idCliente == null || idCliente == 0){
			retorno.add(0, "ERRO");
			retorno.add(1, "Cliente não localizado");
			return retorno;
		}else if(vendedor == null
				|| vendedor.equals("Selecione")){						
			retorno.add(0, "ERRO");
			retorno.add(1, "Vendedor Inválido");
			return retorno;
		}else if (dataVenda == null
				|| dataVenda.equals("")
				|| dataVenda.length() < 10 
				|| dataVenda.length() > 10){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite a data de venda");
			return retorno;
		}
		else{		
			retorno.add(0, "NAOERRO");
			retorno.add(1, "DADOS OK");
			return retorno;
		}
	}

}
