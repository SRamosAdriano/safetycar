package br.com.safetycar.relatorios.dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import br.com.safetycar.dao.FornecedorDespesasDAO;
import br.com.safetycar.dao.MarcasVeiculosDAO;
import br.com.safetycar.dao.impl.FornecedorDespesasDAOImpl;
import br.com.safetycar.dao.impl.MarcasVeiculosDAOImpl;
import br.com.safetycar.modelos.FornecedorDespesas;
import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.relatorios.RelatorioGastosVeiculos;
import br.com.safetycar.relatorios.dao.RelatorioGastosVeiculosDAO;

public class RelatorioGastosVeiculosDAOImpl implements RelatorioGastosVeiculosDAO{
	
	private static Logger LOG = Logger.getLogger(RelatorioGastosVeiculosDAOImpl.class.getName());
	
	private Connection cnn;
	
	public RelatorioGastosVeiculosDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public List<RelatorioGastosVeiculos> pesquisaGastosVeiculos(String dtInicio, String dtFim, String placa, String marca, String modelo, String nomeFornecedor){
		
		if(dtInicio != null && dtInicio.equals("")){
			dtInicio = null;
		}
		if(dtFim != null && dtFim.equals("")){
			dtFim = null;
		}
		if(placa != null && placa.equals("")){
			placa = null;
		}
		if(marca != null && marca.equals("")){
			marca = null;
		}
		if(modelo != null && modelo.equals("")){
			modelo = null;
		}
		if(nomeFornecedor != null && nomeFornecedor.equals("")){
			nomeFornecedor = null;
		}
		
		List<RelatorioGastosVeiculos> relatorio = new ArrayList<RelatorioGastosVeiculos>();
		
		try {			
			String strSql = "select veiculo.placa, veiculo.marca, veiculo.modelo " +
					        "       , gastos.nDocumento, gastos.dtLancamento as dtGasto, gastos.descricao, gastos.valor " +
					        "       , fornecedor.nome as nomeFornecedor " +
					        "  from veiculo as veiculo " +
					        "       , gastosveiculos as gastos " +
					        "       , fornecedordespesas as fornecedor " +
					        " where veiculo.id = gastos.veiculo_id " +
					        "   and fornecedor.id = gastos.fornecedordespesas_id ";
			
			if(dtInicio != null && dtFim != null){
				strSql = strSql + " and gastos.dtLancamento >='" + dtInicio.trim() + "' ";
				strSql = strSql + " and gastos.dtLancamento <='" + dtFim.trim() + "' ";
			}else if(dtInicio != null && dtFim == null){
				strSql = strSql + " and gastos.dtLancamento ='" + dtInicio.trim() + "' ";
			}
			if(placa != null){
				strSql = strSql + " and veiculo.placa = '" + placa.trim() + "' ";
			}
			if(marca != null){
				strSql = strSql + " and veiculo.marca = '" + marca.trim() + "' ";
			}
			if(modelo != null){
				strSql = strSql + " and veiculo.modelo = '" + modelo.trim() + "' ";
			}
			if(nomeFornecedor != null){
				strSql = strSql + " and fornecedor.nome = '" + nomeFornecedor.trim() + "' ";
			}
			
			strSql = strSql + " order by gastos.dtLancamento, veiculo.placa, veiculo.marca, veiculo.modelo, fornecedor.nome, gastos.nDocumento, gastos.descricao, gastos.valor";
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				RelatorioGastosVeiculos gastoVeiculo = new RelatorioGastosVeiculos();
				
				gastoVeiculo.setPlaca(rs.getString("placa"));
				gastoVeiculo.setMarca(rs.getString("marca"));
				gastoVeiculo.setModelo(rs.getString("modelo"));
				gastoVeiculo.setnDocumento(rs.getString("nDocumento"));
				gastoVeiculo.setDescricaoGasto(rs.getString("descricao"));
				gastoVeiculo.setNomeFornecedor(rs.getString("nomeFornecedor"));
				
				Date data = rs.getDate("dtGasto");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(data);
				gastoVeiculo.setDtGasto(calendar);				
				
				Locale locale = new Locale("pt", "BR" );  
			    NumberFormat formatter = DecimalFormat.getCurrencyInstance(locale);				
			    gastoVeiculo.setValorGasto(formatter.format(Double.valueOf(rs.getString("valor"))));
				
				relatorio.add(gastoVeiculo);
			}
			
			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return relatorio;
	}
	
	public List<MarcasVeiculos> getMarcasVeiculos(){
		
		MarcasVeiculosDAO dao = new MarcasVeiculosDAOImpl(cnn);
		
		return dao.listaMarcasVeiculos();
	}
	
	public List<String> getNomeForcedores(){
		
		FornecedorDespesasDAO dao = new FornecedorDespesasDAOImpl(cnn);
		
		List<FornecedorDespesas> fornecedores = dao.pesquisaFornecedorDespesas(0, null, null, null);
		
		List<String> nomeFornecedores = new ArrayList<String>();
		for (FornecedorDespesas fornecedor : fornecedores) {
			nomeFornecedores.add(fornecedor.getNome());
		}
		return nomeFornecedores;
	}

}
