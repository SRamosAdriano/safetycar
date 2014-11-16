package br.com.safetycar.dwr;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.Vendedor;
import br.com.safetycar.servico.dao.AlteracaoCadastroVendedorDAO;
import br.com.safetycar.servico.dao.impl.AlteracaoCadastroVendedorDAOImpl;

public class AlteracaoCadastroVendedorAjaxDwr {
	
	private static Logger LOG = Logger.getLogger(AlteracaoCadastroVendedorAjaxDwr.class.getName());
	
	public List<String> altVendedor(Vendedor vendedor){		
		List<String> retorno = new ArrayList<String>();
		
		retorno = validaTudo(vendedor);
		
		if(!retorno.get(0).equals("ERRO")){
			Connection connectionBD = new ConnectionFactory().getConnection();
			AlteracaoCadastroVendedorDAO dao = new AlteracaoCadastroVendedorDAOImpl(connectionBD);
					
			if(dao.existeVendedor(vendedor.getNome(), vendedor.getSobreNome())){
				retorno.add(0, "ERRO");
				retorno.add(1, "O Vendedor:  " + vendedor.getNome() + " já está cadastrado");
				return retorno;
			}	
			/*Atualiza*/
			dao.atualizaVendedor(vendedor);			
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
	 * Validação dos campos obrigatórios menos data de cadastro
	 *  retornos (ERRO e NAOERRO) e (MENSAGEM)
	 */
	private List<String> validaTudo(Vendedor vendedor) {
		
		List<String> retorno = new ArrayList<String>();
		
		if (vendedor.getNome() == null
				|| vendedor.getNome().replaceAll(" ", "").length() == 0
				|| vendedor.getNome().length() < 3) {
			retorno.add(0, "ERRO");
			retorno.add(1, "Digite o Nome Vendedor");
			return retorno;		
		}else {
			retorno.add(0, "NAOERRO");
			retorno.add(1, "DADOS OK");
			return retorno;
		}
	}		

}
