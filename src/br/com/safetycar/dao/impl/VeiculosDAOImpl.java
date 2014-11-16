package br.com.safetycar.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.dao.VeiculosDAO;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.utils.PaginacaoUtils;

public class VeiculosDAOImpl extends PaginacaoUtils implements VeiculosDAO{
	
	private static Logger LOG = Logger.getLogger(VeiculosDAOImpl.class.getName());
	
	private Connection cnn;
	
	public VeiculosDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public void adicionaVeiculo(Veiculos veiculo){		
		
		try {			
			String strSql = "insert into veiculo (placa, cidade, estado, marca, modelo, versao, anoFab, anoMod " +
					        "                     , renavan, chassi, km, combustivel, cor, dataCadastro " +
					        "                     , status, infDocNome, infDocEndereco) " +
					        "             values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";		

			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setString(1, veiculo.getPlaca());
			stmt.setString(2, veiculo.getCidade());
			stmt.setString(3, veiculo.getEstado());
			stmt.setString(4, veiculo.getMarca());
			stmt.setString(5, veiculo.getModelo());
			stmt.setString(6, veiculo.getVersao());
			stmt.setInt(7, veiculo.getAnoFab());
			stmt.setInt(8, veiculo.getAnoMod());
			stmt.setLong(9, veiculo.getRenavan());
			stmt.setString(10, veiculo.getChassi());
			stmt.setLong(11, veiculo.getKm());
			stmt.setString(12, veiculo.getCombustivel());
			stmt.setString(13, veiculo.getCor());
			stmt.setDate(14, new java.sql.Date(veiculo.getDataCadastro().getTimeInMillis()));
			stmt.setString(15, "Ativo");
			stmt.setString(16, veiculo.getInfDocNome());
			stmt.setString(17, veiculo.getInfDocEndereco());
			
			stmt.execute();		
			
			stmt.close();
			
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}
	
	public void atualizaVeiculo(Veiculos veiculo){		
		
		try {			
			String strSql = " update veiculo set placa = ?, cidade = ?, estado = ?, marca = ?, modelo = ?, versao = ?, anoFab = ? " +
					        "                     , anoMod = ?, renavan = ?, chassi = ?, km = ?, combustivel = ?, cor = ?, dataCadastro = ? " +
					        "                     , infDocNome = ?, infDocEndereco = ?, status = ?" +
					        "        where id = ? ";		

			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setString(1, veiculo.getPlaca());
			stmt.setString(2, veiculo.getCidade());
			stmt.setString(3, veiculo.getEstado());
			stmt.setString(4, veiculo.getMarca());
			stmt.setString(5, veiculo.getModelo());
			stmt.setString(6, veiculo.getVersao());
			stmt.setInt(7, veiculo.getAnoFab());
			stmt.setInt(8, veiculo.getAnoMod());
			stmt.setLong(9, veiculo.getRenavan());
			stmt.setString(10, veiculo.getChassi());
			stmt.setLong(11, veiculo.getKm());
			stmt.setString(12, veiculo.getCombustivel());
			stmt.setString(13, veiculo.getCor());
			stmt.setDate(14, new java.sql.Date(veiculo.getDataCadastro().getTimeInMillis()));			
			stmt.setString(15, veiculo.getInfDocNome());
			stmt.setString(16, veiculo.getInfDocEndereco());	
			stmt.setString(17, veiculo.getStatus());
			stmt.setLong(18, veiculo.getId());
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	public void removeVeiculo (long id){
		try {
			String strSql = "delete from veiculo " +
			"                      where id = " + id;
			
							
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				
			stmt.executeUpdate();
				
			stmt.close();			

		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}
	
	public boolean existeVeiculoAtivo(String placa, long idVeiculo){
		
		if(placa != null && placa.equals("")){
			placa = null;			
		}		
		
		try {			
			String strSql ="select id from veiculo where status = \"Ativo\" ";
			
			if(placa != null){
				strSql = strSql + " and placa = \"" + placa.trim() + "\"";
			}
			if(idVeiculo > 0){
				strSql = strSql + " and id <>" + idVeiculo;
			}
						
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				stmt.close();
				return true;
			}

			stmt.close();
			return false;
			
		}catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}			
	}
	
	public int countVeiculos(long id, String placa, String marca, String modelo
			,String versao, String anoMod, String anoFab, String renavan, String chassi
			, String combustivel, String cor , String status){
		
		if(placa != null && placa.replaceAll(" ", "").length() == 0){
			placa = null;
		}
		if(marca != null && marca.equals("Selecione")){
			marca = null;			
		}
		if(modelo != null && modelo.replaceAll(" ", "").length() == 0){
			modelo = null;			
		}
		if(versao != null && versao.replaceAll(" ", "").length() == 0){
			versao = null;			
		}
		if(anoMod != null && anoMod.replaceAll(" ", "").length() == 0){
			anoMod = null;			
		}
		if(anoFab != null && anoFab.replaceAll(" ", "").length() == 0){
			anoFab = null;			
		}
		if(renavan != null && renavan.replaceAll(" ", "").length() == 0){
			renavan = null;			
		}
		if(chassi != null && chassi.replaceAll(" ", "").length() == 0){
			chassi = null;			
		}
		if(combustivel != null && combustivel.equals("Selecione")){
			combustivel = null;			
		}
		if(cor != null && cor.replaceAll(" ", "").length() == 0){
			cor = null;			
		}
		if(status != null && status.replaceAll(" ", "").length() == 0){
			status = null;			
		}
		
		try {			
			String strSql = "select count(1) as totalPaginas from veiculo where 1 = 1 ";
					        
			if(id > 0){
				strSql = strSql + " and id ='" + id + "' ";
			}			
			if(placa != null){
				strSql = strSql + " and placa ='" + placa.trim() + "' ";
			}
			if(marca != null){
				strSql = strSql + " and marca ='" + marca.trim() + "' ";
			}
			if(modelo != null){
				strSql = strSql + " and modelo like('%" + modelo.trim() + "%') ";
			}
			if(versao != null){
				strSql = strSql + " and versao like('%" + versao.trim() + "%') ";
			}
			if(anoMod != null){
				strSql = strSql + " and anoMod ='" + anoMod.trim() + "' ";
			}
			if(anoFab != null){
				strSql = strSql + " and anoFab ='" + anoFab.trim() + "' ";
			}
			if(renavan != null){
				strSql = strSql + " and renavan ='" + renavan.trim() + "' ";
			}
			if(chassi != null){
				strSql = strSql + " and chassi ='" + chassi.trim() + "' ";
			}
			if(combustivel != null){
				strSql = strSql + " and combustivel ='" + combustivel.trim() + "' ";
			}
			if(cor != null){
				strSql = strSql + " and cor like('%" + cor.trim() + "%') ";
			}
			if(status != null){
				strSql = strSql + " and status ='" + status.trim() + "' ";
			}
						
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			ResultSet rs = stmt.executeQuery();
			
			int totalPaginas = 0;
			while (rs.next()) {
				totalPaginas = rs.getInt("totalPaginas");
			}			
			
			stmt.close();
			
			return totalPaginas;
			
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}	
	}
	
	public List<Veiculos> pesquisaVeiculo (long id, String placa, String marca, String modelo
			,String versao, String anoMod, String anoFab, String renavan, String chassi
			, String combustivel, String cor , String status){
		
		return  pesqVeiculo (id, placa, marca, modelo, versao, anoMod, anoFab, renavan, chassi, combustivel, cor, status, 0, 0);
	}
	
	public List<Veiculos> pesquisaVeiculo (long id, String placa, String marca, String modelo
			,String versao, String anoMod, String anoFab, String renavan, String chassi
			, String combustivel, String cor , String status, int numeroPaginaAtual, int qtdePorPagina){
		
		return  pesqVeiculo (id, placa, marca, modelo, versao, anoMod, anoFab, renavan, chassi, combustivel, cor, status, numeroPaginaAtual, qtdePorPagina);
	}
	
	private List<Veiculos> pesqVeiculo (long id, String placa, String marca, String modelo
			,String versao, String anoMod, String anoFab, String renavan, String chassi
			, String combustivel, String cor , String status, int numeroPaginaAtual, int qtdePorPagina){
		
		if(placa != null && placa.replaceAll(" ", "").length() == 0){
			placa = null;			
		}
		if(marca != null && marca.equals("Selecione")){
			marca = null;			
		}
		if(modelo != null && modelo.replaceAll(" ", "").length() == 0){
			modelo = null;			
		}
		if(versao != null && versao.replaceAll(" ", "").length() == 0){
			versao = null;			
		}
		if(anoMod != null && anoMod.replaceAll(" ", "").length() == 0){
			anoMod = null;			
		}
		if(anoFab != null && anoFab.replaceAll(" ", "").length() == 0){
			anoFab = null;			
		}
		if(renavan != null && renavan.replaceAll(" ", "").length() == 0){
			renavan = null;			
		}
		if(chassi != null && chassi.replaceAll(" ", "").length() == 0){
			chassi = null;			
		}
		if(combustivel != null && combustivel.equals("Selecione")){
			combustivel = null;			
		}
		if(cor != null && cor.replaceAll(" ", "").length() == 0){
			cor = null;			
		}
		if(status != null && status.replaceAll(" ", "").length() == 0){
			status = null;			
		}
		List<Veiculos> veiculos = new ArrayList<Veiculos>();
		
		try {			
			String strSql = " select id, placa, cidade, estado, marca, versao, modelo, anoFab, anoMod " +
					        "       , renavan, chassi, km, combustivel, cor, dataCadastro, status, infDocNome, infDocEndereco " +
					        "   from veiculo " +
					        "  where 1 = 1 ";
			if(id > 0){
				strSql = strSql + " and id ='" + id + "' ";
			}			
			if(placa != null){
				strSql = strSql + " and placa ='" + placa.trim() + "' ";
			}
			if(marca != null){
				strSql = strSql + " and marca ='" + marca.trim() + "' ";
			}
			if(modelo != null){
				strSql = strSql + " and modelo like('%" + modelo.trim() + "%') ";
			}
			if(versao != null){
				strSql = strSql + " and versao like('%" + versao.trim() + "%') ";
			}
			if(anoMod != null){
				strSql = strSql + " and anoMod ='" + anoMod.trim() + "' ";
			}
			if(anoFab != null){
				strSql = strSql + " and anoFab ='" + anoFab.trim() + "' ";
			}
			if(renavan != null){
				strSql = strSql + " and renavan ='" + renavan.trim() + "' ";
			}
			if(chassi != null){
				strSql = strSql + " and chassi ='" + chassi.trim() + "' ";
			}
			if(combustivel != null){
				strSql = strSql + " and combustivel ='" + combustivel.trim() + "' ";
			}
			if(cor != null){
				strSql = strSql + " and cor like('%" + cor.trim() + "%') ";
			}
			if(status != null){
				strSql = strSql + " and status ='" + status.trim() + "' ";
			}
			strSql = strSql + " order by marca ";
			
			if(numeroPaginaAtual > 0 && qtdePorPagina > 0){
				strSql = strSql + " LIMIT "+ parametroLimit(numeroPaginaAtual, qtdePorPagina) +", "+ qtdePorPagina;
			}
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Veiculos veiculo = new Veiculos();
				
				veiculo.setId(rs.getLong("id"));
				veiculo.setPlaca(rs.getString("placa"));
				veiculo.setCidade(rs.getString("cidade"));
				veiculo.setEstado(rs.getString("estado"));
				veiculo.setMarca(rs.getString("marca"));
				veiculo.setVersao(rs.getString("versao"));
				veiculo.setModelo(rs.getString("modelo"));
				veiculo.setAnoFab(rs.getInt("anoFab"));
				veiculo.setAnoMod(rs.getInt("anoMod"));
				veiculo.setRenavan(rs.getLong("renavan"));
				veiculo.setChassi(rs.getString("chassi"));
				veiculo.setKm(rs.getLong("km"));
				veiculo.setCombustivel(rs.getString("combustivel"));
				veiculo.setCor(rs.getString("cor"));
									
				Date data = rs.getDate("dataCadastro");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(data);
				veiculo.setDataCadastro(calendar);
				
				veiculo.setStatus(rs.getString("status"));
				veiculo.setInfDocNome(rs.getString("infDocNome"));
				veiculo.setInfDocEndereco(rs.getString("infDocEndereco"));
				
				veiculos.add(veiculo);				
			}			
			
			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
		return veiculos;
		
	}

}
