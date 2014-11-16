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

import br.com.safetycar.dao.VendedorDAO;
import br.com.safetycar.dao.impl.VendedorDAOImpl;
import br.com.safetycar.modelos.Vendedor;
import br.com.safetycar.modelos.relatorios.RelatorioVeiculosVendidos;
import br.com.safetycar.relatorios.dao.RelatorioVeiculosVendidosDAO;

public class RelatorioVeiculosVendidosDAOImpl implements RelatorioVeiculosVendidosDAO{
	
	private static Logger LOG = Logger.getLogger(RelatorioVeiculosVendidosDAOImpl.class.getName());
	
	private Connection cnn;
	
	public RelatorioVeiculosVendidosDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public List<RelatorioVeiculosVendidos> pesquisaVeiculosVendidos(String dtInicio, String dtFim, String nomeVendedor,Boolean consignado){
		
		if(dtInicio != null && dtInicio.equals("")){
			dtInicio = null;
		}
		if(dtFim != null && dtFim.equals("")){
			dtFim = null;
		}
		if(nomeVendedor != null && nomeVendedor.equals("")){
			nomeVendedor = null;
		}
		
		List<RelatorioVeiculosVendidos> relatorio = new ArrayList<RelatorioVeiculosVendidos>();
		
		try {			
			String strSql = "select veiculo.id as veiculo_id,veiculo.placa, veiculo.marca, veiculo.modelo, veiculo.versao " +
					        "        ,cliente.id as cliente_id, cliente.nomeCompleto as nomeCliente " +
					        "        , vendedor.nome as nomeVendedor " +
					        "        , venda.valorVenda as valorVenda, venda.dataVenda " +
					        "        , compra.consignado " +
					        "  from veiculo as veiculo " +
					        "        , vendaveiculo as venda " +
					        "        , clifor as cliente " +
					        "        , vendedor as vendedor " +
					        "        , compraveiculo compra " +
					        " where veiculo.id = venda.veiculo_id " +
					        "   and venda.cliente_id = cliente.id " +
					        "   and venda.vendedor_id = vendedor.id " +
					        "   and veiculo.id = compra.veiculo_id ";
			
			if(dtInicio != null && dtFim != null){
				strSql = strSql + " and venda.dataVenda >='" + dtInicio.trim() + "' ";
				strSql = strSql + " and venda.dataVenda <='" + dtFim.trim() + "' ";
			}else if(dtInicio != null && dtFim == null){
				strSql = strSql + " and venda.dataVenda ='" + dtInicio.trim() + "' ";
			}
			if(nomeVendedor != null){
				strSql = strSql + " and vendedor.nome = '" + nomeVendedor.trim() + "' ";
			}
			if(consignado != null){
				strSql = strSql + " and compra.consignado = " + consignado + " ";
			}
			
			strSql = strSql + " order by venda.dataVenda, venda.valorVenda, vendedor.nome, veiculo.marca, veiculo.modelo, veiculo.versao, veiculo.placa";
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				RelatorioVeiculosVendidos veiculoVendido = new RelatorioVeiculosVendidos();
				
				veiculoVendido.setVeiculo_id(rs.getLong("veiculo_id"));
				veiculoVendido.setPlaca(rs.getString("placa"));
				veiculoVendido.setMarca(rs.getString("marca"));
				veiculoVendido.setModelo(rs.getString("modelo"));
				veiculoVendido.setVersao(rs.getString("versao"));
				veiculoVendido.setCliente_id(rs.getLong("cliente_id"));
				veiculoVendido.setNomeCliente(rs.getString("nomeCliente"));
				veiculoVendido.setNomeVendedor(rs.getString("nomeVendedor"));
				
				Locale locale = new Locale("pt", "BR" );  
			    NumberFormat formatter = DecimalFormat.getCurrencyInstance(locale);				
			    veiculoVendido.setValorVenda(formatter.format(Double.valueOf(rs.getString("valorVenda"))));				
				
			    Date data = rs.getDate("dataVenda");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(data);
				veiculoVendido.setDataVenda(calendar);
				
				veiculoVendido.setConsignado(rs.getBoolean("consignado"));
			   
				
				relatorio.add(veiculoVendido);
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
