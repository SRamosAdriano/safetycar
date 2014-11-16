package br.com.safetycar.relatorios.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import br.com.safetycar.dao.MarcasVeiculosDAO;
import br.com.safetycar.dao.VeiculosDAO;
import br.com.safetycar.dao.VeiculosOpcionaisDAO;
import br.com.safetycar.dao.VlComissaoEVendaVeiculoDAO;
import br.com.safetycar.dao.impl.MarcasVeiculosDAOImpl;
import br.com.safetycar.dao.impl.VeiculosDAOImpl;
import br.com.safetycar.dao.impl.VeiculosOpcionaisDAOImpl;
import br.com.safetycar.dao.impl.VlComissaoEVendaVeiculoDAOImpl;
import br.com.safetycar.modelos.MarcasVeiculos;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.modelos.VeiculosOpcionais;
import br.com.safetycar.modelos.VlComissaoEVendaVeiculo;
import br.com.safetycar.modelos.relatorios.RelatorioVeiculosDisponiveis;
import br.com.safetycar.relatorios.dao.RelatorioVeiculosDisponiveisDAO;

public class RelatorioVeiculosDisponiveisDAOImpl implements RelatorioVeiculosDisponiveisDAO{
	
	private static Logger LOG = Logger.getLogger(RelatorioVeiculosDisponiveisDAOImpl.class.getName());
	
	private Connection cnn;
	
	public RelatorioVeiculosDisponiveisDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	public List<RelatorioVeiculosDisponiveis> pesquisaVeiculosDisponiveisParaVenda(String placa, String marca, String modelo){
		
		List<RelatorioVeiculosDisponiveis> relatorio = new ArrayList<RelatorioVeiculosDisponiveis>();
		
		try {			
			String strSql = " select v.id as veiculo_id, v.placa, v.marca, v.modelo, v.versao, v.anoFab, v.anoMod, c.valorVenda "
				          + "   from veiculo v "
				          + "        , vlcomissaoevendaveiculo c "
				          + "  where v.id = c.veiculo_id "
				          + "    and v.status = 'Ativo' "; 
				          
			
			if(placa != null){
				strSql = strSql + " and v.placa ='" + placa.trim() + "' ";
			}
			if(marca != null){
				strSql = strSql + " and v.marca ='" + marca.trim() + "' ";
			}
			if(modelo != null){
				strSql = strSql + " and v.modelo like('%" + modelo.trim() + "%') ";
			}
			
			strSql = strSql + "  order by v.marca, v.modelo, v.versao, v.placa";
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				RelatorioVeiculosDisponiveis veiculoDisponivel = new RelatorioVeiculosDisponiveis();
				
				veiculoDisponivel.setVeiculo_id(rs.getLong("veiculo_id"));
				veiculoDisponivel.setPlaca(rs.getString("placa"));
				veiculoDisponivel.setMarca(rs.getString("marca"));
				veiculoDisponivel.setModelo(rs.getString("modelo"));
				veiculoDisponivel.setVersao(rs.getString("versao"));				
				veiculoDisponivel.setAnoFab(Integer.parseInt(rs.getString("anoFab")));
				veiculoDisponivel.setAnoMod(Integer.parseInt(rs.getString("anoMod")));
				
				Locale locale = new Locale("pt", "BR" );  
			    NumberFormat formatter = DecimalFormat.getCurrencyInstance(locale);				
				veiculoDisponivel.setValorVenda(formatter.format(Double.valueOf(rs.getString("valorVenda"))));				
				
				relatorio.add(veiculoDisponivel);
			}
			
			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return relatorio;
	}
	
	public RelatorioVeiculosDisponiveis pesquisaVeiculo(long id){
		
		VeiculosDAO dao = new VeiculosDAOImpl(cnn);
		
		List<Veiculos> veiculos = dao.pesquisaVeiculo(id, null, null, null, null, null, null, null, null, null, null, null);
		
		Veiculos veiculo = veiculos.size() == 0 ? null : veiculos.get(0);
		
		RelatorioVeiculosDisponiveis relatorioVeiculosDisponiveis = new RelatorioVeiculosDisponiveis();
		
		if(veiculo != null){
			relatorioVeiculosDisponiveis.setAnoFab(veiculo.getAnoFab());
			relatorioVeiculosDisponiveis.setAnoMod(veiculo.getAnoMod());			
			relatorioVeiculosDisponiveis.setCombustivel(veiculo.getCombustivel());
			relatorioVeiculosDisponiveis.setCor(veiculo.getCor());
			relatorioVeiculosDisponiveis.setKm(veiculo.getKm());
			relatorioVeiculosDisponiveis.setMarca(veiculo.getMarca());
			relatorioVeiculosDisponiveis.setModelo(veiculo.getModelo());
			relatorioVeiculosDisponiveis.setPlaca(veiculo.getPlaca());
			relatorioVeiculosDisponiveis.setVeiculo_id(id);
			relatorioVeiculosDisponiveis.setVersao(veiculo.getVersao());			
		}
		
		return relatorioVeiculosDisponiveis;
	}
	
	public String buscaOpcionaisVeiculo(long idVeiculo){		
		
		VeiculosOpcionaisDAO dao = new VeiculosOpcionaisDAOImpl(cnn);
		
		VeiculosOpcionais veiculosOpcionais = dao.buscaOpcionaisVeiculos(idVeiculo);		
		
		return veiculosOpcionais.getDescricaoOpcionaisVeiculo();
	}
	
	public String pesquisaVlVendaVeiculo (long veiculoId){
		
		VlComissaoEVendaVeiculoDAO dao = new VlComissaoEVendaVeiculoDAOImpl(cnn);
		
		List<VlComissaoEVendaVeiculo> vlComissaoEVendaVeiculos = dao.pesquisaVlComissaoEVendaVeiculo (0, 0, 0, 0, veiculoId);
		
		VlComissaoEVendaVeiculo vlComissaoEVendaVeiculo = vlComissaoEVendaVeiculos.size() == 0 ? null : vlComissaoEVendaVeiculos.get(0);
		
		if(vlComissaoEVendaVeiculo != null){
			Locale locale = new Locale("pt", "BR" );  
		    NumberFormat formatter = DecimalFormat.getCurrencyInstance(locale);
			return formatter.format(vlComissaoEVendaVeiculo.getValorVenda());
		}else{
			return "0.0";
		}
	}
	
	public List<MarcasVeiculos> getMarcasVeiculos(){
		
		MarcasVeiculosDAO dao = new MarcasVeiculosDAOImpl(cnn);
		
		return dao.listaMarcasVeiculos();
	}
	

}
