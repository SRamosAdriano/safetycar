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
import br.com.safetycar.modelos.relatorios.RelatorioLucroVeiculo;
import br.com.safetycar.relatorios.dao.RelatorioLucroVeiculoDAO;

public class RelatorioLucroVeiculoDAOImpl implements RelatorioLucroVeiculoDAO{
	
	private static Logger LOG = Logger.getLogger(RelatorioLucroVeiculoDAOImpl.class.getName());
	
	private Connection cnn;
	
	public RelatorioLucroVeiculoDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public List<RelatorioLucroVeiculo> pesquisaLucroVeiculos(String dtInicio, String dtFim, String nomeVendedor,Boolean consignado){
		
		if(dtInicio != null && dtInicio.equals("")){
			dtInicio = null;
		}
		if(dtFim != null && dtFim.equals("")){
			dtFim = null;
		}
		if(nomeVendedor != null && nomeVendedor.equals("")){
			nomeVendedor = null;
		}
		
		List<RelatorioLucroVeiculo> relatorio = new ArrayList<RelatorioLucroVeiculo>();
		
		try {			
			String strSql = "select veiculo.placa, veiculo.marca, veiculo.modelo, veiculo.versao " +
					        "       , compraVeic.dataCompra, compraVeic.valorCompra " +
					        "       , ifnull(vlcomissao.valorComissao,0) + ifnull(vlcomissao.valorBonus,0) as comissao " +
					        "       , vendaVeic.dataVenda, vendaVeic.valorVenda " +
					        "       , (select case when count(1)=0 then 0 " +
					        "                      when count(1)>0 then (select sum(valor) " +
					        "                                              from gastosveiculos gastosveiculo " +
					        "                                             where gastosveiculo.veiculo_id = veiculo.id " +
					        "                                             group by gastosveiculo.veiculo_id " +
					        "                                           ) " +
					        "                  end as veiculo " +
					        "            from gastosveiculos gv " +
					        "           where gv.veiculo_id = veiculo.id " +
					        "          ) as gastos " +
					        "       , vendaVeic.valorVenda - (valorCompra + (ifnull(vlcomissao.valorComissao,0) + ifnull(vlcomissao.valorBonus,0)) + (select case when count(1)=0 then 0 " +
					        "                                                                                                                                     when count(1)>0 then (select sum(valor) " +
					        "                                                                                                                                                             from gastosveiculos gastosveiculo " +
					        "                                                                                                                                                            where gastosveiculo.veiculo_id = veiculo.id " +
					        "                                                                                                                                                            group by gastosveiculo.veiculo_id " +
					        "                                                                                                                                                           ) " +
					        "                                                                                                                                 end as veiculo " +
					        "                                                                                                                           from gastosveiculos gv " +
					        "                                                                                                                          where gv.veiculo_id = veiculo.id " +
					        "                                                                                                                         ) " +
					        "                                )  as lucro " +
					        "  from veiculo as veiculo " +
					        "        , compraveiculo as compraVeic " +
					        "        , vlcomissaoevendaveiculo as vlcomissao " +
					        "        , vendaveiculo as vendaVeic " +
					        "        , vendedor as vendedor " +
					        " where veiculo.id = compraVeic.veiculo_id " +
					        "   and veiculo.id = vlcomissao.veiculo_id " +
					        "   and veiculo.id = vendaVeic.veiculo_id " +
					        "   and vendaVeic.vendedor_id = vendedor.id ";			
			
			if(dtInicio != null && dtFim != null){
				strSql = strSql + " and vendaVeic.dataVenda >='" + dtInicio.trim() + "' ";
				strSql = strSql + " and vendaVeic.dataVenda <='" + dtFim.trim() + "' ";
			}else if(dtInicio != null && dtFim == null){
				strSql = strSql + " and vendaVeic.dataVenda ='" + dtInicio.trim() + "' ";
			}
			if(nomeVendedor != null){
				strSql = strSql + " and vendedor.nome = '" + nomeVendedor.trim() + "' ";
			}
			if(consignado != null){
				strSql = strSql + " and compraVeic.consignado = " + consignado + " ";
			}
			
			strSql = strSql + " order by dataVenda, marca, modelo, versao, placa, lucro";
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				RelatorioLucroVeiculo lucroVeiculo = new RelatorioLucroVeiculo();
				
				lucroVeiculo.setPlaca(rs.getString("placa"));
				lucroVeiculo.setMarca(rs.getString("marca"));
				lucroVeiculo.setModelo(rs.getString("modelo"));
				lucroVeiculo.setVersao(rs.getString("versao"));
				
				Date data = rs.getDate("dataCompra");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(data);
				lucroVeiculo.setDataCompra(calendar);
				
				data = rs.getDate("dataVenda");
				calendar = Calendar.getInstance();
				calendar.setTime(data);
				lucroVeiculo.setDataVenda(calendar);
				
				lucroVeiculo.setVlCompra(rs.getDouble("valorCompra"));
				lucroVeiculo.setVlComissao(rs.getDouble("comissao"));
				lucroVeiculo.setVlGastos(rs.getDouble("gastos"));
				lucroVeiculo.setVlVenda(rs.getDouble("valorVenda"));
				lucroVeiculo.setVlLucro(rs.getDouble("lucro"));
								
				relatorio.add(lucroVeiculo);
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
