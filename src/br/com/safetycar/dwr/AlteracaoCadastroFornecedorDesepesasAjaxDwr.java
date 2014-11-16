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
import br.com.safetycar.modelos.FornecedorDespesas;
import br.com.safetycar.servico.dao.AlteracaoCadastroFornecedorDespesasDAO;
import br.com.safetycar.servico.dao.impl.AlteracaoCadastroFornecedorDespesasDAOImpl;

public class AlteracaoCadastroFornecedorDesepesasAjaxDwr {
	
	private static Logger LOG = Logger.getLogger(AlteracaoCadastroFornecedorDesepesasAjaxDwr.class.getName());
	
	public List<String> altFornecedorDespesas(FornecedorDespesas fornecedorDespesas, String dtCadastro){
		
		List<String> retorno = new ArrayList<String>();
		
		retorno = validaTudo(fornecedorDespesas);
		
		if(dtCadastro != null){
			try {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(format.parse(dtCadastro));
				fornecedorDespesas.setDtCadastro(calendar);
			} catch (ParseException e) {
				LOG.error(e.getMessage(), e.getCause());
				e.printStackTrace();
			}
		}		
		if(!retorno.get(0).equals("ERRO")){
			Connection connectionBD = new ConnectionFactory().getConnection();
			AlteracaoCadastroFornecedorDespesasDAO dao = new AlteracaoCadastroFornecedorDespesasDAOImpl(connectionBD);
			
			if(dao.existeFornecedorDespesas(fornecedorDespesas.getId(), fornecedorDespesas.getNome())){
				retorno.add(0, "ERRO");
				retorno.add(1, "O Fornecedor  " + fornecedorDespesas.getNome() + " já está cadastrado");
				return retorno;
			}			
			/*Atualiza*/
			dao.atualizaFornecedorDespesas(fornecedorDespesas);
			
			retorno.add(1, "Fornecedor adicionado com sucesso");
			
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
	
	
	private List<String> validaTudo(FornecedorDespesas fornecedorDespesas) {
		
		List<String> retorno = new ArrayList<String>();
				
		if (fornecedorDespesas.getNome() == null
				|| fornecedorDespesas.getNome().replaceAll(" ", "").length() == 0
				|| fornecedorDespesas.getNome().length() < 3) {
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Nome do Fornecedor");
			return retorno;
		}else if (fornecedorDespesas.getContato() == null
				|| fornecedorDespesas.getContato().replaceAll(" ", "").length() == 0
				|| fornecedorDespesas.getContato().length() < 2) {
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Contato");
			return retorno;
		}else{
			retorno.add(0, "NAOERRO");
			retorno.add(1, "DADOS OK");
			return retorno;
		}		
	}

}
