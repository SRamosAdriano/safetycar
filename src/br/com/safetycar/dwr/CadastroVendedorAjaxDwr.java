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
import br.com.safetycar.modelos.Vendedor;
import br.com.safetycar.servico.dao.CadastroVendedorDAO;
import br.com.safetycar.servico.dao.impl.CadastroVendedorDAOImpl;

public class CadastroVendedorAjaxDwr {
	
	private static Logger LOG = Logger.getLogger(CadastroVendedorAjaxDwr.class.getName());
	
	public List<String> addVendedor(Vendedor vendedor, String dataCadastro){
		
		List<String> retorno = new ArrayList<String>();
		
		retorno = validaTudo(vendedor);
		if(retorno.get(0).equals("ERRO")){
			return retorno;
		}
		
		if(dataCadastro != null && (dataCadastro.length() < 10 || dataCadastro.length() > 10)){
			retorno.add(0, "ERRO");
			retorno.add(1, "Data Cadastro Inválida");
			return retorno;			
		}
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(format.parse(dataCadastro));
			vendedor.setDataCadastro(calendar);
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e.getCause());
			e.printStackTrace();
		}
		
		if(!retorno.get(0).equals("ERRO")){
			Connection connectionBD = new ConnectionFactory().getConnection();
			CadastroVendedorDAO dao = new CadastroVendedorDAOImpl(connectionBD);
			
			if(dao.existeVendedor(vendedor.getNome(), vendedor.getSobreNome())){
				retorno.add(0, "ERRO");
				retorno.add(1, "O Vendedor  " + vendedor.getNome() + " já está cadastrado");
				return retorno;
			}			
			/*Adiciona*/
			dao.adicionaVendedor(vendedor);
			
			retorno.add(1, "Vendedor adicionado com sucesso");
			
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
	
	
	private List<String> validaTudo(Vendedor vendedor) {
		
		List<String> retorno = new ArrayList<String>();
				
		if (vendedor.getNome() == null
				|| vendedor.getNome().replaceAll(" ", "").length() == 0
				|| vendedor.getNome().length() < 3) {
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Nome do Vendedor");
			return retorno;		
		}else{
			retorno.add(0, "NAOERRO");
			retorno.add(1, "DADOS OK");
			return retorno;
		}		
	}

}
