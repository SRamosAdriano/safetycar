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
import br.com.safetycar.modelos.ClienteFornecedor;
import br.com.safetycar.servico.dao.CadastroClienteFornecedorDAO;
import br.com.safetycar.servico.dao.impl.CadastroClienteFornecedorDAOImpl;

public class CadastroCliForAjaxDwr {	
	
	private static Logger LOG = Logger.getLogger(CadastroCliForAjaxDwr.class.getName());
	
	public List<String> addClienteFornecedor(ClienteFornecedor clienteFornecedor, String dataCadastro){		
		List<String> retorno = new ArrayList<String>();
		
		retorno = validaTudo(clienteFornecedor);
		
		if(dataCadastro != null){
			if(dataCadastro.length() < 10 || dataCadastro.length() > 10){
				retorno.add(0, "ERRO");
				retorno.add(1, "Data Cadastro Inv�lida");
				return retorno;
			}
		}			
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(format.parse(dataCadastro));
			clienteFornecedor.setDataCadastro(calendar);
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e.getCause());
			e.printStackTrace();
		}				
		
		if(!retorno.get(0).equals("ERRO")){
			Connection connectionBD = new ConnectionFactory().getConnection();
			CadastroClienteFornecedorDAO dao = new CadastroClienteFornecedorDAOImpl(connectionBD);
			
			if(dao.existeCpfCadastrado(clienteFornecedor.getCpf())){
				retorno.add(0, "ERRO");
				retorno.add(1, "O CPF:  " + clienteFornecedor.getCpf() + " j� est� cadastrado");
				return retorno;
			}			
			/*Adiciona*/
			dao.adicionaClienteFornecedor(clienteFornecedor);
			
			retorno.add(1, "Cliente adicionado com sucesso");
			
			try {
				connectionBD.close();				
			} catch (SQLException e) {	
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}
		}
		
		/* Retorno primeiro parametro (ERRO / NAOERRO) e depois  a MENSAGEM */
		return retorno;
	}
	

	/*
	 * Valida��o dos campos obrigat�rios menos data de cadastro
	 *  retornos (ERRO e NAOERRO) e (MENSAGEM)
	 */
	private List<String> validaTudo(ClienteFornecedor cliFor) {
		
		List<String> retorno = new ArrayList<String>();
		
		if (cliFor.getNomeCompleto() == null
				|| cliFor.getNomeCompleto().replaceAll(" ", "").length() == 0
				|| cliFor.getNomeCompleto().length() < 3) {
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Nome Completo do "+ cliFor.getTipoCliFor());
			return retorno;
		} else if (cliFor.getRg() == null
					|| cliFor.getRg().replaceAll(" ", "").length() == 0
					|| cliFor.getRg().length() < 3) {
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o RG do "+ cliFor.getTipoCliFor());
			return retorno;
		}else if (cliFor.getCpf() == null
					|| cliFor.getCpf().replaceAll(" ", "").length() == 0
					|| cliFor.getCpf().length() < 3) {
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o CPF do "+ cliFor.getTipoCliFor());
			return retorno;
		}else if (validaCpf(cliFor.getCpf()) == false){
			retorno.add(0, "ERRO");
			retorno.add(1, "CPF invalido");
			return retorno;
		}else if (cliFor.getEndereco() == null
					|| cliFor.getEndereco().replaceAll(" ", "").length() == 0
					|| cliFor.getEndereco().length() < 3){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Endere�o do "+ cliFor.getTipoCliFor());
			return retorno;
		}else if (cliFor.getBairro() == null
					|| cliFor.getBairro().replaceAll(" ", "").length() == 0
					|| cliFor.getBairro().length() < 3){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Bairro do " + cliFor.getTipoCliFor());
			return retorno;
		}else if (cliFor.getCep() == null
					|| cliFor.getCep().replaceAll(" ", "").length() == 0
					|| cliFor.getCep().replaceAll("-", "").length() < 8
					|| cliFor.getCep().replaceAll("-", "").length() > 8){
			retorno.add(0, "ERRO");
			retorno.add(1, "CEP inv�lido");
			return retorno;
		}else if (cliFor.getCidade() == null
					|| cliFor.getCidade().replaceAll(" ", "").length() == 0
					|| cliFor.getCidade().length() < 3){
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Cidade do "+ cliFor.getTipoCliFor());
			return retorno;
		}else if (cliFor.getEstado().equals("Selecione")) {
			retorno.add(0, "ERRO");
			retorno.add(1, "Selecione o Estado  do "+ cliFor.getTipoCliFor());
			return retorno;
		}else if (cliFor.getTelefone() == null
					|| cliFor.getTelefone().replaceAll(" ", "").length() == 0
					|| cliFor.getTelefone().length() < 12) {
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Telefone do "+ cliFor.getTipoCliFor());
			return retorno;
		}else if (cliFor.getCelular() == null
					|| cliFor.getCelular().replaceAll(" ", "").length() == 0
					|| cliFor.getCelular().length() < 12) {
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Celular do "+ cliFor.getTipoCliFor());
			return retorno;
		}else {
			retorno.add(0, "NAOERRO");
			retorno.add(1, "DADOS OK");
			return retorno;
		}
	}		
		
	/*
	 * Valida��o do CPF
	 */
	private static boolean validaCpf(String cpf) {
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		cpf = cpf.replace("/", "");
		cpf = cpf.replace(" ", "");

		if(cpf.length() < 11 || cpf.length() > 11 ){
			return false;
		}
		
		int[] calcPrimDigito = { 10, 9, 8, 7, 6, 5, 4, 3, 2 };
		int[] calcSegDigito = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
		int[] numCpf = new int[cpf.length()];

		for (int i = 0; i < numCpf.length; i++) {
			numCpf[i] = Integer.parseInt(cpf.substring(i, i + 1));
		}
		int primDigito = 0;
		for (int i = 0; i < calcPrimDigito.length; i++) {
			primDigito = primDigito + (calcPrimDigito[i] * numCpf[i]);
		}
		primDigito = primDigito % 11;
		if (primDigito < 2) {
			primDigito = 0;
		} else {
			primDigito = 11 - primDigito;
		}
		if (primDigito == numCpf[9]) {
			int segDigito = 0;
			for (int i = 0; i < numCpf.length - 1; i++) {
				segDigito = segDigito + (calcSegDigito[i] * numCpf[i]);
			}
			segDigito = segDigito % 11;
			if (segDigito < 2) {
				segDigito = 0;
			} else {
				segDigito = 11 - segDigito;
			}
			if (segDigito != numCpf[10]) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
}
