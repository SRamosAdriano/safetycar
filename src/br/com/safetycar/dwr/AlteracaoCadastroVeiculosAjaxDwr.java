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
import br.com.safetycar.modelos.CompraVeiculo;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.modelos.VeiculosOpcionais;
import br.com.safetycar.servico.dao.AlteracaoCadastroVeiculoDAO;
import br.com.safetycar.servico.dao.impl.AlteracaoCadastroVeiculoDAOImpl;

public class AlteracaoCadastroVeiculosAjaxDwr {
	
	private static Logger LOG = Logger.getLogger(AlteracaoCadastroVeiculosAjaxDwr.class.getName());
	
	public List<String> altVeiculo(Veiculos veiculo, VeiculosOpcionais veiculosOpcionais, CompraVeiculo compraVeiculo, String dataEntrada, String valorCompra){		
		List<String> retorno = new ArrayList<String>();
		
		retorno = validaTudo(veiculo, veiculosOpcionais, compraVeiculo, dataEntrada, valorCompra);
		
		if(!retorno.get(0).equals("ERRO")){
			Connection connectionBD = new ConnectionFactory().getConnection();
			AlteracaoCadastroVeiculoDAO dao = new AlteracaoCadastroVeiculoDAOImpl(connectionBD);
			
			if(dao.existeVeiculoAtivo(veiculo.getPlaca(), veiculo.getId())){
				retorno.add(0, "ERRO");
				retorno.add(1, "O veiculo de placa: " + veiculo.getPlaca() + " já esta Cadastrado e ativo no sistema. ");
				return retorno;
			}
			
			if(valorCompra != null){
				valorCompra = valorCompra.replace("R$", "");
			}			
			if(valorCompra != null && !valorCompra.equals("") && !valorCompra.equals("0") && valorCompra.length() > 1){			
				compraVeiculo.setValorCompra(new Double(valorCompra.substring(0,valorCompra.length()-3).replace(".", "") + valorCompra.substring(valorCompra.length()-3).replace(",", ".")));
			}else if(valorCompra != null && valorCompra.length() == 1){
				compraVeiculo.setValorCompra(Double.valueOf(valorCompra));			
			}	
			
			try {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(format.parse(dataEntrada));
				veiculo.setDataCadastro(calendar);
				compraVeiculo.setDataCompra(calendar);
			} catch (ParseException e) {
				LOG.error(e.getMessage(), e.getCause());
				e.printStackTrace();
			}
			
			veiculosOpcionais.setVeiculo_id(veiculo.getId());
			compraVeiculo.setVeiculo_id(veiculo.getId());
			
			/*Altera*/			
			dao.atualizaCadastroVeiculo(veiculo, veiculosOpcionais, compraVeiculo);					
			retorno.add(1, "Veiculo adicionado com sucesso");			
			
			try {
				connectionBD.close();				
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}
			
		}
		
		return retorno;
	}
	
	private List<String> validaTudo(Veiculos veiculo, VeiculosOpcionais veiculosOpcionais, CompraVeiculo compraVeiculo, String dataEntrada, String valorCompra){
		List<String> retorno = new ArrayList<String>();
		
		if(veiculo.getPlaca() == null
				|| veiculo.getPlaca().replaceAll(" ", "").length() == 0
				|| veiculo.getPlaca().length() < 7
				|| veiculo.getPlaca().length() >= 9){			
			retorno.add(0, "ERRO");
			retorno.add(1, "Placa Inválida");
			return retorno;
		}else if(veiculo.getCidade()== null
					||veiculo.getCidade().replaceAll(" ", "").length() == 0){
			retorno.add(0, "ERRO");
			retorno.add(1, "Cidade Inválida");
			return retorno;
		}else if(veiculo.getEstado() == null
					|| veiculo.getEstado().equals("Selecione")){
			retorno.add(0, "ERRO");
			retorno.add(1, "Estado Inválido");
			return retorno;
		}else if(veiculo.getMarca() == null
					|| veiculo.getMarca().equals("Selecione")){						
			retorno.add(0, "ERRO");
			retorno.add(1, "Marca Inválida");
			return retorno;
		}else if(veiculo.getModelo() == null
					|| veiculo.getModelo().replaceAll(" ", "").length() < 2){
			retorno.add(0, "ERRO");
			retorno.add(1, "Modelo Inválida");
			return retorno;
		}else if(veiculo.getVersao() == null
					|| veiculo.getVersao().replaceAll(" ", "").length() < 2){
			retorno.add(0, "ERRO");
			retorno.add(1, "Versão Inválida");
			return retorno;							
		}else if(veiculo.getAnoFab() < 1880){
			retorno.add(0, "ERRO");
			retorno.add(1, "Ano Fabricação Inválido. Digite um ano superior a 1880");
			return retorno;							
		}else if(veiculo.getAnoMod() < 1880){
			retorno.add(0, "ERRO");
			retorno.add(1, "Ano Modelo Inválido. Digite um ano superior a 1880");
			return retorno;
		}else if(veiculo.getRenavan() < 999999){
			retorno.add(0, "ERRO");
			retorno.add(1, "Renavan Inválido");
			return retorno;
		}else if(veiculo.getChassi() == null
					|| veiculo.getChassi().replaceAll(" ", "").length() == 0
					|| veiculo.getChassi().length() < 17){
			retorno.add(0, "ERRO");
			retorno.add(1, "Chassi Inválido. O chassi contem 17 caracteres");
			return retorno;
		}else if(veiculo.getKm() <= 0){
			retorno.add(0, "ERRO");
			retorno.add(1, "Km Inválida");
			return retorno;
		}else if(veiculo.getCombustivel() == null
					|| veiculo.getCombustivel().equals("Selecione")){
			retorno.add(0, "ERRO");
			retorno.add(1, "Combustivel Inválido");
			return retorno;
		}else if(veiculo.getCor() == null
					|| veiculo.getCor().replaceAll(" ", "").length() < 2){
			retorno.add(0, "ERRO");
			retorno.add(1, "Cor Inválida");
			return retorno;
		}else if(valorCompra == null || valorCompra.equals("")){
			retorno.add(0, "ERRO");
			retorno.add(1, "Valor Compra Inválido");
			return retorno;
		}else if(veiculo.getInfDocNome() == null
					|| veiculo.getInfDocNome().replaceAll(" ", "").length() < 2){
			retorno.add(0, "ERRO");
			retorno.add(1, "Nome Informação Documento Inválido");
			return retorno;
		}else if(veiculo.getInfDocEndereco() == null
					|| veiculo.getInfDocEndereco().replaceAll(" ", "").length() < 2){
			retorno.add(0, "ERRO");
			retorno.add(1, "Endereço Informação Documento Inválido");
			return retorno;
		}else if (compraVeiculo.getFornecedor_id() == 0.0){
			retorno.add(0, "ERRO");
			retorno.add(1, "Selecione o fornecedor do veiculo");
			return retorno;
		}else if (dataEntrada == null
					|| dataEntrada.equals("")
					|| dataEntrada.length() < 10 
					|| dataEntrada.length() > 10){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite a data de entrada");
			return retorno;
		}else{
			retorno.add(0, "NAOERRO");
			retorno.add(1, "DADOS OK");
			return retorno;
		}
	}

}
