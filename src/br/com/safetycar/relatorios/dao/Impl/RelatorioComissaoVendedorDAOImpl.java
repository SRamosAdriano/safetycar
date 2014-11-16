package br.com.safetycar.relatorios.dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.dao.VendedorDAO;
import br.com.safetycar.dao.impl.VendedorDAOImpl;
import br.com.safetycar.modelos.Vendedor;
import br.com.safetycar.modelos.relatorios.RelatorioComissaoVendedor;
import br.com.safetycar.relatorios.dao.RelatorioComissaoVendedorDAO;

public class RelatorioComissaoVendedorDAOImpl implements RelatorioComissaoVendedorDAO{
	
	private static Logger LOG = Logger.getLogger(RelatorioComissaoVendedorDAOImpl.class.getName());
	
	private Connection cnn;
	
	public RelatorioComissaoVendedorDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public List<RelatorioComissaoVendedor> pesquisaComissaoVendedor(String dtInicio, String dtFim, String nomeVendedor){
		
		if(dtInicio != null && dtInicio.equals("")){
			dtInicio = null;
		}
		if(dtFim != null && dtFim.equals("")){
			dtFim = null;
		}
		if(nomeVendedor != null && nomeVendedor.equals("")){
			nomeVendedor = null;
		}
		
		List<RelatorioComissaoVendedor> relatorio = new ArrayList<RelatorioComissaoVendedor>();
		
		try {			
			String strSql = "select veiculo.placa,veiculo.marca,veiculo.modelo,veiculo.versao " +
					        "       , venda.dataVenda " +
					        "       , vendedor.nome as nomeVendedor " +
					        "       , ifnull(vlComiVenda.valorComissao,0) as valorComissao, ifnull(vlComiVenda.valorBonus,0) as valorBonus " +
					        "       , ifnull(vlComiVenda.valorComissao,0) + ifnull(vlComiVenda.valorBonus,0) as total " +
					        "  from veiculo as veiculo " +
					        "       , vendaveiculo as venda " +
					        "       , vendedor as vendedor " +
					        "       , vlcomissaoevendaveiculo as vlComiVenda " +
					        " where venda.veiculo_id = veiculo.id " +
					        "   and venda.vendedor_id = vendedor.id " +
					        "   and vlComiVenda.veiculo_id = veiculo.id ";			
			
			if(dtInicio != null && dtFim != null){
				strSql = strSql + " and venda.dataVenda >='" + dtInicio.trim() + "' ";
				strSql = strSql + " and venda.dataVenda <='" + dtFim.trim() + "' ";
			}else if(dtInicio != null && dtFim == null){
				strSql = strSql + " and venda.dataVenda ='" + dtInicio.trim() + "' ";
			}
			if(nomeVendedor != null){
				strSql = strSql + " and vendedor.nome = '" + nomeVendedor.trim() + "' ";
			}
			
			
			strSql = strSql + " order by dataVenda, nomeVendedor, veiculo.marca,veiculo.modelo,veiculo.versao, veiculo.placa ";
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				RelatorioComissaoVendedor comissaoVendedor = new RelatorioComissaoVendedor();
				
				
				comissaoVendedor.setPlaca(rs.getString("placa"));
				comissaoVendedor.setMarca(rs.getString("marca"));
				comissaoVendedor.setModelo(rs.getString("modelo"));
				comissaoVendedor.setVersao(rs.getString("versao"));
				comissaoVendedor.setNomeVendedor(rs.getString("nomeVendedor"));
				comissaoVendedor.setVlComissao(rs.getDouble("valorComissao"));
				comissaoVendedor.setVlBonus(rs.getDouble("valorBonus"));
				comissaoVendedor.setTotal(rs.getDouble("total"));								
				
			    Date data = rs.getDate("dataVenda");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(data);
				comissaoVendedor.setDataVenda(calendar);
			   
				
				relatorio.add(comissaoVendedor);
			}
			
			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return relatorio;
	}
	
	public List<String> getVendedores(){
		
		VendedorDAO dao1 = new VendedorDAOImpl(cnn);
		
		List<Vendedor> vendedores = dao1.pesquisaVendedor(0, null, null);
		
		List<String> nomeVendedores = new ArrayList<String>();
		for (Vendedor vendedor : vendedores) {
			nomeVendedores.add(vendedor.getNome());
		}		
		return nomeVendedores;
	}

}
